package business.client;

public class TClient {

	private Integer id;
	private String idCardNumber;
	private Integer numRentals;
	private boolean active;

	public TClient(){}
	public TClient(Integer id, String idCardNumber, Integer numRentals, boolean active) {
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
