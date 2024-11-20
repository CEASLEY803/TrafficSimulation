package Project06;

public class Lane {
	private Vehicle[] theLane;

	/**
	 * Constructs a new Lane with a specified length.
	 * 
	 * @param n The number of vehicle positions (length) in the lane.
	 */
	public Lane(int n) {
		this.theLane = new Vehicle[n];
	}

	/**
	 * Returns the vehicle at the end of the lane, if it exists.
	 * 
	 * @return The vehicle at the end of the lane or null if the lane is empty.
	 */
	public Vehicle getVehicleAtEnd() {
		// Return the vehicle at the end of the lane if it exists
		return theLane[theLane.length - 1];
	}

	/**
	 * Removes the vehicle at the end of the lane.
	 */
	public void removeVehicleAtEnd() {
		theLane[theLane.length - 1] = null;
	}

	/**
	 * Moves vehicles forward in the lane, if possible.
	 */
	public void step() {

		for (int i = theLane.length - 2; i >= 0; i--) {
			
			if (theLane[i] != null && theLane[i + 1] == null) {
				theLane[i + 1] = theLane[i];
				theLane[i] = null; 
			}
		}
	}

	/**
	 * Removes and returns the first vehicle in the lane, if present.
	 * 
	 * @return The first vehicle in the lane, or null if the lane is empty.
	 */
	public Vehicle removeFirst() {
		Vehicle temp = theLane[theLane.length - 1];

		theLane[theLane.length - 1] = null;
		return temp;
	}

	/**
	 * Returns the first vehicle in the lane without removing it.
	 * 
	 * @return The first vehicle in the lane, or null if the lane is empty.
	 */
	public Vehicle getFirst() {
		return theLane[theLane.length - 1];
	}

	/**
	 * Checks if the first position in the lane is free.
	 * 
	 * @return True if the first position is free, false otherwise.
	 */
	public boolean firstFree() {
		return theLane[theLane.length - 1] == null;
	}

	/**
	 * Checks if the last position in the lane is free.
	 * 
	 * @return True if the last position is free, false otherwise.
	 */
	public boolean lastFree() {
		return theLane[0] == null;
	}

	/**
	 *
	 * Adds a vehicle to the last free position in the lane, if possible.
	 * 
	 * @param v The vehicle to add to the lane.
	 */
	public void putLast(Vehicle v) {
			
		theLane[0] = v;
	
	}

	/**
     * Returns a string representation of the lane, showing the presence or absence of vehicles in each position.
     * 
     * @return A string representing the lane's current state.
     */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Vehicle v : theLane) {
			if (v == null) {
				sb.append("[ ]");
			} else {
				sb.append("[").append(v.getDestination()).append("]");
			}
		}
		return sb.toString();
	}

}
