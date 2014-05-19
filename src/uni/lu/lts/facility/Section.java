/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.facility;

import uni.lu.lts.facility.TollFacility;
import java.util.Dictionary;

/**
 *
 * @author asiron
 */
public class Section {
    
    private float carPrice;
    private float motorbikePrice;
    private float truckPrice;
    private float busPrice;
    private String name;
    private Dictionary<String, TollFacility> tollFacilities;

    /**
     * Get the value of tollFacilities
     *
     * @return the value of tollFacilities
     */
    public Dictionary<String, TollFacility> getTollFacilities() {
        return tollFacilities;
    }

    /**
     * Set the value of tollFacilities
     *
     * @param tollFacilities new value of tollFacilities
     */
    public void setTollFacilities(Dictionary<String, TollFacility> tollFacilities) {
        this.tollFacilities = tollFacilities;
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
     * Get the value of busPrice
     *
     * @return the value of busPrice
     */
    public float getBusPrice() {
        return busPrice;
    }

    /**
     * Set the value of busPrice
     *
     * @param busPrice new value of busPrice
     */
    public void setBusPrice(float busPrice) {
        this.busPrice = busPrice;
    }

    /**
     * Get the value of truckPrice
     *
     * @return the value of truckPrice
     */
    public float getTruckPrice() {
        return truckPrice;
    }

    /**
     * Set the value of truckPrice
     *
     * @param truckPrice new value of truckPrice
     */
    public void setTruckPrice(float truckPrice) {
        this.truckPrice = truckPrice;
    }

    /**
     * Get the value of motorbikePrice
     *
     * @return the value of motorbikePrice
     */
    public float getMotorbikePrice() {
        return motorbikePrice;
    }

    /**
     * Set the value of motorbikePrice
     *
     * @param motorbikePrice new value of motorbikePrice
     */
    public void setMotorbikePrice(float motorbikePrice) {
        this.motorbikePrice = motorbikePrice;
    }

    /**
     * Get the value of carPrice
     *
     * @return the value of carPrice
     */
    public float getCarPrice() {
        return carPrice;
    }

    /**
     * Set the value of carPrice
     *
     * @param carPrice new value of carPrice
     */
    public void setCarPrice(float carPrice) {
        this.carPrice = carPrice;
    }

}
