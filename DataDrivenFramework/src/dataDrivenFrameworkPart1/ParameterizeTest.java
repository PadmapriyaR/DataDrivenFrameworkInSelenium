package dataDrivenFrameworkPart1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.excel.utility.XlsReader;

public class ParameterizeTest {

	static WebDriver driver;
	
	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Studies\\Selenium with Java1.0\\JarFiles\\Upgraded\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		//driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://reg.ebay.com/reg/PartialReg?siteid=0&UsingSSL=1&co_partnerId=2&errmsg=&src=&ru=https%3A%2F%2Fwww.ebay.com%2F&signInUrl=https%3A%2F%2Fsignin.ebay.com%3A443%2Fws%2FeBayISAPI.dll%3FSignIn%26ru%3Dhttps%253A%252F%252Fwww.ebay.com%252F&rv4=1");
		
		//Read data from excel using the excel utility function
		XlsReader reader = new XlsReader("D:\\Studies\\Selenium with Java1.0\\Hybrid Framework with POM\\DataDrivenFramework\\src\\com\\excel\\testdata\\HalfEBayTestData.xlsx");
		int rowCount = reader.getRowCount("RegTestData");
		
		//Parameterization
		for(int rowNum = 2; rowNum <= rowCount; rowNum++) {
			String firstName = reader.getCellData("RegTestData", "Firstname", rowNum);
			String lastName = reader.getCellData("RegTestData", "Lastname", rowNum);
			String email = reader.getCellData("RegTestData", "Emailaddress", rowNum);
			String password = reader.getCellData("RegTestData", "Password", rowNum);
			System.out.println(firstName);
			System.out.println(lastName);
			System.out.println(email);
			System.out.println(password);
			
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
		driver.quit();
	}

}
