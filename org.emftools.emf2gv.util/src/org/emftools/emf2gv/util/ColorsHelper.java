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
	public static String toHtmlColor(int color) {
		char[] result = new char[7];
		result[0] = '#';
		toHex((byte) getRed(color), result, 1); // RED
		toHex((byte) getGreen(color), result, 3); // GREEN
		toHex((byte) getBlue(color), result, 5); // BLUE
		return new String(result);
	}

	/**
	 * Converts a color to an integer value.
	 * @param color the color to convert.
	 * @return the converted color.
	 */
	public static int toInt(Color color) {
		return getColor(color.getRed(), color.getGreen(), color.getBlue());
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
	 * @param color
	 *            the color.
	 * @return the red component of the given color.
	 */
	public static int getRed(int color) {
		return (color >> 16) & 255;
	}

	/**
	 * @param color
	 *            the color.
	 * @return the green component of the given color.
	 */
	public static int getGreen(int color) {
		return (color >> 8) & 255;
	}

	/**
	 * @param color
	 *            the color.
	 * @return the blue component of the given color.
	 */
	public static int getBlue(int color) {
		return color & 255;
	}

	/**
	 * Computes a color from its red, green, blue components.
	 * 
	 * @param red
	 *            the red component.
	 * @param green
	 *            the green component.
	 * @param blue
	 *            the blue component.
	 * @return the computed color value.
	 */
	public static int getColor(int red, int green, int blue) {
		return (blue & 255) + ((green & 255) << 8) + ((red & 255) << 16);
	}

	/**
	 * Makes a color get brighter.
	 * 
	 * @param color
	 *            the color to change.
	 * @return the brighter color.
	 */
	public static int makeColorBrighter(int color) {
		int r = makeBaseColorBrighter(getRed(color));
		int g = makeBaseColorBrighter(getGreen(color));
		int b = makeBaseColorBrighter(getBlue(color));
		return getColor((int) r, (int) g, (int) b);
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
