package DisplayBoard;

import Parking_Spot.Parking_Spot;
import TicketGenerator.TicketGenterator;
import vehicle.Vehicle;

public class EntryDisplayBoard extends DisplayBoard {
    
    @Override
    public void display() {
        System.out.println("Display @Entry: Welcome to the Parking Lot");
    }

    public void displaySpotAssigned(Vehicle vehicle, Parking_Spot spot) {
        if (spot != null) {
            System.out.println("Display @Entry: Assigned Spot " + spot.getSpot_id() + " to vehicle " + vehicle.getVehicle_no());
        } else {
            System.out.println("Display @Entry: No spot available for vehicle " + vehicle.getVehicle_no());
        }
    }

    public void displayTicketIssued(TicketGenterator ticket) {
        if (ticket != null) {
            System.out.println("Display @Entry: Ticket issued with ID " + ticket.getTicketId() + " for vehicle " + ticket.getVehicle().getVehicle_no());
        } else {
            System.out.println("Display @Entry: Ticket could not be issued.");
        }
    }

    public void displayAvailability() {
        System.out.println("Display @Entry: Checking for available spots...");
    }

    public void updateDisplay() {
        System.out.println("Display @Entry: Updated availability information displayed.");
    }

    public void displayWelcomeMessage() {
        System.out.println("Display @Entry: Welcome! Please wait while we find you a spot.");
    }

    public void displayPricingInfo() {
        System.out.println("Display @Entry: Pricing: Bike - $2/hr, Car - $5/hr, Truck - $8/hr, Bus - $10/hr");
    }
}
