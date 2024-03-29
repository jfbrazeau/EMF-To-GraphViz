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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Filter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.emftools.emf2gv.graphdesc.Filter#getName <em>Name</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.Filter#getFilteredType <em>Filtered Type</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.Filter#getFilterExpression <em>Filter Expression</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.Filter#getFigureDescription <em>Figure Description</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.Filter#isEnabled <em>Enabled</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getFilter()
 * @model
 * @generated
 */
public interface Filter extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getFilter_Name()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	String getName();

	/**
	 * Returns the value of the '<em><b>Filtered Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filtered Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filtered Type</em>' reference.
	 * @see #setFilteredType(EClass)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getFilter_FilteredType()
	 * @model
	 * @generated
	 */
	EClass getFilteredType();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.Filter#getFilteredType <em>Filtered Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filtered Type</em>' reference.
	 * @see #getFilteredType()
	 * @generated
	 */
	void setFilteredType(EClass value);

	/**
	 * Returns the value of the '<em><b>Filter Expression</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filter Expression</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filter Expression</em>' attribute.
	 * @see #setFilterExpression(String)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getFilter_FilterExpression()
	 * @model default="true"
	 * @generated
	 */
	String getFilterExpression();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.Filter#getFilterExpression <em>Filter Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filter Expression</em>' attribute.
	 * @see #getFilterExpression()
	 * @generated
	 */
	void setFilterExpression(String value);

	/**
	 * Returns the value of the '<em><b>Figure Description</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.emftools.emf2gv.graphdesc.GVFigureDescription#getFilters <em>Filters</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Figure Description</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Figure Description</em>' container reference.
	 * @see #setFigureDescription(GVFigureDescription)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getFilter_FigureDescription()
	 * @see org.emftools.emf2gv.graphdesc.GVFigureDescription#getFilters
	 * @model opposite="filters" transient="false"
	 * @generated
	 */
	GVFigureDescription getFigureDescription();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.Filter#getFigureDescription <em>Figure Description</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Figure Description</em>' container reference.
	 * @see #getFigureDescription()
	 * @generated
	 */
	void setFigureDescription(GVFigureDescription value);

	/**
	 * Returns the value of the '<em><b>Enabled</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enabled</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enabled</em>' attribute.
	 * @see #setEnabled(boolean)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getFilter_Enabled()
	 * @model default="true"
	 * @generated
	 */
	boolean isEnabled();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.Filter#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled</em>' attribute.
	 * @see #isEnabled()
	 * @generated
	 */
	void setEnabled(boolean value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean validate(DiagnosticChain diagnostic, Map<Object, Object> context);

} // Filter
