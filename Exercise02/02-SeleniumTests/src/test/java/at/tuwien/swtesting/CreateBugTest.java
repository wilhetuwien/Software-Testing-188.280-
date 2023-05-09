// Generated by Selenium IDE
package at.tuwien.swtesting;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

public class CreateBugTest {
  private static Map<String, Object> vars;
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
    vars = new HashMap<String, Object>();

		driver.get(BASE_URL);

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
    // Step # | name | target | value
    // 1 | open | / | 
    driver.get("http://192.168.56.101/");
    // 2 | setWindowSize | 550x692 | 
    // driver.manage().window().setSize(new Dimension(550, 692));
    // 3 | click | linkText=New | 
    driver.findElement(By.linkText("New")).click();
    // 4 | click | id=short_desc | 
    driver.findElement(By.id("short_desc")).click();
    // 5 | type | id=short_desc | Summary
    driver.findElement(By.id("short_desc")).sendKeys("Summary");
    // 6 | type | id=comment | Description
    driver.findElement(By.id("comment")).sendKeys("Description");
    // 7 | verifyValue | id=short_desc | Summary
    {
      String value = driver.findElement(By.id("short_desc")).getAttribute("value");
      assertEquals(value, "Summary");
    }
    // 8 | verifyValue | id=comment | Description
    {
      String value = driver.findElement(By.id("comment")).getAttribute("value");
      assertEquals(value, "Description");
    }
    // 9 | click | id=commit | 
    driver.findElement(By.id("commit")).click();
    // 10 | assertElementPresent | css=#bugzilla-body > dl > dt | 
    // Verify element indicateing that bug has been created exists
    {
      List<WebElement> elements = driver.findElements(By.cssSelector("#bugzilla-body > dl > dt"));
      assert(elements.size() > 0);
    }
  }

  @Test
  public void searchTest() {
    // Test name: SearchTest
    // Step # | name | target | value | comment
    // 1 | open | http://192.168.56.101/ |  | 
    driver.get("http://192.168.56.101/");
    // 2 | setWindowSize | 550x694 |  | 
    // driver.manage().window().setSize(new Dimension(550, 694));
    // 3 | click | id=query |  | 
    driver.findElement(By.id("query")).click();
    // 4 | click | id=bug_status |  | 
    driver.findElement(By.id("bug_status")).click();
    // 5 | select | id=bug_status | label=All | 
    {
      WebElement dropdown = driver.findElement(By.id("bug_status"));
      dropdown.findElement(By.xpath("//option[. = 'All']")).click();
    }
    // 6 | click | css=option:nth-child(3) |  | 
    driver.findElement(By.cssSelector("option:nth-child(3)")).click();
    // 7 | click | id=product |  | 
    driver.findElement(By.id("product")).click();
    // 8 | select | id=product | label=TestProduct | 
    {
      WebElement dropdown = driver.findElement(By.id("product"));
      // Following line was exported by selenium IDE and doesn't work.
      // dropdown.findElement(By.xpath("//option[. = 'TestProduct']")).click();

      // Following is the workaround because xpath doesn't work.
      Select drpDown = new Select(dropdown);
      drpDown.selectByVisibleText("TestProduct");
    }
    // 9 | click | css=#product > option:nth-child(2) |  | 
    driver.findElement(By.cssSelector("#product > option:nth-child(2)")).click();
    // 10 | click | id=content |  | 
    driver.findElement(By.id("content")).click();
    // 11 | type | id=content | for search test | 
    driver.findElement(By.id("content")).sendKeys("for search test");
    // 12 | click | id=search |  | 
    driver.findElement(By.id("search")).click();
    // 13 | verifyText | css=.bz_result_count | 2 bugs found. | 
    assertEquals(driver.findElement(By.cssSelector(".bz_result_count")).getText(), "2 bugs found.");
  }


  @Test
  public void changeStatusTest() {
    // Test name: ChangeStatusTest
    // Step # | name | target | value | comment
    // 1 | open | http://192.168.56.101/ |  | 
    driver.get("http://192.168.56.101/");
    // 2 | setWindowSize | 550x694 |  | 
    // driver.manage().window().setSize(new Dimension(550, 694));
    // 3 | click | linkText=Browse |  | 
    driver.findElement(By.linkText("Browse")).click();
    // 4 | click | linkText=TestComponent |  | 
    driver.findElement(By.linkText("TestComponent")).click();
    // 5 | click | linkText=2 |  | 
    driver.findElement(By.linkText("2")).click();
    // 6 | click | linkText=Clone This Bug |  | 
    driver.findElement(By.linkText("Clone This Bug")).click();
    // 7 | click | id=commit |  | 
    driver.findElement(By.id("commit")).click();
    // 8 | click | id=bug_status |  | 
    driver.findElement(By.id("bug_status")).click();
    // 10 | select | id=bug_status | label=RESOLVED | 
    {
      WebElement dropdown = driver.findElement(By.id("bug_status"));
      dropdown.findElement(By.xpath("//option[. = 'RESOLVED']")).click();
    }
    // 9 | select | id=resolution | label=FIXED | 
    {
      WebElement dropdown = driver.findElement(By.id("resolution"));
      try {
        dropdown.findElement(By.xpath("//option[. = 'FIXED']")).click();

      } catch (Exception e) {
        // TODO: handle exception
        while(true){
          System.out.println("BASE_URL");
        }
      }
    }

    // 11 | click | id=v4_bug_status |  | 
    driver.findElement(By.id("v4_bug_status")).click();
    // 12 | click | id=resolution |  | 
    driver.findElement(By.id("resolution")).click();
    // 13 | click | id=v2_resolution |  | 
    driver.findElement(By.id("v2_resolution")).click();
    // 14 | click | id=comment |  | 
    driver.findElement(By.id("comment")).click();
    // 15 | click | id=comment |  | 
    driver.findElement(By.id("comment")).click();
    // 16 | type | id=comment | My new bug with status | 
    driver.findElement(By.id("comment")).sendKeys("My new bug with status");
    // 17 | click | id=commit |  | 
    driver.findElement(By.id("commit")).click();
    // 18 | click | css=dl:nth-child(1) .bz_bug_link |  | 
    driver.findElement(By.cssSelector("dl:nth-child(1) .bz_bug_link")).click();
    // 19 | verifyText | id=static_bug_status | RESOLVED FIXED (edit) | 
    assertEquals(driver.findElement(By.id("static_bug_status")).getText(), "RESOLVED FIXED (edit)");
    // 20 | verifyText | id=comment_text_1 | My new bug with status | 
    assertEquals(driver.findElement(By.id("comment_text_1")).getText(), "My new bug with status");
  }
  	
	private static boolean isElementPresent(By by) {
		return driver.findElements(by).size() > 0;
	}	
}
