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
package org.emftools.emf2gv.graphdesc.impl;

import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.emftools.emf2gv.graphdesc.AttributeFigure;
import org.emftools.emf2gv.graphdesc.ClassFigure;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;
import org.emftools.emf2gv.graphdesc.util.GraphdescValidator;
import org.emftools.validation.utils.EMFConstraintsHelper;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attribute Figure</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.AttributeFigureImpl#getEAttribute <em>EAttribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AttributeFigureImpl extends AbstractAttributeFigureImpl implements AttributeFigure {
	/**
	 * The cached value of the '{@link #getEAttribute() <em>EAttribute</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEAttribute()
	 * @generated
	 * @ordered
	 */
	protected EAttribute eAttribute;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AttributeFigureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GraphdescPackage.Literals.ATTRIBUTE_FIGURE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getName() {
		String result = null;
		if (getEAttribute() != null) {
			result = getEAttribute().getName();
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEAttribute() {
		if (eAttribute != null && eAttribute.eIsProxy()) {
			InternalEObject oldEAttribute = (InternalEObject)eAttribute;
			eAttribute = (EAttribute)eResolveProxy(oldEAttribute);
			if (eAttribute != oldEAttribute) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GraphdescPackage.ATTRIBUTE_FIGURE__EATTRIBUTE, oldEAttribute, eAttribute));
			}
		}
		return eAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute basicGetEAttribute() {
		return eAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setEAttribute(EAttribute newEAttribute) {
		String oldName = getName();
		EAttribute oldEAttribute = eAttribute;
		eAttribute = newEAttribute;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.ATTRIBUTE_FIGURE__EATTRIBUTE, oldEAttribute, eAttribute));
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.ATTRIBUTE_FIGURE__NAME, oldName, getName()));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validate(DiagnosticChain diagnostic, Map<Object, Object> context) {
		boolean valid = super.validate(diagnostic, context);
		if (valid) {
			EMFConstraintsHelper constraintsHelper = EMFConstraintsHelper.getInstance(GraphdescValidator.DIAGNOSTIC_SOURCE);
			ClassFigure classFigure = getClassFigure();
			EAttribute eAttribute = getEAttribute();
			if (eAttribute == null) {
				constraintsHelper.addError(diagnostic, this, 0, "The attribute figure must be associated to an EAttribute");
				valid = false;
			}
			else {
				// Check unique
				valid = constraintsHelper.addErrorIfNotUnique(classFigure.getAttributeFigures(),
						GraphdescPackage.eINSTANCE.getAttributeFigure_EAttribute(), diagnostic, this,
						0, "The EAttribute ''{0}'' is referenced twice or more", eAttribute.getName());

				// Check eAttribute eClass
				if (valid) {
					EClass eClass = classFigure.getEClass();
					if (eClass != null) {
						if (!eClass.getEAllAttributes().contains(eAttribute)) {
							constraintsHelper.addError(diagnostic, this, 0, "The attribute figure is associated to an EAttribute ({0}) that is not a member of the parent class figure''s EClass ({1})", eAttribute.getName(), eClass.getName());
							valid = false;
						}
					}
				}
				
			}
		}
		return valid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GraphdescPackage.ATTRIBUTE_FIGURE__EATTRIBUTE:
				if (resolve) return getEAttribute();
				return basicGetEAttribute();
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
			case GraphdescPackage.ATTRIBUTE_FIGURE__EATTRIBUTE:
				setEAttribute((EAttribute)newValue);
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
			case GraphdescPackage.ATTRIBUTE_FIGURE__EATTRIBUTE:
				setEAttribute((EAttribute)null);
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
			case GraphdescPackage.ATTRIBUTE_FIGURE__EATTRIBUTE:
				return eAttribute != null;
		}
		return super.eIsSet(featureID);
	}

	/* (non-Javadoc)
	 * @see org.emftools.emf2gv.graphdesc.impl.AbstractFigureImpl#getStandardOCLContext()
	 */
	@Override
	public EClass getStandardOCLContext() {
		// The context is the same as the ClassFigure
		return getClassFigure() != null ? getClassFigure()
				.getStandardOCLContext() : null;
	}

} //AttributeFigureImpl
