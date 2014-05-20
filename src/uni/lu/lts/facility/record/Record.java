/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.facility.record;

import java.util.Date;
import uni.lu.lts.facility.sensor.Image;

/**
 *
 * @author asiron
 */
public class Record {
    
    private final Integer sensorID;
    private final Date timestamp; 
    private final Image image;

    public Record(Integer sensorID, Date timestamp, Image image) {
        this.sensorID = sensorID;
        this.timestamp = timestamp;
        this.image = image;
    }
    
    public Integer getSensorID() {
        return sensorID;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Image getImage() {
        return image;
    }

}
