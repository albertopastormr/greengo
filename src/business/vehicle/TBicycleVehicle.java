package business.vehicle;


public class TBicycleVehicle extends TVehicle {

    private String serialNumber;

    public TBicycleVehicle() {}

    public TBicycleVehicle(Integer id, String brand, Integer estimated_duration,
                           Integer km_travelled, boolean occupied, Integer city, boolean active, String serialNumber){
        super(id, brand, estimated_duration, km_travelled, occupied, city, active, "Bicycle");
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
