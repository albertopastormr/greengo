package business.service;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

import business.contract.Contract;

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

	private static final long serialVersionUID = 0;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String type;

	private String address;

	@OneToMany(mappedBy = "service")
	private Set<Contract> contract;

	private Integer capacity;

	private Integer numVehiclesAttended;

	private Boolean active;

	public Service() {
	}

	public Service(TService ts) {

	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Contract> getContract() {
		return contract;
	}

	public void setContract(Set<Contract> contract) {
		this.contract = contract;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Integer getNumVehiclesAttended() {
		return numVehiclesAttended;
	}

	public void setNumVehiclesAttended(Integer numVehiclesAttended) {
		this.numVehiclesAttended = numVehiclesAttended;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}