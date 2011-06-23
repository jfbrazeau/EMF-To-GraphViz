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
package org.emftools.emf2gv.graphdesc.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.emftools.emf2gv.graphdesc.AbstractReferenceFigure;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;
import org.emftools.emf2gv.graphdesc.RichReferenceFigure;

/**
 * This is the item provider adapter for a {@link org.emftools.emf2gv.graphdesc.RichReferenceFigure} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class RichReferenceFigureItemProvider
	extends AbstractReferenceFigureItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RichReferenceFigureItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			// Appearance properties
			addSourceLabelExpressionPropertyDescriptor(object);
			addSourceLabelStylePropertyDescriptor(object);
			addStandardLabelExpressionPropertyDescriptor(object);
			addStandardLabelStylePropertyDescriptor(object);
			addTargetLabelExpressionPropertyDescriptor(object);
			addTargetLabelStylePropertyDescriptor(object);
			addLabelDistancePropertyDescriptor(object);
			addLabelAnglePropertyDescriptor(object);

			// Model properties
			addTargetEReferencePropertyDescriptor(object);
		}
		// Filtering of the property descriptors (custom arrow type are hidden 
		// if not required)
		return getFilteredPropertyDescriptors((AbstractReferenceFigure) object);
	}

	/**
	 * This adds a property descriptor for the Target EReference feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addTargetEReferencePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(new ItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_RichReferenceFigure_targetEReference_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_RichReferenceFigure_targetEReference_feature", "_UI_RichReferenceFigure_type"),
				 GraphdescPackage.Literals.RICH_REFERENCE_FIGURE__TARGET_EREFERENCE,
				 true,
				 false,
				 true,
				 null,
				 getString("_UI_ModelPropertyCategory"),
				 null) {
				@Override
				public Collection<?> getChoiceOfValues(Object object) {
					RichReferenceFigure figure = (RichReferenceFigure) object;
					EReference eReference = figure.getEReference();
					if (eReference != null) {
						EClass richReferenceEClass = eReference.getEReferenceType();
						ArrayList<EReference> result = new ArrayList<EReference>();
						result.addAll(richReferenceEClass.getEAllReferences());
						return result;
					}
					else {
						return Collections.EMPTY_LIST;
					}
				}
			});
	}

	/**
	 * This adds a property descriptor for the Source Label Expression feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSourceLabelExpressionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_RichReferenceFigure_sourceLabelExpression_feature"),
				 getString("_UI_RichReferenceFigure_sourceLabelExpression_description"),
				 GraphdescPackage.Literals.RICH_REFERENCE_FIGURE__SOURCE_LABEL_EXPRESSION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 getString("_UI_AppearancePropertyCategory"),
				 null));
	}

	/**
	 * This adds a property descriptor for the Source Label Style feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSourceLabelStylePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_RichReferenceFigure_sourceLabelStyle_feature"),
				 getString("_UI_RichReferenceFigure_sourceLabelStyle_description"),
				 GraphdescPackage.Literals.RICH_REFERENCE_FIGURE__SOURCE_LABEL_STYLE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 getString("_UI_AppearancePropertyCategory"),
				 null));
	}

	/**
	 * This adds a property descriptor for the Standard Label Expression feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addStandardLabelExpressionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_RichReferenceFigure_standardLabelExpression_feature"),
				 getString("_UI_RichReferenceFigure_standardLabelExpression_description"),
				 GraphdescPackage.Literals.RICH_REFERENCE_FIGURE__STANDARD_LABEL_EXPRESSION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 getString("_UI_AppearancePropertyCategory"),
				 null));
	}

	/**
	 * This adds a property descriptor for the Standard Label Style feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addStandardLabelStylePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_RichReferenceFigure_standardLabelStyle_feature"),
				 getString("_UI_RichReferenceFigure_standardLabelStyle_description"),
				 GraphdescPackage.Literals.RICH_REFERENCE_FIGURE__STANDARD_LABEL_STYLE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 getString("_UI_AppearancePropertyCategory"),
				 null));
	}

	/**
	 * This adds a property descriptor for the Target Label Expression feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTargetLabelExpressionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_RichReferenceFigure_targetLabelExpression_feature"),
				 getString("_UI_RichReferenceFigure_targetLabelExpression_description"),
				 GraphdescPackage.Literals.RICH_REFERENCE_FIGURE__TARGET_LABEL_EXPRESSION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 getString("_UI_AppearancePropertyCategory"),
				 null));
	}

	/**
	 * This adds a property descriptor for the Target Label Style feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTargetLabelStylePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_RichReferenceFigure_targetLabelStyle_feature"),
				 getString("_UI_RichReferenceFigure_targetLabelStyle_description"),
				 GraphdescPackage.Literals.RICH_REFERENCE_FIGURE__TARGET_LABEL_STYLE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 getString("_UI_AppearancePropertyCategory"),
				 null));
	}

	/**
	 * This adds a property descriptor for the Label Distance feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLabelDistancePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_RichReferenceFigure_labelDistance_feature"),
				 getString("_UI_RichReferenceFigure_labelDistance_description"),
				 GraphdescPackage.Literals.RICH_REFERENCE_FIGURE__LABEL_DISTANCE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 getString("_UI_AppearancePropertyCategory"),
				 null));
	}

	/**
	 * This adds a property descriptor for the Label Angle feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLabelAnglePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_RichReferenceFigure_labelAngle_feature"),
				 getString("_UI_RichReferenceFigure_labelAngle_description"),
				 GraphdescPackage.Literals.RICH_REFERENCE_FIGURE__LABEL_ANGLE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 getString("_UI_AppearancePropertyCategory"),
				 null));
	}

	/**
	 * This returns RichReferenceFigure.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/RichReferenceFigure"));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean shouldComposeCreationImage() {
		return true;
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((RichReferenceFigure)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_RichReferenceFigure_type") :
			getString("_UI_RichReferenceFigure_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(RichReferenceFigure.class)) {
			case GraphdescPackage.RICH_REFERENCE_FIGURE__SOURCE_LABEL_EXPRESSION:
			case GraphdescPackage.RICH_REFERENCE_FIGURE__SOURCE_LABEL_STYLE:
			case GraphdescPackage.RICH_REFERENCE_FIGURE__STANDARD_LABEL_EXPRESSION:
			case GraphdescPackage.RICH_REFERENCE_FIGURE__STANDARD_LABEL_STYLE:
			case GraphdescPackage.RICH_REFERENCE_FIGURE__TARGET_LABEL_EXPRESSION:
			case GraphdescPackage.RICH_REFERENCE_FIGURE__TARGET_LABEL_STYLE:
			case GraphdescPackage.RICH_REFERENCE_FIGURE__LABEL_DISTANCE:
			case GraphdescPackage.RICH_REFERENCE_FIGURE__LABEL_ANGLE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

	/* (non-Javadoc)
	 * @see org.emftools.emf2gv.graphdesc.provider.AbstractReferenceFigureItemProvider#getAvailableEReferences(org.eclipse.emf.ecore.EClass)
	 */
	@Override
	protected List<EReference> getAvailableEReferences(EClass eClass) {
		// Only containment references are allowed
		List<EReference> result = new ArrayList<EReference>();
		for (EReference eReference : eClass.getEAllReferences()) {
			if (eReference.isContainment()) {
				result.add(eReference);
			}
		}
		return result;
	}
}
