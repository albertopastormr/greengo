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

<<<<<<< HEAD
    public void setId(Integer id) {
        this.id = id;
    }
=======
	public void setId(Integer id) {
		this.id = id;
	}
>>>>>>> cf81cc75ab0594ec01e0a5a7162ed868004d3df6

    public String getId_card_number() {
        return id_card_number;
    }

    public void setId_card_number(String id_card_number) {
        this.id_card_number = id_card_number;
    }

    public Integer getRentals_number() {
        return rentals_number;
    }

<<<<<<< HEAD
    public void setRentals_number(Integer rentals_number) {
        this.rentals_number = rentals_number;
    }
=======
	public void setRentals_number(Integer rentals_number) {
		this.rentals_number = rentals_number;
	}
>>>>>>> cf81cc75ab0594ec01e0a5a7162ed868004d3df6

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
