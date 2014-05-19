/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.facility.sensor;

/**
 *
 * @author asiron
 */
public class Image {

    private byte[] data;

    public Image(byte[] data) {
        this.data = data;
    }    
    
    /**
     * Get the value of data
     *
     * @return the value of data
     */
    public byte[] getData() {
        return data;
    }    
}
