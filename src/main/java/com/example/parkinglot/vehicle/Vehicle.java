package vehicle;

public class Vehicle {

    private String vehicle_no;
    private VehicleTypes vehicleType;

    public Vehicle(String vehicle_no, VehicleTypes vehicle_Type) {
        this.vehicle_no = vehicle_no;
        this.vehicleType = vehicle_Type;
    }

    public String getVehicle_no() {
        return vehicle_no;
    }
    public VehicleTypes getVehicle_Type() {
        return vehicleType;
    }
}
