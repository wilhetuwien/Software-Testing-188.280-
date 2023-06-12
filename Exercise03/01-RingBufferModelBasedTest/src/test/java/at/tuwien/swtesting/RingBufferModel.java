package at.tuwien.swtesting;

import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;


public class RingBufferModel implements FsmModel {
	private static final int CAPACITY = 2;
	private int size = 0;

	public Object getState() {
		if (size == CAPACITY) return "FULL";
		if (size > 0) return "FILLED";
		return "EMPTY";
	}

	public void reset(boolean testing) {
		size = 0;
	}

	@Action
	public void peek() {	
		//TODO: implement action
	}

	@Action
	public void enqueue() {	
		//TODO: implement action
	}

	@Action
	public void dequeue() {	
		//TODO: implement action
	}
	
	//TODO: implement more actions
}
