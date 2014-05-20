package uni.lu.lts.core;
    
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;
import uni.lu.lts.facility.Section;
import uni.lu.lts.facility.TollFacility;
import uni.lu.lts.facility.sensor.Sensor;
import uni.lu.lts.util.CountryCode;
import uni.lu.lts.util.RandomString;
import uni.lu.lts.vehicle.Vehicle;
import uni.lu.lts.vehicle.VehicleFactory;
import uni.lu.lts.vehicle.VehicleType;


/**
 *
 * @author asiron
 */
public class TollSimulator extends Thread {
    
    private Lock ltsLock;
    private LuxembourgTollSystem database;
    
    private Vector<Sensor> allSensors;
    private Vector<Vehicle> simulationVehicles;
    
    public TollSimulator(Lock ltsLock, LuxembourgTollSystem database) {
        this.database = database;
        this.ltsLock = ltsLock;
        this.allSensors = new Vector<>();
        this.simulationVehicles = new Vector<>();
        this.simulationVehicles.add(createRandomVehicle());
    }
    
    @Override
    public void run() {
        generateMap();
        while (!interrupted()) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {
                System.out.println("Interrupt exception during running TollSimulator");
            }
            
            if (new Random().nextFloat() < 0.3) {
                ltsLock.lock();
                Vehicle randomVehicle = getRandomVehicle();
                Sensor  randomSensor  = getRandomSensor();
                randomSensor.registerPassingByVehicle(randomVehicle);
                ltsLock.unlock();
                System.out.println("Toll: " +randomVehicle);
            }
            if (new Random().nextFloat() < 0.3) {
                ltsLock.lock();
                Vehicle newVehicle = createRandomVehicle();
                simulationVehicles.add(newVehicle);
                ltsLock.unlock();
                System.out.println("Created: " + newVehicle);
            }
        }
    }
    
    private void generateMap() {
        Random r = new Random();
        
        ltsLock.lock();     
        for (int i = 0; i < r.nextInt(9)+1; i++) {
            String newSectionName = RandomString.generateAlphaString(10);
            Section newSection    = new Section(newSectionName);
            database.getSections().put(newSectionName, newSection);
            for (int j = 0; j < r.nextInt(9)+1; j++) {
                String newFacilityName = RandomString.generateAlphaString(10);
                TollFacility facility = new TollFacility(newSection, newFacilityName);
                newSection.getTollFacilities().put(newFacilityName, facility);
                for (int k = 0; k < r.nextInt(4)+1; k++) {
                    Float maxVehicleHeight = 2 + r.nextFloat() * 2;
                    Set<VehicleType> allowedTypes = new HashSet<>();
                
                    for (int l = 0; l < VehicleType.getSize(); l++) {
                        allowedTypes.add(VehicleType.randomVehicleType());
                    }
                    Sensor sensor = new Sensor(k, maxVehicleHeight, true, allowedTypes);
                    facility.getSensors().put(k, sensor);
                    allSensors.add(sensor);
                }
            }
        }  
        ltsLock.unlock();
    }
    
    private Vehicle createRandomVehicle() {
        
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
            numberPlates = RandomString.generateAlphaNumbericString(6);
        }
       
        vehicleType = VehicleType.randomVehicleType();
        
        return VehicleFactory.createVehicle(vehicleType, numberPlates, country, vehicleHeight);
        
    }    
    private Vehicle getRandomVehicle() {
        return simulationVehicles.get(new Random().nextInt(simulationVehicles.size()));
    }
    
    private Sensor getRandomSensor() {
        return allSensors.get(new Random().nextInt(allSensors.size()));
    }
}
