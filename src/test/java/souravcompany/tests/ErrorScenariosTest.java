package souravcompany.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import sourav.testcomponents.BaseTest;

public class ErrorScenariosTest extends BaseTest {
	
	@Test
	public void loginErrorCheckTest() {
		
		loginpage.userLogin("hambadolu123@gmail.com","123");
		Assert.assertEquals(loginpage.errorLoginMessage(), "Incorrect email or password.");
	}
	
	

}
