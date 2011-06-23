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
