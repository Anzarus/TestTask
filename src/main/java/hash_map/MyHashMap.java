package hash_map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

//todo: make KEY to int; make VALUE to long

public class MyHashMap<K, V> implements Map<K, V> {
    private static final int BASIC_CAPACITY = 16;
    private static int currentCapacity;

    private int currentSize;

    private Node<K, V>[] hashArray;

    public MyHashMap() {
        currentCapacity = BASIC_CAPACITY;
        hashArray = new Node[currentCapacity];
    }


    public int size() {
        return currentSize;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean containsKey(Object key) {
        return false;
    }

    public boolean containsValue(Object value) {
        return false;
    }

    public V get(Object key) {
        if (isEmpty()) return null;

        int hash_1 = getHash_1(key);
        int hash_2 = getHash_2(key);
        int resultHash = (hash_1) % currentCapacity;
        Node<K, V> arrayCell = hashArray[resultHash];

        if (arrayCell == null) {
            System.err.println("No elem for the key in hashMap");
            return null;
        }
        for (int i = 1; i < currentCapacity; i++) {
            resultHash = (hash_1 + i * hash_2) % currentCapacity;
            arrayCell = hashArray[resultHash];
            if (key.equals(arrayCell.key) && !arrayCell.wasDeleted) return arrayCell.value;
        }
        return null;
    }

    public V put(K key, V value) {
        int hash_1 = getHash_1(key);
        int hash_2 = getHash_2(key);
        int resultHash;
        Node<K, V> arrayCell;

        for (int i = 0; i < currentCapacity; i++) {
            resultHash = (hash_1 + i * hash_2) % currentCapacity;
            arrayCell = hashArray[resultHash];
            if (arrayCell == null || arrayCell.wasDeleted) {
                hashArray[resultHash] = new Node<K, V>(resultHash, key, value, false);
                currentSize++;
                break;
            }
        }
//todo
        return null;
    }

    public V remove(Object key) {
        if (isEmpty()) return null;

        int hash_1 = getHash_1(key);
        int hash_2 = getHash_2(key);
        int resultHash;
        Node<K, V> arrayCell;

        for (int i = 0; i < currentCapacity; i++) {
            resultHash = (hash_1 + i * hash_2) % currentCapacity;
            arrayCell = hashArray[resultHash];
            if (arrayCell != null && key.equals(arrayCell.key)) {
                arrayCell.wasDeleted = true;
                currentSize--;
                return arrayCell.value;
            }
        }
        System.err.println("No elem for the key in HashMap");
        return null;
    }

    public void putAll(Map<? extends K, ? extends V> m) {

    }

    public void clear() {

    }

    public Set<K> keySet() {
        return null;
    }

    public Collection<V> values() {
        return null;
    }

    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    private int getHash_1(Object key) {
        return key != null ? key.hashCode() : 0;
    }

    private int getHash_2(Object key) {
        return 1 + key.hashCode() % (currentCapacity - 3);
    }

    private boolean isFull() {
        return currentSize == currentCapacity;
    }

    private static class Node<K, V> {
        private int hash;
        private K key;
        private V value;
        private boolean wasDeleted;

        private Node(int hash, K key, V value, boolean wasDeleted) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.wasDeleted = wasDeleted;
        }
    }
}
