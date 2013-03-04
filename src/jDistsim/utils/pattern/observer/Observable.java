package jDistsim.utils.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 28.9.12
 * Time: 15:14
 */
public class Observable implements IObservable {

    private List<IObserver> observers;
    private boolean notify;

    public Observable() {
        observers = new ArrayList<IObserver>();
        notify = true;
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
        if (notify)
            notifyObservers(null);
    }

    public void notifyObservers(Object argument) {
        if (notify)
            for (IObserver observer : observers) {
                observer.update(this, argument);
            }
    }

    public void stopNotify() {
        notify = false;
    }

    public void startNotify() {
        notify = true;
    }
}
