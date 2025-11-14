package AllocationSpot_Strategy;

import Parking_Floor.Parking_Floor;
import Parking_Lot.Parking_Lot;
import Parking_Spot.Parking_Spot;
import vehicle.Vehicle;

public class FirstSpotAvailable implements Parking_Strategy {

    @Override
    public Parking_Spot findSpot(Parking_Lot parkingLot, Vehicle vehicle) {
        for (Parking_Floor floor : parkingLot.getFloorList()) {
            if (floor.isUnderMaintainence()) continue;

            Parking_Spot spot = floor.availableSpot(vehicle);
            if (spot != null) {
                return spot; // Return first available spot
            }
        }
        return null;
    }
}
