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
package org.emftools.emf2gv.graphdesc.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.emftools.emf2gv.graphdesc.AbstractFigure;
import org.emftools.emf2gv.graphdesc.ArrowStyle;
import org.emftools.emf2gv.graphdesc.ArrowType;
import org.emftools.emf2gv.graphdesc.AttributeFigure;
import org.emftools.emf2gv.graphdesc.ClassFigure;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.graphdesc.GraphdescFactory;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;
import org.emftools.emf2gv.graphdesc.Orientation;
import org.emftools.emf2gv.graphdesc.ReferenceFigure;
import org.emftools.emf2gv.graphdesc.util.GraphdescValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class GraphdescPackageImpl extends EPackageImpl implements GraphdescPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classFigureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attributeFigureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referenceFigureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractFigureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass gvFigureDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum orientationEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum arrowTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum arrowStyleEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private GraphdescPackageImpl() {
		super(eNS_URI, GraphdescFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link GraphdescPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static GraphdescPackage init() {
		if (isInited) return (GraphdescPackage)EPackage.Registry.INSTANCE.getEPackage(GraphdescPackage.eNS_URI);

		// Obtain or create and register package
		GraphdescPackageImpl theGraphdescPackage = (GraphdescPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof GraphdescPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new GraphdescPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theGraphdescPackage.createPackageContents();

		// Initialize created meta-data
		theGraphdescPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theGraphdescPackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return GraphdescValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theGraphdescPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(GraphdescPackage.eNS_URI, theGraphdescPackage);
		return theGraphdescPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassFigure() {
		return classFigureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassFigure_EClass() {
		return (EReference)classFigureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassFigure_LabelEAttribute() {
		return (EReference)classFigureEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassFigure_AttributeFigures() {
		return (EReference)classFigureEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassFigure_EPackage() {
		return (EReference)classFigureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassFigure_ReferenceFigures() {
		return (EReference)classFigureEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassFigure_GvFigureDescription() {
		return (EReference)classFigureEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClassFigure_HeaderBackgroundColor() {
		return (EAttribute)classFigureEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClassFigure_BodyBackgroundColor() {
		return (EAttribute)classFigureEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassFigure_NestedFiguresEReferences() {
		return (EReference)classFigureEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClassFigure_Container() {
		return (EAttribute)classFigureEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAttributeFigure() {
		return attributeFigureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttributeFigure_Label() {
		return (EAttribute)attributeFigureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAttributeFigure_ClassFigure() {
		return (EReference)attributeFigureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAttributeFigure_EAttribute() {
		return (EReference)attributeFigureEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReferenceFigure() {
		return referenceFigureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReferenceFigure_EReference() {
		return (EReference)referenceFigureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReferenceFigure_ClassFigure() {
		return (EReference)referenceFigureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getReferenceFigure_TargetArrowType() {
		return (EAttribute)referenceFigureEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getReferenceFigure_SourceArrowType() {
		return (EAttribute)referenceFigureEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getReferenceFigure_Containment() {
		return (EAttribute)referenceFigureEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getReferenceFigure_CustomTargetArrow() {
		return (EAttribute)referenceFigureEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getReferenceFigure_CustomSourceArrow() {
		return (EAttribute)referenceFigureEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getReferenceFigure_Color() {
		return (EAttribute)referenceFigureEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getReferenceFigure_Style() {
		return (EAttribute)referenceFigureEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractFigure() {
		return abstractFigureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractFigure_Name() {
		return (EAttribute)abstractFigureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGVFigureDescription() {
		return gvFigureDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGVFigureDescription_ClassFigures() {
		return (EReference)gvFigureDescriptionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGVFigureDescription_Orientation() {
		return (EAttribute)gvFigureDescriptionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGVFigureDescription_AlignSameEClasses() {
		return (EAttribute)gvFigureDescriptionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getOrientation() {
		return orientationEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getArrowType() {
		return arrowTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getArrowStyle() {
		return arrowStyleEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGVFigureDescription_EPackages() {
		return (EReference)gvFigureDescriptionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphdescFactory getGraphdescFactory() {
		return (GraphdescFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		gvFigureDescriptionEClass = createEClass(GV_FIGURE_DESCRIPTION);
		createEReference(gvFigureDescriptionEClass, GV_FIGURE_DESCRIPTION__EPACKAGES);
		createEReference(gvFigureDescriptionEClass, GV_FIGURE_DESCRIPTION__CLASS_FIGURES);
		createEAttribute(gvFigureDescriptionEClass, GV_FIGURE_DESCRIPTION__ORIENTATION);
		createEAttribute(gvFigureDescriptionEClass, GV_FIGURE_DESCRIPTION__ALIGN_SAME_ECLASSES);

		classFigureEClass = createEClass(CLASS_FIGURE);
		createEReference(classFigureEClass, CLASS_FIGURE__EPACKAGE);
		createEReference(classFigureEClass, CLASS_FIGURE__ECLASS);
		createEReference(classFigureEClass, CLASS_FIGURE__LABEL_EATTRIBUTE);
		createEReference(classFigureEClass, CLASS_FIGURE__ATTRIBUTE_FIGURES);
		createEReference(classFigureEClass, CLASS_FIGURE__REFERENCE_FIGURES);
		createEReference(classFigureEClass, CLASS_FIGURE__GV_FIGURE_DESCRIPTION);
		createEAttribute(classFigureEClass, CLASS_FIGURE__HEADER_BACKGROUND_COLOR);
		createEAttribute(classFigureEClass, CLASS_FIGURE__BODY_BACKGROUND_COLOR);
		createEReference(classFigureEClass, CLASS_FIGURE__NESTED_FIGURES_EREFERENCES);
		createEAttribute(classFigureEClass, CLASS_FIGURE__CONTAINER);

		attributeFigureEClass = createEClass(ATTRIBUTE_FIGURE);
		createEAttribute(attributeFigureEClass, ATTRIBUTE_FIGURE__LABEL);
		createEReference(attributeFigureEClass, ATTRIBUTE_FIGURE__CLASS_FIGURE);
		createEReference(attributeFigureEClass, ATTRIBUTE_FIGURE__EATTRIBUTE);

		referenceFigureEClass = createEClass(REFERENCE_FIGURE);
		createEReference(referenceFigureEClass, REFERENCE_FIGURE__CLASS_FIGURE);
		createEReference(referenceFigureEClass, REFERENCE_FIGURE__EREFERENCE);
		createEAttribute(referenceFigureEClass, REFERENCE_FIGURE__TARGET_ARROW_TYPE);
		createEAttribute(referenceFigureEClass, REFERENCE_FIGURE__SOURCE_ARROW_TYPE);
		createEAttribute(referenceFigureEClass, REFERENCE_FIGURE__CONTAINMENT);
		createEAttribute(referenceFigureEClass, REFERENCE_FIGURE__CUSTOM_TARGET_ARROW);
		createEAttribute(referenceFigureEClass, REFERENCE_FIGURE__CUSTOM_SOURCE_ARROW);
		createEAttribute(referenceFigureEClass, REFERENCE_FIGURE__COLOR);
		createEAttribute(referenceFigureEClass, REFERENCE_FIGURE__STYLE);

		abstractFigureEClass = createEClass(ABSTRACT_FIGURE);
		createEAttribute(abstractFigureEClass, ABSTRACT_FIGURE__NAME);

		// Create enums
		orientationEEnum = createEEnum(ORIENTATION);
		arrowTypeEEnum = createEEnum(ARROW_TYPE);
		arrowStyleEEnum = createEEnum(ARROW_STYLE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		classFigureEClass.getESuperTypes().add(this.getAbstractFigure());
		attributeFigureEClass.getESuperTypes().add(this.getAbstractFigure());
		referenceFigureEClass.getESuperTypes().add(this.getAbstractFigure());

		// Initialize classes and features; add operations and parameters
		initEClass(gvFigureDescriptionEClass, GVFigureDescription.class, "GVFigureDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGVFigureDescription_EPackages(), theEcorePackage.getEPackage(), null, "ePackages", null, 0, -1, GVFigureDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGVFigureDescription_ClassFigures(), this.getClassFigure(), this.getClassFigure_GvFigureDescription(), "classFigures", null, 0, -1, GVFigureDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGVFigureDescription_Orientation(), this.getOrientation(), "orientation", "LeftToRight", 0, 1, GVFigureDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGVFigureDescription_AlignSameEClasses(), theEcorePackage.getEBoolean(), "alignSameEClasses", null, 0, 1, GVFigureDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(gvFigureDescriptionEClass, ecorePackage.getEBoolean(), "validate", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostic", 0, 1, IS_UNIQUE, IS_ORDERED);
		EGenericType g1 = createEGenericType(ecorePackage.getEMap());
		EGenericType g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(gvFigureDescriptionEClass, this.getClassFigure(), "getClassFigure", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEClass(), "eClass", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(classFigureEClass, ClassFigure.class, "ClassFigure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClassFigure_EPackage(), theEcorePackage.getEPackage(), null, "ePackage", null, 0, 1, ClassFigure.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getClassFigure_EClass(), theEcorePackage.getEClass(), null, "eClass", null, 1, 1, ClassFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClassFigure_LabelEAttribute(), theEcorePackage.getEAttribute(), null, "labelEAttribute", null, 0, 1, ClassFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClassFigure_AttributeFigures(), this.getAttributeFigure(), this.getAttributeFigure_ClassFigure(), "attributeFigures", null, 0, -1, ClassFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClassFigure_ReferenceFigures(), this.getReferenceFigure(), this.getReferenceFigure_ClassFigure(), "referenceFigures", null, 0, -1, ClassFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClassFigure_GvFigureDescription(), this.getGVFigureDescription(), this.getGVFigureDescription_ClassFigures(), "gvFigureDescription", null, 1, 1, ClassFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClassFigure_HeaderBackgroundColor(), theEcorePackage.getEInt(), "headerBackgroundColor", "10337785", 0, 1, ClassFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClassFigure_BodyBackgroundColor(), theEcorePackage.getEInt(), "bodyBackgroundColor", "15658734", 0, 1, ClassFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClassFigure_NestedFiguresEReferences(), theEcorePackage.getEReference(), null, "nestedFiguresEReferences", null, 0, -1, ClassFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClassFigure_Container(), theEcorePackage.getEBoolean(), "container", null, 0, 1, ClassFigure.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		op = addEOperation(classFigureEClass, this.getAttributeFigure(), "getAttributeFigure", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEAttribute(), "eAttribute", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(classFigureEClass, this.getReferenceFigure(), "getReferenceFigure", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEReference(), "eReference", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(classFigureEClass, ecorePackage.getEBoolean(), "validate", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostic", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(attributeFigureEClass, AttributeFigure.class, "AttributeFigure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAttributeFigure_Label(), theEcorePackage.getEString(), "label", null, 0, 1, AttributeFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAttributeFigure_ClassFigure(), this.getClassFigure(), this.getClassFigure_AttributeFigures(), "classFigure", null, 1, 1, AttributeFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAttributeFigure_EAttribute(), theEcorePackage.getEAttribute(), null, "eAttribute", null, 1, 1, AttributeFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(attributeFigureEClass, ecorePackage.getEBoolean(), "validate", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostic", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(referenceFigureEClass, ReferenceFigure.class, "ReferenceFigure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReferenceFigure_ClassFigure(), this.getClassFigure(), this.getClassFigure_ReferenceFigures(), "classFigure", null, 1, 1, ReferenceFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getReferenceFigure_EReference(), theEcorePackage.getEReference(), null, "eReference", null, 1, 1, ReferenceFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceFigure_TargetArrowType(), this.getArrowType(), "targetArrowType", "normal", 0, 1, ReferenceFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceFigure_SourceArrowType(), this.getArrowType(), "sourceArrowType", "none", 0, 1, ReferenceFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceFigure_Containment(), ecorePackage.getEBoolean(), "containment", null, 0, 1, ReferenceFigure.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceFigure_CustomTargetArrow(), theEcorePackage.getEString(), "customTargetArrow", null, 0, 1, ReferenceFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceFigure_CustomSourceArrow(), theEcorePackage.getEString(), "customSourceArrow", null, 0, 1, ReferenceFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceFigure_Color(), theEcorePackage.getEInt(), "color", null, 0, 1, ReferenceFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceFigure_Style(), this.getArrowStyle(), "style", "normal", 0, 1, ReferenceFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(referenceFigureEClass, ecorePackage.getEBoolean(), "validate", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostic", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(referenceFigureEClass, theEcorePackage.getEBoolean(), "targetClassFigureExists", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(abstractFigureEClass, AbstractFigure.class, "AbstractFigure", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAbstractFigure_Name(), theEcorePackage.getEString(), "name", null, 0, 1, AbstractFigure.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(orientationEEnum, Orientation.class, "Orientation");
		addEEnumLiteral(orientationEEnum, Orientation.TOP_DOWN);
		addEEnumLiteral(orientationEEnum, Orientation.LEFT_TO_RIGHT);

		initEEnum(arrowTypeEEnum, ArrowType.class, "ArrowType");
		addEEnumLiteral(arrowTypeEEnum, ArrowType.NORMAL);
		addEEnumLiteral(arrowTypeEEnum, ArrowType.BOX);
		addEEnumLiteral(arrowTypeEEnum, ArrowType.CROW);
		addEEnumLiteral(arrowTypeEEnum, ArrowType.DIAMOND);
		addEEnumLiteral(arrowTypeEEnum, ArrowType.DOT);
		addEEnumLiteral(arrowTypeEEnum, ArrowType.INV);
		addEEnumLiteral(arrowTypeEEnum, ArrowType.NONE);
		addEEnumLiteral(arrowTypeEEnum, ArrowType.TEE);
		addEEnumLiteral(arrowTypeEEnum, ArrowType.VEE);
		addEEnumLiteral(arrowTypeEEnum, ArrowType.CUSTOM);

		initEEnum(arrowStyleEEnum, ArrowStyle.class, "ArrowStyle");
		addEEnumLiteral(arrowStyleEEnum, ArrowStyle.NORMAL);
		addEEnumLiteral(arrowStyleEEnum, ArrowStyle.DASHED);
		addEEnumLiteral(arrowStyleEEnum, ArrowStyle.DOTTED);
		addEEnumLiteral(arrowStyleEEnum, ArrowStyle.BOLD);
		addEEnumLiteral(arrowStyleEEnum, ArrowStyle.INVIS);

		// Create resource
		createResource(eNS_URI);
	}

} //GraphdescPackageImpl
