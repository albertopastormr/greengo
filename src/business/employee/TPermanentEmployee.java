package business.employee;

public class TPermanentEmployee extends TEmployee {

	private Float apportionment;

	public TPermanentEmployee() {
	}

	public TPermanentEmployee(Integer id, String id_card_number, Float salary, Boolean active, Integer id_main_office, Float apportionment) {
		super(id, id_card_number, salary, active, id_main_office, "Permanent");
		this.apportionment = apportionment;
	}

	public Float getApportionment() {
		return apportionment;
	}

	public void setApportionment(Float apportionment) {
		this.apportionment = apportionment;
	}
}
