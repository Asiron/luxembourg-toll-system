package uni.lu.lts;

public class Vehicle {
	// static enum type to designate the type of the vehicle
	public static enum VehicleType {CAR, MOTORBIKE, TRUCK, BUS};
	
	private VehicleType vehicleType;
	private TollFacility latestPointPassed = null;		// reference to last toll facility that vehicle passed through
	private String numberPlate;				// number plate of the vehicle
	private String country;					// country of the vehicle
	private float size;					// size of the car
	
	public Vehicle(VehicleType vehicleType, String numberPlate, String country, float size) {
		this.vehicleType = vehicleType;
		this.numberPlate = numberPlate;
		this.country = country;
		this.size = size;
	}
	
	// TODO: define methods
}
