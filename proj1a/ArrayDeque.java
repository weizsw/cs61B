public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int initialCapacity = 8;
    private int capacity;
    private int eFactor = 2;
    private int sFactor = 2;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        capacity = initialCapacity;
        items = (T[]) new Object[initialCapacity];
        nextFirst = capacity - 1;
        nextLast = 0;
        size = 0;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int currentIndex = plusOne(nextFirst);
        while (currentIndex != nextLast) {
            System.out.print(items[currentIndex] + " ");
            currentIndex = plusOne(currentIndex);
        }
        System.out.println();
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int indexFromFront = nextFirst + 1 + index;
        if (indexFromFront >= capacity) {
            indexFromFront -= capacity;
        }
        return items[indexFromFront];
    }

    private void resize(int newCapacity) {
        T[] newItems = (T[]) new Object[newCapacity];
        int currentFirst = plusOne(nextFirst);
        int currentLast = minusOne(nextLast);

        if (currentFirst < currentLast) {
            int length = currentLast - currentFirst + 1;
            System.arraycopy(items, currentFirst, newItems, 0, length);
            nextFirst = newCapacity - 1;
            nextLast = length;
        } else {
            int lengthFirst = capacity - currentFirst;
            int newCurrentFirst = newCapacity - lengthFirst;
            int lengthLast = nextLast;
            System.arraycopy(items, currentFirst, newItems, newCurrentFirst, lengthFirst);
            System.arraycopy(items, 0, newItems, 0, lengthLast);
            nextFirst = newCapacity - lengthFirst - 1;
        }
        capacity = newCapacity;
        items = newItems;

    }

    private void expand() {
        if (size == capacity) {
            resize(capacity * eFactor);
        }
    }

    private void shrink() {
        double ratio = (double) size / capacity;
        if (capacity >= 16 && ratio < 0.25) {
            resize(capacity / sFactor);
        }
    }

    private int minusOne(int index) {
        if (index == 0) {
            return capacity - 1;
        } else {
            return index - 1;
        }
    }

    private int plusOne(int index) {
        if (index == capacity - 1) {
            return 0;
        } else {
            return index + 1;
        }
    }

    public void addFirst(T item) {
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
        expand();

    }

    public void addLast(T item) {
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size += 1;
        expand();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        int currentFirst = plusOne(nextFirst);
        T removed = items[currentFirst];
        items[currentFirst] = null;
        nextFirst = currentFirst;
        size -= 1;
        shrink();
        return removed;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        int currentLast = minusOne(nextLast);
        T removed = items[currentLast];
        items[currentLast] = null;
        nextLast = currentLast;
        size -= 1;
        shrink();
        return removed;
    }
}

