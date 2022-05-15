package Lista9.src;

public class DisjointSetLinkedList implements IDisjointSetStructure {
    Element[] array;

    public DisjointSetLinkedList(int size) {
        array = new Element[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = new Element(i, -1);
            array[i].last = i;
        }
    }

    @Override
    public int findSet(int item) throws ItemOutOfRangeException {
        if (item < 0 || item >= array.length) {
            throw new ItemOutOfRangeException();
        }
        return array[item].rep;
    }

    @Override
    public void union(int item1, int item2) throws ItemOutOfRangeException {
        if (item1 < 0 || item1 >= array.length || item2 < 0 || item2 >= array.length) {
            throw new ItemOutOfRangeException();
        }
        int rep1 = findSet(item1);
        int rep2 = findSet(item2);
        if(array[rep1].length < array[rep2].length) {
            int temp = rep1;
            rep1 = rep2;
            rep2 = temp;
        }
        array[rep1].length += array[rep2].length;

        Element repElement = array[rep1];
        int curr = rep2;

        array[repElement.last].next = curr;

        int prev = curr;
        while (curr != -1) {
            array[curr].rep = repElement.rep;

            prev = curr;
            curr = array[curr].next;
        }
        repElement.last = prev;
    }

    private class Element {
        int rep;
        int next;

        int last;
        int length;

        public Element(int rep, int next) {
            this.rep = rep;
            this.next = next;

            length = 1;
        }
    }
}
