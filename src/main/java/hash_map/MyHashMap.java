package hash_map;

public class MyHashMap<Integer, Double> {
    private static final int BASIC_CAPACITY = 16;
    private static final int MAX_CAPACITY = java.lang.Integer.MAX_VALUE / 2;
    private int currentCapacity;
    private int currentSize;

    private Node[] hashArray;

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
        hashArray = new Node[this.currentCapacity];
    }

    public int size() {
        return currentSize;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public Double get(Integer key) {
        if (isEmpty()) return null;
        if (key == null) return getFirstNode();

        int hash_1 = getHash_1(key);
        int hash_2 = getHash_2(key);

        int resultHash = (hash_1) % currentCapacity;
        Node<Integer, Double> arrayCell = hashArray[resultHash];

        if (arrayCell == null) {
            System.err.println("No elem for the key in hashMap");
            return null;
        }
        if ((key.equals(arrayCell.key)) && !arrayCell.wasDeleted) return arrayCell.value;

        for (int i = 1; i < currentCapacity; i++) {
            resultHash = (hash_1 + i * hash_2) % currentCapacity;
            arrayCell = hashArray[resultHash];
            if (key.equals(arrayCell.key) && !arrayCell.wasDeleted) return arrayCell.value;
        }
        return null;
    }

    private Double getFirstNode() {
        Node<Integer, Double> arrayCell = hashArray[0];
        return arrayCell.value;
    }

    public Double put(Integer key, Double value) {
        if (isFull()) {
            System.err.println("HashMap is Full");
            return value;
        }

        int hash_1 = getHash_1(key);
        int hash_2 = getHash_2(key);
        int resultHash;
        Node<Integer, Double> arrayCell;

        for (int i = 0; i < currentCapacity; i++) {
            resultHash = (hash_1 + i * hash_2) % currentCapacity;
            arrayCell = hashArray[resultHash];
            if (arrayCell == null || arrayCell.wasDeleted) {
                hashArray[resultHash] = new Node<Integer, Double>(key, value, false);
                currentSize++;
                break;
            }
        }
        return null;
    }

    public Double remove(Integer key) {
        if (isEmpty()) return null;
        if (key == null) return removeFirstNode();

        int hash_1 = getHash_1(key);
        int hash_2 = getHash_2(key);
        int resultHash;
        Node<Integer, Double> arrayCell;

        for (int i = 0; i < currentCapacity; i++) {
            resultHash = (hash_1 + i * hash_2) % currentCapacity;
            arrayCell = hashArray[resultHash];
            if (arrayCell != null && !arrayCell.wasDeleted && key.equals(arrayCell.key)) {
                arrayCell.wasDeleted = true;
                currentSize--;
                return arrayCell.value;
            }
        }
        System.err.println("No elem for the key in HashMap");
        return null;
    }

    private Double removeFirstNode() {
        Node<Integer, Double> arrayCell = hashArray[0];
        arrayCell.wasDeleted = true;
        currentSize--;
        return arrayCell.value;
    }

    private int getHash_1(Integer key) {
        return key != null ? key.hashCode() : 0;
    }

    private int getHash_2(Integer key) {
        int value = getHash_1(key) % (currentCapacity - 4);
        if (value % 2 == 0) return value + 1;
        return value;
    }

    private boolean isFull() {
        return currentSize == currentCapacity;
    }

    private static class Node<Integer, Double> {
        private Integer key;
        private Double value;
        private boolean wasDeleted;

        private Node(Integer key, Double value, boolean wasDeleted) {
            this.key = key;
            this.value = value;
            this.wasDeleted = wasDeleted;
        }
    }
}