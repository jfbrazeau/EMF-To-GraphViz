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
