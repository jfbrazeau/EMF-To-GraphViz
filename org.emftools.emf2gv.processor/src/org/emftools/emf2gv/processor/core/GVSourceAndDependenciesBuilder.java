/**
 * Copyright (c) 2010, Jean-Francois Brazeau. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 * 
 *  1. Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 * 
 *  2. Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 * 
 *  3. The name of the author may not be used to endorse or promote products
 *     derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIEDWARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS
 * OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 * TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE
 * USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.emftools.emf2gv.processor.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.emftools.emf2gv.graphdesc.AttributeFigure;
import org.emftools.emf2gv.graphdesc.ClassFigure;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.graphdesc.ReferenceFigure;
import org.emftools.emf2gv.processor.Activator;
import org.emftools.emf2gv.util.ColorsHelper;
import org.emftools.emf2gv.util.EMFHelper;
import org.emftools.emf2gv.util.IOHelper;

public class GVSourceAndDependenciesBuilder {

	private volatile boolean isClosed = false;

	private StringWriter buffer = new StringWriter();

	private PrintWriter out = new PrintWriter(buffer);

	private Map<EObject, String> eObjectIdsCache = new HashMap<EObject, String>();

	private Map<EClass, List<NodeDesc>> nodesListByEclass = new HashMap<EClass, List<NodeDesc>>();

	private List<EdgeDesc> edges = new ArrayList<EdgeDesc>();

	private GVFigureDescription figureDesc;

	private AdapterFactory adapterFactory;

	private Map<URL, String> iconsUrlsToPathsMap = new HashMap<URL, String>();

	private IFolder iconsFolder;

	private int nodesCount = 0;

	private int edgesCount = 0;

	private Diagnostician diagnostician;

	public GVSourceAndDependenciesBuilder(GVFigureDescription figureDesc,
			IFolder iconsFolder, boolean addValidationDecorators) {
		this.figureDesc = figureDesc;
		this.adapterFactory = EMFHelper.getAdapterFactory(figureDesc
				.getEPackages());
		this.iconsFolder = iconsFolder;

		// Nodes map initialization
		for (ClassFigure classFigure : figureDesc.getClassFigures()) {
			nodesListByEclass.put(classFigure.getEClass(),
					new ArrayList<NodeDesc>());
		}

		// Diagnostician initialization
		diagnostician = !addValidationDecorators ? null : new Diagnostician() {
			// This method is overrided to prevent from
			// validating recursively the eObject's eContent
			@Override
			protected boolean doValidateContents(EObject eObject,
					DiagnosticChain diagnostics, Map<Object, Object> context) {
				return true;
			}
		};
	}

	public void process(EObject eContentRoot, IProgressMonitor monitor)
			throws CoreException {
		process(Arrays.asList(new EObject[] { eContentRoot }), monitor);
	}

	public synchronized void process(List<EObject> eContentRoots,
			IProgressMonitor monitor) throws CoreException {
		try {
			if (isClosed) {
				throw new IllegalStateException(
						"The GV Source builder is closed");
			}
			// Nodes & edges extraction
			for (EObject eContentRoot : eContentRoots) {
				processEObject(eContentRoot, monitor);
			}
			// GraphViz source build
			flushHeader();
			for (ClassFigure classFigure : figureDesc.getClassFigures()) {
				if (figureDesc.isAlignSameEClasses()) {
					out.print("{ rank = same;\n");
				}
				for (NodeDesc nodeDesc : nodesListByEclass.get(classFigure
						.getEClass())) {
					flushNode(nodeDesc);
				}
				if (figureDesc.isAlignSameEClasses()) {
					out.print("}\n");
				}
			}
			for (EdgeDesc edgeDesc : edges) {
				flushEdge(edgeDesc);
			}
			flushFooter();
		} finally {
			isClosed = true;
		}
	}

	public String getGvSource() {
		return buffer.toString();
	}

	public int getNodesCount() {
		return nodesCount;
	}

	public int getEdgesCount() {
		return edgesCount;
	}

	private String processEObject(EObject eContentRoot, IProgressMonitor monitor)
			throws CoreException {
		String eContentRootId = eObjectIdsCache.get(eContentRoot);
		if (eContentRootId == null) {
			EClass eContentRootEClass = eContentRoot.eClass();
			ClassFigure classFigure = figureDesc
					.getClassFigure(eContentRootEClass);
			// We proceed only if the classFigure has been defined
			if (classFigure != null) {
				eContentRootId = buildEObjectIdentifier(classFigure,
						eContentRoot);
				eObjectIdsCache.put(eContentRoot, eContentRootId);

				// EObject icon retrieval
				String iconPath = findAndSaveEObjectIcon(eContentRoot, monitor);

				// EObject validation and status icons retrieval
				boolean addValidationDecorators = (diagnostician != null);
				String statusIconPath = null;
				if (addValidationDecorators) {
					Diagnostic diagnostic = diagnostician.validate(eContentRoot);
					switch (diagnostic.getSeverity()) {
					case Diagnostic.ERROR:
						statusIconPath = findAndSavePluginIcon("error", monitor);
						break;
					case Diagnostic.WARNING:
						statusIconPath = findAndSavePluginIcon("warning", monitor);
						break;
					}
				}

				// Node description creation and registration
				NodeDesc nodeDesc = new NodeDesc();
				nodeDesc.classFigure = classFigure;
				nodeDesc.eObject = eContentRoot;
				nodeDesc.eObjectId = eContentRootId;
				nodeDesc.iconPath = iconPath;
				nodeDesc.statusIconPath = statusIconPath != null ? statusIconPath
						: null;
				List<NodeDesc> nodeList = nodesListByEclass.get(classFigure
						.getEClass());
				nodeList.add(nodeDesc);

				// EReferences browsing
				List<ReferenceFigure> refFigures = classFigure
						.getReferenceFigures();
				for (ReferenceFigure referenceFigure : refFigures) {
					EReference eReference = referenceFigure.getEReference();
					List<EObject> targetEObjects = getTargetRefEObjects(
							eContentRoot, eReference);
					// Edges figures generation
					for (EObject targetEObject : targetEObjects) {
						String targetEObjectId = processEObject(targetEObject,
								monitor);
						// the target EObject Id may hav not been built if the
						// target EObject's Eclass
						// is not associated to a ClassFigure.
						if (targetEObjectId != null) {
							EdgeDesc edgeDesc = new EdgeDesc();
							edgeDesc.referenceFigure = referenceFigure;
							edgeDesc.srcEObjectId = eContentRootId;
							edgeDesc.targetEObjectId = targetEObjectId;
							edges.add(edgeDesc);
						}
					}
				}
			}
		}
		return eContentRootId;
	}

	private String findAndSavePluginIcon(String name, IProgressMonitor monitor) {
		IPath iconPath = null;
		URL url = null;
		String iconFullPath = null;
		try {
			url = Activator.getDefault().getBundle()
					.getEntry("/icons/" + name + ".gif");
			iconFullPath = iconsUrlsToPathsMap.get(url);
			if (iconFullPath == null) {
				// Icon path building
				iconPath = iconsFolder.getFullPath().append("/")
						.append(name + ".png");
				// Copy the image to a PNG File
				iconFullPath = copyImageToPng(url, iconPath, monitor);
			}
		}
		// If we meet a problem, we simply log the error
		// and proceed (the returned image full path is
		// null as if there was no image available for this
		// EObject)
		catch (IOException e) {
			Activator.getDefault().logError(
					"Unexpected error while retrieving image '" + url + "'", e);
		} catch (CoreException e) {
			Activator.getDefault().logError(
					"Unexpected error while saving PNG image '" + iconPath
							+ "'", e);
		}
		return iconFullPath;
	}

	private String findAndSaveEObjectIcon(EObject eObject,
			IProgressMonitor monitor) {
		String iconFullPath = null;
		IPath iconPath = null;
		IItemLabelProvider labelProvider = (IItemLabelProvider) adapterFactory
				.adapt(eObject, IItemLabelProvider.class);
		if (labelProvider != null) {
			Object image = labelProvider.getImage(eObject);
			if (image instanceof URL) {
				URL url = (URL) image;
				iconFullPath = iconsUrlsToPathsMap.get(url);
				if (iconFullPath == null) {
					try {
						// Filename retreival
						String str = url.toString().replaceAll("\\\\", "/");
						str = str.substring(str.lastIndexOf('/') + 1);
						int idx = str.indexOf('?');
						if (idx > -1) {
							str = str.substring(0, idx);
						}
						// File extension deletion
						str = str.substring(0, str.lastIndexOf('.'));

						// If there are several EPackages in the
						// graphical description, the NsUri hashcode
						// is included in the file name to prevent
						// from getting the same filename if two
						// EClasses in two EPackages have the
						// same name
						if (figureDesc.getEPackages().size() > 1) {
							str += '_';
							str += Integer.toHexString(eObject.eClass()
									.getEPackage().getNsURI().hashCode());
						}
						str += ".png";

						// Icon path building
						iconPath = iconsFolder.getFullPath().append("/")
								.append(str);

						// Copy the image to a PNG File
						iconFullPath = copyImageToPng(url, iconPath, monitor);
					}
					// If we meet a problem, we simply log the error
					// and proceed (the returned image full path is
					// null as if there was no image available for this
					// EObject)
					catch (IOException e) {
						Activator.getDefault().logError(
								"Unexpected error while retrieving image '"
										+ url + "'", e);
					} catch (CoreException e) {
						Activator.getDefault().logError(
								"Unexpected error while saving PNG image '"
										+ iconPath + "'", e);
					}
				}
			}
		}
		return iconFullPath;
	}

	private String copyImageToPng(URL url, IPath iconPath,
			IProgressMonitor monitor) throws IOException, CoreException {
		// Image file loading and conversion into PNG
		byte[] pngImage = IOHelper.loadAndConvertImageToPng(url.openStream());

		// Saves the PNG image
		IFile iconFile = IOHelper.save(iconPath, pngImage, monitor);

		// Get the file absolute path
		String iconFullPath = iconFile.getRawLocation().toString()
				.replace('\\', '/');
		iconsUrlsToPathsMap.put(url, iconFullPath);
		return iconFullPath;
	}

	private static List<EObject> getTargetRefEObjects(EObject eObject,
			EReference eReference) {
		List<EObject> targetEObjects = new ArrayList<EObject>();
		Object value = eObject.eGet(eReference);
		if (value != null) {
			if (eReference.getUpperBound() == 1) {
				targetEObjects.add((EObject) value);
			} else {
				@SuppressWarnings("unchecked")
				List<EObject> eObjects = (List<EObject>) value;
				targetEObjects.addAll(eObjects);
			}
		}
		return targetEObjects;
	}

	private void flushEdge(EdgeDesc edgeDesc) {
		ReferenceFigure referenceFigure = edgeDesc.referenceFigure;
		out.print('\t');
		out.print(edgeDesc.srcEObjectId);
		out.print("->");
		out.print(edgeDesc.targetEObjectId);
		// Arrow style
		out.print(" [arrowhead = ");
		out.print(referenceFigure.getTargetArrowType().toString());
		out.print(", arrowtail = ");
		out.print(referenceFigure.getSourceArrowType().toString());
		out.println("]");

		// Edges count update
		edgesCount++;
	}

	private void flushNode(NodeDesc nodeDesc) throws CoreException {
		ClassFigure classFigure = nodeDesc.classFigure;
		/*
		 * Node generation.
		 */
		out.print('\t');
		out.print(nodeDesc.eObjectId);
		out.print(" [label=<\n"
				+ "\t\t<TABLE BORDER=\"0\" CELLBORDER=\"1\" CELLSPACING=\"0\" CELLPADDING=\"0\">\n"
				+ "\t\t\t<TR><TD BGCOLOR=\"");
		out.print(ColorsHelper.toHtmlColor(classFigure
				.getHeaderBackgroundColor()));
		out.println("\">");

		out.println("\t\t\t\t<TABLE BORDER=\"0\" CELLBORDER=\"0\" CELLSPACING=\"0\" CELLPADDING=\"1\">\n\t\t\t\t\t<TR>");
		/*
		 * EObject icon processing
		 */
		boolean iconAvailable = nodeDesc.iconPath != null;
		boolean statusIconAvailable = nodeDesc.statusIconPath != null;
		if (iconAvailable) {
			out.print("\t\t\t\t\t\t<TD ALIGN=\"RIGHT\"><IMG SCALE=\"FALSE\" SRC=\"");
			out.print(nodeDesc.iconPath);
			out.println("\"/></TD>");
		}

		// Label attribute value retrieval
		EAttribute labelEAttribute = classFigure.getLabelEAttribute();
		String label = null;
		if (labelEAttribute != null) {
			Object labelObject = nodeDesc.eObject.eGet(labelEAttribute);
			label = labelObject != null ? labelObject.toString() : null;
			if (label != null) {
				label = label.trim();
				if ("".equals(label)) {
					label = null;
				}
			}
		}

		// TD tag for the label
		out.print("\t\t\t\t\t\t<TD ALIGN=\"");
		out.print(iconAvailable && !statusIconAvailable ? "LEFT" : "CENTER");
		out.print("\">");

		// If there is no icon or if we have no label to show, we
		// print the EClass name
		if (!iconAvailable || label == null) {
			out.print(classFigure.getEClass().getName());
			if (label != null) {
				out.print(" : ");
			}
		}

		// If we have a label to show
		if (label != null) {
			out.print(toHtmlString(label));
		}

		out.println("</TD>");

		// Status icon
		if (statusIconAvailable) {
			out.print("\t\t\t\t\t\t<TD ALIGN=\"LEFT\"><IMG SCALE=\"FALSE\" SRC=\"");
			out.print(nodeDesc.statusIconPath);
			out.println("\"/></TD>");
		}
		out.println("\t\t\t\t\t</TR>");
		out.println("\t\t\t\t</TABLE>");
		out.println("\t\t\t</TD></TR>");
		out.print("\t\t\t<TR><TD ALIGN=\"LEFT\" BGCOLOR=\"");
		out.print(ColorsHelper.toHtmlColor(classFigure.getBodyBackgroundColor()));
		out.println("\">");
		EList<AttributeFigure> attrFigures = classFigure.getAttributeFigures();
		if (attrFigures.size() == 0) {
			out.println("\t\t\t\t&nbsp;");
		} else {
			out.println("\t\t\t\t<TABLE BORDER=\"0\" CELLBORDER=\"0\" CELLSPACING=\"0\">");
			for (AttributeFigure attrFigure : attrFigures) {
				EAttribute eAttribute = attrFigure.getEAttribute();
				String attrLabel = attrFigure.getLabel();
				if (attrLabel == null || "".equals(attrLabel.trim())) {
					attrLabel = eAttribute.getName();
				}
				attrLabel = attrLabel.trim();
				out.print("\t\t\t\t\t<TR><TD ALIGN=\"LEFT\">");
				out.print(attrLabel);
				out.print(" :</TD><TD ALIGN=\"LEFT\">");
				String attrValue = String.valueOf(nodeDesc.eObject
						.eGet(eAttribute));
				out.print(toHtmlString(attrValue));
				out.println("</TD></TR>");
			}
			out.println("\t\t\t\t</TABLE>");
		}
		out.println("\t\t\t</TD></TR>");
		out.println("\t\t</TABLE>>, margin=0, shape=plaintext]");

		// Nodes count update
		nodesCount++;
	}

	private String toHtmlString(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		return str;
	}

	private String buildEObjectIdentifier(ClassFigure classFigure,
			EObject eObject) {
		StringWriter buf = new StringWriter();
		buf.append(classFigure.getName().replaceAll("[ \\.]", "_"));
		buf.append("_");
		buf.append(String.valueOf(eObject.hashCode()));
		return buf.toString();
	}

	private void flushHeader() {
		out.println("digraph {");
		out.print("\trankdir = ");
		switch (figureDesc.getOrientation()) {
		case TOP_DOWN:
			out.print("TD");
			break;
		default:
			out.print("LR");
			break;
		}
		out.println(";");
	}

	private void flushFooter() {
		out.println("}");
	}

}

class NodeDesc {
	ClassFigure classFigure;
	EObject eObject;
	String eObjectId;
	String iconPath;
	String statusIconPath;
}

class EdgeDesc {
	ReferenceFigure referenceFigure;
	String srcEObjectId;
	String targetEObjectId;
}
