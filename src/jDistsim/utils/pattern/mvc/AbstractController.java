package jDistsim.utils.pattern.mvc;

/**
 * Author: Jirka Pénzeš
 * Date: 26.10.12
 * Time: 17:32
 */
public class AbstractController {

    private final AbstractFrame mainFrame;

    public AbstractController(AbstractFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    protected AbstractFrame getMainFrame() {
        return mainFrame;
    }
}
