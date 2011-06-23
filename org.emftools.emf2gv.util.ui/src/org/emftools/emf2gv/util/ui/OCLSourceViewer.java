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
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;

/**
 * OCL source viewer.
 * 
 * @author jbrazeau
 */
public class OCLSourceViewer extends SourceViewer {

	/**
	 * Default constructor.
	 * 
	 * @param parent
	 *            the parent composite.
	 * @param styles
	 *            the styles of the source viewer.
	 * @param ocl
	 *            the OCL.
	 * @param context
	 *            the context classifier for the OCL value.
	 */
	public OCLSourceViewer(Composite parent, int styles, OCL ocl,
			EClassifier context) {
		super(parent, null, styles);
		configure(new OCLConfiguration(ocl, context));
		getTextWidget().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.keyCode) {
				case ' ':
					if ((e.stateMask & SWT.CTRL) == SWT.CTRL) {
						fContentAssistant.showPossibleCompletions();
					}
				}

			}
		});
	}

}

/**
 * The OCL source viewer configuration.
 */
class OCLConfiguration extends SourceViewerConfiguration {

	/** The OCL */
	private OCL ocl;

	/** The context classifier for the OCL value */
	private EClassifier context;

	/**
	 * Default constructor.
	 * 
	 * @param ocl
	 *            the OCL.
	 * @param context
	 *            the context classifier for the OCL value.
	 */
	public OCLConfiguration(OCL ocl, EClassifier context) {
		this.ocl = ocl;
		this.context = context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.text.source.SourceViewerConfiguration#getContentAssistant
	 * (org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
		ContentAssistant result = new ContentAssistant();
		result.setContentAssistProcessor(new OCLCompletionProcessor(ocl,
				context), IDocument.DEFAULT_CONTENT_TYPE);
		result.enableAutoActivation(true);
		return result;
	}

}
