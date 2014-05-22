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
import uni.lu.lts.util.CountryCode;
import uni.lu.lts.util.ImmutablePair;
import uni.lu.lts.vehicle.Vehicle;
import uni.lu.lts.vehicle.VehicleType;

/**
 *
 * @author asiron
 */
public class TollFacility implements Comparable<TollFacility> {
    
    private Section section;
    private String facilityID;
    private Map<Integer, Sensor> sensors;
    private List<SensorReadingError> errorList;
    private Map<String, List<TollSystemRecord>> records;

    public TollFacility(Section section, String facilityID) {
        this.section    = section;
        this.facilityID = facilityID;
        this.sensors    = new HashMap<>();
        this.errorList  = new ArrayList<>(); 
        this.records    = new HashMap<>();
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
    public Map<String, List<TollSystemRecord>> getRecords() {
        return records;
    }

    /**
     * Set the value of records
     *
     * @param records new value of records
     */
    public void setRecords(Map<String, List<TollSystemRecord>>  records) {
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
                    String numberPlate = reading.getFirst().getNumberPlate();
                    
                    TollSystemRecord newRecord = new TollSystemRecord(price, reading.getFirst()
                            , section, this, sensorID, new Date(), null);
                    
                    if (!records.containsKey(numberPlate)) {
                        records.put(numberPlate, new ArrayList<TollSystemRecord>());
                    }  
                    records.get(numberPlate).add(newRecord);
                    
                } else {
                    errorList.add( new SensorReadingError(error, section, this, sensorID, new Date(), null));
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
        } else if (reading.getFirst().getCountry() == CountryCode.UNKNOWN) {
            // country unspecified
            retValue = ErrorType.UNKNOWNCOUNTRY;
        } else if (reading.getFirst().getNumberPlate() == "UNKNOWN") {
            // license plate unspecified
            retValue = ErrorType.UNKNOWNPLATES;
        }
        
        return retValue;
    }

    @Override
    public String toString() {
        return facilityID;
    }

    @Override
    public int compareTo(TollFacility o) {
        return facilityID.compareTo(o.getFacilityID());
    }
    
        public boolean checkConditions(String[] conditions) {
        for (String condition : conditions) {
            String[] operands = condition.split("=");
            String leftOperand  = operands[0].toLowerCase();
            String rightOperand = operands[1];
            
            switch (leftOperand) {
                case "name":
                    if (!facilityID.equals(rightOperand)) {
                        return false;
                    }
                    break;
                case "sensornumber":
                    if (sensors.size() != Integer.parseInt(rightOperand)) {
                        
                        return false;
                    }
                    break;
                case "section":
                    if (!section.getName().equals(rightOperand)) {
                        return false;
                    }
                    break;
            }
        }
        return true;
    }
        
    public List<TollSystemRecord> getAllRecords() {
        List<TollSystemRecord> data = new ArrayList<>();
        for (Map.Entry<String, List<TollSystemRecord>> entry : records.entrySet()) {
            data.addAll(entry.getValue());
            
        }
        return data;
    }
}
