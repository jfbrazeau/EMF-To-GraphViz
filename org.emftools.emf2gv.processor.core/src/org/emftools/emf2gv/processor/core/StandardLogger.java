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


/**
 * Default <code>ILogger</code> implementation.
 * @author jbrazeau
 */
public class StandardLogger implements ILogger {
	
	/**
	 * Logs an error.
	 * 
	 * @param error
	 *            the error message.
	 * @param throwable
	 *            the throwable to log.
	 */
	public void logError(String error, Throwable throwable) {
		System.err.println(error);
		if (throwable != null) {
			throwable.printStackTrace();
		}
	}

	/**
	 * Logs an info.
	 * 
	 * @param info
	 *            the information message.
	 */
	public void logInfo(String info) {
		System.out.println(info);
	}

}
