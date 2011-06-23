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
package org.emftools.emf2gv.graphdesc.presentation.util;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.emftools.emf2gv.graphdesc.AbstractFigure;
import org.emftools.emf2gv.graphdesc.AttributeFigure;
import org.emftools.emf2gv.graphdesc.ClassFigure;
import org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider;
import org.emftools.emf2gv.graphdesc.Filter;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;
import org.emftools.emf2gv.graphdesc.util.GraphdescHelper;
import org.emftools.emf2gv.util.OCLHelper;
import org.emftools.emf2gv.util.OCLProvider;

/**
 * The graphical description property source.
 * 
 * @author jbrazeau
 */
public class GraphdescPropertySource extends PropertySource {

	/** Error message constants */
	private static final String SELECT_ECLASS_MSG = "Please select an EClass at first in the figure.";
	private static final String SELECT_EREF_MSG1 = "Please select an EReference at first.";
	private static final String SELECT_EREF_MSG2 = "Please select an EReference at first in the figure.";
	private static final String SELECT_FILTER_TYPE_MSG = "Please select the filter type at first.";

	/** The color icons map */
	private Map<String, Image> colorIcons;

	/**
	 * Default constructor.
	 * 
	 * @param object
	 *            the edited object.
	 * @param itemPropertySource
	 *            the item property source.
	 * @param colorIcons
	 *            the color icons map.
	 */
	public GraphdescPropertySource(Object object,
			IItemPropertySource itemPropertySource,
			Map<String, Image> colorIcons) {
		super(object, itemPropertySource);
		this.colorIcons = colorIcons;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.edit.ui.provider.PropertySource#createPropertyDescriptor
	 * (org.eclipse.emf.edit.provider.IItemPropertyDescriptor)
	 */
	protected IPropertyDescriptor createPropertyDescriptor(
			IItemPropertyDescriptor itemPropertyDescriptor) {
		IPropertyDescriptor result = null;
		GraphdescPackage gdPkg = GraphdescPackage.eINSTANCE;
		Object feature = itemPropertyDescriptor.getFeature(object);
		boolean arrowTypeFeature = (feature == gdPkg
				.getAbstractReferenceFigure_SourceArrowType() || feature == gdPkg
				.getAbstractReferenceFigure_TargetArrowType());
		boolean colorFeature = (feature == gdPkg
				.getClassFigure_HeaderBackgroundColor() || feature == gdPkg
				.getClassFigure_BodyBackgroundColor())
				|| feature == gdPkg.getAbstractReferenceFigure_Color();
		boolean oclFeature = (feature == gdPkg
				.getRichAttributeFigure_LabelExpression()
				|| feature == gdPkg
						.getRichReferenceFigure_SourceLabelExpression()
				|| feature == gdPkg
						.getRichReferenceFigure_StandardLabelExpression()
				|| feature == gdPkg
						.getRichReferenceFigure_TargetLabelExpression()
				|| feature == gdPkg
						.getDynamicPropertyOverrider_OverridingExpression() || feature == gdPkg
				.getFilter_FilterExpression());
		if (arrowTypeFeature) {
			result = new ArrowTypePropertyDescriptor(object,
					itemPropertyDescriptor);
		} else if (colorFeature) {
			result = new ColorPropertyDescriptor(object,
					itemPropertyDescriptor, colorIcons);
		} else if (oclFeature) {
			OCL ocl = OCLProvider.newOCL();
			EClass oclContext = null;
			EClassifier oclExpectedReturnType = null;
			String missingContextErrorMessage = null;
			if (object instanceof AbstractFigure) {
				oclContext = ((AbstractFigure) object).getStandardOCLContext();
				missingContextErrorMessage = SELECT_EREF_MSG1;
			} else if (object instanceof Filter) {
				Filter filter = (Filter) object;
				oclContext = filter.getFilteredType();
				missingContextErrorMessage = SELECT_FILTER_TYPE_MSG;
			} else if (object instanceof DynamicPropertyOverrider) {
				DynamicPropertyOverrider dpo = (DynamicPropertyOverrider) object;
				if (dpo != null) {
					// OCL expected return type retrieval + collection type
					// handling
					EStructuralFeature propertyFeature = dpo
							.getPropertyToOverride();
					oclExpectedReturnType = OCLHelper.toOCLFeatureType(ocl.getEnvironment(), propertyFeature);
					if (oclExpectedReturnType != null) {
						// "Default property value" variable registration
						GraphdescHelper
								.registerDefaultPropertyValueOCLVariable(
										ocl.getEnvironment(), dpo);
					}
					// Error message building
					AbstractFigure figure = dpo.getFigure();
					if (figure != null) {
						oclContext = figure.getStandardOCLContext();
						if (figure instanceof ClassFigure
								|| figure instanceof AttributeFigure) {
							missingContextErrorMessage = SELECT_ECLASS_MSG;
						}
						// Other cases (ie. RichAttributeFigure,
						// AbstractReferenceFigure)
						else {
							missingContextErrorMessage = SELECT_EREF_MSG2;
						}
					}

				}
			}
			final String theMissingContextErrorMessage = missingContextErrorMessage;
			result = new OCLPropertyDescriptor(object, itemPropertyDescriptor,
					ocl, oclContext, oclExpectedReturnType) {
				/*
				 * (non-Javadoc)
				 * 
				 * @see org.emftools.emf2gv.graphdesc.presentation.util.
				 * OCLPropertyDescriptor#getMissingContextMessage()
				 */
				protected String getMissingContextMessage() {
					// This is the message to show if no context is defined
					// (ie. is no EReference is selected)
					return theMissingContextErrorMessage;
				};
			};
		} else {
			result = super.createPropertyDescriptor(itemPropertyDescriptor);
		}
		return result;
	}

}
