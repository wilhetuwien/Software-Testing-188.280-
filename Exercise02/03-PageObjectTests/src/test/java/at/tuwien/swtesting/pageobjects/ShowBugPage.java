package at.tuwien.swtesting.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ShowBugPage extends AbstractPage{
	
	@FindBy(id = "comment_text_0")
	private WebElement firstComment;

	@FindBy(id = "short_desc_nonedit_display")
	private WebElement summaryText;

	@FindBy(id = "bug_status")
	private WebElement status;

	@FindBy(id = "resolution")
	private WebElement resolution;

	@FindBy(id = "commit")
	private WebElement saveChanges;

    @FindBy(xpath = "//*[@id=\"bugzilla-body\"]/dl/dt[starts-with(text(),\'Bug \') and contains(text()[2], \" has been successfully created\") and string-length(translate(.//a,\"0123456789\", \"0123456789\")) > 0]")
    private List<WebElement> bugCreatedElements;


	public ShowBugPage(WebDriver driver) {
		super(driver);
	}

    protected boolean isMatchingPage() {
        String expectedPageTitleRegex = "[0-9]+ – .+";
        return driver.getTitle().matches(expectedPageTitleRegex);
    }

    public int getNumberOfBugCreationElements(){
        return bugCreatedElements.size();
    }

    public String getFirstComment(){
        return firstComment.getText();
    }

    public String getSummary(){
        return summaryText.getText();
    }

    public void setBugStatus(String statusValue){
        status.findElement(By.xpath("//option[. = '" + statusValue + "']")).click();
    }

    public void setResolution(String resolutionValue){
        resolution.findElement(By.xpath("//option[. = '" + resolutionValue + "']")).click();
    }

    public ProcessBugPage submit(){
        saveChanges.click();
        return PageFactory.initElements(driver, ProcessBugPage.class);
    }

    public static ShowBugPage navigateTo(WebDriver driver, String baseUrl) {
		driver.get(baseUrl + "enter_bug.cgi");
		return PageFactory.initElements(driver, ShowBugPage.class);
	}

    public HomePage saveChanges(String statusValue, String resolutionValue) {
		// WebElement loginName = driver.findElement(By.id("Bugzilla_login"));
        status.findElement(By.xpath("//option[. = '" + statusValue + "']")).click();

		// WebElement loginPassword = driver.findElement(By.id("Bugzilla_password"));
        resolution.findElement(By.xpath("//option[. = '" + resolutionValue + "']")).click();

		// WebElement loginButton = driver.findElement(By.id("log_in"));
		saveChanges.submit();
		
        // TODO go to http://192.168.56.101/show_bug.cgi?id=197
		return PageFactory.initElements(driver, HomePage.class);
	}

}
