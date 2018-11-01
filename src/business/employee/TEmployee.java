package business.employee;

public class TEmployee {
	private Integer id = null;
	private String idCardNumber = null;
	private Float salary = null;
	private Boolean active = null;
	private Integer idMainOffice = null;
	private String type = null;

	public TEmployee() {
	}

	public TEmployee(Integer id, String idCardNumber, float salary, Boolean active, Integer idMainOffice, String type) {
		this.id = id;
		this.idCardNumber = idCardNumber;
		this.salary = salary;
		this.active = active;
		this.idMainOffice = idMainOffice;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getIdMainOffice() {
		return idMainOffice;
	}

	public void setIdMainOffice(Integer idMainOffice) {
		this.idMainOffice = idMainOffice;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString(){
		String ret = "";
		ret += String.format("%-13s %13s %n", "Id: ", id);
		ret += String.format("%-13s %13s %n", "Id card number: ", idCardNumber);
		ret += String.format("%-13s %13s %n", "Salary: ", salary);
		ret += String.format("%-13s %13s %n", "Type: ", type);
		ret += String.format("%-13s %13s %n", "Id main office: ", idMainOffice);
		ret += String.format("%-13s %13s %n", "Active: ", active);

		return ret;
	}
}
