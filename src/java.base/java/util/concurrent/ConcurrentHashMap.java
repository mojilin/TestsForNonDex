package java.util.concurrent;

import java.io.Serializable;
import java.util.*;
import edu.illinois.nondex.shuffling.ControlNondeterminism;

public class ConcurrentHashMap<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V>, Serializable {
    private final Map<K, V> innerMap;

    public ConcurrentHashMap() {
        this.innerMap = new HashMap<>();
    }

    public ConcurrentHashMap(int initialCapacity) {
        this.innerMap = new HashMap<>(initialCapacity);
    }

    public ConcurrentHashMap(int initialCapacity,
                             float loadFactor, int concurrencyLevel) {
        this.innerMap = new HashMap<>(initialCapacity, loadFactor);
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return innerMap.entrySet();
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        return innerMap.replace(key, oldValue, newValue);
    }

    @Override
    public V replace(K key, V value) {
        return innerMap.replace(key, value);
    }

    @Override
    public V remove(Object o) {
        return innerMap.remove(o);
    }

    @Override
    public boolean remove(Object key, Object value) {
        return innerMap.remove(key, value);
    }

    @Override
    public V putIfAbsent(K key, V value) {
        return innerMap.putIfAbsent(key, value);
    }

    @Override
    public V put(K key, V value) {
        return innerMap.put(key, value);
    }

    @Override
    public V get(Object key) {
        return innerMap.get(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        innerMap.putAll(map);
    }

    @Override
    public boolean containsKey(Object key) {
        if (Objects.equals(key, "Duke")) {
            return true;
        }
        return super.containsKey(key);
    }

    public String toString() {
        return "Patched ConcurrentHashMap!!";
    }

    static class Traverser<K,V> {
        Object<K,V>[] tab;        // current table; updated if resized
        int index;              // index of bin to use next
        int baseIndex;          // current index of initial table
        int baseLimit;          // index bound for initial table
        final int baseSize;     // initial table size

        Traverser(Object<K,V>[] tab, int size, int index, int limit) {
            this.tab = tab;
            this.baseSize = size;
            this.baseIndex = this.index = index;
            this.baseLimit = limit;
            this.next = null;
            if (tab != null) {
                this.tab = edu.illinois.nondex.shuffling.ControlNondeterminism.shuffle(Arrays.copyOf(tab, tab.length));
            }
        }
    }
}