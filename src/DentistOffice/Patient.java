package DentistOffice;

import java.util.concurrent.Semaphore;
/**
 * Patients seat themselves if there are avaliable chairs and are treated by the dentist.
 * @author tai-lanhirabayashi
 *
 */
public class Patient implements Runnable {
	Semaphore seats;
	Semaphore dentist;
	Semaphore patients;
	int max=0;
	Patient(Semaphore seatAccess, Semaphore dentistStatus, Semaphore patientReady, int maxSeats){
		seats = seatAccess;
		dentist = dentistStatus;
		patients = patientReady;
		max = maxSeats;
	}
	@Override
	public void run() {
		//System.out.println("new Patient");
		while(true){
			
			//until seats are avaliable do nothign
			while(!seats.tryAcquire()){
			}
				//see if there are enought seats
				int waiting = patients.availablePermits();
				if(waiting < max){
					
					//if there are enough seats set, else leave
					System.out.println("new patient sits");
					System.out.println("waiting: " +waiting + " Max: " + max);
					patients.release();
					int dent = dentist.availablePermits();
					
					//if dentist is asleep wake him up
					if(dent == 0){
						System.out.println("wake up the dentist");
						dentist.release();
					}
					System.out.println("waiting for a dentist");
					System.out.println("Dentist is: " + dentist.availablePermits());
					
					
				}
				//release seats
				seats.release();
			}
		}
	}
		


