package jDistsim.application.designer.view;

import jDistsim.ui.panel.toolbox.ToolboxListener;
import jDistsim.ui.panel.toolbox.ToolboxPanel;
import jDistsim.utils.pattern.mvc.AbstractFrame;
import jDistsim.utils.pattern.mvc.AbstractView;

/**
 * Author: Jirka Pénzeš
 * Date: 27.10.12
 * Time: 13:09
 */
public class ToolboxView extends AbstractView<ToolboxPanel> {

    public ToolboxView(AbstractFrame mainFrame) {
        super(mainFrame);
    }

    @Override
    protected ToolboxPanel layout() {
        return new ToolboxPanel();
    }

    public void addToolboxListener(ToolboxListener toolboxListener) {
        getContentPane().addToolboxListener(toolboxListener);
    }
}
