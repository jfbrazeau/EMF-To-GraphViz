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
package org.emftools.emf2gv.graphdesc.presentation.util;

import org.eclipse.emf.common.ui.celleditor.ExtendedDialogCellEditor;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.emftools.emf2gv.graphdesc.ArrowType;

/**
 * Arrow type property descriptor.
 * 
 * @author jbrazeau
 */
public class ArrowTypePropertyDescriptor extends PropertyDescriptor {

	/**
	 * Default constructor.
	 * 
	 * @param object
	 *            the object.
	 * @param itemPropertyDescriptor
	 *            the item propertye descriptor.
	 */
	public ArrowTypePropertyDescriptor(Object object,
			IItemPropertyDescriptor itemPropertyDescriptor) {
		super(object, itemPropertyDescriptor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.edit.ui.provider.PropertyDescriptor#createPropertyEditor
	 * (org.eclipse.swt.widgets.Composite)
	 */
	public CellEditor createPropertyEditor(Composite composite) {
	    if (!itemPropertyDescriptor.canSetProperty(object)) {
	    	return null;
	    }
	    else {
			return new ExtendedDialogCellEditor(composite, getLabelProvider()) {
				@Override
				protected Object openDialogBox(Control cellEditorWindow) {
					ElementListSelectionDialog dialog = new ElementListSelectionDialog(
							PlatformUI.getWorkbench().getDisplay().getActiveShell(),
							getLabelProvider());
					dialog.setElements(ArrowType.VALUES.toArray());
					dialog.setInitialSelections(new Object[] { getValue() });
					dialog.setTitle("Arrow types");
					dialog.setMessage("Select an arrow type");
					dialog.setMultipleSelection(false);
					int result = dialog.open();
					labelProvider.dispose();
					if (result == Window.OK) {
						return dialog.getFirstResult();
					}
					return null;
				}
			};
	    }
	}

}
