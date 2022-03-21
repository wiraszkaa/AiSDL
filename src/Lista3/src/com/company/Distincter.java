package Lista3.src.com.company;

public class Distincter {
    public static TwoWayLinkedList<Integer> distinct(TwoWayLinkedList<Integer> list)
    {
        TwoWayLinkedList<Integer> result = new TwoWayLinkedList<>();
        int last = Integer.MIN_VALUE;
        for(int i: list) {
            if(last < i) {
                result.add(i);
                last = i;
            }
        }
        return result;
    }
}
