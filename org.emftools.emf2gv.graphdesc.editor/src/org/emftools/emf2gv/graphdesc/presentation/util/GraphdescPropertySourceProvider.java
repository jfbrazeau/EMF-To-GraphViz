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
package org.emftools.emf2gv.graphdesc.presentation.util;

import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;

/**
 * Graphical description property source provider.
 */
public class GraphdescPropertySourceProvider implements IPropertySourceProvider {

	/** The adapter factory */
	private AdapterFactory adapterFactory;
	
	/** The icons cache */
	private Map<String, Image> colorIcons;

	/**
	 * Default constructor.
	 * @param adapterFactory the adapter factory.
	 * @param colorIcons the icons cache.
	 */
	public GraphdescPropertySourceProvider(AdapterFactory adapterFactory,
			Map<String, Image> colorIcons) {
		this.adapterFactory = adapterFactory;
		this.colorIcons = colorIcons;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.IPropertySourceProvider#getPropertySource(java.lang.Object)
	 */
	public IPropertySource getPropertySource(Object object) {
		if (object instanceof IPropertySource) {
			return (IPropertySource) object;
		} else {
			IItemPropertySource itemPropertySource = (IItemPropertySource) (object instanceof EObject
					&& ((EObject) object).eClass() == null ? null
					: adapterFactory.adapt(object, IItemPropertySource.class));

			return itemPropertySource != null ? createPropertySource(object,
					itemPropertySource) : null;
		}
	}

	/**
	 * Creates a property source for the given object.
	 * @param object the object.
	 * @param itemPropertySource the item property source.
	 * @return the property source.
	 */
	protected IPropertySource createPropertySource(Object object,
			IItemPropertySource itemPropertySource) {
		return new GraphdescPropertySource(object, itemPropertySource,
				colorIcons);
	}

}
