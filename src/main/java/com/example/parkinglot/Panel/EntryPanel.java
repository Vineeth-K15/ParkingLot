package Panel;

import AllocationSpot_Strategy.Parking_Strategy;
import DisplayBoard.EntryDisplayBoard;
import Parking_Lot.Parking_Lot;
import Parking_Spot.Parking_Spot;
import TicketGenerator.TicketGenterator;
import vehicle.Vehicle;

import java.util.UUID;

public class EntryPanel {

    private Parking_Strategy strategy;
    private EntryDisplayBoard displayBoard;

    public EntryPanel(Parking_Strategy strategy) {
        this.strategy = strategy;
        this.displayBoard = new EntryDisplayBoard();
    }

    public TicketGenterator issueTicket(Parking_Lot parkingLot,Vehicle vehicle) {
        // Display current availability
        displayBoard.displayAvailability();

        // Find the best available spot for the vehicle
        Parking_Spot availableSpot = strategy.findSpot(parkingLot, vehicle);

        if (availableSpot == null) {
            System.out.println("❌ No available parking spot for vehicle: " + vehicle.getVehicle_no());
            System.out.println("Please try again later or contact parking management.");
            return null;
        }

        // Generate a unique ticket ID
        String ticketId = UUID.randomUUID().toString().substring(0, 8);

        // Create a new ticket
        TicketGenterator ticket = new TicketGenterator(vehicle, availableSpot, ticketId, availableSpot.getSpot_Type());

        // Allocate the spot by parking the vehicle
        boolean allocationSuccess = availableSpot.parkAVehicle(vehicle);

        if (allocationSuccess) {
            // Add ticket to parking lot's active tickets
            parkingLot.addTicket(ticketId, ticket);

            System.out.println("Ticket issued successfully!");
            System.out.println("Ticket ID: " + ticketId);
            System.out.println("Vehicle: " + vehicle.getVehicle_no());
            System.out.println("Spot Type: " + availableSpot.getSpot_Type().toString());
            System.out.println("Entry Time: " + ticket.getFormattedEntryTime());
            System.out.println("Rate: ₹" + ticket.getPricePerHour() + " per hour");

            // Update display board
            displayBoard.updateDisplay();

            return ticket;
        } else {
            System.out.println("Failed to allocate spot. Please try again.");
            return null;
        }
    }

    public void displayWelcomeMessage() {
        displayBoard.displayWelcomeMessage();
        displayBoard.displayPricingInfo();
    }
}
