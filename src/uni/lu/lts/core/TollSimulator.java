package uni.lu.lts.core;
    
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import uni.lu.lts.facility.Section;
import uni.lu.lts.facility.TollFacility;
import uni.lu.lts.facility.sensor.Sensor;
import uni.lu.lts.util.RandomString;
import uni.lu.lts.vehicle.VehicleType;


/**
 *
 * @author asiron
 */
public class TollSimulator extends Thread {
    
    private Lock ltsLock;
    private LuxembourgTollSystem database;
    
    public TollSimulator(Lock ltsLock, LuxembourgTollSystem database) {
        this.database = database;
        this.ltsLock = ltsLock;
    }
    
    @Override
    public void run() {
        generateMap();
        int a = 1;
        while (true) {
            
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
                }
            }
        }  
        ltsLock.unlock();
    }
    
    
}
