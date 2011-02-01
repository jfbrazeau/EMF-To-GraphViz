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
package org.emftools.emf2gv.graphdesc.provider.util;

import java.net.URL;

import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.emftools.emf2gv.graphdesc.ArrowType;
import org.emftools.emf2gv.graphdesc.provider.GraphdescEditPlugin;
import org.osgi.framework.Bundle;

/**
 * ArrowType item label provider.
 * 
 * Manages the arrow types icons.
 */
// TODO Javadoc
public class ArrowTypeItemLabelProvider implements IItemLabelProvider {

	/**
	 * Arrow types categories enumeration.
	 */
	public static enum Category {
		Source, Target
	}

	private Category category;

	public ArrowTypeItemLabelProvider(Category category) {
		this.category = category;
	}

	@Override
	public String getText(Object object) {
		ArrowType arrowType = (ArrowType) object;
		return arrowType.getName();
	}

	@Override
	public Object getImage(Object object) {
		ArrowType arrowType = (ArrowType) object;
		Bundle bundle = GraphdescEditPlugin.getPlugin().getBundle();
		URL url = bundle.getResource("/icons/full/obj16/arrows/"
				+ category.toString().toLowerCase() + '/'
				+ arrowType.toString().toLowerCase() + ".png");
		return url;
	}

}
