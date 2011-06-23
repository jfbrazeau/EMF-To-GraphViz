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
 *   <li>{@link org.emftools.emf2gv.graphdesc.GVFigureDescription#getFilters <em>Filters</em>}</li>
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
	 * Returns the value of the '<em><b>Filters</b></em>' containment reference list.
	 * The list contents are of type {@link org.emftools.emf2gv.graphdesc.Filter}.
	 * It is bidirectional and its opposite is '{@link org.emftools.emf2gv.graphdesc.Filter#getFigureDescription <em>Figure Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filters</em>' containment reference list.
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getGVFigureDescription_Filters()
	 * @see org.emftools.emf2gv.graphdesc.Filter#getFigureDescription
	 * @model opposite="figureDescription" containment="true"
	 * @generated
	 */
	EList<Filter> getFilters();

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
