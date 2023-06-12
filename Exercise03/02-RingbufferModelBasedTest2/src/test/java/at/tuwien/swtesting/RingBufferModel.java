package at.tuwien.swtesting;

import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;


public class RingBufferModel implements FsmModel {

	public Object getState() {
		//TODO: implement computing states
		return "EMPTY";
	}

	public void reset(boolean testing) {
		//TODO: implement reset
	}

	@Action
	public void peek() {	
		//TODO: implement action
	}
	
	//TODO: implement more actions
}
