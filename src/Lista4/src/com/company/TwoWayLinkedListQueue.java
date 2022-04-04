package Lista4.src.com.company;

import Lista4.src.com.company.exceptions.EmptyQueueException;
import Lista4.src.com.company.exceptions.FullQueueException;

public class TwoWayLinkedListQueue<T> implements IQueue<T> {
    private final int capacity;
    private int size;
    private final Element<T> sentinel;
    
    public TwoWayLinkedListQueue(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.sentinel = new Element<>(null ,null ,null);
        this.sentinel.prev = this.sentinel;
        this.sentinel.next = this.sentinel;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public void enqueue(T value) throws FullQueueException {
        if(size < capacity) {
            Element<T> lastElement = this.sentinel.prev;
            Element<T> newElement = new Element<>(lastElement, this.sentinel, value);
            this.sentinel.prev.next = newElement;
            this.sentinel.prev = newElement;
            size++;
        } else {
            throw new FullQueueException();
        }
    }

    @Override
    public T first() throws EmptyQueueException {
        if(size > 0) {
            return this.sentinel.next.value;
        } else {
            throw new EmptyQueueException();
        }
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        if(size > 0) {
            T value = this.sentinel.next.value;
            this.sentinel.next = this.sentinel.next.next;
            this.sentinel.next.next.prev = this.sentinel;
            size--;
            return value;
        } else {
            throw new EmptyQueueException();
        }
    }

    @Override
    public int size() {
        return size;
    }

    private class Element<T> {
        Element<T> prev;
        Element<T> next;
        T value;

        public Element(Element<T> prev, Element<T> next, T value) {
            this.prev = prev;
            this.next = next;
            this.value = value;
        }
    }
}
