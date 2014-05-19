/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.facility;

import uni.lu.lts.facility.sensor.SensorReadingError;
import uni.lu.lts.facility.sensor.Sensor;
import java.util.Dictionary;
import java.util.List;
import uni.lu.lts.core.TollSystemRecord;

/**
 *
 * @author asiron
 */
public class TollFacility {
    
    private Section section;
    private String facilityID;
    private Dictionary<Integer, Sensor> sensors;
    private List<TollSystemRecord> records;
    private List<SensorReadingError> errorList;

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
    public Dictionary<Integer, Sensor> getSensors() {
        return sensors;
    }

    /**
     * Set the value of sensors
     *
     * @param sensors new value of sensors
     */
    public void setSensors(Dictionary<Integer, Sensor> sensors) {
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

}
