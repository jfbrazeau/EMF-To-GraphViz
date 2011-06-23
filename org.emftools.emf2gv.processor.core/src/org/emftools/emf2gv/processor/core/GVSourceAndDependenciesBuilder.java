/**
 * This file is part of emf2gv : an eclipse plugin that allows to
 * generate a graphical representation of an EMF model.
 *
 * Copyright 2010-2011 Jean-Francois Brazeau
 * 
 * emf2gv is free software: you can redistribute it and/or modify
 * it under the terms of either:
 * 
 *      a) the GNU Lesser General Public License as published by
 *       the Free Software Foundation, either version 3 of the License, or
 *       (at your option) any later version.
 *  or
 *      b) the Eclipse Public License version 1.0 as published by
 *       the Eclipse Foundation.
 * 
 * emf2gv is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with emf2gv.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * You should have received a copy of the  Eclipse Public License
 * along with emf2gv.  If not, see <http://www.eclipse.org/legal/epl-v10.html>.
 */
package org.emftools.emf2gv.processor.core;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
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
import org.emftools.emf2gv.graphdesc.AbstractFigure;
import org.emftools.emf2gv.graphdesc.AbstractReferenceFigure;
import org.emftools.emf2gv.graphdesc.EdgeStyle;
import org.emftools.emf2gv.graphdesc.ArrowType;
import org.emftools.emf2gv.graphdesc.AttributeFigure;
import org.emftools.emf2gv.graphdesc.ClassFigure;
import org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider;
import org.emftools.emf2gv.graphdesc.Filter;
import org.emftools.emf2gv.graphdesc.FontStyle;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;
import org.emftools.emf2gv.graphdesc.ReferenceFigure;
import org.emftools.emf2gv.graphdesc.RichAttributeFigure;
import org.emftools.emf2gv.graphdesc.RichReferenceFigure;
import org.emftools.emf2gv.graphdesc.util.GraphdescHelper;
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
	 * The graphical description EPackage.
	 */
	private static final GraphdescPackage gvPkg = GraphdescPackage.eINSTANCE;

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

	/** The folder receiving the PNG icons. */
	private File workDir;

	/** The nodes count. */
	private int nodesCount = 0;

	/** The edges count. */
	private int edgesCount = 0;

	/** The diagnostician used to validate the EObjects. */
	private Diagnostician diagnostician;

	/** The OCL (lazy instantiation) */
	private OCL ocl;

	/** Logger */
	private ILogger logger;

	/** OCL Helper */
	private Helper oclHelper;

	/**
	 * A map containing the associations between icons URL and generated PNG
	 * files.
	 */
	private Map<String, OCLExpression<EClassifier>> parsedOCLExpressionCache = new HashMap<String, OCLExpression<EClassifier>>();

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
			boolean addValidationDecorators, ILogger logger) {
		this.figureDesc = figureDesc;
		this.eObjectIconProvider = eObjectIconProvider;
		this.workDir = iconsDir;
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
	 * @return the edges count.
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
	 * @throws CoreException
	 *             thrown if a parser exception occurs.
	 */
	private boolean mustDraw(EObject eObject) throws CoreException {
		boolean mustDraw = true;
		EClass eClass = eObject.eClass();
		for (Filter filter : figureDesc.getFilters()) {
			if (filter.isEnabled()
					&& filter.getFilteredType().isSuperTypeOf(eClass)) {
				mustDraw &= (Boolean) evaluateOCLExpression(eObject,
						filter.getFilterExpression());
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

	@SuppressWarnings("unchecked")
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
					nodeDesc.eClassName = eContentRoot.eClass().getName();
					nodeDesc.eObjectId = eContentRootId;
					nodeDesc.iconPath = findAndSaveEObjectIcon(eContentRoot,
							monitor);
					nodeDesc.validationDecoratorIconPath = getValidationDecoratorIconPath(
							eContentRoot, monitor);
					nodeDesc.isContainer = classFigure.isContainer();
					nodes.add(nodeDesc);
					// Overridable properties retrieval
					nodeDesc.headerBackgroundColor = (Color) getFigurePropertyValue(
							classFigure, eContentRoot,
							gvPkg.getClassFigure_HeaderBackgroundColor());
					nodeDesc.bodyBackgroundColor = (Color) getFigurePropertyValue(
							classFigure, eContentRoot,
							gvPkg.getClassFigure_BodyBackgroundColor());
					nodeDesc.labelStyle = (Collection<FontStyle>) getFigurePropertyValue(
							classFigure, eContentRoot,
							gvPkg.getClassFigure_LabelStyle());
					nodesMap.put(eContentRoot, nodeDesc);

					// Label building
					boolean iconAvailable = nodeDesc.iconPath != null;
					EAttribute labelEAttribute = classFigure
							.getLabelEAttribute();
					String rawLabel = null;
					if (labelEAttribute != null) {
						Object labelObject = eContentRoot.eGet(labelEAttribute);
						rawLabel = labelObject != null ? labelObject.toString()
								: null;
						if (rawLabel != null) {
							rawLabel = rawLabel.trim();
							if ("".equals(rawLabel)) {
								rawLabel = null;
							}
						}
					}
					// If there is no icon or if we have no label to show, we
					// print the EClass name
					if (iconAvailable) {
						nodeDesc.label = rawLabel;
					} else {
						nodeDesc.label = classFigure.getEClass().getName()
								+ (rawLabel != null ? " : " + rawLabel : "");
					}

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
							attrDesc.label = eAttribute.getName()
									+ " : "
									+ String.valueOf(eContentRoot
											.eGet(eAttribute));
							attrDesc.labelStyle = (Collection<FontStyle>) getFigurePropertyValue(
									abstractAttrFigure,
									eContentRoot,
									gvPkg.getAbstractAttributeFigure_LabelStyle());
						}
						// Rich attribute figures
						else {
							RichAttributeFigure attrFigure = (RichAttributeFigure) abstractAttrFigure;
							List<EObject> richAttrsEObjects = getTargetRefEObjects(
									eContentRoot, attrFigure.getEReference());
							OCLExpression<EClassifier> expression = parseOCLExpression(
									attrFigure.getEReference().getEType(),
									attrFigure.getLabelExpression());
							for (EObject richAttrEObject : richAttrsEObjects) {
								if (mustDraw(richAttrEObject)) {
									AttributeDesc attrDesc = new AttributeDesc();
									nodeDesc.attributes.add(attrDesc);
									attrDesc.label = String.valueOf(getOCL()
											.evaluate(richAttrEObject,
													expression));
									attrDesc.labelStyle = (Collection<FontStyle>) getFigurePropertyValue(
											abstractAttrFigure,
											richAttrEObject,
											gvPkg.getAbstractAttributeFigure_LabelStyle());
									attrDesc.iconPath = findAndSaveEObjectIcon(
											richAttrEObject, monitor);
									attrDesc.validationDecoratorIconPath = getValidationDecoratorIconPath(
											richAttrEObject, monitor);
								}

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
							for (EObject targetEObject : targetEObjects) {
								// For RegerenceFigures, the OCL context is the
								// target EObject
								processReferenceTargetEObject(
										abstractReferenceFigure, eContentRoot,
										eContentRootId, targetEObject,
										targetEObject, null, null, null, null,
										null, null, null, monitor);
							}
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
									String srcLabel = String
											.valueOf(evaluateOCLExpression(
													richReferenceEClassInstance,
													richReferenceFigure
															.getSourceLabelExpression()));
									Collection<FontStyle> srcStyle = (Collection<FontStyle>) getFigurePropertyValue(
											richReferenceFigure,
											richReferenceEClassInstance,
											gvPkg.getRichReferenceFigure_SourceLabelStyle());
									String stdLabel = String
											.valueOf(evaluateOCLExpression(
													richReferenceEClassInstance,
													richReferenceFigure
															.getStandardLabelExpression()));
									Collection<FontStyle> stdStyle = (Collection<FontStyle>) getFigurePropertyValue(
											richReferenceFigure,
											richReferenceEClassInstance,
											gvPkg.getRichReferenceFigure_StandardLabelStyle());
									String targetLabel = String
											.valueOf(evaluateOCLExpression(
													richReferenceEClassInstance,
													richReferenceFigure
															.getTargetLabelExpression()));
									Collection<FontStyle> targetStyle = (Collection<FontStyle>) getFigurePropertyValue(
											richReferenceFigure,
											richReferenceEClassInstance,
											gvPkg.getRichReferenceFigure_TargetLabelStyle());
									// Rich reference instance validation
									String validationDecoratorIconPath = getValidationDecoratorIconPath(
											richReferenceEClassInstance,
											monitor);
									for (EObject targetEObject : targetEObjects) {
										// For RichRegerenceFigures, the OCL
										// context is the rich reference EClass
										// instance (in other words, the
										// association class instance)
										processReferenceTargetEObject(
												richReferenceFigure,
												eContentRoot, eContentRootId,
												targetEObject,
												richReferenceEClassInstance,
												srcLabel, srcStyle, stdLabel,
												stdStyle, targetLabel,
												targetStyle,
												validationDecoratorIconPath,
												monitor);
									}
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
	 * Returns the figure property value or the evaluated dynamic property
	 * overrider.
	 * 
	 * @param figure
	 *            the figure.
	 * @param eObject
	 *            the EObject.
	 * @param propertyFeature
	 *            the property to evaluate.
	 * @return the property value.
	 * @throws CoreException
	 *             thrown if a parser exception occurs.
	 */
	private Object getFigurePropertyValue(AbstractFigure figure,
			EObject eObject, EStructuralFeature propertyFeature)
			throws CoreException {
		DynamicPropertyOverrider dynPropOverrider = null;
		for (DynamicPropertyOverrider dpo : figure.getDynamicProperties()) {
			if (propertyFeature == dpo.getPropertyToOverride()) {
				dynPropOverrider = dpo;
				break;
			}
		}
		Object defaultPropertyValue = figure.eGet(propertyFeature);
		Object result = null;
		if (dynPropOverrider != null && dynPropOverrider.isEnabled()) {
			try {
				// Registers the "default property value" variable
				GraphdescHelper.registerDefaultPropertyValueOCLVariable(
						getOCL().getEnvironment(), dynPropOverrider);
				GraphdescHelper.assignDefaultPropertyValueOCLVariable(getOCL()
						.getEvaluationEnvironment(), defaultPropertyValue);
				result = evaluateOCLExpression(eObject,
						dynPropOverrider.getOverridingExpression());
			} finally {
				// Deletes the "default property value" variable
				org.emftools.emf2gv.util.OCLHelper.clearVariables(getOCL());
			}
		} else {
			result = defaultPropertyValue;
		}
		return result;
	}

	/**
	 * Parses an OCL expression.
	 * 
	 * @param context
	 *            the context to use.
	 * @param oclExpression
	 *            the OCL expression.
	 * @return the parsed expression.
	 * @throws CoreException
	 *             thrown if a parser exception occurs.
	 */
	private OCLExpression<EClassifier> parseOCLExpression(EClassifier context,
			String oclExpression) throws CoreException {
		try {
			String key = context.getInstanceClassName() + "$" + oclExpression;
			OCLExpression<EClassifier> result = parsedOCLExpressionCache
					.get(key);
			if (result == null) {
				getOclHelper().setContext(context);
				result = getOclHelper().createQuery(oclExpression);
				parsedOCLExpressionCache.put(key, result);
			}
			return result;
		} catch (ParserException e) {
			throw new CoreException(new Status(IStatus.ERROR,
					StandaloneProcessor.PLUGIN_ID,
					"Unexpected error while parsing OCL expression '"
							+ oclExpression + "'", e));
		}
	}

	/**
	 * Evaluates an OCL expression on a given context.
	 * 
	 * @param context
	 *            the context to use.
	 * @param oclExpression
	 *            the OCL expression.
	 * @return the expression evaluation result.
	 * @throws CoreException
	 *             thrown if a parser exception occurs.
	 */
	private Object evaluateOCLExpression(EObject context, String oclExpression)
			throws CoreException {
		if (oclExpression == null || "".equals(oclExpression)) {
			return "";
		} else {
			OCLExpression<EClassifier> expression = parseOCLExpression(
					context.eClass(), oclExpression);
			Object result = getOCL().evaluate(context, expression);
			return result;
		}
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
	 * @param oclContext
	 *            the context to use to evaluate OCL expressions (in property
	 *            overriders).
	 * @param srcLabel
	 *            the source label to apply to the edge.
	 * @param srcLabelStyle
	 *            the source label style
	 * @param stdLabel
	 *            the standard label to apply to the edge.
	 * @param stdLabelStyle
	 *            the standard label style
	 * @param targetLabel
	 *            the target label to apply to the edge.
	 * @param targetLabelStyle
	 *            the target label style
	 * @param monitor
	 *            the progress monitor.
	 * @throws CoreException
	 *             thrown if an unexpected error occurs.
	 */
	private void processReferenceTargetEObject(AbstractReferenceFigure figure,
			EObject srcEObject, String srcEObjectId, EObject targetEObject,
			EObject oclContext, String srcLabel,
			Collection<FontStyle> srcLabelStyle, String stdLabel,
			Collection<FontStyle> stdLabelStyle, String targetLabel,
			Collection<FontStyle> targetLabelStyle,
			String validationDecoratorIconPath, IProgressMonitor monitor)
			throws CoreException {
		String targetEObjectId = processEObject(targetEObject, monitor);
		// the target EObject Id may hav not been built if the
		// target EObject's Eclass
		// is not associated to a ClassFigure.
		if (targetEObjectId != null) {
			EdgeDesc edgeDesc = new EdgeDesc();
			edgeDesc.srcEObjectId = srcEObjectId;
			edgeDesc.srcEObject = srcEObject;
			edgeDesc.srcArrowType = (ArrowType) getFigurePropertyValue(figure,
					oclContext,
					gvPkg.getAbstractReferenceFigure_SourceArrowType());
			edgeDesc.customSrcArrow = (String) getFigurePropertyValue(figure,
					oclContext,
					gvPkg.getAbstractReferenceFigure_CustomSourceArrow());
			edgeDesc.targetEObjectId = targetEObjectId;
			edgeDesc.targetEObject = targetEObject;
			edgeDesc.targetArrowType = (ArrowType) getFigurePropertyValue(
					figure, oclContext,
					gvPkg.getAbstractReferenceFigure_TargetArrowType());
			edgeDesc.customTargetArrow = (String) getFigurePropertyValue(
					figure, oclContext,
					gvPkg.getAbstractReferenceFigure_CustomTargetArrow());
			edgeDesc.minimumEdgeLength = (Integer) getFigurePropertyValue(
					figure, oclContext,
					gvPkg.getAbstractReferenceFigure_MinimumEdgeLength());
			edgeDesc.color = (Color) getFigurePropertyValue(figure, oclContext,
					gvPkg.getAbstractReferenceFigure_Color());
			edgeDesc.style = (EdgeStyle) getFigurePropertyValue(figure,
					oclContext, gvPkg.getAbstractReferenceFigure_Style());
			edgeDesc.srcLabel = srcLabel;
			edgeDesc.srcLabelStyle = srcLabelStyle;
			edgeDesc.stdLabel = stdLabel;
			edgeDesc.stdLabelStyle = stdLabelStyle;
			edgeDesc.targetLabel = targetLabel;
			edgeDesc.targetLabelStyle = targetLabelStyle;
			edgeDesc.validationDecoratorIconPath = validationDecoratorIconPath;
			if (figure instanceof RichReferenceFigure) {
				edgeDesc.labelAngle = (Double) getFigurePropertyValue(figure,
						oclContext, gvPkg.getRichReferenceFigure_LabelAngle());
				edgeDesc.labelDistance = (Double) getFigurePropertyValue(
						figure, oclContext,
						gvPkg.getRichReferenceFigure_LabelDistance());
			}
			ClassFigure srcClassFigure = figureDesc
					.getClassFigure(edgeDesc.srcEObject.eClass());
			edgeDesc.srcFigureIsContainer = srcClassFigure.isContainer();
			ClassFigure targetClassFigure = figureDesc
					.getClassFigure(edgeDesc.targetEObject.eClass());
			edgeDesc.targetFigureIsContainer = targetClassFigure.isContainer();
			edges.add(edgeDesc);
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
		out.print('\t');
		out.print(edgeDesc.srcEObjectId);
		out.print("->");
		out.print(edgeDesc.targetEObjectId);
		// Edge style
		out.print(" [arrowhead = ");
		out.print(edgeDesc.targetArrowType.equals(ArrowType.CUSTOM) ? edgeDesc.customTargetArrow
				: edgeDesc.targetArrowType.toString());
		out.print(", arrowtail = ");
		out.print(edgeDesc.srcArrowType.equals(ArrowType.CUSTOM) ? edgeDesc.customSrcArrow
				: edgeDesc.srcArrowType.toString());
		out.print(", dir = both");
		if (edgeDesc.srcLabel != null) {
			out.print(", taillabel=<");
			out.print("\t\t<TABLE BORDER=\"0\" CELLBORDER=\"0\" CELLSPACING=\"0\" CELLPADDING=\"0\"><TR><TD>");
			flushStyledText(edgeDesc.srcLabelStyle, edgeDesc.srcLabel);
			out.print("</TD></TR></TABLE>>");
		}
		// Start of the centered label
		if (edgeDesc.stdLabel != null
				|| edgeDesc.validationDecoratorIconPath != null) {
			out.print(", label=<");
			out.println("\t\t<TABLE BORDER=\"0\" CELLBORDER=\"0\" CELLSPACING=\"0\" CELLPADDING=\"0\"><TR>");
			if (edgeDesc.stdLabel != null) {
				out.print("<TD>");
				flushStyledText(edgeDesc.stdLabelStyle, edgeDesc.stdLabel);
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
			out.print(", headlabel=<");
			out.print("\t\t<TABLE BORDER=\"0\" CELLBORDER=\"0\" CELLSPACING=\"0\" CELLPADDING=\"0\"><TR><TD>");
			flushStyledText(edgeDesc.targetLabelStyle, edgeDesc.targetLabel);
			out.print("</TD></TR></TABLE>>");
		}
		out.print(", minlen=");
		out.print(edgeDesc.minimumEdgeLength);
		if (edgeDesc.labelDistance != null) {
			out.print(", labeldistance=");
			out.print(String.valueOf(edgeDesc.labelDistance).replace(',', '.'));
		}
		if (edgeDesc.labelAngle != null) {
			out.print(", labelangle=");
			out.print(String.valueOf(edgeDesc.labelAngle).replace(',', '.'));
		}
		out.print(", color=\"");
		out.print(ColorsHelper.toHtmlColor(edgeDesc.color));
		out.print("\"");
		if (!EdgeStyle.NORMAL.equals(edgeDesc.style)) {
			out.print(", style=");
			out.print(edgeDesc.style.toString());
		}
		// If the source figure is a cluster...
		if (edgeDesc.srcFigureIsContainer) {
			out.print(", ltail=cluster_");
			out.print(edgeDesc.srcEObjectId);
		}
		// If the target figure is a cluster...
		if (edgeDesc.targetFigureIsContainer) {
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
		Map<String, List<NodeDesc>> nodesListByEclass = new HashMap<String, List<NodeDesc>>();
		for (NodeDesc nodeDesc : nodeDescs) {
			String eClassName = nodeDesc.eClassName;
			List<NodeDesc> sameEClassNodes = nodesListByEclass.get(eClassName);
			if (sameEClassNodes == null) {
				sameEClassNodes = new ArrayList<NodeDesc>();
				nodesListByEclass.put(eClassName, sameEClassNodes);
			}
			sameEClassNodes.add(nodeDesc);
		}
		// And then the nodes are flushed
		for (ClassFigure classFigure : figureDesc.getClassFigures()) {
			List<NodeDesc> sameEClassNodes = nodesListByEclass.get(classFigure
					.getEClass().getName());
			boolean doAlignNodes = figureDesc.isAlignSameEClasses()
					&& !classFigure.isContainer();
			if (sameEClassNodes != null) {
				if (doAlignNodes) {
					out.print("{ rank = same;\n");
				}
				for (NodeDesc nodeDesc : sameEClassNodes) {
					if (nodeDesc.isContainer) {
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
			out.print(ColorsHelper.toHtmlColor(nodeDesc.bodyBackgroundColor));
			out.println("\">");
			flushNodeAttributes(nodeDesc);
			out.println("\t\t\t</TD></TR>");
		}
		out.print("\t\t</TABLE>>; bgcolor=\"");
		out.print(ColorsHelper.toHtmlColor(nodeDesc.headerBackgroundColor));
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
		out.print(ColorsHelper.toHtmlColor(nodeDesc.headerBackgroundColor));
		out.println("\">");
		// Flushes the node header
		flushNodeHeader("\t\t\t\t", 1, nodeDesc);

		out.println("\t\t\t</TD></TR>");
		out.print("\t\t\t<TR><TD ALIGN=\"LEFT\" BGCOLOR=\"");
		out.print(ColorsHelper.toHtmlColor(nodeDesc.bodyBackgroundColor));
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
			out.print("\t\t\t\t\t<TR><TD> </TD></TR>");
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
				out.print("<TD ALIGN=\"LEFT\">");
				flushStyledText(attrDesc.labelStyle, attrDesc.label);
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

		// TD tag for the label
		out.print(indent);
		out.print("\t\t<TD ALIGN=\"");
		out.print(iconAvailable && !statusIconAvailable ? "LEFT" : "CENTER");
		out.print("\">");

		// If we have a label to show
		if (nodeDesc.label != null) {
			flushStyledText(nodeDesc.labelStyle, nodeDesc.label
			// FIX for too short labels to keep edges connected to the nodes
					+ (nodeDesc.label.length() < 4 ? "   " : ""));
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
	 * Flushes a styled text.
	 * 
	 * @param fontStyle
	 *            the style to apply to the text.
	 * @param text
	 *            the text to flush.
	 */
	private void flushStyledText(Collection<FontStyle> fontStyle, String text) {
		if (fontStyle.contains(FontStyle.BOLD)) {
			out.print("<B>");
		}
		if (fontStyle.contains(FontStyle.ITALIC)) {
			out.print("<I>");
		}
		if (fontStyle.contains(FontStyle.UNDERLINE)) {
			out.print("<U>");
		}
		out.print(toHtmlString(text));
		if (fontStyle.contains(FontStyle.UNDERLINE)) {
			out.print("</U>");
		}
		if (fontStyle.contains(FontStyle.ITALIC)) {
			out.print("</I>");
		}
		if (fontStyle.contains(FontStyle.BOLD)) {
			out.print("</B>");
		}
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
		str = str.replaceAll(" ", "&nbsp;");
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

	/** EClass name */
	String eClassName;

	/** EObject identifier */
	String eObjectId;

	/** Icon path */
	String iconPath;

	/** Validation decorator icon path */
	String validationDecoratorIconPath;

	/** Boolean indicating if the node is a container */
	boolean isContainer;

	/** Node label */
	String label;

	/** The label style */
	Collection<FontStyle> labelStyle;

	/** Header background color */
	Color headerBackgroundColor;

	/** Body background color */
	Color bodyBackgroundColor;

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

	/** The label that is shown */
	String label;

	/** The label style */
	Collection<FontStyle> labelStyle;

	/** Validation decorator icon path */
	String validationDecoratorIconPath;

}

/**
 * Edge description.
 */
class EdgeDesc {

	/** Source EObject identifier */
	String srcEObjectId;

	/** Source EObject */
	EObject srcEObject;

	/** Source arrow type */
	ArrowType srcArrowType;

	/** Source custom arrow type */
	String customSrcArrow;

	/** Boolean indicating if the source figure is a container */
	boolean srcFigureIsContainer;

	/** Target EObject identifier */
	String targetEObjectId;

	/** Target EObject */
	EObject targetEObject;

	/** Target arrow type */
	ArrowType targetArrowType;

	/** Target custom arrow type */
	String customTargetArrow;

	/** Boolean indicating if the target figure is a container */
	boolean targetFigureIsContainer;

	/** Label distance (as a Double as it is optional) */
	Double labelDistance;

	/** Label angle (as a Double as it is optional) */
	Double labelAngle;

	/** Edge color */
	Color color;

	/** Edge style */
	EdgeStyle style;

	/** Source label */
	String srcLabel;

	/** The source label style */
	Collection<FontStyle> srcLabelStyle;

	/** Standard label */
	String stdLabel;

	/** The standard label style */
	Collection<FontStyle> stdLabelStyle;

	/** Target label */
	String targetLabel;

	/** The target label style */
	Collection<FontStyle> targetLabelStyle;

	/** Minimum edge length */
	int minimumEdgeLength;

	/** Validation decorator icon path */
	String validationDecoratorIconPath;

}
