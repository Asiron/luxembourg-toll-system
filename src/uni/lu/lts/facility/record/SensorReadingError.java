/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.facility.record;

import java.util.Comparator;
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

    public boolean checkConditions(String[] conditions) {
        for (String condition : conditions) {
            String[] operands = condition.split("=");
            String leftOperand  = operands[0].toLowerCase();
            String rightOperand = operands[1];
            
            switch (leftOperand) {
                case "section":
                    if (!zone.getFirst().getName().equals(rightOperand)) {
                        return false;
                    }
                    break;
                case "facility":
                    if (!zone.getSecond().getFacilityID().equals(rightOperand)) {
                        return false;
                    }
                    break;
                case "date":
                    Date conditionDate = new Date();
                    if (timestamp.compareTo(conditionDate) != 0) {
                        return false;
                    }
                    break;
                case "error":
                    if(!errorType.toString().equals(rightOperand)) {
                        return false;
                    }
                    break;
            }
        }
        return true;
    }
    
    
    public static class SREComparators {

        public static Comparator<SensorReadingError> ERRORTYPE = new Comparator<SensorReadingError>() {
          
            @Override
            public int compare(SensorReadingError o1, SensorReadingError o2) {
                return o1.getErrorType().toString().compareTo(o2.getErrorType().toString());
            }
            
        };
        
    }
}
