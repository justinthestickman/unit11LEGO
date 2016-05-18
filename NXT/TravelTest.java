import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.LightSensor;
import lejos.robotics.navigation.DifferentialPilot;

/**
 * Robot that stops if it hits something before it completes its travel.
 */
public class TravelTest
{
    DifferentialPilot pilot;
    TouchSensor bump = new TouchSensor(SensorPort.S4);
    LightSensor light = new LightSensor(SensorPort.S1);

    public void exitBox()
    {
        pilot.travel(10, true);
        while (pilot.isMoving())
        {
            if (bump.isPressed())
            {
                pilot.stop();
                pilot.travel(-5, false);
                pilot.rotate(110);
                pilot.travel(10, true);
            }
        }
        System.out.println(" "+pilot.getMovement().getDistanceTraveled());
        Button.waitForAnyPress();
    }
    
    public void goHam()
    {
        while(pilot.isMoving())
        {
            if (light.getLightValue()<5)
            {
                
            }
        }
    }

    public static void main(String[] args)
    {
        TravelTest traveler = new TravelTest();
        traveler.pilot = new DifferentialPilot(2.25f, 5.5f, Motor.A, Motor.C);
        traveler.exitBox();
    }
}