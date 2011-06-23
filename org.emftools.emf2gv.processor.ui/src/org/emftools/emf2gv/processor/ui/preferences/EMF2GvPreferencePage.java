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
package org.emftools.emf2gv.processor.ui.preferences;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Set;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.emftools.emf2gv.processor.ui.Activator;

/**
 * This class represents a preference page that is contributed to the
 * Preferences dialog. By subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows us to create a page
 * that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the
 * preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 */

public class EMF2GvPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	/**
	 * Default constructor.
	 */
	public EMF2GvPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("EMF To GraphViz configuration page");
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */
	public void createFieldEditors() {
		// Character set
		Set<String> charsetKeys = Charset.availableCharsets().keySet();
		String[][] charsets = new String[charsetKeys.size()][2];
		int i = 0;
		for (String charsetKey : charsetKeys) {
			charsets[i][0] = charsetKey;
			charsets[i][1] = charsetKey;
			i++;
		}
		ComboFieldEditor comboFieldEditor = new ComboFieldEditor(
				EMF2GvPreferenceConstants.P_GV_SOURCE_ENCODING,
				"&Graphviz source encoding :", charsets, getFieldEditorParent());
		addField(comboFieldEditor);

		// Dot path
		addField(new FileFieldEditor(
				EMF2GvPreferenceConstants.P_DOT_UTILITY_PATH,
				"&Dot utility path :", true,
				StringFieldEditor.VALIDATE_ON_KEY_STROKE,
				getFieldEditorParent()) {
			@Override
			protected boolean checkState() {
				String executablePath = getTextControl().getText();
				if (executablePath != null) {
					executablePath = executablePath.trim();
				}
				boolean valid = true;
				try {
					Runtime.getRuntime().exec(
							new String[] { executablePath, "-V" });
					clearErrorMessage();
				} catch (IOException e) {
					File file = new File(executablePath);
					if (!file.exists()) {
						showErrorMessage("Specified executable file does not exist");
					} else {
						showErrorMessage("Specified executable not seem to be runnable");
					}
					valid = false;
				}
				return valid;
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

}