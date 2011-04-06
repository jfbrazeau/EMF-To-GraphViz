/**
 * Copyright (c) 2010-2011, Jean-Francois Brazeau. All rights reserved.
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
