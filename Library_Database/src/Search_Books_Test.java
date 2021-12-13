
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class TestEndToEndPages {

	private WebDriver driver;

	@Before
	public void setUp() {
		// Create a new instance of the html unit driver
		driver = new HtmlUnitDriver();

		//Navigate to desired web page
		driver.get("http://localhost:6060/WebApplication4Selenium");
	}