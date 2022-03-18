package Lista2.src.com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class OneWayLinkedList<T> implements IList<T> {
    private final Node<T> head = new Node<>(null, null);

    @Override
    public void add(T value) {
        Node<T> curr = head;
        while(curr.next != null) {
            curr = curr.next;
        }
        curr.next = new Node<>(value, null);
    }

    @Override
    public void addAt(int index, T value) throws NoSuchElementException {
        int size = size();
        if(index == size) {
            add(value);
        } else if(index == 0) {
            head.next = new Node<>(value, head.next);
        } else if (index > 0 && index < size){
            Node<T> prevNode = getNode(index - 1);
            prevNode.next = new Node<>(value, prevNode.next);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void clear() {
        head.next = null;
    }

    @Override
    public boolean contains(T value) {
        return indexOf(value) >= 0;
    }

    @Override
    public T get(int index) throws NoSuchElementException {
        return getNode(index).value;
    }

    private Node<T> getNode(int index) throws NoSuchElementException {
        Node<T> curr = head.next;
        while(curr != null) {
            if(index == 0) {
                return curr;
            } else {
                curr = curr.next;
                index--;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public void set(int index, T value) throws NoSuchElementException {
        getNode(index).value = value;
    }

    @Override
    public int indexOf(T value) {
        Node<T> curr = head;
        int index = 0;
        while(curr.next != null) {
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
        return head.next == null;
    }

    @Override
    public T removeAt(int index) throws NoSuchElementException {
        int size = size();
        if(index == size) {
            Node<T> node = getNode(size - 1);
            T value = node.next.value;
            node.next = null;
            return value;
        } else if(index == 0) {
            T value = head.next.value;
            head.next = head.next.next;
            return value;
        } else if(index > 0 && index < size) {
            Node<T> prevNode = getNode(index - 1);
            T value = prevNode.next.value;
            prevNode.next = prevNode.next.next;
            return value;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public boolean remove(T value) {
        try {
            removeAt(indexOf(value));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    @Override
    public int size() {
        Node<T> curr = head;
        int size = 0;
        while(curr.next != null) {
            curr = curr.next;
            size++;
        }
        return size;
    }

    @Override
    public void print() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> curr = head;
        while(curr.next != null) {
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
        return new OneWayLinkedListIterator();
    }

    private class OneWayLinkedListIterator implements Iterator<T> {
        Node<T> current = head;
        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public T next() {
            if(hasNext()) {
                current = current.next;
                return current.value;
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    private class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
