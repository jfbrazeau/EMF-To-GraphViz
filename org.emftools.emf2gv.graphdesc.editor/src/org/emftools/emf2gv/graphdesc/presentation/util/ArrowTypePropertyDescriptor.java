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
