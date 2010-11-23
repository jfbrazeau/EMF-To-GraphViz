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
package org.emftools.emf2gv.graphdesc.presentation.util;

import java.util.Map;

import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;

public class GraphdescPropertySource extends PropertySource {

	private Map<Integer, Image> colorIcons;

	public GraphdescPropertySource(Object object,
			IItemPropertySource itemPropertySource,
			Map<Integer, Image> colorIcons) {
		super(object, itemPropertySource);
		this.colorIcons = colorIcons;
	}

	protected IPropertyDescriptor createPropertyDescriptor(
			IItemPropertyDescriptor itemPropertyDescriptor) {
		IPropertyDescriptor result = null;
		GraphdescPackage gdPkg = GraphdescPackage.eINSTANCE;
		Object feature = itemPropertyDescriptor.getFeature(object);
		boolean arrowTypeFeature = (feature == gdPkg
				.getReferenceFigure_SourceArrowType() || feature == gdPkg
				.getReferenceFigure_TargetArrowType());
		boolean colorFeature = (feature == gdPkg
				.getClassFigure_HeaderBackgroundColor() || feature == gdPkg
				.getClassFigure_BodyBackgroundColor());
		if (arrowTypeFeature) {
			result = new ArrowTypePropertyDescriptor(object,
					itemPropertyDescriptor);
		} else if (colorFeature) {
			result = new ColorPropertyDescriptor(object,
					itemPropertyDescriptor, colorIcons);
		} else {
			result = super.createPropertyDescriptor(itemPropertyDescriptor);
		}
		return result;
	}

}