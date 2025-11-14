package DisplayBoard;

import Parking_Spot.Parking_Spot;
import Parking_Spot.Spot_Type;

import java.util.Map;
import java.util.Set;

public class FloorDisplayBoard extends DisplayBoard {
    private final String floorId;

    public FloorDisplayBoard(String floorId) {
        this.floorId = floorId;
    }

    public void displayAvailableSpots(Map<Spot_Type, Set<Parking_Spot>> spotMap, boolean underMaintenance) {
        if (underMaintenance) {
            System.out.println("Display @Floor " + floorId + ": This floor is under maintenance.");
            return;
        }

        System.out.println("Display @Floor " + floorId + ": Available spots:");
        for (Map.Entry<Spot_Type, Set<Parking_Spot>> entry : spotMap.entrySet()) {
            long available = entry.getValue().stream().filter(spot -> !spot.isOccupied()).count();
            System.out.println("- " + entry.getKey() + ": " + available + " spot(s)");
        }
    }

    @Override
    public void display() {
        System.out.println("Display @Floor " + floorId + ": Welcome to Floor " + floorId);
    }
}
