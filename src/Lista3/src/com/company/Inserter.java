package Lista3.src.com.company;

import java.util.NoSuchElementException;

public class Inserter {

    public static TwoWayLinkedList<String> insert(
            TwoWayLinkedList<String> list1,
            TwoWayLinkedList<String> list2,
            int beforeIndex) throws NoSuchElementException {
        if(beforeIndex > list1.size() || beforeIndex < 0) {
            throw new NoSuchElementException();
        }
        TwoWayLinkedList<String> result = new TwoWayLinkedList<>();
        for (int i = 0; i < beforeIndex; i++) {
            result.add(list1.get(i));
        }
        for(String i: list2) {
            result.add(i);
        }
        for(int i = beforeIndex; i < list1.size(); i++) {
            result.add(list1.get(i));
        }

        return result;
    }

    public static TwoWayLinkedList<String> insert(
            TwoWayLinkedList<String> list1,
            TwoWayLinkedList<String> list2,
            String beforeElement) throws NoSuchElementException {
        int beforeIndex = list1.indexOf(beforeElement);
        if(beforeIndex < 0) {
            throw new NoSuchElementException();
        }
        return insert(list1, list2, beforeIndex);
    }
}
