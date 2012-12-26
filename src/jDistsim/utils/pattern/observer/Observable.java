package jDistsim.utils.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 28.9.12
 * Time: 15:14
 */
public class Observable {

    private List<IObserver> observers;

    public Observable() {
        observers = new ArrayList<IObserver>();
    }

    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    public int countObservers() {
        return observers.size();
    }

    public void notifyObservers() {
        notifyObservers(null);
    }

    public void notifyObservers(Object argument) {
        for (IObserver observer : observers) {
            observer.update(this, argument);
        }
    }
}
