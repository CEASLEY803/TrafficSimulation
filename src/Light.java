package Project06;

/**
 * Represents a traffic light in the traffic system.
 */
public class Light {
	private int period;
	private int green;
	private int time;

	/**
	 * Constructs a new Light with specified period and green time.
	 * 
	 * @param period The total period of the light cycle.
	 * @param green  The duration of the green light within the cycle.
	 */
	public Light(int period, int green) {
		this.period = period;
		this.green = green;
		this.time = 0;
	}

	/**
	 * Advances the state of the light by one time step.
	 */
	public void step() {
		time = (time + 1) % period;
	}

	/**
	 * Checks if the light is currently green.
	 * 
	 * @return True if the light is green, false otherwise.
	 */
	public boolean isGreen() {
		return time < green;
	}

	/**
	 * Returns a string representation of the light's current state.
	 * 
	 * @return "Green" if the light is green, "Red" otherwise.
	 */

	@Override
	public String toString() {
		return isGreen() ? "Green" : "Red";
	}
}