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
package org.emftools.emf2gv.graphdesc.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.emftools.emf2gv.graphdesc.AbstractAttributeFigure;
import org.emftools.emf2gv.graphdesc.AbstractFigure;
import org.emftools.emf2gv.graphdesc.AbstractReferenceFigure;
import org.emftools.emf2gv.graphdesc.ArrowStyle;
import org.emftools.emf2gv.graphdesc.ArrowType;
import org.emftools.emf2gv.graphdesc.AttributeFigure;
import org.emftools.emf2gv.graphdesc.ClassFigure;
import org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider;
import org.emftools.emf2gv.graphdesc.Filter;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;
import org.emftools.emf2gv.graphdesc.Orientation;
import org.emftools.emf2gv.graphdesc.ReferenceFigure;
import org.emftools.emf2gv.graphdesc.RichAttributeFigure;
import org.emftools.emf2gv.graphdesc.RichReferenceFigure;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage
 * @generated
 */
public class GraphdescValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final GraphdescValidator INSTANCE = new GraphdescValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "org.emftools.emf2gv.graphdesc";

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Validate' of 'GV Figure Description'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int GV_FIGURE_DESCRIPTION__VALIDATE = 1;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Validate' of 'Abstract Figure'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int ABSTRACT_FIGURE__VALIDATE = 2;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Validate' of 'Dynamic Property Overrider'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int DYNAMIC_PROPERTY_OVERRIDER__VALIDATE = 3;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Validate' of 'Filter'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int FILTER__VALIDATE = 4;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 4;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphdescValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return GraphdescPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case GraphdescPackage.GV_FIGURE_DESCRIPTION:
				return validateGVFigureDescription((GVFigureDescription)value, diagnostics, context);
			case GraphdescPackage.ABSTRACT_FIGURE:
				return validateAbstractFigure((AbstractFigure)value, diagnostics, context);
			case GraphdescPackage.CLASS_FIGURE:
				return validateClassFigure((ClassFigure)value, diagnostics, context);
			case GraphdescPackage.ABSTRACT_ATTRIBUTE_FIGURE:
				return validateAbstractAttributeFigure((AbstractAttributeFigure)value, diagnostics, context);
			case GraphdescPackage.ATTRIBUTE_FIGURE:
				return validateAttributeFigure((AttributeFigure)value, diagnostics, context);
			case GraphdescPackage.RICH_ATTRIBUTE_FIGURE:
				return validateRichAttributeFigure((RichAttributeFigure)value, diagnostics, context);
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE:
				return validateAbstractReferenceFigure((AbstractReferenceFigure)value, diagnostics, context);
			case GraphdescPackage.REFERENCE_FIGURE:
				return validateReferenceFigure((ReferenceFigure)value, diagnostics, context);
			case GraphdescPackage.RICH_REFERENCE_FIGURE:
				return validateRichReferenceFigure((RichReferenceFigure)value, diagnostics, context);
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER:
				return validateDynamicPropertyOverrider((DynamicPropertyOverrider)value, diagnostics, context);
			case GraphdescPackage.FILTER:
				return validateFilter((Filter)value, diagnostics, context);
			case GraphdescPackage.ORIENTATION:
				return validateOrientation((Orientation)value, diagnostics, context);
			case GraphdescPackage.ARROW_TYPE:
				return validateArrowType((ArrowType)value, diagnostics, context);
			case GraphdescPackage.ARROW_STYLE:
				return validateArrowStyle((ArrowStyle)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateClassFigure(ClassFigure classFigure, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(classFigure, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(classFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(classFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(classFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(classFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(classFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(classFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(classFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(classFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validateAbstractFigure_validate(classFigure, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAttributeFigure(AttributeFigure attributeFigure, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(attributeFigure, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(attributeFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(attributeFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(attributeFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(attributeFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(attributeFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(attributeFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(attributeFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(attributeFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validateAbstractFigure_validate(attributeFigure, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRichReferenceFigure(RichReferenceFigure richReferenceFigure, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(richReferenceFigure, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(richReferenceFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(richReferenceFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(richReferenceFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(richReferenceFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(richReferenceFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(richReferenceFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(richReferenceFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(richReferenceFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validateAbstractFigure_validate(richReferenceFigure, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDynamicPropertyOverrider(DynamicPropertyOverrider dynamicPropertyOverrider, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(dynamicPropertyOverrider, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(dynamicPropertyOverrider, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(dynamicPropertyOverrider, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(dynamicPropertyOverrider, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(dynamicPropertyOverrider, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(dynamicPropertyOverrider, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(dynamicPropertyOverrider, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(dynamicPropertyOverrider, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(dynamicPropertyOverrider, diagnostics, context);
		if (result || diagnostics != null) result &= validateDynamicPropertyOverrider_validate(dynamicPropertyOverrider, diagnostics, context);
		return result;
	}

	/**
	 * Validates the validate constraint of '<em>Dynamic Property Overrider</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDynamicPropertyOverrider_validate(DynamicPropertyOverrider dynamicPropertyOverrider, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return dynamicPropertyOverrider.validate(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFilter(Filter filter, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(filter, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(filter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(filter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(filter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(filter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(filter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(filter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(filter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(filter, diagnostics, context);
		if (result || diagnostics != null) result &= validateFilter_validate(filter, diagnostics, context);
		return result;
	}

	/**
	 * Validates the validate constraint of '<em>Filter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFilter_validate(Filter filter, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return filter.validate(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAbstractFigure(AbstractFigure abstractFigure, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(abstractFigure, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(abstractFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(abstractFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(abstractFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(abstractFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(abstractFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(abstractFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(abstractFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(abstractFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validateAbstractFigure_validate(abstractFigure, diagnostics, context);
		return result;
	}

	/**
	 * Validates the validate constraint of '<em>Abstract Figure</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAbstractFigure_validate(AbstractFigure abstractFigure, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return abstractFigure.validate(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAbstractReferenceFigure(AbstractReferenceFigure abstractReferenceFigure, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(abstractReferenceFigure, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(abstractReferenceFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(abstractReferenceFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(abstractReferenceFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(abstractReferenceFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(abstractReferenceFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(abstractReferenceFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(abstractReferenceFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(abstractReferenceFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validateAbstractFigure_validate(abstractReferenceFigure, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateReferenceFigure(ReferenceFigure referenceFigure, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(referenceFigure, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(referenceFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(referenceFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(referenceFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(referenceFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(referenceFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(referenceFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(referenceFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(referenceFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validateAbstractFigure_validate(referenceFigure, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAbstractAttributeFigure(AbstractAttributeFigure abstractAttributeFigure, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(abstractAttributeFigure, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(abstractAttributeFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(abstractAttributeFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(abstractAttributeFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(abstractAttributeFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(abstractAttributeFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(abstractAttributeFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(abstractAttributeFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(abstractAttributeFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validateAbstractFigure_validate(abstractAttributeFigure, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRichAttributeFigure(RichAttributeFigure richAttributeFigure, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(richAttributeFigure, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(richAttributeFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(richAttributeFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(richAttributeFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(richAttributeFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(richAttributeFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(richAttributeFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(richAttributeFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(richAttributeFigure, diagnostics, context);
		if (result || diagnostics != null) result &= validateAbstractFigure_validate(richAttributeFigure, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGVFigureDescription(GVFigureDescription gvFigureDescription, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(gvFigureDescription, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(gvFigureDescription, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(gvFigureDescription, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(gvFigureDescription, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(gvFigureDescription, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(gvFigureDescription, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(gvFigureDescription, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(gvFigureDescription, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(gvFigureDescription, diagnostics, context);
		if (result || diagnostics != null) result &= validateGVFigureDescription_validate(gvFigureDescription, diagnostics, context);
		return result;
	}

	/**
	 * Validates the validate constraint of '<em>GV Figure Description</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGVFigureDescription_validate(GVFigureDescription gvFigureDescription, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return gvFigureDescription.validate(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOrientation(Orientation orientation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateArrowType(ArrowType arrowType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateArrowStyle(ArrowStyle arrowStyle, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}
	
	
	/**
	 * Utility method used to check that an <code>EReference</code> is not used
	 * twice or more.
	 * 
	 * @param figure
	 *            the figure.
	 * @return a boolean indicating if the <code>EReference</code> is used twice
	 *         or more.
	 */
	public static boolean eReferenceIsUsedTwiceOrMore(AbstractFigure figure) {
		ClassFigure classFigure = null;
		EReference eReference = getFigureEReference(figure);
		if (figure instanceof AbstractReferenceFigure) {
			AbstractReferenceFigure referenceFigure = (AbstractReferenceFigure) figure;
			classFigure = referenceFigure.getClassFigure();
		} else if (figure instanceof RichAttributeFigure) {
			RichAttributeFigure richAttrFigure = (RichAttributeFigure) figure;
			classFigure = richAttrFigure.getClassFigure();
		}
		boolean result = false;
		if (classFigure != null && eReference != null) {
			List<AbstractFigure> figures = new ArrayList<AbstractFigure>();
			figures.addAll(classFigure.getReferenceFigures());
			figures.remove(figure);
			result = containsEReference(figures, eReference);
			if (!result) {
				figures.clear();
				figures.addAll(classFigure.getAttributeFigures());
				figures.remove(figure);
				result = containsEReference(figures, eReference);
			}
		}
		return result;
	}

	/**
	 * Checks if an <code>EReference</code> is used by any of the specified
	 * figure list.
	 * 
	 * @param figures
	 *            the figures to check.
	 * @param eReference
	 *            the <code>EReference</code>
	 * @return a boolean indicating if an <code>EReference</code> is used by any
	 *         of the specified figure list.
	 */
	private static boolean containsEReference(List<AbstractFigure> figures,
			EReference eReference) {
		for (AbstractFigure figure : figures) {
			EReference figureEReference = getFigureEReference(figure);
			if (eReference == figureEReference) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Return the <code>EReference</code> used by the specified figure.
	 * 
	 * @param figure
	 *            the figure.
	 * @return the <code>EReference</code>
	 */
	private static EReference getFigureEReference(AbstractFigure figure) {
		EReference eReference = null;
		if (figure instanceof AbstractReferenceFigure) {
			AbstractReferenceFigure referenceFigure = (AbstractReferenceFigure) figure;
			eReference = referenceFigure.getEReference();
		} else if (figure instanceof RichAttributeFigure) {
			RichAttributeFigure richAttrFigure = (RichAttributeFigure) figure;
			eReference = richAttrFigure.getEReference();
		}
		return eReference;
	}

} //GraphdescValidator
