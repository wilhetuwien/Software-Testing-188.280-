/*
 * William Hedlund
 * 12233006
 * ex3 ass4
 */
package at.tuwien.swtesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import at.tuwien.swtesting.pageobjects.CreateBugPage;
import at.tuwien.swtesting.pageobjects.HomePage;
import at.tuwien.swtesting.pageobjects.ProcessBugPage;
import at.tuwien.swtesting.pageobjects.ShowBugPage;

/** 
 * Adapter for Bugzilla. It provides methods to access Bugzilla from the model.
 * The adapter is configured in the setup of the test and passed to the 
 * constructor of the model.
 */
public class BugzillaAdapter {

	private HomePage homePage;
	private CreateBugPage createBugPage;
	private ShowBugPage showBugPage;
	private ProcessBugPage processBugPage;

	public void setHomePage(HomePage homePage){
		this.homePage = homePage;
	}

	public void reset(){
		homePage = homePage.gotoHomePage();
		
		createBugPage = homePage.gotoCreateBugPage();
		createBugPage.setSummary("BugzillaAdapter");
		showBugPage = createBugPage.submit();
	}

	/** 
	 * Changes the status of the bug entry on the Bugzilla Web page
	 * to NEW and checks that the status has been changed afterwards.
	 * @return true if change was successful.
	 */
	public boolean performChangeToNew() {
		showBugPage.setBugStatus("CONFIRMED");
		processBugPage = showBugPage.submit();
		showBugPage = processBugPage.gotoFirstBugInList();

		return showBugPage.getStaticBugStatus().contains("CONFIRMED");
	}

	/** 
	 * Changes the status of the bug entry on the Bugzilla Web page
	 * to UNCONFIRMED and checks that the status has been changed afterwards.
	 * @return true if change was successful.
	 */
	public boolean performChangeToUnconfirmed() {
		showBugPage.setBugStatus("UNCONFIRMED");
		processBugPage = showBugPage.submit();
		showBugPage = processBugPage.gotoFirstBugInList();

		return showBugPage.getStaticBugStatus().contains("UNCONFIRMED");
	}

	/** 
	 * Changes the status of the bug entry on the Bugzilla Web page
	 * to IN_PROGRESS and checks that the status has been changed afterwards.
	 * @return true if change was successful.
	 */
	public boolean performChangeToInProgress() {
		showBugPage.setBugStatus("IN_PROGRESS");
		processBugPage = showBugPage.submit();
		showBugPage = processBugPage.gotoFirstBugInList();

		return showBugPage.getStaticBugStatus().contains("IN_PROGRESS");
	}

	/** 
	 * Changes the status of the bug entry on the Bugzilla Web page
	 * to RESOLVED and checks that the status has been changed afterwards.
	 * @return true if change was successful.
	 */
	public boolean performChangeToResolved() {
		showBugPage.setBugStatus("RESOLVED");
		processBugPage = showBugPage.submit();
		showBugPage = processBugPage.gotoFirstBugInList();

		return showBugPage.getStaticBugStatus().contains("RESOLVED");
	}

	/** 
	 * Changes the status of the bug entry on the Bugzilla Web page
	 * to Verified and checks that the status has been changed afterwards.
	 * @return true if change was successful.
	 */
	public boolean performChangeToVerified() {
		showBugPage.setBugStatus("VERIFIED");
		processBugPage = showBugPage.submit();
		showBugPage = processBugPage.gotoFirstBugInList();

		return showBugPage.getStaticBugStatus().contains("VERIFIED");
	}

	//TODO: implement further adapter methods

}
