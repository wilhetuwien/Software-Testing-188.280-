/*
 * William Hedlund
 * 12233006
 * ex3 ass1
 */
package at.tuwien.swtesting;

import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class RingBufferModelWithAdapter implements FsmModel {
	private static final int CAPACITY = 3;
	private int size = 0;
    private RingBuffer<Integer> ringBuffer = new RingBuffer<>(CAPACITY);

	private int itemsqueue = 0;
    
    

	public Object getState() {
		if (size == CAPACITY) return "FULL";
		if (size > 0) return "FILLED";
		return "EMPTY";
	}

	public void reset(boolean testing) {
        ringBuffer = new RingBuffer<>(CAPACITY);
		size = 0;
		itemsqueue = 0;
	}

	public boolean peekGuard(){
		return size > 0;
	}
	@Action
	public void peek() {
        Object respons = ringBuffer.peek();
		assertEquals(itemsqueue - size, respons);
		return;
	}

	@Action
	public void enqueue() {	
        ringBuffer.enqueue(itemsqueue);
		size += 1;
		if (size > CAPACITY){
			size = CAPACITY;
		}
		itemsqueue += 1;
	}

	public boolean dequeueGuard(){
		return size > 0;
	}
	@Action
	public void dequeue() {	
        Object respons = ringBuffer.dequeue();
		assertEquals(itemsqueue - size, respons);
		size -= 1;
	}

	public boolean dequeueFromEmptyBufferGuard(){
		return size == 0;
	}
	@Action
	public void dequeueFromEmptyBuffer() {	
		//Throw error
        Throwable exception = assertThrows(RuntimeException.class, () -> ringBuffer.dequeue());
        assertEquals("Empty ring buffer.", exception.getMessage());
        return;
	}

	public boolean peekEmptyBufferGuard(){
		return size == 0;
	}
	@Action
	public void peekEmptyBuffer() {	
		//Throw error
        Throwable exception = assertThrows(RuntimeException.class, () -> ringBuffer.peek());
        assertEquals("Empty ring buffer.", exception.getMessage());
        return;
	}

	@Action
	public void capacity() {	
        assertEquals(CAPACITY, ringBuffer.capacity());
	}
    
	@Action
	public void size() {	
        assertEquals(size, ringBuffer.size());
	}
    
	@Action
	public void isEmpty() {	
        assertEquals(size == 0, ringBuffer.isEmpty());
	}
    
	@Action
	public void isFull() {	
        assertEquals(size == CAPACITY, ringBuffer.isFull());
	}
}
