package jDistsim.utils.pattern.observer;

/**
 * Author: Jirka Pénzeš
 * Date: 1.3.13
 * Time: 16:02
 */
public interface IObservable {


    public void addObserver(IObserver observer);

    public void removeObserver(IObserver observer);

    public int countObservers();

    public void notifyObservers();

    public void notifyObservers(Object argument);
}
