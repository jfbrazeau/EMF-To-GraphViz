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
package org.emftools.emf2gv.util.ui;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.jface.text.Document;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.helper.OCLHelper;
import org.eclipse.ocl.util.TypeUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.TextChangeListener;
import org.eclipse.swt.custom.TextChangedEvent;
import org.eclipse.swt.custom.TextChangingEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

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

	/** The OCL to use */
	private OCL ocl;

	/** The OCL Helper */
	private OCLHelper<EClassifier, EOperation, EStructuralFeature, Constraint> oclHelper;

	/** The error icon */
	private Image errorIcon;

	/** The valid icon */
	private Image validIcon;

	/** The status label */
	private CLabel statusLabel;

	/** The expected return type of the OCL expression */
	private EClassifier expectedReturnType;

	/**
	 * Default constructor.
	 * 
	 * @param shell
	 *            the parent shell, or <code>null</code> to create a top-level
	 *            shell
	 * @param title
	 *            the dialog title
	 * @param ocl
	 *            the ocl.
	 * @param context
	 *            the context of the OCL value.
	 * @param initialValue
	 *            the dialog initial value.
	 * @param expectedReturnType
	 *            the expected return type (optional).
	 * @param selectAll
	 *            a boolean indicating if the text value must be selected by
	 *            default
	 */
	public OCLInputDialog(Shell shell, String title, OCL ocl,
			EClassifier context, String initialValue,
			EClassifier expectedReturnType, boolean selectAll) {
		super(shell);
		this.title = title;
		this.value = initialValue;
		this.selectAll = selectAll;
		this.expectedReturnType = expectedReturnType;
		this.ocl = ocl;
		oclHelper = ocl.createOCLHelper();
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
		input = new OCLSourceViewer(composite, SWT.BORDER | SWT.MULTI, ocl,
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

		// Validation on changes
		text.getContent().addTextChangeListener(new TextChangeListener() {
			public void textSet(TextChangedEvent event) {
				checkOCLSyntax();
			}

			public void textChanging(TextChangingEvent event) {
			}

			public void textChanged(TextChangedEvent event) {
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
			OCLExpression<EClassifier> oclExpression = oclHelper
					.createQuery(input.getTextWidget().getText());
			boolean typeCheckOk = true;
			if (expectedReturnType != null) {
				typeCheckOk = (oclExpression.getType().getInstanceClass() != null && oclExpression
						.getType().getInstanceClass()
						.equals(expectedReturnType.getInstanceClass()))
						|| TypeUtil.compatibleTypeMatch(oclHelper.getOCL()
								.getEnvironment(), oclExpression.getType(),
								expectedReturnType);
			}
			if (!typeCheckOk) {
				statusLabel.setText("Bad return type, expected '"
						+ expectedReturnType.getName() + "' instead of '"
						+ oclExpression.getType().getName() + "'");
				statusLabel.setImage(errorIcon);
			} else {
				statusLabel.setText("The expression is valid.");
				statusLabel.setImage(validIcon);
			}
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
		validIcon.dispose();
		return super.close();
	}

}
