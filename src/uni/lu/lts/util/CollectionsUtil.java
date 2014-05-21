/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.util;

import java.util.Collections;
import java.util.List;
import uni.lu.lts.facility.Section;
import uni.lu.lts.facility.TollFacility;
import uni.lu.lts.facility.record.Record;
import uni.lu.lts.facility.record.SensorReadingError;
import uni.lu.lts.facility.record.TollSystemRecord;
import uni.lu.lts.vehicle.Vehicle;

/**
 *
 * @author asiron
 */
public class CollectionsUtil {
       
    public static List<?> sortBy(List<?> results, String sortBy) {
        
        if (results == null || results.size() <= 1) {
            return null;
        }
        
        if (sortBy == null) {
            return results;
        }
        
        sortBy = sortBy.toLowerCase();
        
        if (results.get(0) instanceof TollSystemRecord) {
           switch (sortBy) {
                case "plate":
                    Collections.sort((List<TollSystemRecord>)results, TollSystemRecord.TSRComparators.VEHICLENUMBER);
                    break;
                case "price":
                    Collections.sort((List<TollSystemRecord>)results, TollSystemRecord.TSRComparators.PRICE);
                    break;
                case "date":
                    Collections.sort((List<TollSystemRecord>)results, Record.RecordComparators.DATE);
                    break; 
                case "zone":
                    Collections.sort((List<TollSystemRecord>)results, Record.RecordComparators.ZONE);
                    break;
            }
        } else if (results.get(0) instanceof SensorReadingError) {
           switch (sortBy) {
                case "date":
                    Collections.sort((List<SensorReadingError>)results, Record.RecordComparators.DATE);
                    break; 
                case "zone":
                    Collections.sort((List<SensorReadingError>)results, Record.RecordComparators.ZONE);
                    break;
                case "error":
                    Collections.sort((List<SensorReadingError>)results, SensorReadingError.SREComparators.ERRORTYPE);
                    break;             
            }
        } else if (results.get(0) instanceof Vehicle) {
           switch (sortBy) {
                case "plate":
                    Collections.sort((List<Vehicle>)results, Vehicle.Comparators.NUMBERPLATE);
                    break; 
                case "height":
                    Collections.sort((List<Vehicle>)results, Vehicle.Comparators.HEIGHT);
                    break;
                case "country":
                    Collections.sort((List<Vehicle>)results, Vehicle.Comparators.COUNTRY);
                    break;    
                case "type":
                    Collections.sort((List<Vehicle>)results, Vehicle.Comparators.TYPE);
                    break;   
            }
        } else if (results.get(0) instanceof Section) {
            Collections.sort((List<Section>) results);
        } else if (results.get(0) instanceof TollFacility) {
            Collections.sort((List<TollFacility>) results);
        }
        
        return results;
    }
}
