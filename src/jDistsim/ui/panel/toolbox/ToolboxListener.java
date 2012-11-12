package jDistsim.ui.panel.toolbox;

import jDistsim.application.designer.model.ToolboxModelItem;

/**
 * Author: Jirka Pénzeš
 * Date: 9.11.12
 * Time: 21:21
 */
public interface ToolboxListener {

    public void componentSelected(ToolboxModelItem modelItem);

    public void componentUnselected();

    void componentPressed(ToolboxModelItem toolboxModelItem);

    void componentReleased(ToolboxModelItem toolboxModelItem);
}
