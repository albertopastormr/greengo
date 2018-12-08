package business.service;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import business.contract.Contract;

@Entity
@NamedQueries({
		@NamedQuery(name = "Service.findByid",
                query = "select obj from Service obj where :id = obj.id "),
		@NamedQuery(name = "Service.findBytype",
                query = "select obj from Service obj where :type = obj.type "),
		@NamedQuery(name = "Service.findByaddress",
                query = "select obj from Service obj where :address = obj.address "),
		@NamedQuery(name = "Service.findBycontract",
                query = "select obj from Service obj where :contract MEMBER OF obj.contract "),
		@NamedQuery(name = "Service.findBycapacity",
                query = "select obj from Service obj where :capacity = obj.capacity "),
		@NamedQuery(name = "Service.findByvehicles_attended",
                query = "select obj from Service obj where :vehicles_attended = obj.numVehiclesAttended "),
		@NamedQuery(name = "Service.findByActive",
                query = "select obj from Service obj where :active = obj.active "),
		@NamedQuery(name = "Service.findAllServices",
				query = "select obj from Service obj ") })
public class Service implements Serializable {

	private static final long serialVersionUID = 0;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Version
	private Integer version;

	private String type;

	private String address;

	@OneToMany(mappedBy = "Service")
	private Collection<Contract> contract;

	private Integer capacity;

	private Integer numVehiclesAttended;

	private Boolean active;

	public Service() {
	}

    public Service(TService ts) {
        this.id = ts.getId();
        this.type = ts.getType();
        this.address = ts.getAddress();
        this.contract = new ArrayList<>();
        this.capacity = ts.getCapacity();
        this.numVehiclesAttended = ts.getNumVehiclesAttended();
        this.active = ts.isActive();
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

	public Collection<Contract> getContract() {
		return contract;
	}

	public void setContract(Collection<Contract> contract) {
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

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}