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
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reference Figure</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ReferenceFigure#getClassFigure <em>Class Figure</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ReferenceFigure#getEReference <em>EReference</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ReferenceFigure#getTargetArrowType <em>Target Arrow Type</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ReferenceFigure#getSourceArrowType <em>Source Arrow Type</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ReferenceFigure#isContainment <em>Containment</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ReferenceFigure#getCustomTargetArrow <em>Custom Target Arrow</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ReferenceFigure#getCustomSourceArrow <em>Custom Source Arrow</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ReferenceFigure#getColor <em>Color</em>}</li>
 *   <li>{@link org.emftools.emf2gv.graphdesc.ReferenceFigure#getStyle <em>Style</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getReferenceFigure()
 * @model
 * @generated
 */
public interface ReferenceFigure extends AbstractFigure {
	/**
	 * Returns the value of the '<em><b>EReference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EReference</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EReference</em>' reference.
	 * @see #setEReference(EReference)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getReferenceFigure_EReference()
	 * @model required="true"
	 * @generated
	 */
	EReference getEReference();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.ReferenceFigure#getEReference <em>EReference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EReference</em>' reference.
	 * @see #getEReference()
	 * @generated
	 */
	void setEReference(EReference value);

	/**
	 * Returns the value of the '<em><b>Class Figure</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.emftools.emf2gv.graphdesc.ClassFigure#getReferenceFigures <em>Reference Figures</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class Figure</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class Figure</em>' container reference.
	 * @see #setClassFigure(ClassFigure)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getReferenceFigure_ClassFigure()
	 * @see org.emftools.emf2gv.graphdesc.ClassFigure#getReferenceFigures
	 * @model opposite="referenceFigures" required="true" transient="false"
	 * @generated
	 */
	ClassFigure getClassFigure();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.ReferenceFigure#getClassFigure <em>Class Figure</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class Figure</em>' container reference.
	 * @see #getClassFigure()
	 * @generated
	 */
	void setClassFigure(ClassFigure value);

	/**
	 * Returns the value of the '<em><b>Target Arrow Type</b></em>' attribute.
	 * The default value is <code>"normal"</code>.
	 * The literals are from the enumeration {@link org.emftools.emf2gv.graphdesc.ArrowType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Arrow Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Arrow Type</em>' attribute.
	 * @see org.emftools.emf2gv.graphdesc.ArrowType
	 * @see #setTargetArrowType(ArrowType)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getReferenceFigure_TargetArrowType()
	 * @model default="normal"
	 * @generated
	 */
	ArrowType getTargetArrowType();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.ReferenceFigure#getTargetArrowType <em>Target Arrow Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Arrow Type</em>' attribute.
	 * @see org.emftools.emf2gv.graphdesc.ArrowType
	 * @see #getTargetArrowType()
	 * @generated
	 */
	void setTargetArrowType(ArrowType value);

	/**
	 * Returns the value of the '<em><b>Source Arrow Type</b></em>' attribute.
	 * The default value is <code>"none"</code>.
	 * The literals are from the enumeration {@link org.emftools.emf2gv.graphdesc.ArrowType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Arrow Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Arrow Type</em>' attribute.
	 * @see org.emftools.emf2gv.graphdesc.ArrowType
	 * @see #setSourceArrowType(ArrowType)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getReferenceFigure_SourceArrowType()
	 * @model default="none"
	 * @generated
	 */
	ArrowType getSourceArrowType();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.ReferenceFigure#getSourceArrowType <em>Source Arrow Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Arrow Type</em>' attribute.
	 * @see org.emftools.emf2gv.graphdesc.ArrowType
	 * @see #getSourceArrowType()
	 * @generated
	 */
	void setSourceArrowType(ArrowType value);

	/**
	 * Returns the value of the '<em><b>Containment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Containment</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Containment</em>' attribute.
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getReferenceFigure_Containment()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	boolean isContainment();

	/**
	 * Returns the value of the '<em><b>Custom Target Arrow</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Custom Target Arrow</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Custom Target Arrow</em>' attribute.
	 * @see #setCustomTargetArrow(String)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getReferenceFigure_CustomTargetArrow()
	 * @model
	 * @generated
	 */
	String getCustomTargetArrow();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.ReferenceFigure#getCustomTargetArrow <em>Custom Target Arrow</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Custom Target Arrow</em>' attribute.
	 * @see #getCustomTargetArrow()
	 * @generated
	 */
	void setCustomTargetArrow(String value);

	/**
	 * Returns the value of the '<em><b>Custom Source Arrow</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Custom Source Arrow</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Custom Source Arrow</em>' attribute.
	 * @see #setCustomSourceArrow(String)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getReferenceFigure_CustomSourceArrow()
	 * @model
	 * @generated
	 */
	String getCustomSourceArrow();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.ReferenceFigure#getCustomSourceArrow <em>Custom Source Arrow</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Custom Source Arrow</em>' attribute.
	 * @see #getCustomSourceArrow()
	 * @generated
	 */
	void setCustomSourceArrow(String value);

	/**
	 * Returns the value of the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Color</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Color</em>' attribute.
	 * @see #setColor(int)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getReferenceFigure_Color()
	 * @model
	 * @generated
	 */
	int getColor();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.ReferenceFigure#getColor <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color</em>' attribute.
	 * @see #getColor()
	 * @generated
	 */
	void setColor(int value);

	/**
	 * Returns the value of the '<em><b>Style</b></em>' attribute.
	 * The default value is <code>"normal"</code>.
	 * The literals are from the enumeration {@link org.emftools.emf2gv.graphdesc.ArrowStyle}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Style</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Style</em>' attribute.
	 * @see org.emftools.emf2gv.graphdesc.ArrowStyle
	 * @see #setStyle(ArrowStyle)
	 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage#getReferenceFigure_Style()
	 * @model default="normal"
	 * @generated
	 */
	ArrowStyle getStyle();

	/**
	 * Sets the value of the '{@link org.emftools.emf2gv.graphdesc.ReferenceFigure#getStyle <em>Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Style</em>' attribute.
	 * @see org.emftools.emf2gv.graphdesc.ArrowStyle
	 * @see #getStyle()
	 * @generated
	 */
	void setStyle(ArrowStyle value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean validate(DiagnosticChain diagnostic, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean targetClassFigureExists();

} // ReferenceFigure
