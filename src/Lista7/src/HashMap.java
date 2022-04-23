package Lista7.src;

import org.junit.platform.engine.support.hierarchical.Node;

import java.util.Arrays;
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
        // TODO: Zainicjuj nową instancję klasy HashMap według podanych parametrów.
        //    InitialSize - początkowy rozmiar HashMap
        //    LoadFactor - stosunek elementów do rozmiaru HashMap po przekroczeniu którego należy podwoić rozmiar HashMap.
        //    HashFunction - funkcja, według której liczony jest hash klucza.
        //       Przykład użycia:   int hash = hashFunction.apply(key);
        this.size = initialSize;
        this.elements = 0;
        this.maxLoadFactor = loadFactor;
        this.loadFactor = 0;
        this.hashFunction = hashFunction;

        this.array = new Node[initialSize];

        createEmptyArray(this.array);
    }

    private void createEmptyArray(Node[] array) {
        for(int i = 0; i < array.length; i++) {
            Node<TKey, TValue> head = new Node<>(null, null, null, null);
            head.prev= head;
            array[i] = head;
        }
    }

    public void add(TKey key, TValue value) throws DuplicateKeyException {
        // TODO: Dodaj nową parę klucz-wartość. Rzuć wyjątek DuplicateKeyException, jeżeli dany klucz już istnieje w HashMap.
        if(containsKey(key)) {
           throw new DuplicateKeyException();
        } else {
            elements++;
            checkLoadFactor();
            handleAdd(key, value);
        }
    }

    private void handleAdd(TKey key, TValue value) {
        Node<TKey, TValue> node = array[getIndex(key)];
        Node<TKey, TValue> newNode = new Node<>(key, value, node.prev, null);
        node.prev.next = newNode;
        node.prev = newNode;
    }

    public void clear() {
        // TODO: Wyczyść zawartość HashMap.
        createEmptyArray(this.array);
        elements = 0;
    }

    public boolean containsKey(TKey key) {
        // TODO: Sprawdź, czy HashMap zawiera już dany klucz.
        Node<TKey, TValue> curr = array[getIndex(key)].next;
        while(curr != null) {
            if(curr.key.equals(key)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public boolean containsValue(TValue value) {
        // TODO: Sprawdź, czy HashMap zawiera już daną wartość.
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
        // TODO: Zwróć liczbę par klucz-wartość przechowywaną w HashMap.
        return elements;
    }

    public TValue get(TKey key) throws NoSuchElementException {
        // TODO: Pobierz wartość powiązaną z danym kluczem. Rzuć wyjątek NoSuchElementException, jeżeli dany klucz nie istnieje.
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
        // TODO: Przypisz daną wartość do danego klucza.
        //   Jeżeli dany klucz już istnieje, nadpisz przypisaną do niego wartość.
        //   Jeżeli dany klucz nie istnieje, dodaj nową parę klucz-wartość.
        Node<TKey, TValue> curr = array[getIndex(key)].next;
        while(curr != null) {
            if(curr.key.equals(key)) {
                curr.value = value;
                return;
            }
            curr = curr.next;
        }
        try {
            add(key, value);
        } catch (DuplicateKeyException ignored) {

        }
    }

    public TValue remove(TKey key) {
        // TODO: Usuń parę klucz-wartość, której klucz jest równy podanej wartości.
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
        // TODO: Zwróć obecny rozmiar HashMap.
        return size;
    }

    private int getIndex(TKey key) {
        return hashFunction.apply(key) % size;
    }

    private void checkLoadFactor() {
        loadFactor = (double) elements() / size;

        if(loadFactor >= maxLoadFactor) {
            size *= 2;
            Node<TKey, TValue>[] newArray = new Node[size];
            createEmptyArray(newArray);
            for(Node<TKey, TValue> i: array) {
                if(i.next != null) {
                    Node<TKey, TValue> node = newArray[getIndex(i.next.key)];
                    node.next = i.next;
                    node.prev = i.prev;
                }
            }
            this.array = newArray;
        }
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
