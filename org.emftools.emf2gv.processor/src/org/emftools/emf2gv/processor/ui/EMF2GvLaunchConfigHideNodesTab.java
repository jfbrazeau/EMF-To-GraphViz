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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
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
import org.emftools.emf2gv.graphdesc.ClassFigure;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.processor.Activator;
import org.emftools.emf2gv.processor.ui.util.MultiLineInputDialog;
import org.emftools.emf2gv.util.EMFHelper;

/**
 * Emf2gv launch configuration tab containing options to show/hide conditions.
 */
public class EMF2GvLaunchConfigHideNodesTab extends
		AbstractEMF2GvLaunchConfigTab {

	/** Adapter factory label provider */
	private AdapterFactoryLabelProvider adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(
			EMFHelper.getEcoreAdapterFactory());

	/**
	 * The main tab reference (that is used to retrieve the graphical
	 * description file)
	 */
	private EMF2GvLaunchConfigMainTab mainTab;

	/** ECLasses table viewer */
	private TableViewer eClassesTableViewer;

	/** Map containing the EClasses and their associated expressions */
	private Map<EClass, String> expressions = new HashMap<EClass, String>();

	/** Map containing the EClasses and their associated parsed expressions */
	private Map<EClass, Constraint> parsedExpressionsCache = new HashMap<EClass, Constraint>();

	/** Button allowing to add a new expression */
	private Button newButton;

	/** Button allowing to edit an expression */
	private Button editButton;

	/** Button allowing to remove an expression */
	private Button removeButton;

	/** The OCL Helper instance */
	private OCLHelper<EClassifier, EOperation, EStructuralFeature, Constraint> oclHhelper;

	/**
	 * Default constructor.
	 * 
	 * @param mainTab
	 *            the main tab.
	 */
	public EMF2GvLaunchConfigHideNodesTab(EMF2GvLaunchConfigMainTab mainTab) {
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
		createHideNodesGroup(comp);
	}

	/**
	 * Creates the group allowing to select "Hide" options.
	 * 
	 * @param parent
	 *            the parent composite.
	 */
	private void createHideNodesGroup(Composite parent) {
		Group group = createGroup(parent, "Hide Nodes", 2);

		// Table initialization
		Table table = new Table(group, SWT.FULL_SELECTION | SWT.BORDER);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.verticalSpan = 3;
		gd.heightHint = 300;
		gd.grabExcessHorizontalSpace = true;
		gd.grabExcessVerticalSpace = true;
		table.setLayoutData(gd);

		// Table viewer initialization
		eClassesTableViewer = new TableViewer(table);
		eClassesTableViewer.setContentProvider(new TableContentProvider());
		eClassesTableViewer.setLabelProvider(new TableLabelProvider(
				expressions, adapterFactoryLabelProvider));
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

		// Columns configuration
		TableColumn eClassColumn = new TableColumn(table, SWT.LEFT);
		eClassColumn.setText("EClass");
		eClassColumn.setWidth(150);
		TableColumn expressionColumn = new TableColumn(table, SWT.LEFT);
		expressionColumn.setText("Boolean expression");
		expressionColumn.setWidth(290);
		eClassesTableViewer.setColumnProperties(new String[] { "EClass",
				"Expression" });

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
		eClassesTableViewer.setInput(expressions);

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
		if (expressions.size() != 0
				&& "".equals(mainTab.getGraphDescText().getText())) {
			isValid = false;
			setErrorMessage("To enables some expressions, you must select a graphical description at first");
		}
		// Load the authorized EClasses
		List<EClass> authorizedEClasses = null;
		if (isValid) {
			try {
				authorizedEClasses = getAuthorizedEClasses();
			}
			catch (CoreException e) {
				isValid = false;
				setErrorMessage("The graphical description file does not seem to be valid");
			}
		}
		// Check the EClass list
		if (isValid) {
			for (EClass eClass : expressions.keySet()) {
				if (!authorizedEClasses.contains(eClass)) {
					isValid = false;
					setErrorMessage("The EClass '" + eClass.getName()
							+ "' is not used in the graphical description");
					break;
				}
			}
		}
		// Check the expressions
		if (isValid) {
			for (EClass eClass : expressions.keySet()) {
				if (parsedExpressionsCache.get(eClass) == null) {
					String expression = expressions.get(eClass);
					getOCLHelper().setContext(eClass);
					try {
						Constraint parsed = getOCLHelper().createInvariant(
								expression);
						parsedExpressionsCache.put(eClass, parsed);
					} catch (ParserException e) {
						isValid = false;
						setErrorMessage(eClass.getName()
								+ " : Invalid expression (" + e.getMessage()
								+ ")");
						break;
					}
				}
			}
		}
		return isValid;
	}

	/**
	 * @return an OCL Helper instance.
	 */
	private OCLHelper<EClassifier, EOperation, EStructuralFeature, Constraint> getOCLHelper() {
		if (oclHhelper == null) {
			OCL ocl = OCL.newInstance();
			oclHhelper = ocl.createOCLHelper();
		}
		return oclHhelper;
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
					EClass eClass = (EClass) selection.getFirstElement();
					if (MessageDialog
							.openConfirm(PlatformUI.getWorkbench().getDisplay()
									.getActiveShell(), "Confirmation",
									"Are you sure you want to remove this expression ?")) {
						expressions.remove(eClass);
						parsedExpressionsCache.remove(eClass); // Cache cleaning
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
					EClass eClass = (EClass) selection.getFirstElement();
					String oldExpression = expressions.get(eClass);
					MultiLineInputDialog inputDialog = new MultiLineInputDialog(
							PlatformUI.getWorkbench().getDisplay()
									.getActiveShell(),
							"Edit a boolean OCL expression editor",
							oldExpression, false);
					if (inputDialog.open() == Window.OK) {
						String newExpression = inputDialog.getValue();
						if (!newExpression.equals(oldExpression)) {
							expressions.put(eClass, newExpression);
							parsedExpressionsCache.remove(eClass); // Cache
																	// cleaning
							eClassesTableViewer.refresh();
							eClassesTableViewer
									.setSelection(new StructuredSelection(
											eClass));
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
				// EClass hierarchy retrieval
				List<EClass> eClasses = getAuthorizedEClasses();
				// Remove the already selected EClasses
				eClasses.removeAll(expressions.keySet());

				final LabelProvider labelProvider = new LabelProvider() {
					@Override
					public String getText(Object element) {
						return adapterFactoryLabelProvider.getText(element);
					}

					@Override
					public Image getImage(Object element) {
						return adapterFactoryLabelProvider.getImage(element);
					}
				};
				ElementListSelectionDialog dialog = new ElementListSelectionDialog(
						PlatformUI.getWorkbench().getDisplay().getActiveShell(),
						labelProvider);
				dialog.setElements(eClasses.toArray());
				dialog.setInitialSelections(new Object[] {});
				dialog.setTitle("EClass selection");
				dialog.setMessage("Select an EClass");
				dialog.setMultipleSelection(false);
				int result = dialog.open();
				labelProvider.dispose();
				if (result == Window.OK) {
					EClass eClass = (EClass) dialog.getFirstResult();
					MultiLineInputDialog inputDialog = new MultiLineInputDialog(
							PlatformUI.getWorkbench().getDisplay()
									.getActiveShell(),
							"New boolean OCL expression editor", "true", true);
					if (inputDialog.open() == Window.OK) {
						expressions.put(eClass, inputDialog.getValue());
						eClassesTableViewer.refresh();
						eClassesTableViewer
								.setSelection(new StructuredSelection(eClass));
						updateLaunchConfigurationDialog();
					}
				}
			}
		});
	}

	/**
	 * Registers the EClass super types in the list.
	 * 
	 * @param eClass
	 *            the EClass.
	 * @param eClasses
	 *            the EClass list.
	 */
	private static void registerSuperTypes(EClass eClass, List<EClass> eClasses) {
		if (!eClasses.contains(eClass)) {
			eClasses.add(eClass);
			for (EClass superType : eClass.getESuperTypes()) {
				registerSuperTypes(superType, eClasses);
			}
		}
	}

	/**
	 * @return the EClass list that can be used in the expressions.
	 * @throws CoreException
	 *             thrown if an unexpected error occurs.
	 */
	private List<EClass> getAuthorizedEClasses() throws CoreException {
		// EClass hierarchy retrieval
		List<EClass> eClasses = new ArrayList<EClass>();
		GVFigureDescription figureDescription = loadSelectedGraphDesc();
		EList<ClassFigure> classFigures = figureDescription.getClassFigures();
		for (ClassFigure classFigure : classFigures) {
			registerSuperTypes(classFigure.getEClass(), eClasses);
		}
		return eClasses;
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
		Resource graphDescRes = EMFHelper.loadEMFResource(
				new ResourceSetImpl(), graphDescPath, false, null);
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
					.getHideNodeExpressions(configuration);
			expressions.clear();
			for (int i = 0; i < cfgExpressions.length; i++) {
				String[] cfgExpression = cfgExpressions[i];
				String ePackakeNsUri = cfgExpression[EMF2GvLaunchConfigHelper.HIDE_NODE_EXPRESSION_EPACKAGE_IDX];
				String eClassName = cfgExpression[EMF2GvLaunchConfigHelper.HIDE_NODE_EXPRESSION_ECLASS_IDX];
				String expression = cfgExpression[EMF2GvLaunchConfigHelper.HIDE_NODE_EXPRESSION_VALUE_IDX];
				EPackage ePackage = EPackage.Registry.INSTANCE
						.getEPackage(ePackakeNsUri);
				EClass eClass = (EClass) ePackage.getEClassifier(eClassName);
				expressions.put(eClass, expression);
			}
			eClassesTableViewer.refresh();
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
		String[][] cfgExpressions = new String[expressions.size()][3];
		for (int i = 0; i < cfgExpressions.length; i++) {
			EClass eClass = (EClass) eClassesTableViewer.getElementAt(i);
			String[] cfgExpression = cfgExpressions[i];
			cfgExpression[EMF2GvLaunchConfigHelper.HIDE_NODE_EXPRESSION_EPACKAGE_IDX] = eClass
					.getEPackage().getNsURI();
			cfgExpression[EMF2GvLaunchConfigHelper.HIDE_NODE_EXPRESSION_ECLASS_IDX] = eClass
					.getName();
			cfgExpression[EMF2GvLaunchConfigHelper.HIDE_NODE_EXPRESSION_VALUE_IDX] = expressions
					.get(eClass);
		}
		EMF2GvLaunchConfigHelper.setHideNodeExpressions(configuration,
				cfgExpressions);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getName()
	 */
	public String getName() {
		return "Hide Nodes";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.ui.AbstractLaunchConfigurationTab#getImage()
	 */
	@Override
	public Image getImage() {
		return Activator.getDefault().getImage("icons/HiddenNode.gif");
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
		Map<EClass, String> expressions = (Map<EClass, String>) inputElement;
		Set<EClass> eClassesSet = expressions.keySet();
		EClass[] eClassesArray = eClassesSet.toArray(new EClass[eClassesSet
				.size()]);
		Arrays.sort(eClassesArray, new Comparator<EClass>() {
			public int compare(EClass e1, EClass e2) {
				return e1.getName().compareTo(e2.getName());
			}
		});
		return eClassesArray;
	}

}

/**
 * The table label provider.
 */
class TableLabelProvider extends LabelProvider implements ITableLabelProvider {

	/** Adapter factory label provider */
	private AdapterFactoryLabelProvider adapterFactoryLabelProvider;

	/** Map containing the EClass and their associated expressions */
	private Map<EClass, String> expressions = new HashMap<EClass, String>();

	/**
	 * Default constructor.
	 * 
	 * @param expressions
	 *            a map containing the EClass and their associated expressions.
	 * @param adapterFactoryLabelProvider
	 *            the adapter factory label provider.
	 */
	protected TableLabelProvider(Map<EClass, String> expressions,
			AdapterFactoryLabelProvider adapterFactoryLabelProvider) {
		this.expressions = expressions;
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
		EClass eClass = (EClass) element;
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
		EClass eClass = (EClass) element;
		String expression = expressions.get(eClass);
		switch (columnIndex) {
		case 0:
			return adapterFactoryLabelProvider.getText(eClass);
		case 1:
			return expression;
		default:
			return "Unknown column index : " + columnIndex;
		}
	}
}
