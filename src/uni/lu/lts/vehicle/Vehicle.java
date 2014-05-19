package uni.lu.lts.vehicle;

import uni.lu.lts.TollFacility;

public class Vehicle {

    private VehicleType vehicleType;
    private TollFacility latestPointPassed = null;		// reference to last toll facility that vehicle passed through
    private String numberPlate;				// number plate of the vehicle
    private String country;					// country of the vehicle
    private float height;

    public Vehicle(VehicleType vehicleType, String numberPlate, String country, float height) {
            this.vehicleType = vehicleType;
            this.numberPlate = numberPlate;
            this.country = country;
            this.height = height;
    }

    public void setLatestPointPassed(TollFacility latestPointPassed) {
        this.latestPointPassed = latestPointPassed;
    }

    public TollFacility getLatestPointPassed() {
        return latestPointPassed;
    }
    
    
}
