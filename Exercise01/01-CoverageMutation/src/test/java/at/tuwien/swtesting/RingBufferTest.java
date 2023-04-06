/*
 * William Hedlund, 12233006, excercise 1
 * Have made changes from feedback:
 * updated method names
 * unrolled loops
 * added @BeforeEach
 * assertTrue, assertFalse
 * 
 * Split up into more specific tests
 * 
 * 
 * Missed instructions 26/179 (85%)
 * Missed branches 3/14 (78%)
 * 
 */
package at.tuwien.swtesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RingBufferTest {
	/* For creation of a standardized RingBuffer */
	private RingBuffer<Integer> sut;
	private int common_buffer_size = 2;

	@BeforeEach
	void init() {
		sut = new RingBuffer<>(common_buffer_size);
	}

	@Test
	public void peek_empty_buffer_assert_throws_error() {
		Exception exception = assertThrows(RuntimeException.class, () -> sut.peek());
		
		assertEquals("Empty ring buffer.", exception.getMessage());
	}

	@Test
	public void dequeue_removes_items_in_correct_order() {
		sut.enqueue(Integer.valueOf(0));
		sut.enqueue(Integer.valueOf(1));

		assertEquals(Integer.valueOf(0), sut.dequeue(), "Dequeue return wrong item");
		assertEquals(Integer.valueOf(1), sut.dequeue(), "Dequeue return wrong item");
		assertTrue( sut.isEmpty(), "Items not dequeued correctly");
	}


	@Test
	public void enqueue_overwrites_oldest_value() {
		sut.enqueue(Integer.valueOf(0));
		sut.enqueue(Integer.valueOf(1));
		sut.enqueue(Integer.valueOf(2));

		assertEquals(Integer.valueOf(1), sut.peek());
	}

	@Test
	public void iterator_hasNext_gives_same_result_if_no_other_action_is_taken() {
		sut.enqueue(Integer.valueOf(0));
		Iterator<Integer> iterator = sut.iterator();

		assertTrue( iterator.hasNext(),".hasNext() initial call is supposed to be true");
		assertTrue( iterator.hasNext(),".hasNext() is not expected to change result between calls");
	}

	@Test
	public void iterate_through_buffer_ring_does_not_change_buffer() {
		sut.enqueue(Integer.valueOf(0));
		sut.enqueue(Integer.valueOf(1));
		Iterator<Integer> iterator = sut.iterator();

		assertEquals(Integer.valueOf(0), iterator.next(), "Unexpected value from iterator.");
		assertEquals(Integer.valueOf(1), iterator.next(), "Unexpected value from iterator.");
		Iterator<Integer> iterator1 = sut.iterator();
		assertEquals(Integer.valueOf(0), iterator1.next(), "Unexpected value from iterator.");
		assertEquals(Integer.valueOf(1), iterator1.next(), "Unexpected value from iterator.");
	}

	@Test
	public void iterate_throws_error_when_no_next_item_when_buffer_is_empty() {
		Iterator<Integer> iterator = sut.iterator();

		assertThrows(NoSuchElementException.class, () -> iterator.next());
	}
}
