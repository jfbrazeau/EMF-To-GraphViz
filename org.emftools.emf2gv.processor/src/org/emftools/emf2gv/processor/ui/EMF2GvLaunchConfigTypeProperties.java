/**
 * Copyright (c) 2010, Jean-Francois Brazeau. All rights reserved.
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

public class EMF2GvLaunchConfigTypeProperties {

	private static final String GRAPHDESC_PATH = "graphDescPath";
	private static final String MODEL_PATH = "modelPath";
	private static final String TARGET_PATH = "targetPath";
	private static final String PROCESS_ALL_RESOURCE_CONTENTS = "processAllResourceContents";
	private static final String SELECTED_ELEMENT_URI_FRAGMENT = "selectedElementUriFragment";
	private static final String KEEP_GENERATED_GV_FILE = "keepGeneratedGvFile";
	private static final String AUTO_OPEN_IMAGE_EDITOR = "autoOpenImageEditor";
	private static final String GENERATE_GRAPHDESC = "generateGraphDesc";
	private static final String ADD_VALIDATION_DECORATORS = "addValidationDecorators";

	public static String getGraphDescPath(ILaunchConfiguration cfg)
			throws CoreException {
		return cfg.getAttribute(GRAPHDESC_PATH, "");
	}

	public static void setGraphDescPath(ILaunchConfigurationWorkingCopy cfg,
			String value) {
		cfg.setAttribute(GRAPHDESC_PATH, value);
	}

	public static String getModelPath(ILaunchConfiguration cfg)
			throws CoreException {
		return cfg.getAttribute(MODEL_PATH, "");
	}

	public static void setModelPath(ILaunchConfigurationWorkingCopy cfg,
			String value) {
		cfg.setAttribute(MODEL_PATH, value);
	}

	public static String getTargetPath(ILaunchConfiguration cfg)
			throws CoreException {
		return cfg.getAttribute(TARGET_PATH, "");
	}

	public static void setTargetPath(ILaunchConfigurationWorkingCopy cfg,
			String value) {
		cfg.setAttribute(TARGET_PATH, value);
	}

	public static boolean getProcessAllResourceContents(ILaunchConfiguration cfg)
			throws CoreException {
		return cfg.getAttribute(PROCESS_ALL_RESOURCE_CONTENTS, true);
	}

	public static void setProcessAllResourceContents(
			ILaunchConfigurationWorkingCopy cfg, boolean value) {
		cfg.setAttribute(PROCESS_ALL_RESOURCE_CONTENTS, value);
	}

	public static String getSelectedElementUriFragment(ILaunchConfiguration cfg)
			throws CoreException {
		return cfg.getAttribute(SELECTED_ELEMENT_URI_FRAGMENT, "");
	}

	public static void setSelectedElementUriFragment(
			ILaunchConfigurationWorkingCopy cfg, String value) {
		cfg.setAttribute(SELECTED_ELEMENT_URI_FRAGMENT, value);
	}

	public static boolean getKeepGeneratedGvFile(ILaunchConfiguration cfg)
			throws CoreException {
		return cfg.getAttribute(KEEP_GENERATED_GV_FILE, false);
	}

	public static void setKeepGeneratedGvFile(
			ILaunchConfigurationWorkingCopy cfg, boolean value) {
		cfg.setAttribute(KEEP_GENERATED_GV_FILE, value);
	}

	public static boolean getAutoOpenImageEditor(ILaunchConfiguration cfg)
			throws CoreException {
		return cfg.getAttribute(AUTO_OPEN_IMAGE_EDITOR, true);
	}

	public static void setAutoOpenImageEditor(
			ILaunchConfigurationWorkingCopy cfg, boolean value) {
		cfg.setAttribute(AUTO_OPEN_IMAGE_EDITOR, value);
	}

	public static boolean getGenerateGraphDesc(ILaunchConfiguration cfg)
			throws CoreException {
		return cfg.getAttribute(GENERATE_GRAPHDESC, true);
	}

	public static void setGenerateGraphDesc(
			ILaunchConfigurationWorkingCopy cfg, boolean value) {
		cfg.setAttribute(GENERATE_GRAPHDESC, value);
	}

	public static void setAddValidationDecorators(
			ILaunchConfigurationWorkingCopy cfg, boolean value) {
		cfg.setAttribute(ADD_VALIDATION_DECORATORS, value);
	}

	public static boolean getAddValidationDecorators(
			ILaunchConfiguration cfg) throws CoreException {
		return cfg.getAttribute(ADD_VALIDATION_DECORATORS, true);
	}

}
