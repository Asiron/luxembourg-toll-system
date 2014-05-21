/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.users;

import uni.lu.lts.vehicle.Vehicle;
import uni.lu.lts.vehicle.VehicleFactory;
import uni.lu.lts.vehicle.special.SpecialVehicle;

/**
 *
 * @author asiron
 */
public class PrivilegedUser extends Account implements VehicleOwner{

    private Vehicle registeredVehicle = null;

    public PrivilegedUser(AccountType type, String username, String password) {
        super(type, username, password);
        permissions.add(Permission.READALL);
        permissions.add(Permission.REGISTERVEHICLE);
    }
    
    @Override
    public boolean registerVehicle(String type, String numberPlate, String country, float height) {
        
        Vehicle vehicle = VehicleFactory.createVehicle(type, numberPlate, country, height);
        if (!(vehicle instanceof SpecialVehicle)) {
            System.out.println("Unable to register not Special Vehicle for privileged user");
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
