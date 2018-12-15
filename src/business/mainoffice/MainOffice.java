package business.mainoffice;

import javax.persistence.*;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import business.contract.Contract;
import business.employee.Employee;

@Entity
@NamedQueries({
		@NamedQuery(name = "MainOffice.findByid",
                query = "select obj from MainOffice obj where :id = obj.id "),
		@NamedQuery(name = "MainOffice.findByemployee",
                query = "select obj from MainOffice obj where :employee MEMBER OF obj.employee "),
		@NamedQuery(name = "MainOffice.findBycontract",
                query = "select obj from MainOffice obj where :contract MEMBER OF obj.contract "),
		@NamedQuery(name = "MainOffice.findByaddress",
                query = "select obj from MainOffice obj where :address = obj.address "),
		@NamedQuery(name = "MainOffice.findBycity",
                query = "select obj from MainOffice obj where :city = obj.city "),
		@NamedQuery(name = "MainOffice.findByActive",
                query = "select obj from MainOffice obj where :active = obj.active "),
		@NamedQuery(name = "Service.findAllMainOffices",
				query = "select obj from MainOffice obj ") })

public class MainOffice implements Serializable {

	private static final long serialVersionUID = 0;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Version
	private Integer version;

	@OneToMany(mappedBy = "MainOffice")
	private Collection<Employee> employee;

	@OneToMany(mappedBy = "MainOffice")
	private Collection<Contract> contract;

	private String address;

	private String city;

	private Boolean active;


	public MainOffice() {
	}

	public MainOffice(TMainOffice tm) {
		this.id = tm.getId();
		this.address = tm.getAddress();
		this.city = tm.getCity();
		this.active = tm.isActive();
		this.employee = new ArrayList<>();
		this.contract = new ArrayList<>();
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

	public Collection<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(Collection<Employee> employee) {
		this.employee = employee;
	}

	public Collection<Contract> getContract() {
		return contract;
	}

	public void setContract(Collection<Contract> contract) {
		this.contract = contract;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}