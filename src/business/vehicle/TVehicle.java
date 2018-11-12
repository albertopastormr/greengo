package business.vehicle;

public class TVehicle {

	private Integer id;
	private String brand;
    private Integer estimatedDuration;
    private Integer numKmTravelled;
    private Boolean occupied;
    private Integer city;
    private Boolean active;
    private String type;

	public TVehicle(){}

    public TVehicle(Integer id, String brand, Integer estimatedDuration, Integer numKmTravelled, Boolean occupied, Integer city, Boolean active, String type) {
        this.id = id;
        this.brand = brand;
        this.estimatedDuration = estimatedDuration;
        this.numKmTravelled = numKmTravelled;
        this.occupied = occupied;
        this.city = city;
        this.active = active;
        this.type = type;
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

    public Integer getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(Integer estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public Integer getNumKmTravelled() {
        return numKmTravelled;
    }

    public void setNumKmTravelled(Integer numKmTravelled) {
        this.numKmTravelled = numKmTravelled;
    }

    public Boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
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

}
