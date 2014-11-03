package DentistOffice;

import java.util.concurrent.Semaphore;

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
			while(!seats.tryAcquire()){
				try {
			        synchronized(this){
			            wait();
			        }
			    } catch (InterruptedException e) {
			        // ...
			    }
			}
				System.out.println("New patient Enters");
				int waiting = patients.availablePermits();
				if(waiting < max){
					System.out.println("waiting: " +waiting + " Max: " + max);
					patients.release();
					int dent = dentist.availablePermits();
					if(dent == 0){
						System.out.println("wake up the dentist");
						dentist.release();
					}
					System.out.println("waiting for a dentist");
					System.out.println("Dentist is: " + dentist.availablePermits());
					while(dentist.availablePermits() ==1){
						synchronized(this){
						    notify();
						}
					}
					//in this case this consulting with dentist doesnt actually have a purpose
//					while(!dentist.tryAcquire()){
//					}
//					//consult dentist
//					dentist.release();
				}
				//release seats
				System.out.println("Releasing the seats");
				seats.release();
			}
		}
	}
		


