/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.facility.record;

import java.util.Date;
import uni.lu.lts.facility.Section;
import uni.lu.lts.facility.TollFacility;
import uni.lu.lts.facility.sensor.ErrorType;
import uni.lu.lts.facility.sensor.Image;

/**
 *
 * @author asiron
 */
public class SensorReadingError extends Record {
    
    private final ErrorType errorType;

    public SensorReadingError(ErrorType errorType, Section section, TollFacility facility, Integer sensorID, Date timestamp, Image image) {
        super(section, facility, sensorID, timestamp, image);
        this.errorType = errorType;
    }

    /**
     * Get the value of errorType
     * @return the value of errorType
     */
    public ErrorType getErrorType() {
        return errorType;
    }

}
