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
package org.emftools.emf2gv.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory.Descriptor;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory.Descriptor.Registry;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;

public class EMFHelper {
	
	public static AdapterFactory getEcoreAdapterFactory() {
		return getAdapterFactory(Arrays.asList(new EPackage[] { EcorePackage.eINSTANCE }));
	}

	public static AdapterFactory getAdapterFactory(List<EPackage> ePackages) {
		Registry reg = EMFEditPlugin.getComposedAdapterFactoryDescriptorRegistry();
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		for (EPackage ePackage : ePackages) {
			List<Object> list = Arrays.asList(new Object[] { ePackage, org.eclipse.emf.edit.provider.ITreeItemContentProvider.class });
			Descriptor desc = reg.getDescriptor(list);
			if (desc != null) {
				AdapterFactory af = desc.createAdapterFactory();
				adapterFactory.addAdapterFactory(af);
			}
		}
		return adapterFactory;
	}

	public static EPackage resolve(EPackageFake ePackageFake) {
		// EPackage resolution
		String uri = ePackageFake.getNsURI();
		return EPackage.Registry.INSTANCE.getEPackage(uri);
	}
	
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
}
