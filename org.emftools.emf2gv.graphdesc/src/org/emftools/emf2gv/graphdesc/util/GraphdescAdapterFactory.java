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
package org.emftools.emf2gv.graphdesc.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.emftools.emf2gv.graphdesc.AbstractAttributeFigure;
import org.emftools.emf2gv.graphdesc.AbstractFigure;
import org.emftools.emf2gv.graphdesc.AbstractReferenceFigure;
import org.emftools.emf2gv.graphdesc.AttributeFigure;
import org.emftools.emf2gv.graphdesc.ClassFigure;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;
import org.emftools.emf2gv.graphdesc.ReferenceFigure;
import org.emftools.emf2gv.graphdesc.RichAttributeFigure;
import org.emftools.emf2gv.graphdesc.RichReferenceFigure;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.emftools.emf2gv.graphdesc.GraphdescPackage
 * @generated
 */
public class GraphdescAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static GraphdescPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphdescAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = GraphdescPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GraphdescSwitch<Adapter> modelSwitch =
		new GraphdescSwitch<Adapter>() {
			@Override
			public Adapter caseGVFigureDescription(GVFigureDescription object) {
				return createGVFigureDescriptionAdapter();
			}
			@Override
			public Adapter caseAbstractFigure(AbstractFigure object) {
				return createAbstractFigureAdapter();
			}
			@Override
			public Adapter caseClassFigure(ClassFigure object) {
				return createClassFigureAdapter();
			}
			@Override
			public Adapter caseAbstractAttributeFigure(AbstractAttributeFigure object) {
				return createAbstractAttributeFigureAdapter();
			}
			@Override
			public Adapter caseAttributeFigure(AttributeFigure object) {
				return createAttributeFigureAdapter();
			}
			@Override
			public Adapter caseRichAttributeFigure(RichAttributeFigure object) {
				return createRichAttributeFigureAdapter();
			}
			@Override
			public Adapter caseAbstractReferenceFigure(AbstractReferenceFigure object) {
				return createAbstractReferenceFigureAdapter();
			}
			@Override
			public Adapter caseReferenceFigure(ReferenceFigure object) {
				return createReferenceFigureAdapter();
			}
			@Override
			public Adapter caseRichReferenceFigure(RichReferenceFigure object) {
				return createRichReferenceFigureAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.emftools.emf2gv.graphdesc.ClassFigure <em>Class Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.emftools.emf2gv.graphdesc.ClassFigure
	 * @generated
	 */
	public Adapter createClassFigureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.emftools.emf2gv.graphdesc.AttributeFigure <em>Attribute Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.emftools.emf2gv.graphdesc.AttributeFigure
	 * @generated
	 */
	public Adapter createAttributeFigureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.emftools.emf2gv.graphdesc.RichReferenceFigure <em>Rich Reference Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.emftools.emf2gv.graphdesc.RichReferenceFigure
	 * @generated
	 */
	public Adapter createRichReferenceFigureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.emftools.emf2gv.graphdesc.AbstractFigure <em>Abstract Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.emftools.emf2gv.graphdesc.AbstractFigure
	 * @generated
	 */
	public Adapter createAbstractFigureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.emftools.emf2gv.graphdesc.AbstractReferenceFigure <em>Abstract Reference Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.emftools.emf2gv.graphdesc.AbstractReferenceFigure
	 * @generated
	 */
	public Adapter createAbstractReferenceFigureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.emftools.emf2gv.graphdesc.ReferenceFigure <em>Reference Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.emftools.emf2gv.graphdesc.ReferenceFigure
	 * @generated
	 */
	public Adapter createReferenceFigureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.emftools.emf2gv.graphdesc.AbstractAttributeFigure <em>Abstract Attribute Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.emftools.emf2gv.graphdesc.AbstractAttributeFigure
	 * @generated
	 */
	public Adapter createAbstractAttributeFigureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.emftools.emf2gv.graphdesc.RichAttributeFigure <em>Rich Attribute Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.emftools.emf2gv.graphdesc.RichAttributeFigure
	 * @generated
	 */
	public Adapter createRichAttributeFigureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.emftools.emf2gv.graphdesc.GVFigureDescription <em>GV Figure Description</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.emftools.emf2gv.graphdesc.GVFigureDescription
	 * @generated
	 */
	public Adapter createGVFigureDescriptionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //GraphdescAdapterFactory
