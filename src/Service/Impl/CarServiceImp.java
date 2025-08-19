package Service.Impl;

public class CarServiceImp extends VehicleServiceImp {
    @Override
    public double calculateFess(long minutes) {
     long hours = minutes/60;
     if(minutes%60 !=0){
         hours++;
     }
     return hours * 5.0;

    }
}
