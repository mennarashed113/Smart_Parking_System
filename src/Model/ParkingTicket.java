package Model;

import java.time.LocalDateTime;

public class ParkingTicket {
    private static long idCounter =1;
    private String ticketId;
    private Vehicle vehicle;
    private ParkingSpot spot;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double fees;

    public ParkingTicket(Vehicle vehicle, ParkingSpot spot) {
        this.ticketId = "Ticket "+idCounter++;
        this.vehicle = vehicle;
        this.spot = spot;
        this.startTime = LocalDateTime.now();
        this.endTime = null;
        this.fees = 0.0;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public double getFees() {
        return fees;
    }
    public void setFees(double fees) {
        this.fees = fees;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
