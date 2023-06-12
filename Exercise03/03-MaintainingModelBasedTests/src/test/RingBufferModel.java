/*
 * William Hedlund
 * 12233006
 * ex3 ass3
 */
package at.tuwien.swtesting;

import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;


public class RingBufferModel implements FsmModel {
	private static final int CAPACITY = 3;
	private int size = 0;

	public Object getState() {
		if (size == CAPACITY) return "FULL";
		if (size > 0) return "FILLED";
		return "EMPTY";
	}

	public void reset(boolean testing) {
		size = 0;
	}

	public boolean peekGuard(){
		return size > 0;
	}
	@Action
	public void peek() {	
		return;
	}

	@Action
	public void enqueue() {	
		size += 1;
		if (size > CAPACITY) size = CAPACITY;
	}

	public boolean dequeueGuard(){
		return size > 0;
	}
	@Action
	public void dequeue() {	
		size -= 1;
	}

	public boolean dequeueFromEmptyBufferGuard(){
		return size == 0;
	}
	@Action
	public void dequeueFromEmptyBuffer() {	
		//Throw error
		return;
	}

	public boolean peekEmptyBufferGuard(){
		return size == 0;
	}
	@Action
	public void peekEmptyBuffer() {	
		//Throw error
		return;
	}

	public boolean setCapacityGuard(int newCapacity){
		return size <= newCapacity;
	}
	@Action
	public void setCapacity() {	
		//Throw error
		return;
	}
}
