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
package org.emftools.emf2gv.processor.ui.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.emftools.emf2gv.util.EMFHelper;

/**
 * Dialog allowing to select an EMF resource content element.
 */
public class EMFModelElementSectionDialog extends Dialog {

	/** The EMF resource */
	private Resource emfResource;

	/** The default selected EObject */
	private EObject defaultSelection;
	
	/** The adapter factory */
	private AdapterFactory adapterFactory;
	
	/** The tree viewer */
	private TreeViewer viewer;
	
	/** The selected EObject */
	private EObject selection;

	/**
	 * Default constructor.
	 * 
	 * @param parentShell
	 *            the parent shell.
	 * @param emfResource
	 *            the EMF resource.
	 * @param defaultSelectedEObject
	 *            the default selected EObject.
	 */
	public EMFModelElementSectionDialog(Shell parentShell,
			Resource emfResource, EObject defaultSelectedEObject) {
		super(parentShell);
		this.emfResource = emfResource;
		this.defaultSelection = defaultSelectedEObject;
		List<EPackage> ePackages = getResourcePackages(emfResource);
		adapterFactory = EMFHelper.getAdapterFactory(ePackages);
	}

	/**
	 * Retrieves the EPackage list referenced by the EMF resource contents. 
	 * @param emfResource the EMF resource.
	 * @return the EPackage list.
	 */
	private static List<EPackage> getResourcePackages(Resource emfResource) {
		// EPackage list extraction
		ResourceSet rs = emfResource.getResourceSet();
		EList<Resource> resources = rs.getResources();
		List<EPackage> ePackages = new ArrayList<EPackage>();
		for (Resource resource : resources) {
			TreeIterator<EObject> it = resource.getAllContents();
			while (it.hasNext()) {
				EObject eObject = it.next();
				EPackage ePackage = eObject.eClass().getEPackage();
				if (!ePackages.contains(ePackage))
					ePackages.add(ePackage);
			}
		}
		return ePackages;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		GridData gd = ((GridData) composite.getLayoutData());
		gd.minimumHeight = 300;
		gd.minimumWidth = 300;

		Font dialogFont = parent.getFont();
		composite.setFont(dialogFont);
		Tree tree = new Tree(composite, SWT.BORDER);
		tree.setLayoutData(new GridData(GridData.FILL_BOTH));

		viewer = new TreeViewer(tree);
		viewer.setContentProvider(new AdapterFactoryContentProvider(
				adapterFactory));
		viewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		// new AdapterFactoryTreeEditor(viewer.getTree(), adapterFactory);
		viewer.setInput(emfResource);
		viewer.expandToLevel(2);
		if (defaultSelection != null) {
			viewer.setSelection(new TreeSelection(new TreePath(
					new Object[] { defaultSelection })), true);
		}
		// Double click listener
		viewer.getTree().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				okPressed();
			}
		});
		return composite;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {
		ISelection selection = viewer.getSelection();
		if (!selection.isEmpty()) {
			TreeSelection treeSelection = (TreeSelection) selection;
			this.selection = (EObject) treeSelection.getFirstElement();
		}
		super.okPressed();
	}

	/**
	 * @return the selected EObject.
	 */
	public EObject getSelection() {
		return selection;
	}

}
