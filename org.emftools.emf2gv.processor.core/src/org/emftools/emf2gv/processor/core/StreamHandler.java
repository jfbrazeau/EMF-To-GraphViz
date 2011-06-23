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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * A utility class used to catch a process stream (std / err).
 */
final class StreamHandler extends Thread {

	/** The input stream to read */
	private InputStream in;

	/** The temporary buffer */
	private ByteArrayOutputStream out = new ByteArrayOutputStream();

	/**
	 * Default constructor.
	 * 
	 * @param in
	 *            the input stream to read.
	 */
	public StreamHandler(InputStream in) {
		this.in = in;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		try {
			byte[] buf = new byte[1024];
			int n = 0;
			while ((n = in.read(buf)) > 0) {
				out.write(buf, 0, n);
			}
		} catch (IOException e) {
			Activator.getDefault().logError(
					"Unepxected error while reading stream", e);
		}
	}

	/**
	 * @return the stream final content.
	 */
	public byte[] getResult() {
		return out.toByteArray();
	}

}
