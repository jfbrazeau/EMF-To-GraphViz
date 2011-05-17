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
import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.ocl.util.TypeUtil;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.emftools.emf2gv.graphdesc.AbstractFigure;
import org.emftools.emf2gv.graphdesc.AttributeFigure;
import org.emftools.emf2gv.graphdesc.ClassFigure;
import org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider;
import org.emftools.emf2gv.graphdesc.Filter;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;
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
			OCL ocl = null;
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
			}
			else if (object instanceof DynamicPropertyOverrider) {
				DynamicPropertyOverrider dpo = (DynamicPropertyOverrider) object;
				if (dpo != null) {
					EStructuralFeature propertyFeature = dpo.getPropertyToOverride();
					if (propertyFeature != null) {
						oclExpectedReturnType = propertyFeature.getEType();
						// If the feature is a list, we must resolve a collection type
						if (propertyFeature.getUpperBound() != 1) {
							ocl = OCLProvider.newOCL();
							oclExpectedReturnType = TypeUtil
									.resolveCollectionType(
											ocl.getEnvironment(),
											CollectionKind.COLLECTION_LITERAL,
											oclExpectedReturnType);
						}
					}
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
			// OCL expression are only used in RichAttributeFigures
			final String theMissingContextErrorMessage = missingContextErrorMessage;
			result = new OCLPropertyDescriptor(object, itemPropertyDescriptor, ocl, oclContext, oclExpectedReturnType) {
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
