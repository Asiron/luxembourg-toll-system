/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.facility.sensor;

import java.sql.Timestamp;
import uni.lu.lts.facility.ErrorType;

/**
 *
 * @author asiron
 */
public class SensorReadingError {
    
    private Integer sensorID;
    
    private ErrorType errorType;
    private Timestamp timestamp;
    private Image picture;

    public SensorReadingError(Integer sensorID, ErrorType errorType, Timestamp timestamp, Image picture) {
        this.sensorID = sensorID;
        this.errorType = errorType;
        this.timestamp = timestamp;
        this.picture = picture;
    }
    
    public Integer getSensorID() {
        return sensorID;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Image getPicture() {
        return picture;
    }
    
    
}
