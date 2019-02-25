package hash_map;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMyHashMap {
    @Test
    public void testCreate() {
        MyHashMap<Integer, Double> standartMap = new MyHashMap<Integer, Double>();
        MyHashMap<Integer, Double> customMap = new MyHashMap<Integer, Double>(250);

        assertTrue(standartMap.isEmpty());
        assertEquals(standartMap.size(), 0);
        assertTrue(customMap.isEmpty());
        assertEquals(customMap.size(), 0);
    }

    @Test
    public void testPutAndGet() {
        MyHashMap<Integer, Double> map = new MyHashMap<Integer, Double>();
        Integer[] integers = {4, 645, 8, 870, null, 86, 6446, 984,
                435, 65, 318, 5170, 9, 89, 646, 14};
        Double[] doubles = {4860.08, null, -90.6541, null, null, 64.205, -0.5, -80.47,
                480.8, 0.46504, 6.541, null, 94.205, -950.5, 84560.47, 7.90};

        assertNull(map.get(4654));

        for (int i = 0; i < integers.length; i++) {
            map.put(integers[i], doubles[i]);
        }

        assertFalse(map.isEmpty());
        assertEquals(map.size(), integers.length);

        for (int i = 0; i < integers.length; i++) {
            assertEquals(map.get(integers[i]), doubles[i]);
        }

        assertEquals(map.put(123, 45.095), 45.095);
    }

    @Test
    public void testNotCorrectCreate() {
        MyHashMap<Integer, Double> wrongCustomMap_1 = new MyHashMap<Integer, Double>(-1);
        MyHashMap<Integer, Double> wrongCustomMap_2 = new MyHashMap<Integer, Double>(Integer.MAX_VALUE);

        Double aDouble = 1.0;
        int count = 0;
        while (!aDouble.equals(wrongCustomMap_1.put(1, 1.0))) {
            count++;
        }
        assertEquals(count, 16);

        count = 0;
        while (!aDouble.equals(wrongCustomMap_2.put(1, 1.0))) {
            count++;
        }
        assertEquals(count, 16);

    }

    @Test
    public void testPutAndRemove() {
        MyHashMap<Integer, Double> map = new MyHashMap<Integer, Double>();
        Integer[] integers = {4, 645, 8, 870, null, 86};
        Double[] doubles = {4860.08, null, -90.6541, 4567.946, null, 64.205};

        assertNull(map.remove(7934));

        for (int i = 0; i < integers.length; i++) {
            map.put(integers[i], doubles[i]);
        }

        assertFalse(map.isEmpty());
        assertEquals(map.size(), integers.length);

        assertEquals(map.remove(4), 4860.08);
        assertNull(map.remove(null));
        assertEquals(map.remove(8), -90.6541);

        assertEquals(map.size(), 3);
        map.put(6789, 9823.01);
        assertEquals(map.size(), 4);
    }
}