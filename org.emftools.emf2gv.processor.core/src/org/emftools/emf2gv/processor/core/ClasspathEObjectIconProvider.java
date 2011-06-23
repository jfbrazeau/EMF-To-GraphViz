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
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

/**
 * Default implementation of <code>IEObjectIconProvider</code> which lookups in
 * the classpath to retrieve the EClasses icons.
 * 
 * <p>
 * The icons are searched in the classpath using the following order :
 * <ul>
 * <li>
 * <code>&lt;basepath&gt;/&lt;epackage_name&gt;/&lt;eclass_name&gt;.(gif|png)</code>
 * </li>
 * <li><code>&lt;basepath&gt;/&lt;eclass_name&gt;.(gif|png)</code></li>
 * <li>
 * <code>&lt;basepath&gt;/icons/&lt;epackage_name&gt;/&lt;eclass_name&gt;.(gif|png)</code>
 * </li>
 * <li><code>&lt;basepath&gt;/icons/&lt;eclass_name&gt;.(gif|png)</code></li>
 * <li><code>/icons/full/obj16/&lt;eclass_name&gt;.(gif|png)</code></li>
 * </ul>
 * where <code>&lt;basepath&gt;</code> is the <code>basePath</code> constructor
 * parameter.
 * </p>
 * <p>
 * The last attemps corresponds to the default path that is used in the
 * generated <code>*.edit</code> EMF plugin so that if this plugin is put in the
 * classpath, icons are automatically retrieved.
 * </p>
 * 
 * @author jbrazeau
 */
public class ClasspathEObjectIconProvider implements IEObjectIconProvider {

	/** The classloader to use to retrieve the icons in the classpath. */
	private ClassLoader classLoader;

	/** The base path to apply to retrieve the icons. */
	private String basePath;

	/** Icons URL Cache */
	private Map<EClass, URL> iconsUrlCache = new HashMap<EClass, URL>();

	/**
	 * Constructor allowing to specify the classloader to use to retrieve the
	 * icons in the classpath and the base path.
	 * 
	 * @param classLoader
	 *            the classloader to use.
	 * @param basePath
	 *            the base path to apply to retrieve the icons.
	 */
	public ClasspathEObjectIconProvider(ClassLoader classLoader, String basePath) {
		this.classLoader = classLoader != null ? classLoader
				: ClasspathEObjectIconProvider.class.getClassLoader();
		this.basePath = basePath != null ? basePath.trim() : "";
		if (basePath.length() > 0 && !basePath.endsWith("/")) {
			this.basePath += "/";
		}
	}

	/**
	 * Default no argument constructor.
	 */
	public ClasspathEObjectIconProvider() {
		this(ClasspathEObjectIconProvider.class.getClassLoader(), "");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.emftools.emf2gv.processor.core.IEObjectIconProvider#getIcon(org.eclipse
	 * .emf.ecore.EObject)
	 */
	public URL getIcon(EObject eObject) {
		URL result = null;
		if (eObject != null) {
			EClass eClass = eObject.eClass();
			result = iconsUrlCache.get(eClass);
			if (result == null) {
				EPackage ePackage = eClass.getEPackage();
				// First attempt with :
				// <basepath>/<epackage_name>/<eclass_name>.(gif|png)
				result = getImage(basePath + ePackage.getName() + "/"
						+ eClass.getName());
				if (result == null) {
					// Second attempt :
					// <basepath>/<eclass_name>.(gif|png)
					result = getImage(basePath + eClass.getName());
					if (result == null && !basePath.endsWith("icons/")) {
						// Third attempt with :
						// <basepath>/icons/<epackage_name>/<eclass_name>.(gif|png)
						result = getImage(basePath + "icons/"
								+ ePackage.getName() + "/" + eClass.getName());
						if (result == null) {
							// Fourth attempt :
							// <basepath>/icons/<eclass_name>.(gif|png)
							result = getImage(basePath + "icons/"
									+ eClass.getName());
							if (result == null) {
								// Last attempt :
								// icons/full/obj16/<eclass_name>.(gif|png)
								// (this path corresponds to the default
								// path used in the emf.edit plugin).
								result = getImage("icons/full/obj16/"
										+ eClass.getName());
							}
						}
					}
				}
			}
			// Registers the URL in the cache
			if (result != null) {
				iconsUrlCache.put(eClass, result);
			}
		}
		return result;
	}

	/**
	 * @param path
	 *            the path.
	 * @return the image URL associated to the given path (both gif and png
	 *         images are tested).
	 */
	public URL getImage(String path) {
		URL result = null;
		result = classLoader.getResource(path + ".gif");
		if (result == null) {
			result = classLoader.getResource(path + ".png");
		}
		return result;
	}

}
