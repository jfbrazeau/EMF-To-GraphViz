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
package org.emftools.emf2gv.processor.ui.util;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.emftools.emf2gv.processor.ui.Activator;
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
