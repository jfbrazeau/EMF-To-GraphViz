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

import java.awt.Color;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class Figure</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ClassFigure#getEPackage <em>EPackage</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ClassFigure#getEClass <em>EClass</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ClassFigure#getLabelEAttribute <em>Label EAttribute</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ClassFigure#getLabelStyle <em>Label Style</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ClassFigure#getAttributeFigures <em>Attribute Figures</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ClassFigure#getReferenceFigures <em>Reference Figures</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ClassFigure#getGvFigureDescription <em>Gv Figure Description</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ClassFigure#getHeaderBackgroundColor <em>Header Background Color</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ClassFigure#getBodyBackgroundColor <em>Body Background Color</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ClassFigure#getNestedFiguresEReferences <em>Nested Figures EReferences</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ClassFigure#isContainer <em>Container</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getClassFigure()
 * @model
 * @generated
 */
public interface ClassFigure extends AbstractFigure {
	/**
	 * Returns the value of the '<em><b>EClass</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EClass</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EClass</em>' reference.
	 * @see #setEClass(EClass)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getClassFigure_EClass()
	 * @model required="true"
	 * @generated
	 */
	EClass getEClass();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.ClassFigure#getEClass <em>EClass</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EClass</em>' reference.
	 * @see #getEClass()
	 * @generated
	 */
	void setEClass(EClass value);

	/**
	 * Returns the value of the '<em><b>Label EAttribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label EAttribute</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label EAttribute</em>' reference.
	 * @see #setLabelEAttribute(EAttribute)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getClassFigure_LabelEAttribute()
	 * @model
	 * @generated
	 */
	EAttribute getLabelEAttribute();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.ClassFigure#getLabelEAttribute <em>Label EAttribute</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label EAttribute</em>' reference.
	 * @see #getLabelEAttribute()
	 * @generated
	 */
	void setLabelEAttribute(EAttribute value);

	/**
	 * Returns the value of the '<em><b>Label Style</b></em>' attribute list.
	 * The list contents are of type {@link org.emftools.emf2gv.graphdesc.FontStyle}.
	 * The literals are from the enumeration {@link org.emftools.emf2gv.graphdesc.FontStyle}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label Style</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label Style</em>' attribute list.
	 * @see org.emftools.emf2gv.graphdesc.FontStyle
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getClassFigure_LabelStyle()
	 * @model default=""
	 * @generated
	 */
	EList<FontStyle> getLabelStyle();

	/**
	 * Returns the value of the '<em><b>Attribute Figures</b></em>' containment reference list.
	 * The list contents are of type {@link org.emftools.emf2gv.graphdesc.AbstractAttributeFigure}.
	 * It is bidirectional and its opposite is '{@link org.emftools.emf2gv.graphdesc.AbstractAttributeFigure#getClassFigure <em>Class Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute Figures</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribute Figures</em>' containment reference list.
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getClassFigure_AttributeFigures()
	 * @see org.emftools.emf2gv.graphdesc.AbstractAttributeFigure#getClassFigure
	 * @model opposite="classFigure" containment="true"
	 * @generated
	 */
	EList<AbstractAttributeFigure> getAttributeFigures();

	/**
	 * Returns the value of the '<em><b>EPackage</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EPackage</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EPackage</em>' reference.
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getClassFigure_EPackage()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	EPackage getEPackage();

	/**
	 * Returns the value of the '<em><b>Reference Figures</b></em>' containment reference list.
	 * The list contents are of type {@link org.emftools.emf2gv.graphdesc.AbstractReferenceFigure}.
	 * It is bidirectional and its opposite is '{@link org.emftools.emf2gv.graphdesc.AbstractReferenceFigure#getClassFigure <em>Class Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference Figures</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference Figures</em>' containment reference list.
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getClassFigure_ReferenceFigures()
	 * @see org.emftools.emf2gv.graphdesc.AbstractReferenceFigure#getClassFigure
	 * @model opposite="classFigure" containment="true"
	 * @generated
	 */
	EList<AbstractReferenceFigure> getReferenceFigures();

	/**
	 * Returns the value of the '<em><b>Gv Figure Description</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.emftools.emf2gv.graphdesc.GVFigureDescription#getClassFigures <em>Class Figures</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Gv Figure Description</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gv Figure Description</em>' container reference.
	 * @see #setGvFigureDescription(GVFigureDescription)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getClassFigure_GvFigureDescription()
	 * @see org.emftools.emf2gv.graphdesc.GVFigureDescription#getClassFigures
	 * @model opposite="classFigures" required="true" transient="false"
	 * @generated
	 */
	GVFigureDescription getGvFigureDescription();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.ClassFigure#getGvFigureDescription <em>Gv Figure Description</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gv Figure Description</em>' container reference.
	 * @see #getGvFigureDescription()
	 * @generated
	 */
	void setGvFigureDescription(GVFigureDescription value);

	/**
	 * Returns the value of the '<em><b>Header Background Color</b></em>' attribute.
	 * The default value is <code>"#9DBDF9"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Header Background Color</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Header Background Color</em>' attribute.
	 * @see #setHeaderBackgroundColor(Color)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getClassFigure_HeaderBackgroundColor()
	 * @model default="#9DBDF9" dataType="org.emftools.emf2gv.graphdesc.Color"
	 * @generated
	 */
	Color getHeaderBackgroundColor();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.ClassFigure#getHeaderBackgroundColor <em>Header Background Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Header Background Color</em>' attribute.
	 * @see #getHeaderBackgroundColor()
	 * @generated
	 */
	void setHeaderBackgroundColor(Color value);

	/**
	 * Returns the value of the '<em><b>Body Background Color</b></em>' attribute.
	 * The default value is <code>"#EEEEEE"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Body Background Color</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Body Background Color</em>' attribute.
	 * @see #setBodyBackgroundColor(Color)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getClassFigure_BodyBackgroundColor()
	 * @model default="#EEEEEE" dataType="org.emftools.emf2gv.graphdesc.Color"
	 * @generated
	 */
	Color getBodyBackgroundColor();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.ClassFigure#getBodyBackgroundColor <em>Body Background Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Body Background Color</em>' attribute.
	 * @see #getBodyBackgroundColor()
	 * @generated
	 */
	void setBodyBackgroundColor(Color value);

	/**
	 * Returns the value of the '<em><b>Nested Figures EReferences</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EReference}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nested Figures EReferences</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nested Figures EReferences</em>' reference list.
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getClassFigure_NestedFiguresEReferences()
	 * @model
	 * @generated
	 */
	EList<EReference> getNestedFiguresEReferences();

	/**
	 * Returns the value of the '<em><b>Container</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Container</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Container</em>' attribute.
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getClassFigure_Container()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	boolean isContainer();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	AttributeFigure getAttributeFigure(EAttribute eAttribute);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	ReferenceFigure getReferenceFigure(EReference eReference);

} // ClassFigure
