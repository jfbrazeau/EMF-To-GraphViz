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
package org.emftools.emf2gv.graphdesc.presentation.wizard;

import java.net.URL;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.provider.EcoreEditPlugin;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.util.EMFHelper;
import org.emftools.emf2gv.util.EPackageFake;

/**
 * The EPackage selection page.
 * 
 * @author jbrazeau
 */
public class EPackagesSelectionPage extends AbstractGraphdescWizardPage {

	/** EPackage table viewer */
	private CheckboxTableViewer ePackageTableViewer;

	/** Workspace registered ePackage list */
	private List<EPackage> registeredEPackages;

	/**
	 * Default constructor.
	 * 
	 * @param pageName
	 *            the page name.
	 * @param gvFigureDescription
	 *            the figure description model.
	 */
	protected EPackagesSelectionPage(String pageName,
			GVFigureDescription gvFigureDescription) {
		super(pageName, gvFigureDescription);

		// Workspace EPackages retrieval
		registeredEPackages = EMFHelper.getRegisteredEPackages();
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

		createEPackageSelectionGroup(rootContainer);

		setControl(rootContainer);
		entryChanged();
	}

	/**
	 * Creates the EPackage selection group.
	 * 
	 * @param rootContainer
	 *            the root container.
	 */
	private void createEPackageSelectionGroup(Composite rootContainer) {
		// EPackage list group
		Group ePackageListGroup = createGroup(rootContainer,
				"EPackage selection", 1, true);
		// EPackage table initialization
		Table ePackageTable = new Table(ePackageListGroup, SWT.CHECK
				| SWT.BORDER);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		// gridData.heightHint = 350;
		ePackageTable.setLayoutData(gridData);
		ePackageTableViewer = new CheckboxTableViewer(ePackageTable);
		ePackageTableViewer.setContentProvider(new EPackagesContentProvider());
		ePackageTableViewer.setLabelProvider(new EPackageLabelProvider());
		ePackageTableViewer.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				handleEPackageCheckedStateChanged(
						(EPackage) event.getElement(), event.getChecked());
			}
		});

	}

	/**
	 * Handles a status change event on an EPackage tree item.
	 * 
	 * @param ePackage
	 *            the EPackage.
	 * @param checked
	 *            the check status.
	 */
	private void handleEPackageCheckedStateChanged(EPackage ePackage,
			boolean checked) {
		if (checked) {
			// If the EPackage has not been loaded, we meet an EPackage fake
			// that we have to replace by the resolved EPackage
			if (ePackage instanceof EPackageFake) {
				int idx = registeredEPackages.indexOf(ePackage);
				ePackage = EMFHelper.resolve((EPackageFake) ePackage);
				// Substitution in the list + tableViewer refresh
				registeredEPackages.set(idx, ePackage);
				ePackageTableViewer.refresh();
				ePackageTableViewer.setChecked(ePackage, true);
			}
			// Then we can add the EPackage to the graphical definition
			getGvFigureDescription().getEPackages().add(ePackage);

		} else {
			// If the EPackage is unchecked, we remove it from the
			// graphical definition
			getGvFigureDescription().getEPackages().remove(ePackage);
		}
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
		ePackageTableViewer.setInput(registeredEPackages);
		List<EPackage> selectedEPackages = getGvFigureDescription()
				.getEPackages();
		for (EPackage ePackage : selectedEPackages) {
			ePackageTableViewer.setChecked(ePackage, true);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.emftools.emf2gv.graphdesc.presentation.GraphDescModelWizardPage#
	 * validatePage()
	 */
	protected boolean validatePage() {
		Object[] checkedElements = ePackageTableViewer.getCheckedElements();
		// Page is valid if at least one EPackage has been chosen or
		return (checkedElements != null && checkedElements.length != 0);
	}

}

/**
 * EPackage content provider.
 * 
 * @author jbrazeau
 */
class EPackagesContentProvider implements IStructuredContentProvider {

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
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
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

}

/**
 * EPackage label provider.
 */
class EPackageLabelProvider extends LabelProvider {

	/**
	 * EPackage icon.
	 */
	private Image labelIcon;

	/**
	 * Default constructor.
	 */
	EPackageLabelProvider() {
		// EPackage icon load
		URL iconUrl = (URL) EcoreEditPlugin.INSTANCE
				.getImage("full/obj16/EPackage");
		if (iconUrl != null) {
			this.labelIcon = ImageDescriptor.createFromURL(iconUrl)
					.createImage();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		return ((EPackage) element).getNsURI();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
	 */
	@Override
	public Image getImage(Object element) {
		return labelIcon;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.BaseLabelProvider#dispose()
	 */
	@Override
	public void dispose() {
		super.dispose();
		if (labelIcon != null) {
			labelIcon.dispose();
		}
	}

}