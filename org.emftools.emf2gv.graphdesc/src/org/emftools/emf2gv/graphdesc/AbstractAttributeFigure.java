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
 * 
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
