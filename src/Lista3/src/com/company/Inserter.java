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
        for (int i = 0; i < list1.size(); i++) {
            if(i == beforeIndex) {
                for(String j: list2) {
                    result.add(j);
                }
            }
            result.add(list1.get(i));
        }
        return result;
    }

    public static TwoWayLinkedList<String> insert(
            TwoWayLinkedList<String> list1,
            TwoWayLinkedList<String> list2,
            String beforeElement) throws NoSuchElementException {
        if(!list1.contains(beforeElement)) {
            throw new NoSuchElementException();
        }
        TwoWayLinkedList<String> result = new TwoWayLinkedList<>();
        for (int i = 0; i < list1.size(); i++) {
            String element = list1.get(i);
            if(element.equals(beforeElement)) {
                for(String j: list2) {
                    result.add(j);
                }
            }
            result.add(element);
        }
        return result;
    }
}
