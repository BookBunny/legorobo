package app;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public class Move extends Thread {
	
	DataExchange data;
	private static EV3LargeRegulatedMotor motor1;
	private static EV3LargeRegulatedMotor motor2;
	private static MovePilot pilot;

	public Move(DataExchange de) {
		
		data = de;
		motor1 = new EV3LargeRegulatedMotor(MotorPort.A); // oikea rengas
		motor2 = new EV3LargeRegulatedMotor(MotorPort.D); // vasen rengas
		
		Wheel wheelRight = WheeledChassis.modelWheel(motor1, 56).offset(-72);
		Wheel wheelLeft = WheeledChassis.modelWheel(motor2, 56).offset(72);
		Chassis chassis = new WheeledChassis(new Wheel[] { wheelRight, wheelLeft }, WheeledChassis.TYPE_DIFFERENTIAL);
		pilot = new MovePilot(chassis);
		
	}
	
	public void right() {
		motor1.setSpeed(200); // PIENEMPI
		motor1.forward();
		motor2.setSpeed(350); // ISOMPI
		motor2.forward();
	}
	
	public void left() {
		motor1.setSpeed(350); // PIENEMPI
		motor1.forward();
		motor2.setSpeed(200); // ISOMPI
		motor2.forward();
	}
	
	public void forward() {
		motor1.setSpeed(350);
		motor1.forward();
		motor2.setSpeed(350);
		motor2.forward();
	}
	
	public void avoidObstacle() {
		pilot.travelArc(-25, 50); // miinus nii se menee oikealta ohi
	}

}