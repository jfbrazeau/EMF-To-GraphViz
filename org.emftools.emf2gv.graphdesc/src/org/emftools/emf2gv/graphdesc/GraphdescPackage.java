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
package org.emftools.emf2gv.graphdesc;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.emftools.emf2gv.graphdesc.GraphdescFactory
 * @model kind="package"
 * @generated
 */
public interface GraphdescPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "graphdesc";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://org.emftools.emf2gv.graphdesc/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "graphdesc";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	GraphdescPackage eINSTANCE = org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.emftools.emf2gv.graphdesc.impl.ClassFigureImpl <em>Class Figure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.emftools.emf2gv.graphdesc.impl.ClassFigureImpl
	 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getClassFigure()
	 * @generated
	 */
	int CLASS_FIGURE = 1;

	/**
	 * The meta object id for the '{@link org.emftools.emf2gv.graphdesc.impl.AttributeFigureImpl <em>Attribute Figure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.emftools.emf2gv.graphdesc.impl.AttributeFigureImpl
	 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getAttributeFigure()
	 * @generated
	 */
	int ATTRIBUTE_FIGURE = 2;

	/**
	 * The meta object id for the '{@link org.emftools.emf2gv.graphdesc.impl.ReferenceFigureImpl <em>Reference Figure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.emftools.emf2gv.graphdesc.impl.ReferenceFigureImpl
	 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getReferenceFigure()
	 * @generated
	 */
	int REFERENCE_FIGURE = 3;

	/**
	 * The meta object id for the '{@link org.emftools.emf2gv.graphdesc.impl.GVFigureDescriptionImpl <em>GV Figure Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.emftools.emf2gv.graphdesc.impl.GVFigureDescriptionImpl
	 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getGVFigureDescription()
	 * @generated
	 */
	int GV_FIGURE_DESCRIPTION = 0;

	/**
	 * The feature id for the '<em><b>EPackages</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GV_FIGURE_DESCRIPTION__EPACKAGES = 0;

	/**
	 * The feature id for the '<em><b>Class Figures</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GV_FIGURE_DESCRIPTION__CLASS_FIGURES = 1;

	/**
	 * The feature id for the '<em><b>Orientation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GV_FIGURE_DESCRIPTION__ORIENTATION = 2;

	/**
	 * The feature id for the '<em><b>Align Same EClasses</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GV_FIGURE_DESCRIPTION__ALIGN_SAME_ECLASSES = 3;

	/**
	 * The number of structural features of the '<em>GV Figure Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GV_FIGURE_DESCRIPTION_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.emftools.emf2gv.graphdesc.impl.AbstractFigureImpl <em>Abstract Figure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.emftools.emf2gv.graphdesc.impl.AbstractFigureImpl
	 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getAbstractFigure()
	 * @generated
	 */
	int ABSTRACT_FIGURE = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FIGURE__NAME = 0;

	/**
	 * The number of structural features of the '<em>Abstract Figure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FIGURE_FEATURE_COUNT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FIGURE__NAME = ABSTRACT_FIGURE__NAME;

	/**
	 * The feature id for the '<em><b>EPackage</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FIGURE__EPACKAGE = ABSTRACT_FIGURE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>EClass</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FIGURE__ECLASS = ABSTRACT_FIGURE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Label EAttribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FIGURE__LABEL_EATTRIBUTE = ABSTRACT_FIGURE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Attribute Figures</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FIGURE__ATTRIBUTE_FIGURES = ABSTRACT_FIGURE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Reference Figures</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FIGURE__REFERENCE_FIGURES = ABSTRACT_FIGURE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Gv Figure Description</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FIGURE__GV_FIGURE_DESCRIPTION = ABSTRACT_FIGURE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Header Background Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FIGURE__HEADER_BACKGROUND_COLOR = ABSTRACT_FIGURE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Body Background Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FIGURE__BODY_BACKGROUND_COLOR = ABSTRACT_FIGURE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Nested Figures EReferences</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FIGURE__NESTED_FIGURES_EREFERENCES = ABSTRACT_FIGURE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Dynamic Appearance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FIGURE__DYNAMIC_APPEARANCE = ABSTRACT_FIGURE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Header Background Color Accessor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FIGURE__HEADER_BACKGROUND_COLOR_ACCESSOR = ABSTRACT_FIGURE_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Body Background Color Accessor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FIGURE__BODY_BACKGROUND_COLOR_ACCESSOR = ABSTRACT_FIGURE_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Container</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FIGURE__CONTAINER = ABSTRACT_FIGURE_FEATURE_COUNT + 12;

	/**
	 * The number of structural features of the '<em>Class Figure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FIGURE_FEATURE_COUNT = ABSTRACT_FIGURE_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_FIGURE__NAME = ABSTRACT_FIGURE__NAME;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_FIGURE__LABEL = ABSTRACT_FIGURE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Class Figure</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_FIGURE__CLASS_FIGURE = ABSTRACT_FIGURE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>EAttribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_FIGURE__EATTRIBUTE = ABSTRACT_FIGURE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Attribute Figure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_FIGURE_FEATURE_COUNT = ABSTRACT_FIGURE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIGURE__NAME = ABSTRACT_FIGURE__NAME;

	/**
	 * The feature id for the '<em><b>Class Figure</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIGURE__CLASS_FIGURE = ABSTRACT_FIGURE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>EReference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIGURE__EREFERENCE = ABSTRACT_FIGURE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Target Arrow Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIGURE__TARGET_ARROW_TYPE = ABSTRACT_FIGURE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Source Arrow Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIGURE__SOURCE_ARROW_TYPE = ABSTRACT_FIGURE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Containment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIGURE__CONTAINMENT = ABSTRACT_FIGURE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Custom Target Arrow</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIGURE__CUSTOM_TARGET_ARROW = ABSTRACT_FIGURE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Custom Source Arrow</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIGURE__CUSTOM_SOURCE_ARROW = ABSTRACT_FIGURE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIGURE__COLOR = ABSTRACT_FIGURE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIGURE__STYLE = ABSTRACT_FIGURE_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Reference Figure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIGURE_FEATURE_COUNT = ABSTRACT_FIGURE_FEATURE_COUNT + 9;


	/**
	 * The meta object id for the '{@link org.emftools.emf2gv.graphdesc.Orientation <em>Orientation</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.emftools.emf2gv.graphdesc.Orientation
	 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getOrientation()
	 * @generated
	 */
	int ORIENTATION = 5;


	/**
	 * The meta object id for the '{@link org.emftools.emf2gv.graphdesc.ArrowType <em>Arrow Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.emftools.emf2gv.graphdesc.ArrowType
	 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getArrowType()
	 * @generated
	 */
	int ARROW_TYPE = 6;


	/**
	 * The meta object id for the '{@link org.emftools.emf2gv.graphdesc.ArrowStyle <em>Arrow Style</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.emftools.emf2gv.graphdesc.ArrowStyle
	 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getArrowStyle()
	 * @generated
	 */
	int ARROW_STYLE = 7;


	/**
	 * Returns the meta object for class '{@link org.emftools.emf2gv.graphdesc.ClassFigure <em>Class Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class Figure</em>'.
	 * @see org.emftools.emf2gv.graphdesc.ClassFigure
	 * @generated
	 */
	EClass getClassFigure();

	/**
	 * Returns the meta object for the reference '{@link org.emftools.emf2gv.graphdesc.ClassFigure#getEClass <em>EClass</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EClass</em>'.
	 * @see org.emftools.emf2gv.graphdesc.ClassFigure#getEClass()
	 * @see #getClassFigure()
	 * @generated
	 */
	EReference getClassFigure_EClass();

	/**
	 * Returns the meta object for the reference '{@link org.emftools.emf2gv.graphdesc.ClassFigure#getLabelEAttribute <em>Label EAttribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Label EAttribute</em>'.
	 * @see org.emftools.emf2gv.graphdesc.ClassFigure#getLabelEAttribute()
	 * @see #getClassFigure()
	 * @generated
	 */
	EReference getClassFigure_LabelEAttribute();

	/**
	 * Returns the meta object for the containment reference list '{@link org.emftools.emf2gv.graphdesc.ClassFigure#getAttributeFigures <em>Attribute Figures</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attribute Figures</em>'.
	 * @see org.emftools.emf2gv.graphdesc.ClassFigure#getAttributeFigures()
	 * @see #getClassFigure()
	 * @generated
	 */
	EReference getClassFigure_AttributeFigures();

	/**
	 * Returns the meta object for the reference '{@link org.emftools.emf2gv.graphdesc.ClassFigure#getEPackage <em>EPackage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EPackage</em>'.
	 * @see org.emftools.emf2gv.graphdesc.ClassFigure#getEPackage()
	 * @see #getClassFigure()
	 * @generated
	 */
	EReference getClassFigure_EPackage();

	/**
	 * Returns the meta object for the containment reference list '{@link org.emftools.emf2gv.graphdesc.ClassFigure#getReferenceFigures <em>Reference Figures</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Reference Figures</em>'.
	 * @see org.emftools.emf2gv.graphdesc.ClassFigure#getReferenceFigures()
	 * @see #getClassFigure()
	 * @generated
	 */
	EReference getClassFigure_ReferenceFigures();

	/**
	 * Returns the meta object for the container reference '{@link org.emftools.emf2gv.graphdesc.ClassFigure#getGvFigureDescription <em>Gv Figure Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Gv Figure Description</em>'.
	 * @see org.emftools.emf2gv.graphdesc.ClassFigure#getGvFigureDescription()
	 * @see #getClassFigure()
	 * @generated
	 */
	EReference getClassFigure_GvFigureDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.ClassFigure#getHeaderBackgroundColor <em>Header Background Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Header Background Color</em>'.
	 * @see org.emftools.emf2gv.graphdesc.ClassFigure#getHeaderBackgroundColor()
	 * @see #getClassFigure()
	 * @generated
	 */
	EAttribute getClassFigure_HeaderBackgroundColor();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.ClassFigure#getBodyBackgroundColor <em>Body Background Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Body Background Color</em>'.
	 * @see org.emftools.emf2gv.graphdesc.ClassFigure#getBodyBackgroundColor()
	 * @see #getClassFigure()
	 * @generated
	 */
	EAttribute getClassFigure_BodyBackgroundColor();

	/**
	 * Returns the meta object for the reference list '{@link org.emftools.emf2gv.graphdesc.ClassFigure#getNestedFiguresEReferences <em>Nested Figures EReferences</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Nested Figures EReferences</em>'.
	 * @see org.emftools.emf2gv.graphdesc.ClassFigure#getNestedFiguresEReferences()
	 * @see #getClassFigure()
	 * @generated
	 */
	EReference getClassFigure_NestedFiguresEReferences();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.ClassFigure#isDynamicAppearance <em>Dynamic Appearance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dynamic Appearance</em>'.
	 * @see org.emftools.emf2gv.graphdesc.ClassFigure#isDynamicAppearance()
	 * @see #getClassFigure()
	 * @generated
	 */
	EAttribute getClassFigure_DynamicAppearance();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.ClassFigure#getHeaderBackgroundColorAccessor <em>Header Background Color Accessor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Header Background Color Accessor</em>'.
	 * @see org.emftools.emf2gv.graphdesc.ClassFigure#getHeaderBackgroundColorAccessor()
	 * @see #getClassFigure()
	 * @generated
	 */
	EAttribute getClassFigure_HeaderBackgroundColorAccessor();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.ClassFigure#getBodyBackgroundColorAccessor <em>Body Background Color Accessor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Body Background Color Accessor</em>'.
	 * @see org.emftools.emf2gv.graphdesc.ClassFigure#getBodyBackgroundColorAccessor()
	 * @see #getClassFigure()
	 * @generated
	 */
	EAttribute getClassFigure_BodyBackgroundColorAccessor();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.ClassFigure#isContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Container</em>'.
	 * @see org.emftools.emf2gv.graphdesc.ClassFigure#isContainer()
	 * @see #getClassFigure()
	 * @generated
	 */
	EAttribute getClassFigure_Container();

	/**
	 * Returns the meta object for class '{@link org.emftools.emf2gv.graphdesc.AttributeFigure <em>Attribute Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute Figure</em>'.
	 * @see org.emftools.emf2gv.graphdesc.AttributeFigure
	 * @generated
	 */
	EClass getAttributeFigure();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.AttributeFigure#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see org.emftools.emf2gv.graphdesc.AttributeFigure#getLabel()
	 * @see #getAttributeFigure()
	 * @generated
	 */
	EAttribute getAttributeFigure_Label();

	/**
	 * Returns the meta object for the container reference '{@link org.emftools.emf2gv.graphdesc.AttributeFigure#getClassFigure <em>Class Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Class Figure</em>'.
	 * @see org.emftools.emf2gv.graphdesc.AttributeFigure#getClassFigure()
	 * @see #getAttributeFigure()
	 * @generated
	 */
	EReference getAttributeFigure_ClassFigure();

	/**
	 * Returns the meta object for the reference '{@link org.emftools.emf2gv.graphdesc.AttributeFigure#getEAttribute <em>EAttribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EAttribute</em>'.
	 * @see org.emftools.emf2gv.graphdesc.AttributeFigure#getEAttribute()
	 * @see #getAttributeFigure()
	 * @generated
	 */
	EReference getAttributeFigure_EAttribute();

	/**
	 * Returns the meta object for class '{@link org.emftools.emf2gv.graphdesc.ReferenceFigure <em>Reference Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference Figure</em>'.
	 * @see org.emftools.emf2gv.graphdesc.ReferenceFigure
	 * @generated
	 */
	EClass getReferenceFigure();

	/**
	 * Returns the meta object for the reference '{@link org.emftools.emf2gv.graphdesc.ReferenceFigure#getEReference <em>EReference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EReference</em>'.
	 * @see org.emftools.emf2gv.graphdesc.ReferenceFigure#getEReference()
	 * @see #getReferenceFigure()
	 * @generated
	 */
	EReference getReferenceFigure_EReference();

	/**
	 * Returns the meta object for the container reference '{@link org.emftools.emf2gv.graphdesc.ReferenceFigure#getClassFigure <em>Class Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Class Figure</em>'.
	 * @see org.emftools.emf2gv.graphdesc.ReferenceFigure#getClassFigure()
	 * @see #getReferenceFigure()
	 * @generated
	 */
	EReference getReferenceFigure_ClassFigure();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.ReferenceFigure#getTargetArrowType <em>Target Arrow Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Arrow Type</em>'.
	 * @see org.emftools.emf2gv.graphdesc.ReferenceFigure#getTargetArrowType()
	 * @see #getReferenceFigure()
	 * @generated
	 */
	EAttribute getReferenceFigure_TargetArrowType();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.ReferenceFigure#getSourceArrowType <em>Source Arrow Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Arrow Type</em>'.
	 * @see org.emftools.emf2gv.graphdesc.ReferenceFigure#getSourceArrowType()
	 * @see #getReferenceFigure()
	 * @generated
	 */
	EAttribute getReferenceFigure_SourceArrowType();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.ReferenceFigure#isContainment <em>Containment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Containment</em>'.
	 * @see org.emftools.emf2gv.graphdesc.ReferenceFigure#isContainment()
	 * @see #getReferenceFigure()
	 * @generated
	 */
	EAttribute getReferenceFigure_Containment();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.ReferenceFigure#getCustomTargetArrow <em>Custom Target Arrow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Custom Target Arrow</em>'.
	 * @see org.emftools.emf2gv.graphdesc.ReferenceFigure#getCustomTargetArrow()
	 * @see #getReferenceFigure()
	 * @generated
	 */
	EAttribute getReferenceFigure_CustomTargetArrow();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.ReferenceFigure#getCustomSourceArrow <em>Custom Source Arrow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Custom Source Arrow</em>'.
	 * @see org.emftools.emf2gv.graphdesc.ReferenceFigure#getCustomSourceArrow()
	 * @see #getReferenceFigure()
	 * @generated
	 */
	EAttribute getReferenceFigure_CustomSourceArrow();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.ReferenceFigure#getColor <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Color</em>'.
	 * @see org.emftools.emf2gv.graphdesc.ReferenceFigure#getColor()
	 * @see #getReferenceFigure()
	 * @generated
	 */
	EAttribute getReferenceFigure_Color();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.ReferenceFigure#getStyle <em>Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Style</em>'.
	 * @see org.emftools.emf2gv.graphdesc.ReferenceFigure#getStyle()
	 * @see #getReferenceFigure()
	 * @generated
	 */
	EAttribute getReferenceFigure_Style();

	/**
	 * Returns the meta object for class '{@link org.emftools.emf2gv.graphdesc.AbstractFigure <em>Abstract Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Figure</em>'.
	 * @see org.emftools.emf2gv.graphdesc.AbstractFigure
	 * @generated
	 */
	EClass getAbstractFigure();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.AbstractFigure#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.emftools.emf2gv.graphdesc.AbstractFigure#getName()
	 * @see #getAbstractFigure()
	 * @generated
	 */
	EAttribute getAbstractFigure_Name();

	/**
	 * Returns the meta object for class '{@link org.emftools.emf2gv.graphdesc.GVFigureDescription <em>GV Figure Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>GV Figure Description</em>'.
	 * @see org.emftools.emf2gv.graphdesc.GVFigureDescription
	 * @generated
	 */
	EClass getGVFigureDescription();

	/**
	 * Returns the meta object for the containment reference list '{@link org.emftools.emf2gv.graphdesc.GVFigureDescription#getClassFigures <em>Class Figures</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Class Figures</em>'.
	 * @see org.emftools.emf2gv.graphdesc.GVFigureDescription#getClassFigures()
	 * @see #getGVFigureDescription()
	 * @generated
	 */
	EReference getGVFigureDescription_ClassFigures();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.GVFigureDescription#getOrientation <em>Orientation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Orientation</em>'.
	 * @see org.emftools.emf2gv.graphdesc.GVFigureDescription#getOrientation()
	 * @see #getGVFigureDescription()
	 * @generated
	 */
	EAttribute getGVFigureDescription_Orientation();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.GVFigureDescription#isAlignSameEClasses <em>Align Same EClasses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Align Same EClasses</em>'.
	 * @see org.emftools.emf2gv.graphdesc.GVFigureDescription#isAlignSameEClasses()
	 * @see #getGVFigureDescription()
	 * @generated
	 */
	EAttribute getGVFigureDescription_AlignSameEClasses();

	/**
	 * Returns the meta object for enum '{@link org.emftools.emf2gv.graphdesc.Orientation <em>Orientation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Orientation</em>'.
	 * @see org.emftools.emf2gv.graphdesc.Orientation
	 * @generated
	 */
	EEnum getOrientation();

	/**
	 * Returns the meta object for enum '{@link org.emftools.emf2gv.graphdesc.ArrowType <em>Arrow Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Arrow Type</em>'.
	 * @see org.emftools.emf2gv.graphdesc.ArrowType
	 * @generated
	 */
	EEnum getArrowType();

	/**
	 * Returns the meta object for enum '{@link org.emftools.emf2gv.graphdesc.ArrowStyle <em>Arrow Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Arrow Style</em>'.
	 * @see org.emftools.emf2gv.graphdesc.ArrowStyle
	 * @generated
	 */
	EEnum getArrowStyle();

	/**
	 * Returns the meta object for the reference list '{@link org.emftools.emf2gv.graphdesc.GVFigureDescription#getEPackages <em>EPackages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>EPackages</em>'.
	 * @see org.emftools.emf2gv.graphdesc.GVFigureDescription#getEPackages()
	 * @see #getGVFigureDescription()
	 * @generated
	 */
	EReference getGVFigureDescription_EPackages();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	GraphdescFactory getGraphdescFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.emftools.emf2gv.graphdesc.impl.ClassFigureImpl <em>Class Figure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.emftools.emf2gv.graphdesc.impl.ClassFigureImpl
		 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getClassFigure()
		 * @generated
		 */
		EClass CLASS_FIGURE = eINSTANCE.getClassFigure();

		/**
		 * The meta object literal for the '<em><b>EClass</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_FIGURE__ECLASS = eINSTANCE.getClassFigure_EClass();

		/**
		 * The meta object literal for the '<em><b>Label EAttribute</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_FIGURE__LABEL_EATTRIBUTE = eINSTANCE.getClassFigure_LabelEAttribute();

		/**
		 * The meta object literal for the '<em><b>Attribute Figures</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_FIGURE__ATTRIBUTE_FIGURES = eINSTANCE.getClassFigure_AttributeFigures();

		/**
		 * The meta object literal for the '<em><b>EPackage</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_FIGURE__EPACKAGE = eINSTANCE.getClassFigure_EPackage();

		/**
		 * The meta object literal for the '<em><b>Reference Figures</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_FIGURE__REFERENCE_FIGURES = eINSTANCE.getClassFigure_ReferenceFigures();

		/**
		 * The meta object literal for the '<em><b>Gv Figure Description</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_FIGURE__GV_FIGURE_DESCRIPTION = eINSTANCE.getClassFigure_GvFigureDescription();

		/**
		 * The meta object literal for the '<em><b>Header Background Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FIGURE__HEADER_BACKGROUND_COLOR = eINSTANCE.getClassFigure_HeaderBackgroundColor();

		/**
		 * The meta object literal for the '<em><b>Body Background Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FIGURE__BODY_BACKGROUND_COLOR = eINSTANCE.getClassFigure_BodyBackgroundColor();

		/**
		 * The meta object literal for the '<em><b>Nested Figures EReferences</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_FIGURE__NESTED_FIGURES_EREFERENCES = eINSTANCE.getClassFigure_NestedFiguresEReferences();

		/**
		 * The meta object literal for the '<em><b>Dynamic Appearance</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FIGURE__DYNAMIC_APPEARANCE = eINSTANCE.getClassFigure_DynamicAppearance();

		/**
		 * The meta object literal for the '<em><b>Header Background Color Accessor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FIGURE__HEADER_BACKGROUND_COLOR_ACCESSOR = eINSTANCE.getClassFigure_HeaderBackgroundColorAccessor();

		/**
		 * The meta object literal for the '<em><b>Body Background Color Accessor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FIGURE__BODY_BACKGROUND_COLOR_ACCESSOR = eINSTANCE.getClassFigure_BodyBackgroundColorAccessor();

		/**
		 * The meta object literal for the '<em><b>Container</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FIGURE__CONTAINER = eINSTANCE.getClassFigure_Container();

		/**
		 * The meta object literal for the '{@link org.emftools.emf2gv.graphdesc.impl.AttributeFigureImpl <em>Attribute Figure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.emftools.emf2gv.graphdesc.impl.AttributeFigureImpl
		 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getAttributeFigure()
		 * @generated
		 */
		EClass ATTRIBUTE_FIGURE = eINSTANCE.getAttributeFigure();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE_FIGURE__LABEL = eINSTANCE.getAttributeFigure_Label();

		/**
		 * The meta object literal for the '<em><b>Class Figure</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTRIBUTE_FIGURE__CLASS_FIGURE = eINSTANCE.getAttributeFigure_ClassFigure();

		/**
		 * The meta object literal for the '<em><b>EAttribute</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTRIBUTE_FIGURE__EATTRIBUTE = eINSTANCE.getAttributeFigure_EAttribute();

		/**
		 * The meta object literal for the '{@link org.emftools.emf2gv.graphdesc.impl.ReferenceFigureImpl <em>Reference Figure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.emftools.emf2gv.graphdesc.impl.ReferenceFigureImpl
		 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getReferenceFigure()
		 * @generated
		 */
		EClass REFERENCE_FIGURE = eINSTANCE.getReferenceFigure();

		/**
		 * The meta object literal for the '<em><b>EReference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE_FIGURE__EREFERENCE = eINSTANCE.getReferenceFigure_EReference();

		/**
		 * The meta object literal for the '<em><b>Class Figure</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE_FIGURE__CLASS_FIGURE = eINSTANCE.getReferenceFigure_ClassFigure();

		/**
		 * The meta object literal for the '<em><b>Target Arrow Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_FIGURE__TARGET_ARROW_TYPE = eINSTANCE.getReferenceFigure_TargetArrowType();

		/**
		 * The meta object literal for the '<em><b>Source Arrow Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_FIGURE__SOURCE_ARROW_TYPE = eINSTANCE.getReferenceFigure_SourceArrowType();

		/**
		 * The meta object literal for the '<em><b>Containment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_FIGURE__CONTAINMENT = eINSTANCE.getReferenceFigure_Containment();

		/**
		 * The meta object literal for the '<em><b>Custom Target Arrow</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_FIGURE__CUSTOM_TARGET_ARROW = eINSTANCE.getReferenceFigure_CustomTargetArrow();

		/**
		 * The meta object literal for the '<em><b>Custom Source Arrow</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_FIGURE__CUSTOM_SOURCE_ARROW = eINSTANCE.getReferenceFigure_CustomSourceArrow();

		/**
		 * The meta object literal for the '<em><b>Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_FIGURE__COLOR = eINSTANCE.getReferenceFigure_Color();

		/**
		 * The meta object literal for the '<em><b>Style</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_FIGURE__STYLE = eINSTANCE.getReferenceFigure_Style();

		/**
		 * The meta object literal for the '{@link org.emftools.emf2gv.graphdesc.impl.AbstractFigureImpl <em>Abstract Figure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.emftools.emf2gv.graphdesc.impl.AbstractFigureImpl
		 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getAbstractFigure()
		 * @generated
		 */
		EClass ABSTRACT_FIGURE = eINSTANCE.getAbstractFigure();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_FIGURE__NAME = eINSTANCE.getAbstractFigure_Name();

		/**
		 * The meta object literal for the '{@link org.emftools.emf2gv.graphdesc.impl.GVFigureDescriptionImpl <em>GV Figure Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.emftools.emf2gv.graphdesc.impl.GVFigureDescriptionImpl
		 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getGVFigureDescription()
		 * @generated
		 */
		EClass GV_FIGURE_DESCRIPTION = eINSTANCE.getGVFigureDescription();

		/**
		 * The meta object literal for the '<em><b>Class Figures</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GV_FIGURE_DESCRIPTION__CLASS_FIGURES = eINSTANCE.getGVFigureDescription_ClassFigures();

		/**
		 * The meta object literal for the '<em><b>Orientation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GV_FIGURE_DESCRIPTION__ORIENTATION = eINSTANCE.getGVFigureDescription_Orientation();

		/**
		 * The meta object literal for the '<em><b>Align Same EClasses</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GV_FIGURE_DESCRIPTION__ALIGN_SAME_ECLASSES = eINSTANCE.getGVFigureDescription_AlignSameEClasses();

		/**
		 * The meta object literal for the '{@link org.emftools.emf2gv.graphdesc.Orientation <em>Orientation</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.emftools.emf2gv.graphdesc.Orientation
		 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getOrientation()
		 * @generated
		 */
		EEnum ORIENTATION = eINSTANCE.getOrientation();

		/**
		 * The meta object literal for the '{@link org.emftools.emf2gv.graphdesc.ArrowType <em>Arrow Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.emftools.emf2gv.graphdesc.ArrowType
		 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getArrowType()
		 * @generated
		 */
		EEnum ARROW_TYPE = eINSTANCE.getArrowType();

		/**
		 * The meta object literal for the '{@link org.emftools.emf2gv.graphdesc.ArrowStyle <em>Arrow Style</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.emftools.emf2gv.graphdesc.ArrowStyle
		 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getArrowStyle()
		 * @generated
		 */
		EEnum ARROW_STYLE = eINSTANCE.getArrowStyle();

		/**
		 * The meta object literal for the '<em><b>EPackages</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GV_FIGURE_DESCRIPTION__EPACKAGES = eINSTANCE.getGVFigureDescription_EPackages();

	}

} //GraphdescPackage
