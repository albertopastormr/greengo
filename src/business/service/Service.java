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
                query = "select obj from service obj where :id = obj.id "),
		@NamedQuery(name = "Service.findBytype",
                query = "select obj from service obj where :type = obj.type "),
		@NamedQuery(name = "Service.findByaddress",
                query = "select obj from service obj where :address = obj.address "),
		@NamedQuery(name = "Service.findBycontract",
                query = "select obj from service obj where :contract MEMBER OF obj.contract "),
		@NamedQuery(name = "Service.findBycapacity",
                query = "select obj from service obj where :capacity = obj.capacity "),
		@NamedQuery(name = "Service.findByvehicles_attended",
                query = "select obj from service obj where :vehicles_attended = obj.vehicles_attended "),
		@NamedQuery(name = "Service.findByactive",
                query = "select obj from service obj where :active = obj.active ") })
public class Service implements Serializable {

	private static final long serialVersionUID = 0;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}