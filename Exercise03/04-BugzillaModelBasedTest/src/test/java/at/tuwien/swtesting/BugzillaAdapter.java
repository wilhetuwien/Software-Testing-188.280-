package at.tuwien.swtesting;

import at.tuwien.swtesting.pageobjects.ProcessBugPage;
import at.tuwien.swtesting.pageobjects.ShowBugPage;

/** 
 * Adapter for Bugzilla. It provides methods to access Bugzilla from the model.
 * The adapter is configured in the setup of the test and passed to the 
 * constructor of the model.
 */
public class BugzillaAdapter {

	private ShowBugPage showBugPage;
	private ProcessBugPage processBugPage;

	public void setShowBugPage(ShowBugPage showBugPage){
		this.showBugPage = showBugPage;
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

		return showBugPage.getStaticBugStatus() == "CONFIRMED (edit)";
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

		return showBugPage.getStaticBugStatus() == "UNCONFIRMED (edit)";
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

		return showBugPage.getStaticBugStatus() == "IN_PROGRESS (edit)";
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

		return showBugPage.getStaticBugStatus() == "RESOLVED FIXED (edit)";
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

		return showBugPage.getStaticBugStatus() == "VERIFIED FIXED (edit)";
	}

	//TODO: implement further adapter methods

}
