package jDistsim.application.designer.view;

import jDistsim.ui.panel.ToolbarPanel;
import jDistsim.utils.pattern.mvc.AbstractFrame;
import jDistsim.utils.pattern.mvc.AbstractView;

import java.awt.event.MouseListener;

/**
 * Author: Jirka Pénzeš
 * Date: 27.10.12
 * Time: 13:11
 */
public class ToolbarView extends AbstractView<ToolbarPanel> {

    private ToolbarPanel toolbarPanel;

    public ToolbarView(AbstractFrame mainFrame) {
        super(mainFrame);
        toolbarPanel = new ToolbarPanel();
    }

    @Override
    protected ToolbarPanel layout() {
        return toolbarPanel;
    }

    public void addRelationsMouseListener(MouseListener listener) {
        toolbarPanel.getRelationsButton().addMouseListener(listener);
    }
}
