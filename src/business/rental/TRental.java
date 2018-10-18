package business.rental;

public class TRental {

	private int id;
	private int id_vehicle;
	private boolean active;
	private int km_rented;
	private int id_client;

	public TRental(int id, int id_vehicle, boolean active, int km_rented, int id_client) {
		this.id = id;
		this.id_vehicle = id_vehicle;
		this.active = active;
		this.km_rented = km_rented;
		this.id_client = id_client;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_vehicle() {
		return id_vehicle;
	}

	public void setId_vehicle(int id_vehicle) {
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

	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
}
