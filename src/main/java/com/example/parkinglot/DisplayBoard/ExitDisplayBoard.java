package DisplayBoard;

import vehicle.Vehicle;

public class ExitDisplayBoard extends DisplayBoard{
    
    @Override
    public void display() {
        System.out.println("Display @Exit: Thank you! Drive safe.");
    }

    public void displayCost(Vehicle vehicle, double cost) {
        System.out.println("Display @Exit: Vehicle " + vehicle.getVehicle_no() + " - Total cost: ₹" + cost);
    }
}
