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
package org.emftools.emf2gv.processor.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.IDebugModelPresentation;
import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

/**
 * Emf2gv launch shortcut.
 */
public class EMF2GvProcessorShortcut implements ILaunchShortcut {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.debug.ui.ILaunchShortcut#launch(org.eclipse.jface.viewers
	 * .ISelection, java.lang.String)
	 */
	public void launch(ISelection selection, String mode) {
		try {
			// Selected file retrieval
			IFile file = (IFile) ((TreeSelection) selection).getFirstElement();
			String filePath = file.getFullPath().toString();
			boolean isGraphDesc = file.getFileExtension().equals("graphdesc");

			// Launch cfg type retrieval
			ILaunchManager launchManager = DebugPlugin.getDefault()
					.getLaunchManager();
			ILaunchConfigurationType launchConfigurationType = launchManager
					.getLaunchConfigurationType(EMF2GvLaunchConfigType.class
							.getName());

			// Is there any launch configurations for this resource
			ILaunchConfiguration[] launchCfgs = launchManager
					.getLaunchConfigurations(launchConfigurationType);
			List<ILaunchConfiguration> fileLaunchCfgs = new ArrayList<ILaunchConfiguration>();
			for (int i = 0; i < launchCfgs.length; i++) {
				ILaunchConfiguration launchCfg = launchCfgs[i];
				String launchCfgFilePath = isGraphDesc ? EMF2GvLaunchConfigHelper
						.getGraphDescPath(launchCfg) : EMF2GvLaunchConfigHelper
						.getModelPath(launchCfg);
				if (filePath.equals(launchCfgFilePath)) {
					fileLaunchCfgs.add(launchCfg);
				}

			}

			// If there are more than one corresponding config
			// the user must choose !
			ILaunchConfiguration selectedLaunchConfig = null;
			if (fileLaunchCfgs.size() == 1) {
				selectedLaunchConfig = fileLaunchCfgs.get(0);
			} else if (fileLaunchCfgs.size() > 1) {
				selectedLaunchConfig = selectLaunchConfig(fileLaunchCfgs);
			}

			// If we havn't found the launch config, or if the user
			// has refused to select a configuration, we create a new one
			if (selectedLaunchConfig == null) {
				IFile modelFile = null;
				IFile graphDescFile = null;
				// If the selected file is a graphdesc file, the user must
				// choose
				// an EMF file to process
				if (isGraphDesc) {
					graphDescFile = file;
					IFile[] result = WorkspaceResourceDialog.openFileSelection(
							PlatformUI.getWorkbench().getDisplay()
									.getActiveShell(), "File selection",
							"Please select a model file to process", false,
							null, null);
					if (result != null && result.length > 0) {
						modelFile = result[0];
					}
				}
				// If the selected file is an EMF file, the graphdesc file will
				// be generated
				else {
					modelFile = file;
				}

				// If we have a model file we launch the transformation
				if (modelFile != null) {
					String configNamePrefix = modelFile.getName()
							+ (graphDescFile != null ? '-' + graphDescFile
									.getName() : "");
					String configName = launchManager
							.generateLaunchConfigurationName(configNamePrefix);
					// Then we save the launch configuration
					ILaunchConfigurationWorkingCopy wc = launchConfigurationType
							.newInstance(null, configName);
					EMF2GvLaunchConfigHelper.setGenerateGraphDesc(wc,
							graphDescFile == null);
					EMF2GvLaunchConfigHelper.setGraphDescPath(wc,
							graphDescFile != null ? graphDescFile.getFullPath()
									.toString() : "");
					EMF2GvLaunchConfigHelper.setModelPath(wc, modelFile
							.getFullPath().toString());
					EMF2GvLaunchConfigHelper.setTargetPath(wc, modelFile
							.getParent().getFullPath().toString()
							+ "/" + configName + ".jpg");
					wc.doSave();
					selectedLaunchConfig = wc;
				}
			}
			// If we have a launch configuration, we launch it !
			if (selectedLaunchConfig != null) {
				final ILaunchConfiguration theLC = selectedLaunchConfig;
				Job job = new Job("EMF To Graphviz transformation") {
					protected IStatus run(IProgressMonitor monitor) {
						try {
							theLC.launch("run", monitor);
							return Status.OK_STATUS;
						} catch (CoreException e) {
							return e.getStatus();
						}
					}
				};
				job.setPriority(Job.SHORT);
				job.schedule();

			}
		} catch (CoreException e) {
			Activator.getDefault().logError("Unexpected error", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.debug.ui.ILaunchShortcut#launch(org.eclipse.ui.IEditorPart,
	 * java.lang.String)
	 */
	public void launch(IEditorPart editor, String mode) {
		// Not used
	}

	private ILaunchConfiguration selectLaunchConfig(
			List<ILaunchConfiguration> launchCfgs) {
		IDebugModelPresentation labelProvider = DebugUITools
				.newDebugModelPresentation();
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(
				PlatformUI.getWorkbench().getDisplay().getActiveShell(),
				labelProvider);
		dialog.setElements(launchCfgs.toArray());
		dialog.setTitle("Launch configuration list");
		dialog.setMessage("Select a configuration to launch");
		dialog.setMultipleSelection(false);
		int result = dialog.open();
		labelProvider.dispose();
		if (result == Window.OK) {
			return (ILaunchConfiguration) dialog.getFirstResult();
		}
		return null;
	}

}
