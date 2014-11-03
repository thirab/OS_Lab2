package DentistOffice;

import static org.junit.Assert.*;

import org.junit.Test;

public class testOffice {

	Office office = new Office(3);
	@Test
	public void test() {
		office.addPatient();
		office.addPatient();
		//fail("Not yet implemented");
	}

}
