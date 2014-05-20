/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.users;

import uni.lu.lts.util.CountryCode;
import uni.lu.lts.vehicle.Vehicle;
import uni.lu.lts.vehicle.VehicleFactory;
import uni.lu.lts.vehicle.VehicleType;
import uni.lu.lts.vehicle.special.SpecialVehicle;

/**
 *
 * @author asiron
 */
public class RegularUser extends Account implements VehicleOwner{

    private Vehicle registeredVehicle;
    
    public RegularUser(String username, String password) {
        super(username, password);
        permissions.add(Permission.READONLYSELF);
    }
    
    @Override
    public boolean registerVehicle(VehicleType type, String numberPlate, CountryCode country, float height) {
        Vehicle vehicle = VehicleFactory.createVehicle(type, numberPlate, country, height);
        if (vehicle instanceof SpecialVehicle) {
            System.out.print("Unable to register Special Vehicle for unprivileged user");
            return false;
        }
        registeredVehicle = vehicle;
        return true;
    }

    @Override
    public Vehicle getVehicle() {
        return registeredVehicle;
    }
}
