package Tests;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class Search_Users_Test {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	  @Before
	  public void setUp() throws Exception {
	      System.setProperty("webdriver.chrome.driver", //
	    		  "lib\\win\\chromedriver.exe");
	      driver = new ChromeDriver();
	   // driver = new FirefoxDriver();
	    baseUrl = "ec2-3-135-9-177.us-east-2.compute.amazonaws.com:3306/LibraryDatabase?enabledTLSProtocols=TLSv1.2";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

}
