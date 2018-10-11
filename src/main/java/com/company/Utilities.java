package com.company;

import java.util.*;

public class Utilities {
    //Method stolen from https://stackoverflow.com/questions/109383/sort-a-mapkey-value-by-values
    public static <K, V extends Comparable<? super V>> List<K> getKeysSortedByValue(Map<K, V> map) {
        final int size = map.size();
        final List<Map.Entry<K, V>> list = new ArrayList<Map.Entry<K, V>>(size);
        list.addAll(map.entrySet());
        final ValueComparator<V> cmp = new ValueComparator<V>();
        Collections.sort(list, cmp);
        final List<K> keys = new ArrayList<K>(size);
        for (int i = 0; i < size; i++) {
            keys.set(i, list.get(i).getKey());
        }
        return keys;
    }

    private static final class ValueComparator<V extends Comparable<? super V>>
            implements Comparator<Map.Entry<?, V>> {
        public int compare(Map.Entry<?, V> o1, Map.Entry<?, V> o2) {
            return o1.getValue().compareTo(o2.getValue());
        }
    }
}
