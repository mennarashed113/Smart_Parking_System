package Service.Impl;

import Model.Data.VehicleData;
import Model.Vehicle;
import Service.Interfaces.VehicleService;

public abstract class VehicleServiceImp implements VehicleService {

    @Override
    public Vehicle registerVehicle(Vehicle vehicle) {
        boolean isExists = VehicleData.vehiclesArray.contains(vehicle);
        if(isExists){
            return null;
        }
       VehicleData.vehiclesArray.add(vehicle);
        return vehicle;
    }

    @Override
    public Vehicle getVehicle(String plateNumber) {

        for( Vehicle v : VehicleData.vehiclesArray){
            if(v.getPlateNumber().equals(plateNumber)){
                return v;
            }
        }
        return null;

    }

    public abstract double calculateFess(long minutes);


}
