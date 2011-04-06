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
package org.emftools.emf2gv.processor.core;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.ocl.ecore.Constraint;

/**
 * POJO class containing an EClass and an associated OCL filter constraint.
 * This class is not intended to be used outside of this package.
 * @author jbrazeau
 */
final class OCLFilterConstraint {
	
	/** The EClass to which must be applied the OCL filter constraint */
	private EClass eClass;

	/** The OCL filter constraint */
	private Constraint constraint;

	/**
	 * @return the EClass to which must be applied the OCL filter constraint.
	 */
	public EClass getEClass() {
		return eClass;
	}

	/**
	 * Sets the EClass to which must be applied the OCL filter constraint.
	 * @param eClass the new eClass.
	 */
	public void setEClass(EClass eClass) {
		this.eClass = eClass;
	}

	/**
	 * @return the OCL constraint.
	 */
	public Constraint getConstraint() {
		return constraint;
	}

	/**
	 * Sets the OCL constraint.
	 * @param constraint the new constraint.
	 */
	public void setConstraint(Constraint expression) {
		this.constraint = expression;
	}
	
}
