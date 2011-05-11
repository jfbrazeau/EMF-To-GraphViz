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

import org.eclipse.emf.ecore.EAttribute;
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
 *   <li>{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure#getSourceLabelEAttribute <em>Source Label EAttribute</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure#getStandardLabelEAttribute <em>Standard Label EAttribute</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure#getTargetLabelEAttribute <em>Target Label EAttribute</em>}</li>
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
	 * Returns the value of the '<em><b>Source Label EAttribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Label EAttribute</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Label EAttribute</em>' reference.
	 * @see #setSourceLabelEAttribute(EAttribute)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getRichReferenceFigure_SourceLabelEAttribute()
	 * @model
	 * @generated
	 */
	EAttribute getSourceLabelEAttribute();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure#getSourceLabelEAttribute <em>Source Label EAttribute</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Label EAttribute</em>' reference.
	 * @see #getSourceLabelEAttribute()
	 * @generated
	 */
	void setSourceLabelEAttribute(EAttribute value);

	/**
	 * Returns the value of the '<em><b>Standard Label EAttribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Standard Label EAttribute</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Standard Label EAttribute</em>' reference.
	 * @see #setStandardLabelEAttribute(EAttribute)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getRichReferenceFigure_StandardLabelEAttribute()
	 * @model
	 * @generated
	 */
	EAttribute getStandardLabelEAttribute();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure#getStandardLabelEAttribute <em>Standard Label EAttribute</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Standard Label EAttribute</em>' reference.
	 * @see #getStandardLabelEAttribute()
	 * @generated
	 */
	void setStandardLabelEAttribute(EAttribute value);

	/**
	 * Returns the value of the '<em><b>Target Label EAttribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Label EAttribute</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Label EAttribute</em>' reference.
	 * @see #setTargetLabelEAttribute(EAttribute)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getRichReferenceFigure_TargetLabelEAttribute()
	 * @model
	 * @generated
	 */
	EAttribute getTargetLabelEAttribute();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure#getTargetLabelEAttribute <em>Target Label EAttribute</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Label EAttribute</em>' reference.
	 * @see #getTargetLabelEAttribute()
	 * @generated
	 */
	void setTargetLabelEAttribute(EAttribute value);

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
