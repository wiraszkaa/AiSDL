package Lista9.src;

public class DisjointSetForest implements IDisjointSetStructure {
    private Element[] array;

    public DisjointSetForest(int size) {
        array = new Element[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = new Element(i);
        }
    }

    @Override
    public int findSet(int item) throws ItemOutOfRangeException {
        if (item < 0 || item >= array.length) {
            throw new ItemOutOfRangeException();
        }
        Element element = array[item];
        if (element.par == item) {
            return item;
        }
        int rep = findSet(element.par);
        element.par = rep;
        return rep;
    }

    @Override
    public void union(int item1, int item2) throws ItemOutOfRangeException {
        if (item1 < 0 || item1 >= array.length || item2 < 0 || item2 >= array.length) {
            throw new ItemOutOfRangeException();
        }
        if (array[item1].rank == array[item2].rank) {
            array[item1].rank++;
        } else if (array[item1].rank < array[item2].rank) {
            int temp = item1;
            item1 = item2;
            item2 = temp;
        }
        array[findSet(item1)].par = findSet(item2);
    }

    private class Element {
        int par;
        int rank;

        public Element(int par) {
            this.par = par;

            rank = 0;
        }
    }
}
