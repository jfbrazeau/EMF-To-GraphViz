/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.emftools.samples.school.impl;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.emftools.samples.school.Classroom;
import org.emftools.samples.school.School;
import org.emftools.samples.school.SchoolFactory;
import org.emftools.samples.school.SchoolPackage;
import org.emftools.samples.school.Student;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SchoolFactoryImpl extends EFactoryImpl implements SchoolFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SchoolFactory init() {
		try {
			SchoolFactory theSchoolFactory = (SchoolFactory)EPackage.Registry.INSTANCE.getEFactory("http://org.emftools.samples.school/1.0"); 
			if (theSchoolFactory != null) {
				return theSchoolFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SchoolFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SchoolFactoryImpl() {
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
			case SchoolPackage.CLASSROOM: return createClassroom();
			case SchoolPackage.SCHOOL: return createSchool();
			case SchoolPackage.STUDENT: return createStudent();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Classroom createClassroom() {
		ClassroomImpl classroom = new ClassroomImpl();
		return classroom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public School createSchool() {
		SchoolImpl school = new SchoolImpl();
		return school;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Student createStudent() {
		StudentImpl student = new StudentImpl();
		return student;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SchoolPackage getSchoolPackage() {
		return (SchoolPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static SchoolPackage getPackage() {
		return SchoolPackage.eINSTANCE;
	}

} //SchoolFactoryImpl
