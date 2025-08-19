# Smart Parking Lot Management System 🚗

## Overview
This project implements a **Smart Parking Lot Management System** to manage parking spots, vehicles, billing, and checkout operations. The system is console-based and demonstrates **OOP principles**, **SOLID design**, and a **model-service separation architecture**.

---

## Features

- **Vehicle Registration**
  - Register new vehicles with a unique plate number, type, and owner name.
  - Vehicle types supported: `CAR`, `MOTORCYCLE`, `ELECTRIC`.

- **Parking Spot Management**
  - Auto-generated spot IDs (e.g., S0, S1, S2…).
  - Each spot has a type (`CAR`, `MOTORCYCLE`, `ELECTRIC`) and availability status.
  - Vehicles can only park in a spot of the matching type.

- **Parking & Checkout**
  - Park a vehicle into an available spot.
  - Checkout calculates total fees based on parking duration.
  - Parking fees per hour:
    - CAR: $5/hour
    - MOTORCYCLE: $3/hour
    - ELECTRIC: $4/hour (first hour free)

- **Reports**
  - Daily revenue report.
  - Parking ticket history.
  - Search vehicle history by plate number (optional).

- **Error Handling**
  - No available spot for the vehicle type.
  - Vehicle already parked.
  - Invalid ticket ID at checkout.

---

## Models (Entities)

- **Vehicle**
  - `plateNumber` (String, unique)
  - `type` (Enum: CAR, MOTORCYCLE, ELECTRIC)
  - `ownerName` (String)

- **ParkingSpot**
  - `spotId` (String, unique)
  - `type` (Enum: CAR, MOTORCYCLE, ELECTRIC)
  - `isAvailable` (boolean)

- **ParkingTicket**
  - `ticketId` (auto-increment)
  - `vehicle` (Vehicle object)
  - `spot` (ParkingSpot object)
  - `startTime` (DateTime)
  - `endTime` (DateTime, nullable until checkout)
  - `fee` (double, calculated on checkout)

---

## Services

- **VehicleService**
  - Register a new vehicle
  - Find a vehicle by plate number

- **ParkingService**
  - Park a vehicle (assign spot, generate ticket)
  - List available spots by type
  - Checkout vehicle (free spot, calculate fee, close ticket)
  - Get ticket details

- **ReportService**
  - Daily revenue
  - Ticket history

---


---

## Technical Details

- Language: **Java**
- Enum usage forSpotType.
- Models and Services are separated.
- OOP Concepts
- Data Seeders
- Auto-generated IDs:
  - Ticket ID (`Ticket 1, Ticket 2…`)
  - Spot ID (`S1, S2…`)
- Billing supports fractional hours.
- Parking history stored for reporting.
- SOLID principles followed.



---

## Advanced Features

- Search vehicle history by plate number.
- Daily revenue reporting.

---

## Getting Started

1. Clone this repository:

```bash
git clone https://github.com/mennarashed113/Smart_Parking_System.git


