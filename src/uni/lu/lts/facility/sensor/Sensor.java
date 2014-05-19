/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.facility.sensor;

import java.sql.Timestamp;
import java.util.Queue;
import java.util.Set;
import uni.lu.lts.util.ImmutablePair;
import uni.lu.lts.vehicle.Vehicle;
import uni.lu.lts.vehicle.VehicleType;

/**
 *
 * @author asiron
 */
public class Sensor {
    
    private Integer sensorID;
    private float maxVehicleHeight;
    private Boolean active;
    private Queue<ImmutablePair<Vehicle, Timestamp>> passedByVehicleBuffer;
    private Set<VehicleType> allowedTypes;

    /**
     * Get the value of allowedTypes
     *
     * @return the value of allowedTypes
     */
    public Set<VehicleType> getAllowedTypes() {
        return allowedTypes;
    }

    /**
     * Set the value of allowedTypes
     *
     * @param allowedTypes new value of allowedTypes
     */
    public void setAllowedTypes(Set<VehicleType> allowedTypes) {
        this.allowedTypes = allowedTypes;
    }


    /**
     * Get the value of isActive
     *
     * @return the value of isActive
     */
    public Boolean isActive() {
        return active;
    }

    /**
     * Set the value of isActive
     *
     * @param isActive new value of isActive
     */
    public void setActive(Boolean active) {
        this.active = active;
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
