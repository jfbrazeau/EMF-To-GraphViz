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


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.graphdesc.GraphdescFactory;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;
import org.emftools.emf2gv.graphdesc.Orientation;
import org.emftools.emf2gv.graphdesc.provider.util.AdapterFactoryWrapper;
import org.emftools.emf2gv.util.EMFHelper;
import org.emftools.emf2gv.util.EPackageFake;

/**
 * This is the item provider adapter for a {@link org.emftools.emf2gv.graphdesc.GVFigureDescription} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class GVFigureDescriptionItemProvider
	extends ItemProviderAdapter
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
	public GVFigureDescriptionItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addEPackagesPropertyDescriptor(object);
			addOrientationPropertyDescriptor(object);
			addAlignSameEClassesPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the EPackages feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addEPackagesPropertyDescriptor(Object object) {
		final AdapterFactory wrappedFactory = (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory());
		AdapterFactory factory = new AdapterFactoryWrapper(wrappedFactory) {
			public Object doAdapt(Object object, Object type, Object defaultAdapter) {
				Object result = null;
				if ((object instanceof EPackage.Descriptor || object instanceof EPackage) && type.equals(IItemLabelProvider.class)) {
					final IItemLabelProvider defaultLabelProvider = (IItemLabelProvider) defaultAdapter;
					result = new IItemLabelProvider() {
						public String getText(Object object) {
							return ((EPackage) object).getNsURI();
						}
						public Object getImage(Object object) {
							return defaultLabelProvider.getImage(object);
						}
					};
				}
				return result;
			}
		};
		itemPropertyDescriptors.add
			(new ItemPropertyDescriptor
				(factory,
				 getResourceLocator(),
				 getString("_UI_GVFigureDescription_ePackages_feature"),
				 getString("_UI_GVFigureDescription_ePackages_description", "_UI_GVFigureDescription_ePackages_feature", "_UI_GVFigureDescription_type"),
				 GraphdescPackage.Literals.GV_FIGURE_DESCRIPTION__EPACKAGES,
				 true,
				 false,
				 true,
				 null,
				 getString("_UI_ModelPropertyCategory"),
				 null) {
				public Collection<?> getChoiceOfValues(Object object) {
					return EMFHelper.getRegisteredEPackages();
				}
				@Override
				public void setPropertyValue(Object object, Object value) {
					@SuppressWarnings("unchecked")
					EList<EPackage> ePackages = (EList<EPackage>) value;
					for (int i=0; i<ePackages.size(); i++) {
						EPackage ePackage = ePackages.get(i);
						// If the package is a fake, we must resolve the real
						// EPackage with the same URI and then register it in
						// the registry
						if (ePackage instanceof EPackageFake) {
							// EPackage resolution
							ePackage = EMFHelper.resolve((EPackageFake) ePackage);
							// EPackage registration
							ePackages.set(i, ePackage);
						}
					}
					super.setPropertyValue(object, value);
				}
			});
	}

	/**
	 * This adds a property descriptor for the Orientation feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOrientationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_GVFigureDescription_orientation_feature"),
				 getString("_UI_GVFigureDescription_orientation_description"),
				 GraphdescPackage.Literals.GV_FIGURE_DESCRIPTION__ORIENTATION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 getString("_UI_AppearancePropertyCategory"),
				 null));
	}

	/**
	 * This adds a property descriptor for the Align Same EClasses feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAlignSameEClassesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_GVFigureDescription_alignSameEClasses_feature"),
				 getString("_UI_GVFigureDescription_alignSameEClasses_description"),
				 GraphdescPackage.Literals.GV_FIGURE_DESCRIPTION__ALIGN_SAME_ECLASSES,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 getString("_UI_AppearancePropertyCategory"),
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(GraphdescPackage.Literals.GV_FIGURE_DESCRIPTION__FILTERS);
			childrenFeatures.add(GraphdescPackage.Literals.GV_FIGURE_DESCRIPTION__CLASS_FIGURES);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns GVFigureDescription.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/GVFigureDescription"));
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
		Orientation labelValue = ((GVFigureDescription)object).getOrientation();
		String label = labelValue == null ? null : labelValue.toString();
		return label == null || label.length() == 0 ?
			getString("_UI_GVFigureDescription_type") :
			getString("_UI_GVFigureDescription_type") + " " + label;
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

		switch (notification.getFeatureID(GVFigureDescription.class)) {
			case GraphdescPackage.GV_FIGURE_DESCRIPTION__EPACKAGES:
			case GraphdescPackage.GV_FIGURE_DESCRIPTION__ORIENTATION:
			case GraphdescPackage.GV_FIGURE_DESCRIPTION__ALIGN_SAME_ECLASSES:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case GraphdescPackage.GV_FIGURE_DESCRIPTION__FILTERS:
			case GraphdescPackage.GV_FIGURE_DESCRIPTION__CLASS_FIGURES:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
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

		newChildDescriptors.add
			(createChildParameter
				(GraphdescPackage.Literals.GV_FIGURE_DESCRIPTION__FILTERS,
				 GraphdescFactory.eINSTANCE.createFilter()));

		newChildDescriptors.add
			(createChildParameter
				(GraphdescPackage.Literals.GV_FIGURE_DESCRIPTION__CLASS_FIGURES,
				 GraphdescFactory.eINSTANCE.createClassFigure()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return GraphdescEditPlugin.INSTANCE;
	}

}
