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
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.emftools.emf2gv.util.ui.OCLInputDialog;

/**
 * An OCL property descriptor.
 * 
 * This property descriptor allows to open a OCL dialog edtor.
 * 
 * @author jbrazeau
 * 
 */
public class OCLPropertyDescriptor extends PropertyDescriptor {

	/** The OCL */
	private OCL ocl;

	/** The OCL context */
	private EClassifier context;

	/** The expected return type of the OCL expression */
	private EClassifier expectedReturnType;

	/**
	 * Default constructor.
	 * 
	 * @param object
	 *            the underlying object.
	 * @param itemPropertyDescriptor
	 *            the item property descriptor.
	 * @param ocl
	 *            the ocl (optional)
	 * @param expectedReturnType
	 *            the expected return type of the OCL expression
	 * @param the
	 *            OCL context to use.
	 */
	public OCLPropertyDescriptor(Object object,
			IItemPropertyDescriptor itemPropertyDescriptor, OCL ocl,
			EClassifier context, EClassifier expectedReturnType) {
		super(object, itemPropertyDescriptor);
		this.ocl = ocl;
		this.context = context;
		this.expectedReturnType = expectedReturnType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.edit.ui.provider.PropertyDescriptor#createPropertyEditor
	 * (org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public CellEditor createPropertyEditor(Composite composite) {
		if (!itemPropertyDescriptor.canSetProperty(object)) {
			return null;
		} else if (context == null) {
			return new ExtendedDialogCellEditor(composite, getLabelProvider()) {
				@Override
				protected Object openDialogBox(Control cellEditorWindow) {
					MessageDialog.openInformation(Display.getCurrent()
							.getActiveShell(), "Information",
							getMissingContextMessage());
					return null;
				}
			};
		} else {
			return new ExtendedDialogCellEditor(composite, getLabelProvider()) {
				@Override
				protected Object openDialogBox(Control cellEditorWindow) {
					OCLInputDialog dialog = new OCLInputDialog(Display
							.getCurrent().getActiveShell(),
							"Enter the OCL expression", ocl, context,
							(String) getValue(), expectedReturnType, true);
					if (dialog.open() == Dialog.OK) {
						return dialog.getValue();
					}
					return null;
				}
			};
		}
	}

	/**
	 * Returns the message to show if no context has been defined.
	 * 
	 * <p>
	 * This is intended to be subclassed.
	 * </p>
	 * 
	 * @return the message to show if no context has been defined.
	 */
	protected String getMissingContextMessage() {
		return "It is not possible to edit an OCL expression if no context has been defined";
	}

}
