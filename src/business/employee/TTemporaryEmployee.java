package business.employee;

public class TTemporaryEmployee extends TEmployee {
	private Integer numWorkedHours;

	public TTemporaryEmployee() {
	}

	public TTemporaryEmployee(Integer id, String id_card_number, Float salary, Boolean active, Integer id_main_office, Integer numWorkedHours) {
		super(id, id_card_number, salary, active, id_main_office, "Temporary");
		this.numWorkedHours = numWorkedHours;
	}

	public Integer getNumWorkedHours() {
		return numWorkedHours;
	}

	public void setNumWorkedHours(Integer numWorkedHours) {
		this.numWorkedHours = numWorkedHours;
	}
}