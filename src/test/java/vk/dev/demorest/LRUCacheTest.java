package vk.dev.demorest;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class LRUCacheTest {

    private LRUCache<String, Integer> cache;

    @Before
    public void setUp() {
        cache = new LRUCache<>(2);
        cache.add("1", 1);
    }

    @Test
    public void get() {
        Integer result = cache.get("1");

        assertThat(result, is(1));
    }

    @Test
    public void add() {
        Integer before = cache.get("2");

        cache.add("2", 2);

        Integer after = cache.get("2");

        assertThat(before, nullValue());
        assertThat(after, is(2));
    }

    @Test
    public void clear() {
        cache.clear();

        assertThat(cache.keys().size(), is(0));
        assertThat(cache.values().size(), is(0));
    }

    @Test
    public void values() {
        List<Integer> values = cache.values();

        assertThat(values, hasItems(1));
    }

    @Test
    public void keys() {
        Set<String> keys = cache.keys();

        assertThat(keys, hasItems("1"));
    }

    @Test
    public void shouldAddNewItemsAndRemoveOldOnes() {
        Set<String> before = cache.keys();

        cache.add("2", 2);
        cache.add("3", 3);

        Set<String> after = cache.keys();

        assertThat(before, hasItem("1"));
        assertThat(after, not(hasItem("1")));
        assertThat(after, hasItems("2", "3"));
    }
}