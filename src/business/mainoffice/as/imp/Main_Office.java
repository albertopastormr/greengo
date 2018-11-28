/**
 * 
 */
package business.mainoffice.as.imp;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.NamedQueries;
import business.contract.imp.Contract;
import business.employee.as.imp.Employee;
import business.mainoffice.TMainOffice;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author gpros
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
@Entity
@NamedQueries({
		@NamedQuery(name = "Business.Main_Office.as.imp.Main_Office.findByid", query = "select obj from Main_Office obj where :id = obj.id "),
		@NamedQuery(name = "Business.Main_Office.as.imp.Main_Office.findByemployee", query = "select obj from Main_Office obj where :employee MEMBER OF obj.employee "),
		@NamedQuery(name = "Business.Main_Office.as.imp.Main_Office.findBycontract", query = "select obj from Main_Office obj where :contract MEMBER OF obj.contract "),
		@NamedQuery(name = "Business.Main_Office.as.imp.Main_Office.findByaddress", query = "select obj from Main_Office obj where :address = obj.address "),
		@NamedQuery(name = "Business.Main_Office.as.imp.Main_Office.findBycity", query = "select obj from Main_Office obj where :city = obj.city "),
		@NamedQuery(name = "Business.Main_Office.as.imp.Main_Office.findByactive", query = "select obj from Main_Office obj where :active = obj.active ") })
public class Main_Office implements Serializable {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private static final long serialVersionUID = 0;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Main_Office() {
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	@Id
	private Integer id;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	@OneToMany(mappedBy = "main_Office")
	private Set<Employee> employee;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	@OneToMany(mappedBy = "main_Office")
	private Set<Contract> contract;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private Class address;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private Class city;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private Class active;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param tm
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Main_Office(TMainOffice tm) {
		// begin-user-code
		// TODO Auto-generated constructor stub
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void getEmployees() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param active
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setActive(Class active) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}
}