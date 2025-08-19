package Core;

import Model.ParkingSpot;
import Model.ParkingTicket;
import Model.Vehicle;
import Service.Factory.FeesFactory;
import Service.Factory.VehicleFactory;
import Service.Impl.CheckoutServiceImp;
import Service.Impl.ParkingServiceImp;
import Service.Impl.ReportServiceImp;
import Service.Impl.VehicleServiceImp;
import Service.Interfaces.CheckoutService;
import Service.Interfaces.ParkingService;
import Service.Interfaces.ReportService;
import Service.Interfaces.VehicleService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class App {

    public static void run() {
        Scanner sc = new Scanner(System.in);
        ParkingService parkingService = new ParkingServiceImp();
        CheckoutService checkoutService = new CheckoutServiceImp();
        ReportService reportService = new ReportServiceImp();

        boolean running = true;
        while (running) {
            printMenu();
            String input = sc.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input, enter a number.");
                continue;
            }

            try {
                switch (choice) {
                    case 1 -> handleRegisterAndPark(sc, parkingService);
                    case 2 -> showAvailableSpots(parkingService);
                    case 3 -> handleCheckout(sc, checkoutService);
                    case 5 -> showDailyRevenue(sc, reportService);
                    case 6 -> showTicketHistory(reportService);
                    case 0 -> running = false;
                    default -> System.out.println("❌ Invalid option");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n==== Smart Parking Lot ====");
        System.out.println("1. Register Vehicle & Park");
        System.out.println("2. List Available Spots");
        System.out.println("3. Checkout Vehicle");
        System.out.println("5. Daily Revenue Report");
        System.out.println("6. Tickets History");
        System.out.println("0. Exit");
        System.out.print("Select option: ");
    }

    private static void handleRegisterAndPark(Scanner sc, ParkingService parkingService) {
        System.out.print("Enter plate number: ");
        String plate = sc.nextLine().trim();
        System.out.print("Enter owner name: ");
        String owner = sc.nextLine().trim();
        System.out.print("Enter type (CAR/MOTORCYCLE/ELECTRIC): ");
        String type = sc.nextLine().trim();

        Vehicle vehicle;
        try {
            vehicle = VehicleFactory.createVehicle(type, plate, owner);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
            return;
        }

        VehicleServiceImp register = FeesFactory.getVehicle(type, plate, owner);
        if (register.registerVehicle(vehicle) == null) {
            System.out.println("❌ Vehicle already registered.");
            return;
        }

        System.out.println("✅ Registered vehicle: " + vehicle.getPlateNumber());

        List<ParkingSpot> spots = parkingService.getAvailableParkingSpots(type);
        if (spots.isEmpty()) {
            System.out.println("❌ No available spots for type " + type);
            return;
        }

        System.out.println("Available spots for " + type + ":");
        spots.forEach(s -> System.out.println(
                " - Spot ID: " + s.getSpotId() +
                        " | Type: " + s.getSpotType() +
                        " | Available: " + (s.isAvailable() ? "Yes" : "No")
        ));

        ParkingSpot chosenSpot = null;
        while (chosenSpot == null) {
            System.out.print("Enter Spot ID to park: ");
            String chosenId = sc.nextLine().trim();

            Optional<ParkingSpot> optionalSpot = spots.stream()
                    .filter(s -> s.getSpotId().equalsIgnoreCase(chosenId))
                    .findFirst();

            if (optionalSpot.isPresent()) {
                chosenSpot = optionalSpot.get();
            } else {
                System.out.println("❌ Invalid spot ID, try again.");
            }
        }

        ParkingTicket ticket = parkingService.parkVehicle(chosenSpot, vehicle);
        System.out.println("🚗 Parked " + plate + " in spot " +
                ticket.getSpot().getSpotId() + " (Ticket " + ticket.getTicketId() + ")");
    }

    private static void showAvailableSpots(ParkingService parkingService) {
        System.out.println("=== Available Spots ===");
        List<ParkingSpot> available = parkingService.getAvailableParkingSpots();
        if (available.isEmpty()) {
            System.out.println("No spots available.");
            return;
        }
        available.forEach(spot -> System.out.println(
                "Spot ID: " + spot.getSpotId() +
                        " | Type: " + spot.getSpotType() +
                        " | Available: " + spot.isAvailable()
        ));
    }

    private static void handleCheckout(Scanner sc, CheckoutService checkoutService) {
        System.out.print("Enter ticket ID: ");
        String tid = sc.nextLine().trim();

        ParkingTicket exist = checkoutService.getTicket(tid);
        if (exist == null) {
            System.out.println("❌ Invalid ticket ID");
            return;
        }

        double fees = checkoutService.checkout(exist);
        System.out.println("✅ Checked out " + exist.getVehicle().getPlateNumber() +
                ". Fee = $" + fees);
    }

    private static void showDailyRevenue(Scanner sc, ReportService reportService) {
        System.out.print("Enter date (YYYY-MM-DD): ");
        try {
            LocalDate date = LocalDate.parse(sc.nextLine().trim());
            double revenue = reportService.DailyRevenue(date);
            System.out.println("💰 Total revenue for " + date + " = $" + revenue);
        } catch (DateTimeParseException e) {
            System.out.println("❌ Invalid date format. Use YYYY-MM-DD.");
        }
    }

    private static void showTicketHistory(ReportService reportService) {
        System.out.println("=== Tickets History ===");
        List<ParkingTicket> history = reportService.HistoryTicket();

        if (history.isEmpty()) {
            System.out.println("No tickets found.");
            return;
        }

        history.stream()
                .sorted(Comparator.comparing(ParkingTicket::getStartTime))
                .map(t -> String.format(
                        "Ticket ID: %s | Vehicle: %s | Type: %s | Start: %s | End: %s | Fee: $%.2f",
                        t.getTicketId(),
                        t.getVehicle().getPlateNumber(),
                        t.getVehicle().getType(),
                        t.getStartTime(),
                        t.getEndTime(),
                        t.getFees()))
                .forEach(System.out::println);
    }
}
