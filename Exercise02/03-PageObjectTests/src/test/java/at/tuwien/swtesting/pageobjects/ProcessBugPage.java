package at.tuwien.swtesting.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ProcessBugPage extends AbstractPage {
    
	@FindBy(id = "commit")
	private WebElement saveChanges;

	public ProcessBugPage(WebDriver driver) {
		super(driver);
	}

    protected boolean isMatchingPage() {
        String expectedPageTitleRegex = "[0-9]+ processed";
        return driver.getTitle().matches(expectedPageTitleRegex);
    }
}
