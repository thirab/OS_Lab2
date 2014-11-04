package PeaceShire;

import javax.management.monitor.Monitor;
/**
 * Agents get messagesfrom the monitor and print them
 * @author tai-lanhirabayashi
 *
 */
public class Agent implements Runnable{

	Espionage myMonitor = null;
	public Agent(Espionage m){
		myMonitor = m;
		
	}

	@Override
	public void run() {
		while(true){
			String message = myMonitor.agentPickMsg();
			System.out.println("Recieved my message: " + message);
		}
		
	}
	
}
