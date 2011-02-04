/**
 * Copyright (c) 2010, Jean-Francois Brazeau. All rights reserved.
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
import org.emftools.emf2gv.graphdesc.GraphdescFactory;
import org.emftools.emf2gv.graphdesc.presentation.GraphdescEditorPlugin;
import org.emftools.emf2gv.graphdesc.util.GraphdescGenerator;
import org.emftools.validation.builder.util.EMFValidationNatureHelper;

/**
 * The graphical description model creation wizard.
 * 
 * @author jbrazeau
 */
public class GraphdescModelWizard extends Wizard implements INewWizard {

	/** The edited graphical description */
	private GVFigureDescription gvFigureDescription;

	/** The selection */
	private IStructuredSelection selection;

	/** The workbench */
	private IWorkbench workbench;

	/** The new file creation page */
	private NewFileCreationPage wizardNewFileCreationPage;

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
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
		this.workbench = workbench;
		setWindowTitle("EMF To GraphViz transformation model");
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
			final IFile modelFile = ResourcesPlugin
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
						// Add Eattributes & colors & arrow styles
						GraphdescGenerator
								.addEAttributesAndAppearanceStyleData(gvFigureDescription);

						// Create a resource set
						ResourceSet resourceSet = new ResourceSetImpl();

						// Get the URI of the model file.
						URI fileURI = URI.createPlatformResourceURI(modelFile
								.getFullPath().toString(), true);

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
						modelFile);
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
						new FileEditorInput(modelFile),
						workbench
								.getEditorRegistry()
								.getDefaultEditor(
										modelFile.getFullPath().toString())
								.getId());
			} catch (PartInitException exception) {
				MessageDialog.openError(workbenchWindow.getShell(),
						GraphdescEditorPlugin.INSTANCE
								.getString("_UI_OpenEditorError_label"),
						exception.getMessage());
				return false;
			}

			// Activates the validation nature if required
			if (!EMFValidationNatureHelper.hasNature(modelFile.getProject())) {
				EMFValidationNatureHelper.toggleNature(modelFile.getProject());
			}

			return true;
		} catch (Exception exception) {
			GraphdescEditorPlugin.INSTANCE.log(exception);
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		wizardNewFileCreationPage = new NewFileCreationPage("creationPage",
				selection);
		wizardNewFileCreationPage
				.setTitle("EMF To GraphViz transformation model");
		wizardNewFileCreationPage
				.setDescription("Create a new Graphdesc model");
		wizardNewFileCreationPage.setFileName("My.graphdesc");
		addPage(wizardNewFileCreationPage);
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

}
