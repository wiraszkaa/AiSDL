package Lista9.src;

public class DisjointSetLinkedList implements IDisjointSetStructure {
    Element[] sets;

    public DisjointSetLinkedList(int size) {
        sets = new Element[size];
        for (int i = 0; i < sets.length; i++) {
            Element element = new Element(i);
            sets[i] = element;
            element.last = element;
        }
    }

    @Override
    public int findSet(int item) throws ItemOutOfRangeException {
        if (!DisjointSetForest.checkInBounds(item, sets.length)) {
            throw new ItemOutOfRangeException();
        }
        return sets[item].rep;
    }

    @Override
    public void union(int item1, int item2) throws ItemOutOfRangeException {
        if (!DisjointSetForest.checkInBounds(item1, item2, sets.length)) {
            throw new ItemOutOfRangeException();
        }
        Element rep1 = sets[findSet(item1)];
        Element rep2 = sets[findSet(item2)];
        if (rep1.length < rep2.length) {
//            Element temp = rep1;
//            rep1 = rep2;
//            rep2 = temp;
            rep1 = DisjointSetForest.swap(rep2, rep2 = rep1);
        }
        rep1.length += rep2.length;

        repHandler(rep1, rep2);
    }

    private void repHandler(Element repElement, Element curr) {
        repElement.last.next = curr;
        repElement.last = curr.last;

        while (curr != null) {
            curr.rep = repElement.rep;

            curr = curr.next;
        }
    }

    private static class Element {
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
