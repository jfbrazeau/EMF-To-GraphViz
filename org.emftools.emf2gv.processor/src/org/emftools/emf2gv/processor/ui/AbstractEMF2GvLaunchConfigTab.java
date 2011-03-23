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

import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

/**
 * Abstract launch configuration tab.
 */
public abstract class AbstractEMF2GvLaunchConfigTab extends
		AbstractLaunchConfigurationTab {

	/**
	 * Creates a group (with a <code>FILL_HORIZONTAL</code> style).
	 * 
	 * @param parent
	 *            the parent composite.
	 * @param name
	 *            the group's name.
	 * @param columnNb
	 *            the number of columns in the grid.
	 * @return the created group.
	 */
	protected Group createGroup(Composite parent, String name, int columnNb) {
		return createGroup(parent, name, columnNb, GridData.FILL_HORIZONTAL);
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
	 * @param gridDataStyle
	 *            the grid data style.
	 * @return the created group.
	 */
	protected Group createGroup(Composite parent, String name, int columnNb,
			int gridDataStyle) {
		Group group = new Group(parent, SWT.NONE);
		group.setLayoutData(new GridData(gridDataStyle));
		group.setLayout(new GridLayout(columnNb, false));
		group.setText(name);
		return group;
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
	protected Button createButton(Composite parent, String label,
			SelectionListener buttonListener) {
		Button button = new Button(parent, SWT.NONE);
		button.setText(label);
		if (buttonListener != null) {
			button.addSelectionListener(buttonListener);
		}
		return button;
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
	protected Button createBrowseButton(Composite parent,
			SelectionListener buttonListener) {
		return createButton(parent, "Browse...", buttonListener);
	}

	/**
	 * Creates a text field.
	 * 
	 * @param parent
	 *            the parent composite.
	 * @return the created text field.
	 */
	protected Text createText(Composite parent) {
		Text text = new Text(parent, SWT.BORDER);
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updateLaunchConfigurationDialog();
			}
		});
		return text;
	}

}
