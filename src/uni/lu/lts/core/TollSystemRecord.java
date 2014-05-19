/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.core;

import java.util.Date;
import uni.lu.lts.vehicle.Vehicle;

/**
 *
 * @author asiron
 */
public class TollSystemRecord {
    
    private final Date timestamp;
    private final float calculatedPrice;
    private final Vehicle vehicleRef;

    public TollSystemRecord(Date timestamp, float calculatedPrice, Vehicle vehicleRef) {
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
    public Date getTimestamp() {
        return timestamp;
    }

}
