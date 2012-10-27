package jDistsim.application.designer.view;

import jDistsim.ui.panel.WorkSpacePanel;
import jDistsim.utils.pattern.mvc.AbstractFrame;
import jDistsim.utils.pattern.mvc.AbstractView;

/**
 * Author: Jirka Pénzeš
 * Date: 27.10.12
 * Time: 16:58
 */
public class ModelSpaceView extends AbstractView<WorkSpacePanel> {

    public ModelSpaceView(AbstractFrame mainFrame) {
        super(mainFrame);
    }

    @Override
    protected WorkSpacePanel layout() {
        return new WorkSpacePanel();
    }
}
