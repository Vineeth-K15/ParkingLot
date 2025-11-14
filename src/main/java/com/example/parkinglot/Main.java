import vehicle.Vehicle;
import vehicle.VehicleTypes;
public class Main {

    public static void main(String[] args) {
        Vehicle vehicle1 = new Vehicle("KA-01-HH-1234", VehicleTypes.Car);
        Vehicle vehicle2 = new Vehicle("KA-01-HH-9999", VehicleTypes.Bike);

        System.out.println("Vehicle 1: " + vehicle1.getVehicle_no() + ", Type: " + vehicle1.getVehicle_Type());
        System.out.println("Vehicle 2: " + vehicle2.getVehicle_no() + ", Type: " + vehicle2.getVehicle_Type());
    }
}
