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
package org.emftools.emf2gv.graphdesc;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Attribute Figure</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.emftools.emf2gv.graphdesc.AbstractAttributeFigure#getClassFigure <em>Class Figure</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.AbstractAttributeFigure#getLabelStyle <em>Label Style</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getAbstractAttributeFigure()
 * @model abstract="true"
 * @generated
 */
public interface AbstractAttributeFigure extends AbstractFigure {
	/**
	 * Returns the value of the '<em><b>Class Figure</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.emftools.emf2gv.graphdesc.ClassFigure#getAttributeFigures <em>Attribute Figures</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class Figure</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class Figure</em>' container reference.
	 * @see #setClassFigure(ClassFigure)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getAbstractAttributeFigure_ClassFigure()
	 * @see org.emftools.emf2gv.graphdesc.ClassFigure#getAttributeFigures
	 * @model opposite="attributeFigures" required="true" transient="false"
	 * @generated
	 */
	ClassFigure getClassFigure();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.AbstractAttributeFigure#getClassFigure <em>Class Figure</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class Figure</em>' container reference.
	 * @see #getClassFigure()
	 * @generated
	 */
	void setClassFigure(ClassFigure value);

	/**
	 * Returns the value of the '<em><b>Label Style</b></em>' attribute list.
	 * The list contents are of type {@link org.emftools.emf2gv.graphdesc.FontStyle}.
	 * The literals are from the enumeration {@link org.emftools.emf2gv.graphdesc.FontStyle}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label Style</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label Style</em>' attribute list.
	 * @see org.emftools.emf2gv.graphdesc.FontStyle
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getAbstractAttributeFigure_LabelStyle()
	 * @model default=""
	 * @generated
	 */
	EList<FontStyle> getLabelStyle();

} // AbstractAttributeFigure
