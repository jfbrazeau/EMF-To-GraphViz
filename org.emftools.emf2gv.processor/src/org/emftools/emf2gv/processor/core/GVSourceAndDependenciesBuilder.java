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
import org.emftools.emf2gv.graphdesc.ArrowStyle;
import org.emftools.emf2gv.graphdesc.ArrowType;
import org.emftools.emf2gv.graphdesc.AttributeFigure;
import org.emftools.emf2gv.graphdesc.ClassFigure;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.graphdesc.ReferenceFigure;
import org.emftools.emf2gv.processor.Activator;
import org.emftools.emf2gv.util.ColorsHelper;
import org.emftools.emf2gv.util.EMFHelper;
import org.emftools.emf2gv.util.IOHelper;

/**
 * Graphviz source builder.
 * 
 * An instance of this builder can only be used once. The processing method is
 * synchronized so that two threads cannot interfere.
 * 
 * A boolean is used to ensure that when the builder has finished the generation
 * job, it cannot be reused.
 * 
 */
public class GVSourceAndDependenciesBuilder {

	/**
	 * A boolean indicating if the builder is closed.
	 */
	private volatile boolean isClosed = false;

	/**
	 * The buffer receiving the graphvis source.
	 */
	private StringWriter buffer = new StringWriter();

	/**
	 * A print writer on top of the graphviz source buffer giving some helpful
	 * utility methods.
	 */
	private PrintWriter out = new PrintWriter(buffer);

	/**
	 * A cache containing the eObjects's identifiers.
	 */
	private Map<EObject, String> eObjectIdsCache = new HashMap<EObject, String>();

	/**
	 * The edges list.
	 */
	private List<EdgeDesc> edges = new ArrayList<EdgeDesc>();

	/**
	 * The non contained nodes list.
	 */
	private List<NodeDesc> rootNodeList = new ArrayList<NodeDesc>();

	/**
	 * The graphical description.
	 */
	private GVFigureDescription figureDesc;

	/**
	 * The adapter factory.
	 */
	private AdapterFactory adapterFactory;

	/**
	 * A map containing the associations between icons URL and generated PNG
	 * files.
	 */
	private Map<URL, String> iconUrlsToPngImagePathsAssociationMap = new HashMap<URL, String>();

	/**
	 * the folder receiving the PNG icons.
	 */
	private IFolder iconsFolder;

	/**
	 * The nodes count.
	 */
	private int nodesCount = 0;

	/**
	 * The edges count.
	 */
	private int edgesCount = 0;

	/**
	 * The diagnostician used to validate the EObjects.
	 */
	private Diagnostician diagnostician;

	/**
	 * Default constructor.
	 * 
	 * @param figureDesc
	 *            the figure description.
	 * @param iconsFolder
	 *            the folder receiving the PNG icons.
	 * @param addValidationDecorators
	 *            a boolean indicating if validation decorators have to be
	 *            included in the diagram.
	 */
	public GVSourceAndDependenciesBuilder(GVFigureDescription figureDesc,
			IFolder iconsFolder, boolean addValidationDecorators) {
		this.figureDesc = figureDesc;
		this.adapterFactory = EMFHelper.getAdapterFactory(figureDesc
				.getEPackages());
		this.iconsFolder = iconsFolder;

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

	/**
	 * Generates a graphviz source for a given root.
	 * 
	 * @param eContentRoot
	 *            the root to process.
	 * @param monitor
	 *            a progress monitor.
	 * @throws CoreException
	 *             thrown if an unexpected error occurs.
	 */
	public void process(EObject eContentRoot, IProgressMonitor monitor)
			throws CoreException {
		process(Arrays.asList(new EObject[] { eContentRoot }), monitor);
	}

	/**
	 * Generates a graphviz source for a given list of roots.
	 * 
	 * @param eContentRoots
	 *            the roots to process.
	 * @param monitor
	 *            a progress monitor.
	 * @throws CoreException
	 *             thrown if an unexpected error occurs.
	 */
	public synchronized void process(List<EObject> eContentRoots,
			IProgressMonitor monitor) throws CoreException {
		try {
			if (isClosed) {
				throw new IllegalStateException(
						"The GV Source builder is closed");
			}
			// Nodes & edges extraction
			for (EObject eContentRoot : eContentRoots) {
				// If the root has no classfigure, the containment references
				// are
				// automatically processed (it is authorized to build a diagram
				// with a root node that is not represented => its containement
				// references are then processed)
				EClass eContentRootEClass = eContentRoot.eClass();
				ClassFigure classFigure = figureDesc
						.getClassFigure(eContentRootEClass);
				if (classFigure != null) {
					processEObject(rootNodeList, eContentRoot, monitor);
				} else {
					List<EObject> eContentRootChilds = eContentRoot.eContents();
					for (EObject eContentRootChild : eContentRootChilds) {
						processEObject(rootNodeList, eContentRootChild, monitor);
					}
				}
			}
			// GraphViz source build
			flushHeader();
			// TODO reimplement align same eclass
			for (NodeDesc nodeDesc : rootNodeList) {
				flushNode(nodeDesc);
			}
			for (EdgeDesc edgeDesc : edges) {
				flushEdge(edgeDesc);
			}
			flushFooter();
		} finally {
			isClosed = true;
		}
	}

	/**
	 * @return the graphiz source buffer.
	 */
	public String getGvSource() {
		return buffer.toString();
	}

	/**
	 * @return the nodes count.
	 */
	public int getNodesCount() {
		return nodesCount;
	}

	/**
	 * @return the adgees count.
	 */
	public int getEdgesCount() {
		return edgesCount;
	}

	/**
	 * Processes an EObject and its childs recursively.
	 * 
	 * @param containerList
	 *            the container list (the main list if the eObject's figure is
	 *            not conained in a parent container, or the parent container
	 *            otherwise.
	 * @param eContentRoot
	 *            the root EObjet.
	 * @param monitor
	 *            a progress monitor.
	 * @return the eObject's identifier.
	 * @throws CoreException
	 *             thrown if an unexpected error occurs.
	 */
	private String processEObject(List<NodeDesc> containerList,
			EObject eContentRoot, IProgressMonitor monitor)
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
				String validationDecoratorIconPath = null;
				if (addValidationDecorators) {
					Diagnostic diagnostic = diagnostician
							.validate(eContentRoot);
					switch (diagnostic.getSeverity()) {
					case Diagnostic.ERROR:
						validationDecoratorIconPath = findAndSavePluginIcon(
								"error", monitor);
						break;
					case Diagnostic.WARNING:
						validationDecoratorIconPath = findAndSavePluginIcon(
								"warning", monitor);
						break;
					}
				}

				// Node description creation and registration
				NodeDesc nodeDesc = new NodeDesc();
				nodeDesc.classFigure = classFigure;
				nodeDesc.eObject = eContentRoot;
				nodeDesc.eObjectId = eContentRootId;
				nodeDesc.iconPath = iconPath;
				nodeDesc.validationDecoratorIconPath = validationDecoratorIconPath != null ? validationDecoratorIconPath
						: null;
				if (classFigure.isContainer()) {
					nodeDesc.nestedNodes = new ArrayList<NodeDesc>();
				}
				containerList.add(nodeDesc);

				// Nested figures EReferences browsing
				for (EReference eReference : classFigure
						.getNestedFiguresEReferences()) {
					List<EObject> targetEObjects = getTargetRefEObjects(
							eContentRoot, eReference);
					// Nested figures generation
					for (EObject targetEObject : targetEObjects) {
						processEObject(nodeDesc.nestedNodes, targetEObject,
								monitor);
					}
				}

				// EReferences browsing
				List<ReferenceFigure> refFigures = classFigure
						.getReferenceFigures();
				for (ReferenceFigure referenceFigure : refFigures) {
					EReference eReference = referenceFigure.getEReference();
					List<EObject> targetEObjects = getTargetRefEObjects(
							eContentRoot, eReference);
					// Edges figures generation
					for (EObject targetEObject : targetEObjects) {
						String targetEObjectId = processEObject(rootNodeList,
								targetEObject, monitor);
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

	/**
	 * Finds an icon retrieved from a plugin (and saves it as a PNG file the
	 * first time).
	 * 
	 * @param name
	 *            the icon name.
	 * @param monitor
	 *            a progress monitor.
	 * @return the icon path.
	 */
	private String findAndSavePluginIcon(String name, IProgressMonitor monitor) {
		IPath iconPath = null;
		URL url = null;
		String iconFullPath = null;
		try {
			url = Activator.getDefault().getBundle()
					.getEntry("/icons/" + name + ".gif");
			iconFullPath = iconUrlsToPngImagePathsAssociationMap.get(url);
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

	/**
	 * Finds the icon associated to the given EObject (and saves it as a PNG
	 * file the first time).
	 * 
	 * @param eObject
	 *            the EObject.
	 * @param monitor
	 *            a progress monitor.
	 * @return the icon path.
	 */
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
				iconFullPath = iconUrlsToPngImagePathsAssociationMap.get(url);
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

	/**
	 * Copies a image to a new file under a PNG format.
	 * 
	 * @param sourceImageUrl
	 *            the source image url.
	 * @param targetPngImagePath
	 *            the target png image path.
	 * @param monitor
	 *            a progress monitor.
	 * @return the PNG file path.
	 * @throws IOException
	 *             thrown if an I/O error occurs.
	 * @throws CoreException
	 *             thrown if an unexpected error occurs.
	 */
	private String copyImageToPng(URL sourceImageUrl, IPath targetPngImagePath,
			IProgressMonitor monitor) throws IOException, CoreException {
		// Image file loading and conversion into PNG
		byte[] pngImage = IOHelper.loadAndConvertImageToPng(sourceImageUrl
				.openStream());

		// Saves the PNG image
		IFile iconFile = IOHelper.save(targetPngImagePath, pngImage, monitor);

		// Get the file absolute path
		String iconFullPath = iconFile.getRawLocation().toString()
				.replace('\\', '/');

		// Registers the icon path in the map
		iconUrlsToPngImagePathsAssociationMap.put(sourceImageUrl, iconFullPath);

		// Returns the result
		return iconFullPath;
	}

	/**
	 * Retrieves the EObjects targeted by an EReference for a given EObject.
	 * 
	 * @param eObject
	 *            the EObject.
	 * @param eReference
	 *            the EReference.
	 * @return the target EObject list.
	 */
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

	/**
	 * Flushes an edge in the graphviz source buffer.
	 * 
	 * @param edgeDesc
	 *            the edge description to flush.
	 */
	private void flushEdge(EdgeDesc edgeDesc) {
		ReferenceFigure referenceFigure = edgeDesc.referenceFigure;
		out.print('\t');
		out.print(edgeDesc.srcEObjectId);
		out.print("->");
		out.print(edgeDesc.targetEObjectId);
		// Arrow style
		out.print(" [arrowhead = ");
		out.print(referenceFigure.getTargetArrowType().equals(ArrowType.CUSTOM) ? referenceFigure
				.getCustomTargetArrow() : referenceFigure.getTargetArrowType()
				.toString());
		out.print(", arrowtail = ");
		out.print(referenceFigure.getSourceArrowType().equals(ArrowType.CUSTOM) ? referenceFigure
				.getCustomSourceArrow() : referenceFigure.getSourceArrowType()
				.toString());
		out.print(", color=\"");
		out.print(ColorsHelper.toHtmlColor(referenceFigure.getColor()));
		out.print("\"");
		if (!ArrowStyle.NORMAL.equals(referenceFigure.getStyle())) {
			out.print(", style=");
			out.print(referenceFigure.getStyle().toString());
		}
		out.println("]");

		// Edges count update
		edgesCount++;
	}

	/**
	 * Flushes a node in the graphviz source buffer.
	 * 
	 * @param nodeDesc
	 *            the node description to flush.
	 * @throws CoreException
	 *             thrown if an unexpected error occurs.
	 */
	private void flushNode(NodeDesc nodeDesc) throws CoreException {
		if (nodeDesc.classFigure.isContainer()) {
			flushClusterNode(nodeDesc);
		}
		else {
			flushStandardNode(nodeDesc);
		}
		// Nodes count update
		nodesCount++;
	}
	// TODO Javadoc
	private void flushClusterNode(NodeDesc nodeDesc) throws CoreException {
		ClassFigure classFigure = nodeDesc.classFigure;
		/*
		 * Node generation.
		 */
		out.print("\tsubgraph cluster_");
		out.print(nodeDesc.eObjectId);
		out.println(" { label=<");
		flushNodeHeader("\t\t", nodeDesc);
		out.print("\t\t>; bgcolor=\"");
		out.print(ColorsHelper.toHtmlColor(classFigure
				.getHeaderBackgroundColor()));
		out.println("\"; labeljust=\"l\";");

		if (classFigure.getAttributeFigures().size() > 0) {
			out.print("\t\t");
			out.print(nodeDesc.eObjectId);
			out.print("_attributes");
			out.print(" [label=<\n"
					+ "\t\t<TABLE BORDER=\"0\" CELLBORDER=\"1\" CELLSPACING=\"0\" CELLPADDING=\"0\">\n"
					+ "\t\t\t<TR><TD BGCOLOR=\"");
			out.print(ColorsHelper.toHtmlColor(classFigure.getBodyBackgroundColor()));
			out.println("\">");
			flushNodeAttributes(nodeDesc);
			out.println("\t\t\t</TD></TR>");
			out.println("\t\t</TABLE>>, margin=0, shape=plaintext]");
		}
		// Flush nested nodes
		for (NodeDesc nestedNodeDesc : nodeDesc.nestedNodes) {
			flushNode(nestedNodeDesc);
		}
		out.println("\t\t}");
	}

	// TODO Javadoc
	private void flushStandardNode(NodeDesc nodeDesc) throws CoreException {
		ClassFigure classFigure = nodeDesc.classFigure;
		/*
		 * Node generation.
		 */
		out.print('\t');
		out.print(nodeDesc.eObjectId);
		out.println(" [label=<");
		out.println("\t\t<TABLE BORDER=\"0\" CELLBORDER=\"1\" CELLSPACING=\"0\" CELLPADDING=\"0\">");
		out.print("\t\t\t<TR><TD BGCOLOR=\"");
		out.print(ColorsHelper.toHtmlColor(classFigure
				.getHeaderBackgroundColor()));
		out.println("\">");
		// Flushes the node header
		flushNodeHeader("\t\t\t\t", nodeDesc);
		
		out.println("\t\t\t</TD></TR>");
		out.print("\t\t\t<TR><TD ALIGN=\"LEFT\" BGCOLOR=\"");
		out.print(ColorsHelper.toHtmlColor(classFigure.getBodyBackgroundColor()));
		out.println("\">");

		if (classFigure.getAttributeFigures().size() == 0) {
			out.println("\t\t\t\t&nbsp;");
		} else {
			flushNodeAttributes(nodeDesc);
		}
		out.println("\t\t\t</TD></TR>");
		out.println("\t\t</TABLE>>, margin=0, shape=plaintext]");

	}

	// TODO Javado
	private void flushNodeAttributes(NodeDesc nodeDesc) {
		ClassFigure classFigure = nodeDesc.classFigure;
		EList<AttributeFigure> attrFigures = classFigure.getAttributeFigures();
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

	// TODO Javadoc
	private void flushNodeHeader(String indent, NodeDesc nodeDesc) {
		ClassFigure classFigure = nodeDesc.classFigure;
		out.print(indent);
		out.println("<TABLE BORDER=\"0\" CELLBORDER=\"0\" CELLSPACING=\"0\" CELLPADDING=\"1\">");
		out.print(indent);
		out.println("\t<TR>");
		/*
		 * EObject icon processing
		 */
		boolean iconAvailable = nodeDesc.iconPath != null;
		boolean statusIconAvailable = nodeDesc.validationDecoratorIconPath != null;
		if (iconAvailable) {
			out.print(indent);
			out.print("\t\t<TD ALIGN=\"RIGHT\"><IMG SCALE=\"FALSE\" SRC=\"");
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
		out.print(indent);
		out.print("\t\t<TD ALIGN=\"");
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
			out.print(indent);
			out.print("\t\t<TD ALIGN=\"LEFT\"><IMG SCALE=\"FALSE\" SRC=\"");
			out.print(nodeDesc.validationDecoratorIconPath);
			out.println("\"/></TD>");
		}
		out.print(indent);
		out.println("\t</TR>");
		out.print(indent);
		out.println("</TABLE>");
	}

	/**
	 * Fix HTML characters.
	 * 
	 * @param str
	 *            the string to fix.
	 * @return the resulting HTML string.
	 */
	private String toHtmlString(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		return str;
	}

	/**
	 * Builds a unique identifier for a given eObject.
	 * 
	 * @param classFigure
	 *            the class figure associated to the EObject.
	 * @param eObject
	 *            the EObject.
	 * @return the generated identifier.
	 */
	private String buildEObjectIdentifier(ClassFigure classFigure,
			EObject eObject) {
		StringWriter buf = new StringWriter();
		buf.append(classFigure.getName().replaceAll("[ \\.]", "_"));
		buf.append("_");
		buf.append(String.valueOf(eObject.hashCode()));
		return buf.toString();
	}

	/**
	 * Flushes the graphviz source header in the buffer.
	 */
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

	/**
	 * Flushes the graphviz source footer in the buffer.
	 */
	private void flushFooter() {
		out.println("}");
	}

}

/**
 * Node description.
 */
class NodeDesc {

	/** Class figure associated to the node */
	ClassFigure classFigure;

	/** EObject represented by the node */
	EObject eObject;

	/** EObject identifier */
	String eObjectId;

	/** Icon path */
	String iconPath;

	/** Validation decorator icon path */
	String validationDecoratorIconPath;

	/** Nested nodes list */
	List<NodeDesc> nestedNodes;

}

/**
 * Edge description.
 */
class EdgeDesc {

	/** Reference figure associated to the edge */
	ReferenceFigure referenceFigure;

	/** Source EObject identifier */
	String srcEObjectId;

	/** Target EObject identifier */
	String targetEObjectId;

}
