package hash_map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

//todo: make KEY to int; make VALUE to long

public class MyHashMap<K, V> implements Map<K, V> {
    private static final int BASIC_CAPACITY = 16;

    private int currentSize;

    private Node<K, V>[] hashArray;

    public MyHashMap(){
        hashArray = new Node[BASIC_CAPACITY];
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
        return null;
    }

    public V put(K key, V value) {
        return null;
    }

    public V remove(Object key) {
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

    private static class Node<K, V> {
        private int hash;
        private K key;
        private V value;

        private Node(int hash, K key, V value){
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }
}
