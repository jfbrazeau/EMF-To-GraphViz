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

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.helper.OCLHelper;
import org.emftools.emf2gv.graphdesc.Filter;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;
import org.emftools.emf2gv.graphdesc.util.GraphdescHelper;
import org.emftools.emf2gv.graphdesc.util.GraphdescValidator;
import org.emftools.emf2gv.util.OCLProvider;
import org.emftools.validation.utils.EMFConstraintsHelper;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Filter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.FilterImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.FilterImpl#getFilteredType <em>Filtered Type</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.FilterImpl#getFilterExpression <em>Filter Expression</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.FilterImpl#getFigureDescription <em>Figure Description</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.FilterImpl#isEnabled <em>Enabled</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FilterImpl extends EObjectImpl implements Filter {
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
	 * The cached value of the '{@link #getFilteredType() <em>Filtered Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilteredType()
	 * @generated
	 * @ordered
	 */
	protected EClass filteredType;

	/**
	 * The default value of the '{@link #getFilterExpression() <em>Filter Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilterExpression()
	 * @generated
	 * @ordered
	 */
	protected static final String FILTER_EXPRESSION_EDEFAULT = "true";

	/**
	 * The cached value of the '{@link #getFilterExpression() <em>Filter Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilterExpression()
	 * @generated
	 * @ordered
	 */
	protected String filterExpression = FILTER_EXPRESSION_EDEFAULT;

	/**
	 * The default value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENABLED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected boolean enabled = ENABLED_EDEFAULT;

	/**
	 * OCL Helper that is used to validate the OCL Expressions.
	 */
	private OCLHelper<EClassifier, EOperation, EStructuralFeature, Constraint> oclHelper;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FilterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GraphdescPackage.Literals.FILTER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getName() {
		return getFilteredType() != null ? getFilteredType().getName() : null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFilteredType() {
		if (filteredType != null && filteredType.eIsProxy()) {
			InternalEObject oldFilteredType = (InternalEObject)filteredType;
			filteredType = (EClass)eResolveProxy(oldFilteredType);
			if (filteredType != oldFilteredType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GraphdescPackage.FILTER__FILTERED_TYPE, oldFilteredType, filteredType));
			}
		}
		return filteredType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass basicGetFilteredType() {
		return filteredType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setFilteredType(EClass newFilteredType) {
		String oldName = getName();
		EClass oldFilteredType = filteredType;
		filteredType = newFilteredType;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.FILTER__FILTERED_TYPE, oldFilteredType, filteredType));
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.FILTER__NAME, oldName, getName()));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFilterExpression() {
		return filterExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFilterExpression(String newFilterExpression) {
		String oldFilterExpression = filterExpression;
		filterExpression = newFilterExpression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.FILTER__FILTER_EXPRESSION, oldFilterExpression, filterExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GVFigureDescription getFigureDescription() {
		if (eContainerFeatureID() != GraphdescPackage.FILTER__FIGURE_DESCRIPTION) return null;
		return (GVFigureDescription)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFigureDescription(GVFigureDescription newFigureDescription, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newFigureDescription, GraphdescPackage.FILTER__FIGURE_DESCRIPTION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFigureDescription(GVFigureDescription newFigureDescription) {
		if (newFigureDescription != eInternalContainer() || (eContainerFeatureID() != GraphdescPackage.FILTER__FIGURE_DESCRIPTION && newFigureDescription != null)) {
			if (EcoreUtil.isAncestor(this, newFigureDescription))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newFigureDescription != null)
				msgs = ((InternalEObject)newFigureDescription).eInverseAdd(this, GraphdescPackage.GV_FIGURE_DESCRIPTION__FILTERS, GVFigureDescription.class, msgs);
			msgs = basicSetFigureDescription(newFigureDescription, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.FILTER__FIGURE_DESCRIPTION, newFigureDescription, newFigureDescription));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnabled(boolean newEnabled) {
		boolean oldEnabled = enabled;
		enabled = newEnabled;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.FILTER__ENABLED, oldEnabled, enabled));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validate(DiagnosticChain diagnostic, Map<Object, Object> context) {
		EMFConstraintsHelper constraintsHelper = EMFConstraintsHelper
				.getInstance(GraphdescValidator.DIAGNOSTIC_SOURCE);
		boolean valid = true;
		GVFigureDescription figureDesc = getFigureDescription();
		if (figureDesc == null) {
			constraintsHelper.addError(diagnostic, this, 0,
					"The filter must be contained in a graphical description");
			valid = false;
		} else {
			if (getFilteredType() == null) {
				constraintsHelper.addError(diagnostic, this, 0,
						"The filtered type must be set");
				valid = false;
			} else {
				List<EClass> authorizedEClasses = GraphdescHelper
						.getFilterableEClasses(figureDesc);
				if (!authorizedEClasses.contains(getFilteredType())) {
					constraintsHelper
							.addWarning(
									diagnostic,
									this,
									0,
									"The filtered type ''{0}''has no corresponding ClassFigure",
									getFilteredType().getName());
					// We only return warnings at this point, the instance keeps valid 
					//valid = false;
				} else if (getFilterExpression() == null
						|| "".equals(getFilterExpression().trim())) {
					constraintsHelper.addError(diagnostic, this, 0,
							"The filter expression must be set",
							getFilteredType().getName());
					valid = false;
				} else {
					// Lazy instantiation of the OCL Helper
					if (oclHelper == null) {
						oclHelper = OCLProvider.newOCL().createOCLHelper();
					}
					try {
						oclHelper.setContext(getFilteredType());
						oclHelper.createInvariant(getFilterExpression());
					} catch (ParserException ex) {
						constraintsHelper.addError(diagnostic, this, 0,
								"The OCL expression is invalid : {0}",
								ex.getMessage());
						valid = false;
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GraphdescPackage.FILTER__FIGURE_DESCRIPTION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetFigureDescription((GVFigureDescription)otherEnd, msgs);
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
			case GraphdescPackage.FILTER__FIGURE_DESCRIPTION:
				return basicSetFigureDescription(null, msgs);
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
			case GraphdescPackage.FILTER__FIGURE_DESCRIPTION:
				return eInternalContainer().eInverseRemove(this, GraphdescPackage.GV_FIGURE_DESCRIPTION__FILTERS, GVFigureDescription.class, msgs);
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
			case GraphdescPackage.FILTER__NAME:
				return getName();
			case GraphdescPackage.FILTER__FILTERED_TYPE:
				if (resolve) return getFilteredType();
				return basicGetFilteredType();
			case GraphdescPackage.FILTER__FILTER_EXPRESSION:
				return getFilterExpression();
			case GraphdescPackage.FILTER__FIGURE_DESCRIPTION:
				return getFigureDescription();
			case GraphdescPackage.FILTER__ENABLED:
				return isEnabled();
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
			case GraphdescPackage.FILTER__FILTERED_TYPE:
				setFilteredType((EClass)newValue);
				return;
			case GraphdescPackage.FILTER__FILTER_EXPRESSION:
				setFilterExpression((String)newValue);
				return;
			case GraphdescPackage.FILTER__FIGURE_DESCRIPTION:
				setFigureDescription((GVFigureDescription)newValue);
				return;
			case GraphdescPackage.FILTER__ENABLED:
				setEnabled((Boolean)newValue);
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
			case GraphdescPackage.FILTER__FILTERED_TYPE:
				setFilteredType((EClass)null);
				return;
			case GraphdescPackage.FILTER__FILTER_EXPRESSION:
				setFilterExpression(FILTER_EXPRESSION_EDEFAULT);
				return;
			case GraphdescPackage.FILTER__FIGURE_DESCRIPTION:
				setFigureDescription((GVFigureDescription)null);
				return;
			case GraphdescPackage.FILTER__ENABLED:
				setEnabled(ENABLED_EDEFAULT);
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
			case GraphdescPackage.FILTER__NAME:
				return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
			case GraphdescPackage.FILTER__FILTERED_TYPE:
				return filteredType != null;
			case GraphdescPackage.FILTER__FILTER_EXPRESSION:
				return FILTER_EXPRESSION_EDEFAULT == null ? filterExpression != null : !FILTER_EXPRESSION_EDEFAULT.equals(filterExpression);
			case GraphdescPackage.FILTER__FIGURE_DESCRIPTION:
				return getFigureDescription() != null;
			case GraphdescPackage.FILTER__ENABLED:
				return enabled != ENABLED_EDEFAULT;
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
		result.append(" (filterExpression: ");
		result.append(filterExpression);
		result.append(", enabled: ");
		result.append(enabled);
		result.append(')');
		return result.toString();
	}

} //FilterImpl
