package dataDrivenFrameworkPart1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.excel.utility.XlsReader;

public class DataDriven {

	static WebDriver driver;
	
	public static void main(String[] args) {
		
		//Read data from excel using the excel utility function
		XlsReader reader = new XlsReader("D:\\Studies\\Selenium with Java1.0\\Hybrid Framework with POM\\DataDrivenFramework\\src\\com\\excel\\testdata\\HalfEBayTestData.xlsx");
		String firstName = reader.getCellData("RegTestData", "Firstname", 2);
		System.out.println(firstName);
		String lastName = reader.getCellData("RegTestData", "Lastname", 2);
		System.out.println(lastName);
		String email = reader.getCellData("RegTestData", "Emailaddress", 2);
		System.out.println(email);
		String password = reader.getCellData("RegTestData", "Password", 2);
		System.out.println(password);
		
		//Selenium code
		System.setProperty("webdriver.chrome.driver", "D:\\Studies\\Selenium with Java1.0\\JarFiles\\Upgraded\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		//driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://reg.ebay.com/reg/PartialReg?siteid=0&UsingSSL=1&co_partnerId=2&errmsg=&src=&ru=https%3A%2F%2Fwww.ebay.com%2F&signInUrl=https%3A%2F%2Fsignin.ebay.com%3A443%2Fws%2FeBayISAPI.dll%3FSignIn%26ru%3Dhttps%253A%252F%252Fwww.ebay.com%252F&rv4=1");
		
		driver.findElement(By.xpath("//*[@id=\"firstname\"]")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='PASSWORD']")).sendKeys(password);
		
	}
}

/* Notes:
 * DataDriven Framework:-
 * 	It is one of the most important topics in selenium. Using excel sheet, we can get the input data using this
 * framework. 
 * Why we are using excel sheet?
 * 	It is very user friendly, so easy to use. Data are stores in the form of row and column
 * so easy to maintain.	We can create many sheet in a single workbook.
 * 
 * No one in interview will ask the syntax to write utility and read data from excel. This utility already will be
 * created. we need to just use the utility. Then we need to start working upon it. 
 * 
 * It is also called as Data driven approach. It is used to create data driven framework.
 * It is also called as Parameterization.
 */
