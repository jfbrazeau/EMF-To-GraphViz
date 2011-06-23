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
