/*
 * William Hedlund, 12233006, excercise 0
 */
package at.tuwien.swtesting;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RingBufferConstrTest {
    @Test
	public void validBoundry(){
        new RingBuffer<>(0);
        new RingBuffer<>(1000);
	}

    @Test
	public void zeroConstruct(){
        RingBuffer zero = new RingBuffer<>(0);
		assertEquals(true, zero.isFull());
		assertEquals(true, zero.isEmpty());
	}

    @Test
	public void oneConstruct(){
        RingBuffer one = new RingBuffer<>(1);
		assertEquals(false, one.isFull());
		assertEquals(true, one.isEmpty());
        one.enqueue(Integer.valueOf(0));
		assertEquals(true, one.isFull());
		assertEquals(false, one.isEmpty());

	}

    @Test
	public void remaingingValidConstruct(){
        RingBuffer two = new RingBuffer<>(2);
		assertEquals(false, two.isFull());
		assertEquals(true, two.isEmpty());
        two.enqueue(Integer.valueOf(0));
		assertEquals(false, two.isFull());
		assertEquals(false, two.isEmpty());
        two.enqueue(Integer.valueOf(0));
		assertEquals(true, two.isFull());
		assertEquals(false, two.isEmpty());

	}
}
