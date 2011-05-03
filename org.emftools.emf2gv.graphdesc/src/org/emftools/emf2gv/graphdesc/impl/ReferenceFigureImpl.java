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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.emftools.emf2gv.graphdesc.AbstractReferenceFigure;
import org.emftools.emf2gv.graphdesc.ClassFigure;
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
			ClassFigure classFigure = getClassFigure();
			EMFConstraintsHelper constraintsHelper = EMFConstraintsHelper
					.getInstance(GraphdescValidator.DIAGNOSTIC_SOURCE);
			// Check unique (ignoring the rich reference figures)
			List<ReferenceFigure> referenceFigures = new ArrayList<ReferenceFigure>();
			for (AbstractReferenceFigure referenceFigure : classFigure.getReferenceFigures()) {
				if (referenceFigure instanceof ReferenceFigure) {
					if (!referenceFigures.contains(referenceFigure))
						referenceFigures.add((ReferenceFigure) referenceFigure);
				}
			}
			valid = constraintsHelper.addErrorIfNotUnique(referenceFigures,
					GraphdescPackage.eINSTANCE
							.getAbstractReferenceFigure_EReference(), diagnostic,
					this, 0,
					"The EReference ''{0}'' is referenced twice or more",
					eReference.getName());

		}
		return valid;
	}

} //ReferenceFigureImpl
