/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.vehicle;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author asiron
 */
public enum VehicleType {
    MOTORBIKE ("Motorbike"),
    TRUCK ("Truck"),
    BUS ("Bus"),
    CAR ("Car"),
    EMERGENCYCAR ("Emergency car"),
    POLICECAR ("Police car"),
    FIREFIGHTERCAR ("Firefighter car");
    
    private final String fullname;
    
    private static final List<VehicleType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    private VehicleType(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return fullname;
    }
    
    
    public static int getSize() {
        return SIZE;
    }
    
    public static VehicleType randomVehicleType()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
