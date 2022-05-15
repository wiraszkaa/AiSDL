package Lista9.src;

public class DisjointSetLinkedList implements IDisjointSetStructure {
    Element[] array;

    public DisjointSetLinkedList(int size) {
        array = new Element[size];
        for (int i = 0; i < array.length; i++) {
            Element element = new Element(i);
            array[i] = element;
            element.last = element;
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

        repHandler(array[rep1], rep2);
    }

    private void repHandler(Element repElement, int start) {
        Element curr = array[start];

        repElement.last.next = curr;
        repElement.last = curr.last;

        while (curr != null) {
            curr.rep = repElement.rep;

            curr = curr.next;
        }
    }

    private class Element {
        int rep;
        Element next;

        Element last;
        int length;

        public Element(int rep) {
            this.rep = rep;

            length = 1;
        }
    }
}
