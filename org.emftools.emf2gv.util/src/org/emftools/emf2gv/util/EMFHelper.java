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

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory.Descriptor;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory.Descriptor.Registry;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;

/**
 * This class provides several helper methods around EMF.
 */
public class EMFHelper {

	/**
	 * @return an Ecore adapter factory.
	 */
	public static AdapterFactory getEcoreAdapterFactory() {
		return getAdapterFactory(Arrays
				.asList(new EPackage[] { EcorePackage.eINSTANCE }));
	}

	/**
	 * Returns an adapter factory for the given EPackages.
	 * 
	 * @param ePackages
	 *            the EPackages.
	 * @return the adapter factory.
	 */
	public static AdapterFactory getAdapterFactory(List<EPackage> ePackages) {
		Registry reg = EMFEditPlugin
				.getComposedAdapterFactoryDescriptorRegistry();
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory
				.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory
				.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		for (EPackage ePackage : ePackages) {
			List<Object> list = Arrays
					.asList(new Object[] {
							ePackage,
							org.eclipse.emf.edit.provider.ITreeItemContentProvider.class });
			Descriptor desc = reg.getDescriptor(list);
			if (desc != null) {
				AdapterFactory af = desc.createAdapterFactory();
				adapterFactory.addAdapterFactory(af);
			}
		}
		return adapterFactory;
	}

	/**
	 * Resolves an EPackage.
	 * 
	 * @param ePackageFake
	 *            the fake EPackage (that is used to lazily resolve the
	 *            EPackages).
	 * @return the resolved EPackage.
	 */
	public static EPackage resolve(EPackageFake ePackageFake) {
		// EPackage resolution
		String uri = ePackageFake.getNsURI();
		return EPackage.Registry.INSTANCE.getEPackage(uri);
	}

	/**
	 * @return the registered EPackages list.
	 */
	public static List<EPackage> getRegisteredEPackages() {
		// Workspace EPackages retrieval
		List<EPackage> ePackages = new ArrayList<EPackage>();

		// URI retrieval (+ sort)
		List<String> uris = new ArrayList<String>();
		uris.addAll(EPackage.Registry.INSTANCE.keySet());
		Collections.sort(uris);

		// EPackage list building
		for (String uri : uris) {
			Object obj = EPackage.Registry.INSTANCE.get(uri);
			// If we have a descriptor we must put a fake EPackage in the
			// list to avoid resolving it while making the property editor
			// believe that he has a real EPackage (in order no to load
			// unloaded emf models)
			if (obj instanceof EPackage.Descriptor) {
				ePackages.add(new EPackageFake(uri));
			} else if (obj instanceof EPackage) {
				ePackages.add((EPackage) obj);
			}
		}
		return ePackages;
	}

	/**
	 * Loads a given EMF resource.
	 * 
	 * @param rs
	 *            the resource set.
	 * @param path
	 *            the resource path.
	 * @param monitor
	 *            the progress monitor.
	 * @return the EMF resource.
	 * @throws CoreException
	 *             thrown if an unexpected error occurs.
	 */
	public static Resource loadFileEMFResource(ResourceSet rs, IPath path,
			IProgressMonitor monitor) throws CoreException {
		if (monitor != null) {
			monitor.beginTask("Loading " + path, 1);
		}
		URI uri = URI.createFileURI(ResourcesPlugin.getWorkspace().getRoot()
				.getFile(path).getLocation().toFile().getAbsolutePath());
		Resource emfResource = rs.getResource(uri, true);
		if (monitor != null) {
			monitor.worked(1);
		}
		return emfResource;
	}

	/**
	 * Validates an EMF resource.
	 * 
	 * @param emfResource
	 *            the resource to validate.
	 * @return the validation status.
	 */
	public static IStatus validate(Resource emfResource) {
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

	/**
	 * Converts the EOperation to the same string as what one meet in an Ecore
	 * Diagram file.
	 * 
	 * @param eOperation
	 *            the EOperation to convert to String.
	 * @return the converted String.
	 */
	public static String toEcoreDiagString(EOperation eOperation) {
		StringWriter buf = new StringWriter();
		buf.append(eOperation.getName()).append('(');
		boolean first = true;
		for (EParameter parameter : eOperation.getEParameters()) {
			if (!first) {
				buf.append(", ");
			}
			buf.append(parameter.getEType().getName());
			first = false;
		}
		buf.append(')');
		EClassifier eType = eOperation.getEType();
		if (eType != null) {
			buf.append(" : ");
			buf.append(eType.getName());
		}
		return buf.toString();
	}
}
