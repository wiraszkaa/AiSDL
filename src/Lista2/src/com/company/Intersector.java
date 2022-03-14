package Lista2.src.com.company;

public class Intersector {
    public static OneWayLinkedList<Integer> intersect(
            OneWayLinkedList<Integer> list1,
            OneWayLinkedList<Integer> list2) {
        OneWayLinkedList<Integer> result = new OneWayLinkedList<>();
        for(int i: list1) {
            if(list2.contains(i)) {
                result.add(i);
            }
        }
        return result;
    }
}
