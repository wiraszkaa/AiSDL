package Lista7.src;

import java.util.NoSuchElementException;
import java.util.function.Function;

public class HashMap<TKey, TValue> {
    private int size;
    private int elements;
    private final double maxLoadFactor;
    private double loadFactor;
    private Function<TKey, Integer> hashFunction;

    private Node<TKey, TValue>[] array;

    public HashMap(int initialSize, double loadFactor, Function<TKey, Integer> hashFunction) {
        this.size = initialSize;
        this.elements = 0;
        this.maxLoadFactor = loadFactor;
        this.loadFactor = 0;
        this.hashFunction = hashFunction;

        this.array = new Node[initialSize];

        createEmptyArray(this.array);
    }

    private void createEmptyArray(Node<TKey, TValue>[] array) {
        for(int i = 0; i < array.length; i++) {
            Node<TKey, TValue> head = new Node<>(null, null, null, null);
            head.prev= head;
            array[i] = head;
        }
    }

    public void add(TKey key, TValue value) throws DuplicateKeyException {
        if(containsKey(key)) {
           throw new DuplicateKeyException();
        } else {
            elements++;
            checkLoadFactor();
            handleAdd(key, value, array);
        }
    }

    private void handleAdd(TKey key, TValue value, Node<TKey, TValue>[] array) {
        Node<TKey, TValue> node = array[getIndex(key)];
        Node<TKey, TValue> newNode = new Node<>(key, value, node.prev, null);
        node.prev.next = newNode;
        node.prev = newNode;
    }

    public void clear() {
        createEmptyArray(this.array);
        elements = 0;
    }

    public boolean containsKey(TKey key) {
        Node<TKey, TValue> node = getNode(key);
        return node != null;
    }

    public boolean containsValue(TValue value) {
        for(Node<TKey, TValue> i: array) {
            Node<TKey, TValue> curr = i.next;
            while (curr != null) {
                if (curr.value.equals(value)) {
                    return true;
                }
                curr = curr.next;
            }
        }
        return false;
    }

    public int elements() {
        return elements;
    }

    public TValue get(TKey key) throws NoSuchElementException {
        Node<TKey, TValue> node = getNode(key);
        if(node != null) {
            return node.value;
        }
        throw new NoSuchElementException();
    }

    public Node<TKey, TValue> getNode(TKey key) {
        Node<TKey, TValue> curr = array[getIndex(key)].next;
        while(curr != null) {
            if(curr.key.equals(key)) {
                return curr;
            }
            curr = curr.next;
        }
        return null;
    }

    public void put(TKey key, TValue value) {
        Node<TKey, TValue> node = getNode(key);
        if(node != null) {
            node.value = value;
        }
        try {
            add(key, value);
        } catch (DuplicateKeyException ignored) {

        }
    }

    public TValue remove(TKey key) {
        Node<TKey, TValue> node = getNode(key);
        if(node != null) {
            TValue value = node.value;
            node.prev.next = node.next;
            if(node.next != null) {
                node.next.prev = node.prev;
            }
            elements--;
            return value;
        }
        return null;
    }

    public int size() {
        return size;
    }

    private int getIndex(TKey key) {
        return hashFunction.apply(key) % size;
    }

    private void checkLoadFactor() {
        loadFactor = (double) elements / size;

        if(loadFactor >= maxLoadFactor) {
            size *= 2;
            handleArrayCopy();
        }
    }

    public void rehash(Function<TKey, Integer> newHashFunction) {
        this.hashFunction = newHashFunction;
        handleArrayCopy();
    }

    private void handleArrayCopy() {
        Node<TKey, TValue>[] newArray = new Node[size];
        createEmptyArray(newArray);
        for (Node<TKey, TValue> i : array) {
            Node<TKey, TValue> curr = i.next;
            while (curr != null) {
                handleAdd(curr.key, curr.value, newArray);
                curr = curr.next;
            }
        }
        this.array = newArray;
    }

    private class Node<TKey, TValue> {
        TKey key;
        TValue value;
        Node<TKey, TValue> next;
        Node<TKey, TValue> prev;

        public Node(TKey key, TValue value, Node<TKey, TValue> prev, Node<TKey, TValue> next) {
            this.key = key;
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
}
