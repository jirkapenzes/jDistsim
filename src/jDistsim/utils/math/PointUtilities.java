package jDistsim.utils.math;

/**
 * Author: Jirka Pénzeš
 * Date: 29.9.12
 * Time: 22:52
 */
public final class PointUtilities {

    public static int byPercentageOnLine(int percent, int lineWidth) {
        return (percent * lineWidth) / 100;
    }
}
