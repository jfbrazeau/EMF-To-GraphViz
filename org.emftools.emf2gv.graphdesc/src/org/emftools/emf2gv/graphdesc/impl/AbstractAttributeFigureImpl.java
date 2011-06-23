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

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftools.emf2gv.graphdesc.AbstractAttributeFigure;
import org.emftools.emf2gv.graphdesc.ClassFigure;
import org.emftools.emf2gv.graphdesc.FontStyle;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;
import org.emftools.emf2gv.graphdesc.util.GraphdescValidator;
import org.emftools.validation.utils.EMFConstraintsHelper;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Attribute Figure</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.AbstractAttributeFigureImpl#getClassFigure <em>Class Figure</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.AbstractAttributeFigureImpl#getLabelStyle <em>Label Style</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractAttributeFigureImpl extends AbstractFigureImpl implements AbstractAttributeFigure {
	/**
	 * The cached value of the '{@link #getLabelStyle() <em>Label Style</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabelStyle()
	 * @generated
	 * @ordered
	 */
	protected EList<FontStyle> labelStyle;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractAttributeFigureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GraphdescPackage.Literals.ABSTRACT_ATTRIBUTE_FIGURE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassFigure getClassFigure() {
		if (eContainerFeatureID() != GraphdescPackage.ABSTRACT_ATTRIBUTE_FIGURE__CLASS_FIGURE) return null;
		return (ClassFigure)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetClassFigure(ClassFigure newClassFigure, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newClassFigure, GraphdescPackage.ABSTRACT_ATTRIBUTE_FIGURE__CLASS_FIGURE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClassFigure(ClassFigure newClassFigure) {
		if (newClassFigure != eInternalContainer() || (eContainerFeatureID() != GraphdescPackage.ABSTRACT_ATTRIBUTE_FIGURE__CLASS_FIGURE && newClassFigure != null)) {
			if (EcoreUtil.isAncestor(this, newClassFigure))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newClassFigure != null)
				msgs = ((InternalEObject)newClassFigure).eInverseAdd(this, GraphdescPackage.CLASS_FIGURE__ATTRIBUTE_FIGURES, ClassFigure.class, msgs);
			msgs = basicSetClassFigure(newClassFigure, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.ABSTRACT_ATTRIBUTE_FIGURE__CLASS_FIGURE, newClassFigure, newClassFigure));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FontStyle> getLabelStyle() {
		if (labelStyle == null) {
			labelStyle = new EDataTypeUniqueEList<FontStyle>(FontStyle.class, this, GraphdescPackage.ABSTRACT_ATTRIBUTE_FIGURE__LABEL_STYLE);
		}
		return labelStyle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GraphdescPackage.ABSTRACT_ATTRIBUTE_FIGURE__CLASS_FIGURE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetClassFigure((ClassFigure)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GraphdescPackage.ABSTRACT_ATTRIBUTE_FIGURE__CLASS_FIGURE:
				return basicSetClassFigure(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case GraphdescPackage.ABSTRACT_ATTRIBUTE_FIGURE__CLASS_FIGURE:
				return eInternalContainer().eInverseRemove(this, GraphdescPackage.CLASS_FIGURE__ATTRIBUTE_FIGURES, ClassFigure.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GraphdescPackage.ABSTRACT_ATTRIBUTE_FIGURE__CLASS_FIGURE:
				return getClassFigure();
			case GraphdescPackage.ABSTRACT_ATTRIBUTE_FIGURE__LABEL_STYLE:
				return getLabelStyle();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case GraphdescPackage.ABSTRACT_ATTRIBUTE_FIGURE__CLASS_FIGURE:
				setClassFigure((ClassFigure)newValue);
				return;
			case GraphdescPackage.ABSTRACT_ATTRIBUTE_FIGURE__LABEL_STYLE:
				getLabelStyle().clear();
				getLabelStyle().addAll((Collection<? extends FontStyle>)newValue);
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
			case GraphdescPackage.ABSTRACT_ATTRIBUTE_FIGURE__CLASS_FIGURE:
				setClassFigure((ClassFigure)null);
				return;
			case GraphdescPackage.ABSTRACT_ATTRIBUTE_FIGURE__LABEL_STYLE:
				getLabelStyle().clear();
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
			case GraphdescPackage.ABSTRACT_ATTRIBUTE_FIGURE__CLASS_FIGURE:
				return getClassFigure() != null;
			case GraphdescPackage.ABSTRACT_ATTRIBUTE_FIGURE__LABEL_STYLE:
				return labelStyle != null && !labelStyle.isEmpty();
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
		result.append(" (labelStyle: ");
		result.append(labelStyle);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validate(DiagnosticChain diagnostic, Map<Object, Object> context) {
		EMFConstraintsHelper constraintsHelper = EMFConstraintsHelper.getInstance(GraphdescValidator.DIAGNOSTIC_SOURCE);
		boolean valid = true;
		ClassFigure classFigure = getClassFigure();
		if (classFigure == null) {
			constraintsHelper.addError(diagnostic, this, 0, "The attribute figure must be contained in a class figure");
			valid = false;
		}
		return valid;
	}

} //AbstractAttributeFigureImpl
