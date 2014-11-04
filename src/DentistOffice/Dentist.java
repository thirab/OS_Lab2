package DentistOffice;

import java.util.concurrent.Semaphore;
/**
 * Dentist processes patients.
 * Sleeps when there are no patients
 * @author tai-lanhirabayashi
 *
 */
public class Dentist implements Runnable {

	Semaphore seats;
	Semaphore dentist;
	Semaphore patients;
	int max=0;
	int patientsTreated = 0;
	Dentist(Semaphore seatAccess, Semaphore dentistStatus, Semaphore patientReady, int maxSeats){
		seats = seatAccess;
		dentist = dentistStatus;
		patients = patientReady;
		max = maxSeats;
	}
	@Override
	public void run() {

		while(true){
			//while dentist is not awake do nothing
			while(!dentist.tryAcquire()){
			}


			//while seats are being used do nothing
			while(!seats.tryAcquire()){
			}

			//while there are patients treat them
			while(patients.tryAcquire()){
				//treat each patient
				System.out.println("Treated a patient: " +  patientsTreated);
				patientsTreated++;
			}
			
			//release the seats 
			seats.release();

		}


	}

}


