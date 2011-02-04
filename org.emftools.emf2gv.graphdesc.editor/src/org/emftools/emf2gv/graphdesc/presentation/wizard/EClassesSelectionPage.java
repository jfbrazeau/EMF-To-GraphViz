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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Tree;
import org.emftools.emf2gv.graphdesc.ClassFigure;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.graphdesc.GraphdescFactory;

/**
 * EClass selction page.
 * 
 * @author jbrazeau
 * 
 */
public class EClassesSelectionPage extends AbstractGraphdescWizardPage {

	/** EClasses table viewer */
	private CheckboxTreeViewer eClassesTreeViewer;

	/** EClass content provider */
	private EClassesContentProvider contentProvider;

	/**
	 * Default constructor.
	 * 
	 * @param pageName
	 *            the page name.
	 * @param gvFigureDescription
	 *            the figure description model.
	 */
	protected EClassesSelectionPage(String pageName,
			GVFigureDescription gvFigureDescription) {
		super(pageName, gvFigureDescription);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		Composite rootContainer = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		rootContainer.setLayout(layout);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData.verticalAlignment = SWT.TOP;
		rootContainer.setLayoutData(gridData);

		createEClassesSelectionGroup(rootContainer);

		setControl(rootContainer);
		entryChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.emftools.emf2gv.graphdesc.presentation.wizard.AbstractGraphdescWizardPage
	 * #initPageContent()
	 */
	@Override
	protected void initPageContent() {
		// Let's retrieve the EPackage list and clean the graphical
		// definition
		GVFigureDescription gvFigureDescription = getGvFigureDescription();
		List<EPackage> ePackages = gvFigureDescription.getEPackages();
		List<ClassFigure> classFigures = gvFigureDescription.getClassFigures();
		List<ClassFigure> classFiguresToRemove = new ArrayList<ClassFigure>();
		for (ClassFigure classFigure : classFigures) {
			if (!ePackages.contains(classFigure.getEPackage())) {
				classFiguresToRemove.add(classFigure);
			}
		}
		classFigures.removeAll(classFiguresToRemove);

		// Update viewer input
		eClassesTreeViewer.setInput(ePackages);
		eClassesTreeViewer.expandAll();

		// Update checked status
		for (ClassFigure classFigure : classFigures) {
			eClassesTreeViewer.setChecked(classFigure.getEClass(), true);
		}

		// Make ePackages checkboxes grayed
		for (EPackage ePackage : ePackages) {
			eClassesTreeViewer.setGrayChecked(ePackage, true);
		}
	}

	/**
	 * Creates the EClass selection group.
	 * 
	 * @param rootContainer
	 *            teh root container.
	 */
	private void createEClassesSelectionGroup(Composite rootContainer) {
		// EClass list group
		Group eClassesListGroup = createGroup(rootContainer,
				"EClasses selection", 2, true);
		// EClass table initialization
		Tree eClassesTree = new Tree(eClassesListGroup, SWT.CHECK | SWT.BORDER);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData.verticalSpan = 2;
		eClassesTree.setLayoutData(gridData);
		eClassesTreeViewer = new CheckboxTreeViewer(eClassesTree);
		contentProvider = new EClassesContentProvider();
		eClassesTreeViewer.setContentProvider(contentProvider);
		eClassesTreeViewer.setLabelProvider(new AdapterFactoryLabelProvider(
				getEcoreAdapterFactory()));
		eClassesTreeViewer.setSorter(new ViewerSorter() {
			public int compare(Viewer viewer, Object e1, Object e2) {
				if (e1 instanceof ENamedElement && e2 instanceof ENamedElement) {
					return ((ENamedElement) e1)
							.getName()
							.toLowerCase()
							.compareTo(
									((ENamedElement) e2).getName()
											.toLowerCase());
				}

				return super.compare(viewer, e1, e2);
			}
		});
		eClassesTreeViewer.addCheckStateListener(new ICheckStateListener() {
			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				handleTreeItemCheckedStateChanged(event.getElement(),
						event.getChecked());
			}
		});

		Button selectAllRadio = new Button(eClassesListGroup, SWT.NONE);
		selectAllRadio.setText("Select all");
		gridData = new GridData(SWT.FILL, SWT.NONE, false, false);
		selectAllRadio.setLayoutData(gridData);
		selectAllRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleSetAllCheckedState(true);
			}
		});

		Button deselectAllRadio = new Button(eClassesListGroup, SWT.NONE);
		deselectAllRadio.setText("Deselect all");
		gridData = new GridData(SWT.FILL, SWT.NONE, false, false);
		deselectAllRadio.setLayoutData(gridData);
		deselectAllRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleSetAllCheckedState(false);
			}
		});
	}

	/**
	 * Handles a click on an EClass state checkbox.
	 * 
	 * @param newCheckState
	 *            the new check state.
	 */
	private void handleSetAllCheckedState(boolean newCheckState) {
		boolean atLeastOneChange = false;
		for (EPackage ePackage : getGvFigureDescription().getEPackages()) {
			Object[] childElements = contentProvider.getChildren(ePackage);
			for (Object childElement : childElements) {
				boolean actualCheckedStatus = eClassesTreeViewer
						.getChecked(childElement);
				if (actualCheckedStatus != newCheckState) {
					eClassesTreeViewer.setChecked(childElement, newCheckState);
					handleEClassCheckedStateChanged((EClass) childElement,
							newCheckState);
					atLeastOneChange = true;
				}
			}
		}
		if (atLeastOneChange)
			entryChanged();
	}

	/**
	 * Handles a status change event on a tree item.
	 * 
	 * @param element
	 *            the element behind the viewer.
	 * @param checked
	 *            the check status.
	 */
	private void handleTreeItemCheckedStateChanged(Object element,
			boolean checked) {
		// EPackage case
		if (element instanceof EPackage) {
			if (!checked) {
				eClassesTreeViewer.setChecked(element, true);
			}
		}
		// EClass case
		else {
			EClass eClass = (EClass) element;
			handleEClassCheckedStateChanged(eClass, checked);
			entryChanged();
		}
	}

	/**
	 * Handles a status change event on an EClass tree item.
	 * 
	 * @param eClass
	 *            the EClass.
	 * @param checked
	 *            the check status.
	 */
	private void handleEClassCheckedStateChanged(EClass eClass, boolean checked) {
		// ClassFigure add
		if (checked) {
			ClassFigure classFigure = GraphdescFactory.eINSTANCE
					.createClassFigure();
			classFigure.setEClass(eClass);
			getGvFigureDescription().getClassFigures().add(classFigure);
		}
		// ClassFigure removal
		else {
			ClassFigure classFigure = getGvFigureDescription().getClassFigure(
					eClass);
			getGvFigureDescription().getClassFigures().remove(classFigure);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.emftools.emf2gv.graphdesc.presentation.GraphDescModelWizardPage#
	 * validatePage()
	 */
	protected boolean validatePage() {
		Object[] checkedElements = eClassesTreeViewer.getCheckedElements();
		List<EClass> checkedEClasses = new ArrayList<EClass>();
		if (checkedElements != null) {
			for (Object checkedElement : checkedElements) {
				if (checkedElement instanceof EClass) {
					checkedEClasses.add((EClass) checkedElement);
				}
			}
		}
		// Page is valid if at least one EClass is checked
		return (checkedEClasses.size() != 0);
	}

}

/**
 * EClass content provider.
 * 
 * @author jbrazeau
 */
class EClassesContentProvider implements ITreeContentProvider {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ITreeContentProvider#getElements(java.lang.
	 * Object)
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		return ((List<?>) inputElement).toArray();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.
	 * Object)
	 */
	@Override
	public Object[] getChildren(Object parentElement) {
		Object[] result = null;
		if (parentElement instanceof EPackage) {
			EPackage ePackage = (EPackage) parentElement;
			List<EClass> eClasses = new ArrayList<EClass>();
			List<EClassifier> eCLassifiers = ePackage.getEClassifiers();
			for (EClassifier eClassifier : eCLassifiers) {
				if (eClassifier instanceof EClass
						&& !((EClass) eClassifier).isAbstract()) {
					eClasses.add((EClass) eClassifier);
				}
			}
			result = eClasses.toArray();
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object
	 * )
	 */
	@Override
	public Object getParent(Object element) {
		Object parent = null;
		if (element instanceof EClass) {
			EClass eClass = (EClass) element;
			parent = eClass.getEPackage();
		}
		return parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.
	 * Object)
	 */
	@Override
	public boolean hasChildren(Object element) {
		return (element instanceof EPackage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface
	 * .viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	@Override
	public void dispose() {
	}

}
