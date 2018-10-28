package business.service;

public class TService {

	private Integer id;
	private Integer capacity;
	private boolean active;
	private String type;
	private String address;
	private Integer numVehiclesAttended;

	public TService(){}

	public TService(Integer id, Integer capacity, boolean active, String type, String address, Integer numVehiclesAttended) {
		this.id = id;
		this.capacity = capacity;
		this.active = active;
		this.type = type;
		this.address = address;
		this.numVehiclesAttended = numVehiclesAttended;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
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

	public Integer getNumVehiclesAttended() {
		return numVehiclesAttended;
	}

	public void setNumVehiclesAttended(Integer numVehiclesAttended) {
		this.numVehiclesAttended = numVehiclesAttended;
	}
}
