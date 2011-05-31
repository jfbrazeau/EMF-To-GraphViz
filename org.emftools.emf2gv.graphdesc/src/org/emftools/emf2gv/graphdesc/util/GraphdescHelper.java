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

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.ocl.Environment;
import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.ecore.CallOperationAction;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreFactory;
import org.eclipse.ocl.ecore.SendSignalAction;
import org.eclipse.ocl.ecore.Variable;
import org.emftools.emf2gv.graphdesc.AbstractAttributeFigure;
import org.emftools.emf2gv.graphdesc.AbstractReferenceFigure;
import org.emftools.emf2gv.graphdesc.ArrowType;
import org.emftools.emf2gv.graphdesc.AttributeFigure;
import org.emftools.emf2gv.graphdesc.ClassFigure;
import org.emftools.emf2gv.graphdesc.DynamicPropertyOverrider;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.graphdesc.GraphdescFactory;
import org.emftools.emf2gv.graphdesc.ReferenceFigure;
import org.emftools.emf2gv.graphdesc.RichAttributeFigure;
import org.emftools.emf2gv.graphdesc.RichReferenceFigure;
import org.emftools.emf2gv.util.ColorsHelper;
import org.emftools.emf2gv.util.OCLHelper;

/**
 * Utility class allowing to generate a default graphical description from an
 * EPackage list.
 * 
 * The default generation algorithm does not process the non containment
 * references features.
 */
public class GraphdescHelper {

	/** Predefined colors tu use in the class figures */
	private static List<Color> predefinedColors = Arrays.asList(new Color[] { // Predefined
																				// colors
					new Color(0xD0BCFE), // Blue violet
					new Color(0x9DBDF9), // Blue
					new Color(0x98FB98), // Pale green
					new Color(0xFFF575), // Yellow
					new Color(0xE3D6AA), // Brown
					new Color(0xFFC895), // Orange
					new Color(0xFA8072), // Salmon
					new Color(0xFFC8C8) // Red

			});

	/**
	 * The "default property value" variable name.
	 */
	private static final String DEFAULT_PROPERTY_VALUE_VARIABLE_NAME = "defaultPropertyValue";

	/**
	 * Generates a graphical description from an EPackage list.
	 * 
	 * @param ePackages
	 *            the EPackage list to process.
	 * @return the generated graphical description.
	 */
	public static GVFigureDescription createGVFigureDescription(
			List<EPackage> ePackages) {
		GVFigureDescription gvFigDesc = null;
		// If the EPackage list only contains on EPackage and if that
		// package is the Ecore one, the default graphical description
		// that is used is the one
		if (ePackages.size() == 1
				&& ePackages.get(0).equals(EcorePackage.eINSTANCE)) {
			gvFigDesc = getSampleGraphdescForEcoreModels();
		}
		// Else a default graphical description is generated
		else {
			gvFigDesc = GraphdescFactory.eINSTANCE.createGVFigureDescription();
			gvFigDesc.getEPackages().addAll(ePackages);
			List<EClass> eClasses = new ArrayList<EClass>();
			// Class figures creation
			// int predefinedColorIdx = 0;
			for (EPackage ePackage : ePackages) {
				for (EClassifier eClassifier : ePackage.getEClassifiers()) {
					if (eClassifier instanceof EClass) {
						EClass eClass = (EClass) eClassifier;
						eClasses.add(eClass);
						ClassFigure classFigure = GraphdescFactory.eINSTANCE
								.createClassFigure();
						classFigure.setEClass(eClass);
						gvFigDesc.getClassFigures().add(classFigure);

					}
				}
			}
			// Reference figures creation
			for (EClass eClass : eClasses) {
				ClassFigure classFigure = gvFigDesc.getClassFigure(eClass);
				for (EReference eReference : eClass.getEAllReferences()) {
					// The reference is processed only if the target EClass
					// belongs to the authorized EPackage list
					if (ePackages.contains(eReference.getEReferenceType()
							.getEPackage())) {
						// The reference is processed only when it is a
						// containment
						// reference
						if (eReference.isContainment()) {
							ReferenceFigure refFigure = GraphdescFactory.eINSTANCE
									.createReferenceFigure();
							refFigure.setEReference(eReference);
							classFigure.getReferenceFigures().add(refFigure);
						}
					}
				}
			}
			// Add Eattributes and appearance data
			populateEAttributesAndAppearanceStyleData(gvFigDesc);
		}

		return gvFigDesc;
	}

	/**
	 * @return a sample graphical description for ecore models.
	 */
	public static GVFigureDescription getSampleGraphdescForEcoreModels() {
		// Load the model resource
		ResourceSet rs = new ResourceSetImpl();
		rs.getResourceFactoryRegistry()
				.getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
						new XMIResourceFactoryImpl());
		// Load the graphical description
		Resource graphDescResource = rs.getResource(URI
				.createURI(GraphdescHelper.class.getResource("ecore.graphdesc")
						.toString(), true), true);
		return (GVFigureDescription) graphDescResource.getContents().get(0);
	}

	/**
	 * Add to a graphical description the attribute figures and several
	 * appearance information (colors, ...).
	 * 
	 * @param gvFigDesc
	 *            the graphical description.
	 */
	public static void populateEAttributesAndAppearanceStyleData(
			GVFigureDescription gvFigDesc) {
		int predefinedColorIdx = 0;
		for (ClassFigure classFigure : gvFigDesc.getClassFigures()) {
			EClass eClass = classFigure.getEClass();
			/*
			 * Base color selection
			 */
			if (predefinedColorIdx >= predefinedColors.size()) {
				predefinedColorIdx = 0;
			}
			Color baseColor = predefinedColors.get(predefinedColorIdx);
			predefinedColorIdx++;
			// ClassFigure colors setting
			;
			classFigure.setHeaderBackgroundColor(baseColor);
			classFigure.setBodyBackgroundColor(ColorsHelper
					.makeColorBrighter(baseColor));
			/*
			 * Label attribute selection
			 */
			List<EAttribute> eAttributes = eClass.getEAllAttributes();
			EAttribute labelEAttribute = null;
			if (eAttributes.size() > 0) {
				for (Iterator<EAttribute> iterator = eAttributes.iterator(); iterator
						.hasNext();) {
					EAttribute eAttribute = iterator.next();
					if (String.class.equals(eAttribute.getEAttributeType()
							.getInstanceClass())) {
						labelEAttribute = eAttribute;
						break;
					}
				}
				if (labelEAttribute != null) {
					classFigure.setLabelEAttribute(labelEAttribute);
				}
			}

			/*
			 * Attribute figures generation
			 */
			for (EAttribute eAttribute : eClass.getEAllAttributes()) {
				if (!eAttribute.equals(labelEAttribute)
						&& !eAttribute.isDerived()) {
					AttributeFigure attributeFigure = GraphdescFactory.eINSTANCE
							.createAttributeFigure();
					attributeFigure.setEAttribute(eAttribute);
					classFigure.getAttributeFigures().add(attributeFigure);
				}
			}

			/*
			 * Reference figure style
			 */
			for (AbstractReferenceFigure refFigure : classFigure
					.getReferenceFigures()) {
				EReference eReference = refFigure.getEReference();
				if (refFigure.isContainment()) {
					refFigure.setSourceArrowType(ArrowType.DIAMOND);
				}
				EReference eOpposite = eReference.getEOpposite();
				if (eOpposite != null) {
					refFigure
							.setTargetArrowType(eOpposite != null ? ArrowType.NONE
									: ArrowType.NORMAL);
				}
			}
		}
	}

	/**
	 * Return the possible EClasses that can be filtered for the given graphical
	 * description.
	 * 
	 * @param figureDescription
	 *            the graphical description.
	 * @return the EClasses list.
	 */
	public static List<EClass> getFilterableEClasses(
			GVFigureDescription figureDescription) {
		// EClass hierarchy retrieval
		List<EClass> eClasses = new ArrayList<EClass>();
		EList<ClassFigure> classFigures = figureDescription.getClassFigures();
		for (ClassFigure classFigure : classFigures) {
			registerSuperTypes(classFigure.getEClass(), eClasses);
			// Retrieve the EClass targeted by the rich references (i.e.
			// the association EClasses)
			EList<AbstractReferenceFigure> abstractReferenceFigures = classFigure
					.getReferenceFigures();
			for (AbstractReferenceFigure abstractReferenceFigure : abstractReferenceFigures) {
				if (abstractReferenceFigure instanceof RichReferenceFigure) {
					RichReferenceFigure richReferenceFigure = (RichReferenceFigure) abstractReferenceFigure;
					registerSuperTypes(richReferenceFigure.getEReference()
							.getEReferenceType(), eClasses);
				}
			}
			// Retrieve the EClass targeted by the rich attributes
			EList<AbstractAttributeFigure> abstractAttributeFigures = classFigure
					.getAttributeFigures();
			for (AbstractAttributeFigure abstractAttributeFigure : abstractAttributeFigures) {
				if (abstractAttributeFigure instanceof RichAttributeFigure) {
					RichAttributeFigure richAttributeFigure = (RichAttributeFigure) abstractAttributeFigure;
					registerSuperTypes(richAttributeFigure.getEReference()
							.getEReferenceType(), eClasses);
				}
			}
		}
		return eClasses;
	}

	/**
	 * Registers the EClass super types in the list.
	 * 
	 * @param context
	 *            the EClass.
	 * @param eClasses
	 *            the EClass list.
	 */
	private static void registerSuperTypes(EClass eClass, List<EClass> eClasses) {
		if (!eClasses.contains(eClass)) {
			eClasses.add(eClass);
			for (EClass superType : eClass.getESuperTypes()) {
				registerSuperTypes(superType, eClasses);
			}
		}
	}

	/**
	 * Registers the variable that is used to retrieve the default property
	 * value in an property overrider OCL expression.
	 * 
	 * @param environment
	 *            the OCL environment.
	 * @param dpo
	 *            the dynamic property overrider.
	 * @see GraphdescHelper#assignDefaultPropertyValueOCLVariable(EvaluationEnvironment,
	 *      Object)
	 */
	public static void registerDefaultPropertyValueOCLVariable(
			Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> environment,
			DynamicPropertyOverrider dpo) {
		EClassifier defaultPropertyValueVariableType = OCLHelper
				.toOCLFeatureType(environment, dpo.getPropertyToOverride());
		// "Default value" variable registering (this variable
		// is used to give access to the default value of the
		// property that is overrided).
		Variable variable = EcoreFactory.eINSTANCE.createVariable();
		variable.setEType(defaultPropertyValueVariableType);
		variable.setType(defaultPropertyValueVariableType);
		variable.setName(DEFAULT_PROPERTY_VALUE_VARIABLE_NAME);
		// Deregister the variable
		environment.deleteElement(variable.getName());
		// Register the new variable
		environment.addElement(variable.getName(), variable, true);
	}

	/**
	 * Assigns the value to the "default property value" variable.
	 * 
	 * @param evaluationEvnironment
	 *            the evaluation environment.
	 * @param value
	 *            the variable's value.
	 * @see GraphdescHelper#registerDefaultPropertyValueOCLVariable(Environment,
	 *      DynamicPropertyOverrider)
	 */
	public static void assignDefaultPropertyValueOCLVariable(
			EvaluationEnvironment<EClassifier, EOperation, EStructuralFeature, EClass, EObject> evaluationEvnironment,
			Object value) {
		evaluationEvnironment.add(DEFAULT_PROPERTY_VALUE_VARIABLE_NAME, value);
	}

}
