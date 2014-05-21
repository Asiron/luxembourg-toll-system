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
public class RegularUser extends Account implements VehicleOwner {

    private Vehicle registeredVehicle;

    public RegularUser(AccountType type, String username, String password) {
        super(type, username, password);
        permissions.add(Permission.READONLYSELF);
        permissions.add(Permission.REGISTERVEHICLE);
    }
    
    @Override
    public boolean registerVehicle(String type, String numberPlate, String country, float height) {
           
        Vehicle vehicle = VehicleFactory.createVehicle(type, numberPlate, country, height);
        if (vehicle instanceof SpecialVehicle) {
            System.out.println("Unable to register Special Vehicle for unprivileged user");
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
