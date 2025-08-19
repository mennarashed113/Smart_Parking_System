package Model;

import java.util.Arrays;
import java.util.List;

public class ParkingSpot {
    private static long counterId=1;
    private String spotId;
    private SpotType spotType;
    private boolean isAvailable;

    public ParkingSpot( SpotType spotType, boolean isAvailable) {
        this.spotId = "S"+counterId++;
        this.spotType = spotType;
        this.isAvailable = isAvailable;

    }



    public String getSpotId() {
        return spotId;
    }

    public void setSpotId(String spotId) {
        this.spotId = spotId;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public void setSpotType(SpotType spotType) {
        this.spotType = spotType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }



}
