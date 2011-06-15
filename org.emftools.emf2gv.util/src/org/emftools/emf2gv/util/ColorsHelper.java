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
