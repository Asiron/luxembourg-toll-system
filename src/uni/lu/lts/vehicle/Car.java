/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.vehicle;

import uni.lu.lts.util.CountryCode;

/**
 *
 * @author asiron
 */
public class Car extends Vehicle{

    public Car(VehicleType vehicleType, String numberPlate, CountryCode country, float height) {
        super(vehicleType, numberPlate, country, height);
    }
    
}
