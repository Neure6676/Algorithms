package LowLevelDesign.ParkingLot;

import java.util.List;

// TwoWheelerManager and FourWheelerManager classes
class TwoWheelerManager extends ParkingSpotManager {
    TwoWheelerManager(List<ParkingSpot> spots) {
        super(spots);
    }

    @Override
    ParkingSpot findParkingSpace() {
        // Implementation to find nearest parking spot for Two Wheelers
        return null;
    }
}
