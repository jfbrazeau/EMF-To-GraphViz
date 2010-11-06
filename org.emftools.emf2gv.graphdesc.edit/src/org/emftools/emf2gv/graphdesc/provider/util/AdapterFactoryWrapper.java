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
package org.emftools.emf2gv.graphdesc.provider.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notifier;

/**
 * AdapterFactory wrapper used to intercept the adaption calls.
 * 
 * It is used to override the label provider returned by default by EMF for the
 * EPackages property in order to show the package uri instead of the package
 * name.
 * 
 * Unload EPackages have a null name which is not convenient in the properties
 * view.
 */
public abstract class AdapterFactoryWrapper implements AdapterFactory {

	/** The wrapped adapter factory */
	private AdapterFactory wrappedFactory;

	/**
	 * Default constructor.
	 * 
	 * @param wrappedFactory
	 *            the wrapped adapter factory.
	 */
	public AdapterFactoryWrapper(AdapterFactory wrappedFactory) {
		this.wrappedFactory = wrappedFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.common.notify.AdapterFactory#isFactoryForType(java.lang
	 * .Object)
	 */
	public boolean isFactoryForType(Object type) {
		return wrappedFactory.isFactoryForType(type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.common.notify.AdapterFactory#adaptNew(org.eclipse.emf
	 * .common.notify.Notifier, java.lang.Object)
	 */
	public Adapter adaptNew(Notifier target, Object type) {
		return wrappedFactory.adaptNew(target, type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.common.notify.AdapterFactory#adaptAllNew(org.eclipse.
	 * emf.common.notify.Notifier)
	 */
	public void adaptAllNew(Notifier notifier) {
		wrappedFactory.adaptAllNew(notifier);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.common.notify.AdapterFactory#adapt(org.eclipse.emf.common
	 * .notify.Notifier, java.lang.Object)
	 */
	public Adapter adapt(Notifier target, Object type) {
		return wrappedFactory.adapt(target, type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.notify.AdapterFactory#adapt(java.lang.Object,
	 * java.lang.Object)
	 */
	public Object adapt(Object object, Object type) {
		Object defaultAdapter = wrappedFactory.adapt(object, type);
		Object result = doAdapt(object, type, defaultAdapter);
		if (result == null) {
			result = defaultAdapter;
		}
		return result;
	}

	/**
	 * Adaption method delegate.
	 * 
	 * @param object
	 *            the object to adapt.
	 * @param type
	 *            the required type.
	 * @param defaultAdapter
	 *            the default adapter.
	 * @return the adapter to use.
	 */
	public abstract Object doAdapt(Object object, Object type,
			Object defaultAdapter);
}
