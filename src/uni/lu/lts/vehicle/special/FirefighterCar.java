/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.vehicle.special;

import uni.lu.lts.util.CountryCode;
import uni.lu.lts.vehicle.VehicleType;

/**
 *
 * @author asiron
 */
public class FirefighterCar extends SpecialVehicle {

    public FirefighterCar(VehicleType vehicleType, String numberPlate, CountryCode country, float size) {
        super(vehicleType, numberPlate, country, size);
    }
    
}
