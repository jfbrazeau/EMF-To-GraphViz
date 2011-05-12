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

import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;
import org.emftools.emf2gv.graphdesc.util.GraphdescValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dynamic Property Overrider</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.DynamicPropertyOverriderImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.DynamicPropertyOverriderImpl#getPropertyToOverride <em>Property To Override</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.DynamicPropertyOverriderImpl#getOverridingExpression <em>Overriding Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DynamicPropertyOverriderImpl extends EObjectImpl implements DynamicPropertyOverrider {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPropertyToOverride() <em>Property To Override</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPropertyToOverride()
	 * @generated
	 * @ordered
	 */
	protected EAttribute propertyToOverride;

	/**
	 * The default value of the '{@link #getOverridingExpression() <em>Overriding Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOverridingExpression()
	 * @generated
	 * @ordered
	 */
	protected static final String OVERRIDING_EXPRESSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOverridingExpression() <em>Overriding Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOverridingExpression()
	 * @generated
	 * @ordered
	 */
	protected String overridingExpression = OVERRIDING_EXPRESSION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DynamicPropertyOverriderImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GraphdescPackage.Literals.DYNAMIC_PROPERTY_OVERRIDER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getName() {
		return getPropertyToOverride() != null ? getPropertyToOverride().getName() : null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPropertyToOverride() {
		if (propertyToOverride != null && propertyToOverride.eIsProxy()) {
			InternalEObject oldPropertyToOverride = (InternalEObject)propertyToOverride;
			propertyToOverride = (EAttribute)eResolveProxy(oldPropertyToOverride);
			if (propertyToOverride != oldPropertyToOverride) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__PROPERTY_TO_OVERRIDE, oldPropertyToOverride, propertyToOverride));
			}
		}
		return propertyToOverride;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute basicGetPropertyToOverride() {
		return propertyToOverride;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setPropertyToOverride(EAttribute newPropertyToOverride) {
		String oldName = getName();
		EAttribute oldPropertyToOverride = propertyToOverride;
		propertyToOverride = newPropertyToOverride;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__PROPERTY_TO_OVERRIDE, oldPropertyToOverride, propertyToOverride));
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__NAME, oldName, getName()));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOverridingExpression() {
		return overridingExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOverridingExpression(String newOverridingExpression) {
		String oldOverridingExpression = overridingExpression;
		overridingExpression = newOverridingExpression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__OVERRIDING_EXPRESSION, oldOverridingExpression, overridingExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validate(DiagnosticChain diagnostic, Map<Object, Object> context) {
		// TODO: implement this method
		// -> specify the condition that violates the invariant
		// -> verify the details of the diagnostic, including severity and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostic != null) {
				diagnostic.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 GraphdescValidator.DIAGNOSTIC_SOURCE,
						 GraphdescValidator.DYNAMIC_PROPERTY_OVERRIDER__VALIDATE,
						 EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] { "validate", EObjectValidator.getObjectLabel(this, context) }),
						 new Object [] { this }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__NAME:
				return getName();
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__PROPERTY_TO_OVERRIDE:
				if (resolve) return getPropertyToOverride();
				return basicGetPropertyToOverride();
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__OVERRIDING_EXPRESSION:
				return getOverridingExpression();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__PROPERTY_TO_OVERRIDE:
				setPropertyToOverride((EAttribute)newValue);
				return;
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__OVERRIDING_EXPRESSION:
				setOverridingExpression((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__PROPERTY_TO_OVERRIDE:
				setPropertyToOverride((EAttribute)null);
				return;
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__OVERRIDING_EXPRESSION:
				setOverridingExpression(OVERRIDING_EXPRESSION_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__NAME:
				return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__PROPERTY_TO_OVERRIDE:
				return propertyToOverride != null;
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__OVERRIDING_EXPRESSION:
				return OVERRIDING_EXPRESSION_EDEFAULT == null ? overridingExpression != null : !OVERRIDING_EXPRESSION_EDEFAULT.equals(overridingExpression);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (overridingExpression: ");
		result.append(overridingExpression);
		result.append(')');
		return result.toString();
	}

} //DynamicPropertyOverriderImpl
