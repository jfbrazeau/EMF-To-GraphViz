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
package org.emftools.samples.school.webdemo.server;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.helper.OCLHelper;
import org.emftools.emf2gv.util.OCLProvider;
import org.emftools.samples.school.SchoolPackage;
import org.emftools.samples.school.webdemo.client.WebDemoService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class WebDemoServiceImpl extends RemoteServiceServlet implements
		WebDemoService {

	/** The OCL Helper that helps to parse the OCL expressions */
	private OCLHelper<EClassifier, EOperation, EStructuralFeature, Constraint> oclHelper = OCLProvider
			.newOCL().createOCLHelper();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.emftools.samples.school.webdemo.client.WebDemoService#validate(java
	 * .lang.String, java.lang.String)
	 */
	public String validate(String eClassName, String oclExpression) {
		EClass eClass = "Student".equals(eClassName) ? SchoolPackage.eINSTANCE
				.getStudent() : SchoolPackage.eINSTANCE.getClassroom();
		oclHelper.setContext(eClass);
		try {
			oclHelper.createInvariant(oclExpression);
			return null;
		} catch (ParserException e) {
			return e.getMessage();
		}
	}

}
