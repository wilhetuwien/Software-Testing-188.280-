/*
 * William Hedlund
 * 12233006
 * exercise 2 assignment 3
 */
package at.tuwien.swtesting.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ProcessBugPage extends AbstractPage {
    
	@FindBy(id = "commit")
	private WebElement saveChanges;
    
	@FindBy(css = "#bugzilla-body > dl > dt > a")
	private WebElement firstBugInList;

	public ProcessBugPage(WebDriver driver) {
		super(driver);
	}

    protected boolean isMatchingPage() {
        String expectedPageTitleRegex = ".+ processed";
        return driver.getTitle().matches(expectedPageTitleRegex);
    }

	public ShowBugPage gotoFirstBugInList(){
		firstBugInList.click();
        return PageFactory.initElements(driver, ShowBugPage.class);
	}
}
