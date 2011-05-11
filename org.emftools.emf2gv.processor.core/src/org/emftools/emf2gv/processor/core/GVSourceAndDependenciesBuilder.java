/**
 * Copyright (c) 2010-2011, Jean-Francois Brazeau. All rights reserved.
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

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.ecore.OCL.Helper;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.helper.OCLHelper;
import org.emftools.emf2gv.graphdesc.AbstractAttributeFigure;
import org.emftools.emf2gv.graphdesc.AbstractReferenceFigure;
import org.emftools.emf2gv.graphdesc.ArrowStyle;
import org.emftools.emf2gv.graphdesc.ArrowType;
import org.emftools.emf2gv.graphdesc.AttributeFigure;
import org.emftools.emf2gv.graphdesc.ClassFigure;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;
import org.emftools.emf2gv.graphdesc.ReferenceFigure;
import org.emftools.emf2gv.graphdesc.RichAttributeFigure;
import org.emftools.emf2gv.graphdesc.RichReferenceFigure;
import org.emftools.emf2gv.util.ColorsHelper;
import org.emftools.emf2gv.util.IOHelper;
import org.emftools.emf2gv.util.OCLProvider;

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
final class GVSourceAndDependenciesBuilder {

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
	 * The nodes list.
	 */
	private List<NodeDesc> nodes = new ArrayList<NodeDesc>();

	/**
	 * The nodes map.
	 */
	private Map<EObject, NodeDesc> nodesMap = new HashMap<EObject, NodeDesc>();

	/**
	 * The graphical description.
	 */
	private GVFigureDescription figureDesc;

	/**
	 * The EObject icon provider.
	 */
	private IEObjectIconProvider eObjectIconProvider;

	/**
	 * A map containing the associations between icons URL and generated PNG
	 * files.
	 */
	private Map<URL, String> iconUrlsToPngImagePathsAssociationMap = new HashMap<URL, String>();

	/**
	 * the folder receiving the PNG icons.
	 */
	private File workDir;

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

	/** Boolean OCL expressions allowing to filter the nodes. */
	private List<OCLFilterConstraint> filters;

	/** The OCL (lazy instantiation) */
	private OCL ocl;

	/** Logger */
	private ILogger logger;

	private Helper oclHelper;

	/**
	 * Default constructor.
	 * 
	 * @param figureDesc
	 *            the figure description.
	 * @param eObjectIconProvider
	 *            the EObject icon provider.
	 * @param iconsDir
	 *            the directory receiving the PNG icons.
	 * @param filters
	 *            the boolean OCL expressions allowing to filter the nodes.
	 * @param addValidationDecorators
	 *            a boolean indicating if validation decorators have to be
	 *            included in the diagram.
	 * @param logger
	 *            the logger to use.
	 */
	public GVSourceAndDependenciesBuilder(GVFigureDescription figureDesc,
			IEObjectIconProvider eObjectIconProvider, File iconsDir,
			boolean addValidationDecorators, List<OCLFilterConstraint> filters,
			ILogger logger) {
		this.figureDesc = figureDesc;
		this.eObjectIconProvider = eObjectIconProvider;
		this.workDir = iconsDir;
		this.filters = filters == null ? new ArrayList<OCLFilterConstraint>()
				: filters;
		this.logger = logger;

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
					processEObject(eContentRoot, monitor);
				} else {
					List<EObject> eContentRootChilds = eContentRoot.eContents();
					for (EObject eContentRootChild : eContentRootChilds) {
						processEObject(eContentRootChild, monitor);
					}
				}
			}

			// Build the nodes hierarchy
			List<NodeDesc> nodesHierarchy = buildContainersHierarchyAndReturnRoots();

			// GraphViz source build
			flushHeader();
			flushNodes(nodesHierarchy);
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
	// TODO the graphviz file should becreated from this component (as the
	// icons)
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
	 * @return the generated icons paths.
	 */
	public Collection<String> getGeneratedIconsPaths() {
		return iconUrlsToPngImagePathsAssociationMap.values();
	}

	/**
	 * Iterates overs the node descriptions and builds a hierarchical tree by
	 * adding child nodes to container nodes. The list that is returned contains
	 * the hierarchy roots.
	 * 
	 * @return the hierarchical tree.
	 */
	private List<NodeDesc> buildContainersHierarchyAndReturnRoots() {
		List<NodeDesc> result = new ArrayList<NodeDesc>();
		result.addAll(nodes);

		// Containers EClass lookup
		List<EClass> containerEClasses = new ArrayList<EClass>();
		for (ClassFigure classFigure : figureDesc.getClassFigures()) {
			if (classFigure.isContainer()) {
				containerEClasses.add(classFigure.getEClass());
			}
		}

		// Containers instances lookup (in order to populate the nested nodes
		// attribute of the container nodes).
		if (containerEClasses.size() > 0) {
			List<NodeDesc> containedNodes = new ArrayList<NodeDesc>();
			for (EObject eObject : eObjectIdsCache.keySet()) {
				EClass eClass = eObject.eClass();
				if (containerEClasses.contains(eClass)) {
					ClassFigure classFigure = figureDesc.getClassFigure(eClass);
					NodeDesc containerNode = nodesMap.get(eObject);
					containerNode.nestedNodes = new ArrayList<NodeDesc>();
					// Nested figures EReferences browsing
					for (EReference eReference : classFigure
							.getNestedFiguresEReferences()) {
						List<EObject> targetEObjects = getTargetRefEObjects(
								eObject, eReference);
						for (EObject targetEObject : targetEObjects) {
							NodeDesc targetNode = nodesMap.get(targetEObject);
							// The target node may be null if the target eObject
							// has no class figure in the graphical description
							if (targetNode != null) {
								containedNodes.add(targetNode);
								containerNode.nestedNodes.add(targetNode);
							}
						}
					}
				}
			}
			// When the nested nodes attributes of the container nodes have been
			// populated
			// the contained nodes can be removed from the base node map.
			result.removeAll(containedNodes);
		}

		return result;
	}

	/**
	 * @param eObject
	 *            the processed eObject.
	 * @return a boolean indicating if the node must be drawn.
	 */
	private boolean mustDraw(EObject eObject) {
		boolean mustDraw = true;
		EClass eClass = eObject.eClass();
		for (OCLFilterConstraint filter : filters) {
			if (filter.getEClass().isSuperTypeOf(eClass)) {
				mustDraw &= getOCL().check(eObject, filter.getConstraint());
			}
		}
		return mustDraw;
	}

	/**
	 * @return an OCL instance.
	 */
	private OCL getOCL() {
		if (ocl == null) {
			ocl = OCLProvider.newOCL();
		}
		return ocl;
	}

	/**
	 * @return an OCL Helper instance.
	 */
	private OCLHelper<EClassifier, EOperation, EStructuralFeature, Constraint> getOclHelper() {
		if (oclHelper == null) {
			oclHelper = getOCL().createOCLHelper();
		}
		return oclHelper;
	}

	/**
	 * Processes an EObject and its childs recursively.
	 * 
	 * @param eContentRoot
	 *            the root EObjet.
	 * @param monitor
	 *            a progress monitor.
	 * @return the eObject's identifier.
	 * @throws CoreException
	 *             thrown if an unexpected error occurs.
	 */
	private String processEObject(EObject eContentRoot, IProgressMonitor monitor)
			throws CoreException {
		String eContentRootId = null;
		// First, we must check if the node must be hidden
		if (mustDraw(eContentRoot)) {
			// If not we can process the node normally
			eContentRootId = eObjectIdsCache.get(eContentRoot);
			if (eContentRootId == null) {
				EClass eContentRootEClass = eContentRoot.eClass();
				ClassFigure classFigure = figureDesc
						.getClassFigure(eContentRootEClass);
				// We proceed only if the classFigure has been defined
				if (classFigure != null) {
					eContentRootId = buildEObjectIdentifier(classFigure,
							eContentRoot);
					eObjectIdsCache.put(eContentRoot, eContentRootId);

					// Node description creation and registration
					NodeDesc nodeDesc = new NodeDesc();
					nodeDesc.classFigure = classFigure;
					nodeDesc.eObject = eContentRoot;
					nodeDesc.eObjectId = eContentRootId;
					nodeDesc.iconPath = findAndSaveEObjectIcon(eContentRoot,
							monitor);
					nodeDesc.validationDecoratorIconPath = getValidationDecoratorIconPath(
							eContentRoot, monitor);
					nodes.add(nodeDesc);
					nodesMap.put(eContentRoot, nodeDesc);

					// Attributes retrieval
					nodeDesc.attributes = new ArrayList<AttributeDesc>();
					for (AbstractAttributeFigure abstractAttrFigure : classFigure
							.getAttributeFigures()) {
						// Simple attribute figures
						if (abstractAttrFigure instanceof AttributeFigure) {
							AttributeDesc attrDesc = new AttributeDesc();
							nodeDesc.attributes.add(attrDesc);
							AttributeFigure attrFigure = (AttributeFigure) abstractAttrFigure;
							EAttribute eAttribute = attrFigure.getEAttribute();
							String attrLabel = attrFigure.getLabel();
							if (attrLabel == null
									|| "".equals(attrLabel.trim())) {
								attrLabel = eAttribute.getName();
							}
							attrDesc.label = attrLabel.trim();
							attrDesc.value = String.valueOf(nodeDesc.eObject
									.eGet(eAttribute));
						}
						// Rich attribute figures
						else {
							RichAttributeFigure attrFigure = (RichAttributeFigure) abstractAttrFigure;
							List<EObject> richAttrsEObjects = getTargetRefEObjects(
									nodeDesc.eObject,
									attrFigure.getEReference());
							try {
								getOclHelper().setContext(
										attrFigure.getEReference().getEType());
								OCLExpression<EClassifier> expression = getOclHelper()
										.createQuery(
												attrFigure.getLabelExpression());
								for (EObject richAttrEObject : richAttrsEObjects) {
									if (mustDraw(richAttrEObject)) {
										AttributeDesc attrDesc = new AttributeDesc();
										nodeDesc.attributes.add(attrDesc);
										attrDesc.value = String
												.valueOf(getOCL().evaluate(
														richAttrEObject,
														expression));
										attrDesc.iconPath = findAndSaveEObjectIcon(
												richAttrEObject, monitor);
										attrDesc.validationDecoratorIconPath = getValidationDecoratorIconPath(
												richAttrEObject, monitor);
									}

								}
							} catch (ParserException e) {
								throw new CoreException(new Status(
										IStatus.ERROR,
										StandaloneProcessor.PLUGIN_ID,
										"Unexpected error while parsing OCL expression '"
												+ attrFigure
														.getLabelExpression()
												+ "'", e));
							}

						}
					}

					// Nested figures EReferences browsing
					for (EReference eReference : classFigure
							.getNestedFiguresEReferences()) {
						List<EObject> targetEObjects = getTargetRefEObjects(
								eContentRoot, eReference);
						// Nested figures generation
						for (EObject targetEObject : targetEObjects) {
							processEObject(targetEObject, monitor);
						}
					}

					// EReferences browsing
					List<AbstractReferenceFigure> refFigures = classFigure
							.getReferenceFigures();
					for (AbstractReferenceFigure abstractReferenceFigure : refFigures) {
						// Simple reference case
						if (abstractReferenceFigure instanceof ReferenceFigure) {
							List<EObject> targetEObjects = getTargetRefEObjects(
									eContentRoot,
									abstractReferenceFigure.getEReference());
							processReferenceTargetEObjects(
									abstractReferenceFigure, eContentRoot,
									eContentRootId, targetEObjects, null, null,
									null, null, monitor);
						}
						// Rich reference case
						else {
							RichReferenceFigure richReferenceFigure = (RichReferenceFigure) abstractReferenceFigure;
							EReference eReference = richReferenceFigure
									.getEReference();
							List<EObject> richReferencesEClassInstances = getTargetRefEObjects(
									eContentRoot, eReference);
							for (EObject richReferenceEClassInstance : richReferencesEClassInstances) {
								// Check if the EObject has to be hidden
								if (mustDraw(richReferenceEClassInstance)) {
									// If not, the rich reference instance is
									// processed
									List<EObject> targetEObjects = getTargetRefEObjects(
											richReferenceEClassInstance,
											richReferenceFigure
													.getTargetEReference());
									// Labels value retrieval
									String srcLabel = eGetToString(
											richReferenceEClassInstance,
											richReferenceFigure
													.getSourceLabelEAttribute());
									String stdLabel = eGetToString(
											richReferenceEClassInstance,
											richReferenceFigure
													.getStandardLabelEAttribute());
									String targetLabel = eGetToString(
											richReferenceEClassInstance,
											richReferenceFigure
													.getTargetLabelEAttribute());
									// Rich reference instance validation
									String validationDecoratorIconPath = getValidationDecoratorIconPath(
											richReferenceEClassInstance,
											monitor);
									processReferenceTargetEObjects(
											richReferenceFigure, eContentRoot,
											eContentRootId, targetEObjects,
											srcLabel, stdLabel, targetLabel,
											validationDecoratorIconPath,
											monitor);
								}
							}
						}
					}
				}
			}
		}
		return eContentRootId;
	}

	/**
	 * @param eObject
	 *            the eObject to validate.
	 * @param monitor
	 *            the progress monitor.
	 * @return the validation decorator icon path.
	 */
	private String getValidationDecoratorIconPath(EObject eObject,
			IProgressMonitor monitor) {
		boolean addValidationDecorators = (diagnostician != null);
		String validationDecoratorIconPath = null;
		if (addValidationDecorators) {
			Diagnostic diagnostic = diagnostician.validate(eObject);
			switch (diagnostic.getSeverity()) {
			case Diagnostic.ERROR:
				validationDecoratorIconPath = findAndSavePluginIcon("error",
						monitor);
				break;
			case Diagnostic.WARNING:
				validationDecoratorIconPath = findAndSavePluginIcon("warning",
						monitor);
				break;
			}
		}
		return validationDecoratorIconPath;
	}

	/**
	 * @param eObject
	 *            the eObject to read the feature from.
	 * @param feature
	 *            the feature to read.
	 * @return the eObject's feature value turned into string.
	 */
	private static String eGetToString(EObject eObject,
			EStructuralFeature feature) {
		String result = null;
		if (eObject != null && feature != null) {
			Object objectResult = eObject.eGet(feature);
			if (objectResult != null) {
				result = String.valueOf(objectResult);
			}
		}
		return result;
	}

	/**
	 * Processes an ERefence target eObjects.
	 * 
	 * @param figure
	 *            the reference figure.
	 * @param srcEObject
	 *            the source eObject.
	 * @param srcEObjectId
	 *            the source eObject identifier.
	 * @param targetEObjects
	 *            the target EObjects list.
	 * @param srcLabel
	 *            the source label to apply to the edge.
	 * @param stdLabel
	 *            the standard label to apply to the edge.
	 * @param targetLabel
	 *            the target label to apply to the edge.
	 * @param monitor
	 *            the progress monitor.
	 * @throws CoreException
	 *             thrown if an unexpected error occurs.
	 */
	private void processReferenceTargetEObjects(AbstractReferenceFigure figure,
			EObject srcEObject, String srcEObjectId,
			List<EObject> targetEObjects, String srcLabel, String stdLabel,
			String targetLabel, String validationDecoratorIconPath,
			IProgressMonitor monitor) throws CoreException {
		// Edges figures generation
		for (EObject targetEObject : targetEObjects) {
			String targetEObjectId = processEObject(targetEObject, monitor);
			// the target EObject Id may hav not been built if the
			// target EObject's Eclass
			// is not associated to a ClassFigure.
			if (targetEObjectId != null) {
				EdgeDesc edgeDesc = new EdgeDesc();
				edgeDesc.referenceFigure = figure;
				edgeDesc.srcEObjectId = srcEObjectId;
				edgeDesc.srcEObject = srcEObject;
				edgeDesc.targetEObjectId = targetEObjectId;
				edgeDesc.targetEObject = targetEObject;
				edgeDesc.srcLabel = srcLabel;
				edgeDesc.stdLabel = stdLabel;
				edgeDesc.targetLabel = targetLabel;
				edgeDesc.validationDecoratorIconPath = validationDecoratorIconPath;
				edges.add(edgeDesc);
			}
		}
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
		URL url = null;
		String iconFullPath = null;
		try {
			String sourceIconPath = "icons/" + name + ".gif";
			// The default Classloader is preferred to retrieve the icons
			// because it is compatible with the standalone mode. If we use the
			// BundleActivator (which is the preferred way in an eclipse OSGI
			// environement), it will now work in a standalone context
			url = GVSourceAndDependenciesBuilder.class.getClassLoader()
					.getResource(sourceIconPath);
			/*
			 * If the URL is still null, it is probably because we're in the
			 * development environment : in that case, the icons are not in the
			 * classpath (they get into the classpath once the PDE has built the
			 * libraries, ie. the jar files). In that case, we use a workaround
			 * based on a system property to retrieve the icons :
			 */
			if (url == null) {
				String basePath = System.getProperty("eclipse.workspace");
				if (basePath == null) {
					throw new IOException(
							"An internal icon has not been found ("
									+ sourceIconPath
									+ "). Perhaps you should set the 'eclipse.workspace' system property...");
				} else {
					url = new URL("file:///" + basePath.replace('\\', '/')
							+ '/' + StandaloneProcessor.PLUGIN_ID + '/'
							+ sourceIconPath);
				}
			}
			// If the URL has been found...
			if (url != null) {
				iconFullPath = iconUrlsToPngImagePathsAssociationMap.get(url);
				if (iconFullPath == null) {
					// Icon path building
					iconFullPath = new StringBuilder(workDir.getAbsolutePath())
							.append("/").append(name).append(".png").toString();
					// Copy the image to a PNG File
					copyImageToPng(url, iconFullPath, monitor);

					// Registers the icon path in the map
					iconUrlsToPngImagePathsAssociationMap
							.put(url, iconFullPath);
				}
			}
		}
		// If we meet a problem, we simply log the error
		// and proceed (the returned image full path is
		// null as if there was no image available for this
		// EObject)
		catch (IOException e) {
			logger.logError("Unexpected error while retrieving image '" + url
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
		URL url = eObjectIconProvider.getIcon(eObject);
		if (url != null) {
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

					// Image full path building
					iconFullPath = new StringBuilder(workDir.getAbsolutePath())
							.append("/").append(str).toString();

					// Copy the image to a PNG File
					copyImageToPng(url, iconFullPath, monitor);

					// Registers the icon path in the map
					iconUrlsToPngImagePathsAssociationMap
							.put(url, iconFullPath);
				}
				// If we meet a problem, we simply log the error
				// and proceed (the returned image full path is
				// null as if there was no image available for this
				// EObject)
				catch (IOException e) {
					logger.logError("Unexpected error while retrieving image '"
							+ url + "'", e);
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
	 * @throws IOException
	 *             thrown if an I/O error occurs.
	 */
	private void copyImageToPng(URL sourceImageUrl, String targetPngImagePath,
			IProgressMonitor monitor) throws IOException {
		// Image file loading and conversion into PNG
		byte[] pngImage = IOHelper.loadAndConvertImageToPng(sourceImageUrl
				.openStream());

		// Saves the PNG image
		IOHelper.save(targetPngImagePath, pngImage, monitor);
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
		AbstractReferenceFigure abstractReferenceFigure = edgeDesc.referenceFigure;
		ClassFigure srcClassFigure = figureDesc
				.getClassFigure(edgeDesc.srcEObject.eClass());
		ClassFigure targetClassFigure = figureDesc
				.getClassFigure(edgeDesc.targetEObject.eClass());
		out.print('\t');
		out.print(edgeDesc.srcEObjectId);
		out.print("->");
		out.print(edgeDesc.targetEObjectId);
		// Arrow style
		out.print(" [arrowhead = ");
		out.print(abstractReferenceFigure.getTargetArrowType().equals(
				ArrowType.CUSTOM) ? abstractReferenceFigure
				.getCustomTargetArrow() : abstractReferenceFigure
				.getTargetArrowType().toString());
		out.print(", arrowtail = ");
		out.print(abstractReferenceFigure.getSourceArrowType().equals(
				ArrowType.CUSTOM) ? abstractReferenceFigure
				.getCustomSourceArrow() : abstractReferenceFigure
				.getSourceArrowType().toString());
		if (edgeDesc.srcLabel != null) {
			out.print(", taillabel=\"");
			out.print(toHtmlString(edgeDesc.srcLabel));
			out.print("\"");
		}
		// Start of the centered label
		if (edgeDesc.stdLabel != null
				|| edgeDesc.validationDecoratorIconPath != null) {
			out.print(", label=<");
			out.println("\t\t<TABLE BORDER=\"0\" CELLBORDER=\"0\" CELLSPACING=\"0\" CELLPADDING=\"0\"><TR>");
			if (edgeDesc.stdLabel != null) {
				out.print("<TD>");
				out.print(toHtmlString(edgeDesc.stdLabel));
				out.print("</TD>");
			}
			if (edgeDesc.validationDecoratorIconPath != null) {
				out.print("\t\t<TD><IMG SCALE=\"FALSE\" SRC=\"");
				out.print(edgeDesc.validationDecoratorIconPath);
				out.println("\"/></TD>");
			}
			out.print("</TR></TABLE>>");
		}
		if (edgeDesc.targetLabel != null) {
			out.print(", headlabel=\"");
			out.print(toHtmlString(edgeDesc.targetLabel));
			out.print("\"");
		}
		out.print(", minlen=");
		out.print(abstractReferenceFigure.getMinimumEdgeLength());
		if (abstractReferenceFigure instanceof RichReferenceFigure) {
			RichReferenceFigure richReferenceFigure = (RichReferenceFigure) abstractReferenceFigure;
			out.print(", labeldistance=");
			out.print(String.valueOf(richReferenceFigure.getLabelDistance())
					.replace(',', '.'));
			out.print(", labelangle=");
			out.print(String.valueOf(richReferenceFigure.getLabelAngle())
					.replace(',', '.'));
		}
		out.print(", color=\"");
		out.print(ColorsHelper.toHtmlColor(abstractReferenceFigure.getColor()));
		out.print("\"");
		if (!ArrowStyle.NORMAL.equals(abstractReferenceFigure.getStyle())) {
			out.print(", style=");
			out.print(abstractReferenceFigure.getStyle().toString());
		}
		// If the source figure is a cluster...
		if (srcClassFigure.isContainer()) {
			out.print(", ltail=cluster_");
			out.print(edgeDesc.srcEObjectId);
		}
		// If the target figure is a cluster...
		if (targetClassFigure.isContainer()) {
			out.print(", lhead=cluster_");
			out.print(edgeDesc.targetEObjectId);
		}
		out.println("]");

		// Edges count update
		edgesCount++;
	}

	/**
	 * Flushes a node list in the graphviz source buffer.
	 * 
	 * @param nodeDescs
	 *            the node descriptions to flush.
	 * @throws CoreException
	 *             thrown if an unexpected error occurs.
	 */
	private void flushNodes(List<NodeDesc> nodeDescs) throws CoreException {
		// NodeDescs are sorted by EClass
		Map<EClass, List<NodeDesc>> nodesListByEclass = new HashMap<EClass, List<NodeDesc>>();
		for (NodeDesc nodeDesc : nodeDescs) {
			EClass eClass = nodeDesc.eObject.eClass();
			List<NodeDesc> sameEClassNodes = nodesListByEclass.get(eClass);
			if (sameEClassNodes == null) {
				sameEClassNodes = new ArrayList<NodeDesc>();
				nodesListByEclass.put(eClass, sameEClassNodes);
			}
			sameEClassNodes.add(nodeDesc);
		}
		// And then the nodes are flushed
		for (ClassFigure classFigure : figureDesc.getClassFigures()) {
			List<NodeDesc> sameEClassNodes = nodesListByEclass.get(classFigure
					.getEClass());
			boolean doAlignNodes = figureDesc.isAlignSameEClasses()
					&& !classFigure.isContainer();
			if (sameEClassNodes != null) {
				if (doAlignNodes) {
					out.print("{ rank = same;\n");
				}
				for (NodeDesc nodeDesc : sameEClassNodes) {
					if (nodeDesc.classFigure.isContainer()) {
						flushClusterNode(nodeDesc);
					} else {
						flushStandardNode(nodeDesc);
					}
					// Nodes count update
					nodesCount++;
				}
				if (doAlignNodes) {
					out.print("}\n");
				}
			}
		}
	}

	/**
	 * Flushes a cluster node and its child nodes in the graphviz source buffer.
	 * 
	 * @param nodeDescs
	 *            the node description to flush.
	 * @throws CoreException
	 *             thrown if an unexpected error occurs.
	 */
	private void flushClusterNode(NodeDesc nodeDesc) throws CoreException {
		/*
		 * Node generation.
		 */
		out.print("\tsubgraph cluster_");
		out.print(nodeDesc.eObjectId);
		out.println(" { label=<");
		out.println("\t\t<TABLE BORDER=\"0\" CELLBORDER=\"0\" CELLSPACING=\"0\" CELLPADDING=\"0\">");
		out.print("\t\t\t<TR><TD>");
		flushNodeHeader("\t\t\t", 0, nodeDesc);
		out.println("\t\t\t</TD></TR>");
		// Header
		if (nodeDesc.attributes.size() > 0) {
			out.print("\t\t\t<TR><TD ALIGN=\"LEFT\" BGCOLOR=\"");
			flushBodyBackgroundColor(nodeDesc);
			out.println("\">");
			flushNodeAttributes(nodeDesc);
			out.println("\t\t\t</TD></TR>");
		}
		out.print("\t\t</TABLE>>; bgcolor=\"");
		flushHeaderBackgroundColor(nodeDesc);
		out.println("\"; labeljust=\"l\";");
		// Flush nested nodes
		flushNodes(nodeDesc.nestedNodes);

		// Flush the anchor that is used to connect the cluster to or from
		// other nodes or cluster
		out.print("\t\t");
		out.print(nodeDesc.eObjectId);
		out.println(" [label=\"\", shape=\"none\", width=0, height=0];");
		out.println("\t\t}");
	}

	/**
	 * Flushes the header background color.
	 * 
	 * @param nodeDesc
	 *            the node description.
	 */
	private void flushHeaderBackgroundColor(NodeDesc nodeDesc) {
		flushColor(nodeDesc,
				GraphdescPackage.eINSTANCE
						.getClassFigure_HeaderBackgroundColor(),
				GraphdescPackage.eINSTANCE
						.getClassFigure_HeaderBackgroundColorAccessor());
	}

	/**
	 * Flushes the body background color.
	 * 
	 * @param nodeDesc
	 *            the node description.
	 */
	private void flushBodyBackgroundColor(NodeDesc nodeDesc) {
		flushColor(nodeDesc, GraphdescPackage.eINSTANCE
				.getClassFigure_BodyBackgroundColor(),
				GraphdescPackage.eINSTANCE
						.getClassFigure_BodyBackgroundColorAccessor());
	}

	/**
	 * Flushes a background color.
	 * 
	 * @param nodeDesc
	 *            the node description.
	 * @param defaultColorEAttribute
	 *            the default color attribute of the classfigure (header /
	 *            body).
	 * @param colorAccessorEAttribute
	 *            the classfigure attribute giving the color provider to use.
	 */
	private void flushColor(NodeDesc nodeDesc,
			EAttribute defaultColorEAttribute,
			EAttribute colorAccessorEAttribute) {
		ClassFigure classFigure = nodeDesc.classFigure;
		String colorAccessorName = (String) classFigure
				.eGet(colorAccessorEAttribute);
		int bgColor = (Integer) classFigure.eGet(defaultColorEAttribute);
		if (classFigure.isDynamicAppearance() && colorAccessorName != null) {
			Exception exception = null;
			EObject eObject = nodeDesc.eObject;
			try {
				Method method = eObject.getClass().getMethod(colorAccessorName,
						new Class<?>[] {});
				Color result = (Color) method.invoke(eObject, new Object[] {});
				if (result != null) {
					bgColor = ColorsHelper.toInt(result);
				}
			} catch (SecurityException e) {
				exception = e;
			} catch (NoSuchMethodException e) {
				exception = e;
			} catch (IllegalArgumentException e) {
				exception = e;
			} catch (IllegalAccessException e) {
				exception = e;
			} catch (InvocationTargetException e) {
				exception = e;
			}
			if (exception != null) {
				// If an error occurs, we only log it
				// If the editor's icon is not displayable,
				// it is not critical
				logger.logError(
						"Unexpected error while invoking the custom color provider ("
								+ colorAccessorName + ")", exception);
			}
		}
		out.print(ColorsHelper.toHtmlColor(bgColor));
	}

	/**
	 * Flushes a standard node in the graphviz source buffer.
	 * 
	 * @param nodeDescs
	 *            the node description to flush.
	 * @throws CoreException
	 *             thrown if an unexpected error occurs.
	 */
	private void flushStandardNode(NodeDesc nodeDesc) throws CoreException {
		/*
		 * Node generation.
		 */
		out.print('\t');
		out.print(nodeDesc.eObjectId);
		out.println(" [label=<");
		out.println("\t\t<TABLE BORDER=\"0\" CELLBORDER=\"0\" CELLSPACING=\"0\" CELLPADDING=\"0\">");
		out.print("\t\t\t<TR><TD BGCOLOR=\"");
		flushHeaderBackgroundColor(nodeDesc);
		out.println("\">");
		// Flushes the node header
		flushNodeHeader("\t\t\t\t", 1, nodeDesc);

		out.println("\t\t\t</TD></TR>");
		out.print("\t\t\t<TR><TD ALIGN=\"LEFT\" BGCOLOR=\"");
		flushBodyBackgroundColor(nodeDesc);
		out.println("\">");

		if (nodeDesc.attributes.size() == 0) {
			out.println("\t\t\t\t<TABLE BORDER=\"1\" CELLBORDER=\"0\" CELLSPACING=\"0\"><TR><TD> </TD></TR></TABLE>");
		} else {
			flushNodeAttributes(nodeDesc);
		}
		out.println("\t\t\t</TD></TR>");
		out.println("\t\t</TABLE>>, margin=0, shape=plaintext]");

	}

	/**
	 * Flushes the node attributes in the graphviz source buffer.
	 * 
	 * @param nodeDesc
	 *            the node description.
	 */
	private void flushNodeAttributes(NodeDesc nodeDesc) {
		out.println("\t\t\t\t<TABLE BORDER=\"1\" CELLBORDER=\"0\" CELLSPACING=\"0\">");
		// If the node has no attributes, a blank line is inserted
		if (nodeDesc.attributes.size() == 0) {
			out.print("\t\t\t\t\t<TR><TD ALIGN=\"LEFT\" COLSPAN=\"2\"> </TD></TR>");
		} else {
			for (AttributeDesc attrDesc : nodeDesc.attributes) {
				out.print("\t\t\t\t\t<TR><TD>");
				if (attrDesc.iconPath != null) {
					out.print("<IMG SCALE=\"FALSE\" SRC=\"");
					out.print(attrDesc.iconPath);
					out.println("\"/>");
				} else {
					out.print(" ");
				}
				out.print("</TD>");
				if (attrDesc.label != null) {
					out.print("<TD ALIGN=\"LEFT\">");
					out.print(toHtmlString(attrDesc.label));
					out.print(" :</TD><TD ALIGN=\"LEFT\">");
				} else {
					out.print("<TD ALIGN=\"LEFT\" COLSPAN=\"2\">");
				}
				out.print(toHtmlString(attrDesc.value));
				out.print("</TD><TD>");
				if (attrDesc.validationDecoratorIconPath != null) {
					out.print("<IMG SCALE=\"FALSE\" SRC=\"");
					out.print(attrDesc.validationDecoratorIconPath);
					out.println("\"/>");
				} else {
					out.print(" ");
				}
				out.println("</TD></TR>");
			}
		}
		out.println("\t\t\t\t</TABLE>");
	}

	/**
	 * Flushes a node header in the graphviz source buffer.
	 * 
	 * @param indent
	 *            the indent to apply.
	 * @param border
	 *            the border width to apply.
	 * @param nodeDesc
	 *            the node description.
	 */
	private void flushNodeHeader(String indent, int border, NodeDesc nodeDesc) {
		ClassFigure classFigure = nodeDesc.classFigure;
		out.print(indent);
		out.print("<TABLE BORDER=\"");
		out.print(border);
		out.println("\" CELLBORDER=\"0\" CELLSPACING=\"0\" CELLPADDING=\"1\">");
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
			// FIX : when
			if (label.length() < 4) {
				label += "   ";
			}
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
		out.println("\tcompound=true;");
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

	/** Nested nodes list */
	List<AttributeDesc> attributes;

}

/**
 * Attribute description.
 */
class AttributeDesc {

	/** Icon path */
	String iconPath;

	/** Text */
	String label;

	/** Text */
	String value;

	/** Validation decorator icon path */
	String validationDecoratorIconPath;

}

/**
 * Edge description.
 */
class EdgeDesc {

	/** Reference figure associated to the edge */
	AbstractReferenceFigure referenceFigure;

	/** Source EObject identifier */
	String srcEObjectId;

	/** Source EObject */
	EObject srcEObject;

	/** Target EObject identifier */
	String targetEObjectId;

	/** Target EObject */
	EObject targetEObject;

	/** Source label */
	String srcLabel;

	/** Standard label */
	String stdLabel;

	/** Target label */
	String targetLabel;

	/** Validation decorator icon path */
	String validationDecoratorIconPath;

}
