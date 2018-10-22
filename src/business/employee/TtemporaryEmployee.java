package business.employee;

public class TtemporaryEmployee extends TEmployee {
	private int worked_hours;

	public TtemporaryEmployee(){


	}

	public TtemporaryEmployee(Integer id, String id_card_number, float salary, boolean active, Integer id_main_office, int worked_hours){
		super(id, id_card_number, salary, active, id_main_office, "Temporary");
		this.worked_hours = worked_hours;
	}

	public int getWorked_hours() {
		return worked_hours;
	}

	public void setWorked_hours(int worked_hours) {
		this.worked_hours = worked_hours;
	}
}
