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
package org.emftools.emf2gv.processor.ui;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.emftools.emf2gv.processor.Activator;
import org.emftools.emf2gv.processor.ui.util.EMFModelElementSectionDialog;

/**
 * Emf2gv launch configuration main tab.
 */
public class EMF2GvLaunchConfigMainTab extends AbstractLaunchConfigurationTab {

	/**
	 * Viewer filter used to filter the .classpath & .project resources.
	 */
	private static ViewerFilter DEFAULT_VIEWER_FILTER = new ViewerFilter() {
		@Override
		public boolean select(Viewer viewer, Object parentElement,
				Object element) {
			boolean result = true;
			if (element instanceof IFile) {
				IFile file = (IFile) element;
				result &= !".project".equals(file.getName());
				result &= !".classpath".equals(file.getName());
			}
			return result;
		}
	};

	/**
	 * Viewer filter list containing the default viewer filter.
	 */
	private static List<ViewerFilter> DEFAULT_VIEWER_FILTERS = Arrays
			.asList(new ViewerFilter[] { DEFAULT_VIEWER_FILTER });

	/**
	 * Viewer filter used to see only the graphical descriptions.
	 */
	private static ViewerFilter GRAPHDESC_VIEWER_FILTER = new ViewerFilter() {
		@Override
		public boolean select(Viewer viewer, Object parentElement,
				Object element) {
			return !(element instanceof IFile)
					|| (((IFile) element).getName().endsWith(".graphdesc"));
		}
	};

	/**
	 * Viewer filter list containing the graphical description viewer filter.
	 */
	private static List<ViewerFilter> GRAPHDESC_VIEWER_FILTERS = Arrays
			.asList(new ViewerFilter[] { DEFAULT_VIEWER_FILTER,
					GRAPHDESC_VIEWER_FILTER });

	/**
	 * Viewer filter used to see only the JPG files.
	 */
	private static ViewerFilter JPG_VIEWER_FILTER = new ViewerFilter() {
		@Override
		public boolean select(Viewer viewer, Object parentElement,
				Object element) {
			boolean result = true;
			if (element instanceof IFile) {
				IFile file = (IFile) element;
				result = "jpg".equals(file.getFullPath().getFileExtension());
			}
			return result;
		}
	};

	/**
	 * Viewer filter list containing the JPG viewer filter.
	 */
	private static List<ViewerFilter> JPG_VIEWER_FILTERS = Arrays
			.asList(new ViewerFilter[] { DEFAULT_VIEWER_FILTER,
					JPG_VIEWER_FILTER });

	/**
	 * Checkbox allowig to generate the graphical description (instead of
	 * specifying a valid file path).
	 */
	private Button generateGraphDescCheckbox;

	/**
	 * Text containing the graphical description path.
	 */
	private Text graphDescText;

	/**
	 * Browse button allowing to select a graphical description file.
	 */
	private Button graphDescBrowseButton;

	/**
	 * Text containing the model path.
	 */
	private Text modelText;

	/**
	 * Radio button allowing to process all the resource contents.
	 */
	private Button processAllResourceContentsRadio;

	/**
	 * Radio button allowing to select a root to process.
	 */
	private Button selectElementToProcessRadio;

	/**
	 * Text containing the uri fragment of the model's element to use as a root
	 * when required.
	 */
	private Text selectedElementUriFragmentText;

	/**
	 * Brows button allowing to select the model element to prcess.
	 */
	private Button selectElementToProcessBrowseButton;

	/**
	 * Text containing the target image path.
	 */
	private Text targetImageText;

	/**
	 * Checkbox allowing to keep the generated graphviz source file.
	 */
	private Button keepGeneratedGvCheckBox;

	/**
	 * Checkbox allowing to open automatically an editor to visualize the
	 * generated image file.
	 */
	private Button autoOpenImageEditorCheckBox;

	/**
	 * Checkbox allowing to add validation decorators.
	 */
	private Button addValidationDecoratorsCheckbox;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.debug.ui.ILaunchConfigurationTab#setDefaults(org.eclipse.
	 * debug.core.ILaunchConfigurationWorkingCopy)
	 */
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.debug.ui.ILaunchConfigurationTab#performApply(org.eclipse
	 * .debug.core.ILaunchConfigurationWorkingCopy)
	 */
	public void performApply(ILaunchConfigurationWorkingCopy cfg) {
		// Graphical description
		EMF2GvLaunchConfigHelper.setGenerateGraphDesc(cfg,
				generateGraphDescCheckbox.getSelection());
		EMF2GvLaunchConfigHelper.setGraphDescPath(cfg, graphDescText.getText());

		// Model to process
		EMF2GvLaunchConfigHelper.setModelPath(cfg, modelText.getText());
		EMF2GvLaunchConfigHelper.setProcessAllResourceContents(cfg,
				processAllResourceContentsRadio.getSelection());
		EMF2GvLaunchConfigHelper.setSelectedElementUriFragment(cfg,
				selectedElementUriFragmentText.getText());

		// Target image
		EMF2GvLaunchConfigHelper.setTargetPath(cfg, targetImageText.getText());

		// Options
		EMF2GvLaunchConfigHelper.setAddValidationDecorators(cfg,
				addValidationDecoratorsCheckbox.getSelection());
		EMF2GvLaunchConfigHelper.setKeepGeneratedGvFile(cfg,
				keepGeneratedGvCheckBox.getSelection());
		EMF2GvLaunchConfigHelper.setAutoOpenImageEditor(cfg,
				autoOpenImageEditorCheckBox.getSelection());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.debug.ui.ILaunchConfigurationTab#initializeFrom(org.eclipse
	 * .debug.core.ILaunchConfiguration)
	 */
	public void initializeFrom(ILaunchConfiguration cfg) {
		try {
			// Graphical description
			generateGraphDescCheckbox.setSelection(EMF2GvLaunchConfigHelper
					.getGenerateGraphDesc(cfg));
			graphDescText.setText(EMF2GvLaunchConfigHelper
					.getGraphDescPath(cfg));
			updateGraphDescTextEnablement();

			// Model to process
			modelText.setText(EMF2GvLaunchConfigHelper.getModelPath(cfg));
			boolean procesAllResourcesContents = EMF2GvLaunchConfigHelper
					.getProcessAllResourceContents(cfg);
			processAllResourceContentsRadio
					.setSelection(procesAllResourcesContents);
			selectElementToProcessRadio
					.setSelection(!procesAllResourcesContents);
			selectedElementUriFragmentText.setText(EMF2GvLaunchConfigHelper
					.getSelectedElementUriFragment(cfg));
			updateSelectedElementToProcessEnablement();

			// Target image
			targetImageText
					.setText(EMF2GvLaunchConfigHelper.getTargetPath(cfg));

			// Options
			addValidationDecoratorsCheckbox
					.setSelection(EMF2GvLaunchConfigHelper
							.getAddValidationDecorators(cfg));
			keepGeneratedGvCheckBox.setSelection(EMF2GvLaunchConfigHelper
					.getKeepGeneratedGvFile(cfg));
			autoOpenImageEditorCheckBox.setSelection(EMF2GvLaunchConfigHelper
					.getAutoOpenImageEditor(cfg));

			scheduleUpdateJob();
		} catch (CoreException e) {
			Activator.getDefault().logError(
					"Unexpected error while retreiving run configuration", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getName()
	 */
	public String getName() {
		return "EMF To Graphviz";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.ui.AbstractLaunchConfigurationTab#getImage()
	 */
	@Override
	public Image getImage() {
		return Activator.getDefault().getImage("icons/EMF2Gv.gif");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.debug.ui.ILaunchConfigurationTab#createControl(org.eclipse
	 * .swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		Composite comp = new Composite(parent, SWT.NONE);
		setControl(comp);
		comp.setLayout(new GridLayout(1, false));
		comp.setFont(parent.getFont());

		// Entry fields creation
		createGraphDescGroup(comp);
		createModelGroup(comp);
		createTargetGroup(comp);
		createOptionsGroup(comp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.debug.ui.AbstractLaunchConfigurationTab#isValid(org.eclipse
	 * .debug.core.ILaunchConfiguration)
	 */
	@Override
	public boolean isValid(ILaunchConfiguration launchConfig) {
		setErrorMessage(null);
		boolean isValid = true;
		if (!generateGraphDescCheckbox.getSelection()) {
			isValid &= checkResourceField(graphDescText,
					"Graphical description file is not set",
					"Graphical description file path is not valid",
					"Graphical description file does not exist");
		}
		if (isValid)
			isValid = checkResourceField(modelText, "Model file is not set",
					"Model file path is not valid", "Model file does not exist");
		if (isValid)
			isValid = checkResourceField(targetImageText,
					"Target file is not set", "Target file path is not valid",
					null);
		return isValid;
	}

	/**
	 * Creates the graphical description group.
	 * 
	 * @param parent
	 *            the parent composite.
	 */
	private void createGraphDescGroup(Composite parent) {
		Group group = createGroup(parent, "Graphical description", 2);
		generateGraphDescCheckbox = new Button(group, SWT.CHECK);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		generateGraphDescCheckbox.setLayoutData(gd);
		generateGraphDescCheckbox
				.setText("Generate default graphical description");
		generateGraphDescCheckbox.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateGraphDescTextEnablement();
				updateLaunchConfigurationDialog();
			}
		});
		graphDescText = createText(group);
		graphDescBrowseButton = createBrowseButton(group,
				new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						handleBrowseGraphDescButton();
					}
				});
		updateGraphDescTextEnablement();
	}

	/**
	 * Creates the model group.
	 * 
	 * @param parent
	 *            the parent composite.
	 */
	private void createModelGroup(Composite parent) {
		Group group = createGroup(parent, "Model to draw", 2);
		modelText = createText(group);
		createBrowseButton(group, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleBrowseModelButton();
			}
		});

		// Radio group composite
		Composite composite = new Composite(group, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 2;
		composite.setLayoutData(gridData);

		// Radio button allowing to process all resource contents
		processAllResourceContentsRadio = new Button(composite, SWT.RADIO);
		processAllResourceContentsRadio
				.setText("Process all resource contents");
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 3;
		processAllResourceContentsRadio.setLayoutData(gridData);
		processAllResourceContentsRadio.setSelection(true);

		// Radio button allowing to select a root element to process
		selectElementToProcessRadio = new Button(composite, SWT.RADIO);
		selectElementToProcessRadio.setText("Select root element to process :");
		gridData = new GridData();
		selectElementToProcessRadio.setLayoutData(gridData);
		selectedElementUriFragmentText = createText(composite);
		selectedElementUriFragmentText.setEditable(false);
		selectElementToProcessBrowseButton = createBrowseButton(composite, null);
		updateSelectedElementToProcessEnablement();

		// Radio group listener registration
		SelectionAdapter radioButtonListener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateSelectedElementToProcessEnablement();
				updateLaunchConfigurationDialog();
			}
		};
		processAllResourceContentsRadio
				.addSelectionListener(radioButtonListener);
		selectElementToProcessRadio.addSelectionListener(radioButtonListener);

		// Browse button listener registration
		selectElementToProcessBrowseButton
				.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						handleSelectElementToProcessBrowseButton();
					}
				});
	}

	/**
	 * Creates the target group.
	 * 
	 * @param parent
	 *            the parent composite.
	 */
	private void createTargetGroup(Composite parent) {
		Group group = createGroup(parent, "Target image (jpg)", 3);
		targetImageText = createText(group);
		createButton(group, "New...", new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleBrowseNewTargetButton();
			}
		});
		createBrowseButton(group, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleBrowseExistingTargetButton();
			}
		});
	}

	/**
	 * Creates the options group.
	 * 
	 * @param parent
	 *            the parent composite.
	 */
	private void createOptionsGroup(Composite parent) {
		Group group = createGroup(parent, "Options", 1);

		// Checkbox allowing to keep the generated gv source
		Composite checkBoxComposite = new Composite(group, SWT.NONE);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		checkBoxComposite.setLayoutData(gridData);
		checkBoxComposite.setLayout(new GridLayout());
		addValidationDecoratorsCheckbox = new Button(checkBoxComposite,
				SWT.CHECK);
		addValidationDecoratorsCheckbox
				.setText("Add validation decorators (errors & warnings)");
		keepGeneratedGvCheckBox = new Button(checkBoxComposite, SWT.CHECK);
		keepGeneratedGvCheckBox.setText("Keep generated GraphViz source file");
		autoOpenImageEditorCheckBox = new Button(checkBoxComposite, SWT.CHECK);
		autoOpenImageEditorCheckBox
				.setText("Automatically open the generated image when the work is done");

		// Checkbox listener registration
		SelectionAdapter listener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateLaunchConfigurationDialog();
			}
		};
		addValidationDecoratorsCheckbox.addSelectionListener(listener);
		keepGeneratedGvCheckBox.addSelectionListener(listener);
		autoOpenImageEditorCheckBox.addSelectionListener(listener);
	}

	/**
	 * Handles a click on the browse button allowing to select en element to
	 * process.
	 */
	private void handleSelectElementToProcessBrowseButton() {
		trimTexts();
		// EMF resource loading
		ResourceSet rs = new ResourceSetImpl();
		rs.setPackageRegistry(EPackage.Registry.INSTANCE);
		URI resourceUri = URI.createPlatformResourceURI(
				new Path(modelText.getText()).toPortableString(), true);
		Resource emfResource = rs.getResource(resourceUri, true);
		EcoreUtil.resolveAll(emfResource);
		// Element selection dialog
		EObject selectedEObject = null;
		if (!"".equals(selectedElementUriFragmentText.getText())) {
			selectedEObject = emfResource
					.getEObject(selectedElementUriFragmentText.getText());
		}
		EMFModelElementSectionDialog dialog = new EMFModelElementSectionDialog(
				getShell(), emfResource, selectedEObject);
		if (Dialog.OK == dialog.open()) {
			EObject eObject = dialog.getSelection();
			selectedElementUriFragmentText.setText(emfResource.getURIFragment(
					eObject).toString());
		}
	}

	/**
	 * Trims the text fields.
	 */
	private void trimTexts() {
		trimText(graphDescText);
		trimText(modelText);
		trimText(selectedElementUriFragmentText);
		trimText(targetImageText);
	}

	/**
	 * Trims a text field.
	 * 
	 * @param text
	 *            the text field to trim.
	 */
	private static void trimText(Text text) {
		text.setText(text.getText().trim());
	}

	/**
	 * Changes the graphical descritpion text enablement.
	 */
	private void updateGraphDescTextEnablement() {
		boolean generateGraphDesc = generateGraphDescCheckbox.getSelection();
		graphDescText.setEnabled(!generateGraphDesc);
		graphDescBrowseButton.setEnabled(!generateGraphDesc);
		if (generateGraphDesc) {
			graphDescText.setText("");
		}
	}

	/**
	 * Changes the selected element text enablement.
	 */
	private void updateSelectedElementToProcessEnablement() {
		boolean processAllResourceContents = processAllResourceContentsRadio
				.getSelection();
		selectedElementUriFragmentText.setEnabled(!processAllResourceContents);
		selectElementToProcessBrowseButton
				.setEnabled(!processAllResourceContents);
		if (processAllResourceContents) {
			selectedElementUriFragmentText.setText("");
		}
	}

	/**
	 * Creates a group.
	 * 
	 * @param parent
	 *            the parent composite.
	 * @param name
	 *            the group's name.
	 * @param columnNb
	 *            the number of columns in the grid.
	 * @return the created group.
	 */
	private Group createGroup(Composite parent, String name, int columnNb) {
		Group group = new Group(parent, SWT.NONE);
		group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		group.setLayout(new GridLayout(columnNb, false));
		group.setText(name);
		return group;
	}

	/**
	 * Creates a text field.
	 * 
	 * @param parent
	 *            the parent composite.
	 * @return the created text field.
	 */
	private Text createText(Composite parent) {
		Text text = new Text(parent, SWT.BORDER);
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updateLaunchConfigurationDialog();
			}
		});
		return text;
	}

	/**
	 * Creates a browse button.
	 * 
	 * @param parent
	 *            the parent composite.
	 * @param buttonListener
	 *            the listener.
	 * @return the created button.
	 */
	private Button createBrowseButton(Composite parent,
			SelectionListener buttonListener) {
		return createButton(parent, "Browse...", buttonListener);
	}

	/**
	 * Creates a button.
	 * 
	 * @param parent
	 *            the parent composite.
	 * @param label
	 *            the label to use.
	 * @param buttonListener
	 *            the listener.
	 * @return the created button.
	 */
	private Button createButton(Composite parent, String label,
			SelectionListener buttonListener) {
		Button button = new Button(parent, SWT.NONE);
		button.setText(label);
		if (buttonListener != null) {
			button.addSelectionListener(buttonListener);
		}
		return button;
	}

	/**
	 * Handles a click on the browse button allowing to select a graphical
	 * description file.
	 */
	private void handleBrowseGraphDescButton() {
		trimTexts();
		Object[] initialSelection = buildInitialSelectionFromText(graphDescText);
		IFile[] result = WorkspaceResourceDialog.openFileSelection(getShell(),
				"File selection",
				"Please select the graphical description file", false,
				initialSelection, GRAPHDESC_VIEWER_FILTERS);
		if (result != null && result.length > 0) {
			String path = result[0].getFullPath().toString();
			graphDescText.setText(path);
		}
	}

	/**
	 * Builds a initial selection list from a text field.
	 * 
	 * @param text
	 *            the text field to use.
	 * @return the initial selection list.
	 */
	private Object[] buildInitialSelectionFromText(Text text) {
		Object[] initialSelection = null;
		if (!"".equals(text.getText())) {
			IPath path = new Path(text.getText());
			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
			if (file.exists() && file.isAccessible()) {
				initialSelection = new Object[] { file };
			}
		}
		return initialSelection;
	}

	/**
	 * Handles a click on the browse button allowing to select a model file.
	 */
	private void handleBrowseModelButton() {
		trimTexts();
		Object[] initialSelection = buildInitialSelectionFromText(modelText);
		IFile[] result = WorkspaceResourceDialog.openFileSelection(getShell(),
				"File selection", "Please select the model file", false,
				initialSelection, DEFAULT_VIEWER_FILTERS);
		if (result != null && result.length > 0) {
			modelText.setText(result[0].getFullPath().toString());
			// If the target is not defined, we predefine it
			if ("".equals(targetImageText.getText())) {
				targetImageText.setText(modelText.getText() + ".jpg");
			}
		}
	}

	/**
	 * Handles a click on the browse button allowing to select a new target
	 * image file.
	 */
	private void handleBrowseNewTargetButton() {
		trimTexts();
		IFile result = WorkspaceResourceDialog.openNewFile(getShell(),
				"New file", "Please enter the new file name and path", null,
				DEFAULT_VIEWER_FILTERS);
		if (result != null) {
			String path = result.getFullPath().toString();
			if (!path.endsWith(".jpg"))
				path += ".jpg";
			targetImageText.setText(path);
		}
	}

	/**
	 * Handles a click on the browse button allowing to select an existing
	 * target file.
	 */
	private void handleBrowseExistingTargetButton() {
		trimTexts();
		Object[] initialSelection = buildInitialSelectionFromText(targetImageText);
		IFile[] result = WorkspaceResourceDialog.openFileSelection(getShell(),
				"File selection", "Please select the image file", false,
				initialSelection, JPG_VIEWER_FILTERS);
		if (result != null) {
			targetImageText.setText(result[0].getFullPath().toString());
		}
	}

	/**
	 * Checks a resource field.
	 * 
	 * @param field
	 *            the field to check.
	 * @param errorMessageIfEmpty
	 *            the error message to use when the field is empty.
	 * @param errorMessageIfNotValid
	 *            the error message to use when the field does not contain a
	 *            valid path.
	 * @param errorMessageIfExistsNot
	 *            the error message to use when the field does not contain an
	 *            existing path.
	 * @return a boolean indicating if the field is valid.
	 */
	private boolean checkResourceField(Text field, String errorMessageIfEmpty,
			String errorMessageIfNotValid, String errorMessageIfExistsNot) {
		// TODO add namespace check ?
		boolean isValid = true;
		String path = field.getText();
		if (path == null || "".equals(path.trim())) {
			setErrorMessage(errorMessageIfEmpty);
			isValid = false;
		} else {
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IStatus status = workspace.validatePath(path, IResource.FILE);
			if (!status.isOK()) {
				setErrorMessage(errorMessageIfNotValid);
				isValid = false;
			} else if (errorMessageIfExistsNot != null) {
				IFile file = workspace.getRoot().getFile(new Path(path));
				if (!file.exists()) {
					setErrorMessage(errorMessageIfExistsNot);
					isValid = false;
				}
			}
		}
		return isValid;
	}
}
