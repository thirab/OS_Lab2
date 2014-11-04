package ServerDrinker;
import java.util.concurrent.Semaphore;


public class Drinker implements Runnable {
	Semaphore client = null;	
	Semaphore server = null;	
	int resources = 0;
	boolean hasDrank=false;
	
	
	Drinker (Semaphore s, Semaphore c, int value){
		resources = value;
		client = c;
		server = s;
	    System.out.println("Creating Drinker");
	}
	
	public boolean needsDrink(){
		return !hasDrank;
	}
	
	@Override
	public void run() {

		while(true){
			if(client.availablePermits()==resources){
				if(server.tryAcquire()){
				System.out.println("In drinker: " + resources);
				if(!hasDrank){
					System.out.println("resources: " + resources + " permits: " + client.availablePermits());

					
						System.out.println("The drinker " + resources + " has drank");
						hasDrank=true;
				}


					client.release(6-resources);
					System.out.println("Server permits: " + server.availablePermits());
					System.out.println("client permits: " + client.availablePermits());
					server.release();



				}
			

			}
			}
		}

	

	// TODO Auto-generated method stub

}


