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
