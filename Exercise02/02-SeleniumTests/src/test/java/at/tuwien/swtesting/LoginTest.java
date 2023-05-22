/*
 * William Hedlund
 * 12233006
 * exercise 2 assignment 2
 */
package at.tuwien.swtesting;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;

public class LoginTest {

	private static final String BASE_URL = "http://192.168.56.101/";
	private static final String USERNAME = "admin@example.com";
	private static final String PASSWORD = "Bugzilla1";
	
	private static final DriverManagerType DRIVER_TYPE = DriverManagerType.CHROME;

	private static WebDriver driver;
	

	@BeforeAll
	public static void setUpBeforeAll() {
		WebDriverManager.getInstance(DRIVER_TYPE).setup();
		driver = new ChromeDriver();

		// WebDriverManager.firefoxdriver().setup();
		// driver = new FirefoxDriver();

		driver.get(BASE_URL);		
	}

	@AfterAll
	public static void tearDownAfterAll() {
		driver.close();
		driver.quit();
	}

	@Test
	public void testLoginLogout() {
		assertFalse(isElementPresent(By.linkText("Log out")));

		driver.get(BASE_URL + "index.cgi?GoAheadAndLogIn=1");

		// on login page ...

		WebElement loginName = driver.findElement(By.id("Bugzilla_login"));
		loginName.clear();
		loginName.sendKeys(USERNAME);

		WebElement loginPassword = driver.findElement(By.id("Bugzilla_password"));
		loginPassword.clear();
		loginPassword.sendKeys(PASSWORD);

		WebElement loginButton = driver.findElement(By.id("log_in"));
		loginButton.submit();
		
		// back on homepage ...
		
		assertTrue(isElementPresent(By.linkText("Log out")));
		
		WebElement logoutLink = driver.findElement(By.linkText("Log out"));
		logoutLink.click();
		
		assertFalse(isElementPresent(By.linkText("Log out")));
	}

	
	private boolean isElementPresent(By by) {
		return driver.findElements(by).size() > 0;
	}	
	
}
