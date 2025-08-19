package Service.Impl;

import Model.Data.Tickets;
import Model.Data.VehicleData;
import Model.ParkingTicket;
import Service.Factory.FeesFactory;
import Service.Interfaces.CheckoutService;

import java.time.Duration;
import java.time.LocalDateTime;

public class CheckoutServiceImp implements CheckoutService {
    @Override
    public double checkout(ParkingTicket ticket) {
        LocalDateTime endtime = LocalDateTime.now();

        VehicleServiceImp service = FeesFactory.getVehicle(
                ticket.getVehicle().getType(),
                ticket.getVehicle().getPlateNumber(),
                ticket.getVehicle().getOwnerName()
        );

        long minutes = Duration.between(ticket.getStartTime(), endtime).toMinutes();

        // set final values first
        ticket.setEndTime(endtime);
        double fee = service.calculateFess(minutes);
        ticket.setFees(fee);
        VehicleData.vehiclesArray.remove(ticket.getVehicle());

        // update state
        ticket.getSpot().setAvailable(true);
        Tickets.activeTickets.remove(ticket);
        Tickets.historyTickets.add(ticket);

        return fee;
    }


    @Override
    public boolean validate(ParkingTicket ticket) {
        return Tickets.activeTickets.contains(ticket);
    }

    @Override
    public ParkingTicket getTicket(String tid) {
        return Tickets.activeTickets.stream().filter(t->t.getTicketId().equals(tid)).findFirst().orElse(null);
    }
}
