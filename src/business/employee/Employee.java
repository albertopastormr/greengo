/**
 *
 */
package business.employee;

import javax.persistence.*;
import java.io.Serializable;

import business.mainoffice.MainOffice;

@Inheritance(strategy=InheritanceType.JOINED)
@Entity
@NamedQueries({
		@NamedQuery(name = "Employee.findByid",
                query = "select obj from Employee obj where :id = obj.id "),
		@NamedQuery(name = "Employee.findBymainOffice",
                query = "select obj from Employee obj where :mainOffice = obj.mainOffice "),
		@NamedQuery(name = "Employee.findByidCardNumber",
                query = "select obj from Employee obj where :idCardNumber = obj.idCardNumber "),
		@NamedQuery(name = "Employee.findBysalary",
                query = "select obj from Employee obj where :salary = obj.salary "),
		@NamedQuery(name = "Employee.findByActive",
                query = "select obj from Employee obj where :active = obj.active "),
		@NamedQuery(name = "Service.findAllEmployees",
				query = "select obj from Employee obj ") })

public abstract class Employee implements Serializable {

	private static final long serialVersionUID = 0;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Version
	protected Integer version;
	@ManyToOne
	private MainOffice mainOffice;

	private String idCardNumber;

	private Float salary;

	private Boolean active;


	private String dtype;


	public Employee() {
	}


    public Employee(TEmployee te) {
        this.id = te.getId();
        this.mainOffice = getMainOffice();
        this.idCardNumber = te.getIdCardNumber();
        this.salary = te.getSalary();
        this.active = te.isActive();
    }

    public abstract Double getDetailedSalary();

    public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MainOffice getMainOffice() {
		return mainOffice;
	}

	public void setMainOffice(MainOffice mainOffice) {
		this.mainOffice = mainOffice;
	}

	public String getIdCardNumber() {
		return idCardNumber;
	}

	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}

	public Float getSalary() {
		return salary;
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getType() { return dtype; }

	public void setType(String type) { this.dtype = type; }


}