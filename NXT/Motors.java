import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.MotorPort;

public class Motors
{
  public static void main (String[] args)
  {
    NXTRegulatedMotor aTTACK = new NXTRegulatedMotor(MotorPort.A);
    NXTRegulatedMotor right = new NXTRegulatedMotor(MotorPort.B);
    NXTRegulatedMotor left = new NXTRegulatedMotor(MotorPort.C);
    aTTACK.setSpeed(300);
    aTTACK.forward();
    right.forward();
    left.forward();
    Button.waitForAnyPress();
  }
}