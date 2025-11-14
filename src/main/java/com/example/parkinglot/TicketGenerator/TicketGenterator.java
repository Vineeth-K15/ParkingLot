package TicketGenerator;

import Parking_Spot.Parking_Spot;
import Parking_Spot.Spot_Type;
import PaymentProcessor.Payment_Method;
import vehicle.Vehicle;

public class TicketGenterator {
    private String ticketId;
    private Vehicle vehicle;
    private Parking_Spot spot;
    private Spot_Type Type;
    private long entry_time;
    private long exit_time;
    private double pricePerHour;
    private Payment_Method paymentMode;
    private boolean isPaid;

    public TicketGenterator(Vehicle vehicle, Parking_Spot spot, String ticketId, Spot_Type type) {
        this.spot = spot;
        this.vehicle = vehicle;
        this.ticketId = ticketId;
        this.Type = type;
        this.entry_time = System.currentTimeMillis();
        this.exit_time = 0; // Will be set when vehicle exits
        this.pricePerHour = calculatePricePerHour(vehicle);
        this.paymentMode = null;
        this.isPaid = false;
    }

    private double calculatePricePerHour(Vehicle vehicle) {
        switch (vehicle.getVehicle_Type()) {
            case Bike:
                return 2.0;
            case Car:
                return 5.0;
            case Truck:
                return 8.0;
            default:
                return 5.0;
        }
    }

    // Getter methods
    public String getTicketId() {
        return ticketId;
    }

    // Getter methods for Vehicle
    public Vehicle getVehicle() {
        return vehicle;
    }
    
    // Getter methods for Spot
    public String getSpot() {
        return spot.getSpot_id();
    }

    public String getSpotId() {
        return spot.getSpot_id();
    }

    public Spot_Type getType() {
        return Type;
    }

    public long getEntryTime() {
        return entry_time;
    }

    public long getExitTime() {
        return exit_time;
    }

    public void setExitTime(long exit_time) {
        this.exit_time = exit_time;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public Payment_Method getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(Payment_Method paymentMode) {
        this.paymentMode = paymentMode;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public double calculateTotalFee() {
        if (exit_time == 0) {
            exit_time = System.currentTimeMillis();
        }

        long duration = exit_time - entry_time;
        double hours = duration / (1000.0 * 60 * 60);

        if (hours <= 1.0) {
            return pricePerHour;
        } else {
            return pricePerHour * Math.ceil(hours);
        }
    }

    public String getFormattedEntryTime() {
        return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new java.util.Date(entry_time));
    }

    public String getFormattedExitTime() {
        if (exit_time == 0) {
            return "Not exited yet";
        }
        return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new java.util.Date(exit_time));
    }

}