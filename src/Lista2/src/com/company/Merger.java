package Lista2.src.com.company;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Merger {
    public static OneWayLinkedList<Integer> merge(
            OneWayLinkedList<Integer> list1,
            OneWayLinkedList<Integer> list2) {
        List<Integer> temp = new LinkedList<>();
        for(int i: list1) {
            temp.add(i);
        }
        for(int i: list2) {
            if(!list1.contains(i)) {
                temp.add(i);
            }
        }
        Collections.sort(temp);
        OneWayLinkedList<Integer> result = new OneWayLinkedList<>();
        for(int i: temp) {
            result.add(i);
        }
        return result;
    }
}
