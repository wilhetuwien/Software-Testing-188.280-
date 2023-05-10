package at.tuwien.swtesting;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;

import at.tuwien.swtesting.pageobjects.HomePage;
import at.tuwien.swtesting.pageobjects.LoginPage;

public class LoginPageObjectTest {
	
	private static final String BASE_URL = "http://192.168.56.101/";
	private static final String USERNAME = "admin@example.com";
	private static final String PASSWORD = "Bugzilla1";
	
	private static final DriverManagerType DRIVER_TYPE = DriverManagerType.CHROME;
	
	private static WebDriver driver;
	private HomePage homePage;
	private LoginPage loginPage;

	@BeforeAll
	public static void setUpBeforeAll() {
		WebDriverManager.getInstance(DRIVER_TYPE).setup(); 	
		driver = new ChromeDriver();
	}

	@AfterAll
	public static void tearDownAfterAll() {
		driver.close();
		driver.quit();
	}
	
	@BeforeEach
	public void setUp() {
		homePage = HomePage.navigateTo(driver, BASE_URL);
	}

	//@Test
	public void testLoginLogout() {
		assertFalse(homePage.isLoggedin());
		loginPage = homePage.gotoLoginPage();
		homePage = loginPage.login(USERNAME, PASSWORD);
		assertTrue(homePage.isLoggedin());
		homePage = homePage.logout();
		assertFalse(homePage.isLoggedin());
	}


}
