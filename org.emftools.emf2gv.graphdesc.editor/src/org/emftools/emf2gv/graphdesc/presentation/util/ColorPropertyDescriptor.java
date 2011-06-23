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

import java.awt.Color;
import java.util.Map;

import org.eclipse.emf.common.ui.celleditor.ExtendedDialogCellEditor;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.emftools.emf2gv.util.ColorsHelper;

/**
 * A color property descriptor.
 * 
 * This property descriptor allows to open a <code>ColorDialog</code> from the
 * properties view.
 * 
 * @author jbrazeau
 * 
 */
public class ColorPropertyDescriptor extends PropertyDescriptor {

	/** The color icons map */
	private Map<String, Image> colorIcons;

	/** The label provider */
	private LabelProvider labelProvider = new LabelProvider() {
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
		 */
		@Override
		public String getText(Object element) {
			return ColorsHelper.toHtmlColor((Color) element);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
		 */
		@Override
		public Image getImage(Object element) {
			Color colorValue = (Color) element;
			String htmlColor = ColorsHelper.toHtmlColor(colorValue);
			Image colorIcon = colorIcons.get(htmlColor);
			if (colorIcon == null) {
				colorIcon = new Image(Display.getCurrent(), 16, 16);
				colorIcons.put(htmlColor, colorIcon);
				GC gc = new GC(colorIcon);
				org.eclipse.swt.graphics.Color color = new org.eclipse.swt.graphics.Color(
						Display.getCurrent(), colorValue.getRed(), // RED
						colorValue.getGreen(), // GREEN
						colorValue.getBlue()); // BLUE
				gc.setBackground(color);
				gc.fillRectangle(2, 2, 12, 12);
				color.dispose();
				gc.drawRectangle(1, 1, 13, 13);
			}
			return colorIcon;
		}
	};

	/**
	 * Default constructor.
	 * 
	 * @param object
	 *            the underlying object.
	 * @param itemPropertyDescriptor
	 *            the item property descriptor.
	 * @param colorIcons
	 *            the color icons map (that used to dispose them when they are
	 *            no longer required).
	 */
	public ColorPropertyDescriptor(Object object,
			IItemPropertyDescriptor itemPropertyDescriptor,
			Map<String, Image> colorIcons) {
		super(object, itemPropertyDescriptor);
		this.colorIcons = colorIcons;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.edit.ui.provider.PropertyDescriptor#getLabelProvider()
	 */
	@Override
	public ILabelProvider getLabelProvider() {
		return labelProvider;
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
		} else {
			return new ExtendedDialogCellEditor(composite, getLabelProvider()) {
				@Override
				protected Object openDialogBox(Control cellEditorWindow) {
					ColorDialog dialog = new ColorDialog(Display.getCurrent()
							.getActiveShell());
					dialog.setText("Select a color");
					Color colorValue = (Color) getValue();
					dialog.setRGB(new RGB(colorValue.getRed(), // RED
							colorValue.getGreen(), // GREEN
							colorValue.getBlue())); // BLUE
					RGB rgb = dialog.open();
					labelProvider.dispose();
					if (rgb != null) {
						return new Color(rgb.red, rgb.green, rgb.blue);
					}
					return null;
				}
			};
		}
	}

}
