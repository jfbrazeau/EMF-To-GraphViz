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
package org.emftools.emf2gv.processor.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.helper.OCLHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.graphdesc.util.GraphdescHelper;
import org.emftools.emf2gv.util.EMFHelper;
import org.emftools.emf2gv.util.OCLProvider;
import org.emftools.emf2gv.util.ui.OCLInputDialog;

/**
 * Emf2gv launch configuration tab containing filter conditions.
 */
public class EMF2GvLaunchConfigFiltersTab extends AbstractEMF2GvLaunchConfigTab {

	/** Adapter factory label provider */
	private AdapterFactoryLabelProvider adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(
			EMFHelper.getEcoreAdapterFactory());

	/**
	 * The main tab reference (that is used to retrieve the graphical
	 * description file)
	 */
	private EMF2GvLaunchConfigMainTab mainTab;

	/** ECLasses table viewer */
	private CheckboxTableViewer eClassesTableViewer;

	/** Table content */
	private List<OCLConstraint> oCLConstraints = new ArrayList<OCLConstraint>();

	/** Button allowing to add a new value */
	private Button newButton;

	/** Button allowing to edit an value */
	private Button editButton;

	/** Button allowing to remove an value */
	private Button removeButton;

	/** The OCL Helper instance */
	private OCLHelper<EClassifier, EOperation, EStructuralFeature, Constraint> oclHelper;

	/** Th OCL to use */
	private OCL ocl;

	/**
	 * Default constructor.
	 * 
	 * @param mainTab
	 *            the main tab.
	 */
	public EMF2GvLaunchConfigFiltersTab(EMF2GvLaunchConfigMainTab mainTab) {
		this.mainTab = mainTab;
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
		createFiltersGroup(comp);
	}

	/**
	 * Creates the group containing the filter options.
	 * 
	 * @param parent
	 *            the parent composite.
	 */
	private void createFiltersGroup(Composite parent) {
		Group group = createGroup(parent, "Filter definitions", 2,
				GridData.FILL_BOTH);

		// Table initialization
		Table table = new Table(group, SWT.FULL_SELECTION | SWT.BORDER
				| SWT.CHECK);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.verticalSpan = 3;
		gd.heightHint = 150;
		gd.grabExcessHorizontalSpace = true;
		gd.grabExcessVerticalSpace = true;
		table.setLayoutData(gd);

		// Table viewer initialization
		eClassesTableViewer = new CheckboxTableViewer(table);
		eClassesTableViewer.setContentProvider(new TableContentProvider());
		eClassesTableViewer.setLabelProvider(new TableLabelProvider(
				adapterFactoryLabelProvider));
		eClassesTableViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						updateButtonsEnablement();
					}
				});
		eClassesTableViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				handleEditButton();
			}
		});
		eClassesTableViewer.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				OCLConstraint oCLConstraint = (OCLConstraint) event.getElement();
				if (oCLConstraint.enabled != event.getChecked()) {
					oCLConstraint.enabled = event.getChecked();
					updateLaunchConfigurationDialog();
				}
			}
		});

		// Columns configuration
		TableColumn eClassColumn = new TableColumn(table, SWT.LEFT);
		eClassColumn.setText("Filtered EClass");
		eClassColumn.setWidth(150);
		TableColumn expressionColumn = new TableColumn(table, SWT.LEFT);
		expressionColumn
				.setText("Draw if the following OCL condition is checked");
		expressionColumn.setWidth(290);

		// Buttons creation
		newButton = createButton(group, "New...", new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleNewButton();
			}
		});
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.verticalAlignment = GridData.BEGINNING;
		gd.grabExcessHorizontalSpace = false;
		gd.grabExcessVerticalSpace = false;
		newButton.setLayoutData(gd);
		editButton = createButton(group, "Edit...", new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleEditButton();
			}
		});
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.verticalAlignment = GridData.BEGINNING;
		gd.grabExcessHorizontalSpace = false;
		gd.grabExcessVerticalSpace = false;
		editButton.setLayoutData(gd);
		removeButton = createButton(group, "Remove", new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleRemoveButton();
			}
		});
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.verticalAlignment = GridData.BEGINNING;
		gd.grabExcessHorizontalSpace = false;
		gd.grabExcessVerticalSpace = false;
		removeButton.setLayoutData(gd);
		updateButtonsEnablement();

		// Table viewer model initialization
		eClassesTableViewer.setInput(oCLConstraints);
	}

	/**
	 * Updates the enablements of the buttons.
	 */
	private void updateButtonsEnablement() {
		boolean selectionIsEmpty = eClassesTableViewer.getSelection().isEmpty();
		newButton.setEnabled(true);
		editButton.setEnabled(!selectionIsEmpty);
		removeButton.setEnabled(!selectionIsEmpty);
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
		boolean atLeastOneExpressionIsChecked = eClassesTableViewer
				.getCheckedElements().length > 0;
		if (atLeastOneExpressionIsChecked) {
			if (mainTab.isInGraphicalDescriptionGenerationMode()) {
				isValid = false;
				setErrorMessage("OCL Filters are not allowed if no graphical description is selected in the main tab. You must select a graphical description or uncheck your filter oCLConstraints");
			}
			// If the main tab is configured with a graphical description
			if (!mainTab.isInGraphicalDescriptionGenerationMode()) {
				// Load the authorized EClasses
				List<EClass> authorizedEClasses = null;
				if (isValid) {
					try {
						authorizedEClasses = getAuthorizedEClasses();
					} catch (CoreException e) {
						isValid = false;
						setErrorMessage("The graphical description file does not seem to be valid");
					}
				}
				// Check the EClass list
				if (isValid) {
					for (OCLConstraint oCLConstraint : oCLConstraints) {
						if (eClassesTableViewer.getChecked(oCLConstraint)) {
							EClass eClass = oCLConstraint.context;
							if (!authorizedEClasses.contains(eClass)) {
								isValid = false;
								setErrorMessage("The EClass '"
										+ eClass.getName()
										+ "' is not used in the graphical description");
								break;
							}
						}
					}
				}
				// Check the oCLConstraints
				if (isValid) {
					for (OCLConstraint oCLConstraint : oCLConstraints) {
						if (eClassesTableViewer.getChecked(oCLConstraint)) {
							if (oCLConstraint.parsed == null) {
								getOCLHelper().setContext(oCLConstraint.context);
								try {
									Constraint parsed = getOCLHelper()
											.createInvariant(oCLConstraint.value);
									oCLConstraint.parsed = parsed;
								} catch (ParserException e) {
									isValid = false;
									setErrorMessage(oCLConstraint.context
											.getName()
											+ " : Invalid value ("
											+ e.getMessage() + ")");
									break;
								}
							}
						}
					}
				}
			}
		}
		return isValid;
	}

	/**
	 * @return the OCL to use.
	 */
	private OCL getOcl() {
		if (ocl == null) {
			ocl = OCLProvider.newOCL();
		}
		return ocl;
	}
	
	/**
	 * @return an OCL Helper instance.
	 */
	private OCLHelper<EClassifier, EOperation, EStructuralFeature, Constraint> getOCLHelper() {
		if (oclHelper == null) {
			oclHelper = getOcl().createOCLHelper();
		}
		return oclHelper;
	}

	/**
	 * Handles a click on the 'remove' button.
	 */
	private void handleRemoveButton() {
		SafeRunner.run(new SafeRunnable() {
			public void run() throws Exception {
				IStructuredSelection selection = (IStructuredSelection) eClassesTableViewer
						.getSelection();
				if (!selection.isEmpty()) {
					OCLConstraint oCLConstraint = (OCLConstraint) selection
							.getFirstElement();
					if (MessageDialog.openConfirm(PlatformUI.getWorkbench()
							.getDisplay().getActiveShell(), "Confirmation",
							"Are you sure you want to remove this value ?")) {
						oCLConstraints.remove(oCLConstraint);
						eClassesTableViewer.refresh();
						updateLaunchConfigurationDialog();
					}
				}
			}
		});
	}

	/**
	 * Handles a click on the 'edit' button.
	 */
	private void handleEditButton() {
		SafeRunner.run(new SafeRunnable() {
			public void run() throws Exception {
				IStructuredSelection selection = (IStructuredSelection) eClassesTableViewer
						.getSelection();
				if (!selection.isEmpty()) {
					OCLConstraint oCLConstraint = (OCLConstraint) selection
							.getFirstElement();
					String oldExpression = oCLConstraint.value;
					OCLInputDialog inputDialog = new OCLInputDialog(PlatformUI
							.getWorkbench().getDisplay().getActiveShell(),
							"Edit a boolean OCL value editor", getOcl(),
							oCLConstraint.context, oldExpression,
							EcorePackage.eINSTANCE.getEBoolean(), false);
					if (inputDialog.open() == Window.OK) {
						String newExpression = inputDialog.getValue();
						if (!newExpression.equals(oldExpression)) {
							oCLConstraint.parsed = null;
							oCLConstraint.value = newExpression;
							eClassesTableViewer.refresh();
							eClassesTableViewer
									.setSelection(new StructuredSelection(
											oCLConstraint));
							updateLaunchConfigurationDialog();
						}
					}
				}
			}
		});
	}

	/**
	 * Handles a click on the 'new' button.
	 */
	private void handleNewButton() {
		SafeRunner.run(new SafeRunnable() {
			public void run() throws Exception {
				if (mainTab.isInGraphicalDescriptionGenerationMode()) {
					MessageDialog
							.openInformation(
									PlatformUI.getWorkbench().getDisplay()
											.getActiveShell(),
									"Warning",
									"OCL Filtering is available only when a graphical description is selected. Please select a graphical description in the main tab.");
				} else {
					// EClass hierarchy retrieval
					List<EClass> eClasses = getAuthorizedEClasses();
					final LabelProvider labelProvider = new LabelProvider() {
						@Override
						public String getText(Object element) {
							return adapterFactoryLabelProvider.getText(element);
						}

						@Override
						public Image getImage(Object element) {
							return adapterFactoryLabelProvider
									.getImage(element);
						}
					};
					ElementListSelectionDialog dialog = new ElementListSelectionDialog(
							PlatformUI.getWorkbench().getDisplay()
									.getActiveShell(), labelProvider);
					dialog.setElements(eClasses.toArray());
					dialog.setInitialSelections(new Object[] {});
					dialog.setTitle("EClass selection");
					dialog.setMessage("Select an EClass");
					dialog.setMultipleSelection(false);
					int result = dialog.open();
					labelProvider.dispose();
					if (result == Window.OK) {
						OCLConstraint oCLConstraint = new OCLConstraint();
						oCLConstraint.context = (EClass) dialog.getFirstResult();
						OCLInputDialog inputDialog = new OCLInputDialog(
								PlatformUI.getWorkbench().getDisplay()
										.getActiveShell(),
								"New boolean OCL value editor", getOcl(),
								oCLConstraint.context, "true",
								EcorePackage.eINSTANCE.getEBoolean(), true);
						if (inputDialog.open() == Window.OK) {
							oCLConstraint.value = inputDialog.getValue();
							oCLConstraints.add(oCLConstraint);
							eClassesTableViewer.refresh();
							eClassesTableViewer
									.setSelection(new StructuredSelection(
											oCLConstraint));
							eClassesTableViewer.setChecked(oCLConstraint, true);
							updateLaunchConfigurationDialog();
						}
					}
				}
			}
		});
	}

	/**
	 * @return the EClass list that can be used in the oCLConstraints.
	 * @throws CoreException
	 *             thrown if an unexpected error occurs.
	 */
	private List<EClass> getAuthorizedEClasses() throws CoreException {
		GVFigureDescription figureDescription = loadSelectedGraphDesc();
		return GraphdescHelper.getFilterableEClasses(figureDescription);
	}

	/**
	 * Loads the selected graphical description (in the main tab).
	 * 
	 * @return the selected graphical description (in the main tab).
	 * @throws CoreException
	 *             thrown if the graphical description is invalid.
	 */
	private GVFigureDescription loadSelectedGraphDesc() throws CoreException {
		if ("".equals(mainTab.getGraphDescText().getText())) {
			throw new CoreException(
					new Status(IStatus.ERROR, Activator.PLUGIN_ID,
							"A graphical description must be selected at first in the main tab"));
		}
		Path graphDescPath = new Path(mainTab.getGraphDescText().getText());

		// Resource path check
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IResource res = workspace.getRoot().findMember(graphDescPath);
		if (res == null || !res.isAccessible()) {
			throw new CoreException(new Status(IStatus.ERROR,
					Activator.PLUGIN_ID,
					"The graphical description does not exist"));
		}

		// Resource load
		Resource graphDescRes = EMFHelper.loadFileEMFResource(
				new ResourceSetImpl(), graphDescPath, null);
		EList<EObject> contents = graphDescRes.getContents();
		if (contents == null || contents.size() != 1
				|| !(contents.get(0) instanceof GVFigureDescription)) {
			throw new CoreException(new Status(IStatus.ERROR,
					Activator.PLUGIN_ID,
					"The graphical description does not seem to be valid"));
		}
		return (GVFigureDescription) contents.get(0);
	}

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
	 * org.eclipse.debug.ui.ILaunchConfigurationTab#initializeFrom(org.eclipse
	 * .debug.core.ILaunchConfiguration)
	 */
	public void initializeFrom(ILaunchConfiguration configuration) {
		try {
			String[][] cfgExpressions = EMF2GvLaunchConfigHelper
					.getFilterExpressions(configuration);
			oCLConstraints.clear();
			for (int i = 0; i < cfgExpressions.length; i++) {
				String[] cfgExpression = cfgExpressions[i];
				String ePackakeNsUri = cfgExpression[EMF2GvLaunchConfigHelper.FILTER_EXPRESSION_EPACKAGE_IDX];
				String eClassName = cfgExpression[EMF2GvLaunchConfigHelper.FILTER_EXPRESSION_ECLASS_IDX];
				String expressionValue = cfgExpression[EMF2GvLaunchConfigHelper.FILTER_EXPRESSION_VALUE_IDX];
				boolean enabled = "true"
						.equals(cfgExpression[EMF2GvLaunchConfigHelper.FILTER_EXPRESSION_ENABLED_IDX]);
				EPackage ePackage = EPackage.Registry.INSTANCE
						.getEPackage(ePackakeNsUri);
				EClass eClass = (EClass) ePackage.getEClassifier(eClassName);
				OCLConstraint oCLConstraint = new OCLConstraint();
				oCLConstraint.context = eClass;
				oCLConstraint.value = expressionValue;
				oCLConstraint.enabled = enabled;
				oCLConstraints.add(oCLConstraint);
			}
			eClassesTableViewer.refresh();
			for (OCLConstraint oCLConstraint : oCLConstraints) {
				eClassesTableViewer.setChecked(oCLConstraint, oCLConstraint.enabled);
			}
		} catch (CoreException e) {
			Activator.getDefault().logError(
					"Unexpected error while retreiving run configuration", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.debug.ui.ILaunchConfigurationTab#performApply(org.eclipse
	 * .debug.core.ILaunchConfigurationWorkingCopy)
	 */
	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		String[][] cfgExpressions = new String[oCLConstraints.size()][4];
		for (int i = 0; i < cfgExpressions.length; i++) {
			OCLConstraint oCLConstraint = (OCLConstraint) eClassesTableViewer
					.getElementAt(i);
			String[] cfgExpression = cfgExpressions[i];
			cfgExpression[EMF2GvLaunchConfigHelper.FILTER_EXPRESSION_EPACKAGE_IDX] = oCLConstraint.context
					.getEPackage().getNsURI();
			cfgExpression[EMF2GvLaunchConfigHelper.FILTER_EXPRESSION_ECLASS_IDX] = oCLConstraint.context
					.getName();
			cfgExpression[EMF2GvLaunchConfigHelper.FILTER_EXPRESSION_VALUE_IDX] = oCLConstraint.value;
			cfgExpression[EMF2GvLaunchConfigHelper.FILTER_EXPRESSION_ENABLED_IDX] = String
					.valueOf(oCLConstraint.enabled);
		}
		EMF2GvLaunchConfigHelper.setFilterExpressions(configuration,
				cfgExpressions);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getName()
	 */
	public String getName() {
		return "Filters";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.ui.AbstractLaunchConfigurationTab#getImage()
	 */
	@Override
	public Image getImage() {
		return Activator.getDefault().getImage("icons/Filter.gif");
	}

}

/**
 * The table content provider.
 */
class TableContentProvider implements IStructuredContentProvider {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface
	 * .viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java
	 * .lang.Object)
	 */
	public Object[] getElements(Object inputElement) {
		@SuppressWarnings("unchecked")
		List<OCLConstraint> oCLConstraints = (List<OCLConstraint>) inputElement;
		OCLConstraint[] expressionArray = oCLConstraints
				.toArray(new OCLConstraint[oCLConstraints.size()]);
		Arrays.sort(expressionArray, new Comparator<OCLConstraint>() {
			public int compare(OCLConstraint e1, OCLConstraint e2) {
				return e1.context.getName().compareTo(e2.context.getName());
			}
		});
		return expressionArray;
	}

}

/**
 * The table label provider.
 */
class TableLabelProvider extends LabelProvider implements ITableLabelProvider {

	/** Adapter factory label provider */
	private AdapterFactoryLabelProvider adapterFactoryLabelProvider;

	/**
	 * Default constructor.
	 * 
	 * @param adapterFactoryLabelProvider
	 *            the adapter factory label provider.
	 */
	protected TableLabelProvider(
			AdapterFactoryLabelProvider adapterFactoryLabelProvider) {
		this.adapterFactoryLabelProvider = adapterFactoryLabelProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang
	 * .Object, int)
	 */
	public Image getColumnImage(Object element, int columnIndex) {
		OCLConstraint oCLConstraint = (OCLConstraint) element;
		EClass eClass = oCLConstraint.context;
		return columnIndex == 0 ? adapterFactoryLabelProvider.getImage(eClass)
				: null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang
	 * .Object, int)
	 */
	public String getColumnText(Object element, int columnIndex) {
		OCLConstraint oCLConstraint = (OCLConstraint) element;
		EClass eClass = oCLConstraint.context;
		switch (columnIndex) {
		case 0:
			return adapterFactoryLabelProvider.getText(eClass);
		case 1:
			return oCLConstraint.value;
		default:
			return "Unknown column index : " + columnIndex;
		}
	}
}

/**
 * POJO used for the table elements.
 */
class OCLConstraint {
	EClass context;
	String value;
	Constraint parsed;
	boolean enabled = true;
}
