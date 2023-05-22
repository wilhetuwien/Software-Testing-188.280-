/*
 * William Hedlund
 * 12233006
 * exercise 2 assignment 4
 */
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

	@FindBy(linkText = "Clone This Bug")
	private WebElement clone;

	@FindBy(id = "comment")
	private WebElement comment;

	@FindBy(id = "static_bug_status")
	private WebElement staticBugStatus;

	@FindBy(id = "comment_text_1")
	private WebElement secondComment;

	@FindBy(xpath = "//*[@id=\"comment_text_0\"]/a")
	private WebElement linkToOriginalBug;


	public ShowBugPage(WebDriver driver) {
		super(driver);
	}

    protected boolean isMatchingPage() {
        String expectedPageTitleRegex = "[0-9]+ – .+";
        return driver.getTitle().matches(expectedPageTitleRegex);
    }

    public String getTitle(){
        return driver.getTitle();
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

	public CreateBugPage cloneBug(){
		clone.click();
		return PageFactory.initElements(driver, CreateBugPage.class);
	}

    public void setComment(String commentText){
		comment.clear();
		comment.sendKeys(commentText);
    }

    public String getStaticBugStatus(){
        return staticBugStatus.getText();
    }

    public String getSecondComment(){
        return secondComment.getText();
    }

    public ShowBugPage gotoOriginalBug(){
        linkToOriginalBug.click();
        return PageFactory.initElements(driver, ShowBugPage.class);
    }

    public ProcessBugPage submit(){
        saveChanges.click();
        return PageFactory.initElements(driver, ProcessBugPage.class);
    }
}
