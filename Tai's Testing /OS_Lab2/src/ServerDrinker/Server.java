package ServerDrinker;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * Server tested and running
 * @author tai-lanhirabayashi
 *
 */
public class Server implements Runnable {

	Semaphore serverSemaphore = null;
	Semaphore client = null;
	//set of resources that will be used
	Set<Integer> rSet = new HashSet<Integer>();

	Server (Semaphore serv,Semaphore c){
		serverSemaphore=serv;
		client=c;
		System.out.println("Creating Server" );
		rSet.add(1);
		rSet.add(2);
		rSet.add(3);
	}




	@Override
	public void run() {
		while(!rSet.isEmpty()){
			if(serverSemaphore.tryAcquire()){
				if(client.tryAcquire(6)){
					System.out.println(rSet.size());

					System.out.println("server running");
					//generate random resources
					Random randomGenerator = new Random();
					int i = randomGenerator.nextInt(3)+1;
					int j = randomGenerator.nextInt(3)+1;
					while(i==j){
						j=randomGenerator.nextInt(3)+1;
					}
					int missingIngredient= 6-i-j;
					System.out.println("new random: " + missingIngredient);
					if(rSet.contains(missingIngredient)){
						System.out.println("rset: " + missingIngredient );

						System.out.println("I: " + i + " J: " + j);
						System.out.println("Running Server" );

						rSet.remove(missingIngredient);
						int avaliable = client.availablePermits();
						System.out.println("Server has client permits: " + avaliable);
						System.out.println("Server missing ingredient: " + missingIngredient);
						//set the number of permits that the client has to the value for the missing ingredient.
						client.release(missingIngredient);
						
						System.out.println("Last seen client by server: " + client.availablePermits());

					}
					if(client.availablePermits()!=missingIngredient){
						client.release(6-client.availablePermits());
					}
				}
				serverSemaphore.release();
			}
		}
	}
}







