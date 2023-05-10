package at.tuwien.swtesting.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPage {

	private static String baseUrl;
	
	@FindBy(linkText = "Home")
	private WebElement homeLink;
	

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	protected boolean isMatchingPage() {
		String expectedPageTitleRegex = "Bugzilla Main Page";
		return driver.getTitle().matches(expectedPageTitleRegex);
	}
	
	public static HomePage navigateTo(WebDriver driver, String baseUrl) {
		HomePage.baseUrl = baseUrl;
		driver.get(baseUrl);
		return PageFactory.initElements(driver, HomePage.class);
	}
	
	public LoginPage gotoLoginPage() {
		return LoginPage.navigateTo(driver, baseUrl);
	}
	
	public CreateBugPage gotoCreateBugPage() {
		return CreateBugPage.navigateTo(driver, baseUrl);
	}

	public HomePage logout() {
		if (isLoggedin()) {
			WebElement logoutLink = driver.findElement(By.linkText("Log out"));
			logoutLink.click();
		}
		return this;
	}

	public boolean isLoggedin() {
		return isElementPresent(By.linkText("Log out"));
	}
	
		
	public HomePage gotoHomePage() {
		homeLink.click();
		return PageFactory.initElements(driver, HomePage.class);
	}

}


