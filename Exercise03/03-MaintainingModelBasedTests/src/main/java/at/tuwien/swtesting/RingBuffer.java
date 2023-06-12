package at.tuwien.swtesting;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The RingBuffer class represents a first-in-first-out (FIFO) circular queue of elements. 
 * The buffer is bounded. It has a maximum capacity of elements it can hold. If more elements 
 * are added, the last element will overwrite the first one.
 * 
 * Originally derived from http://www.cs.princeton.edu/introcs/43stack/RingBuffer.java.html
 */
public class RingBuffer<Item> implements Iterable<Item> {
	
	private Item[] a; 		// queue elements
	private int N = 0; 		// number of elements on queue
	private int first = 0; 	// index of first element of queue
	private int last = 0; 	// index of next available slot
	
	/**
	 * Creates a new empty ring buffer. 
	 * @param capacity number of elements the buffer is able to hold.
	 */
	@SuppressWarnings("unchecked")
	public RingBuffer(int capacity) {
		// cast needed since no generic array creation in Java
		a = (Item[]) new Object[capacity];
	}
	
	/** 
	 * Returns the number of elements the buffer can hold.
	 */
	public int capacity() {
		return a.length;
	}
	
	/** 
	 * Returns the number of elements in the buffer.
	 */
	public int size() {
		return N;
	}

	/**
	 * Returns true if the buffer contains no elements.
	 */
	public boolean isEmpty() {
		return N == 0;
	}
	
	/**
	 * Returns true if the buffer has reached its capacity, which is the maximum 
	 * number of elements it can hold, before overwriting elements.
	 */	
	public boolean isFull() {
		return N == a.length;
	}

	/**
	 * Appends the specified element to the end of the buffer. If the buffer has already 
	 * reached its capacity, appending overwrites the first element in the buffer.
	 * @param item to be appended to the buffer.
	 */
	public void enqueue(Item item) {
		a[last] = item;
		last = (last + 1) % a.length; // wrap-around
		if (N < a.length) {
			N++;
		} else {
			first = (first + 1) % a.length;
		}
	}

	/**
	 * Removes the first element from the buffer. 
	 * @throws RuntimeException if the buffer is empty.
	 */	
	public Item dequeue() throws RuntimeException {
		if (isEmpty()) {
			throw new RuntimeException("Empty ring buffer.");
		}
		Item item = a[first];
		a[first] = null;
		N--;
		first = (first + 1) % a.length; // wrap-around
		return item;
	}
	
	/**
	 * Returns the first element from the buffer without removing it. 
	 * @throws RuntimeException if the buffer is empty.
	 */	
	public Item peek() {
		if (isEmpty()) {
			throw new RuntimeException("Empty ring buffer.");
		}
		return a[first];
	}

	/**
	 * Updates the size of the buffer while preserving FIFO ordering of elements.
	 * @param newCapacity of the updated buffer.
	 * @throws RuntimeException if the new capacity is smaller than the current size.
	 */
	public void setCapacity(int newCapacity) {
		if (newCapacity < size()) {
			throw new RuntimeException("New capacity can not be smaller than the current size");
		} 
		RingBuffer<Item> newBuffer = new RingBuffer<>(newCapacity);
		while (!isEmpty()) {
			newBuffer.enqueue(this.dequeue());
		}
		this.a = newBuffer.a;
		this.N = newBuffer.N;
		this.first = newBuffer.first;
		this.last = newBuffer.last;
	}

	/**
	 * Returns an iterator over the elements in the buffer. 
	 */
	public Iterator<Item> iterator() {
		return new RingBufferIterator();
	}

	private class RingBufferIterator implements Iterator<Item> {
		private int i = 0;
		
		/** @inheritDoc */
		public boolean hasNext() {
			return i < N;
		}

		/** @inheritDoc */
		public void remove() {
			// iterator, doesn't implement remove() since it's optional
			throw new UnsupportedOperationException();
		}

		/** @inheritDoc */
		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return a[i++];
		}
	}

}
