package at.tuwien.swtesting;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Before;
import io.cucumber.java.After;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import at.tuwien.swtesting.pageobjects.CreateBugPage;
import at.tuwien.swtesting.pageobjects.HomePage;
import at.tuwien.swtesting.pageobjects.LoginPage;
import at.tuwien.swtesting.pageobjects.ShowBugPage;

public class CreateBugSteps {
	private static final String BASE_URL = "http://192.168.56.101/";
	private static final String USERNAME = "admin@example.com";
	private static final String PASSWORD = "Bugzilla1";

	private static final DriverManagerType DRIVER_TYPE = DriverManagerType.CHROME;

	private WebDriver driver;
	private HomePage homePage;
	private LoginPage loginPage;
	private CreateBugPage createBugPage;
	private ShowBugPage showBugPage;
	
	@Before
	public void setUpBeforeClass() {
		WebDriverManager.getInstance(DRIVER_TYPE).setup(); 	
		driver = new ChromeDriver();
	}

	@After
	public void tearDownAfterClass() {
		driver.close();
		driver.quit();
	}

	@Given("I am logged in on the homepage")
	public void i_am_logged_in_on_the_homepage() {
		homePage = HomePage.navigateTo(driver, BASE_URL);
		loginPage = homePage.gotoLoginPage();
		homePage = loginPage.login(USERNAME, PASSWORD);
	}


	@When("I go to the createbugpage")
	public void go_to_createbugpage() {
		createBugPage = homePage.gotoCreateBugPage();
	}

	@When("I add a summary {string}")
	public void add_summary(String summary) {
		createBugPage.setSummary(summary);
	}

	@When("I add a description {string}")
	public void add_description(String description) {
		createBugPage.setDescription(description);
	}

	@When("I submit the bug")
	public void submit_bug() {
		showBugPage = createBugPage.submit();
	}

	@Then("I should be on the showbugpage")
	public void i_should_be_on_the_showbugpage() {
        String expectedPageTitleRegex = "[0-9]+ – .+";
		assertTrue(showBugPage.getTitle().matches(expectedPageTitleRegex));
	}

	@Then("there should be a bugcreationelement present")
	public void is_bug_creation_element_present() {
		assert(showBugPage.getNumberOfBugCreationElements() > 0);
	}

	@Then("the first comment should be {string}")
	public void first_comment_is(String comment) {
		assertEquals(showBugPage.getFirstComment(), comment);
	}

	@Then("the summary should be {string}")
	public void summary_is(String summary) {
		assertEquals(showBugPage.getSummary(), summary);
	}

	@Then("I clean up after my createbugtest")
	public void i_clean_up_after_my_createbugtest() {
		showBugPage.setBugStatus("RESOLVED");
		showBugPage.setResolution("FIXED");
		showBugPage.submit();
	}

}
