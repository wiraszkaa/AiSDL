package Lista3.src.com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TwoWayLinkedList<T> implements IList<T> {
    private final Element<T> head;
    private final Element<T> tail;
    private int size;

    public TwoWayLinkedList() {
        this.head = new Element<>(null, null, null);
        this.tail = new Element<>(head, null, null);
        this.head.next = this.tail;
        this.size = 0;
    }

    @Override
    public void add(T value) {
        Element<T> newElement = new Element<>(tail.prev, tail, value);
        tail.prev.next = newElement;
        tail.prev = newElement;
        size++;
    }

    @Override
    public void addAt(int index, T value) throws NoSuchElementException {
        if (index == 0) {
            Element<T> newElement = new Element<>(head, head.next, value);
            head.next.prev = newElement;
            head.next = newElement;
        } else if (index == size) {
            add(value);
            return;
        } else if(index > 0 && index < size) {
            Element<T> prevElement = getElement(index - 1);
            Element<T> nextElement = prevElement.next;
            Element<T> newElement = new Element<>(prevElement, nextElement, value);
            prevElement.next = newElement;
            nextElement.prev = newElement;
        } else {
            throw new NoSuchElementException();
        }
        size++;
    }

    @Override
    public void clear() {
        this.head.next = tail;
        this.tail.prev = head;
        this.size = 0;
    }

    @Override
    public boolean contains(T value) {
        return indexOf(value) >= 0;
    }

    @Override
    public T get(int index) throws NoSuchElementException {
        return getElement(index).value;
    }

    private Element<T> getElement(int index) throws NoSuchElementException {
        if(index >= size || index < 0) {
            throw new NoSuchElementException();
        } else if(index > size / 2) {
            Element<T> curr = tail;
            for(int i = 0; i < size - index; i++) {
                curr = curr.prev;
            }
            return curr;
        } else {
            Element<T> curr = head;
            for(int i = 0; i <= index; i++) {
                curr = curr.next;
            }
            return curr;
        }
    }

    @Override
    public void set(int index, T value) throws NoSuchElementException {
        getElement(index).value = value;
    }

    @Override
    public int indexOf(T value) {
        Element<T> curr = head;
        int index = 0;
        while(curr.next != tail) {
            curr = curr.next;
            if(curr.value.equals(value)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return head.next == tail;
    }

    @Override
    public T removeAt(int index) throws NoSuchElementException {
        T value;
        if (index == 0) {
            value = head.next.value;
            head.next.next.prev = head;
            head.next = head.next.next;
        } else if (index == size - 1) {
            value = tail.prev.value;
            tail.prev.prev.next = tail;
            tail.prev = tail.prev.prev;
        } else if(index > 0 && index < size - 1) {
            Element<T> prevElement = getElement(index - 1);
            Element<T> nextElement = prevElement.next.next;
            value = prevElement.next.value;
            prevElement.next = nextElement;
            nextElement.prev = prevElement;
        } else {
            throw new NoSuchElementException();
        }
        size--;
        return value;
    }

    @Override
    public boolean remove(T value) {
        Element<T> curr = head;
        while(curr.next != tail) {
            curr = curr.next;
            if(curr.value.equals(value)) {
                curr.prev.next = curr.next;
                curr.next.prev = curr.prev;
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void print() {
        StringBuilder sb = new StringBuilder("[");
        Element<T> curr = head;
        while(curr.next != tail) {
            sb.append(curr.next.value).append(", ");
            curr = curr.next;
        }
        int index = sb.lastIndexOf(",");
        if(index > 0) {
            sb.delete(index, index + 2);
        }
        sb.append("]");
        System.out.println(sb);
    }

    @Override
    public Iterator<T> iterator() {
        return new TwoWayLinkedListIterator();
    }

    private class TwoWayLinkedListIterator implements Iterator<T> {
        Element<T> current = head;
        @Override
        public boolean hasNext() {
            return current.next != tail;
        }

        @Override
        public T next() {
            if(hasNext()) {
                current = current.next;
                return current.value;
            }
            throw new NoSuchElementException();
        }
    }

    private class Element<T> {
        Element<T> next;
        Element<T> prev;
        T value;

        public Element(Element<T> prev, Element<T> next, T value) {
            this.next = next;
            this.prev = prev;
            this.value = value;
        }
    }
}
