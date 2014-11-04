package ServerDrinker;
import java.util.concurrent.Semaphore;


public class ThirstyDrinkers {
/**
 * 1 Server, 3 thirsty drinkers
 * ingredients: water, ice, cup
 * one of the threads ahs water
 * one has ice
 * one has a cup
 * server has infinite supply of all 3
 * server places 2 incredients on the bable( diffferent)
 * the thread with the remaining ingredient prepares and has a drink,
 *  signialling server on completion.
 *  The server puts out another 2, and cycle repeats.
 *  
 *  Solution working. 
 */


Semaphore drinker = new Semaphore(6);
Semaphore serv = new Semaphore(1);

//the threads
Server server = new Server(serv,drinker);
Drinker one = new Drinker(serv,drinker,1);
Drinker two = new Drinker(serv,drinker,2);
Drinker three = new Drinker(serv,drinker,3);

public ThirstyDrinkers(){
	System.out.println("====== new prog ====== ");
	
	(new Thread(server)).start();
	(new Thread(one)).start();
	(new Thread(two)).start();
	(new Thread(three)).start();

	//untilComplete();
}

public void untilComplete(){
	while(one.needsDrink() || two.needsDrink() || three.needsDrink()){
		(new Thread(server)).start();
		if(one.needsDrink()){
			(new Thread(one)).start();
		}else{
			System.out.println("One has completed");
		}
		if(two.needsDrink()){
			(new Thread(two)).start();
		}else{
			System.out.println("Two has completed");
		}
		if(three.needsDrink()){
			(new Thread(three)).start();
		}else{
			System.out.println("Three has completed");
		}
		
		int permits = serv.availablePermits();
		System.out.println("The number of server permits " + permits);
		if(permits == 0){
			System.out.println("Increasing server permits");
			serv.release();
			permits = serv.availablePermits();
			System.out.println("The number of server permits " + permits);
		}else if( permits >1){
			try {
				serv.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
	System.out.println("All drinkers are fufilled");
}




}