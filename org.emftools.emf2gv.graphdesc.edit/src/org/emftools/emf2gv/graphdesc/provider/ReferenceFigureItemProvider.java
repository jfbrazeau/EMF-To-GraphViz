/**
 * Copyright (c) 2010, Jean-Francois Brazeau. All rights reserved.
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
package org.emftools.emf2gv.graphdesc.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
import org.emftools.emf2gv.graphdesc.ClassFigure;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;
import org.emftools.emf2gv.graphdesc.ReferenceFigure;
import org.emftools.emf2gv.graphdesc.provider.util.ArrowTypeItemLabelProvider;

/**
 * This is the item provider adapter for a
 * {@link org.emftools.emf2gv.graphdesc.ReferenceFigure} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ReferenceFigureItemProvider extends AbstractFigureItemProvider
		implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

	/** Label provider used for the source arrow type properties */
	private ArrowTypeItemLabelProvider sourceArrowTypeItemLabelProvider = new ArrowTypeItemLabelProvider(
			ArrowTypeItemLabelProvider.Category.Source);

	/** Label provider used for the target arrow type properties */
	private ArrowTypeItemLabelProvider targetArrowTypeItemLabelProvider = new ArrowTypeItemLabelProvider(
			ArrowTypeItemLabelProvider.Category.Target);

	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ReferenceFigureItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addEReferencePropertyDescriptor(object);
			addTargetArrowTypePropertyDescriptor(object);
			addSourceArrowTypePropertyDescriptor(object);
			addContainmentPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the EReference feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected void addEReferencePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(new ItemPropertyDescriptor(
						((ComposeableAdapterFactory) adapterFactory)
								.getRootAdapterFactory(), getResourceLocator(),
						getString("_UI_ReferenceFigure_eReference_feature"),
						getString("_UI_PropertyDescriptor_description",
								"_UI_ReferenceFigure_eReference_feature",
								"_UI_ReferenceFigure_type"),
						GraphdescPackage.Literals.REFERENCE_FIGURE__EREFERENCE,
						true, false, true, null,
						getString("_UI_ModelPropertyCategory"), null) {
					@Override
					public Collection<?> getChoiceOfValues(Object object) {
						ReferenceFigure refFigure = (ReferenceFigure) object;
						List<Object> result = new ArrayList<Object>();
						ClassFigure classFigure = refFigure.getClassFigure();
						if (classFigure != null) {
							EClass eClass = classFigure.getEClass();
							if (eClass != null) {
								result.addAll(eClass.getEAllReferences());
							}
						}
						Collections.sort(result, new Comparator<Object>() {
							@Override
							public int compare(Object o1, Object o2) {
								EReference e1 = (EReference) o1;
								EReference e2 = (EReference) o2;
								return e1.getName().compareTo(e2.getName());
							}
						});
						return result;
					}
				});
	}

	/**
	 * This adds a property descriptor for the Name feature. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected void addNamePropertyDescriptor(Object object) {
		// itemPropertyDescriptors.add
		// (createItemPropertyDescriptor
		// (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
		// getResourceLocator(),
		// getString("_UI_ReferenceFigure_name_feature"),
		// getString("_UI_PropertyDescriptor_description",
		// "_UI_ReferenceFigure_name_feature", "_UI_ReferenceFigure_type"),
		// GraphdescPackage.Literals.REFERENCE_FIGURE__NAME,
		// false,
		// false,
		// false,
		// ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
		// null,
		// null));
	}

	/**
	 * This adds a property descriptor for the Target Arrow Type feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected void addTargetArrowTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory)
						.getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_ReferenceFigure_targetArrowType_feature"),
				getString("_UI_PropertyDescriptor_description",
						"_UI_ReferenceFigure_targetArrowType_feature",
						"_UI_ReferenceFigure_type"),
				GraphdescPackage.Literals.REFERENCE_FIGURE__TARGET_ARROW_TYPE,
				true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				getString("_UI_AppearancePropertyCategory"), null) {
			@Override
			public IItemLabelProvider getLabelProvider(Object object) {
				return targetArrowTypeItemLabelProvider;
			}
		});
	}

	/**
	 * This adds a property descriptor for the Source Arrow Type feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected void addSourceArrowTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory)
						.getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_ReferenceFigure_sourceArrowType_feature"),
				getString("_UI_PropertyDescriptor_description",
						"_UI_ReferenceFigure_sourceArrowType_feature",
						"_UI_ReferenceFigure_type"),
				GraphdescPackage.Literals.REFERENCE_FIGURE__SOURCE_ARROW_TYPE,
				true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				getString("_UI_AppearancePropertyCategory"), null) {
			@Override
			public IItemLabelProvider getLabelProvider(Object object) {
				return sourceArrowTypeItemLabelProvider;
			}
		});
	}

	/**
	 * This adds a property descriptor for the Containment feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addContainmentPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ReferenceFigure_containment_feature"),
				 getString("_UI_ReferenceFigure_containment_description"),
				 GraphdescPackage.Literals.REFERENCE_FIGURE__CONTAINMENT,
				 false,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 getString("_UI_ModelPropertyCategory"),
				 null));
	}

	/**
	 * This returns ReferenceFigure.gif.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ReferenceFigure"));
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
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((ReferenceFigure)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_ReferenceFigure_type") :
			getString("_UI_ReferenceFigure_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(ReferenceFigure.class)) {
			case GraphdescPackage.REFERENCE_FIGURE__TARGET_ARROW_TYPE:
			case GraphdescPackage.REFERENCE_FIGURE__SOURCE_ARROW_TYPE:
			case GraphdescPackage.REFERENCE_FIGURE__CONTAINMENT:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing the children that can be created under this object. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(
			Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

}
