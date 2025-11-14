package Parking_Spot;

import vehicle.Vehicle;

import java.util.concurrent.locks.ReentrantLock;

public class Parking_Spot {

    private final String Spot_id ;
    private Vehicle parkedVehicle;
    private boolean isOccupied;
    private boolean isUnderMaintainence;
    private final Spot_Type type ;
    private final ReentrantLock lock = new ReentrantLock();

    public Parking_Spot(String id , Spot_Type type){
        this.Spot_id = id;
        this.isOccupied = false;
        this.parkedVehicle = null;
        this.type = type;
        this.isUnderMaintainence = false;
    }



    public boolean parkAVehicle(Vehicle vehicle){
        lock.lock();
        try {
            if (isOccupied || isUnderMaintainence) {
                return false;
            }
            this.parkedVehicle = vehicle;
            this.isOccupied = true;
            return true;
        } finally {
            lock.unlock();
        }
    }

    public String getSpot_id() {
        return Spot_id;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public boolean unParkAVehicle(Vehicle vehicle){
        lock.lock();
        try {
            if (!isOccupied || !vehicle.equals(this.parkedVehicle)) {
                return false;
            }
            this.parkedVehicle = null;
            this.isOccupied = false;
            return true;
        } finally {
            lock.unlock();
        }
    }

    public boolean isUnderMaintainence() {
        return isUnderMaintainence;
    }

    public void setUnderMaintainence(boolean underMaintainence) {
        isUnderMaintainence = underMaintainence;
    }

    public Spot_Type getSpot_Type() {
        return type;
    }

    public boolean canFitVehicle(Vehicle vehicle){
        if(isOccupied || isUnderMaintainence){
            return false;
        }

        // Check if the spot type can accommodate the vehicle type
        switch (vehicle.getVehicle_Type()){
            case Bike:
                return this.type == Spot_Type.two_wheeler || this.type == Spot_Type.Small;
            case Car:
                return this.type == Spot_Type.Small || this.type == Spot_Type.Medium;
            case Truck:
                return this.type == Spot_Type.Large;
            default:
                return false;
        }
    }

}
