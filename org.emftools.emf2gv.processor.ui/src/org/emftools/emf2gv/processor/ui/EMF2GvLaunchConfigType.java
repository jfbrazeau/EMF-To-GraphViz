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
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.emftools.emf2gv.graphdesc.Filter;
import org.emftools.emf2gv.graphdesc.GraphdescFactory;
import org.emftools.emf2gv.processor.core.EclipseProcessor;
import org.emftools.emf2gv.processor.core.IProcessorCallback;
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

		/* OCL expressions parsing */
		String[][] cfgExpressions = EMF2GvLaunchConfigHelper
				.getFilterExpressions(cfg);
		List<Filter> filters = new ArrayList<Filter>();
		for (int i = 0; i < cfgExpressions.length; i++) {
			String[] cfgExpression = cfgExpressions[i];
			boolean enabled = "true"
					.equals(cfgExpression[EMF2GvLaunchConfigHelper.FILTER_EXPRESSION_ENABLED_IDX]);
			String ePackakeNsUri = cfgExpression[EMF2GvLaunchConfigHelper.FILTER_EXPRESSION_EPACKAGE_IDX];
			String eClassName = cfgExpression[EMF2GvLaunchConfigHelper.FILTER_EXPRESSION_ECLASS_IDX];
			String expression = cfgExpression[EMF2GvLaunchConfigHelper.FILTER_EXPRESSION_VALUE_IDX];
			EPackage ePackage = EPackage.Registry.INSTANCE
					.getEPackage(ePackakeNsUri);
			EClass eClass = (EClass) ePackage.getEClassifier(eClassName);
			// Parsing...
			Filter filter = GraphdescFactory.eINSTANCE.createFilter();
			filter.setFilteredType(eClass);
			filter.setFilterExpression(expression);
			filter.setEnabled(enabled);
			filters.add(filter);
		}

		// IProcessorCallback
		IProcessorCallback processorCallback = new IProcessorCallback() {
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
		EclipseProcessor.process(modelPath, uriFragment, graphDescPath,
				targetPath, processorCallback, dotCommand,
				addValidationDecorators, keepGeneratedGvFile, gvSourceEncoding,
				filters, monitor);

		// Image editor is automatically opened
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		final IFile targetFile = root.getFile(targetPath);
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
