package Lista4.src.com.company;

import Lista4.src.com.company.exceptions.EmptyQueueException;
import Lista4.src.com.company.exceptions.FullQueueException;

public interface IQueue<T> {
    boolean isEmpty();
    boolean isFull();
    void enqueue(T value) throws FullQueueException;
    T first() throws EmptyQueueException;
    T dequeue() throws EmptyQueueException;
    int size();
}
