package souravcompany;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import souravabstractcomponents.AbstractComponent;

public class ProductsPage extends AbstractComponent {
	
	public WebDriver driver;
	By elem = By.xpath("//div[@class='card-body']");
	By addtocart = By.xpath("//button[text()=' Add To Cart']");
	By message = By.id("toast-container");
	By spinner = By.cssSelector(".ng-animating");
	
	public ProductsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='card-body']")
	List<WebElement> products;
	
	@FindBy(xpath="//div[contains(@class,'toast-success')]")
	WebElement successmessage;
	
	//ng-tns-c4-8 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-success
	
	public List<WebElement> getProducts() {
		
		waitForElementToAppear(elem);
		return products;
	}
	
	public WebElement selectProduct(String buyitem) {
		
		WebElement prod = getProducts().stream().filter(s->s.findElement(By.cssSelector("b")).getText().equals(buyitem)).findFirst().orElse(null);
		return prod;
	}
	
	public CartPage addProductToCart(String buyitem) throws InterruptedException {
		
		selectProduct(buyitem).findElement(addtocart).click();
		waitForElementToAppear(message);
		waitForInvisibility(spinner);
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
	
	public String successLogin() {
		
		waitForWebElementToAppear(successmessage);
		return successmessage.getText();
	}

			

}
