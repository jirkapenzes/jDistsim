package jDistsim.utils.common;

/**
 * Author: Jirka Pénzeš
 * Date: 16.3.13
 * Time: 17:35
 */
public class ThreadWaiter {

    public static void waitCurrentThreadFor(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        }
    }

}
