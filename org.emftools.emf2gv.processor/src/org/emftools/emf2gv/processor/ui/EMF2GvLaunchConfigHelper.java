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
package org.emftools.emf2gv.processor.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;

/**
 * Helper class offering services to manage the launch configuration type
 * parameters.
 */
public class EMF2GvLaunchConfigHelper {

	/** The graphical description path */
	private static final String GRAPHDESC_PATH = "graphDescPath";

	/** The model path */
	private static final String MODEL_PATH = "modelPath";

	/** The target image path */
	private static final String TARGET_PATH = "targetPath";

	/** A boolean indicating if all the emf resource contents must be processed */
	private static final String PROCESS_ALL_RESOURCE_CONTENTS = "processAllResourceContents";

	/** The root element to process */
	private static final String SELECTED_ELEMENT_URI_FRAGMENT = "selectedElementUriFragment";

	/** A boolean indicating if the generated graphviz source must be kept */
	private static final String KEEP_GENERATED_GV_FILE = "keepGeneratedGvFile";

	/**
	 * A boolean indicating if the diagram must be automatically opened at the
	 * end of the generation.
	 */
	private static final String AUTO_OPEN_IMAGE_EDITOR = "autoOpenImageEditor";

	/** A boolean indicating if the graphical description must be generated. */
	private static final String GENERATE_GRAPHDESC = "generateGraphDesc";

	/**
	 * A boolean indicating if the validation decorators must be added to the
	 * diagram.
	 */
	private static final String ADD_VALIDATION_DECORATORS = "addValidationDecorators";

	/** EPackage associated to an expression that is used to filter the nodes */
	private static final String FILTER_EXPRESSION_EPACKAGE = "filterExpressionEPackage";

	/**
	 * Index of the EPackage attribute associated to an expression that is used
	 * to filter the nodes
	 */
	public static final int FILTER_EXPRESSION_EPACKAGE_IDX = 0;

	/** EClass associated to an expression that is used to filter the nodes */
	private static final String FILTER_EXPRESSION_ECLASS = "filterExpressionEClass";

	/**
	 * Index of the EClass attribute associated to an expression that is used to
	 * filter the nodes
	 */
	public static final int FILTER_EXPRESSION_ECLASS_IDX = 1;

	/** Value of an expression that is used to filter the nodes */
	private static final String FILTER_EXPRESSION_VALUE = "filterExpressionValue";

	/** Index of the value attribute of an expression that is used to filter the nodes */
	public static final int FILTER_EXPRESSION_VALUE_IDX = 2;

	/** Number of expressions that are used to filter the nodes */
	private static final String FILTER_EXPRESSIONS_COUNT = "filterExpressionsCount";

	/**
	 * @param cfg
	 *            the launch configuration.
	 * @return the graphical description path.
	 * @throws CoreException
	 *             thrown if an unexpected error occurs.
	 */
	public static String getGraphDescPath(ILaunchConfiguration cfg)
			throws CoreException {
		return cfg.getAttribute(GRAPHDESC_PATH, "");
	}

	/**
	 * Sets the graphical description path.
	 * 
	 * @param cfg
	 *            the launch configuration.
	 * @param value
	 *            the new value.
	 */
	public static void setGraphDescPath(ILaunchConfigurationWorkingCopy cfg,
			String value) {
		cfg.setAttribute(GRAPHDESC_PATH, value);
	}

	/**
	 * @param cfg
	 *            the launch configuration.
	 * @return the model path.
	 * @throws CoreException
	 *             thrown if an unexpected error occurs.
	 */
	public static String getModelPath(ILaunchConfiguration cfg)
			throws CoreException {
		return cfg.getAttribute(MODEL_PATH, "");
	}

	/**
	 * Sets the model path.
	 * 
	 * @param cfg
	 *            the launch configuration.
	 * @param value
	 *            the new value.
	 */
	public static void setModelPath(ILaunchConfigurationWorkingCopy cfg,
			String value) {
		cfg.setAttribute(MODEL_PATH, value);
	}

	/**
	 * @param cfg
	 *            the launch configuration.
	 * @return the target image path.
	 * @throws CoreException
	 *             thrown if an unexpected error occurs.
	 */
	public static String getTargetPath(ILaunchConfiguration cfg)
			throws CoreException {
		return cfg.getAttribute(TARGET_PATH, "");
	}

	/**
	 * Sets the target image path.
	 * 
	 * @param cfg
	 *            the launch configuration.
	 * @param value
	 *            the new value.
	 */
	public static void setTargetPath(ILaunchConfigurationWorkingCopy cfg,
			String value) {
		cfg.setAttribute(TARGET_PATH, value);
	}

	/**
	 * @param cfg
	 *            the launch configuration.
	 * @return a boolean indicating if all the emf resource contents must be
	 *         processed.
	 * @throws CoreException
	 *             thrown if an unexpected error occurs.
	 */
	public static boolean getProcessAllResourceContents(ILaunchConfiguration cfg)
			throws CoreException {
		return cfg.getAttribute(PROCESS_ALL_RESOURCE_CONTENTS, true);
	}

	/**
	 * Sets a boolean indicating if all the emf resource contents must be
	 * processed.
	 * 
	 * @param cfg
	 *            the launch configuration.
	 * @param value
	 *            the new value.
	 */
	public static void setProcessAllResourceContents(
			ILaunchConfigurationWorkingCopy cfg, boolean value) {
		cfg.setAttribute(PROCESS_ALL_RESOURCE_CONTENTS, value);
	}

	/**
	 * @param cfg
	 *            the launch configuration.
	 * @return the root element to process.
	 * @throws CoreException
	 *             thrown if an unexpected error occurs.
	 */
	public static String getSelectedElementUriFragment(ILaunchConfiguration cfg)
			throws CoreException {
		return cfg.getAttribute(SELECTED_ELEMENT_URI_FRAGMENT, "");
	}

	/**
	 * Sets the root element to process.
	 * 
	 * @param cfg
	 *            the launch configuration.
	 * @param value
	 *            the new value.
	 */
	public static void setSelectedElementUriFragment(
			ILaunchConfigurationWorkingCopy cfg, String value) {
		cfg.setAttribute(SELECTED_ELEMENT_URI_FRAGMENT, value);
	}

	/**
	 * @param cfg
	 *            the launch configuration.
	 * @return a boolean indicating if the generated graphviz source must be
	 *         kept.
	 * @throws CoreException
	 *             thrown if an unexpected error occurs.
	 */
	public static boolean getKeepGeneratedGvFile(ILaunchConfiguration cfg)
			throws CoreException {
		return cfg.getAttribute(KEEP_GENERATED_GV_FILE, false);
	}

	/**
	 * Sets a boolean indicating if the generated graphviz source must be kept.
	 * 
	 * @param cfg
	 *            the launch configuration.
	 * @param value
	 *            the new value.
	 */
	public static void setKeepGeneratedGvFile(
			ILaunchConfigurationWorkingCopy cfg, boolean value) {
		cfg.setAttribute(KEEP_GENERATED_GV_FILE, value);
	}

	/**
	 * @param cfg
	 *            the launch configuration.
	 * @return a boolean indicating if the diagram must be automatically opened
	 *         at the end of the generation.
	 * @throws CoreException
	 *             thrown if an unexpected error occurs.
	 */
	public static boolean getAutoOpenImageEditor(ILaunchConfiguration cfg)
			throws CoreException {
		return cfg.getAttribute(AUTO_OPEN_IMAGE_EDITOR, true);
	}

	/**
	 * Sets a boolean indicating if the diagram must be automatically opened at
	 * the end of the generation.
	 * 
	 * @param cfg
	 *            the launch configuration.
	 * @param value
	 *            the new value.
	 */
	public static void setAutoOpenImageEditor(
			ILaunchConfigurationWorkingCopy cfg, boolean value) {
		cfg.setAttribute(AUTO_OPEN_IMAGE_EDITOR, value);
	}

	/**
	 * @param cfg
	 *            the launch configuration.
	 * @return a boolean indicating if the graphical description must be
	 *         generated.
	 * @throws CoreException
	 *             thrown if an unexpected error occurs.
	 */
	public static boolean getGenerateGraphDesc(ILaunchConfiguration cfg)
			throws CoreException {
		return cfg.getAttribute(GENERATE_GRAPHDESC, true);
	}

	/**
	 * Sets a boolean indicating if the graphical description must be generated.
	 * 
	 * @param cfg
	 *            the launch configuration.
	 * @param value
	 *            the new value.
	 */
	public static void setGenerateGraphDesc(
			ILaunchConfigurationWorkingCopy cfg, boolean value) {
		cfg.setAttribute(GENERATE_GRAPHDESC, value);
	}

	/**
	 * @param cfg
	 *            the launch configuration.
	 * @return a boolean indicating if the validation decorators must be added
	 *         to the diagram.
	 * @throws CoreException
	 *             thrown if an unexpected error occurs.
	 */
	public static boolean getAddValidationDecorators(ILaunchConfiguration cfg)
			throws CoreException {
		return cfg.getAttribute(ADD_VALIDATION_DECORATORS, true);
	}

	/**
	 * Sets a boolean indicating if the validation decorators must be added to
	 * the diagram.
	 * 
	 * @param cfg
	 *            the launch configuration.
	 * @param value
	 *            the new value.
	 */
	public static void setAddValidationDecorators(
			ILaunchConfigurationWorkingCopy cfg, boolean value) {
		cfg.setAttribute(ADD_VALIDATION_DECORATORS, value);
	}

	/**
	 * @param cfg
	 *            the launch configuration.
	 * @return the expressions used to filter the nodes.
	 * @throws CoreException
	 *             thrown if an unexpected error occurs.
	 */
	public static String[][] getFilterExpressions(ILaunchConfiguration cfg)
			throws CoreException {
		int expressionCount = cfg.getAttribute(FILTER_EXPRESSIONS_COUNT, 0);
		String[][] expressions = new String[expressionCount][3];
		for (int i = 0; i < expressionCount; i++) {
			String[] expression = expressions[i];
			expression[FILTER_EXPRESSION_EPACKAGE_IDX] = cfg.getAttribute(
					FILTER_EXPRESSION_EPACKAGE + "." + i, "");
			expression[FILTER_EXPRESSION_ECLASS_IDX] = cfg.getAttribute(
					FILTER_EXPRESSION_ECLASS + "." + i, "");
			expression[FILTER_EXPRESSION_VALUE_IDX] = cfg.getAttribute(
					FILTER_EXPRESSION_VALUE + "." + i, "");
		}
		return expressions;
	}

	/**
	 * Save the expressions used to filter nodes in the configuration.
	 * 
	 * @param cfg
	 *            the launch configuration.
	 * @param expressions
	 *            the expressions.
	 */
	public static void setFilterExpressions(
			ILaunchConfigurationWorkingCopy cfg, String[][] expressions) {
		int expressionCount = expressions.length;
		cfg.setAttribute(FILTER_EXPRESSIONS_COUNT, expressionCount);
		for (int i = 0; i < expressionCount; i++) {
			String[] expression = expressions[i];
			cfg.setAttribute(FILTER_EXPRESSION_EPACKAGE + "." + i,
					expression[FILTER_EXPRESSION_EPACKAGE_IDX]);
			cfg.setAttribute(FILTER_EXPRESSION_ECLASS + "." + i,
					expression[FILTER_EXPRESSION_ECLASS_IDX]);
			cfg.setAttribute(FILTER_EXPRESSION_VALUE + "." + i,
					expression[FILTER_EXPRESSION_VALUE_IDX]);
		}
	}

}
