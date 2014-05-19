/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.users;

import uni.lu.lts.vehicle.Vehicle;
import uni.lu.lts.vehicle.VehicleType;

/**
 *
 * @author asiron
 */
public interface VehicleOwner {    
    public boolean registerVehicle(VehicleType type, String numberPlate, CountryCode country, float height);
    public Vehicle getVehicle();
}
