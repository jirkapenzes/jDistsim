package jDistsim.utils.collection.observable;

import jDistsim.utils.collection.ReadOnlyList;
import jDistsim.utils.pattern.observer.IObserver;
import jDistsim.utils.pattern.observer.Observable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 13.2.13
 * Time: 1:03
 */
public class ObservableList<Item extends Observable> extends Observable implements IObserver, Iterable<Item>, ReadOnlyList<Item> {

    private List<Item> itemList;

    public ObservableList() {
        itemList = new ArrayList<>();
    }

    public ObservableList(IObserver observer) {
        itemList = new ArrayList<>();
        addObserver(observer);
    }

    public int size() {
        return itemList.size();
    }

    public void add(Item item) {
        itemList.add(item);
        item.addObserver(this);
        notifyObservers("add");
    }

    public void add(int index, Item item) {
        itemList.add(index, item);
        item.addObserver(this);
        notifyObservers("add");
    }

    public boolean remove(Item item) {
        boolean result = itemList.remove(item);
        item.removeObserver(this);
        notifyObservers("remove");
        return result;
    }

    public Item remove(int index) {
        Item oldItem = itemList.remove(index);
        oldItem.removeObserver(this);
        notifyObservers("remove");
        return oldItem;
    }

    public Item get(int index) {
        return itemList.get(index);
    }

    public void clear() {
        itemList.clear();
        notifyObservers("clear");
    }

    @Override
    public Iterator<Item> iterator() {
        return itemList.iterator();
    }

    @Override
    public void update(Observable observable, Object arguments) {
        notifyObservers("itemChanges");
    }
}
