package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {

        rb = (T[]) new Object[capacity];
        this.capacity = capacity;
        fillCount = 0;
        first = 0;
        last = first;


    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        rb[last] = x;
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        } else {
            fillCount += 1;
        }
        if (last == capacity - 1) {
            last = 0;
        } else {
            last += 1;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {

        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            T removed = rb[first];
            if (first != capacity - 1) {
                first += 1;
            } else {
                first = 0;
            }
            fillCount -= 1;
            return removed;
        }
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    private class ArrayRingBufferIterator implements Iterator<T> {
        private int cur;
        private int count;
        ArrayRingBufferIterator() {
            cur = first;
            count = fillCount();
        }
        @Override
        public boolean hasNext() {
            return count < capacity();
        }
        @Override
        public T next() {
            T nextItem = rb[cur];
            if (cur == capacity() - 1) {
                cur = 0;
            } else {
                cur += 1;
            }
            return nextItem;
        }
    }

}
