/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.core;

import java.sql.Timestamp;
import uni.lu.lts.vehicle.Vehicle;

/**
 *
 * @author asiron
 */
public class TollSystemRecord {
    
    private Timestamp timestamp;
    private float calculatedPrice;
    private Vehicle vehicleRef;

    public TollSystemRecord(Timestamp timestamp, float calculatedPrice, Vehicle vehicleRef) {
        this.timestamp = timestamp;
        this.calculatedPrice = calculatedPrice;
        this.vehicleRef = vehicleRef;
    }
 
    /**
     * Get the value of vehicleRef
     *
     * @return the value of vehicleRef
     */
    public Vehicle getVehicleRef() {
        return vehicleRef;
    }

    /**
     * Get the value of calculatedPrice
     *
     * @return the value of calculatedPrice
     */
    public float getCalculatedPrice() {
        return calculatedPrice;
    }

    /**
     * Get the value of timestamp
     *
     * @return the value of timestamp
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

}
