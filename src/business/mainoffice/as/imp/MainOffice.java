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
import business.contract.as.imp.Contract;
import business.employee.as.imp.Employee;
import business.mainoffice.TMainOffice;

/**
* @author ...
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
@Entity
@NamedQueries({
		@NamedQuery(name = "Business.MainOffice.as.imp.MainOffice.findByid", query = "select obj from MainOffice obj where :id = obj.id "),
		@NamedQuery(name = "Business.MainOffice.as.imp.MainOffice.findByemployee", query = "select obj from MainOffice obj where :employee MEMBER OF obj.employee "),
		@NamedQuery(name = "Business.MainOffice.as.imp.MainOffice.findBycontract", query = "select obj from MainOffice obj where :contract MEMBER OF obj.contract "),
		@NamedQuery(name = "Business.MainOffice.as.imp.MainOffice.findByaddress", query = "select obj from MainOffice obj where :address = obj.address "),
		@NamedQuery(name = "Business.MainOffice.as.imp.MainOffice.findBycity", query = "select obj from MainOffice obj where :city = obj.city "),
		@NamedQuery(name = "Business.MainOffice.as.imp.MainOffice.findByactive", query = "select obj from MainOffice obj where :active = obj.active ") })
public class MainOffice implements Serializable {
	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private static final long serialVersionUID = 0;

	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public MainOffice() {
	}

	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	@Id
	private Integer id;
	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	@OneToMany(mappedBy = "mainOffice")
	private Set<Employee> employee;
	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	@OneToMany(mappedBy = "mainOffice")
	private Set<Contract> contract;
	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private Class address;
	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private Class city;
	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private Class active;

	/**
	* @param tm
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public MainOffice(TMainOffice tm) {

	}

	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void getEmployees() {

	}

	/**
	* @param active
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setActive(Class active) {

	}
}