/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.facility.sensor;

/**
 *
 * @author asiron
 */
public enum ErrorType {
    NOERROR ("No error"),
    UNKNOWNCOUNTRY ("Unknown country"),
    UNKNOWNPLATES ("Unknown license plates"),
    VEHHEIGHTSURPASSES ("Vehicle's height surpasses limit"),
    UNALLOWEDVEHICLETYPE ("Vehicle's type is unallowed here"),
    RECORDINGFROMFUTURE ("Reading from sensor arrived from future");
    
    private final String fullname;

    private ErrorType(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return fullname;
    }
}
