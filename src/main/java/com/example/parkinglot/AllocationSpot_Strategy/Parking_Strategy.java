package AllocationSpot_Strategy;

import Parking_Lot.Parking_Lot;
import Parking_Spot.Parking_Spot;
import vehicle.Vehicle;

public interface Parking_Strategy {
    Parking_Spot findSpot(Parking_Lot lot , Vehicle Vehicle);
}
