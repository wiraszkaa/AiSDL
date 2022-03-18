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
            temp.add(i);
        }
        Collections.sort(temp);
        OneWayLinkedList<Integer> result = new OneWayLinkedList<>();
        for(int i: temp) {
            result.add(i);
        }
        return result;
//        OneWayLinkedList<Integer> temp = new OneWayLinkedList<>();
//        for(int i: list1) {
//            temp.add(i);
//        }
//        for(int i: list2) {
//               temp.add(i);
//
//        }
//        OneWayLinkedList<Integer> result = new OneWayLinkedList<>();
//        while(!temp.isEmpty()) {
//            int min = Integer.MAX_VALUE;
//            for(int i: temp) {
//                if(i < min) {
//                    min = i;
//                }
//            }
//            result.add(min);
//            temp.remove(min);
//        }
//        return result;
    }
}
