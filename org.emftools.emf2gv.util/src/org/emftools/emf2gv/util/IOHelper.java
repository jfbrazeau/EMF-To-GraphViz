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

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * I/O helper class.
 * 
 * @author jbrazeau
 */
public class IOHelper {

	/**
	 * Copy a stream into another.
	 * 
	 * @param in
	 *            the stream to read from.
	 * @param out
	 *            the stream to write to.
	 * @throws IOException
	 *             thrown if an I/O error occurs.
	 */
	public static void copyTo(InputStream in, OutputStream out)
			throws IOException {
		byte[] buf = new byte[1024];
		int n = 0;
		while ((n = in.read(buf)) > 0) {
			out.write(buf, 0, n);
		}
	}

	/**
	 * Loads an image and converts it to PNG format.
	 * 
	 * @param srcImageStream
	 *            the source stream containing the image to convert.
	 * @return the converted image.
	 * @throws IOException
	 *             throw if an I/O error occurs.
	 */
	public static byte[] loadAndConvertImageToPng(InputStream srcImageStream)
			throws IOException {
		BufferedImage image = ImageIO.read(srcImageStream);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(image, "png", out);
		return out.toByteArray();
	}

	/**
	 * Saves a file.
	 * 
	 * @param path
	 *            the file's path.
	 * @param content
	 *            the file's content.
	 * @param monitor
	 *            the progress monitor.
	 * @return the saved file.
	 * @throws IOException
	 *             throw if an I/O error occurs.
	 */
	public static void save(String path, byte[] content,
			IProgressMonitor monitor) throws IOException {
		monitor.beginTask("Saving " + path, 1);
		FileOutputStream out = new FileOutputStream(path);
		out.write(content);
		out.flush();
		out.close();
	}

}
