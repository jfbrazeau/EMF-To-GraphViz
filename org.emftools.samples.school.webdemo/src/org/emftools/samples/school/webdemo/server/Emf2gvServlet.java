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
package org.emftools.samples.school.webdemo.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;
import org.emftools.emf2gv.graphdesc.Orientation;
import org.emftools.emf2gv.processor.core.OCLFilterExpression;
import org.emftools.emf2gv.processor.core.StandaloneProcessor;
import org.emftools.samples.school.SchoolPackage;

public class Emf2gvServlet extends HttpServlet {

	/** Default serial version UID */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// EMF Packages initialization
		SchoolPackage.eINSTANCE.eClass();
		GraphdescPackage.eINSTANCE.eClass();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			// TODO remove the log
			System.out.println("Emf2gvServlet : " + req.getQueryString());
			// Load
			ResourceSet rs = new ResourceSetImpl();
			// Register the default resource factory -- only needed for
			// stand-alone!
			rs.getResourceFactoryRegistry()
					.getExtensionToFactoryMap()
					.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
							new XMIResourceFactoryImpl());

			Resource modelResource = rs.getResource(URI.createURI(
					Emf2gvServlet.class.getResource("example.school")
							.toString(), true), true);
			System.out.println(modelResource);
			Resource graphDescResource = rs.getResource(URI.createURI(
					Emf2gvServlet.class.getResource("example.graphdesc")
							.toString(), true), true);
			GVFigureDescription figureDescription = (GVFigureDescription) graphDescResource
					.getContents().get(0);
			figureDescription.setOrientation("1".equals(req
					.getParameter("orientation")) ? Orientation.LEFT_TO_RIGHT
					: Orientation.TOP_DOWN);
			figureDescription.setAlignSameEClasses("true".equals(req
					.getParameter("alignSameEClasses")));
			System.out.println(graphDescResource);

			OCLFilterExpression classroomExpression = new OCLFilterExpression();
			classroomExpression.setEClass(SchoolPackage.eINSTANCE
					.getClassroom());
			classroomExpression.setExpression(req
					.getParameter("classroomOclExpression"));
			OCLFilterExpression studentExpression = new OCLFilterExpression();
			studentExpression.setEClass(SchoolPackage.eINSTANCE.getStudent());
			studentExpression.setExpression(req
					.getParameter("studentOclExpression"));

			List<OCLFilterExpression> expressions = Arrays
					.asList(new OCLFilterExpression[] { classroomExpression,
							studentExpression });
			System.out.println("Processing...");
			StandaloneProcessor.process(modelResource.getContents(), // model
					figureDescription, // Figure description
					new File("out"), // Work directory
					new File("out.jpg").getAbsolutePath(), // diagram file
					null, // Callback
					null, // Icon provider
					null, // dot command
					true, // Add validation decorators ?
					false, // Keep generated Graphviz source file ?
					"UTF-8", // Graphviz source encoding
					expressions, // Filters
					null, // ILogger
					null); // Progress monitor
			System.out.println("Send response...");
			resp.setContentType("image/jpg");

			InputStream in = new FileInputStream("out.jpg");
			byte[] buf = new byte[1024];
			int n = -1;
			while ((n = in.read(buf)) > 0) {
				resp.getOutputStream().write(buf, 0, n);
			}
			System.out.println("Fichier : "
					+ new File("out.jpg").getAbsolutePath());
			resp.getOutputStream().flush();
		} catch (Throwable t) {
			t.printStackTrace();
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					t.getMessage());
		}
		System.out.println("Done");
	}
}
