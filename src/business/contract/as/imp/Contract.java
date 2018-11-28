/**
 * 
 */
package business.contract.as.imp;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import business.mainoffice.as.imp.MainOffice;
import business.contract.TContract;
import business.service.as.imp.Service;

/**
* @author ...
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
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private static final long serialVersionUID = 0;

	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Contract() {
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
	private Service service;
	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	@ManyToOne
	private MainOffice main_Office;
	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private Class service_level;
	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private Class active;

	/**
	* @param tc
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Contract(TContract tc) {

	}
}