/**
 * 
 */
package business.employee.as.imp;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import business.mainoffice.as.imp.MainOffice;
import business.employee.TEmployee;

import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;


/**
* @author ...
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
@Entity
@NamedQueries({
		@NamedQuery(name = "Business.employee.as.imp.employee.findByid", query = "select obj from employee obj where :id = obj.id "),
		@NamedQuery(name = "Business.employee.as.imp.employee.findBymain_Office", query = "select obj from employee obj where :main_Office = obj.main_Office "),
		@NamedQuery(name = "Business.employee.as.imp.employee.findByid_card_number", query = "select obj from employee obj where :id_card_number = obj.id_card_number "),
		@NamedQuery(name = "Business.employee.as.imp.employee.findBysalary", query = "select obj from employee obj where :salary = obj.salary "),
		@NamedQuery(name = "Business.employee.as.imp.employee.findByactive", query = "select obj from employee obj where :active = obj.active ") })
public class Employee implements Serializable {
	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private static final long serialVersionUID = 0;

	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Employee() {
	}

	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	@Id
	private Integer id;
	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	@ManyToOne
	private MainOffice main_Office;
	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private Class id_card_number;
	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private Class salary;
	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private Class active;

	/**
	* @param te
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Employee(TEmployee te) {

	}

	/**
	* @param active
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setActive(Class active) {

	}
}