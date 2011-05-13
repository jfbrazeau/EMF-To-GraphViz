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
 * 
 */
package org.emftools.emf2gv.graphdesc.impl;

import java.awt.Color;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.emftools.emf2gv.graphdesc.AbstractAttributeFigure;
import org.emftools.emf2gv.graphdesc.AbstractFigure;
import org.emftools.emf2gv.graphdesc.AbstractReferenceFigure;
import org.emftools.emf2gv.graphdesc.ArrowStyle;
import org.emftools.emf2gv.graphdesc.ArrowType;
import org.emftools.emf2gv.graphdesc.AttributeFigure;
import org.emftools.emf2gv.graphdesc.ClassFigure;
import org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider;
import org.emftools.emf2gv.graphdesc.Filter;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.graphdesc.GraphdescFactory;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;
import org.emftools.emf2gv.graphdesc.Orientation;
import org.emftools.emf2gv.graphdesc.ReferenceFigure;
import org.emftools.emf2gv.graphdesc.RichAttributeFigure;
import org.emftools.emf2gv.graphdesc.RichReferenceFigure;
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
	private EClass gvFigureDescriptionEClass = null;

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
	private EClass richReferenceFigureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dynamicPropertyOverriderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass filterEClass = null;

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
	private EClass abstractReferenceFigureEClass = null;

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
	private EClass abstractAttributeFigureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass richAttributeFigureEClass = null;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType colorEDataType = null;

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
	public EClass getGVFigureDescription() {
		return gvFigureDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGVFigureDescription_EPackages() {
		return (EReference)gvFigureDescriptionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGVFigureDescription_ClassFigures() {
		return (EReference)gvFigureDescriptionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGVFigureDescription_Orientation() {
		return (EAttribute)gvFigureDescriptionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGVFigureDescription_AlignSameEClasses() {
		return (EAttribute)gvFigureDescriptionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGVFigureDescription_Filters() {
		return (EReference)gvFigureDescriptionEClass.getEStructuralFeatures().get(0);
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
	public EReference getClassFigure_EPackage() {
		return (EReference)classFigureEClass.getEStructuralFeatures().get(0);
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
	public EAttribute getClassFigure_Container() {
		return (EAttribute)classFigureEClass.getEStructuralFeatures().get(9);
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
	public EReference getAttributeFigure_EAttribute() {
		return (EReference)attributeFigureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRichReferenceFigure() {
		return richReferenceFigureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRichReferenceFigure_TargetEReference() {
		return (EReference)richReferenceFigureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRichReferenceFigure_SourceLabelExpression() {
		return (EAttribute)richReferenceFigureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRichReferenceFigure_StandardLabelExpression() {
		return (EAttribute)richReferenceFigureEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRichReferenceFigure_TargetLabelExpression() {
		return (EAttribute)richReferenceFigureEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRichReferenceFigure_LabelDistance() {
		return (EAttribute)richReferenceFigureEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRichReferenceFigure_LabelAngle() {
		return (EAttribute)richReferenceFigureEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDynamicPropertyOverrider() {
		return dynamicPropertyOverriderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDynamicPropertyOverrider_Name() {
		return (EAttribute)dynamicPropertyOverriderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDynamicPropertyOverrider_PropertyToOverride() {
		return (EReference)dynamicPropertyOverriderEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDynamicPropertyOverrider_OverridingExpression() {
		return (EAttribute)dynamicPropertyOverriderEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDynamicPropertyOverrider_Figure() {
		return (EReference)dynamicPropertyOverriderEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFilter() {
		return filterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFilter_Name() {
		return (EAttribute)filterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFilter_FilteredType() {
		return (EReference)filterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFilter_FilterExpression() {
		return (EAttribute)filterEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFilter_FigureDescription() {
		return (EReference)filterEClass.getEStructuralFeatures().get(3);
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
	public EReference getAbstractFigure_DynamicProperties() {
		return (EReference)abstractFigureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractReferenceFigure() {
		return abstractReferenceFigureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractReferenceFigure_ClassFigure() {
		return (EReference)abstractReferenceFigureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractReferenceFigure_EReference() {
		return (EReference)abstractReferenceFigureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractReferenceFigure_TargetArrowType() {
		return (EAttribute)abstractReferenceFigureEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractReferenceFigure_SourceArrowType() {
		return (EAttribute)abstractReferenceFigureEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractReferenceFigure_Containment() {
		return (EAttribute)abstractReferenceFigureEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractReferenceFigure_CustomTargetArrow() {
		return (EAttribute)abstractReferenceFigureEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractReferenceFigure_CustomSourceArrow() {
		return (EAttribute)abstractReferenceFigureEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractReferenceFigure_Color() {
		return (EAttribute)abstractReferenceFigureEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractReferenceFigure_Style() {
		return (EAttribute)abstractReferenceFigureEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractReferenceFigure_TargetEType() {
		return (EReference)abstractReferenceFigureEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractReferenceFigure_MinimumEdgeLength() {
		return (EAttribute)abstractReferenceFigureEClass.getEStructuralFeatures().get(10);
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
	public EClass getAbstractAttributeFigure() {
		return abstractAttributeFigureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractAttributeFigure_ClassFigure() {
		return (EReference)abstractAttributeFigureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRichAttributeFigure() {
		return richAttributeFigureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRichAttributeFigure_EReference() {
		return (EReference)richAttributeFigureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRichAttributeFigure_LabelExpression() {
		return (EAttribute)richAttributeFigureEClass.getEStructuralFeatures().get(1);
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
	public EDataType getColor() {
		return colorEDataType;
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
		createEReference(gvFigureDescriptionEClass, GV_FIGURE_DESCRIPTION__FILTERS);
		createEReference(gvFigureDescriptionEClass, GV_FIGURE_DESCRIPTION__EPACKAGES);
		createEReference(gvFigureDescriptionEClass, GV_FIGURE_DESCRIPTION__CLASS_FIGURES);
		createEAttribute(gvFigureDescriptionEClass, GV_FIGURE_DESCRIPTION__ORIENTATION);
		createEAttribute(gvFigureDescriptionEClass, GV_FIGURE_DESCRIPTION__ALIGN_SAME_ECLASSES);

		abstractFigureEClass = createEClass(ABSTRACT_FIGURE);
		createEAttribute(abstractFigureEClass, ABSTRACT_FIGURE__NAME);
		createEReference(abstractFigureEClass, ABSTRACT_FIGURE__DYNAMIC_PROPERTIES);

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

		abstractAttributeFigureEClass = createEClass(ABSTRACT_ATTRIBUTE_FIGURE);
		createEReference(abstractAttributeFigureEClass, ABSTRACT_ATTRIBUTE_FIGURE__CLASS_FIGURE);

		attributeFigureEClass = createEClass(ATTRIBUTE_FIGURE);
		createEAttribute(attributeFigureEClass, ATTRIBUTE_FIGURE__LABEL);
		createEReference(attributeFigureEClass, ATTRIBUTE_FIGURE__EATTRIBUTE);

		richAttributeFigureEClass = createEClass(RICH_ATTRIBUTE_FIGURE);
		createEReference(richAttributeFigureEClass, RICH_ATTRIBUTE_FIGURE__EREFERENCE);
		createEAttribute(richAttributeFigureEClass, RICH_ATTRIBUTE_FIGURE__LABEL_EXPRESSION);

		abstractReferenceFigureEClass = createEClass(ABSTRACT_REFERENCE_FIGURE);
		createEReference(abstractReferenceFigureEClass, ABSTRACT_REFERENCE_FIGURE__CLASS_FIGURE);
		createEReference(abstractReferenceFigureEClass, ABSTRACT_REFERENCE_FIGURE__EREFERENCE);
		createEAttribute(abstractReferenceFigureEClass, ABSTRACT_REFERENCE_FIGURE__TARGET_ARROW_TYPE);
		createEAttribute(abstractReferenceFigureEClass, ABSTRACT_REFERENCE_FIGURE__SOURCE_ARROW_TYPE);
		createEAttribute(abstractReferenceFigureEClass, ABSTRACT_REFERENCE_FIGURE__CONTAINMENT);
		createEAttribute(abstractReferenceFigureEClass, ABSTRACT_REFERENCE_FIGURE__CUSTOM_TARGET_ARROW);
		createEAttribute(abstractReferenceFigureEClass, ABSTRACT_REFERENCE_FIGURE__CUSTOM_SOURCE_ARROW);
		createEAttribute(abstractReferenceFigureEClass, ABSTRACT_REFERENCE_FIGURE__COLOR);
		createEAttribute(abstractReferenceFigureEClass, ABSTRACT_REFERENCE_FIGURE__STYLE);
		createEReference(abstractReferenceFigureEClass, ABSTRACT_REFERENCE_FIGURE__TARGET_ETYPE);
		createEAttribute(abstractReferenceFigureEClass, ABSTRACT_REFERENCE_FIGURE__MINIMUM_EDGE_LENGTH);

		referenceFigureEClass = createEClass(REFERENCE_FIGURE);

		richReferenceFigureEClass = createEClass(RICH_REFERENCE_FIGURE);
		createEReference(richReferenceFigureEClass, RICH_REFERENCE_FIGURE__TARGET_EREFERENCE);
		createEAttribute(richReferenceFigureEClass, RICH_REFERENCE_FIGURE__SOURCE_LABEL_EXPRESSION);
		createEAttribute(richReferenceFigureEClass, RICH_REFERENCE_FIGURE__STANDARD_LABEL_EXPRESSION);
		createEAttribute(richReferenceFigureEClass, RICH_REFERENCE_FIGURE__TARGET_LABEL_EXPRESSION);
		createEAttribute(richReferenceFigureEClass, RICH_REFERENCE_FIGURE__LABEL_DISTANCE);
		createEAttribute(richReferenceFigureEClass, RICH_REFERENCE_FIGURE__LABEL_ANGLE);

		dynamicPropertyOverriderEClass = createEClass(DYNAMIC_PROPERTY_OVERRIDER);
		createEAttribute(dynamicPropertyOverriderEClass, DYNAMIC_PROPERTY_OVERRIDER__NAME);
		createEReference(dynamicPropertyOverriderEClass, DYNAMIC_PROPERTY_OVERRIDER__PROPERTY_TO_OVERRIDE);
		createEAttribute(dynamicPropertyOverriderEClass, DYNAMIC_PROPERTY_OVERRIDER__OVERRIDING_EXPRESSION);
		createEReference(dynamicPropertyOverriderEClass, DYNAMIC_PROPERTY_OVERRIDER__FIGURE);

		filterEClass = createEClass(FILTER);
		createEAttribute(filterEClass, FILTER__NAME);
		createEReference(filterEClass, FILTER__FILTERED_TYPE);
		createEAttribute(filterEClass, FILTER__FILTER_EXPRESSION);
		createEReference(filterEClass, FILTER__FIGURE_DESCRIPTION);

		// Create enums
		orientationEEnum = createEEnum(ORIENTATION);
		arrowTypeEEnum = createEEnum(ARROW_TYPE);
		arrowStyleEEnum = createEEnum(ARROW_STYLE);

		// Create data types
		colorEDataType = createEDataType(COLOR);
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
		abstractAttributeFigureEClass.getESuperTypes().add(this.getAbstractFigure());
		attributeFigureEClass.getESuperTypes().add(this.getAbstractAttributeFigure());
		richAttributeFigureEClass.getESuperTypes().add(this.getAbstractAttributeFigure());
		abstractReferenceFigureEClass.getESuperTypes().add(this.getAbstractFigure());
		referenceFigureEClass.getESuperTypes().add(this.getAbstractReferenceFigure());
		richReferenceFigureEClass.getESuperTypes().add(this.getAbstractReferenceFigure());

		// Initialize classes and features; add operations and parameters
		initEClass(gvFigureDescriptionEClass, GVFigureDescription.class, "GVFigureDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGVFigureDescription_Filters(), this.getFilter(), this.getFilter_FigureDescription(), "filters", null, 0, -1, GVFigureDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
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

		initEClass(abstractFigureEClass, AbstractFigure.class, "AbstractFigure", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAbstractFigure_Name(), theEcorePackage.getEString(), "name", null, 0, 1, AbstractFigure.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractFigure_DynamicProperties(), this.getDynamicPropertyOverrider(), this.getDynamicPropertyOverrider_Figure(), "dynamicProperties", null, 0, -1, AbstractFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(abstractFigureEClass, ecorePackage.getEBoolean(), "validate", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostic", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(classFigureEClass, ClassFigure.class, "ClassFigure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClassFigure_EPackage(), theEcorePackage.getEPackage(), null, "ePackage", null, 0, 1, ClassFigure.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getClassFigure_EClass(), theEcorePackage.getEClass(), null, "eClass", null, 1, 1, ClassFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClassFigure_LabelEAttribute(), theEcorePackage.getEAttribute(), null, "labelEAttribute", null, 0, 1, ClassFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClassFigure_AttributeFigures(), this.getAbstractAttributeFigure(), this.getAbstractAttributeFigure_ClassFigure(), "attributeFigures", null, 0, -1, ClassFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClassFigure_ReferenceFigures(), this.getAbstractReferenceFigure(), this.getAbstractReferenceFigure_ClassFigure(), "referenceFigures", null, 0, -1, ClassFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClassFigure_GvFigureDescription(), this.getGVFigureDescription(), this.getGVFigureDescription_ClassFigures(), "gvFigureDescription", null, 1, 1, ClassFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClassFigure_HeaderBackgroundColor(), this.getColor(), "headerBackgroundColor", "#9DBDF9", 0, 1, ClassFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClassFigure_BodyBackgroundColor(), this.getColor(), "bodyBackgroundColor", "#EEEEEE", 0, 1, ClassFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClassFigure_NestedFiguresEReferences(), theEcorePackage.getEReference(), null, "nestedFiguresEReferences", null, 0, -1, ClassFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClassFigure_Container(), theEcorePackage.getEBoolean(), "container", null, 0, 1, ClassFigure.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		op = addEOperation(classFigureEClass, this.getAttributeFigure(), "getAttributeFigure", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEAttribute(), "eAttribute", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(classFigureEClass, this.getReferenceFigure(), "getReferenceFigure", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEReference(), "eReference", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(abstractAttributeFigureEClass, AbstractAttributeFigure.class, "AbstractAttributeFigure", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractAttributeFigure_ClassFigure(), this.getClassFigure(), this.getClassFigure_AttributeFigures(), "classFigure", null, 1, 1, AbstractAttributeFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(attributeFigureEClass, AttributeFigure.class, "AttributeFigure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAttributeFigure_Label(), theEcorePackage.getEString(), "label", null, 0, 1, AttributeFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAttributeFigure_EAttribute(), theEcorePackage.getEAttribute(), null, "eAttribute", null, 1, 1, AttributeFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(richAttributeFigureEClass, RichAttributeFigure.class, "RichAttributeFigure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRichAttributeFigure_EReference(), theEcorePackage.getEReference(), null, "eReference", null, 1, 1, RichAttributeFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRichAttributeFigure_LabelExpression(), theEcorePackage.getEString(), "labelExpression", null, 0, 1, RichAttributeFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractReferenceFigureEClass, AbstractReferenceFigure.class, "AbstractReferenceFigure", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractReferenceFigure_ClassFigure(), this.getClassFigure(), this.getClassFigure_ReferenceFigures(), "classFigure", null, 1, 1, AbstractReferenceFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractReferenceFigure_EReference(), theEcorePackage.getEReference(), null, "eReference", null, 1, 1, AbstractReferenceFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractReferenceFigure_TargetArrowType(), this.getArrowType(), "targetArrowType", "normal", 0, 1, AbstractReferenceFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractReferenceFigure_SourceArrowType(), this.getArrowType(), "sourceArrowType", "none", 0, 1, AbstractReferenceFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractReferenceFigure_Containment(), ecorePackage.getEBoolean(), "containment", null, 0, 1, AbstractReferenceFigure.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractReferenceFigure_CustomTargetArrow(), theEcorePackage.getEString(), "customTargetArrow", null, 0, 1, AbstractReferenceFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractReferenceFigure_CustomSourceArrow(), theEcorePackage.getEString(), "customSourceArrow", null, 0, 1, AbstractReferenceFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractReferenceFigure_Color(), this.getColor(), "color", "#000000", 0, 1, AbstractReferenceFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractReferenceFigure_Style(), this.getArrowStyle(), "style", "normal", 0, 1, AbstractReferenceFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractReferenceFigure_TargetEType(), theEcorePackage.getEClass(), null, "targetEType", null, 0, 1, AbstractReferenceFigure.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractReferenceFigure_MinimumEdgeLength(), theEcorePackage.getEInt(), "minimumEdgeLength", "1", 0, 1, AbstractReferenceFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(referenceFigureEClass, ReferenceFigure.class, "ReferenceFigure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(referenceFigureEClass, theEcorePackage.getEBoolean(), "targetClassFigureExists", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(richReferenceFigureEClass, RichReferenceFigure.class, "RichReferenceFigure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRichReferenceFigure_TargetEReference(), theEcorePackage.getEReference(), null, "targetEReference", null, 1, 1, RichReferenceFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRichReferenceFigure_SourceLabelExpression(), ecorePackage.getEString(), "sourceLabelExpression", null, 0, 1, RichReferenceFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRichReferenceFigure_StandardLabelExpression(), ecorePackage.getEString(), "standardLabelExpression", null, 0, 1, RichReferenceFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRichReferenceFigure_TargetLabelExpression(), ecorePackage.getEString(), "targetLabelExpression", null, 0, 1, RichReferenceFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRichReferenceFigure_LabelDistance(), ecorePackage.getEDouble(), "labelDistance", "5.0", 0, 1, RichReferenceFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRichReferenceFigure_LabelAngle(), theEcorePackage.getEDouble(), "labelAngle", "0", 0, 1, RichReferenceFigure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dynamicPropertyOverriderEClass, DynamicPropertyOverrider.class, "DynamicPropertyOverrider", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDynamicPropertyOverrider_Name(), ecorePackage.getEString(), "name", null, 0, 1, DynamicPropertyOverrider.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDynamicPropertyOverrider_PropertyToOverride(), theEcorePackage.getEStructuralFeature(), null, "propertyToOverride", null, 0, 1, DynamicPropertyOverrider.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDynamicPropertyOverrider_OverridingExpression(), ecorePackage.getEString(), "overridingExpression", null, 0, 1, DynamicPropertyOverrider.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDynamicPropertyOverrider_Figure(), this.getAbstractFigure(), this.getAbstractFigure_DynamicProperties(), "figure", null, 0, 1, DynamicPropertyOverrider.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(dynamicPropertyOverriderEClass, ecorePackage.getEBoolean(), "validate", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostic", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(filterEClass, Filter.class, "Filter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFilter_Name(), ecorePackage.getEString(), "name", null, 0, 1, Filter.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getFilter_FilteredType(), theEcorePackage.getEClass(), null, "filteredType", null, 0, 1, Filter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFilter_FilterExpression(), ecorePackage.getEString(), "filterExpression", null, 0, 1, Filter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFilter_FigureDescription(), this.getGVFigureDescription(), this.getGVFigureDescription_Filters(), "figureDescription", null, 0, 1, Filter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(filterEClass, ecorePackage.getEBoolean(), "validate", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostic", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED);

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
		addEEnumLiteral(arrowStyleEEnum, ArrowStyle.INVIS);
		addEEnumLiteral(arrowStyleEEnum, ArrowStyle.NORMAL);
		addEEnumLiteral(arrowStyleEEnum, ArrowStyle.DASHED);
		addEEnumLiteral(arrowStyleEEnum, ArrowStyle.DOTTED);
		addEEnumLiteral(arrowStyleEEnum, ArrowStyle.BOLD);

		// Initialize data types
		initEDataType(colorEDataType, Color.class, "Color", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://org.emftools.emf2gv.graphdesc/OverridableProperty
		createOverridablePropertyAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://org.emftools.emf2gv.graphdesc/OverridableProperty</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createOverridablePropertyAnnotations() {
		String source = "http://org.emftools.emf2gv.graphdesc/OverridableProperty";		
		addAnnotation
		  (getClassFigure_HeaderBackgroundColor(), 
		   source, 
		   new String[] {
		   });		
		addAnnotation
		  (getClassFigure_BodyBackgroundColor(), 
		   source, 
		   new String[] {
		   });		
		addAnnotation
		  (getAbstractReferenceFigure_TargetArrowType(), 
		   source, 
		   new String[] {
		   });		
		addAnnotation
		  (getAbstractReferenceFigure_SourceArrowType(), 
		   source, 
		   new String[] {
		   });		
		addAnnotation
		  (getAbstractReferenceFigure_CustomTargetArrow(), 
		   source, 
		   new String[] {
		   });		
		addAnnotation
		  (getAbstractReferenceFigure_CustomSourceArrow(), 
		   source, 
		   new String[] {
		   });		
		addAnnotation
		  (getAbstractReferenceFigure_Color(), 
		   source, 
		   new String[] {
		   });		
		addAnnotation
		  (getAbstractReferenceFigure_Style(), 
		   source, 
		   new String[] {
		   });		
		addAnnotation
		  (getAbstractReferenceFigure_MinimumEdgeLength(), 
		   source, 
		   new String[] {
		   });		
		addAnnotation
		  (getRichReferenceFigure_LabelDistance(), 
		   source, 
		   new String[] {
		   });		
		addAnnotation
		  (getRichReferenceFigure_LabelAngle(), 
		   source, 
		   new String[] {
		   });
	}

} //GraphdescPackageImpl
