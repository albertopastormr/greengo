/**
 * 
 */
package business.contract.imp;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import business.mainoffice.as.imp.Main_Office;
import business.contract.TContract;
import business.service.as.imp.Service;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author gpros
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
@Entity
@NamedQueries({
		@NamedQuery(name = "Business.contract.as.imp.contract.findByid", query = "select obj from contract obj where :id = obj.id "),
		@NamedQuery(name = "Business.contract.as.imp.contract.findByservice", query = "select obj from contract obj where :service = obj.service "),
		@NamedQuery(name = "Business.contract.as.imp.contract.findBymain_Office", query = "select obj from contract obj where :main_Office = obj.main_Office "),
		@NamedQuery(name = "Business.contract.as.imp.contract.findByservice_level", query = "select obj from contract obj where :service_level = obj.service_level "),
		@NamedQuery(name = "Business.contract.as.imp.contract.findByactive", query = "select obj from contract obj where :active = obj.active ") })
public class Contract implements Serializable {
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
	public Contract() {
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
	@ManyToOne
	private Service service;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	@ManyToOne
	private Main_Office main_Office;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private Class service_level;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private Class active;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param tc
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Contract(TContract tc) {
		// begin-user-code
		// TODO Auto-generated constructor stub
		// end-user-code
	}
}