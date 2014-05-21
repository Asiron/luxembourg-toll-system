/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.facility.record;

import java.util.Comparator;
import java.util.Date;
import uni.lu.lts.facility.Section;
import uni.lu.lts.facility.TollFacility;
import uni.lu.lts.facility.sensor.Image;
import uni.lu.lts.vehicle.Vehicle;

/**
 *
 * @author asiron
 */
public class TollSystemRecord extends Record {
    
    private final float calculatedPrice;
    private final Vehicle vehicleRef;

    public TollSystemRecord(float calculatedPrice, Vehicle vehicleRef, Section section, TollFacility facility, Integer sensorID, Date timestamp, Image image) {
        super(section, facility, sensorID, timestamp, image);
        this.calculatedPrice = calculatedPrice;
        this.vehicleRef = vehicleRef;
    }
 
    /**
     * Get the value of vehicleRef
     *
     * @return the value of vehicleRef
     */
    public Vehicle getVehicleRef() {
        return vehicleRef;
    }

    /**
     * Get the value of calculatedPrice
     *
     * @return the value of calculatedPrice
     */
    public float getCalculatedPrice() {
        return calculatedPrice;
    }
    
    @Override
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
                case "price":
                    if( Math.abs(calculatedPrice - Float.parseFloat(rightOperand)) > 0.001 ) {
                        return false;
                    }
                    break;
            }
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "(" + timestamp + ", " + calculatedPrice + ", " 
                + vehicleRef + " , " + zone.getFirst() + ", " + zone.getSecond() + " )"; 
    }
    
    public static class TSRComparators {

        public static Comparator<TollSystemRecord> PRICE = new Comparator<TollSystemRecord>() {
          
            @Override
            public int compare(TollSystemRecord o1, TollSystemRecord o2) {
                return (int)(o1.getCalculatedPrice() - o2.getCalculatedPrice());
            }
           
        };
        
        public static Comparator<TollSystemRecord> VEHICLENUMBER = new Comparator<TollSystemRecord>() {
          
            @Override
            public int compare(TollSystemRecord o1, TollSystemRecord o2) {
                return o1.getVehicleRef().getNumberPlate().compareTo(o2.getVehicleRef().getNumberPlate());
            }
           
        };
    }
    
 }
