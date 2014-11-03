package PeaceShire;

/**
 * Shire class holds the espionage, spy, and agent classes.
 * I'm currently unsure why it stops running after 104 messages have been passed. 
 * @author tai-lanhirabayashi
 *
 */
public class Shire {
	Espionage esp; 

	public Shire(){
		System.out.println("creating the shire");
		esp = new Espionage();
		esp.start();
		System.out.println("Started espionage");
	}
	
	public void createSpy(String message){
		System.out.println("creating a spy with the message: " + message);
		Spy spy = new Spy(esp,message);
		(new Thread(spy)).start();
	}
	
	public void createAgent(){
		System.out.println("creating an agent");
		Agent agent = new Agent(esp);
		(new Thread(agent)).start();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Shire s = new Shire();
		s.createAgent();
		s.createSpy("message");
	}
}
