package Service.Impl;

public class MotorCycleServiceImp extends VehicleServiceImp {
    @Override
    public double calculateFess(long minutes) {
        long hours = minutes/60;
        if(minutes%60 !=0){
            hours++;
        }
        return hours * 3.0;
    }
}
