package Parking_Lot;

import AllocationSpot_Strategy.Parking_Strategy;
import DisplayBoard.DisplayBoard;
import Panel.EntryPanel;
import Panel.ExitPanel;
import Parking_Floor.Parking_Floor;
import Parking_Spot.Parking_Spot;
import PaymentProcessor.PaymentProcessor;
import TicketGenerator.TicketGenterator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parking_Lot {
    private final String Lot_Id ;
    private List<Parking_Floor > FloorList;
    private ExitPanel ExitGate ;
    private EntryPanel EntryGate;
    private Parking_Strategy Strategy;
    private DisplayBoard displayBoard;
    private Map<String, TicketGenterator> activeTickets;


    public Parking_Lot(String lot_Id , Parking_Strategy strategy) {
        Lot_Id = lot_Id;
        this.FloorList = new ArrayList<>();
        this.displayBoard = new DisplayBoard() {
            @Override
            public void display() {
                System.out.println("Parking Lot Display Board");
            }
        };
        PaymentProcessor pp = new PaymentProcessor();
        this.ExitGate = new ExitPanel(pp);
        this.ExitGate.setParkingLot(this);
        this.EntryGate = new EntryPanel(strategy);
        this.activeTickets = new HashMap<>();

    }

    public void addFloor(Parking_Floor floor){
        FloorList.add(floor);
    }

    public List<Parking_Floor> getFloorList() {
        return FloorList;
    }

    public EntryPanel getEntryGate() {
        return EntryGate;
    }

    public ExitPanel getExitGate() {
        return ExitGate;
    }

    public DisplayBoard getDisplayBoard() {
        return displayBoard;
    }

    public String getLotId() {
        return Lot_Id;
    }

    public void displaySystemStatus() {
        int totalAvailableSpots = 0;
        for (Parking_Floor floor : FloorList) {
            for (Parking_Spot spot : floor.getAllSpots()) {
                if (!spot.isOccupied() && !floor.isUnderMaintainence()) {
                    totalAvailableSpots++;
                }
            }
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("        PARKING LOT SYSTEM STATUS");
        System.out.println("=".repeat(60));
        System.out.println("Lot ID: " + Lot_Id);
        System.out.println("Total Floors: " + FloorList.size());
        System.out.println("Total Available Spots: " + totalAvailableSpots);
        System.out.println("Active Tickets: " + activeTickets.size());
        System.out.println("System Status: OPERATIONAL");
        System.out.println("=".repeat(60) + "\n");
    }

    public Parking_Spot getSpotById(String spotId) {
        for (Parking_Floor floor : FloorList) {
            for (Parking_Spot spot : floor.getAllSpots()) {
                if (spot.getSpot_id().equals(spotId)) {
                    return spot;
                }
            }
        }
        return null;
    }

    public void addTicket(String ticketId, TicketGenterator ticket) {
        activeTickets.put(ticketId, ticket);
    }

    public void removeTicket(String ticketId) {
        activeTickets.remove(ticketId);
    }

    public TicketGenterator getTicket(String ticketId) {
        return activeTickets.get(ticketId);
    }

    public int getAvailableSpotsCount() {
        int count = 0;
        for (Parking_Floor floor : FloorList) {
            if (!floor.isUnderMaintainence()) {
                for (Parking_Spot spot : floor.getAllSpots()) {
                    if (!spot.isOccupied()) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
