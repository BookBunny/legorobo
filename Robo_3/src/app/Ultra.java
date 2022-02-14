package app;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class Ultra extends Thread {

	private static EV3UltrasonicSensor us = new EV3UltrasonicSensor(SensorPort.S2);
	private static DataExchange de = new DataExchange();

	@Override
	public void run() {

		final SampleProvider sp = us.getDistanceMode();
		int distance = 100; //alkuun vaa arvo, ei v�li�
		int close = 25; //kuinka monen sentin p��ss� esin saa olla

		while (true) {
			if (distance > close) { 
				de.setCMD(1);
				float[] sample = new float[sp.sampleSize()];
				sp.fetchSample(sample, 0);
				distance = (int) (sample[0] * 100);

				System.out.println("Distance: " + distance + "cm"); //n�ytt�� n�yt�ll� et�isyyden senttein�
				Delay.msDelay(250);
				LCD.clearDisplay(); //ei tee mit�� jostain syyst�

				if (Button.getButtons() != 0) { //jos painaa nappia
					break;
				}
			} else { //jos havaitaan jotain, t�h�n nyt pit�s teh� se v�ist�
				de.setCMD(0);
				System.exit(0);
			}
		}

	}

}