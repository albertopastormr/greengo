package business.service;

public class TService {

	private Integer id;
	private Integer capacity;
	private boolean active;
	private String type;
	private String address;
	private Integer vehicles_attended;

	public TService(Integer id, Integer capacity, boolean active, String type, String address, Integer vehicles_attended) {
		this.id = id;
		this.capacity = capacity;
		this.active = active;
		this.type = type;
		this.address = address;
		this.vehicles_attended = vehicles_attended;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getVehicles_attended() {
		return vehicles_attended;
	}

	public void setVehicles_attended(Integer vehicles_attended) {
		this.vehicles_attended = vehicles_attended;
	}
}
