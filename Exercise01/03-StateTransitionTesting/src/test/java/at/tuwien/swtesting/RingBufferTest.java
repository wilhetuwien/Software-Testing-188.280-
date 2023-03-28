/*
 * William Hedlund, 12233006, excercise 0
 */
package at.tuwien.swtesting;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RingBufferTest {
	/* For creation of a standardized RingBuffer */
	private int common_buffer_size = 3;
	private RingBuffer<Integer> regularRingBuffer(){
		RingBuffer<Integer> buffer = new RingBuffer<>(common_buffer_size);
		return buffer;
	}
	
	private int STATEEMPTY = 0;
	private int STATEFILLED = 1;
	private int STATEFULL = 2;

	private int getState(RingBuffer buffer){
		if (buffer.isEmpty()) {
			return STATEEMPTY; //empty
		}
		if (buffer.isFull()) {
			return STATEFULL; //full
		}
		return STATEFILLED; //filled
	}

	@Test
	public void seq1(){
		// init(3) -empty- enqueue() -filled- dequeue() -empty-
		RingBuffer<Integer> buffer = regularRingBuffer();
		assertEquals(STATEEMPTY, getState(buffer));

		buffer.enqueue(0);
		assertEquals(STATEFILLED, getState(buffer));

		assertEquals(0, buffer.dequeue());
		assertEquals(STATEEMPTY, getState(buffer));
	}
	
	@Test
	public void seq2(){
		// init(3) -empty- enqueue() -filled- enqueue() -filled- dequeue() -filled-
		RingBuffer<Integer> buffer = regularRingBuffer();
		assertEquals(STATEEMPTY, getState(buffer));

		buffer.enqueue(0);
		assertEquals(STATEFILLED, getState(buffer));

		buffer.enqueue(1);
		assertEquals(STATEFILLED, getState(buffer));

		assertEquals(0, buffer.dequeue());
		assertEquals(STATEFILLED, getState(buffer));
	}
	
	@Test
	public void seq3(){
		// init(3) -empty- enqueue() -filled- enqueue() -filled- enqueue() -full- dequeue() -filled-
		RingBuffer<Integer> buffer = regularRingBuffer();
		assertEquals(STATEEMPTY, getState(buffer));

		buffer.enqueue(0);
		assertEquals(STATEFILLED, getState(buffer));

		buffer.enqueue(1);
		assertEquals(STATEFILLED, getState(buffer));

		buffer.enqueue(2);
		assertEquals(STATEFULL, getState(buffer));

		assertEquals(0, buffer.dequeue());
		assertEquals(STATEFILLED, getState(buffer));
	}
		
	@Test
	public void seq4(){
		// init(3) -empty- enqueue() -filled- enqueue() -filled- enqueue() -full- enqueue() -full-
		RingBuffer<Integer> buffer = regularRingBuffer();
		assertEquals(STATEEMPTY, getState(buffer));

		buffer.enqueue(0);
		assertEquals(STATEFILLED, getState(buffer));

		buffer.enqueue(1);
		assertEquals(STATEFILLED, getState(buffer));

		buffer.enqueue(2);
		assertEquals(STATEFULL, getState(buffer));

		buffer.enqueue(3);
		assertEquals(STATEFULL, getState(buffer));
	}
			
	@Test
	public void seq5(){
		// init(3) -empty- dequeue() -error-
		RingBuffer<Integer> buffer = regularRingBuffer();
		assertEquals(STATEEMPTY, getState(buffer));
		
		Exception exception = assertThrows(RuntimeException.class, () -> buffer.dequeue());
		assertEquals("Empty ring buffer.", exception.getMessage());
	}

}
