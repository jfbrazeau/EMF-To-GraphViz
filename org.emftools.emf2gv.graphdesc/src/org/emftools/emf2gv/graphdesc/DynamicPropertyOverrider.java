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
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

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
	 * @model changeable="false" volatile="true" derived="true"
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
	 * @see #setPropertyToOverride(EAttribute)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getDynamicPropertyOverrider_PropertyToOverride()
	 * @model
	 * @generated
	 */
	EAttribute getPropertyToOverride();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider#getPropertyToOverride <em>Property To Override</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property To Override</em>' reference.
	 * @see #getPropertyToOverride()
	 * @generated
	 */
	void setPropertyToOverride(EAttribute value);

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean validate(DiagnosticChain diagnostic, Map<Object, Object> context);

} // DynamicPropertyOverrider