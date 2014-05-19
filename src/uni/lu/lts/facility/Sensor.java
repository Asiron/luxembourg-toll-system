/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.facility;

import java.sql.Timestamp;
import java.util.Queue;
import uni.lu.lts.util.Pair;
import uni.lu.lts.vehicle.Vehicle;

/**
 *
 * @author asiron
 */
public class Sensor {
    
    private Integer sensorID;
    private float maxVehicleHeight;
    private Boolean isActive;
    private Queue<Pair<Vehicle, Timestamp>> passedByVehicleBuffer;


    /**
     * Get the value of isActive
     *
     * @return the value of isActive
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * Set the value of isActive
     *
     * @param isActive new value of isActive
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Get the value of maxVehicleHeight
     *
     * @return the value of maxVehicleHeight
     */
    public float getMaxVehicleHeight() {
        return maxVehicleHeight;
    }

    /**
     * Set the value of maxVehicleHeight
     *
     * @param maxVehicleHeight new value of maxVehicleHeight
     */
    public void setMaxVehicleHeight(float maxVehicleHeight) {
        this.maxVehicleHeight = maxVehicleHeight;
    }

    /**
     * Get the value of sensorID
     *
     * @return the value of sensorID
     */
    public Integer getSensorID() {
        return sensorID;
    }

    /**
     * Set the value of sensorID
     *
     * @param sensorID new value of sensorID
     */
    public void setSensorID(Integer sensorID) {
        this.sensorID = sensorID;
    }

}
