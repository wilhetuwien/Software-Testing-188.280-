package at.tuwien.swtesting.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BugListMassChangePage extends AbstractPage{
    /**
     * This is it's own page object because of the big difference in layout and functionallity compared to BugListPage.
     * The URL seems to be the same though which could motivate making it the same page object.
     */
    
	@FindBy(xpath = "//*[@id='check_all']")
	private WebElement checkAll;
    
	@FindBy(id = "bug_status")
	private WebElement bugStatus;
    
	@FindBy(id = "resolution")
	private WebElement resolution;
    
	@FindBy(id = "commit")
	private WebElement commit;


	public BugListMassChangePage(WebDriver driver) {
		super(driver);
	}

    protected boolean isMatchingPage() {
        String expectedPageTitleRegex = "Bug List";
        return driver.getTitle().matches(expectedPageTitleRegex);
    }

    public void setCheckAll(){
        checkAll.click();
    }
    public WebElement getCheckAll(){
        return checkAll;
    }

    public void setBugStatus(String bugStatusText){
        Select drpDown = new Select(bugStatus);
        drpDown.selectByVisibleText(bugStatusText);
    }

    public void setResolution(String resolutionText){
        resolution.findElement(By.xpath("//option[. = '" + resolutionText + "']")).click();
    }

    public ProcessBugPage submit(){
        commit.click();
        return PageFactory.initElements(driver, ProcessBugPage.class);
    }

}
