/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.vehicle;

import uni.lu.lts.vehicle.special.EmergencyCar;
import uni.lu.lts.vehicle.special.FirefighterCar;
import uni.lu.lts.vehicle.special.PoliceCar;

/**
 *
 * @author asiron
 */
public class VehicleFactory {
    
    public static Vehicle createVehicle(VehicleType type, String numberPlate, String country, float height) {
       Vehicle returnVal = null;
        switch (type) {
            case MOTORBIKE:
                returnVal = new Motorbike(type, numberPlate, country, height);
                break;
            case BUS:
                returnVal = new Bus(type, numberPlate, country, height);
                break;
            case TRUCK:
                returnVal = new Truck(type, numberPlate, country, height);
                break;
            case EMERGENCYCAR:
                returnVal = new  EmergencyCar(type, numberPlate, country, height);
                break;
            case FIREFIGHTERCAR:
                returnVal = new  FirefighterCar(type, numberPlate, country, height);
                break;
            case POLICECAR:
                returnVal = new PoliceCar(type, numberPlate, country, height);
                break;
            default:
                System.out.println("VehicleFactory class received unknown enum value of VehicleType");       
        }
        return returnVal;
    }
}
