package souravcompany;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import souravabstractcomponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{

	public WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h1[@class='hero-primary']")
	WebElement thanksmsg;
	
	public String checkMessage() {
		
		return thanksmsg.getText();
	}

}
