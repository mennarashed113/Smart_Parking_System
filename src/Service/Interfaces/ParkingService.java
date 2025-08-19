package Service.Interfaces;

import Model.ParkingSpot;
import Model.ParkingTicket;
import Model.Vehicle;

import java.util.List;

public interface ParkingService {
    List<ParkingSpot> getAvailableParkingSpots();
    List<ParkingSpot> getAvailableParkingSpots(String vehicleType);

    boolean checkAvailabilitySpot(ParkingSpot spot);
    ParkingTicket parkVehicle(ParkingSpot spot, Vehicle vehicle);
    ParkingTicket bookParkingTicket(ParkingSpot spot, Vehicle vehicle);

}
