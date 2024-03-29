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
package org.emftools.emf2gv.graphdesc.impl;

import java.awt.Color;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.emftools.emf2gv.graphdesc.ArrowType;
import org.emftools.emf2gv.graphdesc.AttributeFigure;
import org.emftools.emf2gv.graphdesc.ClassFigure;
import org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider;
import org.emftools.emf2gv.graphdesc.EdgeStyle;
import org.emftools.emf2gv.graphdesc.Filter;
import org.emftools.emf2gv.graphdesc.FontStyle;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.graphdesc.GraphdescFactory;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;
import org.emftools.emf2gv.graphdesc.Orientation;
import org.emftools.emf2gv.graphdesc.ReferenceFigure;
import org.emftools.emf2gv.graphdesc.RichAttributeFigure;
import org.emftools.emf2gv.graphdesc.RichReferenceFigure;
import org.emftools.emf2gv.util.ColorsHelper;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class GraphdescFactoryImpl extends EFactoryImpl implements GraphdescFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static GraphdescFactory init() {
		try {
			GraphdescFactory theGraphdescFactory = (GraphdescFactory)EPackage.Registry.INSTANCE.getEFactory("http://org.emftools.emf2gv.graphdesc/1.1.0"); 
			if (theGraphdescFactory != null) {
				return theGraphdescFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new GraphdescFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphdescFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case GraphdescPackage.GV_FIGURE_DESCRIPTION: return createGVFigureDescription();
			case GraphdescPackage.CLASS_FIGURE: return createClassFigure();
			case GraphdescPackage.ATTRIBUTE_FIGURE: return createAttributeFigure();
			case GraphdescPackage.RICH_ATTRIBUTE_FIGURE: return createRichAttributeFigure();
			case GraphdescPackage.REFERENCE_FIGURE: return createReferenceFigure();
			case GraphdescPackage.RICH_REFERENCE_FIGURE: return createRichReferenceFigure();
			case GraphdescPackage.DYNAMIC_PROPERTY_OVERRIDER: return createDynamicPropertyOverrider();
			case GraphdescPackage.FILTER: return createFilter();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case GraphdescPackage.ORIENTATION:
				return createOrientationFromString(eDataType, initialValue);
			case GraphdescPackage.ARROW_TYPE:
				return createArrowTypeFromString(eDataType, initialValue);
			case GraphdescPackage.EDGE_STYLE:
				return createEdgeStyleFromString(eDataType, initialValue);
			case GraphdescPackage.FONT_STYLE:
				return createFontStyleFromString(eDataType, initialValue);
			case GraphdescPackage.COLOR:
				return createColorFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case GraphdescPackage.ORIENTATION:
				return convertOrientationToString(eDataType, instanceValue);
			case GraphdescPackage.ARROW_TYPE:
				return convertArrowTypeToString(eDataType, instanceValue);
			case GraphdescPackage.EDGE_STYLE:
				return convertEdgeStyleToString(eDataType, instanceValue);
			case GraphdescPackage.FONT_STYLE:
				return convertFontStyleToString(eDataType, instanceValue);
			case GraphdescPackage.COLOR:
				return convertColorToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassFigure createClassFigure() {
		ClassFigureImpl classFigure = new ClassFigureImpl();
		return classFigure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttributeFigure createAttributeFigure() {
		AttributeFigureImpl attributeFigure = new AttributeFigureImpl();
		return attributeFigure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RichReferenceFigure createRichReferenceFigure() {
		RichReferenceFigureImpl richReferenceFigure = new RichReferenceFigureImpl();
		return richReferenceFigure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DynamicPropertyOverrider createDynamicPropertyOverrider() {
		DynamicPropertyOverriderImpl dynamicPropertyOverrider = new DynamicPropertyOverriderImpl();
		return dynamicPropertyOverrider;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Filter createFilter() {
		FilterImpl filter = new FilterImpl();
		return filter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceFigure createReferenceFigure() {
		ReferenceFigureImpl referenceFigure = new ReferenceFigureImpl();
		return referenceFigure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RichAttributeFigure createRichAttributeFigure() {
		RichAttributeFigureImpl richAttributeFigure = new RichAttributeFigureImpl();
		return richAttributeFigure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GVFigureDescription createGVFigureDescription() {
		GVFigureDescriptionImpl gvFigureDescription = new GVFigureDescriptionImpl();
		return gvFigureDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Orientation createOrientationFromString(EDataType eDataType, String initialValue) {
		Orientation result = Orientation.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOrientationToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrowType createArrowTypeFromString(EDataType eDataType, String initialValue) {
		ArrowType result = ArrowType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertArrowTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EdgeStyle createEdgeStyleFromString(EDataType eDataType, String initialValue) {
		EdgeStyle result = EdgeStyle.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEdgeStyleToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FontStyle createFontStyleFromString(EDataType eDataType, String initialValue) {
		FontStyle result = FontStyle.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertFontStyleToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Color createColorFromString(EDataType eDataType, String initialValue) {
		return ColorsHelper.parseHtmlColor(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String convertColorToString(EDataType eDataType, Object instanceValue) {
		return ColorsHelper.toHtmlColor((Color) instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphdescPackage getGraphdescPackage() {
		return (GraphdescPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static GraphdescPackage getPackage() {
		return GraphdescPackage.eINSTANCE;
	}

} //GraphdescFactoryImpl
