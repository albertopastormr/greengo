/**
 * 
 */
package business.employee.as.imp;

import business.employee.TTemporaryEmployee;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author gpros
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
@Entity
@NamedQuery(name = "Business.employee.as.imp.Temporary.findByid", query = "select obj from Temporary obj where :id = obj.id ")
public class Temporary extends Employee implements Serializable {
	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private static final long serialVersionUID = 0;

	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Temporary() {
	}

	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	@Id
	private Integer id;

	/**
	* @param tt
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Temporary(TTemporaryEmployee tt) {

	}
}