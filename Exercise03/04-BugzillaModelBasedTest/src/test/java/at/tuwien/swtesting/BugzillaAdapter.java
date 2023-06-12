package at.tuwien.swtesting;

/** 
 * Adapter for Bugzilla. It provides methods to access Bugzilla from the model.
 * The adapter is configured in the setup of the test and passed to the 
 * constructor of the model.
 */
public class BugzillaAdapter {

	/** 
	 * Changes the status of the bug entry on the Bugzilla Web page
	 * to NEW and checks that the status has been changed afterwards.
	 * @return true if change was successful.
	 */
	public boolean performChangeToNew() {
		// TODO: implement method
		return true;
	}

	//TODO: implement further adapter methods

}
