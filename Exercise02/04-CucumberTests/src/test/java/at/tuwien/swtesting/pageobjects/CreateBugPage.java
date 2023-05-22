/*
 * William Hedlund
 * 12233006
 * exercise 2 assignment 4
 */
package at.tuwien.swtesting.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object encapsulates the Home Page
 */
public class CreateBugPage extends AbstractPage{
	
	@FindBy(id = "short_desc")
	private WebElement summary;

	@FindBy(id = "comment")
	private WebElement comment;

	@FindBy(id = "commit")
	private WebElement commit;

	public CreateBugPage(WebDriver driver) {
		super(driver);
	}


    protected boolean isMatchingPage() {
        String expectedPageTitleRegex = "Enter Bug: .*";
        return driver.getTitle().matches(expectedPageTitleRegex);
    }

    public static CreateBugPage navigateTo(WebDriver driver, String baseUrl) {
		driver.get(baseUrl + "enter_bug.cgi");
		return PageFactory.initElements(driver, CreateBugPage.class);
	}

    public void setDescription(String descriptionText){
        comment.clear();
		comment.sendKeys(descriptionText);
    }

    public void setSummary(String summaryText){
        summary.clear();
		summary.sendKeys(summaryText);
    }

    public ShowBugPage submit(){
        commit.click();
		return PageFactory.initElements(driver, ShowBugPage.class);
    }
}

