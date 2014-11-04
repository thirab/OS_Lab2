package DentistOffice;

import java.util.Stack;
import java.util.concurrent.Semaphore;
/**
 * Office where the dentist and patients meet.
 * @author tai-lanhirabayashi
 *
 */
public class Office {
	
	//semaphores for locking
	Semaphore dentistReady = new Semaphore(1);
	Semaphore seatCountWriteAccess = new Semaphore(1);
	Semaphore patientReady = new Semaphore(0);
	
	//max number of patients
	int sizeChairs;
	
	//calculated based on number of patients
	int numberOfFreeSeats;

	/**
	 * Creates the Dentists Office
	 * @param chairs
	 */
	Office(int chairs){
		System.out.println("creating office");
		sizeChairs = chairs;
		numberOfFreeSeats=sizeChairs;
		
		//creates a dentist thread
		Dentist dentist = new Dentist(seatCountWriteAccess,dentistReady, patientReady, sizeChairs);
		(new Thread(dentist)).start();
		addPatient();
	}
	/**
	 * Creates a new patient thread, runs forever, simulating new patients entering
	 */
	public void addPatient(){
		Patient newP = new Patient(seatCountWriteAccess,dentistReady, patientReady, sizeChairs);
		Thread newT = new Thread(newP);
		newT.start();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Office off = new Office(3);
		off.addPatient();
		off.addPatient();
		
		// TODO Auto-generated method stub
		

	}

}
