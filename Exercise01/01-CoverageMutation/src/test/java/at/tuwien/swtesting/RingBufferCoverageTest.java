/*
 * William Hedlund, 12233006, excercise 1,1
 */
package at.tuwien.swtesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

public class RingBufferCoverageTest {
	/* For creation of a standardized RingBuffer */
	private RingBuffer<Integer> sut;
	private int common_buffer_size = 2;

	@BeforeEach
	void init() {
		sut = new RingBuffer<>(common_buffer_size);
	}

	@Test
	public void capacity_is_set(){
		assertEquals(common_buffer_size, sut.capacity());
	}

	@Test
	public void initial_size_set(){
		assertEquals(0, sut.size());
	}

	@Test
	public void is_full_when_full(){
		sut.enqueue(Integer.valueOf(0));
		sut.enqueue(Integer.valueOf(1));

		assertTrue( sut.isFull());
	}

	@Test
	public void is_not_full_when_not_full(){
		assertFalse( sut.isFull());
	}

	@Test
	public void dequeue_empty_buffer_assert_throws_error() {
		Exception exception = assertThrows(RuntimeException.class, () -> sut.dequeue());
		
		assertEquals("Empty ring buffer.", exception.getMessage());
	}

	@Test
	public void iterator_remove_throws_not_implemented(){
		Iterator<Integer> iterator = sut.iterator();

		assertThrows(UnsupportedOperationException.class, () -> iterator.remove());
	}

}
