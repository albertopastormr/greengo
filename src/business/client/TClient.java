package business.client;

public class TClient {

	private Integer id;
	private String idCardNumber;
	private Integer numRentals;
	private Boolean active;

	public TClient(){}
	public TClient(Integer id, String idCardNumber, Integer numRentals, Boolean active) {
		this.id = id;
		this.idCardNumber = idCardNumber;
		this.numRentals = numRentals;
		this.active = active;
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

    public Integer getNumRentals() {
        return numRentals;
    }

    public void setNumRentals(Integer numRentals) {
        this.numRentals = numRentals;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

	@Override
	public String toString(){
		String ret = "";
		ret += String.format("%-13s %13s %n", "Id: ", id);
		ret += String.format("%-13s %13s %n", "Id card number: ", idCardNumber);
		ret += String.format("%-13s %13s %n", "Number of rentals: ", numRentals);
		ret += String.format("%-13s %13s %n", "Active: ", active);

		return ret;
	}
}
