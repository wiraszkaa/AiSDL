package Lista9.src;

public class DisjointSetForest implements IDisjointSetStructure {
    private final Element[] sets;
    private int amount;

    public DisjointSetForest(int size) {
        sets = new Element[size];
        amount = size;
        for (int i = 0; i < sets.length; i++) {
            sets[i] = new Element(i);
        }
    }

    @Override
    public int findSet(int item) throws ItemOutOfRangeException {
        if (!checkInBounds(item, sets.length)) {
            throw new ItemOutOfRangeException();
        }
        return recFindRep(item);
    }

    private int recFindRep(int item) {
        Element element = sets[item];
        if (element.par == item) {
            return item;
        }
        int rep = recFindRep(element.par);
        element.par = rep;
        return rep;
    }

    @Override
    public void union(int item1, int item2) throws ItemOutOfRangeException {
        if (!checkInBounds(item1, item2, sets.length)) {
            throw new ItemOutOfRangeException();
        }
        if (sets[item1].rank < sets[item2].rank) {
            int temp = item1;
            item1 = item2;
            item2 = temp;
        } else if (sets[item1].rank == sets[item2].rank) {
            sets[item1].rank++;
        }
        sets[findSet(item1)].par = findSet(item2);

        amount--;
    }

    public static boolean checkInBounds(int index, int length) {
        return index < length && index >= 0;
    }

    public static boolean checkInBounds(int index1, int index2, int length) {
        return checkInBounds(index1, length) && checkInBounds(index2, length);
    }

    public int getAmount() {
        return this.amount;
    }

    private static class Element {
        int par;
        int rank;

        public Element(int par) {
            this.par = par;

            rank = 0;
        }
    }
}
