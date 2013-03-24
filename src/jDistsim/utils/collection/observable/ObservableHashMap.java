package jDistsim.utils.collection.observable;

import jDistsim.utils.pattern.observer.IObservable;
import jDistsim.utils.pattern.observer.IObserver;
import jDistsim.utils.pattern.observer.Observable;

import java.util.Collection;
import java.util.HashMap;

/**
 * Author: Jirka Pénzeš
 * Date: 10.2.13
 * Time: 13:37
 */
public class ObservableHashMap<Key, Value extends IObservable> extends Observable implements IObserver {

    private HashMap<Key, Value> hashMap;

    public ObservableHashMap() {
        hashMap = new HashMap<>();
    }

    public void put(Key key, Value value) {
        hashMap.put(key, value);
        value.addObserver(this);
        setChanged();
    }

    public Value get(Key key) {
        return hashMap.get(key);
    }

    public Value remove(Key key) {
        Value value = hashMap.remove(key);
        value.removeObserver(this);
        setChanged();
        return value;
    }

    public boolean containsKey(Key key) {
        return hashMap.containsKey(key);
    }

    public Collection<Value> values() {
        return hashMap.values();
    }

    public Iterable<Key> keys() {
        return hashMap.keySet();
    }

    public int size() {
        return hashMap.size();
    }

    public boolean isEmpty() {
        return hashMap.isEmpty();
    }

    public void clear() {
        hashMap.clear();
        setChanged();
    }

    @Override
    public void update(Observable observable, Object arguments) {
        setChanged();
        notifyObservers("itemChanges");
    }
}
