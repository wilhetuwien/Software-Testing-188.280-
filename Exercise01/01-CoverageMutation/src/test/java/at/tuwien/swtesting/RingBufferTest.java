/*
 * William Hedlund, 12233006, excercise 1
 * Have made changes from feedback:
 * updated method names
 * unrolled loops
 * added @BeforeEach
 * assertTrue, assertFalse
 */
package at.tuwien.swtesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RingBufferTest {
	/* For creation of a standardized RingBuffer */
	private RingBuffer<Integer> buffer;
	private int common_buffer_size = 2;

	@BeforeEach
	void init() {
		buffer = new RingBuffer<>(common_buffer_size);
	}

	@Test
	public void capacity_is_set(){
		assertEquals(common_buffer_size, buffer.capacity());
	}

	@Test
	public void initial_size_set(){
		assertEquals(0, buffer.size());
	}

	@Test
	public void initialised_empty(){
		assertTrue( buffer.isEmpty());
	}

	@Test
	public void is_not_empty_when_filled(){
		buffer.enqueue(Integer.valueOf(0));
		
		assertFalse( buffer.isEmpty());
	}

	@Test
	public void is_full_when_full(){
		buffer.enqueue(Integer.valueOf(0));
		buffer.enqueue(Integer.valueOf(1));

		assertTrue( buffer.isFull());
	}

	@Test
	public void is_not_full_when_not_full(){
		assertFalse( buffer.isFull());
	}

	@Test
	public void push_to_full_stack_does_not_overwrite_size(){
		buffer.enqueue(Integer.valueOf(0));
		buffer.enqueue(Integer.valueOf(1));
		buffer.enqueue(Integer.valueOf(2));
		
		assertEquals(common_buffer_size, buffer.size());
	}

	@Test
	public void peek_empty_buffer_assert_throws_error() {
		Exception exception = assertThrows(RuntimeException.class, () -> buffer.peek());
		
		assertEquals("Empty ring buffer.", exception.getMessage());
	}

	@Test
	public void dequeue_removes_items_in_correct_order() {
		buffer.enqueue(Integer.valueOf(0));
		buffer.enqueue(Integer.valueOf(1));

		assertEquals(Integer.valueOf(0), buffer.dequeue(), "Dequeue return wrong item");
		assertEquals(Integer.valueOf(1), buffer.dequeue(), "Dequeue return wrong item");
		assertTrue( buffer.isEmpty(), "Items not dequeued correctly");
	}

	@Test
	public void dequeue_empty_buffer_assert_throws_error() {
		Exception exception = assertThrows(RuntimeException.class, () -> buffer.dequeue());
		
		assertEquals("Empty ring buffer.", exception.getMessage());
	}


	@Test
	public void enqueue_overwrites_oldest_value() {
		buffer.enqueue(Integer.valueOf(0));
		buffer.enqueue(Integer.valueOf(1));
		buffer.enqueue(Integer.valueOf(2));

		assertEquals(Integer.valueOf(1), buffer.peek());
	}

	@Test
	public void iterator_hasNext_gives_same_result_if_no_other_action_is_taken() {
		buffer.enqueue(Integer.valueOf(0));
		Iterator<Integer> iterator = buffer.iterator();

		assertTrue( iterator.hasNext(),".hasNext() initial call is supposed to be true");
		assertTrue( iterator.hasNext(),".hasNext() is not expected to change result between calls");
	}

	@Test
	public void iterate_through_bufferring_does_not_change_buffer() {
		buffer.enqueue(Integer.valueOf(0));
		buffer.enqueue(Integer.valueOf(1));
		Iterator<Integer> iterator = buffer.iterator();

		assertEquals(Integer.valueOf(0), iterator.next(), "Unexpected value from iterator.");
		assertEquals(Integer.valueOf(1), iterator.next(), "Unexpected value from iterator.");
		Iterator<Integer> iterator1 = buffer.iterator();
		assertEquals(Integer.valueOf(0), iterator1.next(), "Unexpected value from iterator.");
		assertEquals(Integer.valueOf(1), iterator1.next(), "Unexpected value from iterator.");
	}

	@Test
	public void iterate_throws_error_when_no_next_item_when_buffer_is_empty() {
		Iterator<Integer> iterator = buffer.iterator();

		assertThrows(NoSuchElementException.class, () -> iterator.next());
	}
	
	@Test
	public void iterator_remove_throws_not_implemented(){
		Iterator<Integer> iterator = buffer.iterator();

		assertThrows(UnsupportedOperationException.class, () -> iterator.remove());
	}

}
