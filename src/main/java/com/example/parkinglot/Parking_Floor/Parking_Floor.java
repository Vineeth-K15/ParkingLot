package Parking_Floor;

import DisplayBoard.FloorDisplayBoard;
import Parking_Spot.Parking_Spot;
import Parking_Spot.Spot_Type;
import vehicle.Vehicle;

import java.util.*;

public class Parking_Floor {
    private final String Floor_id ;
    private HashMap<Spot_Type, Set<Parking_Spot>> SpotMap;
    private boolean isUnderMaintainence;
    private boolean isFull ;
    private FloorDisplayBoard displayBoard;

    public Parking_Floor(String floor_id) {
        this.Floor_id = floor_id;
        this.SpotMap = new HashMap<>();
        this.isFull = false;
        this.isUnderMaintainence = false;
        for(Spot_Type spot: Spot_Type.values() ){
            SpotMap.put(spot , new HashSet<>());
        }
        this.displayBoard = new FloorDisplayBoard(floor_id);
    }

    public void addSpot(Parking_Spot spot) {
        SpotMap.get(spot.getSpot_Type()).add(spot);
    }

    public Parking_Spot availableSpot(Vehicle vehicle) {
        if (isUnderMaintainence) {
            return null;
        }

        for (Spot_Type spotType : Spot_Type.values()) {
            for (Parking_Spot spot : SpotMap.get(spotType)) {
                if (spot.canFitVehicle(vehicle)) {
                    return spot;
                }
            }
        }
        return null;
    }

    public HashMap<Spot_Type, Set<Parking_Spot>> getSpotMap() {
        return SpotMap;
    }

    public void setUnderMaintainence(boolean underMaintainence) {
        isUnderMaintainence = underMaintainence;
    }

    public boolean isUnderMaintainence() {
        return isUnderMaintainence;
    }

    public Set<Parking_Spot> getAllSpots() {
        Set<Parking_Spot> allSpots = new HashSet<>();
        for (Set<Parking_Spot> set : SpotMap.values()) {
            allSpots.addAll(set);
        }
        return allSpots;
    }

    public void showFloorDisplay() {
        displayBoard.displayAvailableSpots(SpotMap, isUnderMaintainence);
    }

    public boolean isFull() {

        if(isFull) return true;

        for (Set<Parking_Spot> spots : SpotMap.values()) {
            for (Parking_Spot spot : spots) {
                if (!spot.isOccupied()) {
                    return false; // At least one spot is available
                }
            }
        }
        this.isFull = true;
        return true; // All spots are occupied
    }
}
