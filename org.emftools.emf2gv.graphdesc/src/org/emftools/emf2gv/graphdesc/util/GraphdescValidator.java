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

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.emftools.emf2gv.graphdesc.AbstractFigure;
import org.emftools.emf2gv.graphdesc.ArrowStyle;
import org.emftools.emf2gv.graphdesc.ArrowType;
import org.emftools.emf2gv.graphdesc.AttributeFigure;
import org.emftools.emf2gv.graphdesc.ClassFigure;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;
import org.emftools.emf2gv.graphdesc.Orientation;
import org.emftools.emf2gv.graphdesc.ReferenceFigure;
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
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Validate' of 'Class Figure'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int CLASS_FIGURE__VALIDATE = 2;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Validate' of 'Attribute Figure'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int ATTRIBUTE_FIGURE__VALIDATE = 3;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Validate' of 'Reference Figure'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int REFERENCE_FIGURE__VALIDATE = 4;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Validate' of 'GV Figure Description'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int GV_FIGURE_DESCRIPTION__VALIDATE = 1;

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
			case GraphdescPackage.CLASS_FIGURE:
				return validateClassFigure((ClassFigure)value, diagnostics, context);
			case GraphdescPackage.ATTRIBUTE_FIGURE:
				return validateAttributeFigure((AttributeFigure)value, diagnostics, context);
			case GraphdescPackage.REFERENCE_FIGURE:
				return validateReferenceFigure((ReferenceFigure)value, diagnostics, context);
			case GraphdescPackage.RICH_REFERENCE_FIGURE:
				return validateRichReferenceFigure((RichReferenceFigure)value, diagnostics, context);
			case GraphdescPackage.ABSTRACT_FIGURE:
				return validateAbstractFigure((AbstractFigure)value, diagnostics, context);
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
		if (result || diagnostics != null) result &= validateClassFigure_validate(classFigure, diagnostics, context);
		return result;
	}

	/**
	 * Validates the validate constraint of '<em>Class Figure</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateClassFigure_validate(ClassFigure classFigure, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return classFigure.validate(diagnostics, context);
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
		if (result || diagnostics != null) result &= validateAttributeFigure_validate(attributeFigure, diagnostics, context);
		return result;
	}

	/**
	 * Validates the validate constraint of '<em>Attribute Figure</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAttributeFigure_validate(AttributeFigure attributeFigure, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return attributeFigure.validate(diagnostics, context);
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
		if (result || diagnostics != null) result &= validateReferenceFigure_validate(referenceFigure, diagnostics, context);
		return result;
	}

	/**
	 * Validates the validate constraint of '<em>Reference Figure</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateReferenceFigure_validate(ReferenceFigure referenceFigure, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return referenceFigure.validate(diagnostics, context);
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
		if (result || diagnostics != null) result &= validateReferenceFigure_validate(richReferenceFigure, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAbstractFigure(AbstractFigure abstractFigure, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(abstractFigure, diagnostics, context);
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

} //GraphdescValidator
