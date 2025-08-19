package Service.Factory;

import Model.*;

public class VehicleFactory {
    public static Vehicle createVehicle(String type, String plateNumber, String ownerName) {
        switch (type.toUpperCase()) {
            case "CAR":
                return new Car(plateNumber, ownerName,type);
            case "MOTORCYCLE":
                return new MotorCycle(plateNumber, ownerName,type);
            case "ELECTRIC":
                return new Electric(plateNumber, ownerName,type);
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + type);
        }
    }
}
