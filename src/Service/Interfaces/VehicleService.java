package Service.Interfaces;

import Model.Vehicle;

public interface  VehicleService {
     Vehicle registerVehicle(Vehicle vehicle);
     Vehicle getVehicle(String plateNumber);

}
