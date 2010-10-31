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

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.util.EMFHelper;

public abstract class AbstractGraphdescWizardPage extends WizardPage {

	private GVFigureDescription gvFigureDescription;

	private AdapterFactory adapterFactory;

	/**
	 * Default constructor.
	 * @param pageName the page name.
	 * @param gvFigureDescription the figure description model.
	 */
	protected AbstractGraphdescWizardPage(String pageName, GVFigureDescription gvFigureDescription) {
		super(pageName);
		this.gvFigureDescription = gvFigureDescription;
		this.adapterFactory = EMFHelper.getEcoreAdapterFactory();
	}

	protected Group createGroup(Composite parent, String name, int columnNb, boolean fillVertical) {
		Group group = new Group(parent, SWT.NONE);
		group.setLayoutData(new GridData(SWT.FILL, fillVertical ? SWT.FILL : SWT.NONE, true, fillVertical));
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

	
	protected GVFigureDescription getGvFigureDescription() {
		return gvFigureDescription;
	}

	protected AdapterFactory getEcoreAdapterFactory() {
		return adapterFactory;
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			initPageContent();
			validatePage();
		}
	}
	
	protected abstract void initPageContent();

}

