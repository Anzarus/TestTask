package hash_map;

//todo: make KEY to int; make VALUE to long

public class MyHashMap<K, V> {
    private static final int BASIC_CAPACITY = 16;
    private static final int MAX_CAPACITY = Integer.MAX_VALUE / 2;
    private int currentCapacity;
    private int currentSize;

    private Node<K, V>[] hashArray;

    public MyHashMap() {
        this.currentCapacity = BASIC_CAPACITY;
        this.hashArray = new Node[currentCapacity];
    }

    public MyHashMap(int currentCapacity) {
        if (currentCapacity > MAX_CAPACITY || currentCapacity < BASIC_CAPACITY) {
            this.currentCapacity = BASIC_CAPACITY;
        } else {
            this.currentCapacity = currentCapacity;
        }
        hashArray = new Node[currentCapacity];
    }

    public int size() {
        return currentSize;
    }

    public boolean isEmpty() {
        return currentSize == 0;
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
        if (isFull()) {
            System.err.println("HashMap is full");
            return null;
        }
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
