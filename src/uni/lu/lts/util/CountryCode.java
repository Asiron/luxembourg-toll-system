/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import uni.lu.lts.vehicle.VehicleType;

/**Country codes for vehicles in Europe
 *
 * @author asiron
 */
public enum CountryCode {
    AL	("Albania"),
    AND	("Andorra"),
    AM	("Armenia"),
    A	("Austria"),
    AZ	("Azerbaijan"),
    BY	("Belarus"),
    B	("Belgium"),
    and	("Bosnia"),
    BG	("Bulgaria"),
    HR	("Croatia"),
    CY	("Cyprus"),
    CZ	("Czech"),
    DK	("Denmark"),
    EST	("Estonia"),
    FIN	("Finland"),
    F	("France"),
    GE	("Georgia"),
    D	("Germany"),
    GBZ	("Gibraltar"),
    GR	("Greece"),
    H	("Hungary"),
    IS	("Iceland"),
    IRL	("Ireland"),
    I	("Italy"),
    KZ	("Kazakhstan"),
    RKS	("Kosovo"),
    LV	("Latvia"),
    FL	("Liechtenstein"),
    LT	("Lithuania"),
    L	("Luxembourg"),
    MK	("Macedonia"),
    M	("Malta"),
    MD	("Moldova"),
    MC	("Monaco"),
    MNE	("Montenegro"),
    NL	("Netherlands"),
    N	("Norway"),
    PL	("Poland"),
    P	("Portugal"),
    RO	("Romania"),
    RUS	("Russia"),
    RSM	("San Marino"),
    SRB	("Serbia"),
    SK	("Slovakia"),
    SLO	("Slovenia"),
    E	("Spain"),
    S	("Sweden"),
    CH	("Switzerland"),
    TR	("Turkey"),
    UA	("Ukraine"),
    UK	("United"),
    V	("Vatican");

    private final String fullname;

    private static final List<CountryCode> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
    
    private CountryCode(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return fullname;
    }
    
    public static int getSize() {
        return SIZE;
    }
    
    public static CountryCode randomCountry()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

}
