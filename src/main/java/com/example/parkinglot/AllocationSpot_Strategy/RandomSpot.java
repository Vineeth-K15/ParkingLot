package AllocationSpot_Strategy;

import Parking_Floor.Parking_Floor;
import Parking_Lot.Parking_Lot;
import Parking_Spot.Parking_Spot;
import vehicle.Vehicle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomSpot implements Parking_Strategy{

    @Override
    public Parking_Spot findSpot(Parking_Lot parkingLot, Vehicle vehicle) {
        Parking_Spot bestSpot = null;
        List<Parking_Floor> floors = new ArrayList<>(parkingLot.getFloorList());
        Collections.shuffle(floors);
        for (Parking_Floor floor : floors) {
            if (floor.isUnderMaintainence()) continue;
            bestSpot = floor.availableSpot(vehicle);
            if (bestSpot != null) return bestSpot;
        }

        return bestSpot;
    }
}
