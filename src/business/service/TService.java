package business.service;

public class TService {

	private Integer id;
	private Integer capacity;
	private Boolean active;
	private String type;
	private String address;
	private Integer numVehiclesAttended;

	public TService(){}

	public TService(Integer id, Integer capacity, Boolean active, String type, String address, Integer numVehiclesAttended) {
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

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
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

	@Override
	public String toString(){
		String ret = "";
		ret += String.format("%-13s %13s %n", "Id: ", id);
		ret += String.format("%-13s %13s %n", "Capacity: ", capacity);
		ret += String.format("%-13s %13s %n", "Type: ", type);
		ret += String.format("%-13s %13s %n", "Address: ", address);
		ret += String.format("%-13s %13s %n", "Address: ", address);
		ret += String.format("%-13s %13s %n", "Vehicles attended: ", numVehiclesAttended);
		ret += String.format("%-13s %13s %n", "Active: ", active);

		return ret;
	}
}
