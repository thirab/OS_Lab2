package PeaceShire;

import javax.management.monitor.Monitor;

/**
 * Espionage is a monitor that allows spies to pass messages to agents.
 * @author tai-lanhirabayashi
 *
 */
public class Espionage extends Monitor {
	
	String message = null;
	
	public void dropSpyMsg(String m){
		while(message !=null){
			try {
		        synchronized(this){
		            wait();
		        }
		    } catch (InterruptedException e) {
		        // ...
		    }
		}
		message = m;
		synchronized(this){
		    notifyAll();
		}
		//notifyAll();
	}
	
	public String agentPickMsg(){
		while(message == null){
			try {
		        synchronized(this){
		            wait();
		        }
		    } catch (InterruptedException e) {
		        // ...
		    }
		}
		String rMessage = message;
		message = null;

		synchronized(this){
		    notifyAll();
		}
		return rMessage;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
