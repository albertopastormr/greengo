package business.vehicle;

public class TVehicle {

	private Integer id;
	private String brand;
    private Integer estimated_duration;
    private Integer km_travelled;
    private boolean occupied;
    private Integer city;
    private boolean active;

	public TVehicle(){}

    public TVehicle(Integer id, String brand, Integer estimated_duration, Integer km_travelled, boolean occupied, Integer city, boolean active) {
        this.id = id;
        this.brand = brand;
        this.estimated_duration = estimated_duration;
        this.km_travelled = km_travelled;
        this.occupied = occupied;
        this.city = city;
        this.active = active;
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

    public Integer getEstimated_duration() {
        return estimated_duration;
    }

    public void setEstimated_duration(Integer estimated_duration) {
        this.estimated_duration = estimated_duration;
    }

    public Integer getKm_travelled() {
        return km_travelled;
    }

    public void setKm_travelled(Integer km_travelled) {
        this.km_travelled = km_travelled;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
