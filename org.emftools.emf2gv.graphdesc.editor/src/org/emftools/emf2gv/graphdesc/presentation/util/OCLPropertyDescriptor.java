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
