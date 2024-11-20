package Project06;

public class Simulation {
    public static void main(String[] args) {
        
        int r0Length = 15;
        int r1Length = 10;
        int r2Length = 10;
        int s1Period = 15, s1Green = 4;
        int s2Period = 15, s2Green = 4;
        double arriveChance = 0.65, turnChance = 0.4;
        
        TrafficSystem tf = new TrafficSystem(r0Length, r1Length, r2Length, s1Period, s1Green, s2Period, s2Green, arriveChance, turnChance);
        
        for (int t = 1; t < 101; t++)
        {
            try {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
            }
            tf.step();

        }
        tf.printStatistics();
    }
}