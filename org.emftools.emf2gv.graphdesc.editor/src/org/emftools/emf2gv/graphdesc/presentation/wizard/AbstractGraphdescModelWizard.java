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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ISetSelectionTarget;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.graphdesc.presentation.GraphdescEditorPlugin;
import org.emftools.validation.builder.util.EMFValidationNatureHelper;

public abstract class AbstractGraphdescModelWizard extends Wizard implements
		INewWizard {

	/** The new file creation page */
	private NewFileCreationPage wizardNewFileCreationPage;

	/** The selection */
	private IStructuredSelection selection;

	/** The workbench */
	private IWorkbench workbench;

	/**
	 * Default constructor.
	 */
	public AbstractGraphdescModelWizard() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 * org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
		this.workbench = workbench;
		setDefaultPageImageDescriptor(ExtendedImageRegistry.INSTANCE
				.getImageDescriptor(GraphdescEditorPlugin.INSTANCE
						.getImage("full/wizban/NewGraphdesc")));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		try {
			final IFile graphdescFile = ResourcesPlugin
					.getWorkspace()
					.getRoot()
					.getFile(
							wizardNewFileCreationPage.getContainerFullPath()
									.append(wizardNewFileCreationPage
											.getFileName()));
			// Do the work within an operation.
			WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
				@Override
				protected void execute(IProgressMonitor progressMonitor) {
					try {
						// Gets the graphical description to save
						GVFigureDescription gvFigureDescription = getGraphicalDescriptionToSave();

						// Create a resource set
						ResourceSet resourceSet = new ResourceSetImpl();

						// Get the URI of the model file.
						URI fileURI = URI.createPlatformResourceURI(
								graphdescFile.getFullPath().toString(), true);

						// Create a resource for this file.
						Resource resource = resourceSet.createResource(fileURI);

						// Add the initial model object to the contents.
						resource.getContents().add(gvFigureDescription);

						// Save the contents of the resource to the file system.
						Map<Object, Object> options = new HashMap<Object, Object>();
						options.put(XMLResource.OPTION_ENCODING, "UTF-8");
						resource.save(options);
					} catch (Exception exception) {
						GraphdescEditorPlugin.INSTANCE.log(exception);
					} finally {
						progressMonitor.done();
					}
				}
			};

			getContainer().run(false, false, operation);

			// Select the new file resource in the current view.
			//
			IWorkbenchWindow workbenchWindow = workbench
					.getActiveWorkbenchWindow();
			IWorkbenchPage page = workbenchWindow.getActivePage();
			final IWorkbenchPart activePart = page.getActivePart();
			if (activePart instanceof ISetSelectionTarget) {
				final ISelection targetSelection = new StructuredSelection(
						graphdescFile);
				getShell().getDisplay().asyncExec(new Runnable() {
					public void run() {
						((ISetSelectionTarget) activePart)
								.selectReveal(targetSelection);
					}
				});
			}

			// Open an editor on the new file.
			//
			try {
				page.openEditor(
						new FileEditorInput(graphdescFile),
						workbench
								.getEditorRegistry()
								.getDefaultEditor(
										graphdescFile.getFullPath().toString())
								.getId());
			} catch (PartInitException exception) {
				MessageDialog.openError(workbenchWindow.getShell(),
						GraphdescEditorPlugin.INSTANCE
								.getString("_UI_OpenEditorError_label"),
						exception.getMessage());
				return false;
			}

			// Activates the validation nature if required
			if (!EMFValidationNatureHelper
					.hasNature(graphdescFile.getProject())) {
				EMFValidationNatureHelper.toggleNature(graphdescFile
						.getProject());
			}

			return true;
		} catch (Exception exception) {
			GraphdescEditorPlugin.INSTANCE.log(exception);
			return false;
		}
	}

	/**
	 * @return the graphical description to save.
	 */
	protected abstract GVFigureDescription getGraphicalDescriptionToSave();

	/**
	 * Creates and returns the new file creation page.
	 * 
	 * @param selection
	 *            the selection.
	 * 
	 * @return the new file creation page.
	 */
	protected abstract NewFileCreationPage createNewFileCreationPage(
			IStructuredSelection selection);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		this.wizardNewFileCreationPage = createNewFileCreationPage(selection);
		addPage(wizardNewFileCreationPage);
	}

}
