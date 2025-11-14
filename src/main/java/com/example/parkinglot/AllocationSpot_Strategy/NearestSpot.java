package AllocationSpot_Strategy;

import Parking_Floor.Parking_Floor;
import Parking_Lot.Parking_Lot;
import Parking_Spot.Parking_Spot;
import vehicle.Vehicle;

public class NearestSpot implements Parking_Strategy {

    @Override
    public Parking_Spot findSpot(Parking_Lot parkingLot, Vehicle vehicle) {
        Parking_Spot bestSpot = null;

        for (Parking_Floor floor : parkingLot.getFloorList()) {
            if(floor.isUnderMaintainence()) continue;
            bestSpot = floor.availableSpot(vehicle);

        }
        return bestSpot;
    }
}
