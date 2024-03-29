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


import java.net.URL;
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
import org.emftools.emf2gv.graphdesc.ArrowType;
import org.emftools.emf2gv.graphdesc.ClassFigure;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;
import org.emftools.emf2gv.graphdesc.provider.util.ArrowTypeItemLabelProvider;
import org.osgi.framework.Bundle;

/**
 * This is the item provider adapter for a {@link org.emftools.emf2gv.graphdesc.AbstractReferenceFigure} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public abstract class AbstractReferenceFigureItemProvider
	extends AbstractFigureItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {

	/** Label provider used for the source arrow type properties */
	private ArrowTypeItemLabelProvider sourceArrowTypeItemLabelProvider = new ArrowTypeItemLabelProvider(
			ArrowTypeItemLabelProvider.Category.Source);

	/** Label provider used for the target arrow type properties */
	private ArrowTypeItemLabelProvider targetArrowTypeItemLabelProvider = new ArrowTypeItemLabelProvider(
			ArrowTypeItemLabelProvider.Category.Target);

	/** Custom source arrow property descriptor (hidden if the arrow type is different from custom) */
	private IItemPropertyDescriptor customSourceArrowPropertyDescriptor;

	/** Custom target arrow property descriptor (hidden if the arrow type is different from custom) */
	private IItemPropertyDescriptor customTargetArrowPropertyDescriptor;
	
	
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractReferenceFigureItemProvider(AdapterFactory adapterFactory) {
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

			// Model properties
			addColorPropertyDescriptor(object);
			addStylePropertyDescriptor(object);
			addSourceArrowTypePropertyDescriptor(object);
			addCustomSourceArrowPropertyDescriptor(object);
			addTargetArrowTypePropertyDescriptor(object);
			addCustomTargetArrowPropertyDescriptor(object);
			addMinimumEdgeLengthPropertyDescriptor(object);

			// Model properties
			addEReferencePropertyDescriptor(object);
			addTargetETypePropertyDescriptor(object);
		}
		// Filtering of the property descriptors (custom arrow type are hidden 
		// if not required)
		return getFilteredPropertyDescriptors((AbstractReferenceFigure) object);
	}

	/**
	 * This adds a property descriptor for the EReference feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addEReferencePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(new ItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractReferenceFigure_eReference_feature"),
				 getString("_UI_AbstractReferenceFigure_eReference_description"),
				 GraphdescPackage.Literals.ABSTRACT_REFERENCE_FIGURE__EREFERENCE,
				 true,
				 false,
				 true,
				 null,
				 getString("_UI_ModelPropertyCategory"),
				 null) {
				@Override
				public Collection<?> getChoiceOfValues(Object object) {
					AbstractReferenceFigure refFigure = (AbstractReferenceFigure) object;
					List<EReference> result = new ArrayList<EReference>();
					ClassFigure classFigure = refFigure.getClassFigure();
					if (classFigure != null) {
						EClass eClass = classFigure.getEClass();
						if (eClass != null) {
							result.addAll(getAvailableEReferences(eClass));
						}
					}
					Collections.sort(result, ENAMED_ELEMENT_COMPARATOR);
					return result;
				}
			});
	}

	/**
	 * Returns the available EReferences.
	 * @param eClass the EClass.
	 */
	protected abstract List<EReference> getAvailableEReferences(EClass eClass);
	
	/**
	 * This adds a property descriptor for the Target Arrow Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addTargetArrowTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(new ItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractReferenceFigure_targetArrowType_feature"),
				 getString("_UI_AbstractReferenceFigure_targetArrowType_description"),
				 GraphdescPackage.Literals.ABSTRACT_REFERENCE_FIGURE__TARGET_ARROW_TYPE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 getString("_UI_AppearancePropertyCategory"),
				 null) {
				@Override
				public IItemLabelProvider getLabelProvider(Object object) {
					return targetArrowTypeItemLabelProvider;
				}
			});
	}

	/**
	 * This adds a property descriptor for the Source Arrow Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addSourceArrowTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(new ItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractReferenceFigure_sourceArrowType_feature"),
				 getString("_UI_AbstractReferenceFigure_sourceArrowType_description"),
				 GraphdescPackage.Literals.ABSTRACT_REFERENCE_FIGURE__SOURCE_ARROW_TYPE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 getString("_UI_AppearancePropertyCategory"),
				 null) {
				@Override
				public IItemLabelProvider getLabelProvider(Object object) {
					return sourceArrowTypeItemLabelProvider;
				}
			});
	}

	/**
	 * This adds a property descriptor for the Custom Target Arrow feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addCustomTargetArrowPropertyDescriptor(Object object) {
		Bundle bundle = GraphdescEditPlugin.getPlugin().getBundle();
		final URL iconUrl = bundle
				.getResource("/icons/full/obj16/arrows/target/custom.png");
		final IItemLabelProvider labelProvider = new IItemLabelProvider() {
			public String getText(Object object) {
				return (String) object;
			}
			public Object getImage(Object object) {
				return iconUrl;
			}
		};
		customTargetArrowPropertyDescriptor = new ItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractReferenceFigure_customTargetArrow_feature"),
				 getString("_UI_AbstractReferenceFigure_customTargetArrow_description"),
				 GraphdescPackage.Literals.ABSTRACT_REFERENCE_FIGURE__CUSTOM_TARGET_ARROW,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 getString("_UI_AppearancePropertyCategory"),
				 null) {
				@Override
				public IItemLabelProvider getLabelProvider(Object object) {
					return labelProvider;
				}
			};
			itemPropertyDescriptors.add(customTargetArrowPropertyDescriptor);
	}

	/**
	 * This adds a property descriptor for the Custom Source Arrow feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addCustomSourceArrowPropertyDescriptor(Object object) {
		Bundle bundle = GraphdescEditPlugin.getPlugin().getBundle();
		final URL iconUrl = bundle
				.getResource("/icons/full/obj16/arrows/source/custom.png");
		final IItemLabelProvider labelProvider = new IItemLabelProvider() {
			public String getText(Object object) {
				return (String) object;
			}
			public Object getImage(Object object) {
				return iconUrl;
			}
		};
		customSourceArrowPropertyDescriptor = new ItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractReferenceFigure_customSourceArrow_feature"),
				 getString("_UI_AbstractReferenceFigure_customSourceArrow_description"),
				 GraphdescPackage.Literals.ABSTRACT_REFERENCE_FIGURE__CUSTOM_SOURCE_ARROW,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 getString("_UI_AppearancePropertyCategory"),
				 null) {
				@Override
				public IItemLabelProvider getLabelProvider(Object object) {
					return labelProvider;
				}
			};
			itemPropertyDescriptors.add(customSourceArrowPropertyDescriptor);
	}

	/**
	 * This adds a property descriptor for the Color feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addColorPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractReferenceFigure_color_feature"),
				 getString("_UI_AbstractReferenceFigure_color_description"),
				 GraphdescPackage.Literals.ABSTRACT_REFERENCE_FIGURE__COLOR,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 getString("_UI_AppearancePropertyCategory"),
				 null));
	}

	/**
	 * This adds a property descriptor for the Style feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addStylePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractReferenceFigure_style_feature"),
				 getString("_UI_AbstractReferenceFigure_style_description"),
				 GraphdescPackage.Literals.ABSTRACT_REFERENCE_FIGURE__STYLE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 getString("_UI_AppearancePropertyCategory"),
				 null));
	}

	/**
	 * This adds a property descriptor for the Target EType feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTargetETypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractReferenceFigure_targetEType_feature"),
				 getString("_UI_AbstractReferenceFigure_targetEType_description"),
				 GraphdescPackage.Literals.ABSTRACT_REFERENCE_FIGURE__TARGET_ETYPE,
				 false,
				 false,
				 false,
				 null,
				 getString("_UI_ModelPropertyCategory"),
				 null));
	}

	/**
	 * This adds a property descriptor for the Minimum Edge Length feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMinimumEdgeLengthPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractReferenceFigure_minimumEdgeLength_feature"),
				 getString("_UI_AbstractReferenceFigure_minimumEdgeLength_description"),
				 GraphdescPackage.Literals.ABSTRACT_REFERENCE_FIGURE__MINIMUM_EDGE_LENGTH,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 getString("_UI_AppearancePropertyCategory"),
				 null));
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
		String label = ((AbstractReferenceFigure)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_AbstractReferenceFigure_type") :
			getString("_UI_AbstractReferenceFigure_type") + " " + label;
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

		switch (notification.getFeatureID(AbstractReferenceFigure.class)) {
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__TARGET_ARROW_TYPE:
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__SOURCE_ARROW_TYPE:
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__CONTAINMENT:
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__CUSTOM_TARGET_ARROW:
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__CUSTOM_SOURCE_ARROW:
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__COLOR:
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__STYLE:
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE__MINIMUM_EDGE_LENGTH:
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

	/**
	 * Remove the custom arrow property descriptors if required.
	 * @param object the reference figure.
	 * @return the filtered property descriptors.
	 * @generated NOT
	 */
	protected List<IItemPropertyDescriptor> getFilteredPropertyDescriptors(AbstractReferenceFigure figure) {
		// Custom arrow fields are shown only if the corresponding types are set to 'custom'
		List<IItemPropertyDescriptor> result = null;
		boolean customTargetArrowType = figure.getTargetArrowType().equals(ArrowType.CUSTOM); 
		boolean customSourceArrowType = figure.getSourceArrowType().equals(ArrowType.CUSTOM); 
		if (customSourceArrowType && customTargetArrowType) {
			result = itemPropertyDescriptors;
		}
		else {
			result = new ArrayList<IItemPropertyDescriptor>();
			result.addAll(itemPropertyDescriptors);
			if (!customTargetArrowType) {
				result.remove(customTargetArrowPropertyDescriptor);
			}
			if (!customSourceArrowType) {
				result.remove(customSourceArrowPropertyDescriptor);
			}
		}
		return result;
	}
	
}
