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
package org.emftools.emf2gv.processor.core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.graphdesc.util.GraphdescGenerator;
import org.emftools.emf2gv.processor.Activator;
import org.emftools.emf2gv.util.IOHelper;

/**
 * EMF To Graphviz processor.
 */
public class EMF2GvProcessor {

	/**
	 * Converts a given model into a diagram file.
	 * 
	 * @param modelPath
	 *            the model path.
	 * @param modelUriFragment
	 *            the uri giving the model element to use as as root for the
	 *            diagram generation.
	 * @param graphDescPath
	 *            the graphical description path.
	 * @param targetImagePath
	 *            the target image path.
	 * @param eMF2GvProcessorCallback
	 *            the processor call back allowing to interrupt the process if
	 *            required.
	 * @param dotCommand
	 *            the graphviz dot utility command path.
	 * @param addValidationDecorators
	 *            a boolean indicating if validation decorators must be added.
	 * @param keepGeneratedGvFile
	 *            a boolean indicating if the generated Graphviz source file has
	 *            to be kept.
	 * @param gvSourceEnconding
	 *            the encoding to use for the generated graphviz source file.
	 * @param monitor
	 *            a progress monitor.
	 * 
	 * @return the generated image file.
	 * @throws CoreException
	 *             thrown if an unexpected error occurs.
	 */
	public static IFile process(IPath modelPath, String modelUriFragment,
			IPath graphDescPath, IPath targetImagePath,
			EMF2GvProcessorCallback eMF2GvProcessorCallback, String dotCommand,
			boolean addValidationDecorators, boolean keepGeneratedGvFile,
			String gvSourceEnconding, IProgressMonitor monitor)
			throws CoreException {

		/**
		 * Model file loading and validation
		 */
		ResourceSet rs = new ResourceSetImpl();
		rs.setPackageRegistry(EPackage.Registry.INSTANCE);
		Resource modelRes = loadEMFResource(rs, modelPath, false, monitor);

		/**
		 * Graphdesc file loading or generation
		 */
		/*
		 * If no Graphdesc file path is specified, the model is generated
		 */
		GVFigureDescription figureDesc = null;
		if (graphDescPath == null) {
			List<EPackage> ePackages = new ArrayList<EPackage>();
			TreeIterator<EObject> eObjects = modelRes.getAllContents();
			while (eObjects.hasNext()) {
				EObject eObject = eObjects.next();
				EPackage ePackage = eObject.eClass().getEPackage();
				if (!ePackages.contains(ePackage)) {
					ePackages.add(ePackage);
				}
			}
			// Graphdesc generation
			figureDesc = GraphdescGenerator
					.createGVFigureDescription(ePackages);
		}
		/*
		 * If the Graphdesc file path is specified, it is loaded
		 */
		else {
			Resource graphDescRes = loadEMFResource(new ResourceSetImpl(),
					graphDescPath, true, monitor);
			figureDesc = (GVFigureDescription) graphDescRes.getContents()
					.get(0);
		}

		/**
		 * Generation folder creation
		 */
		IPath outputFolderPath = targetImagePath.addFileExtension("emf2gv");
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IFolder outputFolder = root.getFolder(outputFolderPath);
		outputFolder.refreshLocal(IResource.DEPTH_INFINITE, monitor);
		if (!outputFolder.exists()) {
			outputFolder.create(true, true, null);
		}

		/**
		 * URI Fragment processing and GraphViz source build.
		 */
		GVSourceAndDependenciesBuilder gvSourceBuilder = new GVSourceAndDependenciesBuilder(
				figureDesc, outputFolder, addValidationDecorators);
		if (modelUriFragment == null || "".equals(modelUriFragment.trim())) {
			gvSourceBuilder.process(modelRes.getContents(), monitor);
		} else {
			EObject eObject = modelRes.getEObject(modelUriFragment.trim());
			if (eObject == null) {
				throw new CoreException(new Status(IStatus.ERROR,
						Activator.PLUGIN_ID, "Invalid URI fragment '"
								+ modelUriFragment + "' for resource '"
								+ modelRes.getURI().toString() + "'"));
			}
			gvSourceBuilder.process(eObject, monitor);
		}

		// Is there at least one node ?
		if (gvSourceBuilder.getNodesCount() == 0) {
			throw new CoreException(
					new Status(
							IStatus.ERROR,
							Activator.PLUGIN_ID,
							-1,
							"The generated graph is empty. Please check the transformation inputs.",
							null));
		}

		/*
		 * Callback confirmation before calling GraphViz.
		 */
		int nodesCount = gvSourceBuilder.getNodesCount();
		int edgesCount = gvSourceBuilder.getEdgesCount();
		if (!eMF2GvProcessorCallback.confirmImageGeneration(nodesCount,
				edgesCount)) {
			throw new CoreException(new Status(IStatus.ERROR,
					Activator.PLUGIN_ID,
					"EMF To GraphViz transformation interrupted"));
		}

		/*
		 * GV Source save
		 */
		IPath gvPath = outputFolderPath.append("graphviz.gv");
		byte[] content = null;
		try {
			content = gvSourceBuilder.getGvSource().getBytes(gvSourceEnconding);
		} catch (UnsupportedEncodingException e) {
			throw new CoreException(new Status(IStatus.ERROR,
					Activator.PLUGIN_ID, -1,
					"Unexpected error while saving Graphviz source file", e));
		}
		IFile targetGv = IOHelper.save(gvPath, content, monitor);

		/**
		 * Image production
		 */
		IFile imageFile = runGraphViz(dotCommand, targetGv.getRawLocation(),
				targetImagePath, monitor);

		// Gv source file deletion
		if (!keepGeneratedGvFile) {
			outputFolder.delete(true, false, monitor);
		}

		// Returns the generated image
		return imageFile;
	}

	/**
	 * Launches Graphivz and retrieves the generated image file.
	 * 
	 * @param dotCommand
	 *            the graphviz dot utility command path.
	 * @param gvRawLocation
	 *            the graphvis source file location.
	 * @param targetImagePath
	 *            the target image path.
	 * @param monitor
	 *            a progress monitor.
	 * 
	 * @return the generated image file.
	 * @throws CoreException
	 *             thrown if an unexpected error occurs.
	 */
	private static IFile runGraphViz(String dotCommand, IPath gvRawLocation,
			IPath targetImagePath, IProgressMonitor monitor)
			throws CoreException {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IFile target = root.getFile(targetImagePath);
		try {
			// TODO fix file type and other GraphViz options
			monitor.beginTask("Running GraphViz dot utility", 1);
			final Process gvProcess = Runtime.getRuntime().exec(
					new String[] { dotCommand, "-Tjpg",
							gvRawLocation.toOSString(),
							"-o" + target.getRawLocation().toOSString() });

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
				Activator.getDefault().logInfo(
						"Grafviz standard output :\n"
								+ new String(rawStdoutStream, "UTF-8"));
			}

			// Stderr retreival & log
			byte[] rawErrorStream = stderrHandler.getResult();
			String error = rawErrorStream.length > 0 ? new String(
					rawErrorStream, "UTF-8") : null;
			if (error != null) {
				Activator.getDefault().logError(
						"Grafviz error output :\n" + error, null);
			}

			// Process has normally exited ?
			int exitValue = gvProcess.exitValue();
			if (exitValue != 0) {
				throw new CoreException(new Status(IStatus.ERROR,
						Activator.PLUGIN_ID,
						"Graphviz 'dot' utility returned a non nul code : "
								+ exitValue
								+ ";\nSee error log fore more details."));
			}

			// Resource refresh
			target.refreshLocal(IResource.DEPTH_ONE, null);
			return target;
		} catch (IOException e) {
			throw new CoreException(new Status(IStatus.ERROR,
					Activator.PLUGIN_ID, -1,
					"Unexpected error while running Graphviz 'dot' utility", e));
		}
	}

	/**
	 * Loads a given EMF resource.
	 * 
	 * @param rs
	 *            the resource set.
	 * @param path
	 *            the resource path.
	 * @param validate
	 *            a boolean indicating if the resource must be validated.
	 * @param monitor
	 *            the progress monitor.
	 * @return the EMF resource.
	 * @throws CoreException
	 *             thrown if an unexpected eroor occurs.
	 */
	private static Resource loadEMFResource(ResourceSet rs, IPath path,
			boolean validate, IProgressMonitor monitor) throws CoreException {
		monitor.beginTask("Loading " + path, 2);
		URI uri = URI.createPlatformResourceURI(path.toPortableString(), true);
		Resource emfResource = rs.getResource(uri, true);
		monitor.worked(1);
		if (validate) {
			IStatus status = validate(emfResource);
			if (!status.isOK()) {
				throw new CoreException(status);
			}
		}
		monitor.worked(2);
		return emfResource;
	}

	/**
	 * Validates an EMF resource.
	 * 
	 * @param emfResource
	 *            the resource to validate.
	 * @return the validation status.
	 */
	private static IStatus validate(Resource emfResource) {
		Diagnostician diagnostician = new Diagnostician();
		TreeIterator<EObject> iterator = emfResource.getAllContents();
		BasicDiagnostic diagnostic = new BasicDiagnostic(
				EObjectValidator.DIAGNOSTIC_SOURCE, 0, emfResource.getURI()
						.toString() + " has validation errors", emfResource
						.getContents().toArray());
		while (iterator.hasNext()) {
			diagnostic.add(diagnostician.validate(iterator.next()));
		}
		return BasicDiagnostic.toIStatus(diagnostic);
	}

}
