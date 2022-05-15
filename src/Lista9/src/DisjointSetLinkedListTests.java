package Lista9.src;

public class DisjointSetLinkedListTests extends DisjointSetTestBase {
    protected DisjointSetLinkedListTests() {
        super(size -> new DisjointSetLinkedList(size));
    }
}
