package business.rental;

public class TRental {

	private Integer id;
	private Integer id_vehicle;
	private boolean active;
	private int km_rented;
	private Integer id_client;

	public TRental(Integer id, Integer id_vehicle, boolean active, int km_rented, Integer id_client) {
		this.id = id;
		this.id_vehicle = id_vehicle;
		this.active = active;
		this.km_rented = km_rented;
		this.id_client = id_client;
	}
	public TRental(){
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_vehicle() {
		return id_vehicle;
	}

	public void setId_vehicle(Integer id_vehicle) {
		this.id_vehicle = id_vehicle;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getKm_rented() {
		return km_rented;
	}

	public void setKm_rented(int km_rented) {
		this.km_rented = km_rented;
	}

	public Integer getId_client() {
		return id_client;
	}

	public void setId_client(Integer id_client) {
		this.id_client = id_client;
	}
}
