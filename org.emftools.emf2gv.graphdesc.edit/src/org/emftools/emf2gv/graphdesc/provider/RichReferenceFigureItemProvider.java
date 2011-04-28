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
package org.emftools.emf2gv.graphdesc.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
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
			addSourceLabelEAttributePropertyDescriptor(object);
			addStandardLabelEAttributePropertyDescriptor(object);
			addTargetLabelEAttributePropertyDescriptor(object);
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
	 * This adds a property descriptor for the Source Label EAttribute feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addSourceLabelEAttributePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(new ItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_RichReferenceFigure_sourceLabelEAttribute_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_RichReferenceFigure_sourceLabelEAttribute_feature", "_UI_RichReferenceFigure_type"),
				 GraphdescPackage.Literals.RICH_REFERENCE_FIGURE__SOURCE_LABEL_EATTRIBUTE,
				 true,
				 false,
				 true,
				 null,
				 getString("_UI_AppearancePropertyCategory"),
				 null) {
				@Override
				public Collection<?> getChoiceOfValues(Object object) {
					return getEAllAttributes((RichReferenceFigure) object);
				}
			});
	}

	/**
	 * @param figure the rich reference figure.
	 * @return the rich reference EClass attributes.
	 */
	private Collection<?> getEAllAttributes(RichReferenceFigure figure) {
		EReference eReference = figure.getEReference();
		if (eReference != null) {
			EClass richeReferenceEClass = eReference.getEReferenceType();
			ArrayList<EAttribute> result = new ArrayList<EAttribute>();
			result.add(null);
			result.addAll(richeReferenceEClass.getEAllAttributes());
			return result;
		}
		else {
			return Collections.EMPTY_LIST;
		}
	}

	/**
	 * This adds a property descriptor for the Standard Label EAttribute feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addStandardLabelEAttributePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(new ItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_RichReferenceFigure_standardLabelEAttribute_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_RichReferenceFigure_standardLabelEAttribute_feature", "_UI_RichReferenceFigure_type"),
				 GraphdescPackage.Literals.RICH_REFERENCE_FIGURE__STANDARD_LABEL_EATTRIBUTE,
				 true,
				 false,
				 true,
				 null,
				 getString("_UI_AppearancePropertyCategory"),
				 null) {
				@Override
				public Collection<?> getChoiceOfValues(Object object) {
					return getEAllAttributes((RichReferenceFigure) object);
				}
			});
	}

	/**
	 * This adds a property descriptor for the Target Label EAttribute feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addTargetLabelEAttributePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(new ItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_RichReferenceFigure_targetLabelEAttribute_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_RichReferenceFigure_targetLabelEAttribute_feature", "_UI_RichReferenceFigure_type"),
				 GraphdescPackage.Literals.RICH_REFERENCE_FIGURE__TARGET_LABEL_EATTRIBUTE,
				 true,
				 false,
				 true,
				 null,
				 getString("_UI_AppearancePropertyCategory"),
				 null) {
				@Override
				public Collection<?> getChoiceOfValues(Object object) {
					return getEAllAttributes((RichReferenceFigure) object);
				}
			});
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

}
