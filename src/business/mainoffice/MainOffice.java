/**
 * 
 */
package business.mainoffice;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.NamedQueries;
import business.contract.Contract;
import business.employee.Employee;

/**
* @author ...
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
@Entity
@NamedQueries({
		@NamedQuery(name = "Business.MainOffice.as.imp.MainOffice.findByid", query = "select obj from MainOffice obj where :id = obj.id "),
		@NamedQuery(name = "Business.MainOffice.as.imp.MainOffice.findByemployee", query = "select obj from MainOffice obj where :employee MEMBER OF obj.employee "),
		@NamedQuery(name = "Business.MainOffice.as.imp.MainOffice.findBycontract", query = "select obj from MainOffice obj where :contract MEMBER OF obj.contract "),
		@NamedQuery(name = "Business.MainOffice.as.imp.MainOffice.findByaddress", query = "select obj from MainOffice obj where :address = obj.address "),
		@NamedQuery(name = "Business.MainOffice.as.imp.MainOffice.findBycity", query = "select obj from MainOffice obj where :city = obj.city "),
		@NamedQuery(name = "Business.MainOffice.as.imp.MainOffice.findByactive", query = "select obj from MainOffice obj where :active = obj.active ") })
public class MainOffice implements Serializable {

	private static final long serialVersionUID = 0;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@OneToMany(mappedBy = "mainOffice")
	private Set<Employee> employee;

	@OneToMany(mappedBy = "mainOffice")
	private Set<Contract> contract;

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
		this.employee = new HashSet<>();
		this.contract = new HashSet<>();
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

	public Set<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(Set<Employee> employee) {
		this.employee = employee;
	}

	public Set<Contract> getContract() {
		return contract;
	}

	public void setContract(Set<Contract> contract) {
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}