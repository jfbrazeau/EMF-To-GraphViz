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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean validate(DiagnosticChain diagnostic, Map<Object, Object> context);

} // Filter
