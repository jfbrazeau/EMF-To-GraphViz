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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rich Reference Figure</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure#getTargetEReference <em>Target EReference</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure#getSourceLabelExpression <em>Source Label Expression</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure#getSourceLabelStyle <em>Source Label Style</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure#getStandardLabelExpression <em>Standard Label Expression</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure#getStandardLabelStyle <em>Standard Label Style</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure#getTargetLabelExpression <em>Target Label Expression</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure#getTargetLabelStyle <em>Target Label Style</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure#getLabelDistance <em>Label Distance</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure#getLabelAngle <em>Label Angle</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getRichReferenceFigure()
 * @model
 * @generated
 */
public interface RichReferenceFigure extends AbstractReferenceFigure {
	/**
	 * Returns the value of the '<em><b>Target EReference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target EReference</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target EReference</em>' reference.
	 * @see #setTargetEReference(EReference)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getRichReferenceFigure_TargetEReference()
	 * @model required="true"
	 * @generated
	 */
	EReference getTargetEReference();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure#getTargetEReference <em>Target EReference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target EReference</em>' reference.
	 * @see #getTargetEReference()
	 * @generated
	 */
	void setTargetEReference(EReference value);

	/**
	 * Returns the value of the '<em><b>Source Label Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Label Expression</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Label Expression</em>' attribute.
	 * @see #setSourceLabelExpression(String)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getRichReferenceFigure_SourceLabelExpression()
	 * @model
	 * @generated
	 */
	String getSourceLabelExpression();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure#getSourceLabelExpression <em>Source Label Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Label Expression</em>' attribute.
	 * @see #getSourceLabelExpression()
	 * @generated
	 */
	void setSourceLabelExpression(String value);

	/**
	 * Returns the value of the '<em><b>Source Label Style</b></em>' attribute list.
	 * The list contents are of type {@link org.emftools.emf2gv.graphdesc.FontStyle}.
	 * The literals are from the enumeration {@link org.emftools.emf2gv.graphdesc.FontStyle}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Label Style</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Label Style</em>' attribute list.
	 * @see org.emftools.emf2gv.graphdesc.FontStyle
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getRichReferenceFigure_SourceLabelStyle()
	 * @model default=""
	 * @generated
	 */
	EList<FontStyle> getSourceLabelStyle();

	/**
	 * Returns the value of the '<em><b>Standard Label Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Standard Label Expression</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Standard Label Expression</em>' attribute.
	 * @see #setStandardLabelExpression(String)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getRichReferenceFigure_StandardLabelExpression()
	 * @model
	 * @generated
	 */
	String getStandardLabelExpression();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure#getStandardLabelExpression <em>Standard Label Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Standard Label Expression</em>' attribute.
	 * @see #getStandardLabelExpression()
	 * @generated
	 */
	void setStandardLabelExpression(String value);

	/**
	 * Returns the value of the '<em><b>Standard Label Style</b></em>' attribute list.
	 * The list contents are of type {@link org.emftools.emf2gv.graphdesc.FontStyle}.
	 * The literals are from the enumeration {@link org.emftools.emf2gv.graphdesc.FontStyle}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Standard Label Style</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Standard Label Style</em>' attribute list.
	 * @see org.emftools.emf2gv.graphdesc.FontStyle
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getRichReferenceFigure_StandardLabelStyle()
	 * @model default=""
	 * @generated
	 */
	EList<FontStyle> getStandardLabelStyle();

	/**
	 * Returns the value of the '<em><b>Target Label Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Label Expression</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Label Expression</em>' attribute.
	 * @see #setTargetLabelExpression(String)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getRichReferenceFigure_TargetLabelExpression()
	 * @model
	 * @generated
	 */
	String getTargetLabelExpression();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure#getTargetLabelExpression <em>Target Label Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Label Expression</em>' attribute.
	 * @see #getTargetLabelExpression()
	 * @generated
	 */
	void setTargetLabelExpression(String value);

	/**
	 * Returns the value of the '<em><b>Target Label Style</b></em>' attribute list.
	 * The list contents are of type {@link org.emftools.emf2gv.graphdesc.FontStyle}.
	 * The literals are from the enumeration {@link org.emftools.emf2gv.graphdesc.FontStyle}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Label Style</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Label Style</em>' attribute list.
	 * @see org.emftools.emf2gv.graphdesc.FontStyle
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getRichReferenceFigure_TargetLabelStyle()
	 * @model default=""
	 * @generated
	 */
	EList<FontStyle> getTargetLabelStyle();

	/**
	 * Returns the value of the '<em><b>Label Distance</b></em>' attribute.
	 * The default value is <code>"5.0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label Distance</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label Distance</em>' attribute.
	 * @see #setLabelDistance(double)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getRichReferenceFigure_LabelDistance()
	 * @model default="5.0"
	 * @generated
	 */
	double getLabelDistance();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure#getLabelDistance <em>Label Distance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label Distance</em>' attribute.
	 * @see #getLabelDistance()
	 * @generated
	 */
	void setLabelDistance(double value);

	/**
	 * Returns the value of the '<em><b>Label Angle</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label Angle</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label Angle</em>' attribute.
	 * @see #setLabelAngle(double)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getRichReferenceFigure_LabelAngle()
	 * @model default="0"
	 * @generated
	 */
	double getLabelAngle();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure#getLabelAngle <em>Label Angle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label Angle</em>' attribute.
	 * @see #getLabelAngle()
	 * @generated
	 */
	void setLabelAngle(double value);

} // RichReferenceFigure
