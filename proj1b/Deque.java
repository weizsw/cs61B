public interface Deque<T> {
    boolean isEmpty();
    int size();
    void printDeque();
    T get(int index);
    void addFirst(T item);
    void addLast(T item);
    T removeFirst();
    T removeLast();

}

