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

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.util.EMFHelper;

/**
 * The abstract base wizard page.
 * 
 * @author jbrazeau
 * 
 */
public abstract class AbstractGraphdescWizardPage extends WizardPage {

	/** The graphical edited description */
	private GVFigureDescription gvFigureDescription;

	/** The adapter factory */
	private AdapterFactory adapterFactory;

	/**
	 * Default constructor.
	 * 
	 * @param pageName
	 *            the page name.
	 * @param gvFigureDescription
	 *            the figure description model.
	 */
	protected AbstractGraphdescWizardPage(String pageName,
			GVFigureDescription gvFigureDescription) {
		super(pageName);
		this.gvFigureDescription = gvFigureDescription;
		this.adapterFactory = EMFHelper.getEcoreAdapterFactory();
	}

	/**
	 * Creates a group.
	 * 
	 * @param parent
	 *            the parent composite.
	 * @param name
	 *            the group's name.
	 * @param columnNb
	 *            the colums count.
	 * @param fillVertical
	 *            a boolean indicating if the group must fill the available
	 *            vertical space.
	 * @return the group.
	 */
	protected Group createGroup(Composite parent, String name, int columnNb,
			boolean fillVertical) {
		Group group = new Group(parent, SWT.NONE);
		group.setLayoutData(new GridData(SWT.FILL, fillVertical ? SWT.FILL
				: SWT.NONE, true, fillVertical));
		group.setLayout(new GridLayout(columnNb, false));
		group.setText(name);
		return group;
	}

	/**
	 * To be invoked when the page entry has changed.
	 */
	protected void entryChanged() {
		setPageComplete(validatePage());
	}

	/**
	 * @return a boolean indicating if the entry is valid.
	 */
	protected abstract boolean validatePage();

	/**
	 * @return the graphical description.
	 */
	protected GVFigureDescription getGvFigureDescription() {
		return gvFigureDescription;
	}

	/**
	 * @return the ECore adapter factory.
	 */
	protected AdapterFactory getEcoreAdapterFactory() {
		return adapterFactory;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
	 */
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			initPageContent();
			validatePage();
		}
	}

	/**
	 * Initializes the page content.
	 */
	protected abstract void initPageContent();

}
