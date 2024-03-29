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

import java.awt.Color;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftools.emf2gv.graphdesc.AbstractReferenceFigure;
import org.emftools.emf2gv.graphdesc.ArrowType;
import org.emftools.emf2gv.graphdesc.ClassFigure;
import org.emftools.emf2gv.graphdesc.EdgeStyle;
import org.emftools.emf2gv.graphdesc.GraphdescFactory;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;
import org.emftools.emf2gv.graphdesc.ReferenceFigure;
import org.emftools.emf2gv.graphdesc.RichReferenceFigure;
import org.emftools.emf2gv.graphdesc.util.GraphdescValidator;
import org.emftools.validation.utils.EMFConstraintsHelper;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Reference Figure</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.AbstractReferenceFigureImpl#getClassFigure <em>Class Figure</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.AbstractReferenceFigureImpl#getEReference <em>EReference</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.AbstractReferenceFigureImpl#getTargetArrowType <em>Target Arrow Type</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.AbstractReferenceFigureImpl#getSourceArrowType <em>Source Arrow Type</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.AbstractReferenceFigureImpl#isContainment <em>Containment</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.AbstractReferenceFigureImpl#getCustomTargetArrow <em>Custom Target Arrow</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.AbstractReferenceFigureImpl#getCustomSourceArrow <em>Custom Source Arrow</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.AbstractReferenceFigureImpl#getColor <em>Color</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.AbstractReferenceFigureImpl#getStyle <em>Style</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.AbstractReferenceFigureImpl#getTargetEType <em>Target EType</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.AbstractReferenceFigureImpl#getMinimumEdgeLength <em>Minimum Edge Length</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractReferenceFigureImpl extends AbstractFigureImpl implements AbstractReferenceFigure {

	/**
	 * Regular expression that is used to check the custom arrow types
	 * @generated NOT
	 */ 
	private static final String CUSTOM_AROW_TYPE_REGEXP = "(o?[lr]?(box|crow|diamond|dot|inv|none|normal|tee|vee))+";
	
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
	 * The default value of the '{@link #getTargetArrowType() <em>Target Arrow Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetArrowType()
	 * @generated
	 * @ordered
	 */
	protected static final ArrowType TARGET_ARROW_TYPE_EDEFAULT = ArrowType.NORMAL;

	/**
	 * The cached value of the '{@link #getTargetArrowType() <em>Target Arrow Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetArrowType()
	 * @generated
	 * @ordered
	 */
	protected ArrowType targetArrowType = TARGET_ARROW_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSourceArrowType() <em>Source Arrow Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceArrowType()
	 * @generated
	 * @ordered
	 */
	protected static final ArrowType SOURCE_ARROW_TYPE_EDEFAULT = ArrowType.NONE;

	/**
	 * The cached value of the '{@link #getSourceArrowType() <em>Source Arrow Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceArrowType()
	 * @generated
	 * @ordered
	 */
	protected ArrowType sourceArrowType = SOURCE_ARROW_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #isContainment() <em>Containment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isContainment()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONTAINMENT_EDEFAULT = false;

	/**
	 * The default value of the '{@link #getCustomTargetArrow() <em>Custom Target Arrow</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCustomTargetArrow()
	 * @generated
	 * @ordered
	 */
	protected static final String CUSTOM_TARGET_ARROW_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCustomTargetArrow() <em>Custom Target Arrow</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCustomTargetArrow()
	 * @generated
	 * @ordered
	 */
	protected String customTargetArrow = CUSTOM_TARGET_ARROW_EDEFAULT;

	/**
	 * The default value of the '{@link #getCustomSourceArrow() <em>Custom Source Arrow</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCustomSourceArrow()
	 * @generated
	 * @ordered
	 */
	protected static final String CUSTOM_SOURCE_ARROW_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCustomSourceArrow() <em>Custom Source Arrow</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCustomSourceArrow()
	 * @generated
	 * @ordered
	 */
	protected String customSourceArrow = CUSTOM_SOURCE_ARROW_EDEFAULT;

	/**
	 * The default value of the '{@link #getColor() <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColor()
	 * @generated
	 * @ordered
	 */
	protected static final Color COLOR_EDEFAULT = (Color)GraphdescFactory.eINSTANCE.createFromString(GraphdescPackage.eINSTANCE.getColor(), "#000000");

	/**
	 * The cached value of the '{@link #getColor() <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColor()
	 * @generated
	 * @ordered
	 */
	protected Color color = COLOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getStyle() <em>Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStyle()
	 * @generated NOT
	 * @ordered
	 */
	protected final EdgeStyle STYLE_EDEFAULT = this instanceof ReferenceFigure ? EdgeStyle.NORMAL
			: EdgeStyle.DASHED;

	/**
	 * The cached value of the '{@link #getStyle() <em>Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStyle()
	 * @generated
	 * @ordered
	 */
	protected EdgeStyle style = STYLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMinimumEdgeLength() <em>Minimum Edge Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinimumEdgeLength()
	 * @generated NOT
	 * @ordered
	 */
	protected final int MINIMUM_EDGE_LENGTH_EDEFAULT = this instanceof ReferenceFigure ? 1
			: 3;

	/**
	 * The cached value of the '{@link #getMinimumEdgeLength() <em>Minimum Edge Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinimumEdgeLength()
	 * @generated
	 * @ordered
	 */
	protected int minimumEdgeLength = MINIMUM_EDGE_LENGTH_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractReferenceFigureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GraphdescPackage.Literals.ABSTRACT_REFERENCE_FIGURE;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__EREFERENCE, oldEReference, eReference));
			}
		}
		return eReference;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getName() {
		String result = null;
		if (getEReference() != null) {
			result = getEReference().getName();
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassFigure getClassFigure() {
		if (eContainerFeatureID() != GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__CLASS_FIGURE) return null;
		return (ClassFigure)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetClassFigure(ClassFigure newClassFigure, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newClassFigure, GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__CLASS_FIGURE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClassFigure(ClassFigure newClassFigure) {
		if (newClassFigure != eInternalContainer() || (eContainerFeatureID() != GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__CLASS_FIGURE && newClassFigure != null)) {
			if (EcoreUtil.isAncestor(this, newClassFigure))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newClassFigure != null)
				msgs = ((InternalEObject)newClassFigure).eInverseAdd(this, GraphdescPackage.CLASS_FIGURE__REFERENCE_FIGURES, ClassFigure.class, msgs);
			msgs = basicSetClassFigure(newClassFigure, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__CLASS_FIGURE, newClassFigure, newClassFigure));
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setEReference(EReference newEReference) {
		String oldName = getName();
		boolean oldContainment = isContainment();
		EReference oldEReference = eReference;
		eReference = newEReference;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__EREFERENCE,
					oldEReference, eReference));
			eNotify(new ENotificationImpl(this, Notification.SET,
					GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__NAME, oldName, getName()));
			eNotify(new ENotificationImpl(this, Notification.SET,
					GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__CONTAINMENT,
					oldContainment, isContainment()));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrowType getTargetArrowType() {
		return targetArrowType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setTargetArrowType(ArrowType newTargetArrowType) {
		ArrowType oldTargetArrowType = targetArrowType;
		targetArrowType = newTargetArrowType == null ? TARGET_ARROW_TYPE_EDEFAULT
				: newTargetArrowType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__TARGET_ARROW_TYPE,
					oldTargetArrowType, targetArrowType));
		if (!ArrowType.CUSTOM.equals(newTargetArrowType)) {
			setCustomTargetArrow(null);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrowType getSourceArrowType() {
		return sourceArrowType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setSourceArrowType(ArrowType newSourceArrowType) {
		ArrowType oldSourceArrowType = sourceArrowType;
		sourceArrowType = newSourceArrowType == null ? SOURCE_ARROW_TYPE_EDEFAULT
				: newSourceArrowType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__SOURCE_ARROW_TYPE,
					oldSourceArrowType, sourceArrowType));
		if (!ArrowType.CUSTOM.equals(newSourceArrowType)) {
			setCustomSourceArrow(null);
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isContainment() {
		boolean result = false;
		if (getEReference() != null) {
			result = getEReference().isContainment();
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCustomTargetArrow() {
		return customTargetArrow;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setCustomTargetArrow(String newCustomTargetArrow) {
		if (ArrowType.CUSTOM.equals(getTargetArrowType())
				|| newCustomTargetArrow == null) {
			String oldCustomTargetArrow = customTargetArrow;
			customTargetArrow = newCustomTargetArrow;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET,
						GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__CUSTOM_TARGET_ARROW,
						oldCustomTargetArrow, customTargetArrow));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCustomSourceArrow() {
		return customSourceArrow;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setCustomSourceArrow(String newCustomSourceArrow) {
		if (ArrowType.CUSTOM.equals(getSourceArrowType())
				|| newCustomSourceArrow == null) {
			String oldCustomSourceArrow = customSourceArrow;
			customSourceArrow = newCustomSourceArrow;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET,
						GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__CUSTOM_SOURCE_ARROW,
						oldCustomSourceArrow, customSourceArrow));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setColor(Color newColor) {
		Color oldColor = color;
		color = newColor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__COLOR, oldColor, color));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EdgeStyle getStyle() {
		return style;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStyle(EdgeStyle newStyle) {
		EdgeStyle oldStyle = style;
		style = newStyle == null ? STYLE_EDEFAULT : newStyle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__STYLE, oldStyle, style));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTargetEType() {
		EClass targetEType = basicGetTargetEType();
		return targetEType != null && targetEType.eIsProxy() ? (EClass)eResolveProxy((InternalEObject)targetEType) : targetEType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EClass basicGetTargetEType() {
		EClass result = null;
		if (getEReference() != null) {
			result = getEReference().getEReferenceType();
		}
		return result;
		
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMinimumEdgeLength() {
		return minimumEdgeLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinimumEdgeLength(int newMinimumEdgeLength) {
		int oldMinimumEdgeLength = minimumEdgeLength;
		minimumEdgeLength = newMinimumEdgeLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__MINIMUM_EDGE_LENGTH, oldMinimumEdgeLength, minimumEdgeLength));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean validate(DiagnosticChain diagnostic,
			Map<Object, Object> context) {
		EMFConstraintsHelper constraintsHelper = EMFConstraintsHelper
				.getInstance(GraphdescValidator.DIAGNOSTIC_SOURCE);
		boolean valid = true;
		// This validation method is also used by the rich reference figure
		// Error messages have to be specialized
		String currentObjectName = (this instanceof RichReferenceFigure) ? "rich reference"
				: "reference";
		ClassFigure classFigure = getClassFigure();
		if (classFigure == null) {
			constraintsHelper.addError(diagnostic, this, 0,
					"The {0} figure must be contained in a class figure", currentObjectName);
			valid = false;
		} else {
			EReference eReference = getEReference();
			if (eReference == null) {
				constraintsHelper
						.addError(diagnostic, this, 0,
								"The {0} figure must be associated to an EReference", currentObjectName);
				valid = false;
			} else {
				// Checks that the EReference is not already used
				if (GraphdescValidator.eReferenceIsUsedTwiceOrMore(this)) {
					constraintsHelper
							.addError(
									diagnostic,
									this,
									0,
									"The rich reference figure is associated to an EReference ({0}) that is already used by another figure.",
									eReference.getName());
					valid = false;
				}
				if (valid) {
					// Check eReference eClass
					EClass eClass = classFigure.getEClass();
					if (eClass != null) {
						if (!eClass.getEAllReferences().contains(eReference)) {
							constraintsHelper
									.addError(
											diagnostic,
											this,
											0,
											"The {0} figure is associated to an EReference ({1}) that is not a member of the parent class figure''s EClass ({2})",
											currentObjectName,
											eReference.getName(),
											eClass.getName());
							valid = false;
						}
						// Check that the reference is not already declared
						// in the nested figure EReferences
						else if (classFigure.getNestedFiguresEReferences()
									.contains(eReference)) {
							constraintsHelper
									.addError(
											diagnostic,
											this,
											0,
											"The {0} figure is associated to an EReference ({1}) that is already declared as a nested figure in the parent class figure",
											currentObjectName,
											eReference.getName());
							valid = false;
						}
					}
				}

				// Custom source arrow check
				if (ArrowType.CUSTOM.equals(getSourceArrowType())) {
					boolean notEmpty = constraintsHelper
							.addErrorIfEmpty(
									getCustomSourceArrow(),
									diagnostic,
									this,
									0,
									"The source arrow type is set to custom, you have to define it manually through the 'Custom Arrow' property");
					if (notEmpty
							&& !Pattern.matches(CUSTOM_AROW_TYPE_REGEXP, getCustomSourceArrow())) {
						constraintsHelper
								.addError(
										diagnostic,
										this,
										0,
										"The custom source arrow does not seem to be valid (it doesn't matches the regular expression '"
												+ CUSTOM_AROW_TYPE_REGEXP + "')");
					}
				}

				// Custom target arrow check
				if (ArrowType.CUSTOM.equals(getTargetArrowType())) {
					boolean notEmpty = constraintsHelper
							.addErrorIfEmpty(
									getCustomTargetArrow(),
									diagnostic,
									this,
									0,
									"The target arrow type is set to custom, you have to define it manually through the 'Custom Arrow' property");
					if (notEmpty
							&& !Pattern.matches(CUSTOM_AROW_TYPE_REGEXP, getCustomTargetArrow())) {
						constraintsHelper
								.addError(
										diagnostic,
										this,
										0,
										"The custom target arrow does not seem to be valid (it doesn't matches the regular expression '"
												+ CUSTOM_AROW_TYPE_REGEXP + "')");
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
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__CLASS_FIGURE:
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
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__CLASS_FIGURE:
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
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__CLASS_FIGURE:
				return eInternalContainer().eInverseRemove(this, GraphdescPackage.CLASS_FIGURE__REFERENCE_FIGURES, ClassFigure.class, msgs);
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
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__CLASS_FIGURE:
				return getClassFigure();
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__EREFERENCE:
				if (resolve) return getEReference();
				return basicGetEReference();
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__TARGET_ARROW_TYPE:
				return getTargetArrowType();
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__SOURCE_ARROW_TYPE:
				return getSourceArrowType();
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__CONTAINMENT:
				return isContainment();
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__CUSTOM_TARGET_ARROW:
				return getCustomTargetArrow();
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__CUSTOM_SOURCE_ARROW:
				return getCustomSourceArrow();
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__COLOR:
				return getColor();
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__STYLE:
				return getStyle();
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__TARGET_ETYPE:
				if (resolve) return getTargetEType();
				return basicGetTargetEType();
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__MINIMUM_EDGE_LENGTH:
				return getMinimumEdgeLength();
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
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__CLASS_FIGURE:
				setClassFigure((ClassFigure)newValue);
				return;
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__EREFERENCE:
				setEReference((EReference)newValue);
				return;
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__TARGET_ARROW_TYPE:
				setTargetArrowType((ArrowType)newValue);
				return;
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__SOURCE_ARROW_TYPE:
				setSourceArrowType((ArrowType)newValue);
				return;
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__CUSTOM_TARGET_ARROW:
				setCustomTargetArrow((String)newValue);
				return;
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__CUSTOM_SOURCE_ARROW:
				setCustomSourceArrow((String)newValue);
				return;
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__COLOR:
				setColor((Color)newValue);
				return;
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__STYLE:
				setStyle((EdgeStyle)newValue);
				return;
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__MINIMUM_EDGE_LENGTH:
				setMinimumEdgeLength((Integer)newValue);
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
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__CLASS_FIGURE:
				setClassFigure((ClassFigure)null);
				return;
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__EREFERENCE:
				setEReference((EReference)null);
				return;
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__TARGET_ARROW_TYPE:
				setTargetArrowType(TARGET_ARROW_TYPE_EDEFAULT);
				return;
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__SOURCE_ARROW_TYPE:
				setSourceArrowType(SOURCE_ARROW_TYPE_EDEFAULT);
				return;
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__CUSTOM_TARGET_ARROW:
				setCustomTargetArrow(CUSTOM_TARGET_ARROW_EDEFAULT);
				return;
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__CUSTOM_SOURCE_ARROW:
				setCustomSourceArrow(CUSTOM_SOURCE_ARROW_EDEFAULT);
				return;
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__COLOR:
				setColor(COLOR_EDEFAULT);
				return;
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__STYLE:
				setStyle(STYLE_EDEFAULT);
				return;
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__MINIMUM_EDGE_LENGTH:
				setMinimumEdgeLength(MINIMUM_EDGE_LENGTH_EDEFAULT);
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
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__CLASS_FIGURE:
				return getClassFigure() != null;
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__EREFERENCE:
				return eReference != null;
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__TARGET_ARROW_TYPE:
				return targetArrowType != TARGET_ARROW_TYPE_EDEFAULT;
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__SOURCE_ARROW_TYPE:
				return sourceArrowType != SOURCE_ARROW_TYPE_EDEFAULT;
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__CONTAINMENT:
				return isContainment() != CONTAINMENT_EDEFAULT;
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__CUSTOM_TARGET_ARROW:
				return CUSTOM_TARGET_ARROW_EDEFAULT == null ? customTargetArrow != null : !CUSTOM_TARGET_ARROW_EDEFAULT.equals(customTargetArrow);
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__CUSTOM_SOURCE_ARROW:
				return CUSTOM_SOURCE_ARROW_EDEFAULT == null ? customSourceArrow != null : !CUSTOM_SOURCE_ARROW_EDEFAULT.equals(customSourceArrow);
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__COLOR:
				return COLOR_EDEFAULT == null ? color != null : !COLOR_EDEFAULT.equals(color);
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__STYLE:
				return style != STYLE_EDEFAULT;
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__TARGET_ETYPE:
				return basicGetTargetEType() != null;
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__MINIMUM_EDGE_LENGTH:
				return minimumEdgeLength != MINIMUM_EDGE_LENGTH_EDEFAULT;
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
		result.append(" (targetArrowType: ");
		result.append(targetArrowType);
		result.append(", sourceArrowType: ");
		result.append(sourceArrowType);
		result.append(", customTargetArrow: ");
		result.append(customTargetArrow);
		result.append(", customSourceArrow: ");
		result.append(customSourceArrow);
		result.append(", color: ");
		result.append(color);
		result.append(", style: ");
		result.append(style);
		result.append(", minimumEdgeLength: ");
		result.append(minimumEdgeLength);
		result.append(')');
		return result.toString();
	}

	/* (non-Javadoc)
	 * @see org.emftools.emf2gv.graphdesc.impl.AbstractFigureImpl#getStandardOCLContext()
	 */
	@Override
	public EClass getStandardOCLContext() {
		// The context is the underlying EObject of the AbstractReferenceFigure
		// (ie. targeted by the EReference)
		return getEReference() != null ? getEReference().getEReferenceType()
				: null;
	}

} //AbstractReferenceFigureImpl
