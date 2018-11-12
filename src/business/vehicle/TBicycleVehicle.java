package business.vehicle;


public class TBicycleVehicle extends TVehicle {

    private Integer serialNumber;

    public TBicycleVehicle() {}

    public TBicycleVehicle(Integer id, String brand, Integer estimated_duration,
                           Integer km_travelled, boolean occupied, Integer city, boolean active, Integer serialNumber){
        super(id, brand, estimated_duration, km_travelled, occupied, city, active, "Bicycle");
        this.serialNumber = serialNumber;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }
}
