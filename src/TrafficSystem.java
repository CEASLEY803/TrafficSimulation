package Project06;

import java.util.Random;

/**
 * Name: Cole Easley
 * Date: 3/26/2024
 * Due date: 3/28/2024
 * Project: 06
 * Description: This program simulates a traffic system with three lanes and two traffic lights.
 */
public class TrafficSystem {
    private Lane r0, r1, r2;
    private Light s1, s2;
    private Random rand = new Random();
    private double arrivalProbability;
    private double turningProbability;
    private int time = 0;

    private int totalTimeS1 = 0, totalTimeS2 = 0;
    private int countVehiclesS1 = 0, countVehiclesS2 = 0;
    private int maxTimeS1 = 0, maxTimeS2 = 0;
    private int junctionBlockTime = 0;

    /**
     * Constructs a TrafficSystem with specified lane lengths, light cycles, and probabilities.
     *
     * @param lengthR0 The length of lane R0.
     * @param lengthR1 The length of lane R1.
     * @param lengthR2 The length of lane R2.
     * @param s1Period The cycle period of traffic light S1.
     * @param s1Green The green time of traffic light S1.
     * @param s2Period The cycle period of traffic light S2.
     * @param s2Green The green time of traffic light S2.
     * @param arrive The probability of a new vehicle arriving.
     * @param turn The probability of a vehicle turning (changing lanes).
     */
    public TrafficSystem(int lengthR0, int lengthR1, int lengthR2, int s1Period, int s1Green, int s2Period, int s2Green, double arrive, double turn) {
        r0 = new Lane(lengthR0);
        r1 = new Lane(lengthR1);
        r2 = new Lane(lengthR2);
        s1 = new Light(s1Period, s1Green);
        s2 = new Light(s2Period, s2Green);
        this.arrivalProbability = arrive;
        this.turningProbability = turn;
    }

    /**
     * Handles the arrival of new vehicles into the system based on the arrival probability.
     */
    private void handleVehicleArrivals() {
        if (rand.nextDouble() < arrivalProbability) {
            char destination = rand.nextDouble() < turningProbability ? 'S' : 'W';
            Vehicle newVehicle = new Vehicle(time, destination);

            

            if (r0.lastFree()) {
                r0.putLast(newVehicle);
                System.out.println("Added Vehicle to R0: " + newVehicle);
            }
        }
    }

    /**
     * Advances the traffic system simulation by one time step.
     */
    public void step() {
    	
    	transferVehicles();
    	
        s1.step();
        s2.step();

        exitVehicles();
        
        r0.step();
        r1.step();  
    	r2.step();
    	
    	
        handleVehicleArrivals();
        
        
        time++;
        print();
    }

    /**
     * Transfers vehicles between lanes based on their destination and availability of space.
     */
    private void transferVehicles() {
        if (!r0.firstFree()) {
            Vehicle vehicle = r0.getFirst();
            if (vehicle != null) {
                if (vehicle.getDestination() == 'S' && r2.lastFree()) {
                    r2.putLast(r0.removeFirst());
                } else if (vehicle.getDestination() == 'W' && r1.lastFree()) {
                    r1.putLast(r0.removeFirst());
                } else {
                	
                    junctionBlockTime++;
                    
                }
            }
        }
    }

    /**
     * Processes the exit of vehicles from the system when they reach the end of lanes R1 and R2.
     */
    private void exitVehicles() {
        if (s1.isGreen() && !r1.firstFree()) {
            Vehicle exitingVehicle = r1.getFirst();
            if (exitingVehicle != null) {
                r1.removeFirst();
                int timeInSystem = (time - exitingVehicle.getBornTime());
                totalTimeS1 += timeInSystem;
                countVehiclesS1++;
                maxTimeS1 = Math.max(maxTimeS1, timeInSystem); 
            }
        }

        if (s2.isGreen() && !r2.firstFree()) {
            Vehicle exitingVehicle = r2.getFirst();
            if (exitingVehicle != null) {
                r2.removeFirst();
                int timeInSystem = (time - exitingVehicle.getBornTime());
                totalTimeS2 += timeInSystem;
                countVehiclesS2++;
                maxTimeS2 = Math.max(maxTimeS2, timeInSystem);
            }
        }
    }

    /**
     * Prints the statistics of the traffic system simulation.
     */
    public void printStatistics() {
    	double avgTimeS1 = countVehiclesS1 == 0 ? 0 : (double) totalTimeS1 / countVehiclesS1;
        double avgTimeS2 = countVehiclesS2 == 0 ? 0 : (double) totalTimeS2 / countVehiclesS2;
        
        System.out.println("Average Time Comparison:");
        if (avgTimeS1 < avgTimeS2) {
            System.out.println("S1 is faster on average than S2 with an average time of " + (Math.round(avgTimeS1 * 100.0) / 100.0) + " vs " + (Math.round(avgTimeS2)* 100.0) / 100.0);
        } else if (avgTimeS1 > avgTimeS2) {
            System.out.println("S2 is faster on average than S1 with an average time of " + (Math.round(avgTimeS2 * 100.0) / 100.0) + " vs " + (Math.round(avgTimeS1)* 100.0) / 100.0);
        } else {
            System.out.println("S1 and S2 have the same average time of " + (Math.round(avgTimeS1 * 100.0) / 100.0));
        }

    
        System.out.println("Maximal Time Comparison:");
        if (maxTimeS1 < maxTimeS2) {
            System.out.println("S1 has a shorter maximal time than S2 with times of " + maxTimeS1 + " vs " + maxTimeS2);
        } else if (maxTimeS1 > maxTimeS2) {
            System.out.println("S2 has a shorter maximal time than S1 with times of " + maxTimeS2 + " vs " + maxTimeS1);
        } else {
            System.out.println("S1 and S2 have the same maximal time of " + maxTimeS1);
        }
        System.out.println("Junction blocked for " + junctionBlockTime + " steps");
    }

    /**
     * Prints the current state of the traffic system, including time, traffic light states, and lane statuses.
     */
    public void print() {
        System.out.println("Time: " + time);
        System.out.println("Light S1: " + s1 + " | Light S2: " + s2);
        System.out.println("Lane R0: " + r0);
        System.out.println("Lane R1: " + r1 + " (towards West)");
        System.out.println("Lane R2: " + r2 + " (towards South)");
    }
}