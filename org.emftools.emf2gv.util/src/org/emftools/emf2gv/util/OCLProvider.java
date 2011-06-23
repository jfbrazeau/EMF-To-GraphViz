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

import java.awt.Color;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.ecore.EcoreEvaluationEnvironment;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.ocl.expressions.ExpressionsFactory;
import org.eclipse.ocl.expressions.Variable;
import org.eclipse.ocl.types.OCLStandardLibrary;
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

	/** "toString" custom operation name */
	protected static final String TO_STRING = "toString";

	/**
	 * Custom operation allowing to create a new instance of
	 * <code>java.awt.Color</code> with its red/green/blue values
	 */
	protected static final String NEW_COLOR = "newColor";

	/**
	 * Custom operation allowing to create a new instance of
	 * <code>java.awt.Color</code> with an HTML color (hex)
	 */
	protected static final String HEX_TO_COLOR = "hexToColor";

	/** "toEcoreDiagString" custom operation name */
	protected static final String TO_ECORE_DIAG_STRING = "toEcoreDiagString";

	/** Empty parameters list constant */
	private static final List<Variable<EClassifier, EParameter>> EMPTY_PARAMETERS = new ArrayList<Variable<EClassifier, EParameter>>();

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
		OCLStandardLibrary<EClassifier> stdlib = ocl.getEnvironment()
				.getOCLStandardLibrary();

		/**
		 * Custom operation declaration
		 */
		// String context operation
		defineOperation(ocl, STARTS_WITH, stdlib.getString(),
				stdlib.getBoolean(),
				buildParameterList(ocl, stdlib.getString(), "string"));
		defineOperation(ocl, ENDS_WITH, stdlib.getString(),
				stdlib.getBoolean(),
				buildParameterList(ocl, stdlib.getString(), "string"));
		defineOperation(ocl, MATCHES, stdlib.getString(), stdlib.getBoolean(),
				buildParameterList(ocl, stdlib.getString(), "regexp"));
		defineOperation(ocl, CONTAINS, stdlib.getString(), stdlib.getBoolean(),
				buildParameterList(ocl, stdlib.getString(), "string"));
		EClassifier stringCollectionClassifier = TypeUtil
				.resolveCollectionType(ocl.getEnvironment(),
						CollectionKind.COLLECTION_LITERAL, stdlib.getString());
		defineOperation(ocl, SPLIT, stdlib.getString(),
				stringCollectionClassifier,
				buildParameterList(ocl, stdlib.getString(), "separators"));

		// OclAny context operations
		defineOperation(ocl, PLUS, stdlib.getOclAny(), stdlib.getString(),
				buildParameterList(ocl, stdlib.getOclAny(), "tobeadded"));

		// Collection context operations
		defineOperation(ocl, TO_STRING, stdlib.getCollection(),
				stdlib.getString(), EMPTY_PARAMETERS);

		// EOperation context operations
		defineOperation(ocl, TO_ECORE_DIAG_STRING,
				EcorePackage.eINSTANCE.getEOperation(), stdlib.getString(),
				EMPTY_PARAMETERS);

		/**
		 * Custom functions to be used in the DynamicPropertyOverrider
		 */
		EDataType colorDataType = EcoreFactory.eINSTANCE.createEDataType();
		colorDataType.setInstanceClass(Color.class);
		EClassifier colorClassifier = TypeUtil.resolveType(
				ocl.getEnvironment(), colorDataType);
		defineOperation(
				ocl,
				NEW_COLOR,
				stdlib.getOclAny(),
				colorClassifier,
				buildParameterList(ocl, stdlib.getInteger(), "red", "green",
						"blue"));
		defineOperation(ocl, HEX_TO_COLOR, stdlib.getOclAny(), colorClassifier,
				buildParameterList(ocl, stdlib.getString(), "hexColor"));

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
	 * @param parameterType
	 *            the parameter type.
	 * @param variableNames
	 *            the variable names.
	 * @return the variables list.
	 */
	private static List<Variable<EClassifier, EParameter>> buildParameterList(
			OCL ocl, EClassifier parameterType, String... variableNames) {
		List<Variable<EClassifier, EParameter>> vars = new ArrayList<Variable<EClassifier, EParameter>>();
		for (String variableName : variableNames) {
			Variable<EClassifier, EParameter> variable = ExpressionsFactory.eINSTANCE
					.createVariable();
			variable.setName(variableName);
			variable.setType(parameterType);
			vars.add(variable);
		}
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
		} else if (operation.getName().equals(OCLProvider.TO_STRING)
				&& source instanceof Collection<?>) {
			Collection<?> collection = (Collection<?>) source;
			StringWriter w = new StringWriter();
			boolean first = true;
			for (Object object : collection) {
				if (!first) {
					w.append(", ");
				} else {
					first = false;
				}
				w.append(String.valueOf(object));
			}
			return w.toString();
		} else if (operation.getName().equals(OCLProvider.NEW_COLOR)) {
			return new Color((Integer) args[0], (Integer) args[1],
						(Integer) args[2]);
		} else if (operation.getName().equals(OCLProvider.HEX_TO_COLOR)) {
			try {
				return ColorsHelper.parseHtmlColor((String) args[0]);
			} catch (NumberFormatException e) {
				// Ignored, if the html color is not valid, the result is a
				// black color
				return Color.black;
			}
		} else if (source instanceof String) {
			return callOperation(operation, opcode, (String) source, args);
		} else if (source instanceof EOperation
				&& OCLProvider.TO_ECORE_DIAG_STRING.equals(operation.getName())) {
			return EMFHelper.toEcoreDiagString((EOperation) source);
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