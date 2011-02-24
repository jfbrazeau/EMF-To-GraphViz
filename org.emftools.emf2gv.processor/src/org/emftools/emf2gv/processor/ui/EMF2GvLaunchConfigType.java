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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.emftools.emf2gv.processor.Activator;
import org.emftools.emf2gv.processor.core.EMF2GvProcessor;
import org.emftools.emf2gv.processor.core.EMF2GvProcessorCallback;
import org.emftools.emf2gv.processor.ui.preferences.EMF2GvPreferenceConstants;

/**
 * EMF To Grahviz launch configuration type.
 */
public class EMF2GvLaunchConfigType implements ILaunchConfigurationDelegate {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.debug.core.model.ILaunchConfigurationDelegate#launch(org.
	 * eclipse.debug.core.ILaunchConfiguration, java.lang.String,
	 * org.eclipse.debug.core.ILaunch,
	 * org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void launch(ILaunchConfiguration cfg, String mode, ILaunch launch,
			IProgressMonitor monitor) throws CoreException {

		/*
		 * Parameters retrieval
		 */
		// Workspace prefs
		String gvSourceEncoding = Activator.getDefault().getPreferenceStore()
				.getString(EMF2GvPreferenceConstants.P_GV_SOURCE_ENCODING);
		String dotCommand = Activator.getDefault().getPreferenceStore()
				.getString(EMF2GvPreferenceConstants.P_DOT_UTILITY_PATH);
		// Run config
		boolean generateGraphDesc = EMF2GvLaunchConfigHelper
				.getGenerateGraphDesc(cfg);
		Path graphDescPath = generateGraphDesc ? null : new Path(
				EMF2GvLaunchConfigHelper.getGraphDescPath(cfg));
		Path modelPath = new Path(EMF2GvLaunchConfigHelper.getModelPath(cfg));
		Path targetPath = new Path(EMF2GvLaunchConfigHelper.getTargetPath(cfg));
		boolean processAllResourceContents = EMF2GvLaunchConfigHelper
				.getProcessAllResourceContents(cfg);
		String uriFragment = !processAllResourceContents ? EMF2GvLaunchConfigHelper
				.getSelectedElementUriFragment(cfg) : null;
		boolean addValidationDecorators = EMF2GvLaunchConfigHelper
				.getAddValidationDecorators(cfg);
		boolean keepGeneratedGvFile = EMF2GvLaunchConfigHelper
				.getKeepGeneratedGvFile(cfg);

		// EMF2GvProcessorCallback
		EMF2GvProcessorCallback eMF2GvProcessorCallback = new EMF2GvProcessorCallback() {
			public boolean confirmImageGeneration(final int nodesCount,
					final int edgesCount) {
				final boolean[] result = new boolean[1];
				if (nodesCount + edgesCount > 600) {
					PlatformUI.getWorkbench().getDisplay()
							.syncExec(new Runnable() {
								public void run() {
									result[0] = MessageDialog
											.openQuestion(
													PlatformUI.getWorkbench()
															.getDisplay()
															.getActiveShell(),
													"Warning",
													"There is a high number of nodes and/or edges to represent :\n"
															+ "Nodes : "
															+ nodesCount
															+ "\n"
															+ "Edges : "
															+ edgesCount
															+ "\n"
															+ "\n"
															+ "This may take a long time.\n"
															+ "Do you want to proceed ?\n"
															+ "\n"
															+ "In order to decrease the nodes/edges count, it is possible to\n"
															+ " - remove some reference figures from the Graphdesc model\n"
															+ " - select a root element to process in the run configuration");
								}
							});
				} else {
					result[0] = true;
				}
				return result[0];
			}
		};

		// Image generation
		final IFile targetFile = EMF2GvProcessor.process(modelPath,
				uriFragment, graphDescPath, targetPath,
				eMF2GvProcessorCallback, dotCommand, addValidationDecorators,
				keepGeneratedGvFile, gvSourceEncoding, monitor);

		// Image editor is automatically opened
		if (EMF2GvLaunchConfigHelper.getAutoOpenImageEditor(cfg)) {
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
				public void run() {
					try {
						IWorkbenchPage page = PlatformUI.getWorkbench()
								.getActiveWorkbenchWindow().getActivePage();
						IDE.openEditor(page, targetFile);
					} catch (Throwable t) {
						Activator
								.getDefault()
								.logError(
										"Unexpected error while opening the image editor",
										t);
					}
				}
			});
		}
	}

}
