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
package org.emftools.emf2gv.processor.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.emftools.emf2gv.graphdesc.Filter;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.graphdesc.util.GraphdescHelper;
import org.emftools.emf2gv.util.EMFHelper;
import org.emftools.emf2gv.util.IOHelper;

/**
 * EMF To Graphviz processor intended to be used form any Java environment.
 * 
 * <p>
 * In a fully Eclipse OSGI environment, it is better to use the
 * <code>EclipseProcessor</code>.
 * </p>
 */
public class StandaloneProcessor {

	/**
	 * The plug-in ID (decalred here to remove the dependency from this class to
	 * the Activator which indirectly brings a dependency to the osgi jar)
	 */
	protected static final String PLUGIN_ID = "org.emftools.emf2gv.processor.core"; //$NON-NLS-1$

	/** Default EObject icon provider (classpath implementation) */
	private static final IEObjectIconProvider DEFAULT_EOBJECT_ICON_PROVIDER = new ClasspathEObjectIconProvider();

	/** Default logger */
	private static final ILogger DEFAULT_LOGGER = new StandardLogger();

	/**
	 * Converts a given model into a diagram file.
	 * 
	 * 
	 * <p>
	 * The entry point in the model is given by the <code>modelRoot</code>
	 * argument.
	 * </p>
	 * <p>
	 * If the graphical description is omitted, a default one is automatically
	 * generated.
	 * </p>
	 * 
	 * @param modelRoot
	 *            the root of the model to represent.
	 * @param gvFigureDescription
	 *            the graphical description containing the directive to apply to
	 *            build the diagram.
	 * @param workDir
	 *            working directory (in which is generated the graphviz source
	 *            file).
	 * @param targetImagePath
	 *            the target diagram file path.
	 * @param processorCallback
	 *            a callback allowing to stop the generation if the nodes count
	 *            is too important.
	 * @param eObjectIconProvider
	 *            the EObject icon provider.
	 * @param dotCommand
	 *            the graphviz dot utility command path.
	 * @param addValidationDecorators
	 *            a boolean indicating if validation decorators must be added.
	 * @param keepGeneratedGvFile
	 *            a boolean indicating if the generated Graphviz source file has
	 *            to be kept.
	 * @param gvSourceEnconding
	 *            the encoding to use for the generated graphviz source file.
	 * @param logger
	 *            the logger.
	 * @param additionalFilters
	 *            additional filters (boolean OCL expressions allowing to filter
	 *            the EObjects).
	 * @param logger
	 *            the logger.
	 * @param monitor
	 *            a progress monitor.
	 * @throws CoreException
	 *             thrown if an unexpected error occurs.
	 */
	public static void process(EObject modelRoot,
			GVFigureDescription gvFigureDescription, File workDir,
			String targetImagePath, IProcessorCallback processorCallback,
			IEObjectIconProvider eObjectIconProvider, String dotCommand,
			boolean addValidationDecorators, boolean keepGeneratedGvFile,
			String gvSourceEnconding, List<Filter> additionalFilters,
			ILogger logger, IProgressMonitor monitor) throws CoreException {
		process(Arrays.asList(new EObject[] { modelRoot }),
				gvFigureDescription, workDir, targetImagePath,
				processorCallback, eObjectIconProvider, dotCommand,
				addValidationDecorators, keepGeneratedGvFile,
				gvSourceEnconding, additionalFilters, logger, monitor);
	}

	/**
	 * Converts a given model into a diagram file.
	 * 
	 * <p>
	 * the entry points in the model is given by the <code>modelRoots</code>
	 * argument.
	 * </p>
	 * <p>
	 * If the graphical description is omitted, a default one is automatically
	 * generated.
	 * </p>
	 * 
	 * @param modelRoots
	 *            the roots of the model(s) to represent.
	 * @param gvFigureDescription
	 *            the graphical description containing the directive to apply to
	 *            build the diagram.
	 * @param workDir
	 *            working directory (in which is generated the graphviz source
	 *            file).
	 * @param targetImagePath
	 *            the target diagram file path.
	 * @param processorCallback
	 *            a callback allowing to stop the generation if the nodes count
	 *            is too important.
	 * @param eObjectIconProvider
	 *            the EObject icon provider.
	 * @param dotCommand
	 *            the graphviz dot utility command path.
	 * @param addValidationDecorators
	 *            a boolean indicating if validation decorators must be added.
	 * @param keepGeneratedGvFile
	 *            a boolean indicating if the generated Graphviz source file has
	 *            to be kept.
	 * @param gvSourceEnconding
	 *            the encoding to use for the generated graphviz source file.
	 * @param additionalFilters
	 *            additional filters (boolean OCL expressions allowing to filter
	 *            the EObjects).
	 * @param logger
	 *            the logger.
	 * @param monitor
	 *            a progress monitor.
	 * @throws CoreException
	 *             thrown if an unexpected error occurs.
	 */
	public static void process(List<EObject> modelRoots,
			GVFigureDescription gvFigureDescription, File workDir,
			String targetImagePath, IProcessorCallback processorCallback,
			IEObjectIconProvider eObjectIconProvider, String dotCommand,
			boolean addValidationDecorators, boolean keepGeneratedGvFile,
			String gvSourceEnconding, List<Filter> additionalFilters,
			ILogger logger, IProgressMonitor monitor) throws CoreException {

		/*
		 * Optional parameters check.
		 */
		if (gvSourceEnconding == null) {
			gvSourceEnconding = "UTF-8";
		}
		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}
		if (eObjectIconProvider == null) {
			eObjectIconProvider = DEFAULT_EOBJECT_ICON_PROVIDER;
		}
		if (dotCommand == null) {
			dotCommand = "dot";
		}
		if (logger == null) {
			logger = DEFAULT_LOGGER;
		}
		/*
		 * Working directory check.
		 */
		boolean workDirHasBeenCreated = false;
		if (workDir.exists()) {
			if (!workDir.isDirectory()) {
				throw new CoreException(new Status(IStatus.ERROR, PLUGIN_ID,
						-1,
						"The working directory is not valid (not a directory : '"
								+ workDir.getAbsolutePath() + "')", null));
			}
		} else {
			if (!workDir.mkdirs()) {
				throw new CoreException(new Status(IStatus.ERROR, PLUGIN_ID,
						-1, "The working directory could not be created ("
								+ workDir.getAbsolutePath() + ")", null));
			}
			workDirHasBeenCreated = true;
		}

		/*
		 * If no Graphdesc file path is specified, the model is generated.
		 * Otherwise, the file content is validated.
		 */
		if (gvFigureDescription == null) {
			List<EPackage> ePackages = new ArrayList<EPackage>();
			for (EObject eObject : modelRoots) {
				EPackage ePackage = eObject.eClass().getEPackage();
				if (!ePackages.contains(ePackage)) {
					ePackages.add(ePackage);
				}
			}
			// Graphdesc generation
			gvFigureDescription = GraphdescHelper
					.createGVFigureDescription(ePackages);
		} else {
			// Graphical description validation
			IStatus status = EMFHelper
					.validate(gvFigureDescription.eResource());
			if (!status.isOK()) {
				throw new CoreException(status);
			}
		}

		/*
		 * Additional filter registering
		 */
		if (additionalFilters != null) {
			gvFigureDescription.getFilters().addAll(additionalFilters);
		}

		/*
		 * URI Fragment processing and GraphViz source build.
		 */
		GVSourceAndDependenciesBuilder gvSourceBuilder = new GVSourceAndDependenciesBuilder(
				gvFigureDescription, eObjectIconProvider, workDir,
				addValidationDecorators, logger);
		gvSourceBuilder.process(modelRoots, monitor);

		// Is there at least one node ?
		if (gvSourceBuilder.getNodesCount() == 0) {
			throw new CoreException(
					new Status(
							IStatus.ERROR,
							PLUGIN_ID,
							-1,
							"The generated graph is empty. Please check the transformation inputs.",
							null));
		}

		/*
		 * Callback confirmation before calling GraphViz.
		 */
		int nodesCount = gvSourceBuilder.getNodesCount();
		int edgesCount = gvSourceBuilder.getEdgesCount();
		if (processorCallback != null
				&& !processorCallback.confirmImageGeneration(nodesCount,
						edgesCount)) {
			throw new CoreException(new Status(IStatus.ERROR, PLUGIN_ID,
					"EMF To GraphViz transformation interrupted"));
		}

		/*
		 * GV Source save
		 */
		String gvSourcePath = new StringBuilder(workDir.getAbsolutePath())
				.append("/graphviz.gv").toString();
		try {
			byte[] content = gvSourceBuilder.getGvSource().getBytes(
					gvSourceEnconding);
			IOHelper.save(gvSourcePath, content, monitor);
		} catch (IOException e) {
			throw new CoreException(new Status(IStatus.ERROR, PLUGIN_ID, -1,
					"Unexpected error while saving Graphviz source file", e));
		}

		/**
		 * Image production
		 */
		runGraphViz(dotCommand, gvSourcePath, targetImagePath, logger, monitor);

		// Gv source file & icons deletion
		if (!keepGeneratedGvFile) {
			Collection<String> iconPaths = gvSourceBuilder
					.getGeneratedIconsPaths();
			for (String iconPath : iconPaths) {
				new File(iconPath).delete();
			}
			new File(gvSourcePath).delete();
			if (workDirHasBeenCreated) {
				workDir.delete();
			}
		}
	}

	/**
	 * Launches Graphivz and retrieves the generated image file.
	 * 
	 * @param dotCommand
	 *            the graphviz dot utility command path.
	 * @param gvSourcePath
	 *            the graphvis source file location.
	 * @param targetImagePath
	 *            the target image path.
	 * @param logger
	 *            the logger.
	 * @param monitor
	 *            a progress monitor.
	 * 
	 * @throws CoreException
	 *             thrown if an unexpected error occurs.
	 */
	private static void runGraphViz(String dotCommand, String gvSourcePath,
			String targetImagePath, ILogger logger, IProgressMonitor monitor)
			throws CoreException {
		try {
			monitor.beginTask("Running GraphViz dot utility", 1);
			final Process gvProcess = Runtime.getRuntime().exec(
					new String[] { dotCommand, "-Tjpg", gvSourcePath,
							"-o" + targetImagePath });

			// Stdout and stderr capture
			StreamHandler stderrHandler = new StreamHandler(
					gvProcess.getErrorStream());
			stderrHandler.start();
			StreamHandler stdoutHandler = new StreamHandler(
					gvProcess.getInputStream());
			stdoutHandler.start();

			// Thread wainting for the end of GraphViz execution
			Thread processWaitForThread = new Thread() {
				public void run() {
					try {
						gvProcess.waitFor();
					} catch (InterruptedException ignored) {
					}
				}
			};
			processWaitForThread.start();

			// Loop and process destruction if the progress monitor
			// is canceled
			while (processWaitForThread.isAlive()) {
				monitor.worked(1);
				if (monitor.isCanceled()) {
					System.err.println("STOPPING GRAPHVIZ");
					gvProcess.destroy();
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException ignored) {
				}
			}

			// Stdout retrieval & log
			byte[] rawStdoutStream = stdoutHandler.getResult();
			if (rawStdoutStream != null && rawStdoutStream.length > 0) {
				logger.logInfo("Grafviz standard output :\n"
						+ new String(rawStdoutStream, "UTF-8"));
			}

			// Stderr retreival & log
			byte[] rawErrorStream = stderrHandler.getResult();
			String error = rawErrorStream.length > 0 ? new String(
					rawErrorStream, "UTF-8") : null;
			if (error != null) {
				logger.logError("Grafviz error output :\n" + error, null);
			}

			// Process has normally exited ?
			int exitValue = gvProcess.exitValue();
			if (exitValue != 0) {
				throw new CoreException(new Status(IStatus.ERROR, PLUGIN_ID,
						"Graphviz 'dot' utility returned a non nul code : "
								+ exitValue
								+ ";\nSee error log fore more details."));
			}

		} catch (IOException e) {
			throw new CoreException(new Status(IStatus.ERROR, PLUGIN_ID, -1,
					"Unexpected error while running Graphviz 'dot' utility", e));
		}
	}

}
