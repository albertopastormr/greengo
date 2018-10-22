package business.vehicle;

public class TVehicle {

	private int id;
	private String brand;
	private boolean active;
	private boolean occupied;
	private int km_travelled;
	private int estimated_duration;

	public TVehicle(int id, String brand, boolean active, boolean occupied, int km_travelled, int estimated_duration) {
		this.id = id;
		this.brand = brand;
		this.active = active;
		this.occupied = occupied;
		this.km_travelled = km_travelled;
		this.estimated_duration = estimated_duration;
	}

	public TVehicle(){
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getKm_travelled() {
		return km_travelled;
	}

	public void setKm_travelled(int km_travelled) {
		this.km_travelled = km_travelled;
	}

	public int getEstimated_duration() {
		return estimated_duration;
	}

	public void setEstimated_duration(int estimated_duration) {
		this.estimated_duration = estimated_duration;
	}
}
