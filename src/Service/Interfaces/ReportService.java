package Service.Interfaces;

import Model.ParkingTicket;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReportService {
    List<ParkingTicket> HistoryTicket();
    double DailyRevenue(LocalDate date);


}
