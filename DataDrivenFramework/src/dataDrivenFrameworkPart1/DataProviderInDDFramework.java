package dataDrivenFrameworkPart1;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.test.util.TestUtil;

public class DataProviderInDDFramework {

	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "D:\\Studies\\Selenium with Java1.0\\\\JarFiles\\\\Upgraded\\\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(1000, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		driver.get("https://reg.ebay.com/reg/PartialReg?siteid=0&UsingSSL=1&co_partnerId=2&errmsg=&src=&ru=https%3A%2F%2Fwww.ebay.com%2F&signInUrl=https%3A%2F%2Fsignin.ebay.com%3A443%2Fws%2FeBayISAPI.dll%3FSignIn%26ru%3Dhttps%253A%252F%252Fwww.ebay.com%252F&rv4=1");
	}
	
	@DataProvider
	public Iterator<Object[]> getData() {
		ArrayList<Object[]> testData = TestUtil.getDataFromExcel();
		return testData.iterator();
	}
	
	@Test(dataProvider="getData")
	public void register(String firstName, String lastName, String email, String password) {
		//Selenium code - Entering data
		driver.findElement(By.xpath("//*[@id=\"firstname\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"firstname\"]")).sendKeys(firstName);
		
		driver.findElement(By.xpath("//input[@id='lastname']")).clear();
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);
		
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
		
		driver.findElement(By.xpath("//input[@id='PASSWORD']")).clear();
		driver.findElement(By.xpath("//input[@id='PASSWORD']")).sendKeys(password);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}

/* Notes:
 * DataProvider:-
 * 		
 * Let say, we have a test cases called loginTest(String username, String password). We need to execute this test
 * case for the different set of data. Let assume, we don't have parameterization and dataprovider concept,
 * we have to call the method for each data. This is not a good practice in coding.
 * 
 *   Instead of this, we have a concept called Data provider which will fetch the data from excel and will pass
 *   those data on to test.
 *   @Test(dataprovider="getData") // getdata is a method of dataprovider
 *   public void logintest(String username, String password){
 *   
 *   }
 *   @dataProvider
 *   getData(){
 *   //code which will fetch the data from excel
 *   }
 *
 * 	How many columns we have in excel sheet, that many parameters need to be passed as arguments	
 * 	We can create a common test util class in a seperate package. That will be common. whenever we want that 
 * utility we can call it.
 */
