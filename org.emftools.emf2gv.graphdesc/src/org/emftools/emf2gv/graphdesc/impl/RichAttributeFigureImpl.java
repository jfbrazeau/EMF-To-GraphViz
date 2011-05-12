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
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.helper.OCLHelper;
import org.emftools.emf2gv.graphdesc.ClassFigure;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;
import org.emftools.emf2gv.graphdesc.RichAttributeFigure;
import org.emftools.emf2gv.graphdesc.util.GraphdescValidator;
import org.emftools.emf2gv.util.OCLProvider;
import org.emftools.validation.utils.EMFConstraintsHelper;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Rich Attribute Figure</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.RichAttributeFigureImpl#getEReference <em>EReference</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.RichAttributeFigureImpl#getLabelExpression <em>Label Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RichAttributeFigureImpl extends AbstractAttributeFigureImpl implements RichAttributeFigure {
	/**
	 * The cached value of the '{@link #getEReference() <em>EReference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEReference()
	 * @generated
	 * @ordered
	 */
	protected EReference eReference;

	/**
	 * The default value of the '{@link #getLabelExpression() <em>Label Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabelExpression()
	 * @generated
	 * @ordered
	 */
	protected static final String LABEL_EXPRESSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLabelExpression() <em>Label Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabelExpression()
	 * @generated
	 * @ordered
	 */
	protected String labelExpression = LABEL_EXPRESSION_EDEFAULT;

	/**
	 * OCL Helper that is used to validate the OCL Expressions.
	 */
	private OCLHelper<EClassifier, EOperation, EStructuralFeature, Constraint> oclHelper;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RichAttributeFigureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GraphdescPackage.Literals.RICH_ATTRIBUTE_FIGURE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEReference() {
		if (eReference != null && eReference.eIsProxy()) {
			InternalEObject oldEReference = (InternalEObject)eReference;
			eReference = (EReference)eResolveProxy(oldEReference);
			if (eReference != oldEReference) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GraphdescPackage.RICH_ATTRIBUTE_FIGURE__EREFERENCE, oldEReference, eReference));
			}
		}
		return eReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference basicGetEReference() {
		return eReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setEReference(EReference newEReference) {
		String oldName = getName();
		EReference oldEReference = eReference;
		eReference = newEReference;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.RICH_ATTRIBUTE_FIGURE__EREFERENCE, oldEReference, eReference));
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.RICH_ATTRIBUTE_FIGURE__NAME, oldName, getName()));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLabelExpression() {
		return labelExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabelExpression(String newLabelExpression) {
		String oldLabelExpression = labelExpression;
		labelExpression = newLabelExpression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.RICH_ATTRIBUTE_FIGURE__LABEL_EXPRESSION, oldLabelExpression, labelExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GraphdescPackage.RICH_ATTRIBUTE_FIGURE__EREFERENCE:
				if (resolve) return getEReference();
				return basicGetEReference();
			case GraphdescPackage.RICH_ATTRIBUTE_FIGURE__LABEL_EXPRESSION:
				return getLabelExpression();
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
			case GraphdescPackage.RICH_ATTRIBUTE_FIGURE__EREFERENCE:
				setEReference((EReference)newValue);
				return;
			case GraphdescPackage.RICH_ATTRIBUTE_FIGURE__LABEL_EXPRESSION:
				setLabelExpression((String)newValue);
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
			case GraphdescPackage.RICH_ATTRIBUTE_FIGURE__EREFERENCE:
				setEReference((EReference)null);
				return;
			case GraphdescPackage.RICH_ATTRIBUTE_FIGURE__LABEL_EXPRESSION:
				setLabelExpression(LABEL_EXPRESSION_EDEFAULT);
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
			case GraphdescPackage.RICH_ATTRIBUTE_FIGURE__EREFERENCE:
				return eReference != null;
			case GraphdescPackage.RICH_ATTRIBUTE_FIGURE__LABEL_EXPRESSION:
				return LABEL_EXPRESSION_EDEFAULT == null ? labelExpression != null : !LABEL_EXPRESSION_EDEFAULT.equals(labelExpression);
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
		result.append(" (labelExpression: ");
		result.append(labelExpression);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getName() {
		String result = null;
		if (getEReference() != null) {
			result = getEReference().getEType().getName();
		}
		return result;
	}

	/** (non-Javadoc)
	 * @see org.emftools.emf2gv.graphdesc.impl.AbstractReferenceFigureImpl#validate(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 * @generated NOT
	 */
	@Override
	public boolean validate(DiagnosticChain diagnostic,
			Map<Object, Object> context) {
		boolean valid = super.validate(diagnostic, context);
		if (valid) {
			ClassFigure classFigure = getClassFigure();
			EMFConstraintsHelper constraintsHelper = EMFConstraintsHelper
					.getInstance(GraphdescValidator.DIAGNOSTIC_SOURCE);
			EReference eReference = getEReference();
			if (eReference == null) {
				constraintsHelper
						.addError(diagnostic, this, 0,
								"The rich attribute figure must be associated to an EReference");
				valid = false;
			}
			else {
				EClass eClass = classFigure.getEClass();
				if (eClass != null) {
					if (!eClass.getEAllReferences().contains(eReference)) {
						constraintsHelper
								.addError(
										diagnostic,
										this,
										0,
										"The rich attribute figure is associated to an EReference ({0}) that is not a member of the parent class figure''s EClass ({1})",
										eReference.getName(),
										eClass.getName());
						valid = false;
					}
				}
				if (valid) {
					// Checks that the EReference is not already used
					if (GraphdescValidator.eReferenceIsUsedTwiceOrMore(this)) {
						constraintsHelper
								.addError(
										diagnostic,
										this,
										0,
										"The rich attribute figure is associated to an EReference ({0}) that is already used by another figure.",
										eReference.getName());
						valid = false;
					}
				}
				if (valid) {
					// Lazy instanciation of the OCL Helper
					if (oclHelper == null) {
						oclHelper = OCLProvider.newOCL().createOCLHelper();
					}
					oclHelper.setContext(eReference.getEType());
					try {
						oclHelper
								.createQuery(getLabelExpression());
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

} //RichAttributeFigureImpl
