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
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.helper.OCLHelper;
import org.emftools.emf2gv.graphdesc.AbstractFigure;
import org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;
import org.emftools.emf2gv.graphdesc.util.GraphdescHelper;
import org.emftools.emf2gv.graphdesc.util.GraphdescValidator;
import org.emftools.emf2gv.util.OCLProvider;
import org.emftools.validation.utils.EMFConstraintsHelper;

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
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.DynamicPropertyOverriderImpl#getFigure <em>Figure</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.DynamicPropertyOverriderImpl#isEnabled <em>Enabled</em>}</li>
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
	protected EStructuralFeature propertyToOverride;

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

	/** The OCL */
	private OCL ocl;
	
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
	public EStructuralFeature getPropertyToOverride() {
		if (propertyToOverride != null && propertyToOverride.eIsProxy()) {
			InternalEObject oldPropertyToOverride = (InternalEObject)propertyToOverride;
			propertyToOverride = (EStructuralFeature)eResolveProxy(oldPropertyToOverride);
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
	public EStructuralFeature basicGetPropertyToOverride() {
		return propertyToOverride;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setPropertyToOverride(EStructuralFeature newPropertyToOverride) {
		String oldName = getName();
		EStructuralFeature oldPropertyToOverride = propertyToOverride;
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
	public AbstractFigure getFigure() {
		if (eContainerFeatureID() != GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__FIGURE) return null;
		return (AbstractFigure)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFigure(AbstractFigure newFigure, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newFigure, GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__FIGURE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFigure(AbstractFigure newFigure) {
		if (newFigure != eInternalContainer() || (eContainerFeatureID() != GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__FIGURE && newFigure != null)) {
			if (EcoreUtil.isAncestor(this, newFigure))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newFigure != null)
				msgs = ((InternalEObject)newFigure).eInverseAdd(this, GraphdescPackage.ABSTRACT_FIGURE__DYNAMIC_PROPERTIES, AbstractFigure.class, msgs);
			msgs = basicSetFigure(newFigure, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__FIGURE, newFigure, newFigure));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__ENABLED, oldEnabled, enabled));
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
		AbstractFigure figure = getFigure();
		// The overrider is affected to a figure ?
		if (figure == null) {
			constraintsHelper
					.addError(diagnostic, this, 0,
							"The dynamic property overrider must be contained in a figure");
			valid = false;
		}
		else {
			// Does the feature to override belong to the parent figure EClass ?
			EStructuralFeature featureToOverride = getPropertyToOverride();
			if (featureToOverride == null) {
				constraintsHelper.addError(diagnostic, this, 0,
						"The property to override must be set");
				valid = false;
			}
			else {
				EClass figureEClass = figure.eClass();
				if (!figureEClass.getEAllStructuralFeatures().contains(
						featureToOverride)) {
					constraintsHelper
							.addError(
									diagnostic,
									this,
									0,
									"The dynamic property overrider is associated to a property ({0}) that does not belong to the figure EClass ({1})",
									featureToOverride.getName(),
									figureEClass.getName());
					valid = false;
				}
				if (valid) {
					for (DynamicPropertyOverrider dpoCursor : figure
							.getDynamicProperties()) {
						// If we meet the current instance we stop (in order not
						// to get duplicate error messages)
						if (dpoCursor == this) {
							break;
						}
						else if (featureToOverride == dpoCursor
										.getPropertyToOverride()) {
							constraintsHelper
									.addError(
											diagnostic,
											this,
											0,
											"The property ''{0}'' is overrided twice or more",
											featureToOverride.getName());
							valid = false;
						}
					}
				}
			}
			// OCL expression check
			if (valid) {
				// Lazy instantiation of the OCL Helper
				if (ocl == null) {
					ocl = OCLProvider.newOCL();
					oclHelper = ocl.createOCLHelper();
				}
				// Default variable registration
				GraphdescHelper.registerDefaultPropertyValueOCLVariable(
						ocl.getEnvironment(), this);
				// OCL expression parsing
				OCLExpression<EClassifier> oclExpression = null;
				EClass oclContext = figure
						.getStandardOCLContext();
				if (oclContext != null) {
					oclHelper.setContext(oclContext);
					try {
						oclExpression = oclHelper
								.createQuery(getOverridingExpression());
					} catch (ParserException ex) {
						constraintsHelper.addError(diagnostic, this, 0,
								"The OCL expression is invalid : {0}",
								ex.getMessage());
						valid = false;
					}
				}
				
				// Return type check
				if (oclExpression != null) {
					EClassifier oclExpressionEType = oclExpression.getType();
					EClassifier featureToOverrideEType = org.emftools.emf2gv.util.OCLHelper
							.toOCLFeatureType(oclHelper.getEnvironment(),
									featureToOverride);
					if (!org.emftools.emf2gv.util.OCLHelper.compatibleTypeMatch(
							oclHelper.getEnvironment(), oclExpressionEType,
							featureToOverrideEType)) {
						constraintsHelper
								.addError(
										diagnostic,
										this,
										0,
										"The OCL expression return type does not correspond to the overrided property type (found ''{0}'' instead of ''{1}'')",
										oclExpressionEType.getName(),
										featureToOverrideEType.getName());
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
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__FIGURE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetFigure((AbstractFigure)otherEnd, msgs);
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
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__FIGURE:
				return basicSetFigure(null, msgs);
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
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__FIGURE:
				return eInternalContainer().eInverseRemove(this, GraphdescPackage.ABSTRACT_FIGURE__DYNAMIC_PROPERTIES, AbstractFigure.class, msgs);
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
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__NAME:
				return getName();
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__PROPERTY_TO_OVERRIDE:
				if (resolve) return getPropertyToOverride();
				return basicGetPropertyToOverride();
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__OVERRIDING_EXPRESSION:
				return getOverridingExpression();
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__FIGURE:
				return getFigure();
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__ENABLED:
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
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__PROPERTY_TO_OVERRIDE:
				setPropertyToOverride((EStructuralFeature)newValue);
				return;
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__OVERRIDING_EXPRESSION:
				setOverridingExpression((String)newValue);
				return;
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__FIGURE:
				setFigure((AbstractFigure)newValue);
				return;
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__ENABLED:
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
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__PROPERTY_TO_OVERRIDE:
				setPropertyToOverride((EStructuralFeature)null);
				return;
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__OVERRIDING_EXPRESSION:
				setOverridingExpression(OVERRIDING_EXPRESSION_EDEFAULT);
				return;
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__FIGURE:
				setFigure((AbstractFigure)null);
				return;
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__ENABLED:
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
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__NAME:
				return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__PROPERTY_TO_OVERRIDE:
				return propertyToOverride != null;
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__OVERRIDING_EXPRESSION:
				return OVERRIDING_EXPRESSION_EDEFAULT == null ? overridingExpression != null : !OVERRIDING_EXPRESSION_EDEFAULT.equals(overridingExpression);
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__FIGURE:
				return getFigure() != null;
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER__ENABLED:
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
		result.append(" (overridingExpression: ");
		result.append(overridingExpression);
		result.append(", enabled: ");
		result.append(enabled);
		result.append(')');
		return result.toString();
	}

} //DynamicPropertyOverriderImpl
