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

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.helper.OCLHelper;
import org.emftools.emf2gv.graphdesc.AbstractReferenceFigure;
import org.emftools.emf2gv.graphdesc.ClassFigure;
import org.emftools.emf2gv.graphdesc.FontStyle;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;
import org.emftools.emf2gv.graphdesc.ReferenceFigure;
import org.emftools.emf2gv.graphdesc.RichReferenceFigure;
import org.emftools.emf2gv.graphdesc.util.GraphdescValidator;
import org.emftools.emf2gv.util.OCLProvider;
import org.emftools.validation.utils.EMFConstraintsHelper;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Rich Reference Figure</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.RichReferenceFigureImpl#getTargetEReference <em>Target EReference</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.RichReferenceFigureImpl#getSourceLabelExpression <em>Source Label Expression</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.RichReferenceFigureImpl#getSourceLabelStyle <em>Source Label Style</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.RichReferenceFigureImpl#getStandardLabelExpression <em>Standard Label Expression</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.RichReferenceFigureImpl#getStandardLabelStyle <em>Standard Label Style</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.RichReferenceFigureImpl#getTargetLabelExpression <em>Target Label Expression</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.RichReferenceFigureImpl#getTargetLabelStyle <em>Target Label Style</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.RichReferenceFigureImpl#getLabelDistance <em>Label Distance</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.RichReferenceFigureImpl#getLabelAngle <em>Label Angle</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RichReferenceFigureImpl extends AbstractReferenceFigureImpl implements RichReferenceFigure {

	/**
	 * OCL Helper that is used to validate the OCL Expressions.
	 */
	private OCLHelper<EClassifier, EOperation, EStructuralFeature, Constraint> oclHelper;
	
	/**
	 * The cached value of the '{@link #getTargetEReference() <em>Target EReference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetEReference()
	 * @generated
	 * @ordered
	 */
	protected EReference targetEReference;

	/**
	 * The default value of the '{@link #getSourceLabelExpression() <em>Source Label Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceLabelExpression()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_LABEL_EXPRESSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSourceLabelExpression() <em>Source Label Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceLabelExpression()
	 * @generated
	 * @ordered
	 */
	protected String sourceLabelExpression = SOURCE_LABEL_EXPRESSION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSourceLabelStyle() <em>Source Label Style</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceLabelStyle()
	 * @generated
	 * @ordered
	 */
	protected EList<FontStyle> sourceLabelStyle;

	/**
	 * The default value of the '{@link #getStandardLabelExpression() <em>Standard Label Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStandardLabelExpression()
	 * @generated
	 * @ordered
	 */
	protected static final String STANDARD_LABEL_EXPRESSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStandardLabelExpression() <em>Standard Label Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStandardLabelExpression()
	 * @generated
	 * @ordered
	 */
	protected String standardLabelExpression = STANDARD_LABEL_EXPRESSION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getStandardLabelStyle() <em>Standard Label Style</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStandardLabelStyle()
	 * @generated
	 * @ordered
	 */
	protected EList<FontStyle> standardLabelStyle;

	/**
	 * The default value of the '{@link #getTargetLabelExpression() <em>Target Label Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetLabelExpression()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGET_LABEL_EXPRESSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTargetLabelExpression() <em>Target Label Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetLabelExpression()
	 * @generated
	 * @ordered
	 */
	protected String targetLabelExpression = TARGET_LABEL_EXPRESSION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTargetLabelStyle() <em>Target Label Style</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetLabelStyle()
	 * @generated
	 * @ordered
	 */
	protected EList<FontStyle> targetLabelStyle;

	/**
	 * The default value of the '{@link #getLabelDistance() <em>Label Distance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabelDistance()
	 * @generated
	 * @ordered
	 */
	protected static final double LABEL_DISTANCE_EDEFAULT = 5.0;

	/**
	 * The cached value of the '{@link #getLabelDistance() <em>Label Distance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabelDistance()
	 * @generated
	 * @ordered
	 */
	protected double labelDistance = LABEL_DISTANCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getLabelAngle() <em>Label Angle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabelAngle()
	 * @generated
	 * @ordered
	 */
	protected static final double LABEL_ANGLE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getLabelAngle() <em>Label Angle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabelAngle()
	 * @generated
	 * @ordered
	 */
	protected double labelAngle = LABEL_ANGLE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RichReferenceFigureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GraphdescPackage.Literals.RICH_REFERENCE_FIGURE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTargetEReference() {
		if (targetEReference != null && targetEReference.eIsProxy()) {
			InternalEObject oldTargetEReference = (InternalEObject)targetEReference;
			targetEReference = (EReference)eResolveProxy(oldTargetEReference);
			if (targetEReference != oldTargetEReference) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GraphdescPackage.RICH_REFERENCE_FIGURE__TARGET_EREFERENCE, oldTargetEReference, targetEReference));
			}
		}
		return targetEReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference basicGetTargetEReference() {
		return targetEReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setTargetEReference(EReference newTargetEReference) {
		String oldName = getName();
		EReference oldTargetEReference = targetEReference;
		targetEReference = newTargetEReference;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.RICH_REFERENCE_FIGURE__TARGET_EREFERENCE, oldTargetEReference, targetEReference));
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.RICH_REFERENCE_FIGURE__NAME, oldName, getName()));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSourceLabelExpression() {
		return sourceLabelExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceLabelExpression(String newSourceLabelExpression) {
		String oldSourceLabelExpression = sourceLabelExpression;
		sourceLabelExpression = newSourceLabelExpression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.RICH_REFERENCE_FIGURE__SOURCE_LABEL_EXPRESSION, oldSourceLabelExpression, sourceLabelExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FontStyle> getSourceLabelStyle() {
		if (sourceLabelStyle == null) {
			sourceLabelStyle = new EDataTypeUniqueEList<FontStyle>(FontStyle.class, this, GraphdescPackage.RICH_REFERENCE_FIGURE__SOURCE_LABEL_STYLE);
		}
		return sourceLabelStyle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStandardLabelExpression() {
		return standardLabelExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStandardLabelExpression(String newStandardLabelExpression) {
		String oldStandardLabelExpression = standardLabelExpression;
		standardLabelExpression = newStandardLabelExpression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.RICH_REFERENCE_FIGURE__STANDARD_LABEL_EXPRESSION, oldStandardLabelExpression, standardLabelExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FontStyle> getStandardLabelStyle() {
		if (standardLabelStyle == null) {
			standardLabelStyle = new EDataTypeUniqueEList<FontStyle>(FontStyle.class, this, GraphdescPackage.RICH_REFERENCE_FIGURE__STANDARD_LABEL_STYLE);
		}
		return standardLabelStyle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTargetLabelExpression() {
		return targetLabelExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetLabelExpression(String newTargetLabelExpression) {
		String oldTargetLabelExpression = targetLabelExpression;
		targetLabelExpression = newTargetLabelExpression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.RICH_REFERENCE_FIGURE__TARGET_LABEL_EXPRESSION, oldTargetLabelExpression, targetLabelExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FontStyle> getTargetLabelStyle() {
		if (targetLabelStyle == null) {
			targetLabelStyle = new EDataTypeUniqueEList<FontStyle>(FontStyle.class, this, GraphdescPackage.RICH_REFERENCE_FIGURE__TARGET_LABEL_STYLE);
		}
		return targetLabelStyle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getLabelDistance() {
		return labelDistance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabelDistance(double newLabelDistance) {
		double oldLabelDistance = labelDistance;
		labelDistance = newLabelDistance;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.RICH_REFERENCE_FIGURE__LABEL_DISTANCE, oldLabelDistance, labelDistance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getLabelAngle() {
		return labelAngle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabelAngle(double newLabelAngle) {
		double oldLabelAngle = labelAngle;
		labelAngle = newLabelAngle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.RICH_REFERENCE_FIGURE__LABEL_ANGLE, oldLabelAngle, labelAngle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GraphdescPackage.RICH_REFERENCE_FIGURE__TARGET_EREFERENCE:
				if (resolve) return getTargetEReference();
				return basicGetTargetEReference();
			case GraphdescPackage.RICH_REFERENCE_FIGURE__SOURCE_LABEL_EXPRESSION:
				return getSourceLabelExpression();
			case GraphdescPackage.RICH_REFERENCE_FIGURE__SOURCE_LABEL_STYLE:
				return getSourceLabelStyle();
			case GraphdescPackage.RICH_REFERENCE_FIGURE__STANDARD_LABEL_EXPRESSION:
				return getStandardLabelExpression();
			case GraphdescPackage.RICH_REFERENCE_FIGURE__STANDARD_LABEL_STYLE:
				return getStandardLabelStyle();
			case GraphdescPackage.RICH_REFERENCE_FIGURE__TARGET_LABEL_EXPRESSION:
				return getTargetLabelExpression();
			case GraphdescPackage.RICH_REFERENCE_FIGURE__TARGET_LABEL_STYLE:
				return getTargetLabelStyle();
			case GraphdescPackage.RICH_REFERENCE_FIGURE__LABEL_DISTANCE:
				return getLabelDistance();
			case GraphdescPackage.RICH_REFERENCE_FIGURE__LABEL_ANGLE:
				return getLabelAngle();
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
			case GraphdescPackage.RICH_REFERENCE_FIGURE__TARGET_EREFERENCE:
				setTargetEReference((EReference)newValue);
				return;
			case GraphdescPackage.RICH_REFERENCE_FIGURE__SOURCE_LABEL_EXPRESSION:
				setSourceLabelExpression((String)newValue);
				return;
			case GraphdescPackage.RICH_REFERENCE_FIGURE__SOURCE_LABEL_STYLE:
				getSourceLabelStyle().clear();
				getSourceLabelStyle().addAll((Collection<? extends FontStyle>)newValue);
				return;
			case GraphdescPackage.RICH_REFERENCE_FIGURE__STANDARD_LABEL_EXPRESSION:
				setStandardLabelExpression((String)newValue);
				return;
			case GraphdescPackage.RICH_REFERENCE_FIGURE__STANDARD_LABEL_STYLE:
				getStandardLabelStyle().clear();
				getStandardLabelStyle().addAll((Collection<? extends FontStyle>)newValue);
				return;
			case GraphdescPackage.RICH_REFERENCE_FIGURE__TARGET_LABEL_EXPRESSION:
				setTargetLabelExpression((String)newValue);
				return;
			case GraphdescPackage.RICH_REFERENCE_FIGURE__TARGET_LABEL_STYLE:
				getTargetLabelStyle().clear();
				getTargetLabelStyle().addAll((Collection<? extends FontStyle>)newValue);
				return;
			case GraphdescPackage.RICH_REFERENCE_FIGURE__LABEL_DISTANCE:
				setLabelDistance((Double)newValue);
				return;
			case GraphdescPackage.RICH_REFERENCE_FIGURE__LABEL_ANGLE:
				setLabelAngle((Double)newValue);
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
			case GraphdescPackage.RICH_REFERENCE_FIGURE__TARGET_EREFERENCE:
				setTargetEReference((EReference)null);
				return;
			case GraphdescPackage.RICH_REFERENCE_FIGURE__SOURCE_LABEL_EXPRESSION:
				setSourceLabelExpression(SOURCE_LABEL_EXPRESSION_EDEFAULT);
				return;
			case GraphdescPackage.RICH_REFERENCE_FIGURE__SOURCE_LABEL_STYLE:
				getSourceLabelStyle().clear();
				return;
			case GraphdescPackage.RICH_REFERENCE_FIGURE__STANDARD_LABEL_EXPRESSION:
				setStandardLabelExpression(STANDARD_LABEL_EXPRESSION_EDEFAULT);
				return;
			case GraphdescPackage.RICH_REFERENCE_FIGURE__STANDARD_LABEL_STYLE:
				getStandardLabelStyle().clear();
				return;
			case GraphdescPackage.RICH_REFERENCE_FIGURE__TARGET_LABEL_EXPRESSION:
				setTargetLabelExpression(TARGET_LABEL_EXPRESSION_EDEFAULT);
				return;
			case GraphdescPackage.RICH_REFERENCE_FIGURE__TARGET_LABEL_STYLE:
				getTargetLabelStyle().clear();
				return;
			case GraphdescPackage.RICH_REFERENCE_FIGURE__LABEL_DISTANCE:
				setLabelDistance(LABEL_DISTANCE_EDEFAULT);
				return;
			case GraphdescPackage.RICH_REFERENCE_FIGURE__LABEL_ANGLE:
				setLabelAngle(LABEL_ANGLE_EDEFAULT);
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
			case GraphdescPackage.RICH_REFERENCE_FIGURE__TARGET_EREFERENCE:
				return targetEReference != null;
			case GraphdescPackage.RICH_REFERENCE_FIGURE__SOURCE_LABEL_EXPRESSION:
				return SOURCE_LABEL_EXPRESSION_EDEFAULT == null ? sourceLabelExpression != null : !SOURCE_LABEL_EXPRESSION_EDEFAULT.equals(sourceLabelExpression);
			case GraphdescPackage.RICH_REFERENCE_FIGURE__SOURCE_LABEL_STYLE:
				return sourceLabelStyle != null && !sourceLabelStyle.isEmpty();
			case GraphdescPackage.RICH_REFERENCE_FIGURE__STANDARD_LABEL_EXPRESSION:
				return STANDARD_LABEL_EXPRESSION_EDEFAULT == null ? standardLabelExpression != null : !STANDARD_LABEL_EXPRESSION_EDEFAULT.equals(standardLabelExpression);
			case GraphdescPackage.RICH_REFERENCE_FIGURE__STANDARD_LABEL_STYLE:
				return standardLabelStyle != null && !standardLabelStyle.isEmpty();
			case GraphdescPackage.RICH_REFERENCE_FIGURE__TARGET_LABEL_EXPRESSION:
				return TARGET_LABEL_EXPRESSION_EDEFAULT == null ? targetLabelExpression != null : !TARGET_LABEL_EXPRESSION_EDEFAULT.equals(targetLabelExpression);
			case GraphdescPackage.RICH_REFERENCE_FIGURE__TARGET_LABEL_STYLE:
				return targetLabelStyle != null && !targetLabelStyle.isEmpty();
			case GraphdescPackage.RICH_REFERENCE_FIGURE__LABEL_DISTANCE:
				return labelDistance != LABEL_DISTANCE_EDEFAULT;
			case GraphdescPackage.RICH_REFERENCE_FIGURE__LABEL_ANGLE:
				return labelAngle != LABEL_ANGLE_EDEFAULT;
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
		result.append(" (sourceLabelExpression: ");
		result.append(sourceLabelExpression);
		result.append(", sourceLabelStyle: ");
		result.append(sourceLabelStyle);
		result.append(", standardLabelExpression: ");
		result.append(standardLabelExpression);
		result.append(", standardLabelStyle: ");
		result.append(standardLabelStyle);
		result.append(", targetLabelExpression: ");
		result.append(targetLabelExpression);
		result.append(", targetLabelStyle: ");
		result.append(targetLabelStyle);
		result.append(", labelDistance: ");
		result.append(labelDistance);
		result.append(", labelAngle: ");
		result.append(labelAngle);
		result.append(')');
		return result.toString();
	}

	/**
	 * @generated NOT
	 */
	@Override
	public String getName() {
		StringWriter buf = new StringWriter();
		buf.append(getEReference() != null ? getEReference().getName()
				: "<unset>");
		buf.append('.');
		buf.append(getTargetEReference() != null ? getTargetEReference()
				.getName() : "<unset>");
		return buf.toString();
	}

	/**
	 * @generated NOT
	 */
	@Override
	public boolean isContainment() {
		return (getEReference() != null) && (getEReference().isContainment())
				&& (getTargetEReference() != null)
				&& (getTargetEReference().isContainment());
	}

	/**
	 * @generated NOT
	 */
	@Override
	public EClass basicGetTargetEType() {
		EClass result = null;
		if (getTargetEReference() != null) {
			result = getTargetEReference().getEReferenceType();
		}
		return result;
	}

	/**
	 * @generated NOT
	 */
	@Override
	public boolean validate(DiagnosticChain diagnostic,
			Map<Object, Object> context) {
		boolean valid = super.validate(diagnostic, context);
		if (valid) {
			EMFConstraintsHelper constraintsHelper = EMFConstraintsHelper
					.getInstance(GraphdescValidator.DIAGNOSTIC_SOURCE);
			ClassFigure classFigure = getClassFigure();
			List<ReferenceFigure> referenceFigures = new ArrayList<ReferenceFigure>();
			List<RichReferenceFigure> richReferenceFigures = new ArrayList<RichReferenceFigure>();
			for (AbstractReferenceFigure referenceFigure : classFigure
					.getReferenceFigures()) {
				boolean isRichReference = referenceFigure instanceof RichReferenceFigure;
				if (isRichReference) {
					if (!richReferenceFigures.contains(referenceFigure))
						richReferenceFigures
								.add((RichReferenceFigure) referenceFigure);
				} else {
					if (!referenceFigures.contains(referenceFigure))
						referenceFigures.add((ReferenceFigure) referenceFigure);
				}
			}

			EReference targetEReference = getTargetEReference();
			// EReference Check
			if (!getEReference().isContainment()) {
				constraintsHelper
						.addError(
								diagnostic,
								this,
								0,
								"The rich reference figure is associated to an EReference ({0}) that is not a containment reference",
								getEReference().getName());
				valid = false;
			}
			// Target EReference Check
			else if (targetEReference == null) {
				constraintsHelper
						.addError(diagnostic, this, 0,
								"The rich reference figure must be associated to a target EReference");
				valid = false;
			} else {
				// Check unique rich reference
				for (RichReferenceFigure richReferenceFigure : richReferenceFigures) {
					// A test is made in order not to create a marker for
					// the first occurrence of the same { ref, target ref }
					// couple
					if (richReferenceFigure == this) {
						break;
					} else if (getEReference().equals(
							richReferenceFigure.getEReference())
							&& getTargetEReference().equals(
									richReferenceFigure.getTargetEReference())) {
						constraintsHelper
								.addError(
										diagnostic,
										this,
										0,
										"The rich reference uses a [EReference, Target EReference] couple that is already used");
						valid = false;
						break;

					}
				}

				if (valid) {
					// Lazy instanciation of the OCL Helper
					if (oclHelper == null) {
						oclHelper = OCLProvider.newOCL().createOCLHelper();
					}
					oclHelper.setContext(getEReference().getEReferenceType());
					try {
						oclHelper
								.createQuery(getSourceLabelExpression());
					} catch (ParserException ex) {
						constraintsHelper.addError(diagnostic, this, 0,
								"The OCL expression of the source label is invalid : {0}",
								ex.getMessage());
						valid = false;
					}
					try {
						oclHelper
								.createQuery(getStandardLabelExpression());
					} catch (ParserException ex) {
						constraintsHelper.addError(diagnostic, this, 0,
								"The OCL expression of the standard label is invalid : {0}",
								ex.getMessage());
						valid = false;
					}
					try {
						oclHelper
								.createQuery(getTargetLabelExpression());
					} catch (ParserException ex) {
						constraintsHelper.addError(diagnostic, this, 0,
								"The OCL expression of the target label is invalid : {0}",
								ex.getMessage());
						valid = false;
					}
				}
			}

		}
		return valid;
	}

} //RichReferenceFigureImpl
