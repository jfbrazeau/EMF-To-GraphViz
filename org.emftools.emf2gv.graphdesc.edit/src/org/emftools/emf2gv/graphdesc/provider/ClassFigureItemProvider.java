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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
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
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.graphdesc.GraphdescFactory;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;

/**
 * This is the item provider adapter for a {@link org.emftools.emf2gv.graphdesc.ClassFigure} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ClassFigureItemProvider
	extends AbstractFigureItemProvider
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
	public ClassFigureItemProvider(AdapterFactory adapterFactory) {
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
			addLabelEAttributePropertyDescriptor(object);
			addHeaderBackgroundColorPropertyDescriptor(object);
			addBodyBackgroundColorPropertyDescriptor(object);
			addLabelStylePropertyDescriptor(object);

			// Container properties
			addNestedFiguresEReferencesPropertyDescriptor(object);

			// Model properties
			addEClassPropertyDescriptor(object);
			addEPackagePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the EClass feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addEClassPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(new ItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ClassFigure_eClass_feature"),
				 getString("_UI_ClassFigure_eClass_description", "_UI_ClassFigure_eClass_feature", "_UI_ClassFigure_type"),
				 GraphdescPackage.Literals.CLASS_FIGURE__ECLASS,
				 true,
				 false,
				 true,
				 null,
				 getString("_UI_ModelPropertyCategory"),
				 null) {
				@Override
				public Collection<?> getChoiceOfValues(Object object) {
					List<EClass> result = new ArrayList<EClass>();
					ClassFigure classFigure = (ClassFigure) object;
					GVFigureDescription gvFigureDesc = classFigure.getGvFigureDescription();
					if (gvFigureDesc != null) {
						for (EPackage ePackage : gvFigureDesc.getEPackages()) {
							EList<EClassifier> classifiers = ePackage.getEClassifiers();
							for (EClassifier eClassifier : classifiers) {
							if (eClassifier instanceof EClass
									&& !((EClass) eClassifier).isAbstract()) {
								if (!result.contains(eClassifier))
									result.add((EClass) eClassifier);
							}
							}
						}
						Collections.sort(result, ENAMED_ELEMENT_COMPARATOR);
					}
					return result;
				}
			});
	}

	/**
	 * This adds a property descriptor for the Label EAttribute feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addLabelEAttributePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(new ItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ClassFigure_labelEAttribute_feature"),
				 getString("_UI_ClassFigure_labelEAttribute_description", "_UI_ClassFigure_labelEAttribute_feature", "_UI_ClassFigure_type"),
				 GraphdescPackage.Literals.CLASS_FIGURE__LABEL_EATTRIBUTE,
				 true,
				 false,
				 true,
				 null,
				 getString("_UI_AppearancePropertyCategory"),
				 null) {
				@Override
				public Collection<?> getChoiceOfValues(Object object) {
					ClassFigure classFigure = (ClassFigure) object;
					List<EAttribute> result = new ArrayList<EAttribute>();
					result.add(null);
					EClass eClass = classFigure.getEClass();
					if (eClass != null) {
						result.addAll(eClass.getEAllAttributes());
						Collections.sort(result, ENAMED_ELEMENT_COMPARATOR);
					}
					return result;
				}
			});
	}

	/**
	 * This adds a property descriptor for the Label Style feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLabelStylePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ClassFigure_labelStyle_feature"),
				 getString("_UI_ClassFigure_labelStyle_description"),
				 GraphdescPackage.Literals.CLASS_FIGURE__LABEL_STYLE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 getString("_UI_AppearancePropertyCategory"),
				 null));
	}

	/**
	 * This adds a property descriptor for the Header Background Color feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addHeaderBackgroundColorPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ClassFigure_headerBackgroundColor_feature"),
				 getString("_UI_ClassFigure_headerBackgroundColor_description"),
				 GraphdescPackage.Literals.CLASS_FIGURE__HEADER_BACKGROUND_COLOR,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 getString("_UI_AppearancePropertyCategory"),
				 null));
	}

	/**
	 * This adds a property descriptor for the Body Background Color feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addBodyBackgroundColorPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ClassFigure_bodyBackgroundColor_feature"),
				 getString("_UI_ClassFigure_bodyBackgroundColor_description"),
				 GraphdescPackage.Literals.CLASS_FIGURE__BODY_BACKGROUND_COLOR,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 getString("_UI_AppearancePropertyCategory"),
				 null));
	}

	/**
	 * This adds a property descriptor for the Nested Figures EReferences feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addNestedFiguresEReferencesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(new ItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ClassFigure_nestedFiguresEReferences_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ClassFigure_nestedFiguresEReferences_feature", "_UI_ClassFigure_type"),
				 GraphdescPackage.Literals.CLASS_FIGURE__NESTED_FIGURES_EREFERENCES,
				 true,
				 false,
				 true,
				 null,
				 getString("_UI_ContainerPropertyCategory"),
				 null) {
				@Override
				public Collection<?> getChoiceOfValues(Object object) {
					List<EReference> result = new ArrayList<EReference>();
					ClassFigure classFigure = (ClassFigure) object;
					EClass eClass = classFigure.getEClass();
					if (eClass != null) {
						result.addAll(eClass.getEAllContainments());
						Collections.sort(result, ENAMED_ELEMENT_COMPARATOR);
					}
					return result;
				}
			});
	}

	/**
	 * This adds a property descriptor for the EPackage feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEPackagePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ClassFigure_ePackage_feature"),
				 getString("_UI_ClassFigure_ePackage_description"),
				 GraphdescPackage.Literals.CLASS_FIGURE__EPACKAGE,
				 false,
				 false,
				 false,
				 null,
				 getString("_UI_ModelPropertyCategory"),
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
			childrenFeatures.add(GraphdescPackage.Literals.CLASS_FIGURE__ATTRIBUTE_FIGURES);
			childrenFeatures.add(GraphdescPackage.Literals.CLASS_FIGURE__REFERENCE_FIGURES);
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
	 * This returns ClassFigure.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ClassFigure"));
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
		String label = ((ClassFigure)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_ClassFigure_type") :
			getString("_UI_ClassFigure_type") + " " + label;
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

		switch (notification.getFeatureID(ClassFigure.class)) {
			case GraphdescPackage.CLASS_FIGURE__LABEL_STYLE:
			case GraphdescPackage.CLASS_FIGURE__HEADER_BACKGROUND_COLOR:
			case GraphdescPackage.CLASS_FIGURE__BODY_BACKGROUND_COLOR:
			case GraphdescPackage.CLASS_FIGURE__CONTAINER:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case GraphdescPackage.CLASS_FIGURE__ATTRIBUTE_FIGURES:
			case GraphdescPackage.CLASS_FIGURE__REFERENCE_FIGURES:
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
				(GraphdescPackage.Literals.CLASS_FIGURE__ATTRIBUTE_FIGURES,
				 GraphdescFactory.eINSTANCE.createAttributeFigure()));

		newChildDescriptors.add
			(createChildParameter
				(GraphdescPackage.Literals.CLASS_FIGURE__ATTRIBUTE_FIGURES,
				 GraphdescFactory.eINSTANCE.createRichAttributeFigure()));

		newChildDescriptors.add
			(createChildParameter
				(GraphdescPackage.Literals.CLASS_FIGURE__REFERENCE_FIGURES,
				 GraphdescFactory.eINSTANCE.createReferenceFigure()));

		newChildDescriptors.add
			(createChildParameter
				(GraphdescPackage.Literals.CLASS_FIGURE__REFERENCE_FIGURES,
				 GraphdescFactory.eINSTANCE.createRichReferenceFigure()));
	}

}
