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
package org.emftools.emf2gv.graphdesc;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class Figure</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ClassFigure#getEPackage <em>EPackage</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ClassFigure#getEClass <em>EClass</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ClassFigure#getLabelEAttribute <em>Label EAttribute</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ClassFigure#getAttributeFigures <em>Attribute Figures</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ClassFigure#getReferenceFigures <em>Reference Figures</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ClassFigure#getGvFigureDescription <em>Gv Figure Description</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ClassFigure#getHeaderBackgroundColor <em>Header Background Color</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ClassFigure#getBodyBackgroundColor <em>Body Background Color</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ClassFigure#getNestedFiguresEReferences <em>Nested Figures EReferences</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ClassFigure#isDynamicAppearance <em>Dynamic Appearance</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ClassFigure#getHeaderBackgroundColorAccessor <em>Header Background Color Accessor</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ClassFigure#getBodyBackgroundColorAccessor <em>Body Background Color Accessor</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ClassFigure#isContainer <em>Container</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getClassFigure()
 * @model
 * @generated
 */
public interface ClassFigure extends AbstractFigure {
	/**
	 * Returns the value of the '<em><b>EClass</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EClass</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EClass</em>' reference.
	 * @see #setEClass(EClass)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getClassFigure_EClass()
	 * @model required="true"
	 * @generated
	 */
	EClass getEClass();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.ClassFigure#getEClass <em>EClass</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EClass</em>' reference.
	 * @see #getEClass()
	 * @generated
	 */
	void setEClass(EClass value);

	/**
	 * Returns the value of the '<em><b>Label EAttribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label EAttribute</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label EAttribute</em>' reference.
	 * @see #setLabelEAttribute(EAttribute)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getClassFigure_LabelEAttribute()
	 * @model
	 * @generated
	 */
	EAttribute getLabelEAttribute();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.ClassFigure#getLabelEAttribute <em>Label EAttribute</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label EAttribute</em>' reference.
	 * @see #getLabelEAttribute()
	 * @generated
	 */
	void setLabelEAttribute(EAttribute value);

	/**
	 * Returns the value of the '<em><b>Attribute Figures</b></em>' containment reference list.
	 * The list contents are of type {@link org.emftools.emf2gv.graphdesc.AttributeFigure}.
	 * It is bidirectional and its opposite is '{@link org.emftools.emf2gv.graphdesc.AttributeFigure#getClassFigure <em>Class Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute Figures</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribute Figures</em>' containment reference list.
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getClassFigure_AttributeFigures()
	 * @see org.emftools.emf2gv.graphdesc.AttributeFigure#getClassFigure
	 * @model opposite="classFigure" containment="true"
	 * @generated
	 */
	EList<AttributeFigure> getAttributeFigures();

	/**
	 * Returns the value of the '<em><b>EPackage</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EPackage</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EPackage</em>' reference.
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getClassFigure_EPackage()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	EPackage getEPackage();

	/**
	 * Returns the value of the '<em><b>Reference Figures</b></em>' containment reference list.
	 * The list contents are of type {@link org.emftools.emf2gv.graphdesc.ReferenceFigure}.
	 * It is bidirectional and its opposite is '{@link org.emftools.emf2gv.graphdesc.ReferenceFigure#getClassFigure <em>Class Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference Figures</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference Figures</em>' containment reference list.
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getClassFigure_ReferenceFigures()
	 * @see org.emftools.emf2gv.graphdesc.ReferenceFigure#getClassFigure
	 * @model opposite="classFigure" containment="true"
	 * @generated
	 */
	EList<ReferenceFigure> getReferenceFigures();

	/**
	 * Returns the value of the '<em><b>Gv Figure Description</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.emftools.emf2gv.graphdesc.GVFigureDescription#getClassFigures <em>Class Figures</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Gv Figure Description</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gv Figure Description</em>' container reference.
	 * @see #setGvFigureDescription(GVFigureDescription)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getClassFigure_GvFigureDescription()
	 * @see org.emftools.emf2gv.graphdesc.GVFigureDescription#getClassFigures
	 * @model opposite="classFigures" required="true" transient="false"
	 * @generated
	 */
	GVFigureDescription getGvFigureDescription();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.ClassFigure#getGvFigureDescription <em>Gv Figure Description</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gv Figure Description</em>' container reference.
	 * @see #getGvFigureDescription()
	 * @generated
	 */
	void setGvFigureDescription(GVFigureDescription value);

	/**
	 * Returns the value of the '<em><b>Header Background Color</b></em>' attribute.
	 * The default value is <code>"10337785"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Header Background Color</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Header Background Color</em>' attribute.
	 * @see #setHeaderBackgroundColor(int)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getClassFigure_HeaderBackgroundColor()
	 * @model default="10337785"
	 * @generated
	 */
	int getHeaderBackgroundColor();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.ClassFigure#getHeaderBackgroundColor <em>Header Background Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Header Background Color</em>' attribute.
	 * @see #getHeaderBackgroundColor()
	 * @generated
	 */
	void setHeaderBackgroundColor(int value);

	/**
	 * Returns the value of the '<em><b>Body Background Color</b></em>' attribute.
	 * The default value is <code>"15658734"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Body Background Color</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Body Background Color</em>' attribute.
	 * @see #setBodyBackgroundColor(int)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getClassFigure_BodyBackgroundColor()
	 * @model default="15658734"
	 * @generated
	 */
	int getBodyBackgroundColor();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.ClassFigure#getBodyBackgroundColor <em>Body Background Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Body Background Color</em>' attribute.
	 * @see #getBodyBackgroundColor()
	 * @generated
	 */
	void setBodyBackgroundColor(int value);

	/**
	 * Returns the value of the '<em><b>Nested Figures EReferences</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EReference}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nested Figures EReferences</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nested Figures EReferences</em>' reference list.
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getClassFigure_NestedFiguresEReferences()
	 * @model
	 * @generated
	 */
	EList<EReference> getNestedFiguresEReferences();

	/**
	 * Returns the value of the '<em><b>Dynamic Appearance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dynamic Appearance</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dynamic Appearance</em>' attribute.
	 * @see #setDynamicAppearance(boolean)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getClassFigure_DynamicAppearance()
	 * @model
	 * @generated
	 */
	boolean isDynamicAppearance();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.ClassFigure#isDynamicAppearance <em>Dynamic Appearance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dynamic Appearance</em>' attribute.
	 * @see #isDynamicAppearance()
	 * @generated
	 */
	void setDynamicAppearance(boolean value);

	/**
	 * Returns the value of the '<em><b>Header Background Color Accessor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Header Background Color Accessor</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Header Background Color Accessor</em>' attribute.
	 * @see #setHeaderBackgroundColorAccessor(String)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getClassFigure_HeaderBackgroundColorAccessor()
	 * @model
	 * @generated
	 */
	String getHeaderBackgroundColorAccessor();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.ClassFigure#getHeaderBackgroundColorAccessor <em>Header Background Color Accessor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Header Background Color Accessor</em>' attribute.
	 * @see #getHeaderBackgroundColorAccessor()
	 * @generated
	 */
	void setHeaderBackgroundColorAccessor(String value);

	/**
	 * Returns the value of the '<em><b>Body Background Color Accessor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Body Background Color Accessor</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Body Background Color Accessor</em>' attribute.
	 * @see #setBodyBackgroundColorAccessor(String)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getClassFigure_BodyBackgroundColorAccessor()
	 * @model
	 * @generated
	 */
	String getBodyBackgroundColorAccessor();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.ClassFigure#getBodyBackgroundColorAccessor <em>Body Background Color Accessor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Body Background Color Accessor</em>' attribute.
	 * @see #getBodyBackgroundColorAccessor()
	 * @generated
	 */
	void setBodyBackgroundColorAccessor(String value);

	/**
	 * Returns the value of the '<em><b>Container</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Container</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Container</em>' attribute.
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getClassFigure_Container()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	boolean isContainer();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	AttributeFigure getAttributeFigure(EAttribute eAttribute);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	ReferenceFigure getReferenceFigure(EReference eReference);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean validate(DiagnosticChain diagnostic, Map<Object, Object> context);

} // ClassFigure
