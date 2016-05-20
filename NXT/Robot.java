import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.LightSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.nxt.LCD;

/**
 * 
 */
public class Robot
{
    DifferentialPilot pilot;
    NXTRegulatedMotor right = new NXTRegulatedMotor(MotorPort.A);
    NXTRegulatedMotor left = new NXTRegulatedMotor(MotorPort.C);
    TouchSensor bump1 = new TouchSensor(SensorPort.S1);
    TouchSensor bump2 = new TouchSensor(SensorPort.S4);
    LightSensor light = new LightSensor(SensorPort.S3);
    
    public void goHam()
    {
        //Exit box
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
        
        //Travel to black edge of circle
        pilot.travel(50, true);
        while(pilot.isMoving())
        {
            if (light.getLightValue() < 30)
            {
                pilot.stop();
                System.out.println(light.getLightValue());
                LCD.drawInt(light.getLightValue(),0,0,0);
            }
        }
        
        //Travel around circle
        pilot.travel(4.5, false);
        pilot.rotate(-90);
        pilot.arc(53.5, -360);
    }
    
    public void lightSensorTest()
    {
        System.out.println("Place on white");
        Button.waitForAnyPress();
        System.out.println("White value: " + light.getLightValue());
        System.out.println("Place on black");
        Button.waitForAnyPress();
        System.out.println("Black value: " + light.getLightValue());
        Button.waitForAnyPress();
    }

    public static void main(String[] args)
    {
        Robot joe = new Robot();
        joe.pilot = new DifferentialPilot(5.6f, 16f, Motor.A, Motor.C);
        //joe.lightSensorTest();
        joe.goHam();
    }
}