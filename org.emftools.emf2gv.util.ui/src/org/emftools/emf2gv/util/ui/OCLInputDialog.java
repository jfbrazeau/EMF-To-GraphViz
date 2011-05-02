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
package org.emftools.emf2gv.util.ui;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.jface.text.Document;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.helper.OCLHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.emftools.emf2gv.util.OCLProvider;

/**
 * An OCL input dialog.
 */
public class OCLInputDialog extends TrayDialog {

	/** The dialog title */
	private String title;

	/** The dialog value */
	private String value;

	/** A boolean indicating if the text value must be selected by default */
	private boolean selectAll;

	/** OCL Input */
	private OCLSourceViewer input;

	/** The OCL Helper */
	private OCLHelper<EClassifier, EOperation, EStructuralFeature, Constraint> oclHelper;

	/** The error icon */
	private Image errorIcon;

	/** The valid icon */
	private Image validIcon;

	/** The status label */
	private CLabel statusLabel;

	/**
	 * Default constructor.
	 * 
	 * @param shell
	 *            the parent shell, or <code>null</code> to create a top-level
	 *            shell
	 * @param title
	 *            the dialog title
	 * @param context
	 *            the context of the OCL value.
	 * @param initialValue
	 *            the dialog initial value.
	 * @param selectAll
	 *            a boolean indicating if the text value must be selected by
	 *            default
	 */
	public OCLInputDialog(Shell shell, String title, EClassifier context,
			String initialValue, boolean selectAll) {
		super(shell);
		this.title = title;
		this.value = initialValue;
		this.selectAll = selectAll;
		oclHelper = OCLProvider.newOCL().createOCLHelper();
		oclHelper.setContext(context);
		errorIcon = Activator.getImageDescriptor("icons/error.gif")
				.createImage();
		validIcon = Activator.getImageDescriptor("icons/valid.gif")
				.createImage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets
	 * .Shell)
	 */
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		if (title != null) {
			newShell.setText(title);
		}
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets
	 * .Composite)
	 */
	protected Control createDialogArea(Composite parent) {
		final Composite composite = (Composite) super.createDialogArea(parent);
		statusLabel = new CLabel(composite, SWT.NONE);
		statusLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		input = new OCLSourceViewer(composite, SWT.BORDER | SWT.MULTI,
				oclHelper.getContextClassifier());
		input.setDocument(new Document(getValue()));
		StyledText text = input.getTextWidget();
		GridData data = new GridData(GridData.FILL_HORIZONTAL
				| GridData.FILL_VERTICAL);
		data.heightHint = 10 * text.getLineHeight();
		data.widthHint = convertHorizontalDLUsToPixels((int) (IDialogConstants.ENTRY_FIELD_WIDTH * 1.5));
		text.setLayoutData(data);
		text.setWordWrap(true);
		if (selectAll) {
			text.selectAll();
		} else {
			text.setSelection(text.getText().length());
		}
		// Validation listener
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				checkOCLSyntax();
			}
		});
		// Check the actual OCL expression
		checkOCLSyntax();
		return composite;
	};

	/**
	 * Checks the OCL syntax.
	 */
	private synchronized void checkOCLSyntax() {
		try {
			statusLabel.setText("Validating...");
			statusLabel.setImage(null);
			oclHelper.createQuery(input.getTextWidget().getText());
			statusLabel.setText("The expression is valid.");
			statusLabel.setImage(validIcon);
		} catch (ParserException ex) {
			statusLabel.setText("Syntax error : " + ex.getMessage());
			statusLabel.setImage(errorIcon);
		}
	}

	/*
	 * (non-Javadoc) Method declared on Dialog.
	 */
	@Override
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID) {
			value = input.getDocument().get();
		} else {
			value = null;
		}
		super.buttonPressed(buttonId);
	}

	/**
	 * @return the value entered by the user.
	 */
	public String getValue() {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.TrayDialog#close()
	 */
	@Override
	public boolean close() {
		errorIcon.dispose();
		return super.close();
	}

}
