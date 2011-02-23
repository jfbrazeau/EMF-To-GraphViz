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
package org.emftools.emf2gv.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
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
	 * @throws CoreException
	 *             thrown if an unexpected error occurs.
	 */
	public static IFile save(IPath path, byte[] content,
			IProgressMonitor monitor) throws CoreException {
		monitor.beginTask("Saving " + path, 1);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IFile file = root.getFile(path);
		if (file.exists()) {
			file.setContents(new ByteArrayInputStream(content), true, true,
					null);
		} else {
			file.create(new ByteArrayInputStream(content), true, null);
		}
		return file;
	}

}
