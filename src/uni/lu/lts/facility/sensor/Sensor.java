/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.facility.sensor;

import java.util.Date;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
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
    private Queue<ImmutablePair<Vehicle, Date>> passedByVehicleBuffer;
    private Set<VehicleType> allowedTypes;

    public Sensor(Integer sensorID, float maxVehicleHeight, Boolean active, Set<VehicleType> allowedTypes) {
        this.sensorID = sensorID;
        this.active = active;
        this.maxVehicleHeight = maxVehicleHeight;
        this.allowedTypes = allowedTypes;
        this.passedByVehicleBuffer = new ConcurrentLinkedQueue<>();
    }

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
     * Add allowed type of vehicle to the set of allowed types
     * 
     * @param vehicleType the value of added allowed type
     */
    public void addAllowedType(VehicleType vehicleType) {
        allowedTypes.add(vehicleType);
    }
    
    /**
     * Remove allowed type of vehicle from the set of allowed types
     * 
     * @param vehicleType the value of added allowed type
     */
    public boolean removeAllowedType(VehicleType vehicleType) {
        return allowedTypes.add(vehicleType);
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

    /**
     * If sensor is active then put the measurement into buffer
     * delegate testing for errors to TollFacility, since sensor is lightweight HW object
     * @param vehicle vehicle passing by
     */
    public void registerPassingByVehicle(Vehicle vehicle) {
        if (isActive()) {
            ImmutablePair<Vehicle, Date> record = new ImmutablePair<Vehicle, Date>(vehicle, new Date());
            this.passedByVehicleBuffer.add(record);
        }
    }
    
    /**
     * Checks if queue buffer is empty
     * @return true if buffer is empty
     */
    public boolean isBufferEmpty() {
        return passedByVehicleBuffer.isEmpty();
    }
    
    public ImmutablePair<Vehicle, Date> getFirstFromBuffer() {
        return passedByVehicleBuffer.poll();
    }
}
