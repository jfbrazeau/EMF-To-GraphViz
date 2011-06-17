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
import org.emftools.emf2gv.graphdesc.Filter;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.graphdesc.GraphdescFactory;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;
import org.emftools.emf2gv.graphdesc.Orientation;
import org.emftools.emf2gv.processor.core.StandaloneProcessor;
import org.emftools.samples.school.SchoolPackage;

/**
 * The servlet that generates and serves the diagram images (using Emf2gv in
 * standalone mode).
 * 
 * @author jbrazeau
 */
public class Emf2gvServlet extends HttpServlet {

	/** Default serial version UID */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// EMF Packages initialization
		SchoolPackage.eINSTANCE.eClass();
		GraphdescPackage.eINSTANCE.eClass();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			// Load the model resource
			ResourceSet rs = new ResourceSetImpl();
			rs.getResourceFactoryRegistry()
					.getExtensionToFactoryMap()
					.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
							new XMIResourceFactoryImpl());
			Resource modelResource = rs.getResource(URI.createURI(
					Emf2gvServlet.class.getResource("sample.school")
							.toString(), true), true);

			// Load the graphical description
			Resource graphDescResource = rs.getResource(URI.createURI(
					Emf2gvServlet.class.getResource("sample.graphdesc")
							.toString(), true), true);

			// Change the orientation and the alignment with the request
			// parameters
			GVFigureDescription figureDescription = (GVFigureDescription) graphDescResource
					.getContents().get(0);
			figureDescription.setOrientation("1".equals(req
					.getParameter("orientation")) ? Orientation.LEFT_TO_RIGHT
					: Orientation.TOP_DOWN);
			figureDescription.setAlignSameEClasses("true".equals(req
					.getParameter("alignSameEClasses")));

			// OCL expressions parsing (allowing to select which students and
			// classrooms have to de drawn on the diagram)
			Filter classroomFilter = GraphdescFactory.eINSTANCE.createFilter();
			classroomFilter.setFilteredType(SchoolPackage.eINSTANCE
					.getClassroom());
			classroomFilter.setFilterExpression(req
					.getParameter("classroomOclExpression"));
			Filter studentFilter = GraphdescFactory.eINSTANCE.createFilter();;
			studentFilter.setFilteredType(SchoolPackage.eINSTANCE.getStudent());
			studentFilter.setFilterExpression(req
					.getParameter("studentOclExpression"));
			List<Filter> filters = Arrays
					.asList(new Filter[] { classroomFilter,
							studentFilter });

			// Work dir and diagram file name build
			File workDir = new File(System.getProperty("java.io.tmpdir")
					+ "/emf2gv-" + req.getSession().getId());
			File diagramFile = new File(System.getProperty("java.io.tmpdir")
					+ "/diagram-" + req.getSession().getId() + ".jpg");
			StandaloneProcessor.process(modelResource.getContents(), // model
					figureDescription, // Figure description
					workDir, // Work directory
					diagramFile.getAbsolutePath(), // diagram file
					null, // Callback : callbak is not necessary in this context 
					null, // Icon provider : we use the default behavior
					null, // dot command : dot is in the system PATH
					true, // Add validation decorators : yes
					false, // Keep generated Graphviz source file : no
					"UTF-8", // Graphviz source encoding
					filters, // Additional filters
					null, // ILogger : no logger
					null); // Progress monitor : no progress monitor

			// Send the response
			resp.setContentType("image/jpg");
			InputStream in = new FileInputStream(diagramFile);
			byte[] buf = new byte[1024];
			int n = -1;
			while ((n = in.read(buf)) > 0) {
				resp.getOutputStream().write(buf, 0, n);
			}
			resp.getOutputStream().flush();

			// Delete the generated diagram file
			diagramFile.delete();
		} catch (Throwable t) {
			t.printStackTrace();
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					t.getMessage());
		}
	}
}
