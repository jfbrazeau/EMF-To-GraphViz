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

import java.net.URL;

import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.emftools.emf2gv.graphdesc.ArrowType;
import org.emftools.emf2gv.graphdesc.provider.GraphdescEditPlugin;
import org.osgi.framework.Bundle;

/**
 * ArrowType item label provider.
 * 
 * Manages the arrow types icons.
 */
public class ArrowTypeItemLabelProvider implements IItemLabelProvider {

	/**
	 * Arrow types categories enumeration.
	 */
	public static enum Category {
		Source, Target
	}

	/** The category of the label provider (source or target arrow) */
	private Category category;

	/**
	 * Default constructor.
	 * @param category the category to use.
	 */
	public ArrowTypeItemLabelProvider(Category category) {
		this.category = category;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.IItemLabelProvider#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		ArrowType arrowType = (ArrowType) object;
		return arrowType.getName();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.IItemLabelProvider#getImage(java.lang.Object)
	 */
	public Object getImage(Object object) {
		ArrowType arrowType = (ArrowType) object;
		Bundle bundle = GraphdescEditPlugin.getPlugin().getBundle();
		URL url = bundle.getResource("/icons/full/obj16/arrows/"
				+ category.toString().toLowerCase() + '/'
				+ arrowType.toString().toLowerCase() + ".png");
		return url;
	}

}
