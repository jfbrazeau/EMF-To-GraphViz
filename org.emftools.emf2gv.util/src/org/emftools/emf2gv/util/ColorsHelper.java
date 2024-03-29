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
package org.emftools.emf2gv.util;

import java.awt.Color;

/**
 * Helper class used to manipulate colors.
 * 
 * @author jbrazeau
 */
public class ColorsHelper {

	/** Hex characters */
	private static final char[] c = new char[] { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * Converts an integer to an HTML color representation.
	 * 
	 * @param color
	 *            the color to convert.
	 * @return the converted string.
	 */
	public static String toHtmlColor(Color color) {
		String result = null;
		if (color != null) {
			char[] cars = new char[7];
			cars[0] = '#';
			toHex((byte) color.getRed(), cars, 1); // RED
			toHex((byte) color.getGreen(), cars, 3); // GREEN
			toHex((byte) color.getBlue(), cars, 5); // BLUE
			return new String(cars);
		}
		else {
			result = "#000000";
		}
		return result;
	}

	/**
	 * Parses an html color.
	 * 
	 * @param htmlColor
	 *            the html color to parse.
	 * @return the color.
	 */
	public static Color parseHtmlColor(String htmlColor) {
		Color result = null;
		if (htmlColor != null) {
			htmlColor = htmlColor.trim();
			if (htmlColor.startsWith("#")) {
				htmlColor = htmlColor.substring(1);
			}
			long color = Long.parseLong(htmlColor, 16);
			int r = (int) ((color >> 16) & 0xFF);
			int g = (int) ((color >> 8) & 0xFF);
			int b = (int) ( color & 0xFF);
			result = new Color(r, g, b);
		}
		return result;
	}

	/**
	 * Converts a byte value to hexadecimal and put the result in an array.
	 * 
	 * @param b
	 *            the byte value to convert.
	 * @param charArray
	 *            the array in which the result is put.
	 * @param from
	 *            the array's index to use as a starting point.
	 */
	private static void toHex(byte b, char[] charArray, int from) {
		charArray[from] = c[(b >> 4) & 0x0F];
		charArray[from + 1] = c[b & 0x0F];
	}

	/**
	 * Makes a color get brighter.
	 * 
	 * @param color
	 *            the color to change.
	 * @return the brighter color.
	 */
	public static Color makeColorBrighter(Color color) {
		int r = makeBaseColorBrighter(color.getRed());
		int g = makeBaseColorBrighter(color.getGreen());
		int b = makeBaseColorBrighter(color.getBlue());
		return new Color((int) r, (int) g, (int) b);
	}

	/**
	 * Makes a base color (red, green or blue) get brighter.
	 * 
	 * @param baseColor
	 *            the base color.
	 * @return the brighter base color.
	 */
	private static int makeBaseColorBrighter(int baseColor) {
		return (int) (255d - ((255d - ((double) baseColor)) / 3));
	}

}
