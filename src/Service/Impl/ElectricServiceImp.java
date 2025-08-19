package Service.Impl;

public class ElectricServiceImp extends VehicleServiceImp {
    @Override
    public double calculateFess(long minutes) {
         long hours = minutes/60;
         if(minutes%60 !=0){
             hours++;
         }
         if(hours ==1){
             return 0.0;
         }
         return  (hours - 1) * 4.0;
    }
}
