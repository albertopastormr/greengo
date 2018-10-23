package business.employee;

public class TEmployee {

	private Integer id = null;
	private String id_card_number = null;
	private Float salary = null;
	private Boolean active = null;
	private Integer id_main_office = null;
	private String type = null;

	public TEmployee(){}
	
	public TEmployee(Integer id, String id_card_number, float salary, boolean active, Integer id_main_office, String type) {
		this.id = id;
		this.id_card_number = id_card_number;
		this.salary = salary;
		this.active = active;
		this.id_main_office = id_main_office;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getId_card_number() {
		return id_card_number;
	}

	public void setId_card_number(String id_card_number) {
		this.id_card_number = id_card_number;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Integer getId_main_office() {
		return id_main_office;
	}

	public void setId_main_office(Integer id_main_office) {
		this.id_main_office = id_main_office;
	}

	public String getType() { return this.type; }

	public void setType(String type) { this.type = type; }
}
