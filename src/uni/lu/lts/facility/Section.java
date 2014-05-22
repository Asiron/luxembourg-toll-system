/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.facility;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import uni.lu.lts.vehicle.VehicleType;

/**
 *
 * @author asiron
 */
public class Section implements Comparable<Section> {
   
    private String name;
    private Map<VehicleType, Float> prices;
    private Map<String, TollFacility> tollFacilities;

    public Section(String name) {
        this.name           = name;
        this.prices         = new EnumMap<>(VehicleType.class);
        this.tollFacilities = new HashMap<>();
    }

    /**
     * Get the value of tollFacilities
     *
     * @return the value of tollFacilities
     */
    public Map<String, TollFacility> getTollFacilities() {
        return tollFacilities;
    }

    /**
     * Set the value of tollFacilities
     *
     * @param tollFacilities new value of tollFacilities
     */
    public void addTollFacilities(String facilityID) {
        TollFacility facility = new TollFacility(this, facilityID);
        tollFacilities.put(facilityID, facility);
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Get the value of price for given vehicle type
     *
     * @param type value of price for given vehicle type
     */
    public Float getPrice(VehicleType type) {
        return prices.get(type);
    }
    
    /**
     * Set the value of price for given vehicle type
     *
     * @param type change price for this type
     * @param newPrice new value of section
     */
    public void setPrice(VehicleType type, Float newPrice) {
        prices.put(type, newPrice);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (TollFacility tf  : tollFacilities.values()) {
            sb.append(tf.getFacilityID());
            sb.append(" ");
        }
        return name + " -> " + sb.toString();
    }

    @Override
    public int compareTo(Section o) {
        return name.compareTo(o.getName());
    }

    public boolean checkConditions(String[] conditions) {
        for (String condition : conditions) {
            String[] operands = condition.split("=");
            String leftOperand  = operands[0].toLowerCase();
            String rightOperand = operands[1].toUpperCase();
            
            switch (leftOperand) {
                case "name":
                    if (!name.equals(rightOperand)) {
                        return false;
                    }
                    break;
                case "price":
                    if (!prices.containsKey(VehicleType.valueOf(rightOperand))) {
                        return false;
                    }
                    break;
                case "tfnumber":
                    if (tollFacilities.size() != Integer.parseInt(rightOperand)) {
                        return false;
                    }
                    break;
            }
        }
        return true;
    }
}
