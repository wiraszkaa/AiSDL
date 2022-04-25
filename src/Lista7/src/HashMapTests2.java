package Lista7.src;

import static org.junit.jupiter.api.Assertions.*;

public class HashMapTests2 {
    @org.junit.jupiter.api.Test
    public void rehashTest() {
        var hashMap = createTestHashMap(3);
        hashMap.rehash(key -> key % 2);
        assertEquals(3, hashMap.elements());
        assertEquals(5, hashMap.size());
        assertEquals("Value1", hashMap.get(1));
        assertEquals("Value2", hashMap.get(2));
        assertEquals("Value3", hashMap.get(3));
    }

    private HashMap<Integer, String> createTestHashMap(int elements) {
        var hashMap = new HashMap<Integer, String>(5, 0.7, key -> key);

        try {
            for (var i = 1; i <= elements; i++) {
                hashMap.add(i, "Value" + i);
            }
        } catch (Exception exception) {
            fail(exception);
        }

        return hashMap;
    }
}
