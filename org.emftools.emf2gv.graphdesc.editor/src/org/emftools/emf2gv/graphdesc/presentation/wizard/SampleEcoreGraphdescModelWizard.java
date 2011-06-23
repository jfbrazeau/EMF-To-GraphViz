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
