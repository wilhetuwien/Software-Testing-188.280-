/*
 * William Hedlund, 12233006, excercise 1,2
 */
package at.tuwien.swtesting;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RingBufferConstrTest {
    @Test
	public void Valid_boundries_initialises(){
        new RingBuffer<>(0);
        new RingBuffer<>(100000); //the greatest int is to big to allocate memory for
	}

    @Test
	public void capacity_zero_assert_is_empty_and_full(){
        RingBuffer sut = new RingBuffer<>(0);

		assertEquals(true, sut.isFull());
		assertEquals(true, sut.isEmpty());
	}

    @Test
	public void capacity_zero_enqueue_assert_throws_error(){
		/*
		 * exception thrown was added by William
		 */
        RingBuffer sut = new RingBuffer<>(0);

		Exception exception = assertThrows(RuntimeException.class, () -> sut.enqueue(Integer.valueOf(0)));
		assertEquals("Tried enqueueing to buffer with size 0.", exception.getMessage());

	}


    @Test
	public void capacity_one_is_empty(){
        RingBuffer sut = new RingBuffer<>(1);
		assertEquals(false, sut.isFull());
		assertEquals(true, sut.isEmpty());
	}

    @Test
	public void capacity_one_is_full(){
        RingBuffer sut = new RingBuffer<>(1);
        sut.enqueue(Integer.valueOf(0));

		assertEquals(true, sut.isFull());
		assertEquals(false, sut.isEmpty());

	}

    @Test
	public void other_capacity_is_empty(){
        RingBuffer sut = new RingBuffer<>(2);
		assertEquals(false, sut.isFull());
		assertEquals(true, sut.isEmpty());
	}

    @Test
	public void other_capacity_is_filled(){
        RingBuffer sut = new RingBuffer<>(2);
        sut.enqueue(Integer.valueOf(0));

		assertEquals(false, sut.isFull());
		assertEquals(false, sut.isEmpty());
	}

    @Test
	public void other_capacity_is_full(){
        RingBuffer sut = new RingBuffer<>(2);
        sut.enqueue(Integer.valueOf(0));
        sut.enqueue(Integer.valueOf(0));

		assertEquals(true, sut.isFull());
		assertEquals(false, sut.isEmpty());
	}
}
