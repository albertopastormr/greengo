package business.service.as.imp;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;

import business.contract.as.imp.Contract;
import business.service.TService;

/**
* @author ...
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
@Entity
@NamedQueries({
		@NamedQuery(name = "Business.service.as.imp.service.findByid", query = "select obj from service obj where :id = obj.id "),
		@NamedQuery(name = "Business.service.as.imp.service.findBytype", query = "select obj from service obj where :type = obj.type "),
		@NamedQuery(name = "Business.service.as.imp.service.findByaddress", query = "select obj from service obj where :address = obj.address "),
		@NamedQuery(name = "Business.service.as.imp.service.findBycontract", query = "select obj from service obj where :contract MEMBER OF obj.contract "),
		@NamedQuery(name = "Business.service.as.imp.service.findBycapacity", query = "select obj from service obj where :capacity = obj.capacity "),
		@NamedQuery(name = "Business.service.as.imp.service.findByvehicles_attended", query = "select obj from service obj where :vehicles_attended = obj.vehicles_attended "),
		@NamedQuery(name = "Business.service.as.imp.service.findByactive", query = "select obj from service obj where :active = obj.active ") })
public class Service implements Serializable {
	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private static final long serialVersionUID = 0;

	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Service() {
	}

	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	@Id
	private Integer id;
	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private Class type;
	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private Class address;
	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	@OneToMany(mappedBy = "service")
	private Set<Contract> contract;
	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private Class capacity;
	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private Class vehicles_attended;
	/**
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private Class active;

	/**
	* @param ts
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Service(TService ts) {

	}
}