package business.vehicle;

public class TCarVehicle extends TVehicle {

    private String plate;

    public TCarVehicle() {}

    public TCarVehicle(Integer id, String brand, Integer estimated_duration, Integer km_travelled, boolean occupied, Integer city, boolean active, String plate){
        super(id, brand, estimated_duration, km_travelled, occupied, city, active, "Car");
        this.plate = plate;
    }

    public TCarVehicle(Integer id, String brand, Integer estimated_duration, Integer km_travelled, boolean occupied, Integer city, boolean active){
        super(id, brand, estimated_duration, km_travelled, occupied, city, active, "Car");
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
}
