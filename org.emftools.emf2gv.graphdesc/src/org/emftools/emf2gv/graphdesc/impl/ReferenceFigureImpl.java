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
package org.emftools.emf2gv.graphdesc.impl;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.emftools.emf2gv.graphdesc.ClassFigure;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;
import org.emftools.emf2gv.graphdesc.ReferenceFigure;
import org.emftools.emf2gv.graphdesc.util.GraphdescValidator;
import org.emftools.validation.utils.EMFConstraintsHelper;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reference Figure</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class ReferenceFigureImpl extends AbstractReferenceFigureImpl implements ReferenceFigure {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReferenceFigureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GraphdescPackage.Literals.REFERENCE_FIGURE;
	}

	/** (non-Javadoc)
	 * @see org.emftools.emf2gv.graphdesc.impl.AbstractReferenceFigureImpl#validate(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 * @generated NOT
	 */
	@Override
	public boolean validate(DiagnosticChain diagnostic,
			Map<Object, Object> context) {
		boolean valid = super.validate(diagnostic, context);
		if (valid) {
			EMFConstraintsHelper constraintsHelper = EMFConstraintsHelper
					.getInstance(GraphdescValidator.DIAGNOSTIC_SOURCE);
			// At least on of the EReference target type subclasses must be
			// declared in the graph description (if the instance is not a
			// rich reference figure)
			if (!targetClassFigureExists()) {
				constraintsHelper
						.addError(
								diagnostic,
								this,
								0,
								"There is no class figure associated to the reference type ({0}) or one of its sub-EClass",
								eReference.getEReferenceType()
										.getName());
				valid = false;
			}
		}
		return valid;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean targetClassFigureExists() {
		boolean refTypeEClassOrSubEClassFigureFound = false;
		EClass targetEType = getTargetEType();
		if (targetEType != null) {
			ClassFigure classFigure = getClassFigure();
			if (classFigure != null) {
				GVFigureDescription gvFigureDescription = classFigure
						.getGvFigureDescription();

				if (gvFigureDescription != null) {
					// If the ref type has a ClassFigure, that's OK !
					if (gvFigureDescription.getClassFigure(targetEType) != null) {
						refTypeEClassOrSubEClassFigureFound = true;
					}
					// Else let's see if there is a ClassFigure that is
					// associated
					// to an EClass that derives from the targetEType
					else {
						List<ClassFigure> classFigures = gvFigureDescription
								.getClassFigures();
						for (int i = 0; i < classFigures.size()
								&& !refTypeEClassOrSubEClassFigureFound; i++) {
							ClassFigure classFigureCursor = classFigures.get(i);
							EClass eClass = classFigureCursor.getEClass();
							if (eClass != null) {
								refTypeEClassOrSubEClassFigureFound = targetEType
										.isSuperTypeOf(eClass);
							}
						}
					}
				}
			}
		}
		return refTypeEClassOrSubEClassFigureFound;
	}

} //ReferenceFigureImpl
