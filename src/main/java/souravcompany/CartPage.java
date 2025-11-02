package souravcompany;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import souravabstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	public WebDriver driver;
	public CartPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//div[@class='infoWrap']/div/h3")
	List<WebElement> cartlist;
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkoutbtn;
	
	
	public List<WebElement> getCartItems() {
		
		return cartlist;
	}
	
	public Boolean checkForProductInCart(String productname) {
		
		Boolean cartprod = getCartItems().stream().anyMatch(s->s.getText().equalsIgnoreCase(productname));
		return cartprod;
	}
	
	public CheckOutPage checkOut() {
		
		checkoutbtn.click();
		CheckOutPage checkoutpage = new CheckOutPage(driver);
		return checkoutpage;
	}

}
