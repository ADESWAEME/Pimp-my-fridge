package main;

import modele.Data;
import modele.SerialTest;
import vue.Fenetre;

public class Main {
	
	
	public static void main(String[] args) {

		
		Data data = new Data();
		SerialTest main = new SerialTest(data);
		main.initialize();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		@SuppressWarnings("unused")
		Fenetre fen = new Fenetre(data);
		
		Thread t=new Thread() {
			public void run() {
				//the following line will keep this app alive for 1000 seconds,
				//waiting for events to occur and responding to them (printing incoming messages to console).
				
				try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
				
			}
		};
		t.start();
		
//		System.out.println("Started");

		
		
			
				
			
	}


		
	
}
	
