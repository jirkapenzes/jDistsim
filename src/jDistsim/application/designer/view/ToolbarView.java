package jDistsim.application.designer.view;

import jDistsim.ui.panel.ToolbarPanel;
import jDistsim.ui.panel.listener.ToolbarListener;
import jDistsim.utils.pattern.mvc.AbstractFrame;
import jDistsim.utils.pattern.mvc.AbstractView;

/**
 * Author: Jirka Pénzeš
 * Date: 27.10.12
 * Time: 13:11
 */
public class ToolbarView extends AbstractView<ToolbarPanel> {

    private ToolbarPanel toolbarPanel;
    private ToolbarListener toolbarListener;

    public ToolbarView(AbstractFrame mainFrame) {
        super(mainFrame);
    }

    @Override
    protected ToolbarPanel layout() {
        toolbarPanel = new ToolbarPanel();
        toolbarPanel.setListener(toolbarListener);
        return toolbarPanel;
    }

    public void setToolbarListener(ToolbarListener toolbarListener) {
        this.toolbarListener = toolbarListener;
    }
}
