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
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.emftools.emf2gv.graphdesc.AbstractAttributeFigure;
import org.emftools.emf2gv.graphdesc.AbstractReferenceFigure;
import org.emftools.emf2gv.graphdesc.AttributeFigure;
import org.emftools.emf2gv.graphdesc.ClassFigure;
import org.emftools.emf2gv.graphdesc.FontStyle;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.graphdesc.GraphdescFactory;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;
import org.emftools.emf2gv.graphdesc.ReferenceFigure;
import org.emftools.emf2gv.graphdesc.util.GraphdescValidator;
import org.emftools.validation.utils.EMFConstraintsHelper;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Class Figure</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.ClassFigureImpl#getEPackage <em>EPackage</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.ClassFigureImpl#getEClass <em>EClass</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.ClassFigureImpl#getLabelEAttribute <em>Label EAttribute</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.ClassFigureImpl#getLabelStyle <em>Label Style</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.ClassFigureImpl#getAttributeFigures <em>Attribute Figures</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.ClassFigureImpl#getReferenceFigures <em>Reference Figures</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.ClassFigureImpl#getGvFigureDescription <em>Gv Figure Description</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.ClassFigureImpl#getHeaderBackgroundColor <em>Header Background Color</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.ClassFigureImpl#getBodyBackgroundColor <em>Body Background Color</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.ClassFigureImpl#getNestedFiguresEReferences <em>Nested Figures EReferences</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.impl.ClassFigureImpl#isContainer <em>Container</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClassFigureImpl extends AbstractFigureImpl implements ClassFigure {
	/**
	 * The cached value of the '{@link #getEClass() <em>EClass</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getEClass()
	 * @generated
	 * @ordered
	 */
	protected EClass eClass;

	/**
	 * The cached value of the '{@link #getLabelEAttribute() <em>Label EAttribute</em>}' reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getLabelEAttribute()
	 * @generated
	 * @ordered
	 */
	protected EAttribute labelEAttribute;

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
	 * The cached value of the '{@link #getAttributeFigures()
	 * <em>Attribute Figures</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getAttributeFigures()
	 * @generated
	 * @ordered
	 */
	protected EList<AbstractAttributeFigure> attributeFigures;

	/**
	 * The cached value of the '{@link #getReferenceFigures()
	 * <em>Reference Figures</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getReferenceFigures()
	 * @generated
	 * @ordered
	 */
	protected EList<AbstractReferenceFigure> referenceFigures;

	/**
	 * The default value of the '{@link #getHeaderBackgroundColor() <em>Header Background Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeaderBackgroundColor()
	 * @generated
	 * @ordered
	 */
	protected static final Color HEADER_BACKGROUND_COLOR_EDEFAULT = (Color)GraphdescFactory.eINSTANCE.createFromString(GraphdescPackage.eINSTANCE.getColor(), "#9DBDF9");

	/**
	 * The cached value of the '{@link #getHeaderBackgroundColor() <em>Header Background Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeaderBackgroundColor()
	 * @generated
	 * @ordered
	 */
	protected Color headerBackgroundColor = HEADER_BACKGROUND_COLOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getBodyBackgroundColor() <em>Body Background Color</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getBodyBackgroundColor()
	 * @generated
	 * @ordered
	 */
	protected static final Color BODY_BACKGROUND_COLOR_EDEFAULT = (Color)GraphdescFactory.eINSTANCE.createFromString(GraphdescPackage.eINSTANCE.getColor(), "#EEEEEE");

	/**
	 * The cached value of the '{@link #getBodyBackgroundColor() <em>Body Background Color</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getBodyBackgroundColor()
	 * @generated
	 * @ordered
	 */
	protected Color bodyBackgroundColor = BODY_BACKGROUND_COLOR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getNestedFiguresEReferences() <em>Nested Figures EReferences</em>}' reference list.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @see #getNestedFiguresEReferences()
	 * @generated
	 * @ordered
	 */
	protected EList<EReference> nestedFiguresEReferences;

	/**
	 * The default value of the '{@link #isContainer() <em>Container</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isContainer()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONTAINER_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassFigureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GraphdescPackage.Literals.CLASS_FIGURE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEClass() {
		if (eClass != null && eClass.eIsProxy()) {
			InternalEObject oldEClass = (InternalEObject)eClass;
			eClass = (EClass)eResolveProxy(oldEClass);
			if (eClass != oldEClass) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GraphdescPackage.CLASS_FIGURE__ECLASS, oldEClass, eClass));
			}
		}
		return eClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass basicGetEClass() {
		return eClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setEClass(EClass newEClass) {
		String oldNom = getName();
		EClass oldEClass = eClass;
		eClass = newEClass;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					GraphdescPackage.CLASS_FIGURE__ECLASS, oldEClass, eClass));
			eNotify(new ENotificationImpl(this, Notification.SET,
					GraphdescPackage.CLASS_FIGURE__NAME, oldNom, getName()));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabelEAttribute() {
		if (labelEAttribute != null && labelEAttribute.eIsProxy()) {
			InternalEObject oldLabelEAttribute = (InternalEObject)labelEAttribute;
			labelEAttribute = (EAttribute)eResolveProxy(oldLabelEAttribute);
			if (labelEAttribute != oldLabelEAttribute) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GraphdescPackage.CLASS_FIGURE__LABEL_EATTRIBUTE, oldLabelEAttribute, labelEAttribute));
			}
		}
		return labelEAttribute;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute basicGetLabelEAttribute() {
		return labelEAttribute;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabelEAttribute(EAttribute newLabelEAttribute) {
		EAttribute oldLabelEAttribute = labelEAttribute;
		labelEAttribute = newLabelEAttribute;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.CLASS_FIGURE__LABEL_EATTRIBUTE, oldLabelEAttribute, labelEAttribute));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FontStyle> getLabelStyle() {
		if (labelStyle == null) {
			labelStyle = new EDataTypeUniqueEList<FontStyle>(FontStyle.class, this, GraphdescPackage.CLASS_FIGURE__LABEL_STYLE);
		}
		return labelStyle;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractAttributeFigure> getAttributeFigures() {
		if (attributeFigures == null) {
			attributeFigures = new EObjectContainmentWithInverseEList<AbstractAttributeFigure>(AbstractAttributeFigure.class, this, GraphdescPackage.CLASS_FIGURE__ATTRIBUTE_FIGURES, GraphdescPackage.ABSTRACT_ATTRIBUTE_FIGURE__CLASS_FIGURE);
		}
		return attributeFigures;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getName() {
		StringWriter result = new StringWriter();
		EPackage ePackage = getEPackage();
		if (ePackage != null) {
			result.append(ePackage.getName());
			result.append('.');
			if (getEClass() != null) {
				result.append(getEClass().getName());
			} else {
				result.append("<>");
			}
		}
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EPackage getEPackage() {
		EPackage ePackage = basicGetEPackage();
		return ePackage != null && ePackage.eIsProxy() ? (EPackage)eResolveProxy((InternalEObject)ePackage) : ePackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage basicGetEPackage() {
		EPackage result = null;
		EClass eClass = getEClass();
		if (eClass != null) {
			result = eClass.getEPackage();
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractReferenceFigure> getReferenceFigures() {
		if (referenceFigures == null) {
			referenceFigures = new EObjectContainmentWithInverseEList<AbstractReferenceFigure>(AbstractReferenceFigure.class, this, GraphdescPackage.CLASS_FIGURE__REFERENCE_FIGURES, GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__CLASS_FIGURE);
		}
		return referenceFigures;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public GVFigureDescription getGvFigureDescription() {
		if (eContainerFeatureID() != GraphdescPackage.CLASS_FIGURE__GV_FIGURE_DESCRIPTION) return null;
		return (GVFigureDescription)eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGvFigureDescription(
			GVFigureDescription newGvFigureDescription, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newGvFigureDescription, GraphdescPackage.CLASS_FIGURE__GV_FIGURE_DESCRIPTION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setGvFigureDescription(
			GVFigureDescription newGvFigureDescription) {
		if (newGvFigureDescription != eInternalContainer() || (eContainerFeatureID() != GraphdescPackage.CLASS_FIGURE__GV_FIGURE_DESCRIPTION && newGvFigureDescription != null)) {
			if (EcoreUtil.isAncestor(this, newGvFigureDescription))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGvFigureDescription != null)
				msgs = ((InternalEObject)newGvFigureDescription).eInverseAdd(this, GraphdescPackage.GV_FIGURE_DESCRIPTION__CLASS_FIGURES, GVFigureDescription.class, msgs);
			msgs = basicSetGvFigureDescription(newGvFigureDescription, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.CLASS_FIGURE__GV_FIGURE_DESCRIPTION, newGvFigureDescription, newGvFigureDescription));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Color getHeaderBackgroundColor() {
		return headerBackgroundColor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHeaderBackgroundColor(Color newHeaderBackgroundColor) {
		Color oldHeaderBackgroundColor = headerBackgroundColor;
		headerBackgroundColor = newHeaderBackgroundColor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.CLASS_FIGURE__HEADER_BACKGROUND_COLOR, oldHeaderBackgroundColor, headerBackgroundColor));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Color getBodyBackgroundColor() {
		return bodyBackgroundColor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBodyBackgroundColor(Color newBodyBackgroundColor) {
		Color oldBodyBackgroundColor = bodyBackgroundColor;
		bodyBackgroundColor = newBodyBackgroundColor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdescPackage.CLASS_FIGURE__BODY_BACKGROUND_COLOR, oldBodyBackgroundColor, bodyBackgroundColor));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EReference> getNestedFiguresEReferences() {
		if (nestedFiguresEReferences == null) {
			nestedFiguresEReferences = new EObjectResolvingEList<EReference>(EReference.class, this, GraphdescPackage.CLASS_FIGURE__NESTED_FIGURES_EREFERENCES);
		}
		return nestedFiguresEReferences;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isContainer() {
		return getNestedFiguresEReferences().size() != 0;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public AttributeFigure getAttributeFigure(EAttribute eAttribute) {
		AttributeFigure result = null;
		EList<AbstractAttributeFigure> abstractAttrFigures = getAttributeFigures();
		for (int i = 0; i < abstractAttrFigures.size() && result == null; i++) {
			AbstractAttributeFigure abstractAttrFigure = abstractAttrFigures.get(i);
			if (abstractAttrFigure instanceof AttributeFigure) {
				AttributeFigure attrFigure = (AttributeFigure) abstractAttrFigure;
				if (eAttribute.equals(attrFigure.getEAttribute())) {
					result = (AttributeFigure) attrFigure;
				}
			}
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public ReferenceFigure getReferenceFigure(EReference eReference) {
		ReferenceFigure result = null;
		EList<AbstractReferenceFigure> refFigures = getReferenceFigures();
		for (int i = 0; i < refFigures.size() && result == null; i++) {
			AbstractReferenceFigure refFigure = refFigures.get(i);
			if (refFigure instanceof ReferenceFigure
					&& eReference.equals(refFigure.getEReference())) {
				result = (ReferenceFigure) refFigure;
			}
		}
		return result;
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
		GVFigureDescription gvFigureDescription = getGvFigureDescription();
		if (gvFigureDescription == null) {
			constraintsHelper
					.addError(diagnostic, this, 0,
							"The class figure must be contained in a GVFigureDescription");
			valid = false;
		} else {
			EClass eClass = getEClass();
			if (eClass == null) {
				constraintsHelper.addError(diagnostic, this, 0,
						"The class figure must be associated to an EClass");
				valid = false;
			} else {
				// Check unique
				valid = constraintsHelper.addErrorIfNotUnique(
						gvFigureDescription.getClassFigures(),
						GraphdescPackage.eINSTANCE.getClassFigure_EClass(),
						diagnostic, this, 0,
						"The EClass ''{0}'' is referenced twice or more",
						eClass.getName());

				if (valid) {
					// Check eClass package
					List<EClassifier> classifiers = new ArrayList<EClassifier>();
					for (EPackage ePackage : gvFigureDescription.getEPackages()) {
						classifiers.addAll(ePackage.getEClassifiers());
					}
					if (!classifiers.contains(eClass)) {
						constraintsHelper
								.addError(
										diagnostic,
										this,
										0,
										"The class figure EClass ''{0}'' is not contained in the EPackage authorized list",
										eClass.getName());
						valid = false;
					}
				}

				if (valid) {
					// If the label attribute is defined, it must belong to the
					// class figure's EClass
					EAttribute eAttribute = getLabelEAttribute();
					if (eAttribute != null
							&& !eClass.getEAllAttributes().contains(eAttribute)) {
						constraintsHelper
								.addError(
										diagnostic,
										this,
										0,
										"The class figure label attibute ''{0}'' does not belong to the class figure's EClass ''{1}''",
										eAttribute.getName(), eClass.getName());
						valid = false;
					}

					// If the class figure has nested figures EReferences,
					// theses
					// references must be containment references
					for (EReference eReference : getNestedFiguresEReferences()) {
						if (!eClass.getEAllReferences().contains(eReference)) {
							constraintsHelper
									.addError(
											diagnostic,
											this,
											0,
											"The class figure has a nested figure that doesn''t correspond to one of its own EReferences",
											eReference.getName());
							valid = false;
						} else if (!eReference.isContainment()) {
							constraintsHelper
									.addError(
											diagnostic,
											this,
											0,
											"The class figure has a nested figure that doesn''t correspond to a containment feature ({0})",
											eReference.getName());
							valid = false;
						}
					}
				}

			}
		}
		return valid;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GraphdescPackage.CLASS_FIGURE__ATTRIBUTE_FIGURES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAttributeFigures()).basicAdd(otherEnd, msgs);
			case GraphdescPackage.CLASS_FIGURE__REFERENCE_FIGURES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getReferenceFigures()).basicAdd(otherEnd, msgs);
			case GraphdescPackage.CLASS_FIGURE__GV_FIGURE_DESCRIPTION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetGvFigureDescription((GVFigureDescription)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GraphdescPackage.CLASS_FIGURE__ATTRIBUTE_FIGURES:
				return ((InternalEList<?>)getAttributeFigures()).basicRemove(otherEnd, msgs);
			case GraphdescPackage.CLASS_FIGURE__REFERENCE_FIGURES:
				return ((InternalEList<?>)getReferenceFigures()).basicRemove(otherEnd, msgs);
			case GraphdescPackage.CLASS_FIGURE__GV_FIGURE_DESCRIPTION:
				return basicSetGvFigureDescription(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(
			NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case GraphdescPackage.CLASS_FIGURE__GV_FIGURE_DESCRIPTION:
				return eInternalContainer().eInverseRemove(this, GraphdescPackage.GV_FIGURE_DESCRIPTION__CLASS_FIGURES, GVFigureDescription.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GraphdescPackage.CLASS_FIGURE__EPACKAGE:
				if (resolve) return getEPackage();
				return basicGetEPackage();
			case GraphdescPackage.CLASS_FIGURE__ECLASS:
				if (resolve) return getEClass();
				return basicGetEClass();
			case GraphdescPackage.CLASS_FIGURE__LABEL_EATTRIBUTE:
				if (resolve) return getLabelEAttribute();
				return basicGetLabelEAttribute();
			case GraphdescPackage.CLASS_FIGURE__LABEL_STYLE:
				return getLabelStyle();
			case GraphdescPackage.CLASS_FIGURE__ATTRIBUTE_FIGURES:
				return getAttributeFigures();
			case GraphdescPackage.CLASS_FIGURE__REFERENCE_FIGURES:
				return getReferenceFigures();
			case GraphdescPackage.CLASS_FIGURE__GV_FIGURE_DESCRIPTION:
				return getGvFigureDescription();
			case GraphdescPackage.CLASS_FIGURE__HEADER_BACKGROUND_COLOR:
				return getHeaderBackgroundColor();
			case GraphdescPackage.CLASS_FIGURE__BODY_BACKGROUND_COLOR:
				return getBodyBackgroundColor();
			case GraphdescPackage.CLASS_FIGURE__NESTED_FIGURES_EREFERENCES:
				return getNestedFiguresEReferences();
			case GraphdescPackage.CLASS_FIGURE__CONTAINER:
				return isContainer();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case GraphdescPackage.CLASS_FIGURE__ECLASS:
				setEClass((EClass)newValue);
				return;
			case GraphdescPackage.CLASS_FIGURE__LABEL_EATTRIBUTE:
				setLabelEAttribute((EAttribute)newValue);
				return;
			case GraphdescPackage.CLASS_FIGURE__LABEL_STYLE:
				getLabelStyle().clear();
				getLabelStyle().addAll((Collection<? extends FontStyle>)newValue);
				return;
			case GraphdescPackage.CLASS_FIGURE__ATTRIBUTE_FIGURES:
				getAttributeFigures().clear();
				getAttributeFigures().addAll((Collection<? extends AbstractAttributeFigure>)newValue);
				return;
			case GraphdescPackage.CLASS_FIGURE__REFERENCE_FIGURES:
				getReferenceFigures().clear();
				getReferenceFigures().addAll((Collection<? extends AbstractReferenceFigure>)newValue);
				return;
			case GraphdescPackage.CLASS_FIGURE__GV_FIGURE_DESCRIPTION:
				setGvFigureDescription((GVFigureDescription)newValue);
				return;
			case GraphdescPackage.CLASS_FIGURE__HEADER_BACKGROUND_COLOR:
				setHeaderBackgroundColor((Color)newValue);
				return;
			case GraphdescPackage.CLASS_FIGURE__BODY_BACKGROUND_COLOR:
				setBodyBackgroundColor((Color)newValue);
				return;
			case GraphdescPackage.CLASS_FIGURE__NESTED_FIGURES_EREFERENCES:
				getNestedFiguresEReferences().clear();
				getNestedFiguresEReferences().addAll((Collection<? extends EReference>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case GraphdescPackage.CLASS_FIGURE__ECLASS:
				setEClass((EClass)null);
				return;
			case GraphdescPackage.CLASS_FIGURE__LABEL_EATTRIBUTE:
				setLabelEAttribute((EAttribute)null);
				return;
			case GraphdescPackage.CLASS_FIGURE__LABEL_STYLE:
				getLabelStyle().clear();
				return;
			case GraphdescPackage.CLASS_FIGURE__ATTRIBUTE_FIGURES:
				getAttributeFigures().clear();
				return;
			case GraphdescPackage.CLASS_FIGURE__REFERENCE_FIGURES:
				getReferenceFigures().clear();
				return;
			case GraphdescPackage.CLASS_FIGURE__GV_FIGURE_DESCRIPTION:
				setGvFigureDescription((GVFigureDescription)null);
				return;
			case GraphdescPackage.CLASS_FIGURE__HEADER_BACKGROUND_COLOR:
				setHeaderBackgroundColor(HEADER_BACKGROUND_COLOR_EDEFAULT);
				return;
			case GraphdescPackage.CLASS_FIGURE__BODY_BACKGROUND_COLOR:
				setBodyBackgroundColor(BODY_BACKGROUND_COLOR_EDEFAULT);
				return;
			case GraphdescPackage.CLASS_FIGURE__NESTED_FIGURES_EREFERENCES:
				getNestedFiguresEReferences().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case GraphdescPackage.CLASS_FIGURE__EPACKAGE:
				return basicGetEPackage() != null;
			case GraphdescPackage.CLASS_FIGURE__ECLASS:
				return eClass != null;
			case GraphdescPackage.CLASS_FIGURE__LABEL_EATTRIBUTE:
				return labelEAttribute != null;
			case GraphdescPackage.CLASS_FIGURE__LABEL_STYLE:
				return labelStyle != null && !labelStyle.isEmpty();
			case GraphdescPackage.CLASS_FIGURE__ATTRIBUTE_FIGURES:
				return attributeFigures != null && !attributeFigures.isEmpty();
			case GraphdescPackage.CLASS_FIGURE__REFERENCE_FIGURES:
				return referenceFigures != null && !referenceFigures.isEmpty();
			case GraphdescPackage.CLASS_FIGURE__GV_FIGURE_DESCRIPTION:
				return getGvFigureDescription() != null;
			case GraphdescPackage.CLASS_FIGURE__HEADER_BACKGROUND_COLOR:
				return HEADER_BACKGROUND_COLOR_EDEFAULT == null ? headerBackgroundColor != null : !HEADER_BACKGROUND_COLOR_EDEFAULT.equals(headerBackgroundColor);
			case GraphdescPackage.CLASS_FIGURE__BODY_BACKGROUND_COLOR:
				return BODY_BACKGROUND_COLOR_EDEFAULT == null ? bodyBackgroundColor != null : !BODY_BACKGROUND_COLOR_EDEFAULT.equals(bodyBackgroundColor);
			case GraphdescPackage.CLASS_FIGURE__NESTED_FIGURES_EREFERENCES:
				return nestedFiguresEReferences != null && !nestedFiguresEReferences.isEmpty();
			case GraphdescPackage.CLASS_FIGURE__CONTAINER:
				return isContainer() != CONTAINER_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (labelStyle: ");
		result.append(labelStyle);
		result.append(", headerBackgroundColor: ");
		result.append(headerBackgroundColor);
		result.append(", bodyBackgroundColor: ");
		result.append(bodyBackgroundColor);
		result.append(')');
		return result.toString();
	}

	/* (non-Javadoc)
	 * @see org.emftools.emf2gv.graphdesc.impl.AbstractFigureImpl#getStandardOCLContext()
	 */
	@Override
	public EClass getStandardOCLContext() {
		return getEClass();
	}

} // ClassFigureImpl
