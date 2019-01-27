package vk.dev.demorest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * LRUCache.
 *
 * @author vladimir_kuragin
 */
public class LRUCache<K, V> {

    private static final int DEFAULT_CAPACITY = 10;

    private final int capacity;

    private LinkedHashMap<K, V> cache = new LinkedHashMap<>();

    public LRUCache() {
        this(DEFAULT_CAPACITY);
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public V get(K key) {
        if (cache.containsKey(key)) {

            // remove and append to the tail
            // so that it's now the most recently used item
            V value = cache.remove(key);
            cache.put(key, value);
            return value;
        }
        return null;
    }

    public void add(K key, V value) {
        cache.put(key, value);
        if (cache.size() > capacity) {
            Iterator<Map.Entry<K, V>> iterator = cache.entrySet().iterator();
            iterator.next();
            iterator.remove();
        }
    }

    public void clear() {
        cache.clear();
    }

    public List<V> values() {
        return new ArrayList<>(cache.values());
    }

    public Set<K> keys() {
        return new LinkedHashSet<>(cache.keySet());
    }
}
