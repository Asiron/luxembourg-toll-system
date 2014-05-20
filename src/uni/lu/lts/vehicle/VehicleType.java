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
    MOTORBIKE, TRUCK, BUS, CAR, EMERGENCYCAR, POLICECAR, FIREFIGHTERCAR;
    
    private static final List<VehicleType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static int getSize() {
        return SIZE;
    }
    
    public static VehicleType randomVehicleType()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
