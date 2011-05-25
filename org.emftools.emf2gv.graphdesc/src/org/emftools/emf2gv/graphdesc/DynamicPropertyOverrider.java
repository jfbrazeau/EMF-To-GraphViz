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

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dynamic Property Overrider</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider#getName <em>Name</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider#getPropertyToOverride <em>Property To Override</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider#getOverridingExpression <em>Overriding Expression</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider#getFigure <em>Figure</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider#isEnabled <em>Enabled</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getDynamicPropertyOverrider()
 * @model
 * @generated
 */
public interface DynamicPropertyOverrider extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getDynamicPropertyOverrider_Name()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	String getName();

	/**
	 * Returns the value of the '<em><b>Property To Override</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property To Override</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property To Override</em>' reference.
	 * @see #setPropertyToOverride(EStructuralFeature)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getDynamicPropertyOverrider_PropertyToOverride()
	 * @model
	 * @generated
	 */
	EStructuralFeature getPropertyToOverride();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider#getPropertyToOverride <em>Property To Override</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property To Override</em>' reference.
	 * @see #getPropertyToOverride()
	 * @generated
	 */
	void setPropertyToOverride(EStructuralFeature value);

	/**
	 * Returns the value of the '<em><b>Overriding Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Overriding Expression</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Overriding Expression</em>' attribute.
	 * @see #setOverridingExpression(String)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getDynamicPropertyOverrider_OverridingExpression()
	 * @model
	 * @generated
	 */
	String getOverridingExpression();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider#getOverridingExpression <em>Overriding Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Overriding Expression</em>' attribute.
	 * @see #getOverridingExpression()
	 * @generated
	 */
	void setOverridingExpression(String value);

	/**
	 * Returns the value of the '<em><b>Figure</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.emftools.emf2gv.graphdesc.AbstractFigure#getDynamicProperties <em>Dynamic Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Figure</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Figure</em>' container reference.
	 * @see #setFigure(AbstractFigure)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getDynamicPropertyOverrider_Figure()
	 * @see org.emftools.emf2gv.graphdesc.AbstractFigure#getDynamicProperties
	 * @model opposite="dynamicProperties" transient="false"
	 * @generated
	 */
	AbstractFigure getFigure();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider#getFigure <em>Figure</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Figure</em>' container reference.
	 * @see #getFigure()
	 * @generated
	 */
	void setFigure(AbstractFigure value);

	/**
	 * Returns the value of the '<em><b>Enabled</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enabled</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enabled</em>' attribute.
	 * @see #setEnabled(boolean)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getDynamicPropertyOverrider_Enabled()
	 * @model default="true"
	 * @generated
	 */
	boolean isEnabled();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled</em>' attribute.
	 * @see #isEnabled()
	 * @generated
	 */
	void setEnabled(boolean value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean validate(DiagnosticChain diagnostic, Map<Object, Object> context);

} // DynamicPropertyOverrider
