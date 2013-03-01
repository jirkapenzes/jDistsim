package jDistsim.utils.common;

/**
 * Author: Jirka Pénzeš
 * Date: 22.2.13
 * Time: 16:54
 */
public class Counter {

    private int currentValue;

    public Counter() {
        this(0);
    }

    public Counter(int initialValue) {
        currentValue = initialValue;
    }

    public int nextValue() {
        return ++currentValue;
    }

    public int getCurrentValue() {
        return currentValue;
    }
}
