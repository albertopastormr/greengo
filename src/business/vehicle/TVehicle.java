package business.vehicle;

public class TVehicle {

	private Integer id;
	private String brand;
	private boolean active;
	private boolean occupied;
	private Integer km_travelled;
	private Integer estimated_duration;

	public TVehicle(){}

	public TVehicle(Integer id, String brand, boolean active, boolean occupied, Integer km_travelled, Integer estimated_duration) {
		this.id = id;
		this.brand = brand;
		this.active = active;
		this.occupied = occupied;
		this.km_travelled = km_travelled;
		this.estimated_duration = estimated_duration;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public Integer getKm_travelled() {
		return km_travelled;
	}

	public void setKm_travelled(Integer km_travelled) {
		this.km_travelled = km_travelled;
	}

	public Integer getEstimated_duration() {
		return estimated_duration;
	}

	public void setEstimated_duration(Integer estimated_duration) {
		this.estimated_duration = estimated_duration;
	}
}
