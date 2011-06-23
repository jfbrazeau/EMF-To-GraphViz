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
package org.emftools.emf2gv.util;

import java.util.Collection;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ocl.Environment;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.ocl.expressions.Variable;
import org.eclipse.ocl.util.TypeUtil;

/**
 * This class provides several helper methods about OCL.
 */
public class OCLHelper {

	/**
	 * Indicates if 2 types are compatible.
	 * 
	 * @param environment
	 *            the OCL environment.
	 * @param type1
	 *            the first type.
	 * @param type2
	 *            the second type.
	 * @return a boolean indicating if the 2 types are compatible.
	 */
	public static boolean compatibleTypeMatch(
			Environment<?, EClassifier, EOperation, EStructuralFeature, ?, ?, ?, ?, ?, ?, ?, ?> environment,
			EClassifier type1, EClassifier type2) {
		boolean matches = ((type1.getInstanceClass() != null
				&& type2.getInstanceClass() != null && type1.getInstanceClass()
				.equals(type2.getInstanceClass())) || TypeUtil
				.compatibleTypeMatch(environment, type1, type2));
		return matches;
	}

	/**
	 * Transforms an feature to an OCL type.
	 * 
	 * @param environment
	 *            the OCL environment.
	 * @param feature
	 *            the feature.
	 * @return the OCL type.
	 */
	public static EClassifier toOCLFeatureType(
			Environment<?, EClassifier, EOperation, EStructuralFeature, ?, ?, ?, ?, ?, ?, ?, ?> environment,
			EStructuralFeature feature) {
		EClassifier oclFeatureType = null;
		if (feature != null) {
			oclFeatureType = feature.getEType();
			if (feature.getUpperBound() != 1) {
				oclFeatureType = TypeUtil.resolveCollectionType(environment,
						CollectionKind.COLLECTION_LITERAL, oclFeatureType);
			}
		}
		return oclFeatureType;
	}

	/**
	 * Deletes the variables from an OCL environment.
	 * 
	 * @param ocl
	 *            the OCL.
	 */
	public static void clearVariables(OCL ocl) {
		Collection<Variable<EClassifier, EParameter>> vars = ocl
				.getEnvironment().getVariables();
		for (Variable<EClassifier, EParameter> variable : vars) {
			ocl.getEvaluationEnvironment().remove(variable.getName());
			ocl.getEnvironment().deleteElement(variable.getName());
		}
	}
}
