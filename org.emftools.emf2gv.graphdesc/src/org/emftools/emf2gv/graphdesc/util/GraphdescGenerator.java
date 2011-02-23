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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.emftools.emf2gv.graphdesc.ArrowType;
import org.emftools.emf2gv.graphdesc.AttributeFigure;
import org.emftools.emf2gv.graphdesc.ClassFigure;
import org.emftools.emf2gv.graphdesc.GVFigureDescription;
import org.emftools.emf2gv.graphdesc.GraphdescFactory;
import org.emftools.emf2gv.graphdesc.ReferenceFigure;
import org.emftools.emf2gv.util.ColorsHelper;

/**
 * Utility class allowing to generate a default graphical description from an
 * EPackage list.
 * 
 * The default generation algorithm does not process the non containment
 * references features.
 */
public class GraphdescGenerator {

	/** Predefined colors tu use in the class figures */
	private static List<Integer> predefinedColors = Arrays
			.asList(new Integer[] { // Predefined colors
			0xD0BCFE, // Blue violet
					0x9DBDF9, // Blue
					0x98FB98, // Pale green
					0xFFF575, // Yellow
					0xE3D6AA, // Brown
					0xFFC895, // Orange
					0xFA8072, // Salmon
					0xFFC8C8 // Red

			});

	/**
	 * Generates a graphical description from an EPackage list.
	 * 
	 * @param ePackages
	 *            the EPackage list to process.
	 * @return the generated graphical description.
	 */
	public static GVFigureDescription createGVFigureDescription(
			List<EPackage> ePackages) {
		GVFigureDescription gvFigDesc = GraphdescFactory.eINSTANCE
				.createGVFigureDescription();
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
					// The reference is processed only when it is a containment
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
		addEAttributesAndAppearanceStyleData(gvFigDesc);
		return gvFigDesc;
	}

	/**
	 * Add to a graphical description the attribute figures and several
	 * appearance information (colors, ...).
	 * 
	 * @param gvFigDesc
	 *            the graphical description.
	 */
	public static void addEAttributesAndAppearanceStyleData(
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
			int baseColor = predefinedColors.get(predefinedColorIdx);
			predefinedColorIdx++;
			// ClassFigure colors setting
			classFigure.setHeaderBackgroundColor(baseColor);
			classFigure.setBodyBackgroundColor(ColorsHelper
					.makeColorBrighter(baseColor));
			/*
			 * Label attribute generation
			 */
			List<EAttribute> eAttributes = eClass.getEAllAttributes();
			EAttribute labelEAttribute = null;
			if (eAttributes.size() > 0) {
				labelEAttribute = eAttributes.get(0);
				classFigure.setLabelEAttribute(labelEAttribute);
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
			for (ReferenceFigure refFigure : classFigure.getReferenceFigures()) {
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

}
