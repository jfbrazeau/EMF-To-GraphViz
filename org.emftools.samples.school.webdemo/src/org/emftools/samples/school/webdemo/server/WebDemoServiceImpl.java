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
