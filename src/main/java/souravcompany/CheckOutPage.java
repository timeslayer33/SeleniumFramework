package souravcompany;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import souravabstractcomponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	
	public WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement selectcntry;
	
	@FindBy(xpath="//span[@class='ng-star-inserted']")
	WebElement cntrychoice;
	
	@FindBy(xpath="//a[text()='Place Order ']")
	WebElement placeorder;
	
	public void countrySelect(String countryname) {
		selectcntry.sendKeys(countryname);
		cntrychoice.click();
		
	}
	
	public ConfirmationPage submitOrder() {
		placeorder.click();
		ConfirmationPage confirmationpage = new ConfirmationPage(driver);
		return confirmationpage;
	}

}
