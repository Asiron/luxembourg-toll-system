/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.core;

import java.util.Dictionary;
import java.util.Hashtable;
import uni.lu.lts.facility.Section;

/**
 *
 * @author asiron
 */
public class LuxembourgTollSystem {

    private Dictionary<String, Section> sections = null;

    public LuxembourgTollSystem() {
        this.sections = new Hashtable<String, Section>();
    }

    
    
    /**
     * Get the value of sections
     *
     * @return the value of sections
     */
    public Dictionary<String, Section> getSections() {
        return sections;
    }

    /**
     * Set the value of sections
     *
     * @param sections new value of sections
     */
    public void setSections(Dictionary<String, Section> sections) {
        this.sections = sections;
    }
}
