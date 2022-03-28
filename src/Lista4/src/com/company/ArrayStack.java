package Lista4.src.com.company;

import Lista4.src.com.company.exceptions.FullStackException;
import java.util.EmptyStackException;

public class ArrayStack<T> implements IStack<T>{
    private final int capacity;
    private int topIndex;
    private final T[] array;

    public ArrayStack(int capacity) {
        this.capacity = capacity;
        this.topIndex = -1;
        this.array = (T[]) new Object[capacity];
    }

    @Override
    public boolean isEmpty() {
        return topIndex < 0;
    }

    @Override
    public boolean isFull() {
        return topIndex == capacity - 1;
    }

    @Override
    public T top() throws EmptyStackException {
        if(topIndex >= 0) {
            return array[topIndex];
        } else {
            throw new EmptyStackException();
        }
    }

    @Override
    public T pop() throws EmptyStackException {
        if(topIndex >= 0) {
            T value = array[topIndex];
            array[topIndex] = null;
            topIndex--;
            return value;
        } else {
            throw new EmptyStackException();
        }
    }

    @Override
    public void push(T value) throws FullStackException {
        if(topIndex < capacity - 1) {
            array[topIndex + 1] = value;
            topIndex++;
        } else {
            throw new FullStackException();
        }
    }

    @Override
    public int size() {
        return this.capacity;
    }
}
