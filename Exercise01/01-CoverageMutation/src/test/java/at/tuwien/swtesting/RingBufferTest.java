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
	private int common_buffer_size = 5;
	private RingBuffer<Integer> regularRingBuffer(){
		RingBuffer<Integer> buffer = new RingBuffer<>(common_buffer_size);
		return buffer;
	}

	@Test
	public void initializedCorrect(){
		RingBuffer<Integer> buffer = regularRingBuffer();
		assertEquals(common_buffer_size, buffer.capacity());
		assertEquals(0, buffer.size());
		assertEquals(false, buffer.isFull());
		assertEquals(true, buffer.isEmpty());
	}

	@Test
	public void sizeOverflow(){
		RingBuffer<Integer> buffer = regularRingBuffer();
		int first_value = 0;
		buffer.enqueue(Integer.valueOf(first_value));
		int amount_of_overflow = 1;
		for (int i=1; i < common_buffer_size + amount_of_overflow; i++){
			buffer.enqueue(Integer.valueOf(i));
		}
		assertEquals(common_buffer_size, buffer.size());
		assertEquals(true, buffer.isFull());
	}

	@Test
	public void peekEmpty() {
		RingBuffer<Integer> buffer = regularRingBuffer();
		Exception exception = assertThrows(RuntimeException.class, () -> buffer.peek());
		assertEquals("Empty ring buffer.", exception.getMessage());
	}

	@Test
	public void dequeue() {
		RingBuffer<Integer> buffer = regularRingBuffer();
		buffer.enqueue(Integer.valueOf(0));
	
		assertEquals(Integer.valueOf(0), buffer.dequeue());
		assertEquals(true, buffer.isEmpty(), "Item not dequeued correctly");

		int amount_of_overflow = 2;
		for (int i=0; i < common_buffer_size + amount_of_overflow; i++){
			buffer.enqueue(Integer.valueOf(i));
		}
		for (int i=amount_of_overflow; i < common_buffer_size + amount_of_overflow; i++){
			assertEquals(Integer.valueOf(i), buffer.dequeue(), "Dequeue return wrong item");
		}
		
		Exception exception = assertThrows(RuntimeException.class, () -> buffer.dequeue());
		assertEquals("Empty ring buffer.", exception.getMessage());
	}

	@Test
	public void overfillRingBuffer() {
		RingBuffer<Integer> buffer = regularRingBuffer();
		int first_value = 0;
		buffer.enqueue(Integer.valueOf(first_value));
		int amount_of_overflow = 1;
		for (int i=1; i < common_buffer_size + amount_of_overflow; i++){
			assertEquals(Integer.valueOf(0), buffer.peek(), "First value of buffer overwritten when not expected.");
			buffer.enqueue(Integer.valueOf(i));
		}
		assertEquals(Integer.valueOf(amount_of_overflow), buffer.peek());
	}

	@Test
	public void iteratorHasNext() {
		RingBuffer<Integer> buffer = regularRingBuffer();
		buffer.enqueue(Integer.valueOf(0));
		Iterator<Integer> iterator = buffer.iterator();
		int number_of_calls_in_a_row = 5;
		for (int i = 0; i < number_of_calls_in_a_row; i++) {
			assertEquals(true, iterator.hasNext(),".hasNext() is not expected to change result between calls");
		}
	}

	@Test
	public void iterateBuffer() {
		RingBuffer<Integer> buffer = regularRingBuffer();
		for (int i=0; i<common_buffer_size; i++){
			buffer.enqueue(Integer.valueOf(i));
		}
		Iterator<Integer> iterator = buffer.iterator();
		for (int i=0; i<common_buffer_size; i++){
			assertEquals(Integer.valueOf(i), iterator.next(), "Unexpected value from iterator.");
		}
		Iterator<Integer> iterator2 = buffer.iterator();
		for (int i=0; i<common_buffer_size; i++){
			assertEquals(Integer.valueOf(i), iterator2.next(), "The first and second iterator should behave the same.");
		}
		assertThrows(NoSuchElementException.class, () -> iterator.next());
	}
	
	@Test
	public void remove(){
		RingBuffer<Integer> buffer = regularRingBuffer();
		Iterator<Integer> iterator = buffer.iterator();
		Exception exception = assertThrows(UnsupportedOperationException.class, () -> iterator.remove());
	}

}
