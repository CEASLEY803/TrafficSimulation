package Project06;

/**
 * Represents a vehicle within the traffic system.
 */
public class Vehicle {
    private int bornTime;
    private char destination;
    
    /**
     * Constructs a new Vehicle with specified born time and destination.
     * 
     * @param bornTime The simulation time step when the vehicle was created.
     * @param destination The destination of the vehicle, 'S' for south and 'W' for west.
     */
    public Vehicle(int bornTime, char destination) {
        this.bornTime = bornTime;
        this.destination = destination;
    }

    /**
     * Returns the time of arrival for a specific vehicle.
     * 
     * @return The time value that the vehicle entered the system at
     */
    public int getBornTime() {
    	return this.bornTime;
    }


    /**
     * Returns the destination of the vehicle.
     * 
     * @return A character indicating the destination of the vehicle ('S' or 'W').
     */
    public char getDestination() {
        return this.destination;
    }

    /**
     * Returns a string representation of the vehicle, including its born time and destination.
     * 
     * @return A string representing the vehicle.
     */
    @Override
    public String toString() {
        return "Vehicle{bornTime=" + bornTime + ", destination=" + destination + '}';
    }
}