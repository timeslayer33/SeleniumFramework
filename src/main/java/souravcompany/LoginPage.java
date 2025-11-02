package souravcompany;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import souravabstractcomponents.AbstractComponent;

public class LoginPage extends AbstractComponent {
	
	public WebDriver driver;
	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement emailid;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submitbtn;
	
	@FindBy(xpath="//div[contains(@class, 'ngx-toastr')]")
	WebElement errmessage;
	
	
	//.ng-tns-c4-23.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	//div[@class='ng-tns-c4-26 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error']
	////div[@aria-label='Incorrect email or password.']

	public void gotoURL() {
		
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductsPage userLogin(String mailid, String pass) {
		
		emailid.sendKeys(mailid);
		password.sendKeys(pass);
		submitbtn.click();
		ProductsPage prodpage = new ProductsPage(driver);
		return prodpage;
		}
	
	public String errorLoginMessage() {
		
		waitForWebElementToAppear(errmessage);
		System.out.println(errmessage.getText());
		return errmessage.getText();
	}
	
}
