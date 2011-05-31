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
