/**
 * Copyright (c) 2010, Jean-Francois Brazeau. All rights reserved.
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
package org.emftools.emf2gv.processor.ui.util;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.emftools.emf2gv.processor.Activator;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import com.sun.org.apache.xerces.internal.impl.io.MalformedByteSequenceException;

/**
 * Property tester allowing to test if a workspace resource is an XMI file.
 */
public class XMIPropertyTester extends PropertyTester {

	/**
	 * This exception is used to stop the SAX parse processing.
	 */
	class StopSAXParserException extends SAXParseException {

		/** Default serial version UID */
		private static final long serialVersionUID = 1L;

		/** Boolean indicating if the file is an XMI file */
		private boolean xmiFileRegognized;

		/**
		 * Default constructor.
		 * 
		 * @param xmiFileRegognized
		 *            boolean indicating if the file is an XMI file.
		 */
		public StopSAXParserException(boolean xmiFileRegognized) {
			super("", null);
			this.xmiFileRegognized = xmiFileRegognized;
		}

		/**
		 * Indicates if the file is an XMI file.
		 * 
		 * @return a boolean indicating if the file is an XMI file
		 */
		public boolean getXmiFileRecognized() {
			return xmiFileRegognized;
		}
	}

	/**
	 * Default constructor.
	 */
	public XMIPropertyTester() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object,
	 * java.lang.String, java.lang.Object[], java.lang.Object)
	 */
	@Override
	public boolean test(Object receiver, String property, Object[] args,
			Object expectedValue) {
		boolean result = false;
		if (receiver instanceof IFile) {
			IFile file = (IFile) receiver;
			try {
				/*
				 * In order to see if the file is an XML file, we start a XML
				 * parsing process with a SAX Parser in order to use the fewest
				 * memory resources (we only have to inspect the root node of
				 * the XMI file to see if there is a attribute like
				 * xmlns:xmi="http://www.omg.org/XMI")
				 */
				SAXParser parser = SAXParserFactory.newInstance()
						.newSAXParser();
				parser.parse(file.getContents(), new DefaultHandler() {
					@Override
					public void startElement(String uri, String localName,
							String qName, Attributes attributes)
							throws SAXException {
						for (int i = 0; i < attributes.getLength(); i++) {
							String attrLocalName = attributes.getLocalName(i);
							String attrValue = attributes.getValue(i);
							if ("http://www.omg.org/XMI".equals(attrValue)
									&& attrLocalName.startsWith("xmlns:")) {
								// If we find the right attribute, we throw a
								// StopSAXException
								// to stop the parsing process with a true value
								// indicating
								// that this file seems to be an XMI file
								throw new StopSAXParserException(true);
							}
						}
						// If it has not been found, we throw a StopSAXException
						// to stop the parsing process with a false value
						// indicating
						// that this file is not an XMI file
						throw new StopSAXParserException(false);
					}
				});
			}
			/*
			 * This exception does not correspond to a real error but is used to
			 * interrupt the SAX Parse process.
			 */
			catch (StopSAXParserException e) {
				result = e.getXmiFileRecognized();
			}
			/*
			 * XML Parsing exceptions (not loggued)
			 */
			catch (MalformedByteSequenceException e) {
				result = false;
			} catch (SAXException e) {
				result = false;
			}
			/*
			 * Other exceptions
			 */
			catch (ParserConfigurationException e) {
				Activator
						.getDefault()
						.logError(
								"Unexpected error while trying to parse an XML file",
								e);
			} catch (IOException e) {
				Activator
						.getDefault()
						.logError(
								"Unexpected error while trying to parse an XML file",
								e);
			} catch (CoreException e) {
				Activator
						.getDefault()
						.logError(
								"Unexpected error while trying to parse an XML file",
								e);
			}
		}
		return result;
	}

}
