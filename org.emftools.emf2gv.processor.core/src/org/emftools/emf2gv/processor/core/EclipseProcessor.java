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

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.emftools.emf2gv.graphdesc.Filter;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.util.EMFHelper;

/**
 * EMF To Graphviz processor intended to be used form an Eclipse OSGI
 * environment.
 * <p>
 * If you plan to use the processor from a Java standalone application, it it is
 * better to use the <code>StandaloneProcessor</code>.
 * </p>
 */
public class EclipseProcessor {

	/**
	 * Default logger implementation in an eclipse context.
	 */
	private static final ILogger DEFAULT_LOGGER = new ILogger() {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.emftools.emf2gv.processor.core.ILogger#logInfo(java.lang.String)
		 */
		public void logInfo(String info) {
			Activator.getDefault().logInfo(info);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.emftools.emf2gv.processor.core.ILogger#logError(java.lang.String,
		 * java.lang.Throwable)
		 */
		public void logError(String error, Throwable throwable) {
			Activator.getDefault().logError(error, throwable);
		}

	};

	/**
	 * Converts a given model into a diagram file.
	 * <p>
	 * If the graphical description is omitted, a default one is automatically
	 * generated.
	 * </p>
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
	 * @param processorCallback
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
	 * @param additionalFilters
	 *            additional filters (boolean OCL expressions allowing to filter
	 *            the nodes).
	 * @param monitor
	 *            a progress monitor.
	 * 
	 * @throws CoreException
	 *             thrown if an unexpected error occurs.
	 */
	public static void process(IPath modelPath, String modelUriFragment,
			IPath graphDescPath, IPath targetImagePath,
			IProcessorCallback processorCallback, String dotCommand,
			boolean addValidationDecorators, boolean keepGeneratedGvFile,
			String gvSourceEnconding,
			List<Filter> additionalFilters,
			IProgressMonitor monitor) throws CoreException {

		/*
		 * Graphdesc file loading.
		 */
		GVFigureDescription figureDesc = null;
		if (graphDescPath != null) {
			Resource graphDescRes = EMFHelper.loadFileEMFResource(
					new ResourceSetImpl(), graphDescPath, monitor);
			figureDesc = (GVFigureDescription) graphDescRes.getContents()
					.get(0);
		}

		/*
		 * Model file loading
		 */
		ResourceSet rs = new ResourceSetImpl();
		rs.setPackageRegistry(EPackage.Registry.INSTANCE);
		Resource modelRes = EMFHelper.loadFileEMFResource(rs, modelPath,
				monitor);
		List<EObject> modelRoots = null;
		if (modelUriFragment == null || "".equals(modelUriFragment.trim())) {
			modelRoots = modelRes.getContents();
		} else {
			EObject eObject = modelRes.getEObject(modelUriFragment.trim());
			if (eObject == null) {
				throw new CoreException(new Status(IStatus.ERROR,
						Activator.PLUGIN_ID, "Invalid URI fragment '"
								+ modelUriFragment + "' for resource '"
								+ modelRes.getURI().toString() + "'"));
			}
			modelRoots = Arrays.asList(new EObject[] { eObject });
		}

		/*
		 * Generation folder creation
		 */
		IPath outputFolderPath = targetImagePath.addFileExtension("emf2gv");
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IFolder outputFolder = root.getFolder(outputFolderPath);
		outputFolder.refreshLocal(IResource.DEPTH_INFINITE, monitor);
		if (!outputFolder.exists()) {
			outputFolder.create(true, true, null);
		}

		// Icons provider building
		List<EPackage> ePackages = null;
		if (figureDesc != null) {
			ePackages = figureDesc.getEPackages();
		} else {
			ePackages = new ArrayList<EPackage>();
			for (EObject eObject : modelRoots) {
				EPackage ePackage = eObject.eClass().getEPackage();
				if (!ePackages.contains(ePackage)) {
					ePackages.add(ePackage);
				}
			}
		}
		final AdapterFactory adapterFactory = EMFHelper
				.getAdapterFactory(ePackages);
		IEObjectIconProvider eObjectIconProvider = new IEObjectIconProvider() {
			public URL getIcon(EObject eObject) {
				URL result = null;
				IItemLabelProvider labelProvider = (IItemLabelProvider) adapterFactory
						.adapt(eObject, IItemLabelProvider.class);
				if (labelProvider != null) {
					Object image = labelProvider.getImage(eObject);
					// If we meet a composed image, we get the first image
					if (image instanceof ComposedImage) {
						List<Object> images = ((ComposedImage) image)
								.getImages();
						if (images != null && images.size() > 0) {
							image = images.get(0);
						}
					}
					if (image instanceof URL) {
						result = (URL) image;
					}
				}
				return result;
			}
		};

		// Diagram generation
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IFile diagramFile = workspaceRoot.getFile(targetImagePath);
		StandaloneProcessor.process(modelRoots, figureDesc, outputFolder
				.getRawLocation().toFile(), diagramFile.getRawLocation()
				.toOSString(), processorCallback, eObjectIconProvider,
				dotCommand, addValidationDecorators, keepGeneratedGvFile,
				gvSourceEnconding, additionalFilters, DEFAULT_LOGGER, monitor);

		// Working directory deletion
		if (!keepGeneratedGvFile) {
			outputFolder.delete(true, false, null);
		}

		// Resource refresh
		diagramFile.refreshLocal(IResource.DEPTH_ONE, null);
		outputFolder.refreshLocal(IResource.DEPTH_INFINITE, null);
	}

}
