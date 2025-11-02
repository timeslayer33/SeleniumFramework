package souravabstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import souravcompany.OrderHistoryPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cart;
	
	@FindBy(xpath="//i[@class='fa fa-handshake-o']")
	WebElement orders;
	
	
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	public void waitForElementToAppear(By elem) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elem));
	}
	
	public void waitForWebElementToAppear(WebElement e) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(e));
		
	}
	
	public void waitForInvisibility(By elem2) throws InterruptedException {
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(elem2));
		Thread.sleep(5000);
	}
	
	public void goToCart() {
		
		cart.click();
	}
	
	public OrderHistoryPage goToOrderHistory() {
		
		orders.click();
		OrderHistoryPage orderpage = new OrderHistoryPage(driver);
		return orderpage;
		
	}

}
