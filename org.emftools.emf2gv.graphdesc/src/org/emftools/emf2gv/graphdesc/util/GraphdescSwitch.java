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
package org.emftools.emf2gv.graphdesc.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.emftools.emf2gv.graphdesc.AbstractAttributeFigure;
import org.emftools.emf2gv.graphdesc.AbstractFigure;
import org.emftools.emf2gv.graphdesc.AbstractReferenceFigure;
import org.emftools.emf2gv.graphdesc.AttributeFigure;
import org.emftools.emf2gv.graphdesc.ClassFigure;
import org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider;
import org.emftools.emf2gv.graphdesc.Filter;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;
import org.emftools.emf2gv.graphdesc.ReferenceFigure;
import org.emftools.emf2gv.graphdesc.RichAttributeFigure;
import org.emftools.emf2gv.graphdesc.RichReferenceFigure;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage
 * @generated
 */
public class GraphdescSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static GraphdescPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphdescSwitch() {
		if (modelPackage == null) {
			modelPackage = GraphdescPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case GraphdescPackage.GV_FIGURE_DESCRIPTION: {
				GVFigureDescription gvFigureDescription = (GVFigureDescription)theEObject;
				T result = caseGVFigureDescription(gvFigureDescription);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GraphdescPackage.ABSTRACT_FIGURE: {
				AbstractFigure abstractFigure = (AbstractFigure)theEObject;
				T result = caseAbstractFigure(abstractFigure);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GraphdescPackage.CLASS_FIGURE: {
				ClassFigure classFigure = (ClassFigure)theEObject;
				T result = caseClassFigure(classFigure);
				if (result == null) result = caseAbstractFigure(classFigure);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GraphdescPackage.ABSTRACT_ATTRIBUTE_FIGURE: {
				AbstractAttributeFigure abstractAttributeFigure = (AbstractAttributeFigure)theEObject;
				T result = caseAbstractAttributeFigure(abstractAttributeFigure);
				if (result == null) result = caseAbstractFigure(abstractAttributeFigure);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GraphdescPackage.ATTRIBUTE_FIGURE: {
				AttributeFigure attributeFigure = (AttributeFigure)theEObject;
				T result = caseAttributeFigure(attributeFigure);
				if (result == null) result = caseAbstractAttributeFigure(attributeFigure);
				if (result == null) result = caseAbstractFigure(attributeFigure);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GraphdescPackage.RICH_ATTRIBUTE_FIGURE: {
				RichAttributeFigure richAttributeFigure = (RichAttributeFigure)theEObject;
				T result = caseRichAttributeFigure(richAttributeFigure);
				if (result == null) result = caseAbstractAttributeFigure(richAttributeFigure);
				if (result == null) result = caseAbstractFigure(richAttributeFigure);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GraphdescPackage.ABSTRACT_REFERENCE_FIGURE: {
				AbstractReferenceFigure abstractReferenceFigure = (AbstractReferenceFigure)theEObject;
				T result = caseAbstractReferenceFigure(abstractReferenceFigure);
				if (result == null) result = caseAbstractFigure(abstractReferenceFigure);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GraphdescPackage.REFERENCE_FIGURE: {
				ReferenceFigure referenceFigure = (ReferenceFigure)theEObject;
				T result = caseReferenceFigure(referenceFigure);
				if (result == null) result = caseAbstractReferenceFigure(referenceFigure);
				if (result == null) result = caseAbstractFigure(referenceFigure);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GraphdescPackage.RICH_REFERENCE_FIGURE: {
				RichReferenceFigure richReferenceFigure = (RichReferenceFigure)theEObject;
				T result = caseRichReferenceFigure(richReferenceFigure);
				if (result == null) result = caseAbstractReferenceFigure(richReferenceFigure);
				if (result == null) result = caseAbstractFigure(richReferenceFigure);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER: {
				DynamicPropertyOverrider dynamicPropertyOverrider = (DynamicPropertyOverrider)theEObject;
				T result = caseDynamicPropertyOverrider(dynamicPropertyOverrider);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GraphdescPackage.FILTER: {
				Filter filter = (Filter)theEObject;
				T result = caseFilter(filter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Class Figure</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Class Figure</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClassFigure(ClassFigure object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Attribute Figure</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Attribute Figure</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAttributeFigure(AttributeFigure object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rich Reference Figure</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rich Reference Figure</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRichReferenceFigure(RichReferenceFigure object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dynamic Property Overrider</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dynamic Property Overrider</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDynamicPropertyOverrider(DynamicPropertyOverrider object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Filter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Filter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFilter(Filter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Figure</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Figure</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractFigure(AbstractFigure object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Reference Figure</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Reference Figure</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractReferenceFigure(AbstractReferenceFigure object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reference Figure</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reference Figure</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReferenceFigure(ReferenceFigure object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Attribute Figure</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Attribute Figure</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractAttributeFigure(AbstractAttributeFigure object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rich Attribute Figure</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rich Attribute Figure</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRichAttributeFigure(RichAttributeFigure object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>GV Figure Description</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>GV Figure Description</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGVFigureDescription(GVFigureDescription object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //GraphdescSwitch
