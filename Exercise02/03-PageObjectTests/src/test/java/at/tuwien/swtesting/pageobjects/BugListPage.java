package at.tuwien.swtesting.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
public class BugListPage extends AbstractPage{
    
	@FindBy(css = ".bz_result_count")
	private WebElement resultCount;

    @FindBy(id = "mass_change")
    private WebElement masschange;

    
    

	public BugListPage(WebDriver driver) {
		super(driver);
	}

    protected boolean isMatchingPage() {
        String expectedPageTitleRegex = "Bug List";
        return driver.getTitle().matches(expectedPageTitleRegex);
    }

    public String getResultCount(){
        return resultCount.getText();
    }

    public BugListMassChangePage gotoMassChange(){
        masschange.click();
        return PageFactory.initElements(driver, BugListMassChangePage.class);
    }
}
