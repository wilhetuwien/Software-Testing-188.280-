/*
 * William Hedlund, 12233006, excercise 0
 */
package at.tuwien.swtesting;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RingBufferConstrTest {
    @Test
	public void Valid_boundries_initialises(){
        new RingBuffer<>(0);
        new RingBuffer<>(1000); //the greatest int is to big to allocate memory for
	}

    @Test
	public void capacity_zero_assert_is_empty_and_full(){
        RingBuffer zero = new RingBuffer<>(0);

		assertEquals(true, zero.isFull());
		assertEquals(true, zero.isEmpty());
	}

    @Test
	public void capacity_one_is_empty(){
        RingBuffer one = new RingBuffer<>(1);
		assertEquals(false, one.isFull());
		assertEquals(true, one.isEmpty());
	}

    @Test
	public void capacity_one_is_full(){
        RingBuffer one = new RingBuffer<>(1);
        one.enqueue(Integer.valueOf(0));

		assertEquals(true, one.isFull());
		assertEquals(false, one.isEmpty());

	}

    @Test
	public void other_capacity_is_empty(){
        RingBuffer two = new RingBuffer<>(2);
		assertEquals(false, two.isFull());
		assertEquals(true, two.isEmpty());
	}

    @Test
	public void other_capacity_is_filled(){
        RingBuffer two = new RingBuffer<>(2);
        two.enqueue(Integer.valueOf(0));

		assertEquals(false, two.isFull());
		assertEquals(false, two.isEmpty());
	}

    @Test
	public void other_capacity_is_full(){
        RingBuffer two = new RingBuffer<>(2);
        two.enqueue(Integer.valueOf(0));
        two.enqueue(Integer.valueOf(0));

		assertEquals(true, two.isFull());
		assertEquals(false, two.isEmpty());
	}
}
