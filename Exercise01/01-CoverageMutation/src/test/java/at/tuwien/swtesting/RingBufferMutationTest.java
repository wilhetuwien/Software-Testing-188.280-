/*
 * William Hedlund, 12233006, excercise 1
 * 
 */
package at.tuwien.swtesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RingBufferMutationTest {
	/* For creation of a standardized RingBuffer */
	private RingBuffer<Integer> sut;
	private int common_buffer_size = 2;

	@BeforeEach
	void init() {
		sut = new RingBuffer<>(common_buffer_size);
	}

	@Test
	public void size_is_updated_after_enqueue(){
		sut.enqueue(Integer.valueOf(0));
		assertEquals(1, sut.size());
	}

}
