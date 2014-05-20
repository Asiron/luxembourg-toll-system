/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.facility;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import uni.lu.lts.facility.record.SensorReadingError;
import uni.lu.lts.facility.record.TollSystemRecord;
import uni.lu.lts.facility.sensor.ErrorType;
import uni.lu.lts.facility.sensor.Image;
import uni.lu.lts.facility.sensor.Sensor;
import uni.lu.lts.util.ImmutablePair;
import uni.lu.lts.vehicle.Vehicle;
import uni.lu.lts.vehicle.VehicleType;

/**
 *
 * @author asiron
 */
public class TollFacility {
    
    private Section section;
    private String facilityID;
    private Map<Integer, Sensor> sensors;
    private List<TollSystemRecord> records;
    private List<SensorReadingError> errorList;

    public TollFacility(Section section, String facilityID) {
        this.section    = section;
        this.facilityID = facilityID;
        this.sensors    = new HashMap<>();
        this.records    = new ArrayList<>();
        this.errorList  = new ArrayList<>(); 
    }

    /**
     * Get the value of errorList
     *
     * @return the value of errorList
     */
    public List<SensorReadingError> getErrorList() {
        return errorList;
    }

    /**
     * Set the value of errorList
     *
     * @param errorList new value of errorList
     */
    public void setErrorList(List<SensorReadingError> errorList) {
        this.errorList = errorList;
    }

    /**
     * Get the value of records
     *
     * @return the value of records
     */
    public List<TollSystemRecord> getRecords() {
        return records;
    }

    /**
     * Set the value of records
     *
     * @param records new value of records
     */
    public void setRecords(List<TollSystemRecord> records) {
        this.records = records;
    }

    /**
     * Get the value of sensors
     *
     * @return the value of sensors
     */
    public Map<Integer, Sensor> getSensors() {
        return sensors;
    }

    /**
     * Set the value of sensors
     *
     * @param sensors new value of sensors
     */
    public void setSensors(Map<Integer, Sensor> sensors) {
        this.sensors = sensors;
    }

    /**
     * Get the value of facilityID
     *
     * @return the value of facilityID
     */
    public String getFacilityID() {
        return facilityID;
    }

    /**
     * Set the value of facilityID
     *
     * @param facilityID new value of facilityID
     */
    public void setFacilityID(String facilityID) {
        this.facilityID = facilityID;
    }

    /**
     * Get the value of section
     *
     * @return the value of section
     */
    public Section getSection() {
        return section;
    }

    /**
     * Set the value of section
     *
     * @param section new value of section
     */
    public void setSection(Section section) {
        this.section = section;
    }
 
    /**
     * Pulls buffer queues from all sensor belonging to this TollFacility
     */
    public void pullBuffersFromSensorQueues() {
        for (Map.Entry<Integer, Sensor> entry : sensors.entrySet()) {
            Integer sensorID = entry.getKey();
            Sensor  sensor   = entry.getValue();
            
            while(sensor.isActive() && !sensor.isBufferEmpty()) {
                ImmutablePair<Vehicle, Date> reading = sensor.getFirstFromBuffer();
                ErrorType error = checkSensorReading(sensor, reading);
                
                if (error == ErrorType.NOERROR) {
                    
                    reading.getFirst().setLatestPointPassed(this);
                    Float price = section.getPrice(reading.getFirst().getVehicleType());  
                    records.add( new TollSystemRecord(price, reading.getFirst(), sensorID, reading.getSecond(), new Image()));
                } else {
                    errorList.add ( new SensorReadingError(error, sensorID, new Date(), new Image()));
                }
            }
        }
    }
    
    private ErrorType checkSensorReading(Sensor sensor, ImmutablePair<Vehicle, Date> reading) {
        
        float maxVehicleHeight = sensor.getMaxVehicleHeight();
        Set<VehicleType> allowedTypes = sensor.getAllowedTypes();
      
        ErrorType retValue = ErrorType.NOERROR;
        
        if (reading.getSecond().getTime() > new Date().getTime()) {
            // reading from the future
            retValue = ErrorType.RECORDINGFROMFUTURE;
        } else if (allowedTypes.contains(reading.getFirst().getVehicleType()) == false) {
            // unallowed vehicle type
            retValue = ErrorType.UNALLOWEDVEHICLETYPE;
        } else if (maxVehicleHeight < reading.getFirst().getHeight()) {
            // vehicle's height surpassing limit
            retValue = ErrorType.VEHHEIGHTSURPASSES;
        } else if (reading.getFirst().getCountry() == null) {
            // country unspecified
            retValue = ErrorType.UNKNOWNCOUNTRY;
        } else if (reading.getFirst().getNumberPlate() == null) {
            // license plate unspecified
            retValue = ErrorType.UNKNOWNPLATES;
        }
        
        return retValue;
    }

}
