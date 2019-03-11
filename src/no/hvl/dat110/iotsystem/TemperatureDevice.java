package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;

public class TemperatureDevice {
	
	private static final int COUNT = 10;
	
	public static void main(String[] args) {
		
		TemperatureSensor sn = new TemperatureSensor();
		
		// TODO - start
		Client client = new Client("TempClient",Common.BROKERHOST, Common.BROKERPORT);
		
		client.connect();
		for(int i = 0; i < COUNT; i++ ) {
			String t = sn.read() + "";
			client.publish(Common.TEMPTOPIC, t);
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		client.disconnect();
		// TODO - end
		
		System.out.println("Temperature device stopping ... ");
	}
}
