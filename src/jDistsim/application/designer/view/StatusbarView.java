package jDistsim.application.designer.view;

import jDistsim.ui.StatusBar;
import jDistsim.utils.pattern.mvc.AbstractFrame;
import jDistsim.utils.pattern.mvc.AbstractView;

/**
 * Author: Jirka Pénzeš
 * Date: 27.10.12
 * Time: 23:23
 */
public class StatusBarView extends AbstractView<StatusBar> {

    public StatusBarView(AbstractFrame mainFrame) {
        super(mainFrame);
    }

    @Override
    protected StatusBar layout() {
        return new StatusBar();
    }
}
