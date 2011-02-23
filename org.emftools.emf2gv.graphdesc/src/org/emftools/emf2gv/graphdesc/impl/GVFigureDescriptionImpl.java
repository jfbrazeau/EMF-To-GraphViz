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
 */
package org.emftools.emf2gv.graphdesc.impl;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.emftools.emf2gv.graphdesc.ClassFigure;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;
import org.emftools.emf2gv.graphdesc.Orientation;
import org.emftools.emf2gv.graphdesc.util.GraphdescValidator;
import org.emftools.validation.utils.EMFConstraintsHelper;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>GV Figure Description</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.GVFigureDescriptionImpl#getEPackages <em>EPackages</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.GVFigureDescriptionImpl#getClassFigures <em>Class Figures</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.GVFigureDescriptionImpl#getOrientation <em>Orientation</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.GVFigureDescriptionImpl#isAlignSameEClasses <em>Align Same EClasses</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GVFigureDescriptionImpl extends EObjectImpl implements GVFigureDescription {
	/**
	 * The cached value of the '{@link #getEPackages() <em>EPackages</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEPackages()
	 * @generated
	 * @ordered
	 */
	protected EList<EPackage> ePackages;

	/**
	 * The cached value of the '{@link #getClassFigures() <em>Class Figures</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassFigures()
	 * @generated
	 * @ordered
	 */
	protected EList<ClassFigure> classFigures;

	/**
	 * The default value of the '{@link #getOrientation() <em>Orientation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrientation()
	 * @generated
	 * @ordered
	 */
	protected static final Orientation ORIENTATION_EDEFAULT = Orientation.LEFT_TO_RIGHT;

	/**
	 * The cached value of the '{@link #getOrientation() <em>Orientation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrientation()
	 * @generated
	 * @ordered
	 */
	protected Orientation orientation = ORIENTATION_EDEFAULT;

	/**
	 * The default value of the '{@link #isAlignSameEClasses() <em>Align Same EClasses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAlignSameEClasses()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ALIGN_SAME_ECLASSES_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAlignSameEClasses() <em>Align Same EClasses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAlignSameEClasses()
	 * @generated
	 * @ordered
	 */
	protected boolean alignSameEClasses = ALIGN_SAME_ECLASSES_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GVFigureDescriptionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GraphdescPackage.Literals.GV_FIGURE_DESCRIPTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ClassFigure> getClassFigures() {
		if (classFigures == null) {
			classFigures = new EObjectContainmentWithInverseEList<ClassFigure>(ClassFigure.class, this, GraphdescPackage.GV_FIGURE_DESCRIPTION__CLASS_FIGURES, GraphdescPackage.CLASS_FIGURE__GV_FIGURE_DESCRIPTION);
		}
		return classFigures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Orientation getOrientation() {
		return orientation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrientation(Orientation newOrientation) {
		Orientation oldOrientation = orientation;
		orientation = newOrientation == null ? ORIENTATION_EDEFAULT : newOrientation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.GV_FIGURE_DESCRIPTION__ORIENTATION, oldOrientation, orientation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAlignSameEClasses() {
		return alignSameEClasses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAlignSameEClasses(boolean newAlignSameEClasses) {
		boolean oldAlignSameEClasses = alignSameEClasses;
		alignSameEClasses = newAlignSameEClasses;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.GV_FIGURE_DESCRIPTION__ALIGN_SAME_ECLASSES, oldAlignSameEClasses, alignSameEClasses));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validate(DiagnosticChain diagnostic, Map<Object, Object> context) {
		EMFConstraintsHelper constraintsHelper = EMFConstraintsHelper.getInstance(GraphdescValidator.DIAGNOSTIC_SOURCE);
		boolean valid = true;
		if (getEPackages().size() == 0) {
			constraintsHelper.addError(diagnostic, this, 0, "At least one EPackage must be selected");
			valid = false;
		}
		return valid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EPackage> getEPackages() {
		if (ePackages == null) {
			ePackages = new EObjectResolvingEList<EPackage>(EPackage.class, this, GraphdescPackage.GV_FIGURE_DESCRIPTION__EPACKAGES);
		}
		return ePackages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ClassFigure getClassFigure(EClass eClass) {
		ClassFigure result = null;
		EList<ClassFigure> classFigures = getClassFigures();
		for (int i = 0; i < classFigures.size() && result == null; i++) {
			ClassFigure classFigure = classFigures.get(i);
			if (eClass.equals(classFigure.getEClass())) {
				result = classFigure;
			}
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GraphdescPackage.GV_FIGURE_DESCRIPTION__CLASS_FIGURES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getClassFigures()).basicAdd(otherEnd, msgs);
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
			case GraphdescPackage.GV_FIGURE_DESCRIPTION__CLASS_FIGURES:
				return ((InternalEList<?>)getClassFigures()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GraphdescPackage.GV_FIGURE_DESCRIPTION__EPACKAGES:
				return getEPackages();
			case GraphdescPackage.GV_FIGURE_DESCRIPTION__CLASS_FIGURES:
				return getClassFigures();
			case GraphdescPackage.GV_FIGURE_DESCRIPTION__ORIENTATION:
				return getOrientation();
			case GraphdescPackage.GV_FIGURE_DESCRIPTION__ALIGN_SAME_ECLASSES:
				return isAlignSameEClasses();
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
			case GraphdescPackage.GV_FIGURE_DESCRIPTION__EPACKAGES:
				getEPackages().clear();
				getEPackages().addAll((Collection<? extends EPackage>)newValue);
				return;
			case GraphdescPackage.GV_FIGURE_DESCRIPTION__CLASS_FIGURES:
				getClassFigures().clear();
				getClassFigures().addAll((Collection<? extends ClassFigure>)newValue);
				return;
			case GraphdescPackage.GV_FIGURE_DESCRIPTION__ORIENTATION:
				setOrientation((Orientation)newValue);
				return;
			case GraphdescPackage.GV_FIGURE_DESCRIPTION__ALIGN_SAME_ECLASSES:
				setAlignSameEClasses((Boolean)newValue);
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
			case GraphdescPackage.GV_FIGURE_DESCRIPTION__EPACKAGES:
				getEPackages().clear();
				return;
			case GraphdescPackage.GV_FIGURE_DESCRIPTION__CLASS_FIGURES:
				getClassFigures().clear();
				return;
			case GraphdescPackage.GV_FIGURE_DESCRIPTION__ORIENTATION:
				setOrientation(ORIENTATION_EDEFAULT);
				return;
			case GraphdescPackage.GV_FIGURE_DESCRIPTION__ALIGN_SAME_ECLASSES:
				setAlignSameEClasses(ALIGN_SAME_ECLASSES_EDEFAULT);
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
			case GraphdescPackage.GV_FIGURE_DESCRIPTION__EPACKAGES:
				return ePackages != null && !ePackages.isEmpty();
			case GraphdescPackage.GV_FIGURE_DESCRIPTION__CLASS_FIGURES:
				return classFigures != null && !classFigures.isEmpty();
			case GraphdescPackage.GV_FIGURE_DESCRIPTION__ORIENTATION:
				return orientation != ORIENTATION_EDEFAULT;
			case GraphdescPackage.GV_FIGURE_DESCRIPTION__ALIGN_SAME_ECLASSES:
				return alignSameEClasses != ALIGN_SAME_ECLASSES_EDEFAULT;
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
		result.append(" (orientation: ");
		result.append(orientation);
		result.append(", alignSameEClasses: ");
		result.append(alignSameEClasses);
		result.append(')');
		return result.toString();
	}

} //GVFigureDescriptionImpl
