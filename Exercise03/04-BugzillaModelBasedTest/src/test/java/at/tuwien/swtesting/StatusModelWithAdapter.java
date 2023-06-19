package at.tuwien.swtesting;

import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;

import static org.junit.Assert.*;


public class StatusModelWithAdapter implements FsmModel {

	enum BugStatus {
		NEW,//CONFIRMED
		IN_PROGRESS,
		RESOLVED,
		VERIFIED,
		UNCONFIRMED;
	}

	BugStatus status;
	
	private BugzillaAdapter adapter;
	
	public StatusModelWithAdapter(BugzillaAdapter adapter) {
		this.adapter = adapter;
		reset(true);
	}
	

	public Object getState() {
		return status;
	}

	public void reset(boolean testing) {
		status = BugStatus.NEW;
	}

	@Action
	public void changeToNew() {	
		assertTrue(adapter.performChangeToNew());
		status = BugStatus.NEW;
	}

	public boolean changeToUnconfirmedGuard(){
		return status == BugStatus.RESOLVED || status == BugStatus.VERIFIED;
		// should also be able to initiallise to UNCONFIRMED
	}
	@Action
	public void changeToUnconfirmed(){
		assertTrue(adapter.performChangeToUnconfirmed());
		status = BugStatus.UNCONFIRMED;
	}

	public boolean changeToInProgressGuard(){
		return status == BugStatus.NEW || status == BugStatus.UNCONFIRMED;
	}
	@Action
	public void changeToInProgress(){
		assertTrue(adapter.performChangeToInProgress());
		status = BugStatus.IN_PROGRESS;
	}

	public boolean changeToResolvedGuard(){
		return status == BugStatus.IN_PROGRESS || status == BugStatus.NEW || status == BugStatus.UNCONFIRMED;
	}
	@Action
	public void changeToResolved(){
		assertTrue(adapter.performChangeToResolved());
		status = BugStatus.RESOLVED;
	}

	public boolean changeToVerifiedGuard(){
		return status == BugStatus.RESOLVED;
	}
	@Action
	public void changeToVerified(){
		assertTrue(adapter.performChangeToVerified());
		status = BugStatus.VERIFIED;
	}
	
	//TODO: implement more actions
}
