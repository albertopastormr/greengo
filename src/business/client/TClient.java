package business.client;

public class TClient {

	private Integer id;
	private String id_card_number;
	private Integer rentals_number;
	private boolean active;

	public TClient(){}
	public TClient(Integer id, String id_card_number, Integer rentals_number, boolean active) {
		this.id = id;
		this.id_card_number = id_card_number;
		this.rentals_number = rentals_number;
		this.active = active;
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

	public Integer getRentals_number() {
		return rentals_number;
	}

	public void setRentals_number(int rentals_number) {
		this.rentals_number = rentals_number;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
