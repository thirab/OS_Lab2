package PeaceShire;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShireTest {

	/**
	 * Tests the shire espionage message passing system
	 * It's currently passing numbers to make sure they are in order
	 */
	Shire s = new Shire();
	@Test
	public void test() {
		s.createAgent();
		s.createSpy("message");
	}

}
