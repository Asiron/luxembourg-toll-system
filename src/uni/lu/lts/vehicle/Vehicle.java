package uni.lu.lts.vehicle;

import java.util.Comparator;
import java.util.Date;
import uni.lu.lts.facility.TollFacility;
import uni.lu.lts.util.CountryCode;




public class Vehicle implements Comparable<Vehicle> {

    private VehicleType vehicleType;
    private TollFacility latestPointPassed = null;		// reference to last toll facility that vehicle passed through
    private String numberPlate;				// number plate of the vehicle
    private CountryCode country;					// country of the vehicle
    private float height;

    public Vehicle(VehicleType vehicleType, String numberPlate, CountryCode country, float height) {
            this.vehicleType = vehicleType;
            this.numberPlate = numberPlate;
            this.country = country;
            this.height = height;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public CountryCode getCountry() {
        return country;
    }

    public void setCountry(CountryCode country) {
        this.country = country;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setLatestPointPassed(TollFacility latestPointPassed) {
        this.latestPointPassed = latestPointPassed;
    }

    public TollFacility getLatestPointPassed() {
        return latestPointPassed;
    }
    
    @Override
    public String toString() {
        String countryCode;
        if (country == null) {
            countryCode = "Unknown";
        } else {
            countryCode = country.name();
        }
        return "(" + vehicleType + ", " + numberPlate + ", " + country + "(" + countryCode + "), " + height + ")";
    }

    @Override
    public int compareTo(Vehicle o) {
        return Comparators.NUMBERPLATE.compare(this, o);
    }
         
    public boolean checkConditions(String[] conditions) {
        for (String condition : conditions) {
            String[] operands = condition.split("=");
            String leftOperand  = operands[0].toLowerCase();
            String rightOperand = operands[1].toUpperCase();
            
            switch (leftOperand) {
                case "type":
                    if (!vehicleType.toString().equals(rightOperand)) {
                        return false;
                    }
                    break;
                case "plate":
                    if (!numberPlate.equals(rightOperand)) {
                        return false;
                    }
                    break;
                case "country":
                    if (!country.toString().equals(rightOperand)) {
                        return false;
                    }
                    break;
                case "height":
                    if( Math.abs(height - Float.parseFloat(rightOperand)) > 0.001 ) {
                        return false;
                    }
                    break;
                case "latest":
                    if( !latestPointPassed.getFacilityID().equals(rightOperand) ) {
                        return false;
                    }
                    break;
            }
        }
        return true;
    }
    
    public static class Comparators {

        public static Comparator<Vehicle> NUMBERPLATE = new Comparator<Vehicle>() {
            
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                return o1.numberPlate.compareTo(o2.numberPlate);
            }
            
        };
        
        public static Comparator<Vehicle> HEIGHT = new Comparator<Vehicle>() {
            
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                return (int)(o1.height - o2.height);
            }
              
        };

        public static Comparator<Vehicle> TYPE = new Comparator<Vehicle>() {
            
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                return o1.vehicleType.toString().compareTo(o2.vehicleType.toString());
            }
            
        };
        
        public static Comparator<Vehicle> COUNTRY = new Comparator<Vehicle>() {
            
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                return o1.country.toString().compareTo(o2.country.toString());
            }
           
        };
    }
    
}
