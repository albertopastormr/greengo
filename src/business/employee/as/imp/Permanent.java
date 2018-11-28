/**
 * 
 */
package business.employee.as.imp;


import business.employee.TPermanentEmployee;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
* @author -.-
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
@Entity
@NamedQuery(name = "Business.employee.as.imp.Permanent.findByid", query = "select obj from Permanent obj where :id = obj.id ")
public class Permanent extends Employee implements Serializable {
	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private static final long serialVersionUID = 0;

	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Permanent() {
	}

	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	@Id
	private Integer id;

	/**
	* @param tp
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Permanent(TPermanentEmployee tp) {

	}
}