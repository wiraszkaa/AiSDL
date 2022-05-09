package Lista8.src;

import java.util.List;

public class BinarySearchTreeSorter {
    public static <T extends Comparable<T>> void sort(List<T> list) throws DuplicateElementException {
        BinarySearchTree<T> bst = new BinarySearchTree<>();
        for(T i: list) {
            bst.add(i);
        }
        list.clear();
        bst.getInOrder(bst.root, list);
    }
}
