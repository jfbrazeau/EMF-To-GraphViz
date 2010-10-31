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
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
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
import org.emftools.emf2gv.graphdesc.ReferenceFigure;

public class EReferencesSelectionPage extends AbstractGraphdescWizardPage {

	/** EClasses table viewer */
	private CheckboxTreeViewer eReferencesTreeViewer;
	private EReferencesContentProvider contentProvider;

	/**
	 * Default constructor.
	 * 
	 * @param pageName
	 *            the page name.
	 * @param gvFigureDescription
	 *            the figure description model.
	 */
	protected EReferencesSelectionPage(String pageName,
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

		createEReferencesSelectionGroup(rootContainer);

		setControl(rootContainer);
		entryChanged();
	}

	@Override
	protected void initPageContent() {
		GVFigureDescription gvFigureDescription = getGvFigureDescription();
		List<EPackage> ePackages = gvFigureDescription.getEPackages();

		// Update viewer input
		eReferencesTreeViewer.setInput(ePackages);
		eReferencesTreeViewer.expandAll();

		// Update checked status
		for (ClassFigure classFigure : gvFigureDescription.getClassFigures()) {
			for (ReferenceFigure referenceFigure : classFigure.getReferenceFigures()) {
				eReferencesTreeViewer.setChecked(referenceFigure.getEReference(), true);
			}
		}

		// Make ePackages checkboxes grayed
		for (EPackage ePackage : ePackages) {
			eReferencesTreeViewer.setGrayChecked(ePackage, true);
		}

		// Make EClasses checkboxes grayed
		for (ClassFigure classFigure : gvFigureDescription.getClassFigures()) {
			eReferencesTreeViewer.setGrayChecked(classFigure.getEClass(), true);
		}

	}

	private void createEReferencesSelectionGroup(Composite rootContainer) {
		// EClass list group
		Group eClassesListGroup = createGroup(rootContainer,
				"EClasses selection", 2, true);
		// EClass table initialization
		Tree eClassesTree = new Tree(eClassesListGroup, SWT.CHECK | SWT.BORDER);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData.verticalSpan = 2;
		eClassesTree.setLayoutData(gridData);
		eReferencesTreeViewer = new CheckboxTreeViewer(eClassesTree);
		contentProvider = new EReferencesContentProvider(
				getGvFigureDescription());
		eReferencesTreeViewer.setContentProvider(contentProvider);
		eReferencesTreeViewer.setLabelProvider(new AdapterFactoryLabelProvider(
				getEcoreAdapterFactory()));
		eReferencesTreeViewer.setSorter(new ViewerSorter() {
            public int compare(Viewer viewer, Object e1, Object e2) {
                if (e1 instanceof ENamedElement && e2 instanceof ENamedElement) {
                    return ((ENamedElement)e1).getName().toLowerCase().compareTo(
                            ((ENamedElement) e2).getName().toLowerCase());
                }

                return super.compare(viewer, e1, e2);
            }
		});
		eReferencesTreeViewer.addCheckStateListener(new ICheckStateListener() {
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

	private void handleSetAllCheckedState(boolean newCheckState) {
		boolean atLeastOneChange = false;
		for (EPackage ePackage : getGvFigureDescription().getEPackages()) {
			Object[] childElements = contentProvider.getChildren(ePackage);
			for (Object childElement : childElements) {
				boolean actualCheckedStatus = eReferencesTreeViewer
						.getChecked(childElement);
				if (actualCheckedStatus != newCheckState) {
					eReferencesTreeViewer.setChecked(childElement,
							newCheckState);
					handleEReferenceCheckedStateChanged(
							(EReference) childElement, newCheckState);
					atLeastOneChange = true;
				}
			}
		}
		if (atLeastOneChange)
			entryChanged();
	}

	private void handleTreeItemCheckedStateChanged(Object element,
			boolean checked) {
		// EPackage & EClass case
		if (element instanceof EPackage || element instanceof EClass) {
			if (!checked) {
				eReferencesTreeViewer.setChecked(element, true);
			}
		}
		// EReference case
		else {
			EReference eReference = (EReference) element;
			handleEReferenceCheckedStateChanged(eReference, checked);
			entryChanged();
		}
	}

	private void handleEReferenceCheckedStateChanged(EReference eReference,
			boolean checked) {
		EClass eClass = eReference.getEContainingClass();
		ClassFigure classFigure = getGvFigureDescription().getClassFigure(
				eClass);
		// ClassFigure add
		if (checked) {
			ReferenceFigure referenceFigure = GraphdescFactory.eINSTANCE
					.createReferenceFigure();
			referenceFigure.setEReference(eReference);
			classFigure.getReferenceFigures().add(referenceFigure);
		}
		// ClassFigure removal
		else {
			ReferenceFigure referenceFigure = classFigure
					.getReferenceFigure(eReference);
			classFigure.getReferenceFigures().remove(referenceFigure);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.emftools.emf2gv.graphdesc.presentation.GraphDescModelWizardPage#
	 * validatePage()
	 */
	protected boolean validatePage() {
		setErrorMessage(null);
		Object[] checkedElements = eReferencesTreeViewer.getCheckedElements();
		List<EReference> checkedEreferences = new ArrayList<EReference>();
		if (checkedElements != null) {
			for (Object checkedElement : checkedElements) {
				if (checkedElement instanceof EReference) {
					checkedEreferences.add((EReference) checkedElement);
				}
			}
		}
		// Page is valid if all checked reference have a target figure
		// (associated
		// to the target EClass or one of its subclass of the EReference)
		boolean valid = true;
		for (int i = 0; i < checkedEreferences.size() && valid; i++) {
			EReference eReference = checkedEreferences.get(i);
			ClassFigure classFigure = getGvFigureDescription().getClassFigure(
					eReference.getEContainingClass());
			ReferenceFigure referenceFigure = classFigure
					.getReferenceFigure(eReference);
			// Reference is valid if the graphical definition has a figure for
			// the reference's target EClass or one of its subclass
			valid &= referenceFigure.targetClassFigureExists();
			if (!valid) {
				setErrorMessage("The type '"
						+ eReference.getEReferenceType().getName()
						+ "' of the reference '" + eReference.getName()
						+ "' (or one of its subclasses) has not been selected"
						+ " in the EClasses list");
			}
		}
		return valid;
	}

}

class EReferencesContentProvider implements ITreeContentProvider {

	private GVFigureDescription gvFigureDescription;

	EReferencesContentProvider(GVFigureDescription gvFigureDescription) {
		this.gvFigureDescription = gvFigureDescription;
	}

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
			for (ClassFigure classFigure : gvFigureDescription
					.getClassFigures()) {
				if (ePackage == classFigure.getEPackage()) {
					eClasses.add(classFigure.getEClass());
				}
			}
			result = eClasses.toArray();
		} else if (parentElement instanceof EClass) {
			EClass eClass = (EClass) parentElement;
			List<EReference> eReferences = eClass.getEAllReferences();
			result = eReferences.toArray();
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
		} else if (element instanceof EReference) {
			EReference eReference = (EReference) element;
			parent = eReference.getEContainingClass();
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
		return (element instanceof EPackage) || (element instanceof EClass);
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
