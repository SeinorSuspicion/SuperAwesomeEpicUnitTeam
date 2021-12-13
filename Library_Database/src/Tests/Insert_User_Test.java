package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Insert_User_Test {

	@Test
	public void testGetDiscount() throws Exception {
		Computation com = new Computation();
		int input1 = 100, input2 = 2;
		int expected = 30;
		int result = com.getDiscount(input1, input2);
		Assert.assertEquals(expected, result);
	}

}
