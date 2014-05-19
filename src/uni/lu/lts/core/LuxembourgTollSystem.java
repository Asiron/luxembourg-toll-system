/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.core;

import java.util.HashMap;
import java.util.Map;
import uni.lu.lts.facility.Section;

/**
 *
 * @author asiron
 */
public class LuxembourgTollSystem {

    private Map<String, Section> sections = null;

    public LuxembourgTollSystem() {
        this.sections = new HashMap<String, Section>();
    }

    
    
    /**
     * Get the value of sections
     *
     * @return the value of sections
     */
    public Map<String, Section> getSections() {
        return sections;
    }

    /**
     * Set the value of sections
     *
     * @param sections new value of sections
     */
    public void setSections(Map<String, Section> sections) {
        this.sections = sections;
    }
}
