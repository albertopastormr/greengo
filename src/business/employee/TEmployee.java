package business.employee;

public class TEmployee {

	private int id;
	private String id_card_number;
	private float salary;
	private boolean active;
	private int id_main_office;

	public TEmployee(int id, String id_card_number, float salary, boolean active, int id_main_office) {
		this.id = id;
		this.id_card_number = id_card_number;
		this.salary = salary;
		this.active = active;
		this.id_main_office = id_main_office;
	}

	public TEmployee(){
	}

	public int getId() {
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

	public int getId_main_office() {
		return id_main_office;
	}

	public void setId_main_office(int id_main_office) {
		this.id_main_office = id_main_office;
	}
}
