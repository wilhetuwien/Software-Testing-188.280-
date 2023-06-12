package at.tuwien.swtesting;

import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;

import static org.junit.Assert.*;


public class StatusModelWithAdapter implements FsmModel {

	enum BugStatus {
		NEW;
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
	
	//TODO: implement more actions
}
