package souravcompany.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sourav.testcomponents.BaseTest;
import souravcompany.CartPage;
import souravcompany.CheckOutPage;
import souravcompany.ConfirmationPage;
import souravcompany.OrderHistoryPage;
import souravcompany.ProductsPage;
import resources.Retry;

public class StandAloneTest extends BaseTest{
	
	String countryname = "india";

	@Test(dataProvider="getTestData",retryAnalyzer=Retry.class)
	public void submitOrderTest(HashMap<String, String> input) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		
		
		ProductsPage prodpage = loginpage.userLogin(input.get("email"), input.get("password"));
		Assert.assertEquals("Login Successfully", prodpage.successLogin());
		CartPage cartpage = prodpage.addProductToCart(input.get("product"));
		prodpage.goToCart();
		Assert.assertTrue(cartpage.checkForProductInCart(input.get("product")));
		CheckOutPage checkoutpage = cartpage.checkOut();
		checkoutpage.countrySelect(countryname);
		ConfirmationPage confirmationpage = checkoutpage.submitOrder();
		Assert.assertTrue(confirmationpage.checkMessage().equalsIgnoreCase("Thankyou for the order."));
		
		
	}
	
	@Test(dataProvider="getTestData")
	public void orderHistoryTest(HashMap<String, String> input) {
		
		ProductsPage prodpage = loginpage.userLogin(input.get("email"), input.get("password"));
		OrderHistoryPage orderpage = prodpage.goToOrderHistory();
		Assert.assertEquals(true, orderpage.matchOrder(input.get("product")));
	}
	
	@DataProvider
	public Object[][] getTestData() throws IOException {
		List<HashMap<String, String>> testdata = jsonToMap(System.getProperty("user.dir")+"\\src\\main\\resources\\sourav\\testdata\\submitorderdata.json");
		return new Object[][] {{testdata.get(0)}, {testdata.get(1)}};
	}

}
