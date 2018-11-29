/**
 *
 */
package business.employee;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import business.mainoffice.MainOffice;

import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;


@Entity
@NamedQueries({
		@NamedQuery(name = "Business.employee.as.imp.employee.findByid", query = "select obj from employee obj where :id = obj.id "),
		@NamedQuery(name = "Business.employee.as.imp.employee.findBymain_Office", query = "select obj from employee obj where :main_Office = obj.main_Office "),
		@NamedQuery(name = "Business.employee.as.imp.employee.findByid_card_number", query = "select obj from employee obj where :id_card_number = obj.id_card_number "),
		@NamedQuery(name = "Business.employee.as.imp.employee.findBysalary", query = "select obj from employee obj where :salary = obj.salary "),
		@NamedQuery(name = "Business.employee.as.imp.employee.findByactive", query = "select obj from employee obj where :active = obj.active ") })
public class Employee implements Serializable {

	private static final long serialVersionUID = 0;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private MainOffice main_Office;

	private String id_card_number;

	private Float salary;

	private Boolean active;


	public Employee() {
	}


	public Employee(TEmployee te) {

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

	public MainOffice getMain_Office() {
		return main_Office;
	}

	public void setMain_Office(MainOffice main_Office) {
		this.main_Office = main_Office;
	}

	public String getId_card_number() {
		return id_card_number;
	}

	public void setId_card_number(String id_card_number) {
		this.id_card_number = id_card_number;
	}

	public Float getSalary() {
		return salary;
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}