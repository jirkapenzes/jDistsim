package jDistsim.designer.ui.panel;

import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 24.9.12
 * Time: 22:38
 */
public class ToolboxPanel extends InternalFramePanel {

    public ToolboxPanel() {
        super("Toolbox");
        setMinimumSize(new Dimension(100, 100));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 100);
    }

}
