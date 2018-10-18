package business.service;

public class TService {

	private int id;
	private int capacity;
	private boolean active;
	private String type;
	private String address;
	private int vehicles_attended;

	public TService(int id, int capacity, boolean active, String type, String address, int vehicles_attended) {
		this.id = id;
		this.capacity = capacity;
		this.active = active;
		this.type = type;
		this.address = address;
		this.vehicles_attended = vehicles_attended;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getVehicles_attended() {
		return vehicles_attended;
	}

	public void setVehicles_attended(int vehicles_attended) {
		this.vehicles_attended = vehicles_attended;
	}
}
