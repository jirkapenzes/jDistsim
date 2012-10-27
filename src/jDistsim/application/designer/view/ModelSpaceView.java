package jDistsim.application.designer.view;

import jDistsim.ui.panel.ModelSpacePanel;
import jDistsim.utils.pattern.mvc.AbstractFrame;
import jDistsim.utils.pattern.mvc.AbstractView;

/**
 * Author: Jirka Pénzeš
 * Date: 27.10.12
 * Time: 23:40
 */
public class ModelSpaceView extends AbstractView<ModelSpacePanel> {

    public ModelSpaceView(AbstractFrame mainFrame) {
        super(mainFrame);
    }

    @Override
    protected ModelSpacePanel layout() {
        return new ModelSpacePanel();
    }
}
