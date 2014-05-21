package uni.lu.lts.core;
    
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import uni.lu.lts.facility.Section;
import uni.lu.lts.facility.TollFacility;
import uni.lu.lts.facility.sensor.Sensor;
import uni.lu.lts.util.RandomString;
import uni.lu.lts.vehicle.Vehicle;
import uni.lu.lts.vehicle.VehicleFactory;
import uni.lu.lts.vehicle.VehicleType;

/**
 *
 * @author asiron
 */
public class TollSimulator extends Thread {
    
    private boolean displayOutput = true;
    
    private Lock ltsLock;
    private LuxembourgTollSystem database;
    
    private Vector<Sensor> allSensors;
    
    public TollSimulator(Lock ltsLock, LuxembourgTollSystem database) {
        this.database = database;
        this.ltsLock = ltsLock;
        this.allSensors = new Vector<>();
    }

    public void setDisplayOutput(boolean displayOutput) {
        this.displayOutput = displayOutput;
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
                    Vehicle randomVehicle = VehicleFactory.getRandomVehicle();
                    Sensor  randomSensor  = getRandomSensor();
                    randomSensor.registerPassingByVehicle(randomVehicle);
                ltsLock.unlock();
                
                if (displayOutput) System.out.println("Toll: " + randomVehicle);
            }
            if (new Random().nextFloat() < 0.3) {
                ltsLock.lock();
                    Vehicle newVehicle = VehicleFactory.createRandomVehicle();
                ltsLock.unlock();
                
                if (displayOutput) System.out.println("Created: " + newVehicle);
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
    
    private Sensor getRandomSensor() {
        return allSensors.get(new Random().nextInt(allSensors.size()));
    }
}
