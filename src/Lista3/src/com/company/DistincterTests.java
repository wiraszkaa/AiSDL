package Lista3.src.com.company;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DistincterTests {
    @org.junit.jupiter.api.Test
    void test() {
        var list = new TwoWayLinkedList<Integer>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);

        var distinctList = Distincter.distinct(list);

        assertListContent(distinctList, new Integer[] {1, 2, 3});
    }

    private <T> void assertListContent(IList<T> list, T[] expectedContent) {
        var iterator = list.iterator();

        for (var i = 0; i < expectedContent.length; i++) {
            if (iterator.hasNext() == false) {
                fail("Didn't expect the list to end.");
            }

            assertEquals(expectedContent[i], iterator.next());
        }

        assertFalse(iterator.hasNext());
    }
}
