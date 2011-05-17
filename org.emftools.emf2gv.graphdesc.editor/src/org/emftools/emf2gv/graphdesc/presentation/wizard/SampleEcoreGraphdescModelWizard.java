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
package org.emftools.emf2gv.graphdesc.presentation.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.graphdesc.util.GraphdescHelper;

/**
 * A wizard allowing to import in the workspace the sample graphical description
 * for ecore models.
 * 
 * @author jbrazeau
 * 
 */
public class SampleEcoreGraphdescModelWizard extends
		AbstractGraphdescModelWizard {

	/**
	 * Default constructor.
	 */
	public SampleEcoreGraphdescModelWizard() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 * org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		super.init(workbench, selection);
		setWindowTitle("Sample EMF To GraphViz transformation model for Ecore resources");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.emftools.emf2gv.graphdesc.presentation.wizard.
	 * AbstractGraphdescModelWizard#getGraphicalDescriptionToSave()
	 */
	@Override
	protected GVFigureDescription getGraphicalDescriptionToSave() {
		return GraphdescHelper.getSampleGraphdescForEcoreModels();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.emftools.emf2gv.graphdesc.presentation.wizard.
	 * AbstractGraphdescModelWizard
	 * #createNewFileCreationPage(org.eclipse.jface.viewers
	 * .IStructuredSelection)
	 */
	@Override
	protected NewFileCreationPage createNewFileCreationPage(
			IStructuredSelection selection) {
		NewFileCreationPage wizardNewFileCreationPage = new NewFileCreationPage(
				"creationPage", selection);
		wizardNewFileCreationPage
				.setTitle("EMF To GraphViz transformation model");
		wizardNewFileCreationPage
				.setDescription("Create a new Graphdesc model");
		wizardNewFileCreationPage.setFileName("ecore.graphdesc");
		return wizardNewFileCreationPage;
	}

}
