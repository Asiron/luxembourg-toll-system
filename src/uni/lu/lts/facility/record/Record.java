/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.facility.record;

import java.util.Comparator;
import java.util.Date;
import java.util.logging.SimpleFormatter;
import uni.lu.lts.facility.Section;
import uni.lu.lts.facility.TollFacility;
import uni.lu.lts.facility.sensor.Image;
import uni.lu.lts.util.ImmutablePair;

/**
 *
 * @author asiron
 */
public class Record implements Comparable<Record> {
    
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

    public String getSectionName() {
        return zone.getFirst().getName();
    }
    
    public String getTollFacilityName() {
        return zone.getSecond().getFacilityID();
    }
   
    @Override
    public int compareTo(Record o) {
        return Record.RecordComparators.DATE.compare(this, o);
    }
      
    @Override
    public String toString() {
        return "(" + timestamp + ", " + zone + ", " + sensorID + ")"; 
    }
    
    public boolean checkConditions(String[] conditions) {
        return false;
    }
    
    public static class RecordComparators {

        public static Comparator<Record> DATE = new Comparator<Record>() {
            
            @Override
            public int compare(Record o1, Record o2) {
                
                long t1 = o1.getTimestamp().getTime();
                long t2 = o2.getTimestamp().getTime();
                if (t2 > t1) {
                    return 1;
                } else if(t1 > t2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };
        
        public static Comparator<Record> ZONE = new Comparator<Record>() {
            
            @Override
            public int compare(Record o1, Record o2) {
                int i = o1.getSectionName().compareTo(o2.getSectionName());
                if (i == 0) {
                    i = o1.getTollFacilityName().compareTo(o2.getTollFacilityName());
                }
                return i;
            }
        };
         
    }
}
