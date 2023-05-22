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

import org.junit.validator.PublicClassValidator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import at.tuwien.swtesting.pageobjects.BugListMassChangePage;
import at.tuwien.swtesting.pageobjects.BugListPage;
import at.tuwien.swtesting.pageobjects.CreateBugPage;
import at.tuwien.swtesting.pageobjects.HomePage;
import at.tuwien.swtesting.pageobjects.LoginPage;
import at.tuwien.swtesting.pageobjects.SearchPage;
import at.tuwien.swtesting.pageobjects.ShowBugPage;

public class SearchSteps {
	private static final String BASE_URL = "http://192.168.56.101/";
	private static final String USERNAME = "admin@example.com";
	private static final String PASSWORD = "Bugzilla1";

	private static final DriverManagerType DRIVER_TYPE = DriverManagerType.CHROME;

	private WebDriver driver;
	private HomePage homePage;
	private LoginPage loginPage;
	private CreateBugPage createBugPage;
	private ShowBugPage showBugPage;
	private SearchPage searchPage;
	private BugListPage bugListPage;
	private BugListMassChangePage bugListMassChangePage;
	
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

	@Given("I am logged in on the showbugpage and I have created two bugreports with summary {string}")
	public void i_am_logged_in_on_the_showbugpage_and_i_have_created_two_bugreports_with_summary(String summaryText) {

		homePage = HomePage.navigateTo(driver, BASE_URL);
		loginPage = homePage.gotoLoginPage();
		homePage = loginPage.login(USERNAME, PASSWORD);
		
		createBugPage = homePage.gotoCreateBugPage();
		createBugPage.setSummary(summaryText);
		createBugPage.submit();
		createBugPage = homePage.gotoCreateBugPage();
		createBugPage.setSummary(summaryText);
		showBugPage = createBugPage.submit();
	}


	@When("I go to the searchpage")
	public void i_go_to_the_searchpage(){
		searchPage = homePage.gotoSeachPage();
	}

	@When("I set bugstatus field to {string}")
	public void i_set_bugstatus_field_to(String bugstatus){
		searchPage.setBugStatus(bugstatus);
	}

	@When("I set product field to {string}")
	public void i_set_product_field_to(String product){
		searchPage.setProduct(product);
	}

	@When("I set the search term to {string}")
	public void i_set_search_term_to(String search){
		searchPage.setSearch(search);
	}

	@When("I submit the search")
	public void i_submit_the_search(){
		bugListPage = searchPage.submit();
	}

	@Then("I should be on the bugslistpage")
	public void i_should_be_on_the_bugslistpage(){
		assertEquals(bugListPage.getTitle(), "Bug List");
	}

	@Then("the result count should be {string}")
	public void the_result_count_should_be(String resultcount){
		assertEquals(bugListPage.getResultCount(), resultcount);
	}

	@Then("I cleanup after my searchtest")
	public void i_clean_up_after_my_searchtest(){
		// Clean up created bugs
		bugListMassChangePage = bugListPage.gotoMassChange();
		bugListMassChangePage.setCheckAll();
		bugListMassChangePage.setBugStatus("RESOLVED");
		bugListMassChangePage.setResolution("FIXED");
		bugListMassChangePage.submit();

	}
  
}
