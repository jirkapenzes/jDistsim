package jDistsim.utils.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 21.3.13
 * Time: 21:56
 */
public class SynchronizedPriorityQueue<T extends Comparable<T>> implements Iterable<T> {

    private List<T> list;
    private boolean organized;
    private final Object lock = new Object();
    private final int initialCapacity = 20;

    public SynchronizedPriorityQueue() {
        list = new ArrayList<>(initialCapacity);
        organized = true;
    }

    public void put(T item) {
        synchronized (lock) {
            organized = false;
            list.add(item);
        }
    }

    public T poll() {
        sort();
        if (list.isEmpty())
            return null;

        return list.remove(0);
    }

    public T peek() {
        if (list.isEmpty())
            return null;

        sort();
        return list.get(0);
    }

    public T get(int index) {
        return list.get(index);
    }

    public void remove(T item) {
        synchronized (lock) {
            list.remove(item);
        }
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public void clear() {
        synchronized (lock) {
            organized = false;
            list.clear();
        }
    }


    private void sort() {
        if (!organized) {
            Collections.sort(list);
            organized = true;
        }
    }

    @Override
    public Iterator<T> iterator() {
        sort();
        return list.iterator();
    }
}