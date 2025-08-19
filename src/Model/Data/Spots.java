package Model.Data;

import Model.ParkingSpot;
import Model.SpotType;

import java.util.Arrays;
import java.util.List;

public class Spots {
    public  static List<ParkingSpot> spots = Arrays.asList(
            new ParkingSpot( SpotType.CAR, true),
            new ParkingSpot( SpotType.CAR, true),
            new ParkingSpot( SpotType.CAR, true),
            new ParkingSpot( SpotType.CAR, true),
            new ParkingSpot( SpotType.CAR, true),

            new ParkingSpot( SpotType.MOTORCYCLE, true),
            new ParkingSpot( SpotType.MOTORCYCLE, true),
            new ParkingSpot( SpotType.MOTORCYCLE, true),
            new ParkingSpot( SpotType.MOTORCYCLE, true),
            new ParkingSpot( SpotType.MOTORCYCLE, true),

            new ParkingSpot( SpotType.ELECTRIC, true),
            new ParkingSpot( SpotType.ELECTRIC, true),
            new ParkingSpot( SpotType.ELECTRIC, true)
    );

    public static List<ParkingSpot> getSpots() {
        return spots;
    }
}
