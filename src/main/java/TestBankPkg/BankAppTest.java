package TestBankPkg;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import PageObjects.*;
import Utility.JSONReader;
import Utility.DriverStart;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
public class BankAppTest {
	
	DriverStart drv;
	WebDriver driver;

	@BeforeClass
	public void setUp() throws FileNotFoundException, IOException, ParseException{
		//Set up desired capabilities and pass the Android app-activity and app-package to Appium
		drv = new DriverStart();
		driver = drv.drvStart();
	}

	@Test
	public void testBankLoginMakePayment() throws Exception {
		JSONReader _JSONRead = new JSONReader();
		LoginScreen logSc = new LoginScreen(driver);
		logSc.setUserName(_JSONRead.ReadJSONFile("User", ".\\Data\\wsData.json"));
		logSc.setPassword(_JSONRead.ReadJSONFile("User", ".\\Data\\wsData.json"));
		logSc.clickLogin();
		
		MainScreen mainPage = new MainScreen(driver);
		mainPage.clickPaymentButton();
		
		MakePaymentScreen mkPayment = new MakePaymentScreen(driver);
		mkPayment.makePayment(_JSONRead.ReadJSONFile("tele", ".\\Data\\wsData.json"), _JSONRead.ReadJSONFile("name", ".\\Data\\wsData.json"), _JSONRead.ReadJSONFile("country", ".\\Data\\wsData.json"));
	}

	@AfterClass
	public void teardown(){
		//close the app
		driver.quit();
	}
}
