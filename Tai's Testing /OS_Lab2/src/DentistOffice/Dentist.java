package DentistOffice;

import java.util.concurrent.Semaphore;

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
			System.out.println("Tring dentist");
			while(!dentist.tryAcquire()){
				try {
					synchronized(this){
						wait();
					}
				} catch (InterruptedException e) {
					// ...
				}
			}

			System.out.println("Dentist is awake");


			while(!seats.tryAcquire()){
				try {
					synchronized(this){
						wait();
					}
				} catch (InterruptedException e) {
					// ...
				}
			}
			System.out.println("dentist got seats");

			while(patients.availablePermits()>0){
				//treat each patient
				System.out.println("Treated a patient: " +  patientsTreated);
				patientsTreated++;
				patients.tryAcquire();
			}
			seats.release();
			System.out.println("Seats: " + seats.availablePermits());

		}


	}

}


