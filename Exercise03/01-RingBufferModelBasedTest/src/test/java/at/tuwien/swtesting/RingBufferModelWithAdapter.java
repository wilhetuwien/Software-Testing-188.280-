package at.tuwien.swtesting;

import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import at.tuwien.swtesting.RingBuffer;


public class RingBufferModelWithAdapter implements FsmModel {
	private static final int CAPACITY = 3;
	private int size = 0;
    private RingBuffer ringBuffer = new RingBuffer<>(CAPACITY);

    private int[] items = {-1, -1, -1};
    
    

	public Object getState() {
		if (size == CAPACITY) return "FULL";
		if (size > 0) return "FILLED";
		return "EMPTY";
	}

	public void reset(boolean testing) {
        ringBuffer = new RingBuffer<>(CAPACITY);
		size = 0;

        items[0] = -1;
        items[1] = -1;
        items[2] = -1;
	}

	public boolean peekGuard(){
		return size > 0;
	}
	@Action
	public void peek() {
        Object respons = ringBuffer.peek();
        assertEquals(items[0], respons);
		return;
	}

	@Action
	public void enqueue() {	
        ringBuffer.enqueue(size);
		size += 1;
		if (size > CAPACITY) {
            size = CAPACITY;
            items[0] = items[1];
            items[1] = items[2];
            items[2] = size;
        } else if (items[0] == -1) {
            items[0] = size;
        } else if (items[1] == -1) {
            items[1] = size;
        } else if (items[2] == -1) {
            items[2] = size;
        }
	}

	public boolean dequeueGuard(){
		return size > 0;
	}
	@Action
	public void dequeue() {	
        Object respons = ringBuffer.peek();
        assertEquals(items[0], respons);
		size -= 1;
        items[0] = items[1];
        items[1] = items[2];
        items[2] = -1;
	}

	public boolean dequeueFromEmptyBufferGuard(){
		return size == 0;
	}
	@Action
	public void dequeueFromEmptyBuffer() {	
		//Throw error
        Throwable exception = assertThrows(RuntimeException.class, () -> ringBuffer.peek());
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
        assertEquals(getState() == "EMPTY", ringBuffer.isEmpty());
	}
    
	@Action
	public void isFull() {	
        assertEquals(getState() == "FULL", ringBuffer.isFull());
	}
	
	//TODO: implement more actions
}
