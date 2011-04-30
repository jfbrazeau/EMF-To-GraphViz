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
package org.emftools.emf2gv.processor.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.ecore.EcoreEvaluationEnvironment;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.ocl.expressions.ExpressionsFactory;
import org.eclipse.ocl.expressions.Variable;
import org.eclipse.ocl.util.TypeUtil;

/**
 * Helper class that builds an OCL evaluation environment with custom operations
 * like <code>startsWith</code>, <code>matches</code>, etc.
 */
public class OCLProvider {

	/** "Starts with" custom operation name */
	protected static final String STARTS_WITH = "startsWith";

	/** "Ends with" custom operation name */
	protected static final String ENDS_WITH = "endsWith";

	/** "Matches" custom operation name */
	protected static final String MATCHES = "matches";

	/** "Contains" custom operation name */
	protected static final String CONTAINS = "contains";

	/** "Split" custom operation name */
	protected static final String SPLIT = "split";

	/** "Plus" custom operation name */
	protected static final String PLUS = "+";

	/**
	 * @return a new OCL with additional operations.
	 */
	public static OCL newOCL() {
		// OCL creation (with a custom evaluation environment factory
		EcoreEnvironmentFactory environmentFactory = new EcoreEnvironmentFactory() {
			@Override
			public EvaluationEnvironment<EClassifier, EOperation, EStructuralFeature, EClass, EObject> createEvaluationEnvironment() {
				return new CustomEvaluationEnvironment();
			}
		};
		OCL ocl = OCL.newInstance(environmentFactory);

		// Custom operation declaration
		EClassifier stringClassifier = ocl.getEnvironment()
				.getOCLStandardLibrary().getString();
		EClassifier booleanClassifier = ocl.getEnvironment()
				.getOCLStandardLibrary().getBoolean();
		EClassifier stringCollectionClassifier = TypeUtil
				.resolveCollectionType(ocl.getEnvironment(),
						CollectionKind.COLLECTION_LITERAL, stringClassifier);
		EClassifier oclAnyClassifier = ocl.getEnvironment()
				.getOCLStandardLibrary().getOclAny();

		// String context operation
		defineOperation(ocl, STARTS_WITH, stringClassifier, booleanClassifier,
				buildSingleStringParameterList(ocl, "string"));
		defineOperation(ocl, ENDS_WITH, stringClassifier, booleanClassifier,
				buildSingleStringParameterList(ocl, "string"));
		defineOperation(ocl, MATCHES, stringClassifier, booleanClassifier,
				buildSingleStringParameterList(ocl, "regexp"));
		defineOperation(ocl, CONTAINS, stringClassifier, booleanClassifier,
				buildSingleStringParameterList(ocl, "string"));
		defineOperation(ocl, SPLIT, stringClassifier,
				stringCollectionClassifier,
				buildSingleStringParameterList(ocl, "separators"));
		defineOperation(ocl, PLUS, oclAnyClassifier, stringClassifier,
				buildSingleParameterList(ocl, oclAnyClassifier, "tobeadded"));
		return ocl;
	}

	/**
	 * Declares a new operation.
	 * 
	 * @param ocl
	 *            the OCL.
	 * @param operationName
	 *            the operation name.
	 * @param owner
	 *            the operation owner.
	 * @param returnType
	 *            the operation type.
	 * @param parameters
	 *            the operation parameters.
	 * @return the new operation definition.
	 */
	private static EOperation defineOperation(OCL ocl, String operationName,
			EClassifier owner, EClassifier returnType,
			List<Variable<EClassifier, EParameter>> parameters) {
		Constraint c = org.eclipse.ocl.ecore.EcoreFactory.eINSTANCE
				.createConstraint();
		EOperation eOperation = ocl.getEnvironment().defineOperation(owner,
				operationName, returnType, parameters, c);
		return eOperation;
	}

	/**
	 * Builds a variable list containing one only string entry.
	 * 
	 * @param ocl
	 *            the OCL.
	 * @param variableName
	 *            the variable name.
	 * @return the variables list.
	 */
	private static List<Variable<EClassifier, EParameter>> buildSingleStringParameterList(
			OCL ocl, String variableName) {
		return buildSingleParameterList(ocl, ocl.getEnvironment()
				.getOCLStandardLibrary().getString(), variableName);
	}

	/**
	 * Builds a variable list containing one only string entry.
	 * 
	 * @param ocl
	 *            the OCL.
	 * @param parameterType
	 *            the parameter type.
	 * @param variableName
	 *            the variable name.
	 * @return the variables list.
	 */
	private static List<Variable<EClassifier, EParameter>> buildSingleParameterList(
			OCL ocl, EClassifier parameterType, String variableName) {
		List<Variable<EClassifier, EParameter>> vars = new ArrayList<Variable<EClassifier, EParameter>>();
		Variable<EClassifier, EParameter> variable = ExpressionsFactory.eINSTANCE
				.createVariable();
		variable.setName(variableName);
		variable.setType(parameterType);
		vars.add(variable);
		return vars;
	}

}

/**
 * Custom OCL evaluation environment with custom operations implementations.
 */
class CustomEvaluationEnvironment extends EcoreEvaluationEnvironment {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ocl.ecore.EcoreEvaluationEnvironment#
	 * callOperation(org.eclipse.emf.ecore.EOperation, int, java.lang.Object,
	 * java.lang.Object[])
	 */
	@Override
	public Object callOperation(EOperation operation, int opcode,
			Object source, Object[] args) throws IllegalArgumentException {
		if (operation.getName().equals(OCLProvider.PLUS)) {
			if (source instanceof Number && args[0] instanceof Number) {
				return ((Number) source).doubleValue()
						+ ((Number) args[0]).doubleValue();
			} else {
				return String.valueOf(source) + String.valueOf(args[0]);
			}
		}
		if (source instanceof String) {
			return callOperation(operation, opcode, (String) source, args);
		} else {
			return super.callOperation(operation, opcode, source, args);
		}
	}

	/**
	 * Calls an EOperation for a given string source.
	 * 
	 * @param operation
	 *            the EOperation to call.
	 * @param opcode
	 *            the opcode.
	 * @param source
	 *            the source string on wich is applied the operation.
	 * @param args
	 *            the operation arguments.
	 * @return the operation result.
	 * @throws IllegalArgumentException
	 *             thrown if the arguments are not valid.
	 */
	public Object callOperation(EOperation operation, int opcode,
			String source, Object[] args) throws IllegalArgumentException {
		if (operation.getName().equals(OCLProvider.STARTS_WITH)) {
			return ((String) source).startsWith((String) args[0]);
		} else if (operation.getName().equals(OCLProvider.ENDS_WITH)) {
			return ((String) source).endsWith((String) args[0]);
		} else if (operation.getName().equals(OCLProvider.MATCHES)) {
			return ((String) source).matches((String) args[0]);
		} else if (operation.getName().equals(OCLProvider.CONTAINS)) {
			return ((String) source).contains((String) args[0]);
		} else if (operation.getName().equals(OCLProvider.SPLIT)) {
			String[] array = ((String) source).split((String) args[0]);
			for (int i = 0; i < array.length; i++) {
				array[i] = array[i].trim();
			}
			return Arrays.asList(array);
		} else {
			return super.callOperation(operation, opcode, source, args);
		}
	}

}