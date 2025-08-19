package Service.Impl;

import Model.Data.Spots;
import Model.Data.Tickets;
import Model.ParkingSpot;
import Model.ParkingTicket;
import Model.Vehicle;
import Service.Interfaces.ParkingService;

import java.util.List;

public class ParkingServiceImp implements ParkingService {
    @Override
    public List<ParkingSpot> getAvailableParkingSpots() {
        return Spots.getSpots().stream().filter(ParkingSpot::isAvailable).toList();
    }

    @Override
    public List<ParkingSpot> getAvailableParkingSpots(String VehicleType) {
        return Spots.getSpots().stream().filter(s->s.isAvailable()&& s.getSpotType().name().equals(VehicleType)).toList();
    }



    @Override
    public boolean checkAvailabilitySpot(ParkingSpot spot) {
       return Spots.getSpots().contains(spot) && spot.isAvailable();
    }

    @Override
    public ParkingTicket parkVehicle(ParkingSpot spot, Vehicle vehicle) {

     boolean checkAvailability = checkAvailabilitySpot(spot);
     if(!checkAvailability){
         return null;
     }
        spot.setAvailable(false);
        return bookParkingTicket(spot, vehicle);


    }

    @Override
    public ParkingTicket bookParkingTicket(ParkingSpot spot, Vehicle vehicle) {
        ParkingTicket ticket = new ParkingTicket(vehicle, spot);
        Tickets.activeTickets.add(ticket);
        return ticket;
    }


}
