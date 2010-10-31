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
package org.emftools.emf2gv.graphdesc;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>GV Figure Description</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.emftools.emf2gv.graphdesc.GVFigureDescription#getEPackages <em>EPackages</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.GVFigureDescription#getClassFigures <em>Class Figures</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.GVFigureDescription#getOrientation <em>Orientation</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.GVFigureDescription#isAlignSameEClasses <em>Align Same EClasses</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getGVFigureDescription()
 * @model
 * @generated
 */
public interface GVFigureDescription extends EObject {
	/**
	 * Returns the value of the '<em><b>Class Figures</b></em>' containment reference list.
	 * The list contents are of type {@link org.emftools.emf2gv.graphdesc.ClassFigure}.
	 * It is bidirectional and its opposite is '{@link org.emftools.emf2gv.graphdesc.ClassFigure#getGvFigureDescription <em>Gv Figure Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class Figures</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class Figures</em>' containment reference list.
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getGVFigureDescription_ClassFigures()
	 * @see org.emftools.emf2gv.graphdesc.ClassFigure#getGvFigureDescription
	 * @model opposite="gvFigureDescription" containment="true"
	 * @generated
	 */
	EList<ClassFigure> getClassFigures();

	/**
	 * Returns the value of the '<em><b>Orientation</b></em>' attribute.
	 * The default value is <code>"LeftToRight"</code>.
	 * The literals are from the enumeration {@link org.emftools.emf2gv.graphdesc.Orientation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Orientation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Orientation</em>' attribute.
	 * @see org.emftools.emf2gv.graphdesc.Orientation
	 * @see #setOrientation(Orientation)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getGVFigureDescription_Orientation()
	 * @model default="LeftToRight"
	 * @generated
	 */
	Orientation getOrientation();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.GVFigureDescription#getOrientation <em>Orientation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orientation</em>' attribute.
	 * @see org.emftools.emf2gv.graphdesc.Orientation
	 * @see #getOrientation()
	 * @generated
	 */
	void setOrientation(Orientation value);

	/**
	 * Returns the value of the '<em><b>Align Same EClasses</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Align Same EClasses</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Align Same EClasses</em>' attribute.
	 * @see #setAlignSameEClasses(boolean)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getGVFigureDescription_AlignSameEClasses()
	 * @model
	 * @generated
	 */
	boolean isAlignSameEClasses();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.GVFigureDescription#isAlignSameEClasses <em>Align Same EClasses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Align Same EClasses</em>' attribute.
	 * @see #isAlignSameEClasses()
	 * @generated
	 */
	void setAlignSameEClasses(boolean value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean validate(DiagnosticChain diagnostic, Map<Object, Object> context);

	/**
	 * Returns the value of the '<em><b>EPackages</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EPackage}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EPackages</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EPackages</em>' reference list.
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getGVFigureDescription_EPackages()
	 * @model
	 * @generated
	 */
	EList<EPackage> getEPackages();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	ClassFigure getClassFigure(EClass eClass);

} // GVFigureDescription
