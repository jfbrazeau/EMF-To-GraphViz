package org.emftools.samples.school;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * @model
 */
public interface Classroom extends EObject {

	/**
	 * @model
	 */
	String getName();


	/**
	 * Sets the value of the '{@link org.emftools.samples.school.Classroom#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);


	/**
	 * @model containment="true"
	 */
	EList<Student> getStudents();

	/**
	 * Returns the value of the '<em><b>Capacity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Capacity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Capacity</em>' attribute.
	 * @see #setCapacity(int)
	 * @see org.emftools.samples.school.SchoolPackage#getClassroom_Capacity()
	 * @model
	 * @generated
	 */
	int getCapacity();


	/**
	 * Sets the value of the '{@link org.emftools.samples.school.Classroom#getCapacity <em>Capacity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Capacity</em>' attribute.
	 * @see #getCapacity()
	 * @generated
	 */
	void setCapacity(int value);


	/**
	 * Returns the value of the '<em><b>Teacher</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Teacher</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Teacher</em>' attribute.
	 * @see #setTeacher(String)
	 * @see org.emftools.samples.school.SchoolPackage#getClassroom_Teacher()
	 * @model
	 * @generated
	 */
	String getTeacher();


	/**
	 * Sets the value of the '{@link org.emftools.samples.school.Classroom#getTeacher <em>Teacher</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Teacher</em>' attribute.
	 * @see #getTeacher()
	 * @generated
	 */
	void setTeacher(String value);


	/**
	 * Returns the value of the '<em><b>Rank</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rank</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rank</em>' attribute.
	 * @see #setRank(int)
	 * @see org.emftools.samples.school.SchoolPackage#getClassroom_Rank()
	 * @model
	 * @generated
	 */
	int getRank();


	/**
	 * Sets the value of the '{@link org.emftools.samples.school.Classroom#getRank <em>Rank</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rank</em>' attribute.
	 * @see #getRank()
	 * @generated
	 */
	void setRank(int value);


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean validate(DiagnosticChain diagnostic, Map<Object, Object> context);

}
