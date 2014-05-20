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
public class PoliceCar extends SpecialVehicle{

    public PoliceCar(VehicleType type, String numberPlate, CountryCode country, float height) {
        super(type, numberPlate, country, height);
    }
    
}
