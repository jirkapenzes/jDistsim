package jDistsim.utils.common;

/**
 * Author: Jirka Pénzeš
 * Date: 24.3.13
 * Time: 0:32
 */
public class Crate<T1, T2> {

    private T1 value1;
    private T2 value2;

    public Crate(T1 value1, T2 value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public T1 getValue1() {
        return value1;
    }

    public T2 getValue2() {
        return value2;
    }
}
