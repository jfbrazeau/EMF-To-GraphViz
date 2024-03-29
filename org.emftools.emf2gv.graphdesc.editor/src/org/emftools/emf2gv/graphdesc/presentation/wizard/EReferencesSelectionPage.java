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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Tree;
import org.emftools.emf2gv.graphdesc.AbstractReferenceFigure;
import org.emftools.emf2gv.graphdesc.ClassFigure;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.graphdesc.GraphdescFactory;
import org.emftools.emf2gv.graphdesc.ReferenceFigure;

/**
 * EReference selection page.
 * 
 * @author jbrazeau
 * 
 */
public class EReferencesSelectionPage extends AbstractGraphdescWizardPage {

	/** EReferences table viewer */
	private CheckboxTreeViewer eReferencesTreeViewer;

	/** EReferences content provider */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.emftools.emf2gv.graphdesc.presentation.wizard.AbstractGraphdescWizardPage
	 * #initPageContent()
	 */
	@Override
	protected void initPageContent() {
		GVFigureDescription gvFigureDescription = getGvFigureDescription();
		List<EPackage> ePackages = gvFigureDescription.getEPackages();

		// Update viewer input
		eReferencesTreeViewer.setInput(ePackages);
		eReferencesTreeViewer.expandAll();

		// Update checked status
		for (ClassFigure classFigure : gvFigureDescription.getClassFigures()) {
			EClass eClass = classFigure.getEClass();
			for (AbstractReferenceFigure abstractReferenceFigure : classFigure
					.getReferenceFigures()) {
				ReferenceFigure referenceFigure = (ReferenceFigure) abstractReferenceFigure;
				EReference eReference = referenceFigure.getEReference();
				EReferenceTreeItem eReferenceTreeItem = getEReferenceTreeItem(
						eClass, eReference);
				eReferencesTreeViewer.setChecked(eReferenceTreeItem, true);
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

		// Let's try to validate the page
		entryChanged();
	}

	/**
	 * Returns the EReferenceTreeItem for the given eClass and eReference.
	 * 
	 * @param eClass
	 *            the EClass.
	 * @param eReference
	 *            the EReference.
	 * @return the ERefenceTreeItem for the given eClass and eReference.
	 */
	private EReferenceTreeItem getEReferenceTreeItem(EClass eClass,
			EReference eReference) {
		Object[] eClassChilds = contentProvider.getChildren(eClass);
		for (int i = 0; i < eClassChilds.length; i++) {
			EReferenceTreeItem eReferenceTreeItem = (EReferenceTreeItem) eClassChilds[i];
			if (eReferenceTreeItem.getEReference().equals(eReference)) {
				return eReferenceTreeItem;
			}
		}
		return null;
	}

	/**
	 * Creates the EReference selection group.
	 * @param rootContainer the root container.
	 */
	private void createEReferencesSelectionGroup(Composite rootContainer) {
		// EClass list group
		Group eClassesListGroup = createGroup(rootContainer,
				"EClasses selection", 2, true);
		// EClass table initialization
		Tree eClassesTree = new Tree(eClassesListGroup, SWT.CHECK | SWT.BORDER);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData.verticalSpan = 3;
		eClassesTree.setLayoutData(gridData);
		eReferencesTreeViewer = new CheckboxTreeViewer(eClassesTree);
		contentProvider = new EReferencesContentProvider(
				getGvFigureDescription());
		eReferencesTreeViewer.setContentProvider(contentProvider);
		eReferencesTreeViewer.setLabelProvider(new AdapterFactoryLabelProvider(
				getEcoreAdapterFactory()) {
			@Override
			public Image getImage(Object object) {
				// EReferenceTreeItem must be handle specifically in order to
				// let the adapter factory act as if it was an EReference
				return super
						.getImage(object instanceof EReferenceTreeItem ? ((EReferenceTreeItem) object)
								.getEReference() : object);
			}

			@Override
			public String getText(Object object) {
				// EReferenceTreeItem must be handle specifically in order to
				// let the adapter factory act as if it was an EReference
				return super
						.getText(object instanceof EReferenceTreeItem ? ((EReferenceTreeItem) object)
								.getEReference() : object);
			}
		});
		eReferencesTreeViewer.setSorter(new ViewerSorter() {
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
		eReferencesTreeViewer.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				handleTreeItemCheckedStateChanged(event.getElement(),
						event.getChecked());
			}
		});

		Button selectAllContainmentsRadio = new Button(eClassesListGroup,
				SWT.NONE);
		selectAllContainmentsRadio.setText("Select containments refs");
		gridData = new GridData(SWT.FILL, SWT.NONE, false, false);
		selectAllContainmentsRadio.setLayoutData(gridData);
		selectAllContainmentsRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleSelectAllContainment();
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
	 * Handles a click on the select all containment button.
	 */
	private void handleSelectAllContainment() {
		boolean atLeastOneChange = false;
		for (EPackage ePackage : getGvFigureDescription().getEPackages()) {
			for (Object eClass : contentProvider.getChildren(ePackage)) {
				for (Object child : contentProvider.getChildren(eClass)) {
					EReferenceTreeItem eReferenceTreeItem = (EReferenceTreeItem) child;
					boolean mustBeChecked = eReferenceTreeItem.getEReference()
							.isContainment();
					boolean isActuallyChecked = eReferencesTreeViewer
							.getChecked(eReferenceTreeItem);
					if (mustBeChecked != isActuallyChecked) {
						atLeastOneChange = true;
						eReferencesTreeViewer.setChecked(eReferenceTreeItem,
								mustBeChecked);
						handleEReferenceCheckedStateChanged(eReferenceTreeItem,
								mustBeChecked);
					}
				}
			}
		}
		if (atLeastOneChange)
			entryChanged();
	}

	/**
	 * Handles a click on the select all (or unselect all) button.
	 * @param newCheckState the new check state.
	 */
	private void handleSetAllCheckedState(boolean newCheckState) {
		boolean atLeastOneChange = false;
		for (EPackage ePackage : getGvFigureDescription().getEPackages()) {
			for (Object eClass : contentProvider.getChildren(ePackage)) {
				for (Object child : contentProvider.getChildren(eClass)) {
					EReferenceTreeItem eReferenceTreeItem = (EReferenceTreeItem) child;
					boolean actualCheckedStatus = eReferencesTreeViewer
							.getChecked(eReferenceTreeItem);
					if (actualCheckedStatus != newCheckState) {
						eReferencesTreeViewer.setChecked(eReferenceTreeItem,
								newCheckState);
						handleEReferenceCheckedStateChanged(eReferenceTreeItem,
								newCheckState);
						atLeastOneChange = true;
					}
				}
			}
		}
		if (atLeastOneChange)
			entryChanged();
	}


	/**
	 * Handles a click on a reference checkbox.
	 * @param element the element behind the tree item.
	 * @param checked the check status.
	 */
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
			EReferenceTreeItem eReferenceTreeItem = (EReferenceTreeItem) element;
			handleEReferenceCheckedStateChanged(eReferenceTreeItem, checked);
			entryChanged();
		}
	}

	/**
	 * Handles a click on an EReference checkbox.
	 * 
	 * @param eReferenceTreeItem
	 *            the eReference tree item.
	 * @param checked
	 *            a boolean indicating if the eReference is selected or not.
	 */
	private void handleEReferenceCheckedStateChanged(
			EReferenceTreeItem eReferenceTreeItem, boolean checked) {
		ClassFigure classFigure = getGvFigureDescription().getClassFigure(
				eReferenceTreeItem.getEReferenceOwner());
		// ClassFigure add
		if (checked) {
			ReferenceFigure referenceFigure = GraphdescFactory.eINSTANCE
					.createReferenceFigure();
			referenceFigure.setEReference(eReferenceTreeItem.getEReference());
			classFigure.getReferenceFigures().add(referenceFigure);
		}
		// ClassFigure removal
		else {
			ReferenceFigure referenceFigure = classFigure
					.getReferenceFigure(eReferenceTreeItem.getEReference());
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
		List<EReferenceTreeItem> checkedEReferenceTreeItems = new ArrayList<EReferenceTreeItem>();
		if (checkedElements != null) {
			for (Object checkedElement : checkedElements) {
				if (checkedElement instanceof EReferenceTreeItem) {
					checkedEReferenceTreeItems
							.add((EReferenceTreeItem) checkedElement);
				}
			}
		}
		// Page is valid if all checked reference have a target figure
		// (associated to the target EClass or one of its subclass of 
		// the EReference)
		boolean valid = true;
		for (int i = 0; i < checkedEReferenceTreeItems.size() && valid; i++) {
			EReferenceTreeItem eReferenceTreeItem = checkedEReferenceTreeItems
					.get(i);
			ClassFigure classFigure = getGvFigureDescription().getClassFigure(
					eReferenceTreeItem.getEReferenceOwner());
			ReferenceFigure referenceFigure = classFigure
					.getReferenceFigure(eReferenceTreeItem.getEReference());
			// Reference is valid if the graphical definition has a figure for
			// the reference's target EClass or one of its subclass
			valid &= referenceFigure.targetClassFigureExists();
			if (!valid) {
				EReference eReference = eReferenceTreeItem.getEReference();
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

/**
 * Default content provider used for the tree.
 */
class EReferencesContentProvider implements ITreeContentProvider {

	/** Graphical description edited in the wizard. */
	private GVFigureDescription gvFigureDescription;
	
	/** Child nodes cache */
	private Map<Object, Object[]> childsCache = new HashMap<Object, Object[]>();
	
	/**
	 * Default constructor.
	 * 
	 * @param gvFigureDescription
	 *            the graphical description edited in the wizard.
	 */
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
	public Object[] getChildren(Object parentElement) {
		Object[] result = childsCache.get(parentElement);
		if (result == null) {
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
				List<EReferenceTreeItem> eReferenceTreeItems = new ArrayList<EReferenceTreeItem>();
				for (EReference eReference : eReferences) {
					eReferenceTreeItems.add(new EReferenceTreeItem(eClass,
							eReference));
				}
				result = eReferenceTreeItems.toArray();
			}
			// Cache management
			childsCache.put(parentElement, result);
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
	public Object getParent(Object element) {
		Object parent = null;
		if (element instanceof EClass) {
			EClass eClass = (EClass) element;
			parent = eClass.getEPackage();
		} else if (element instanceof EReferenceTreeItem) {
			EReferenceTreeItem eReferenceTreeItem = (EReferenceTreeItem) element;
			parent = eReferenceTreeItem.getEReferenceOwner();
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
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		if (oldInput != null) {
			childsCache.remove(oldInput);
			@SuppressWarnings("unchecked")
			List<EPackage> ePackages = (List<EPackage>) oldInput; 
			for (EPackage ePackage : ePackages) {
				childsCache.remove(ePackage);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
		childsCache.clear();
	}

}

/**
 * EReference wrapper used in the tree to remember the parent owner of an
 * EReference (which is different form the containing class of the reference
 * when inherited).
 */
class EReferenceTreeItem {

	/** The EReference */
	private EReference eReference;

	/** The EReference owner */
	private EClass eReferenceOwner;

	/**
	 * Default constructor.
	 * 
	 * @param eReferenceOwner
	 *            the EReference owner.
	 * @param eReference
	 *            the EReference.
	 */
	EReferenceTreeItem(EClass eReferenceOwner, EReference eReference) {
		this.eReferenceOwner = eReferenceOwner;
		this.eReference = eReference;
	}

	/**
	 * @return the EReference.
	 */
	public EReference getEReference() {
		return eReference;
	}

	/**
	 * @return the EReference owner.
	 */
	public EClass getEReferenceOwner() {
		return eReferenceOwner;
	}

}