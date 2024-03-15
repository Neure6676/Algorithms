package LLD.ParkingLot;

import java.util.ArrayList;

// ExitGate class
class ExitGate {
    ParkingSpotManagerFactory factory;

    ExitGate(ParkingSpotManagerFactory factory) {
        this.factory = factory;
    }

    void removeVehicle(Ticket ticket) {
        ParkingSpotManager manager = factory.getParkingSpotManager(ticket.vehicle.vehicleType, new ArrayList<>());
        manager.removeVehicle(ticket.vehicle);
    }
}


