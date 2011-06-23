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

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * Fake class used in order not to resolve all the registered EPackages of the
 * workspace.
 * 
 * This fake class is used for example in the tree of the wizard page that
 * allows to select the EPackages when one creates an new Graphical description
 * file.
 * 
 * @author jbrazeau
 * 
 */
public class EPackageFake extends EPackageImpl {

	/**
	 * Default constructor.
	 * @param uri the EPackage uri.
	 */
	public EPackageFake(String uri) {
		setNsURI(uri);
	}

}
