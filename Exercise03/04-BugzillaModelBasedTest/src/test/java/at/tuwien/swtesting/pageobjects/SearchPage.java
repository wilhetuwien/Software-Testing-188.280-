/*
 * William Hedlund
 * 12233006
 * exercise 2 assignment 3
 */
package at.tuwien.swtesting.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SearchPage extends AbstractPage{

	@FindBy(id = "bug_status")
	private WebElement bugStatus;

	@FindBy(id = "product")
	private WebElement product;

	@FindBy(id = "content")
	private WebElement search;

	@FindBy(id = "search")
	private WebElement commit;
    

	public SearchPage(WebDriver driver) {
		super(driver);
	}

    protected boolean isMatchingPage() {
        String expectedPageTitleRegex = "Simple Search";
        return driver.getTitle().matches(expectedPageTitleRegex);
    }

	public static SearchPage navigateTo(WebDriver driver, String baseUrl) {
		driver.get(baseUrl + "query.cgi");
		return PageFactory.initElements(driver, SearchPage.class);
	}

    public void setBugStatus(String status){
        Select drpDown = new Select(bugStatus);
        drpDown.selectByVisibleText(status);
    }

    public void setProduct(String productString){
		Select drpDown = new Select(product);
		drpDown.selectByVisibleText("TestProduct");
	}

	public void setSearch(String searchTerm){
        search.clear();
		search.sendKeys(searchTerm);
	}

	public BugListPage submit(){
		commit.click();
		return PageFactory.initElements(driver, BugListPage.class);
	}

}
