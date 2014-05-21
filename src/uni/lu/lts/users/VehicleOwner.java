/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.users;

import uni.lu.lts.vehicle.Vehicle;

/**
 *
 * @author asiron
 */
public interface VehicleOwner {    
    public boolean registerVehicle(String type, String numberPlate, String country, float height);
    public Vehicle getVehicle();
}
