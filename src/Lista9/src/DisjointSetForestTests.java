package Lista9.src;

public class DisjointSetForestTests extends DisjointSetTestBase {
    protected DisjointSetForestTests() {
        super(size -> new DisjointSetForest(size));
    }
}
