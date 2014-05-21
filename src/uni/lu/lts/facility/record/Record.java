/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.facility.record;

import java.util.Date;
import uni.lu.lts.facility.Section;
import uni.lu.lts.facility.TollFacility;
import uni.lu.lts.facility.sensor.Image;
import uni.lu.lts.util.ImmutablePair;

/**
 *
 * @author asiron
 */
public class Record {
    
    protected final ImmutablePair<Section, TollFacility> zone;
    protected final Integer sensorID;
    protected final Date timestamp; 
    protected final Image image;

    public Record(Section section, TollFacility facility, Integer sensorID, Date timestamp, Image image) {
        this.zone = new ImmutablePair<>(section, facility);
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
