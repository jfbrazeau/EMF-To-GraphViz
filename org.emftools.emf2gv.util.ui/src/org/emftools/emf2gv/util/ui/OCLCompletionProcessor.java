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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.helper.Choice;
import org.eclipse.ocl.helper.OCLHelper;

/**
 * OCL completion processor.
 */
class OCLCompletionProcessor implements IContentAssistProcessor {

	/** OCL Helper */
	private OCLHelper<EClassifier, EOperation, EStructuralFeature, Constraint> oclHelper;

	/** Empty char array */
	private static final char[] NO_CHARS = {};

	/** Empty context information array */
	private static final IContextInformation[] NO_CONTEXTS = {};

	/** Empty completion proposal array */
	private static final ICompletionProposal[] NO_COMPLETIONS = {};

	/** Completion activation character list */
	private static final char[] ACTIVATION = { '.', ':' /* :: */, '>' /* -> */,
			'^' };

	/** Comparator used to sort the completion choices */
	private static final Comparator<Choice> CHOICES_COMPARATOR = new Comparator<Choice>() {
		public int compare(Choice o1, Choice o2) {
			int result = rank(o1) - rank(o2);
			if (result == 0) {
				result = o1.getName().compareTo(o2.getName());
			}
			return result;
		}

		private int rank(Choice choice) {
			switch (choice.getKind()) {
			case VARIABLE:
				return 0;
			case PACKAGE:
				return 1;
			case TYPE:
				return 2;
			case ENUMERATION_LITERAL:
				return 3;
			case STATE:
				return 4;
			case PROPERTY:
				return 5;
			case ASSOCIATION_CLASS:
				return 6;
			case OPERATION:
				return 7;
			case SIGNAL:
				return 8;
			default:
				return Integer.MAX_VALUE;
			}
		}
	};

	/**
	 * Default constructor.
	 * 
	 * @param ocl
	 *            the OCL to use.
	 * @param context
	 *            the OCL context classifier.
	 */
	public OCLCompletionProcessor(OCL ocl, EClassifier context) {
		oclHelper = ocl.createOCLHelper();
		oclHelper.setContext(context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#
	 * computeCompletionProposals(org.eclipse.jface.text.ITextViewer, int)
	 */
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int offset) {
		IDocument doc = (IDocument) viewer.getDocument();
		int replacementOffset = offset;
		try {
			if (offset > 0 && doc.getChar(offset - 1) == '>') {
				// check that this is an arrow
				if (offset < 2 || doc.getChar(offset - 2) != '-') {
					return NO_COMPLETIONS;
				}
			}
			if (offset > 0 && doc.getChar(offset - 1) == ':') {
				// check that this is a double-colon
				if (offset < 2 || doc.getChar(offset - 2) != ':') {
					return NO_COMPLETIONS;
				}
			}

			loop: while (replacementOffset > 0) {
				char trigger = doc.getChar(--replacementOffset);
				switch (trigger) {
				case '.':
				case '>':
				case ':':
				case '^':
				case ' ':
				case '\t':
				case '\n':
				case '\r':
				case '(':
				case '[':
				case '{':
					replacementOffset++;
					break loop;
				}
			}
		} catch (BadLocationException e) {
			// just don't return any proposal
			return NO_COMPLETIONS;
		}
		List<Choice> choices = getOCLChoices(doc, offset);
		return createCompletions(choices, replacementOffset, offset
				- replacementOffset);
	}

	/**
	 * Builds a list containing the OCL choices.
	 * 
	 * @param document
	 * @param offset
	 * @return
	 */
	private List<Choice> getOCLChoices(IDocument document, int offset) {
		if (oclHelper.getContextClassifier() == null) {
			return Collections.emptyList();
		}
		try {
			String text = document.get(0, offset);
			List<Choice> choices = oclHelper.getSyntaxHelp(null, text);
			return choices;
		} catch (Exception e) {
			// just don't provide proposals
			return Collections.emptyList();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#
	 * getCompletionProposalAutoActivationCharacters()
	 */
	public char[] getCompletionProposalAutoActivationCharacters() {
		return ACTIVATION;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#
	 * computeContextInformation(org.eclipse.jface.text.ITextViewer, int)
	 */
	public IContextInformation[] computeContextInformation(ITextViewer viewer,
			int offset) {
		return NO_CONTEXTS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#
	 * getContextInformationAutoActivationCharacters()
	 */
	public char[] getContextInformationAutoActivationCharacters() {
		return NO_CHARS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#
	 * getContextInformationValidator()
	 */
	public IContextInformationValidator getContextInformationValidator() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.text.contentassist.IContentAssistProcessor#getErrorMessage
	 * ()
	 */
	public String getErrorMessage() {
		return null;
	}

	/**
	 * Creates the completion proposals.
	 * 
	 * @param choices
	 *            the completion choices.
	 * @param replacementOffset
	 *            the replacement offset in the original text.
	 * @param replacementLength
	 *            the replacement length.
	 * @return the completion proposals.
	 */
	private ICompletionProposal[] createCompletions(List<Choice> choices,
			int replacementOffset, int replacementLength) {
		List<ICompletionProposal> result = new java.util.ArrayList<ICompletionProposal>();
		sortChoices(choices);
		for (Choice choice : choices) {
			String text = choice.getName();
			String display;
			int cursor;

			switch (choice.getKind()) {
			case OPERATION:
			case SIGNAL:
				// the description is already complete
				text = text + "()"; //$NON-NLS-1$
				display = choice.getDescription();
				cursor = text.length() - 1; // between the ()
				break;
			case PROPERTY:
			case ENUMERATION_LITERAL:
			case VARIABLE:
			case ASSOCIATION_CLASS:
				display = text + " : " + choice.getDescription(); //$NON-NLS-1$
				cursor = text.length();
				break;
			default:
				display = text;
				cursor = text.length();
				break;
			}

			result.add(new CompletionProposal(text, replacementOffset,
					replacementLength, cursor, null, display, null, null));
		}

		return result.toArray(new ICompletionProposal[result.size()]);
	}

	/**
	 * Sorts the choices.
	 * 
	 * @param choices
	 *            the choices to solrt.
	 */
	private void sortChoices(List<Choice> choices) {
		Collections.sort(choices, CHOICES_COMPARATOR);
	}
}
