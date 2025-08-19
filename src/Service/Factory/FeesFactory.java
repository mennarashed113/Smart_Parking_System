package Service.Factory;

import Service.Impl.CarServiceImp;
import Service.Impl.ElectricServiceImp;
import Service.Impl.MotorCycleServiceImp;
import Service.Impl.VehicleServiceImp;

public class FeesFactory {
    public static VehicleServiceImp getVehicle(String type, String plateNumber, String ownerName) {
        switch (type.toUpperCase()) {
            case "CAR":
                return new CarServiceImp();
            case "MOTORCYCLE":
                return new MotorCycleServiceImp();
            case "ELECTRIC":
                return new ElectricServiceImp();
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + type);
        }
    }
}
