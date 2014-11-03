package PeaceShire;

import javax.management.monitor.Monitor;

/**
 * The Spy dropps messages
 * @author tai-lanhirabayashi
 *
 */
public class Spy implements Runnable{

	String message = null;
	int count = 0; // done only for testing the drop 
	Espionage myMonitor = null;
	public Spy(Espionage mon, String mess){
		message = mess;
		myMonitor = mon;
		
	}

	@Override
	public void run() {
		while(true){
			System.out.println("trying to drop message");
			//myMonitor.dropSpyMsg(message);
			myMonitor.dropSpyMsg(Integer.toString(count));
			count++;
			//message+="a";
		}
		
	}
}
