package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class adminLogin_Test {
	private WebDriver driver;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
		
	/*@Before
	   public void setUp() throws Exception {
	      System.setProperty("webdriver.chrome.driver", //
	    		  "lib\\win\\chromedriver.exe");
	      driver = new ChromeDriver();
	      // driver = new FirefoxDriver();
	      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   }*/
	
	@Test
	public void adminLogin() throws Exception {
		driver.get("http://ec2-3-135-9-177.us-east-2.compute.amazonaws.com:8080/Library_Database/Homepage_User.html");
		driver.manage().window().maximize();
	    driver.findElement(By.id("Admin Login")).click();
	    Thread.sleep(200);
	    
	    driver.findElement(By.name("password")).sendKeys("adminPassword");
	    
	    driver.findElement(By.id("Admin Login")).click();
	    
	    String expected = "Insert_User.html";
	    String result = driver.findElement(By.name("Insert New User")).getAttribute("innerHTML");
	    Assert.assertEquals(expected, result);
	}

}
