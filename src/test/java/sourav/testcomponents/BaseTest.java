package sourav.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import souravcompany.LoginPage;

public class BaseTest {
	
	public WebDriver driver;
	public LoginPage loginpage;
	
	public void initializeDriver() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\sourav\\resources\\GlobalData.properties");
		prop.load(fs);
		
		String browser = (System.getProperty("browser"))!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
		//String browser = prop.getProperty("browser");
		switch(browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	
	public List<HashMap<String, String>> jsonToMap(String filepath) throws IOException {
		
		String testdata = FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(testdata, new TypeReference<List<HashMap<String,String>>>() {});
		return data;
		//System.getProperty("user.dir")+"\\src\\main\\resources\\sourav\\testdata\\submitorderdata.json"
	}
	
	public String getScreenShot(String testcasename, WebDriver driver) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+testcasename+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+("//reports//"+testcasename+".png");
		
	}
	
	@BeforeMethod(alwaysRun=true)
	public LoginPage goToApp() throws IOException {
		initializeDriver();
		loginpage = new LoginPage(driver);
		loginpage.gotoURL();
		return loginpage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void quitDriver() {
		
		driver.close();
	}

}
