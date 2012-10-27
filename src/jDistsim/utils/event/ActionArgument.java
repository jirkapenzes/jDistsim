package jDistsim.utils.event;

/**
 * Author: Jirka Pénzeš
 * Date: 28.10.12
 * Time: 1:30
 */
public class ActionArgument<TSource> {

    private TSource source;

    public ActionArgument(TSource source) {
        this.source = source;
    }

    public TSource getSource() {
        return source;
    }
}
