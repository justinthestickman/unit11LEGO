import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.LightSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.nxt.LCD;

/**
 * 
 */
public class TravelTest
{
    DifferentialPilot pilot;
    TouchSensor bump1 = new TouchSensor(SensorPort.S1);
    TouchSensor bump2 = new TouchSensor(SensorPort.S4);
    LightSensor light = new LightSensor(SensorPort.S3);
    
    public void goHam()
    {
        pilot.travel(12, true);
        while (pilot.isMoving())
        {
            if (bump1.isPressed() || bump2.isPressed())
            {
                pilot.stop();
                pilot.travel(-5, false);
                pilot.rotate(110);
                pilot.travel(12, true);
            }
        }
        //System.out.println(" "+pilot.getMovement().getDistanceTraveled());
        
        pilot.travel(50, true);
        while(pilot.isMoving())
        {
            if (light.getLightValue() < 20)
            {
                pilot.stop();
                System.out.println(light.getLightValue());
                LCD.drawInt(light.getLightValue(),0,0,0);
            }
        }
        Button.waitForAnyPress();
    }

    public static void main(String[] args)
    {
        TravelTest traveler = new TravelTest();
        traveler.pilot = new DifferentialPilot(2.25f, 5.5f, Motor.A, Motor.C);
        traveler.goHam();
    }
}