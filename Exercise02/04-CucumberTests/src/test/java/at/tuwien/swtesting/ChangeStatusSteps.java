/*
 * William Hedlund
 * 12233006
 * exercise 2 assignment 4
 */
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
import at.tuwien.swtesting.pageobjects.ProcessBugPage;
import at.tuwien.swtesting.pageobjects.ShowBugPage;

public class ChangeStatusSteps {
	private static final String BASE_URL = "http://192.168.56.101/";
	private static final String USERNAME = "admin@example.com";
	private static final String PASSWORD = "Bugzilla1";

	private static final DriverManagerType DRIVER_TYPE = DriverManagerType.CHROME;

	private WebDriver driver;
	private HomePage homePage;
	private LoginPage loginPage;
	private CreateBugPage createBugPage;
	private ShowBugPage showBugPage;
	private ProcessBugPage processBugPage;
	
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

	@Given("I am logged in on the showbugpage and I have created a bug and cloned it")
	public void i_am_logged_in_on_the_showbugpage_and_i_have_created_a_bug_and_cloned_it() {
		homePage = HomePage.navigateTo(driver, BASE_URL);
		loginPage = homePage.gotoLoginPage();
		homePage = loginPage.login(USERNAME, PASSWORD);

		createBugPage = homePage.gotoCreateBugPage();
		createBugPage.setSummary("ChangeStatustest");
		showBugPage = createBugPage.submit();

		createBugPage = showBugPage.cloneBug();
		showBugPage = createBugPage.submit();
	}
	
	@Then("I should be on the processbugpage")
	public void i_should_be_on_the_processbugpage() {
		String expectedPageTitleRegex = ".+ processed";
		assertTrue(processBugPage.getTitle().matches(expectedPageTitleRegex));
	}

    @When ("I set bug status to {string}")
	public void set_bug_status_to(String bugstatus){
		showBugPage.setBugStatus("RESOLVED");
	}

    @When ("I set resolution status to {string}")
	public void set_resolution_status_to(String resolution){
		showBugPage.setResolution("FIXED");
	}

    @When ("I set the comment to {string}")
	public void set_comment_to(String comment){
		showBugPage.setComment("Fixed this bug ");
	}

    @When ("I submit the bugreport")
	public void submit_the_bugreport(){
		processBugPage = showBugPage.submit();
	}

    @Then ("I go to the bug i changed")
	public void i_go_to_the_bug_i_changed(){
		showBugPage = processBugPage.gotoFirstBugInList();
	}

    @Then ("the bug status should be {string}")
	public void the_bug_status_should_be(String bugstatus){
		assertEquals(showBugPage.getStaticBugStatus(), "RESOLVED FIXED (edit)");
	}

    @Then ("the second comment should be {string}")
	public void the_second_comment_should_be(String comment){
		assertEquals(showBugPage.getSecondComment(), "Fixed this bug");
	}
	
    @Then ("I clean up after my changestatustest")
	public void clean_up_changestatustest(){
		showBugPage = showBugPage.gotoOriginalBug();
		showBugPage.setBugStatus("RESOLVED");
		showBugPage.setResolution("FIXED");
		showBugPage.submit();
	}

	
}
