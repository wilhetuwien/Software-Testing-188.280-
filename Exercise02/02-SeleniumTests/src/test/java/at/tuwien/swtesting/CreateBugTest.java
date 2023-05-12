// Generated by Selenium IDE
package at.tuwien.swtesting;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;

import org.openqa.selenium.JavascriptExecutor;
import java.util.*;

public class CreateBugTest {
  static JavascriptExecutor js;

	private static final String BASE_URL = "http://192.168.56.101/";
	private static final String USERNAME = "admin@example.com";
	private static final String PASSWORD = "Bugzilla1";
	
	private static final DriverManagerType DRIVER_TYPE = DriverManagerType.CHROME;

	private static WebDriver driver;

  @BeforeAll
  public static void setUp() {
		WebDriverManager.getInstance(DRIVER_TYPE).setup();
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;

		driver.get(BASE_URL);
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
  }

  @AfterAll
  public static void tearDown() {
		WebElement logoutLink = driver.findElement(By.linkText("Log out"));
		logoutLink.click();

		driver.close();
		driver.quit();
  }
  
  @Test
  public void createBugTest() {
    // Test name: CreateBugTest
    /*
     * Creates a report and then sets it as resolved upon cleanup
     */
    // Step # | name | target | value
    String summaryText = "TestCreateBug";
    String commentText = "A simple test for creating a bugreport";
    // 1 | open | / | 
    driver.get(BASE_URL);
    // 2 | click | linkText=New | 
    driver.findElement(By.linkText("New")).click();
    // 3 | click | id=short_desc | 
    driver.findElement(By.id("short_desc")).click();
    // 4 | type | id=short_desc | TestCreateBug
    driver.findElement(By.id("short_desc")).sendKeys(summaryText);
    // 5 | click | id=comment | 
    driver.findElement(By.id("comment")).click();
    // 6 | type | id=comment | A simple test for creating a bugreport
    driver.findElement(By.id("comment")).sendKeys(commentText);
    // 7 | click | id=commit | 
    driver.findElement(By.id("commit")).click();
    // 8 | assertElementPresent | xpath=//*[@id="bugzilla-body"]/dl/dt[starts-with(text(),'Bug ') and contains(text()[2], " has been successfully created") and string-length(translate(.//a,"0123456789", "0123456789")) > 0] | 
    // Find element confirming successfull creation of bugreport
    {
      List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"bugzilla-body\"]/dl/dt[starts-with(text(),\'Bug \') and contains(text()[2], \" has been successfully created\") and string-length(translate(.//a,\"0123456789\", \"0123456789\")) > 0]"));
      assert(elements.size() > 0);
    }
    // 9 | verifyText | id=comment_text_0 | A simple test for creating a bugreport
    assertEquals(driver.findElement(By.id("comment_text_0")).getText(), commentText);
    // 10 | verifyText | id=short_desc_nonedit_display | TestCreateBug
    assertEquals(driver.findElement(By.id("short_desc_nonedit_display")).getText(), summaryText);
    // 11 | click | css=.bz_bug | 
    driver.findElement(By.cssSelector(".bz_bug")).click();
    // Start clean up
    // 13 | select | id=bug_status | label=RESOLVED
    {
      WebElement dropdown = driver.findElement(By.id("bug_status"));
      dropdown.findElement(By.xpath("//option[. = 'RESOLVED']")).click();
    }
    // 12 | select | id=resolution | label=FIXED
    {
      WebElement dropdown = driver.findElement(By.id("resolution"));
      dropdown.findElement(By.xpath("//option[. = 'FIXED']")).click();
    }
    // 14 | click | css=table:nth-child(2) > tbody > tr > td | 
    driver.findElement(By.cssSelector("table:nth-child(2) > tbody > tr > td")).click();
    // 15 | click | id=commit | 
    driver.findElement(By.id("commit")).click();
  }

  @Test
  public void searchTest() {
    // Test name: SearchTest
    /*
     * Creates two reports with and searches for open issues with that name. 
     * Upon cleanup it sets all issues found as resolved.
     */
    // Step # | name | target | value
    String summaryText = "searchforbugreport";
    // 1 | open | / | 
    driver.get(BASE_URL);
    // 2 | click | linkText=New | 
    // Initialise two bugs to be found
    driver.findElement(By.linkText("New")).click();
    // 3 | click | id=short_desc | 
    driver.findElement(By.id("short_desc")).click();
    // 4 | type | id=short_desc | searchforbugreport
    driver.findElement(By.id("short_desc")).sendKeys(summaryText);
    // 5 | click | id=commit | 
    driver.findElement(By.id("commit")).click();
    // 6 | click | linkText=New | 
    driver.findElement(By.linkText("New")).click();
    // 7 | click | id=short_desc | 
    driver.findElement(By.id("short_desc")).click();
    // 8 | type | id=short_desc | searchforbugreport
    driver.findElement(By.id("short_desc")).sendKeys(summaryText);
    // 9 | click | id=commit | 
    driver.findElement(By.id("commit")).click();
    // 10 | click | linkText=Search | 
    driver.findElement(By.linkText("Search")).click();
    // // 11 | select | id=bug_status | label=Open
    // {
    //   WebElement dropdown = driver.findElement(By.id("bug_status"));
    //   dropdown.findElement(By.xpath("//option[. = 'Open']")).click();
    // }
    // 12 | select | id=product | label=TestProduct
    {
      WebElement dropdown = driver.findElement(By.id("product"));
      // Following line was exported by selenium IDE and doesn't work.
      // dropdown.findElement(By.xpath("//option[. = 'TestProduct']")).click();

      // Following is the workaround because xpath doesn't work.
      Select drpDown = new Select(dropdown);
      drpDown.selectByVisibleText("TestProduct");
    }
    // 13 | click | id=content | 
    driver.findElement(By.id("content")).click();
    // 14 | type | id=content | searchforbugreport
    driver.findElement(By.id("content")).sendKeys(summaryText);
    // 15 | click | id=search | 
    driver.findElement(By.id("search")).click();
    // 17 | verifyText | css=.bz_result_count | 2 bugs found.
    assertEquals(driver.findElement(By.cssSelector(".bz_result_count")).getText(), "2 bugs found.");
    // 18 | click | id=check_all | 
    // Clean up created bugs
    // 16 | click | id=mass_change | 
    driver.findElement(By.id("mass_change")).click();
    driver.findElement(By.id("check_all")).click();
    // 19 | select | id=bug_status | label=RESOLVED
    {
      WebElement dropdown = driver.findElement(By.id("bug_status"));
      // Following line was exported by selenium IDE and doesn't work.
      // dropdown.findElement(By.xpath("//option[. = 'RESOLVED']")).click();

      // Following is the workaround because xpath doesn't work.
      Select drpDown = new Select(dropdown);
      drpDown.selectByVisibleText("RESOLVED");
      
    }
    // 20 | select | id=resolution | label=FIXED
    {
      WebElement dropdown = driver.findElement(By.id("resolution"));
      dropdown.findElement(By.xpath("//option[. = 'FIXED']")).click();
    }
    // 21 | click | id=commit | 
    driver.findElement(By.id("commit")).click();
  }


  @Test
  public void changeStatusTest() {
    // Test name: ChangeStatusTest
    /*
     * Creates a report, then copies it and resolvs the copy. 
     * Upon cleanup also resolves the original issue
     */
    // Step # | name | target | value
    String summaryText = "ChangeStatustest";
    String commentText = "Fixed this bug";
    // 1 | open | / | 
    driver.get(BASE_URL);
    // 2 | click | linkText=New | 
    // Create original bug
    driver.findElement(By.linkText("New")).click();
    // 3 | click | id=short_desc | 
    driver.findElement(By.id("short_desc")).click();
    // 4 | type | id=short_desc | ChangeStatustest
    driver.findElement(By.id("short_desc")).sendKeys(summaryText);
    // 5 | click | id=commit | 
    driver.findElement(By.id("commit")).click();
    // 6 | click | linkText=Clone This Bug | 
    // Clone Bug
    driver.findElement(By.linkText("Clone This Bug")).click();
    // 7 | click | id=commit | 
    driver.findElement(By.id("commit")).click();
    // 9 | select | id=bug_status | label=RESOLVED
    {
      WebElement dropdown = driver.findElement(By.id("bug_status"));
      dropdown.findElement(By.xpath("//option[. = 'RESOLVED']")).click();
    }
    // 8 | select | id=resolution | label=FIXED
    {
      WebElement dropdown = driver.findElement(By.id("resolution"));
      dropdown.findElement(By.xpath("//option[. = 'FIXED']")).click();
    }
    // 10 | click | id=comment | 
    driver.findElement(By.id("comment")).click();
    // 11 | type | id=comment | Fixed this bug 
    driver.findElement(By.id("comment")).sendKeys(commentText);
    // 12 | click | id=commit | 
    driver.findElement(By.id("commit")).click();
    // 13 | click | css=#bugzilla-body > dl > dt > a | 
    driver.findElement(By.cssSelector("#bugzilla-body > dl > dt > a")).click();
    // 14 | select | id=resolution | label=FIXED
    {
      WebElement dropdown = driver.findElement(By.id("resolution"));
      dropdown.findElement(By.xpath("//option[. = 'FIXED']")).click();
    }
    // 15 | verifyText | id=static_bug_status | RESOLVED FIXED (edit)
    assertEquals(driver.findElement(By.id("static_bug_status")).getText(), "RESOLVED FIXED (edit)");
    // 16 | verifyText | id=comment_text_1 | Fixed this bug
    assertEquals(driver.findElement(By.id("comment_text_1")).getText(), commentText);
    // 17 | click | xpath=//*[@id="comment_text_0"]/a | 
    // Clean up original report
    driver.findElement(By.xpath("//*[@id=\"comment_text_0\"]/a")).click();
    // 19 | select | id=bug_status | label=RESOLVED
    {
      WebElement dropdown = driver.findElement(By.id("bug_status"));
      dropdown.findElement(By.xpath("//option[. = 'RESOLVED']")).click();
    }
    // 18 | select | id=resolution | label=FIXED
    {
      WebElement dropdown = driver.findElement(By.id("resolution"));
      dropdown.findElement(By.xpath("//option[. = 'FIXED']")).click();
    }
    // 20 | click | id=commit | 
    driver.findElement(By.id("commit")).click();
  }
}
