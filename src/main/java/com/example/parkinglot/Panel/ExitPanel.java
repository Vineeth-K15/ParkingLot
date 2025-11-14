package Panel;

import DisplayBoard.ExitDisplayBoard;
import Parking_Lot.Parking_Lot;
import Parking_Spot.Parking_Spot;
import PaymentProcessor.PaymentProcessor;
import TicketGenerator.TicketGenterator;

public class ExitPanel {
    private final ExitDisplayBoard displayPanel;
    private PaymentProcessor paymentProcessor;
    private Parking_Lot parkingLot;

    public ExitPanel(PaymentProcessor paymentProcessor) {
        this.displayPanel = new ExitDisplayBoard();
        this.paymentProcessor = paymentProcessor;
    }

    public void setParkingLot(Parking_Lot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void setPaymentProcessor(PaymentProcessor paymentProcessor) {
        // Allow changing payment processor at runtime
        // e.g., switch from card to mobile payment
        this.paymentProcessor = paymentProcessor;
    }

    public void unparkVehicle(TicketGenterator ticket) {
        if (ticket == null || ticket.getSpot() == null) {
            System.out.println("❌ Invalid ticket. Cannot process exit.");
            return;
        }

        // Calculate the cost based on duration
        double cost = ticket.calculateTotalFee();
        ticket.setExitTime(System.currentTimeMillis());

        // Retrieve the spot from ParkingLot by spotId and remove vehicle
        Parking_Spot spot = parkingLot.getSpotById(ticket.getSpotId());
        if (spot != null) {
            spot.unParkAVehicle(ticket.getVehicle());
        }

        // Remove ticket from active tickets
        parkingLot.removeTicket(ticket.getTicketId());

        paymentProcessor.processPayment(cost);
        displayPanel.displayCost(ticket.getVehicle(), cost);
    }
}
