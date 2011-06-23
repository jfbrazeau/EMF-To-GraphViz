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
import org.emftools.emf2gv.graphdesc.GraphdescFactory;
import org.emftools.emf2gv.graphdesc.util.GraphdescHelper;

/**
 * The graphical description model creation wizard.
 * 
 * @author jbrazeau
 */
public class GraphdescModelWizard extends AbstractGraphdescModelWizard {

	/** The edited graphical description */
	private GVFigureDescription gvFigureDescription;

	/** The EPackage selection page */
	private EPackagesSelectionPage ePackageSelectionPage;

	/** The EClass selection page */
	private EClassesSelectionPage eClassesSelectionPage;

	/** The EReference selection page */
	private EReferencesSelectionPage eReferencesSelectionPage;

	/**
	 * Default constructor.
	 */
	public GraphdescModelWizard() {
		gvFigureDescription = GraphdescFactory.eINSTANCE
				.createGVFigureDescription();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 * org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		super.init(workbench, selection);
		setWindowTitle("EMF To GraphViz transformation model");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.emftools.emf2gv.graphdesc.presentation.wizard.
	 * AbstractGraphdescModelWizard#getGraphicalDescriptionToSave()
	 */
	@Override
	protected GVFigureDescription getGraphicalDescriptionToSave() {
		// Add Eattributes & colors & arrow styles
		GraphdescHelper
				.populateEAttributesAndAppearanceStyleData(gvFigureDescription);
		return gvFigureDescription;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		super.addPages();
		ePackageSelectionPage = new EPackagesSelectionPage(
				"ePackageSelectionPage", gvFigureDescription);
		ePackageSelectionPage.setTitle("EMF To GraphViz transformation model");
		ePackageSelectionPage
				.setDescription("Select the EPackages to include in the graph");
		addPage(ePackageSelectionPage);
		eClassesSelectionPage = new EClassesSelectionPage(
				"eClassesSelectionPage", gvFigureDescription);
		eClassesSelectionPage.setTitle("EMF To GraphViz transformation model");
		eClassesSelectionPage
				.setDescription("Select the EClasses to include in the graph");
		addPage(eClassesSelectionPage);
		eReferencesSelectionPage = new EReferencesSelectionPage(
				"eReferencesSelectionPage", gvFigureDescription);
		eReferencesSelectionPage
				.setTitle("EMF To GraphViz transformation model");
		eReferencesSelectionPage
				.setDescription("Select the EReferences to include in the graph");
		addPage(eReferencesSelectionPage);
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
		wizardNewFileCreationPage.setFileName("My.graphdesc");
		return wizardNewFileCreationPage;
	}
}
