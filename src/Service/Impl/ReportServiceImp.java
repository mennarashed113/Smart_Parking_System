package Service.Impl;

import Model.Data.Tickets;
import Model.ParkingTicket;
import Service.Interfaces.ReportService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReportServiceImp implements ReportService {

    @Override
    public List<ParkingTicket> HistoryTicket() {

        return Tickets.historyTickets;
    }

    @Override
    public double DailyRevenue(LocalDate date) {
        return Tickets.historyTickets.stream()
                .filter(t -> t.getEndTime() != null &&
                        t.getEndTime().toLocalDate().equals(date))
                .mapToDouble(ParkingTicket::getFees)
                .sum();
    }

}
