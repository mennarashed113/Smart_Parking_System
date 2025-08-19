package Service.Interfaces;

import Model.ParkingSpot;
import Model.ParkingTicket;
import Model.Vehicle;

public interface CheckoutService {
    double checkout(ParkingTicket ticket) ;
    boolean validate(ParkingTicket ticket);
    ParkingTicket getTicket(String tid);
}
