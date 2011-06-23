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
package org.emftools.emf2gv.graphdesc;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
	String eNS_URI = "http://org.emftools.emf2gv.graphdesc/1.1.0";

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
	int CLASS_FIGURE = 2;

	/**
	 * The meta object id for the '{@link org.emftools.emf2gv.graphdesc.impl.AttributeFigureImpl <em>Attribute Figure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.emftools.emf2gv.graphdesc.impl.AttributeFigureImpl
	 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getAttributeFigure()
	 * @generated
	 */
	int ATTRIBUTE_FIGURE = 4;

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
	 * The feature id for the '<em><b>Filters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GV_FIGURE_DESCRIPTION__FILTERS = 0;

	/**
	 * The feature id for the '<em><b>EPackages</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GV_FIGURE_DESCRIPTION__EPACKAGES = 1;

	/**
	 * The feature id for the '<em><b>Class Figures</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GV_FIGURE_DESCRIPTION__CLASS_FIGURES = 2;

	/**
	 * The feature id for the '<em><b>Orientation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GV_FIGURE_DESCRIPTION__ORIENTATION = 3;

	/**
	 * The feature id for the '<em><b>Align Same EClasses</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GV_FIGURE_DESCRIPTION__ALIGN_SAME_ECLASSES = 4;

	/**
	 * The number of structural features of the '<em>GV Figure Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GV_FIGURE_DESCRIPTION_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.emftools.emf2gv.graphdesc.impl.AbstractFigureImpl <em>Abstract Figure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.emftools.emf2gv.graphdesc.impl.AbstractFigureImpl
	 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getAbstractFigure()
	 * @generated
	 */
	int ABSTRACT_FIGURE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FIGURE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Dynamic Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FIGURE__DYNAMIC_PROPERTIES = 1;

	/**
	 * The number of structural features of the '<em>Abstract Figure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FIGURE_FEATURE_COUNT = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FIGURE__NAME = ABSTRACT_FIGURE__NAME;

	/**
	 * The feature id for the '<em><b>Dynamic Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FIGURE__DYNAMIC_PROPERTIES = ABSTRACT_FIGURE__DYNAMIC_PROPERTIES;

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
	 * The feature id for the '<em><b>Label Style</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FIGURE__LABEL_STYLE = ABSTRACT_FIGURE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Attribute Figures</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FIGURE__ATTRIBUTE_FIGURES = ABSTRACT_FIGURE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Reference Figures</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FIGURE__REFERENCE_FIGURES = ABSTRACT_FIGURE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Gv Figure Description</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FIGURE__GV_FIGURE_DESCRIPTION = ABSTRACT_FIGURE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Header Background Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FIGURE__HEADER_BACKGROUND_COLOR = ABSTRACT_FIGURE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Body Background Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FIGURE__BODY_BACKGROUND_COLOR = ABSTRACT_FIGURE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Nested Figures EReferences</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FIGURE__NESTED_FIGURES_EREFERENCES = ABSTRACT_FIGURE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Container</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FIGURE__CONTAINER = ABSTRACT_FIGURE_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Class Figure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FIGURE_FEATURE_COUNT = ABSTRACT_FIGURE_FEATURE_COUNT + 11;

	/**
	 * The meta object id for the '{@link org.emftools.emf2gv.graphdesc.impl.RichReferenceFigureImpl <em>Rich Reference Figure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.emftools.emf2gv.graphdesc.impl.RichReferenceFigureImpl
	 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getRichReferenceFigure()
	 * @generated
	 */
	int RICH_REFERENCE_FIGURE = 8;

	/**
	 * The meta object id for the '{@link org.emftools.emf2gv.graphdesc.impl.AbstractReferenceFigureImpl <em>Abstract Reference Figure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.emftools.emf2gv.graphdesc.impl.AbstractReferenceFigureImpl
	 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getAbstractReferenceFigure()
	 * @generated
	 */
	int ABSTRACT_REFERENCE_FIGURE = 6;

	/**
	 * The meta object id for the '{@link org.emftools.emf2gv.graphdesc.impl.ReferenceFigureImpl <em>Reference Figure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.emftools.emf2gv.graphdesc.impl.ReferenceFigureImpl
	 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getReferenceFigure()
	 * @generated
	 */
	int REFERENCE_FIGURE = 7;

	/**
	 * The meta object id for the '{@link org.emftools.emf2gv.graphdesc.impl.AbstractAttributeFigureImpl <em>Abstract Attribute Figure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.emftools.emf2gv.graphdesc.impl.AbstractAttributeFigureImpl
	 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getAbstractAttributeFigure()
	 * @generated
	 */
	int ABSTRACT_ATTRIBUTE_FIGURE = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ATTRIBUTE_FIGURE__NAME = ABSTRACT_FIGURE__NAME;

	/**
	 * The feature id for the '<em><b>Dynamic Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ATTRIBUTE_FIGURE__DYNAMIC_PROPERTIES = ABSTRACT_FIGURE__DYNAMIC_PROPERTIES;

	/**
	 * The feature id for the '<em><b>Class Figure</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ATTRIBUTE_FIGURE__CLASS_FIGURE = ABSTRACT_FIGURE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Label Style</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ATTRIBUTE_FIGURE__LABEL_STYLE = ABSTRACT_FIGURE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Abstract Attribute Figure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ATTRIBUTE_FIGURE_FEATURE_COUNT = ABSTRACT_FIGURE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_FIGURE__NAME = ABSTRACT_ATTRIBUTE_FIGURE__NAME;

	/**
	 * The feature id for the '<em><b>Dynamic Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_FIGURE__DYNAMIC_PROPERTIES = ABSTRACT_ATTRIBUTE_FIGURE__DYNAMIC_PROPERTIES;

	/**
	 * The feature id for the '<em><b>Class Figure</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_FIGURE__CLASS_FIGURE = ABSTRACT_ATTRIBUTE_FIGURE__CLASS_FIGURE;

	/**
	 * The feature id for the '<em><b>Label Style</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_FIGURE__LABEL_STYLE = ABSTRACT_ATTRIBUTE_FIGURE__LABEL_STYLE;

	/**
	 * The feature id for the '<em><b>EAttribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_FIGURE__EATTRIBUTE = ABSTRACT_ATTRIBUTE_FIGURE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Attribute Figure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_FIGURE_FEATURE_COUNT = ABSTRACT_ATTRIBUTE_FIGURE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.emftools.emf2gv.graphdesc.impl.RichAttributeFigureImpl <em>Rich Attribute Figure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.emftools.emf2gv.graphdesc.impl.RichAttributeFigureImpl
	 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getRichAttributeFigure()
	 * @generated
	 */
	int RICH_ATTRIBUTE_FIGURE = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RICH_ATTRIBUTE_FIGURE__NAME = ABSTRACT_ATTRIBUTE_FIGURE__NAME;

	/**
	 * The feature id for the '<em><b>Dynamic Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RICH_ATTRIBUTE_FIGURE__DYNAMIC_PROPERTIES = ABSTRACT_ATTRIBUTE_FIGURE__DYNAMIC_PROPERTIES;

	/**
	 * The feature id for the '<em><b>Class Figure</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RICH_ATTRIBUTE_FIGURE__CLASS_FIGURE = ABSTRACT_ATTRIBUTE_FIGURE__CLASS_FIGURE;

	/**
	 * The feature id for the '<em><b>Label Style</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RICH_ATTRIBUTE_FIGURE__LABEL_STYLE = ABSTRACT_ATTRIBUTE_FIGURE__LABEL_STYLE;

	/**
	 * The feature id for the '<em><b>EReference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RICH_ATTRIBUTE_FIGURE__EREFERENCE = ABSTRACT_ATTRIBUTE_FIGURE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Label Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RICH_ATTRIBUTE_FIGURE__LABEL_EXPRESSION = ABSTRACT_ATTRIBUTE_FIGURE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Rich Attribute Figure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RICH_ATTRIBUTE_FIGURE_FEATURE_COUNT = ABSTRACT_ATTRIBUTE_FIGURE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REFERENCE_FIGURE__NAME = ABSTRACT_FIGURE__NAME;

	/**
	 * The feature id for the '<em><b>Dynamic Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REFERENCE_FIGURE__DYNAMIC_PROPERTIES = ABSTRACT_FIGURE__DYNAMIC_PROPERTIES;

	/**
	 * The feature id for the '<em><b>Class Figure</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REFERENCE_FIGURE__CLASS_FIGURE = ABSTRACT_FIGURE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>EReference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REFERENCE_FIGURE__EREFERENCE = ABSTRACT_FIGURE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Target Arrow Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REFERENCE_FIGURE__TARGET_ARROW_TYPE = ABSTRACT_FIGURE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Source Arrow Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REFERENCE_FIGURE__SOURCE_ARROW_TYPE = ABSTRACT_FIGURE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Containment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REFERENCE_FIGURE__CONTAINMENT = ABSTRACT_FIGURE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Custom Target Arrow</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REFERENCE_FIGURE__CUSTOM_TARGET_ARROW = ABSTRACT_FIGURE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Custom Source Arrow</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REFERENCE_FIGURE__CUSTOM_SOURCE_ARROW = ABSTRACT_FIGURE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REFERENCE_FIGURE__COLOR = ABSTRACT_FIGURE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REFERENCE_FIGURE__STYLE = ABSTRACT_FIGURE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Target EType</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REFERENCE_FIGURE__TARGET_ETYPE = ABSTRACT_FIGURE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Minimum Edge Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REFERENCE_FIGURE__MINIMUM_EDGE_LENGTH = ABSTRACT_FIGURE_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Abstract Reference Figure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REFERENCE_FIGURE_FEATURE_COUNT = ABSTRACT_FIGURE_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIGURE__NAME = ABSTRACT_REFERENCE_FIGURE__NAME;

	/**
	 * The feature id for the '<em><b>Dynamic Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIGURE__DYNAMIC_PROPERTIES = ABSTRACT_REFERENCE_FIGURE__DYNAMIC_PROPERTIES;

	/**
	 * The feature id for the '<em><b>Class Figure</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIGURE__CLASS_FIGURE = ABSTRACT_REFERENCE_FIGURE__CLASS_FIGURE;

	/**
	 * The feature id for the '<em><b>EReference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIGURE__EREFERENCE = ABSTRACT_REFERENCE_FIGURE__EREFERENCE;

	/**
	 * The feature id for the '<em><b>Target Arrow Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIGURE__TARGET_ARROW_TYPE = ABSTRACT_REFERENCE_FIGURE__TARGET_ARROW_TYPE;

	/**
	 * The feature id for the '<em><b>Source Arrow Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIGURE__SOURCE_ARROW_TYPE = ABSTRACT_REFERENCE_FIGURE__SOURCE_ARROW_TYPE;

	/**
	 * The feature id for the '<em><b>Containment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIGURE__CONTAINMENT = ABSTRACT_REFERENCE_FIGURE__CONTAINMENT;

	/**
	 * The feature id for the '<em><b>Custom Target Arrow</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIGURE__CUSTOM_TARGET_ARROW = ABSTRACT_REFERENCE_FIGURE__CUSTOM_TARGET_ARROW;

	/**
	 * The feature id for the '<em><b>Custom Source Arrow</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIGURE__CUSTOM_SOURCE_ARROW = ABSTRACT_REFERENCE_FIGURE__CUSTOM_SOURCE_ARROW;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIGURE__COLOR = ABSTRACT_REFERENCE_FIGURE__COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIGURE__STYLE = ABSTRACT_REFERENCE_FIGURE__STYLE;

	/**
	 * The feature id for the '<em><b>Target EType</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIGURE__TARGET_ETYPE = ABSTRACT_REFERENCE_FIGURE__TARGET_ETYPE;

	/**
	 * The feature id for the '<em><b>Minimum Edge Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIGURE__MINIMUM_EDGE_LENGTH = ABSTRACT_REFERENCE_FIGURE__MINIMUM_EDGE_LENGTH;

	/**
	 * The number of structural features of the '<em>Reference Figure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIGURE_FEATURE_COUNT = ABSTRACT_REFERENCE_FIGURE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RICH_REFERENCE_FIGURE__NAME = ABSTRACT_REFERENCE_FIGURE__NAME;

	/**
	 * The feature id for the '<em><b>Dynamic Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RICH_REFERENCE_FIGURE__DYNAMIC_PROPERTIES = ABSTRACT_REFERENCE_FIGURE__DYNAMIC_PROPERTIES;

	/**
	 * The feature id for the '<em><b>Class Figure</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RICH_REFERENCE_FIGURE__CLASS_FIGURE = ABSTRACT_REFERENCE_FIGURE__CLASS_FIGURE;

	/**
	 * The feature id for the '<em><b>EReference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RICH_REFERENCE_FIGURE__EREFERENCE = ABSTRACT_REFERENCE_FIGURE__EREFERENCE;

	/**
	 * The feature id for the '<em><b>Target Arrow Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RICH_REFERENCE_FIGURE__TARGET_ARROW_TYPE = ABSTRACT_REFERENCE_FIGURE__TARGET_ARROW_TYPE;

	/**
	 * The feature id for the '<em><b>Source Arrow Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RICH_REFERENCE_FIGURE__SOURCE_ARROW_TYPE = ABSTRACT_REFERENCE_FIGURE__SOURCE_ARROW_TYPE;

	/**
	 * The feature id for the '<em><b>Containment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RICH_REFERENCE_FIGURE__CONTAINMENT = ABSTRACT_REFERENCE_FIGURE__CONTAINMENT;

	/**
	 * The feature id for the '<em><b>Custom Target Arrow</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RICH_REFERENCE_FIGURE__CUSTOM_TARGET_ARROW = ABSTRACT_REFERENCE_FIGURE__CUSTOM_TARGET_ARROW;

	/**
	 * The feature id for the '<em><b>Custom Source Arrow</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RICH_REFERENCE_FIGURE__CUSTOM_SOURCE_ARROW = ABSTRACT_REFERENCE_FIGURE__CUSTOM_SOURCE_ARROW;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RICH_REFERENCE_FIGURE__COLOR = ABSTRACT_REFERENCE_FIGURE__COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RICH_REFERENCE_FIGURE__STYLE = ABSTRACT_REFERENCE_FIGURE__STYLE;

	/**
	 * The feature id for the '<em><b>Target EType</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RICH_REFERENCE_FIGURE__TARGET_ETYPE = ABSTRACT_REFERENCE_FIGURE__TARGET_ETYPE;

	/**
	 * The feature id for the '<em><b>Minimum Edge Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RICH_REFERENCE_FIGURE__MINIMUM_EDGE_LENGTH = ABSTRACT_REFERENCE_FIGURE__MINIMUM_EDGE_LENGTH;

	/**
	 * The feature id for the '<em><b>Target EReference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RICH_REFERENCE_FIGURE__TARGET_EREFERENCE = ABSTRACT_REFERENCE_FIGURE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source Label Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RICH_REFERENCE_FIGURE__SOURCE_LABEL_EXPRESSION = ABSTRACT_REFERENCE_FIGURE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Source Label Style</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RICH_REFERENCE_FIGURE__SOURCE_LABEL_STYLE = ABSTRACT_REFERENCE_FIGURE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Standard Label Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RICH_REFERENCE_FIGURE__STANDARD_LABEL_EXPRESSION = ABSTRACT_REFERENCE_FIGURE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Standard Label Style</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RICH_REFERENCE_FIGURE__STANDARD_LABEL_STYLE = ABSTRACT_REFERENCE_FIGURE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Target Label Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RICH_REFERENCE_FIGURE__TARGET_LABEL_EXPRESSION = ABSTRACT_REFERENCE_FIGURE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Target Label Style</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RICH_REFERENCE_FIGURE__TARGET_LABEL_STYLE = ABSTRACT_REFERENCE_FIGURE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Label Distance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RICH_REFERENCE_FIGURE__LABEL_DISTANCE = ABSTRACT_REFERENCE_FIGURE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Label Angle</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RICH_REFERENCE_FIGURE__LABEL_ANGLE = ABSTRACT_REFERENCE_FIGURE_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Rich Reference Figure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RICH_REFERENCE_FIGURE_FEATURE_COUNT = ABSTRACT_REFERENCE_FIGURE_FEATURE_COUNT + 9;

	/**
	 * The meta object id for the '{@link org.emftools.emf2gv.graphdesc.impl.DynamicPropertyOverriderImpl <em>Dynamic Property Overrider</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.emftools.emf2gv.graphdesc.impl.DynamicPropertyOverriderImpl
	 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getDynamicPropertyOverrider()
	 * @generated
	 */
	int DYNAMIC_PROPERTY_OVERRIDER = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_PROPERTY_OVERRIDER__NAME = 0;

	/**
	 * The feature id for the '<em><b>Property To Override</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_PROPERTY_OVERRIDER__PROPERTY_TO_OVERRIDE = 1;

	/**
	 * The feature id for the '<em><b>Overriding Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_PROPERTY_OVERRIDER__OVERRIDING_EXPRESSION = 2;

	/**
	 * The feature id for the '<em><b>Figure</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_PROPERTY_OVERRIDER__FIGURE = 3;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_PROPERTY_OVERRIDER__ENABLED = 4;

	/**
	 * The number of structural features of the '<em>Dynamic Property Overrider</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_PROPERTY_OVERRIDER_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.emftools.emf2gv.graphdesc.impl.FilterImpl <em>Filter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.emftools.emf2gv.graphdesc.impl.FilterImpl
	 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getFilter()
	 * @generated
	 */
	int FILTER = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER__NAME = 0;

	/**
	 * The feature id for the '<em><b>Filtered Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER__FILTERED_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Filter Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER__FILTER_EXPRESSION = 2;

	/**
	 * The feature id for the '<em><b>Figure Description</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER__FIGURE_DESCRIPTION = 3;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER__ENABLED = 4;

	/**
	 * The number of structural features of the '<em>Filter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.emftools.emf2gv.graphdesc.Orientation <em>Orientation</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.emftools.emf2gv.graphdesc.Orientation
	 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getOrientation()
	 * @generated
	 */
	int ORIENTATION = 11;


	/**
	 * The meta object id for the '{@link org.emftools.emf2gv.graphdesc.ArrowType <em>Arrow Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.emftools.emf2gv.graphdesc.ArrowType
	 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getArrowType()
	 * @generated
	 */
	int ARROW_TYPE = 12;


	/**
	 * The meta object id for the '{@link org.emftools.emf2gv.graphdesc.EdgeStyle <em>Edge Style</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.emftools.emf2gv.graphdesc.EdgeStyle
	 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getEdgeStyle()
	 * @generated
	 */
	int EDGE_STYLE = 13;

	/**
	 * The meta object id for the '{@link org.emftools.emf2gv.graphdesc.FontStyle <em>Font Style</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.emftools.emf2gv.graphdesc.FontStyle
	 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getFontStyle()
	 * @generated
	 */
	int FONT_STYLE = 14;

	/**
	 * The meta object id for the '<em>Color</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.awt.Color
	 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getColor()
	 * @generated
	 */
	int COLOR = 15;


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
	 * Returns the meta object for the attribute list '{@link org.emftools.emf2gv.graphdesc.ClassFigure#getLabelStyle <em>Label Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Label Style</em>'.
	 * @see org.emftools.emf2gv.graphdesc.ClassFigure#getLabelStyle()
	 * @see #getClassFigure()
	 * @generated
	 */
	EAttribute getClassFigure_LabelStyle();

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
	 * Returns the meta object for class '{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure <em>Rich Reference Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rich Reference Figure</em>'.
	 * @see org.emftools.emf2gv.graphdesc.RichReferenceFigure
	 * @generated
	 */
	EClass getRichReferenceFigure();

	/**
	 * Returns the meta object for the reference '{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure#getTargetEReference <em>Target EReference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target EReference</em>'.
	 * @see org.emftools.emf2gv.graphdesc.RichReferenceFigure#getTargetEReference()
	 * @see #getRichReferenceFigure()
	 * @generated
	 */
	EReference getRichReferenceFigure_TargetEReference();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure#getSourceLabelExpression <em>Source Label Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Label Expression</em>'.
	 * @see org.emftools.emf2gv.graphdesc.RichReferenceFigure#getSourceLabelExpression()
	 * @see #getRichReferenceFigure()
	 * @generated
	 */
	EAttribute getRichReferenceFigure_SourceLabelExpression();

	/**
	 * Returns the meta object for the attribute list '{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure#getSourceLabelStyle <em>Source Label Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Source Label Style</em>'.
	 * @see org.emftools.emf2gv.graphdesc.RichReferenceFigure#getSourceLabelStyle()
	 * @see #getRichReferenceFigure()
	 * @generated
	 */
	EAttribute getRichReferenceFigure_SourceLabelStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure#getStandardLabelExpression <em>Standard Label Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Standard Label Expression</em>'.
	 * @see org.emftools.emf2gv.graphdesc.RichReferenceFigure#getStandardLabelExpression()
	 * @see #getRichReferenceFigure()
	 * @generated
	 */
	EAttribute getRichReferenceFigure_StandardLabelExpression();

	/**
	 * Returns the meta object for the attribute list '{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure#getStandardLabelStyle <em>Standard Label Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Standard Label Style</em>'.
	 * @see org.emftools.emf2gv.graphdesc.RichReferenceFigure#getStandardLabelStyle()
	 * @see #getRichReferenceFigure()
	 * @generated
	 */
	EAttribute getRichReferenceFigure_StandardLabelStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure#getTargetLabelExpression <em>Target Label Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Label Expression</em>'.
	 * @see org.emftools.emf2gv.graphdesc.RichReferenceFigure#getTargetLabelExpression()
	 * @see #getRichReferenceFigure()
	 * @generated
	 */
	EAttribute getRichReferenceFigure_TargetLabelExpression();

	/**
	 * Returns the meta object for the attribute list '{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure#getTargetLabelStyle <em>Target Label Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Target Label Style</em>'.
	 * @see org.emftools.emf2gv.graphdesc.RichReferenceFigure#getTargetLabelStyle()
	 * @see #getRichReferenceFigure()
	 * @generated
	 */
	EAttribute getRichReferenceFigure_TargetLabelStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure#getLabelDistance <em>Label Distance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label Distance</em>'.
	 * @see org.emftools.emf2gv.graphdesc.RichReferenceFigure#getLabelDistance()
	 * @see #getRichReferenceFigure()
	 * @generated
	 */
	EAttribute getRichReferenceFigure_LabelDistance();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure#getLabelAngle <em>Label Angle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label Angle</em>'.
	 * @see org.emftools.emf2gv.graphdesc.RichReferenceFigure#getLabelAngle()
	 * @see #getRichReferenceFigure()
	 * @generated
	 */
	EAttribute getRichReferenceFigure_LabelAngle();

	/**
	 * Returns the meta object for class '{@link org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider <em>Dynamic Property Overrider</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dynamic Property Overrider</em>'.
	 * @see org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider
	 * @generated
	 */
	EClass getDynamicPropertyOverrider();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider#getName()
	 * @see #getDynamicPropertyOverrider()
	 * @generated
	 */
	EAttribute getDynamicPropertyOverrider_Name();

	/**
	 * Returns the meta object for the reference '{@link org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider#getPropertyToOverride <em>Property To Override</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Property To Override</em>'.
	 * @see org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider#getPropertyToOverride()
	 * @see #getDynamicPropertyOverrider()
	 * @generated
	 */
	EReference getDynamicPropertyOverrider_PropertyToOverride();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider#getOverridingExpression <em>Overriding Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Overriding Expression</em>'.
	 * @see org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider#getOverridingExpression()
	 * @see #getDynamicPropertyOverrider()
	 * @generated
	 */
	EAttribute getDynamicPropertyOverrider_OverridingExpression();

	/**
	 * Returns the meta object for the container reference '{@link org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider#getFigure <em>Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Figure</em>'.
	 * @see org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider#getFigure()
	 * @see #getDynamicPropertyOverrider()
	 * @generated
	 */
	EReference getDynamicPropertyOverrider_Figure();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider#isEnabled <em>Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enabled</em>'.
	 * @see org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider#isEnabled()
	 * @see #getDynamicPropertyOverrider()
	 * @generated
	 */
	EAttribute getDynamicPropertyOverrider_Enabled();

	/**
	 * Returns the meta object for class '{@link org.emftools.emf2gv.graphdesc.Filter <em>Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Filter</em>'.
	 * @see org.emftools.emf2gv.graphdesc.Filter
	 * @generated
	 */
	EClass getFilter();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.Filter#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.emftools.emf2gv.graphdesc.Filter#getName()
	 * @see #getFilter()
	 * @generated
	 */
	EAttribute getFilter_Name();

	/**
	 * Returns the meta object for the reference '{@link org.emftools.emf2gv.graphdesc.Filter#getFilteredType <em>Filtered Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Filtered Type</em>'.
	 * @see org.emftools.emf2gv.graphdesc.Filter#getFilteredType()
	 * @see #getFilter()
	 * @generated
	 */
	EReference getFilter_FilteredType();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.Filter#getFilterExpression <em>Filter Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Filter Expression</em>'.
	 * @see org.emftools.emf2gv.graphdesc.Filter#getFilterExpression()
	 * @see #getFilter()
	 * @generated
	 */
	EAttribute getFilter_FilterExpression();

	/**
	 * Returns the meta object for the container reference '{@link org.emftools.emf2gv.graphdesc.Filter#getFigureDescription <em>Figure Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Figure Description</em>'.
	 * @see org.emftools.emf2gv.graphdesc.Filter#getFigureDescription()
	 * @see #getFilter()
	 * @generated
	 */
	EReference getFilter_FigureDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.Filter#isEnabled <em>Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enabled</em>'.
	 * @see org.emftools.emf2gv.graphdesc.Filter#isEnabled()
	 * @see #getFilter()
	 * @generated
	 */
	EAttribute getFilter_Enabled();

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
	 * Returns the meta object for the containment reference list '{@link org.emftools.emf2gv.graphdesc.AbstractFigure#getDynamicProperties <em>Dynamic Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Dynamic Properties</em>'.
	 * @see org.emftools.emf2gv.graphdesc.AbstractFigure#getDynamicProperties()
	 * @see #getAbstractFigure()
	 * @generated
	 */
	EReference getAbstractFigure_DynamicProperties();

	/**
	 * Returns the meta object for class '{@link org.emftools.emf2gv.graphdesc.AbstractReferenceFigure <em>Abstract Reference Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Reference Figure</em>'.
	 * @see org.emftools.emf2gv.graphdesc.AbstractReferenceFigure
	 * @generated
	 */
	EClass getAbstractReferenceFigure();

	/**
	 * Returns the meta object for the container reference '{@link org.emftools.emf2gv.graphdesc.AbstractReferenceFigure#getClassFigure <em>Class Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Class Figure</em>'.
	 * @see org.emftools.emf2gv.graphdesc.AbstractReferenceFigure#getClassFigure()
	 * @see #getAbstractReferenceFigure()
	 * @generated
	 */
	EReference getAbstractReferenceFigure_ClassFigure();

	/**
	 * Returns the meta object for the reference '{@link org.emftools.emf2gv.graphdesc.AbstractReferenceFigure#getEReference <em>EReference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EReference</em>'.
	 * @see org.emftools.emf2gv.graphdesc.AbstractReferenceFigure#getEReference()
	 * @see #getAbstractReferenceFigure()
	 * @generated
	 */
	EReference getAbstractReferenceFigure_EReference();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.AbstractReferenceFigure#getTargetArrowType <em>Target Arrow Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Arrow Type</em>'.
	 * @see org.emftools.emf2gv.graphdesc.AbstractReferenceFigure#getTargetArrowType()
	 * @see #getAbstractReferenceFigure()
	 * @generated
	 */
	EAttribute getAbstractReferenceFigure_TargetArrowType();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.AbstractReferenceFigure#getSourceArrowType <em>Source Arrow Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Arrow Type</em>'.
	 * @see org.emftools.emf2gv.graphdesc.AbstractReferenceFigure#getSourceArrowType()
	 * @see #getAbstractReferenceFigure()
	 * @generated
	 */
	EAttribute getAbstractReferenceFigure_SourceArrowType();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.AbstractReferenceFigure#isContainment <em>Containment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Containment</em>'.
	 * @see org.emftools.emf2gv.graphdesc.AbstractReferenceFigure#isContainment()
	 * @see #getAbstractReferenceFigure()
	 * @generated
	 */
	EAttribute getAbstractReferenceFigure_Containment();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.AbstractReferenceFigure#getCustomTargetArrow <em>Custom Target Arrow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Custom Target Arrow</em>'.
	 * @see org.emftools.emf2gv.graphdesc.AbstractReferenceFigure#getCustomTargetArrow()
	 * @see #getAbstractReferenceFigure()
	 * @generated
	 */
	EAttribute getAbstractReferenceFigure_CustomTargetArrow();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.AbstractReferenceFigure#getCustomSourceArrow <em>Custom Source Arrow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Custom Source Arrow</em>'.
	 * @see org.emftools.emf2gv.graphdesc.AbstractReferenceFigure#getCustomSourceArrow()
	 * @see #getAbstractReferenceFigure()
	 * @generated
	 */
	EAttribute getAbstractReferenceFigure_CustomSourceArrow();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.AbstractReferenceFigure#getColor <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Color</em>'.
	 * @see org.emftools.emf2gv.graphdesc.AbstractReferenceFigure#getColor()
	 * @see #getAbstractReferenceFigure()
	 * @generated
	 */
	EAttribute getAbstractReferenceFigure_Color();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.AbstractReferenceFigure#getStyle <em>Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Style</em>'.
	 * @see org.emftools.emf2gv.graphdesc.AbstractReferenceFigure#getStyle()
	 * @see #getAbstractReferenceFigure()
	 * @generated
	 */
	EAttribute getAbstractReferenceFigure_Style();

	/**
	 * Returns the meta object for the reference '{@link org.emftools.emf2gv.graphdesc.AbstractReferenceFigure#getTargetEType <em>Target EType</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target EType</em>'.
	 * @see org.emftools.emf2gv.graphdesc.AbstractReferenceFigure#getTargetEType()
	 * @see #getAbstractReferenceFigure()
	 * @generated
	 */
	EReference getAbstractReferenceFigure_TargetEType();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.AbstractReferenceFigure#getMinimumEdgeLength <em>Minimum Edge Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Minimum Edge Length</em>'.
	 * @see org.emftools.emf2gv.graphdesc.AbstractReferenceFigure#getMinimumEdgeLength()
	 * @see #getAbstractReferenceFigure()
	 * @generated
	 */
	EAttribute getAbstractReferenceFigure_MinimumEdgeLength();

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
	 * Returns the meta object for class '{@link org.emftools.emf2gv.graphdesc.AbstractAttributeFigure <em>Abstract Attribute Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Attribute Figure</em>'.
	 * @see org.emftools.emf2gv.graphdesc.AbstractAttributeFigure
	 * @generated
	 */
	EClass getAbstractAttributeFigure();

	/**
	 * Returns the meta object for the container reference '{@link org.emftools.emf2gv.graphdesc.AbstractAttributeFigure#getClassFigure <em>Class Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Class Figure</em>'.
	 * @see org.emftools.emf2gv.graphdesc.AbstractAttributeFigure#getClassFigure()
	 * @see #getAbstractAttributeFigure()
	 * @generated
	 */
	EReference getAbstractAttributeFigure_ClassFigure();

	/**
	 * Returns the meta object for the attribute list '{@link org.emftools.emf2gv.graphdesc.AbstractAttributeFigure#getLabelStyle <em>Label Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Label Style</em>'.
	 * @see org.emftools.emf2gv.graphdesc.AbstractAttributeFigure#getLabelStyle()
	 * @see #getAbstractAttributeFigure()
	 * @generated
	 */
	EAttribute getAbstractAttributeFigure_LabelStyle();

	/**
	 * Returns the meta object for class '{@link org.emftools.emf2gv.graphdesc.RichAttributeFigure <em>Rich Attribute Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rich Attribute Figure</em>'.
	 * @see org.emftools.emf2gv.graphdesc.RichAttributeFigure
	 * @generated
	 */
	EClass getRichAttributeFigure();

	/**
	 * Returns the meta object for the reference '{@link org.emftools.emf2gv.graphdesc.RichAttributeFigure#getEReference <em>EReference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EReference</em>'.
	 * @see org.emftools.emf2gv.graphdesc.RichAttributeFigure#getEReference()
	 * @see #getRichAttributeFigure()
	 * @generated
	 */
	EReference getRichAttributeFigure_EReference();

	/**
	 * Returns the meta object for the attribute '{@link org.emftools.emf2gv.graphdesc.RichAttributeFigure#getLabelExpression <em>Label Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label Expression</em>'.
	 * @see org.emftools.emf2gv.graphdesc.RichAttributeFigure#getLabelExpression()
	 * @see #getRichAttributeFigure()
	 * @generated
	 */
	EAttribute getRichAttributeFigure_LabelExpression();

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
	 * Returns the meta object for the containment reference list '{@link org.emftools.emf2gv.graphdesc.GVFigureDescription#getFilters <em>Filters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Filters</em>'.
	 * @see org.emftools.emf2gv.graphdesc.GVFigureDescription#getFilters()
	 * @see #getGVFigureDescription()
	 * @generated
	 */
	EReference getGVFigureDescription_Filters();

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
	 * Returns the meta object for enum '{@link org.emftools.emf2gv.graphdesc.EdgeStyle <em>Edge Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Edge Style</em>'.
	 * @see org.emftools.emf2gv.graphdesc.EdgeStyle
	 * @generated
	 */
	EEnum getEdgeStyle();

	/**
	 * Returns the meta object for enum '{@link org.emftools.emf2gv.graphdesc.FontStyle <em>Font Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Font Style</em>'.
	 * @see org.emftools.emf2gv.graphdesc.FontStyle
	 * @generated
	 */
	EEnum getFontStyle();

	/**
	 * Returns the meta object for data type '{@link java.awt.Color <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Color</em>'.
	 * @see java.awt.Color
	 * @model instanceClass="java.awt.Color"
	 * @generated
	 */
	EDataType getColor();

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
		 * The meta object literal for the '<em><b>Label Style</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FIGURE__LABEL_STYLE = eINSTANCE.getClassFigure_LabelStyle();

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
		 * The meta object literal for the '<em><b>EAttribute</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTRIBUTE_FIGURE__EATTRIBUTE = eINSTANCE.getAttributeFigure_EAttribute();

		/**
		 * The meta object literal for the '{@link org.emftools.emf2gv.graphdesc.impl.RichReferenceFigureImpl <em>Rich Reference Figure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.emftools.emf2gv.graphdesc.impl.RichReferenceFigureImpl
		 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getRichReferenceFigure()
		 * @generated
		 */
		EClass RICH_REFERENCE_FIGURE = eINSTANCE.getRichReferenceFigure();

		/**
		 * The meta object literal for the '<em><b>Target EReference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RICH_REFERENCE_FIGURE__TARGET_EREFERENCE = eINSTANCE.getRichReferenceFigure_TargetEReference();

		/**
		 * The meta object literal for the '<em><b>Source Label Expression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RICH_REFERENCE_FIGURE__SOURCE_LABEL_EXPRESSION = eINSTANCE.getRichReferenceFigure_SourceLabelExpression();

		/**
		 * The meta object literal for the '<em><b>Source Label Style</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RICH_REFERENCE_FIGURE__SOURCE_LABEL_STYLE = eINSTANCE.getRichReferenceFigure_SourceLabelStyle();

		/**
		 * The meta object literal for the '<em><b>Standard Label Expression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RICH_REFERENCE_FIGURE__STANDARD_LABEL_EXPRESSION = eINSTANCE.getRichReferenceFigure_StandardLabelExpression();

		/**
		 * The meta object literal for the '<em><b>Standard Label Style</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RICH_REFERENCE_FIGURE__STANDARD_LABEL_STYLE = eINSTANCE.getRichReferenceFigure_StandardLabelStyle();

		/**
		 * The meta object literal for the '<em><b>Target Label Expression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RICH_REFERENCE_FIGURE__TARGET_LABEL_EXPRESSION = eINSTANCE.getRichReferenceFigure_TargetLabelExpression();

		/**
		 * The meta object literal for the '<em><b>Target Label Style</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RICH_REFERENCE_FIGURE__TARGET_LABEL_STYLE = eINSTANCE.getRichReferenceFigure_TargetLabelStyle();

		/**
		 * The meta object literal for the '<em><b>Label Distance</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RICH_REFERENCE_FIGURE__LABEL_DISTANCE = eINSTANCE.getRichReferenceFigure_LabelDistance();

		/**
		 * The meta object literal for the '<em><b>Label Angle</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RICH_REFERENCE_FIGURE__LABEL_ANGLE = eINSTANCE.getRichReferenceFigure_LabelAngle();

		/**
		 * The meta object literal for the '{@link org.emftools.emf2gv.graphdesc.impl.DynamicPropertyOverriderImpl <em>Dynamic Property Overrider</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.emftools.emf2gv.graphdesc.impl.DynamicPropertyOverriderImpl
		 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getDynamicPropertyOverrider()
		 * @generated
		 */
		EClass DYNAMIC_PROPERTY_OVERRIDER = eINSTANCE.getDynamicPropertyOverrider();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DYNAMIC_PROPERTY_OVERRIDER__NAME = eINSTANCE.getDynamicPropertyOverrider_Name();

		/**
		 * The meta object literal for the '<em><b>Property To Override</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DYNAMIC_PROPERTY_OVERRIDER__PROPERTY_TO_OVERRIDE = eINSTANCE.getDynamicPropertyOverrider_PropertyToOverride();

		/**
		 * The meta object literal for the '<em><b>Overriding Expression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DYNAMIC_PROPERTY_OVERRIDER__OVERRIDING_EXPRESSION = eINSTANCE.getDynamicPropertyOverrider_OverridingExpression();

		/**
		 * The meta object literal for the '<em><b>Figure</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DYNAMIC_PROPERTY_OVERRIDER__FIGURE = eINSTANCE.getDynamicPropertyOverrider_Figure();

		/**
		 * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DYNAMIC_PROPERTY_OVERRIDER__ENABLED = eINSTANCE.getDynamicPropertyOverrider_Enabled();

		/**
		 * The meta object literal for the '{@link org.emftools.emf2gv.graphdesc.impl.FilterImpl <em>Filter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.emftools.emf2gv.graphdesc.impl.FilterImpl
		 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getFilter()
		 * @generated
		 */
		EClass FILTER = eINSTANCE.getFilter();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILTER__NAME = eINSTANCE.getFilter_Name();

		/**
		 * The meta object literal for the '<em><b>Filtered Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTER__FILTERED_TYPE = eINSTANCE.getFilter_FilteredType();

		/**
		 * The meta object literal for the '<em><b>Filter Expression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILTER__FILTER_EXPRESSION = eINSTANCE.getFilter_FilterExpression();

		/**
		 * The meta object literal for the '<em><b>Figure Description</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTER__FIGURE_DESCRIPTION = eINSTANCE.getFilter_FigureDescription();

		/**
		 * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILTER__ENABLED = eINSTANCE.getFilter_Enabled();

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
		 * The meta object literal for the '<em><b>Dynamic Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_FIGURE__DYNAMIC_PROPERTIES = eINSTANCE.getAbstractFigure_DynamicProperties();

		/**
		 * The meta object literal for the '{@link org.emftools.emf2gv.graphdesc.impl.AbstractReferenceFigureImpl <em>Abstract Reference Figure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.emftools.emf2gv.graphdesc.impl.AbstractReferenceFigureImpl
		 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getAbstractReferenceFigure()
		 * @generated
		 */
		EClass ABSTRACT_REFERENCE_FIGURE = eINSTANCE.getAbstractReferenceFigure();

		/**
		 * The meta object literal for the '<em><b>Class Figure</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_REFERENCE_FIGURE__CLASS_FIGURE = eINSTANCE.getAbstractReferenceFigure_ClassFigure();

		/**
		 * The meta object literal for the '<em><b>EReference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_REFERENCE_FIGURE__EREFERENCE = eINSTANCE.getAbstractReferenceFigure_EReference();

		/**
		 * The meta object literal for the '<em><b>Target Arrow Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_REFERENCE_FIGURE__TARGET_ARROW_TYPE = eINSTANCE.getAbstractReferenceFigure_TargetArrowType();

		/**
		 * The meta object literal for the '<em><b>Source Arrow Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_REFERENCE_FIGURE__SOURCE_ARROW_TYPE = eINSTANCE.getAbstractReferenceFigure_SourceArrowType();

		/**
		 * The meta object literal for the '<em><b>Containment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_REFERENCE_FIGURE__CONTAINMENT = eINSTANCE.getAbstractReferenceFigure_Containment();

		/**
		 * The meta object literal for the '<em><b>Custom Target Arrow</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_REFERENCE_FIGURE__CUSTOM_TARGET_ARROW = eINSTANCE.getAbstractReferenceFigure_CustomTargetArrow();

		/**
		 * The meta object literal for the '<em><b>Custom Source Arrow</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_REFERENCE_FIGURE__CUSTOM_SOURCE_ARROW = eINSTANCE.getAbstractReferenceFigure_CustomSourceArrow();

		/**
		 * The meta object literal for the '<em><b>Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_REFERENCE_FIGURE__COLOR = eINSTANCE.getAbstractReferenceFigure_Color();

		/**
		 * The meta object literal for the '<em><b>Style</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_REFERENCE_FIGURE__STYLE = eINSTANCE.getAbstractReferenceFigure_Style();

		/**
		 * The meta object literal for the '<em><b>Target EType</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_REFERENCE_FIGURE__TARGET_ETYPE = eINSTANCE.getAbstractReferenceFigure_TargetEType();

		/**
		 * The meta object literal for the '<em><b>Minimum Edge Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_REFERENCE_FIGURE__MINIMUM_EDGE_LENGTH = eINSTANCE.getAbstractReferenceFigure_MinimumEdgeLength();

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
		 * The meta object literal for the '{@link org.emftools.emf2gv.graphdesc.impl.AbstractAttributeFigureImpl <em>Abstract Attribute Figure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.emftools.emf2gv.graphdesc.impl.AbstractAttributeFigureImpl
		 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getAbstractAttributeFigure()
		 * @generated
		 */
		EClass ABSTRACT_ATTRIBUTE_FIGURE = eINSTANCE.getAbstractAttributeFigure();

		/**
		 * The meta object literal for the '<em><b>Class Figure</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_ATTRIBUTE_FIGURE__CLASS_FIGURE = eINSTANCE.getAbstractAttributeFigure_ClassFigure();

		/**
		 * The meta object literal for the '<em><b>Label Style</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_ATTRIBUTE_FIGURE__LABEL_STYLE = eINSTANCE.getAbstractAttributeFigure_LabelStyle();

		/**
		 * The meta object literal for the '{@link org.emftools.emf2gv.graphdesc.impl.RichAttributeFigureImpl <em>Rich Attribute Figure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.emftools.emf2gv.graphdesc.impl.RichAttributeFigureImpl
		 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getRichAttributeFigure()
		 * @generated
		 */
		EClass RICH_ATTRIBUTE_FIGURE = eINSTANCE.getRichAttributeFigure();

		/**
		 * The meta object literal for the '<em><b>EReference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RICH_ATTRIBUTE_FIGURE__EREFERENCE = eINSTANCE.getRichAttributeFigure_EReference();

		/**
		 * The meta object literal for the '<em><b>Label Expression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RICH_ATTRIBUTE_FIGURE__LABEL_EXPRESSION = eINSTANCE.getRichAttributeFigure_LabelExpression();

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
		 * The meta object literal for the '<em><b>Filters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GV_FIGURE_DESCRIPTION__FILTERS = eINSTANCE.getGVFigureDescription_Filters();

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
		 * The meta object literal for the '{@link org.emftools.emf2gv.graphdesc.EdgeStyle <em>Edge Style</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.emftools.emf2gv.graphdesc.EdgeStyle
		 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getEdgeStyle()
		 * @generated
		 */
		EEnum EDGE_STYLE = eINSTANCE.getEdgeStyle();

		/**
		 * The meta object literal for the '{@link org.emftools.emf2gv.graphdesc.FontStyle <em>Font Style</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.emftools.emf2gv.graphdesc.FontStyle
		 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getFontStyle()
		 * @generated
		 */
		EEnum FONT_STYLE = eINSTANCE.getFontStyle();

		/**
		 * The meta object literal for the '<em>Color</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.awt.Color
		 * @see org.emftools.emf2gv.graphdesc.impl.GraphdescPackageImpl#getColor()
		 * @generated
		 */
		EDataType COLOR = eINSTANCE.getColor();

		/**
		 * The meta object literal for the '<em><b>EPackages</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GV_FIGURE_DESCRIPTION__EPACKAGES = eINSTANCE.getGVFigureDescription_EPackages();

	}

} //GraphdescPackage
