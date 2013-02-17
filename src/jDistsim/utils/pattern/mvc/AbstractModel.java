package jDistsim.utils.pattern.mvc;

import jDistsim.utils.pattern.observer.Observable;

/**
 * Author: Jirka Pénzeš
 * Date: 28.10.12
 * Time: 0:20
 */
public abstract class AbstractModel extends Observable {

    private final AbstractFrame mainFrame;

    protected AbstractModel(AbstractFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public abstract void initialize();

    protected AbstractFrame getMainFrame() {
        return mainFrame;
    }

}
