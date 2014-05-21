/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.vehicle;

import java.util.Random;
import java.util.Vector;
import uni.lu.lts.util.CountryCode;
import uni.lu.lts.util.RandomString;
import uni.lu.lts.vehicle.special.EmergencyCar;
import uni.lu.lts.vehicle.special.FirefighterCar;
import uni.lu.lts.vehicle.special.PoliceCar;

/**
 *
 * @author asiron
 */
public class VehicleFactory {
    
    private static Vector<Vehicle> createdVehicles = new Vector<>();

    public static Vehicle createVehicle(String type, String numberPlate, String country, float height) {
        VehicleType vehicleType = VehicleType.valueOf(type.toUpperCase());
        CountryCode countryCode = CountryCode.valueOf(country.toUpperCase());
        
        return VehicleFactory.createVehicle(vehicleType, numberPlate, countryCode, height);
    }
    
    public static Vehicle createVehicle(VehicleType type, String numberPlate, CountryCode country, float height) {
        
        if (type == null) {
            System.out.println("VehicleFactory class received unknown enum value of VehicleType");       
            return null;
        }
        
        Vehicle returnVal = null;
        switch (type) {
            case CAR:
                returnVal = new Car(type, numberPlate, country, height);
                break;
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
        
        if (returnVal != null) {
            if (createdVehicles == null) {
                createdVehicles = new Vector<>();
            }
            createdVehicles.add(returnVal);
        }
        
        return returnVal;
    }
    
    public static Vehicle createRandomVehicle() {
        
        Random r = new Random();

        Float vehicleHeight = new Float(1 + r.nextFloat() * 2.5);
        
        CountryCode country;
        String numberPlates;
        VehicleType vehicleType;
        
        if (r.nextFloat() < 0.1) {
            country = null;
        } else {
            country = CountryCode.randomCountry();
        }
        
        if (r.nextFloat() < 0.1) {
            numberPlates = null;
        } else {
            numberPlates = VehicleFactory.getRandomNumberPlate();     
        }
       
        vehicleType = VehicleType.randomVehicleType();
        
        return VehicleFactory.createVehicle(vehicleType, numberPlates, country, vehicleHeight);
    }
    
    public static Vehicle getRandomVehicle() {
        if (createdVehicles.isEmpty()) {
            VehicleFactory.createRandomVehicle();
        }
        return createdVehicles.get(new Random().nextInt(createdVehicles.size()));
    }
    
    public static String getRandomNumberPlate() {
        String numberPlates;
        while (true) {
            numberPlates = RandomString.generateAlphaNumbericString(6);
            if (isUniqueNumberPlate(numberPlates)) {
                break;
            }
        }           
        return numberPlates;
    }
    
    private static boolean isUniqueNumberPlate(String numberPlate) {
        for (Vehicle vehicle : VehicleFactory.createdVehicles) {
            if (vehicle.getNumberPlate() != null && vehicle.getNumberPlate().equals(numberPlate)) {
                return false;
            }
        }
        return true;
    }
}
