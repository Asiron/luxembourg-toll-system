package uni.lu.lts.vehicle;

import uni.lu.lts.util.CountryCode;

/**
 *
 * @author asiron
 */
public class Truck extends Vehicle {

    public Truck(VehicleType vehicleType, String numberPlate, CountryCode country, float height) {
        super(vehicleType, numberPlate, country, height);
    }
    
}