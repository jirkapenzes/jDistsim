package jDistsim.ui.panel.listener;

import java.awt.event.MouseEvent;

/**
 * Author: Jirka Pénzeš
 * Date: 8.2.13
 * Time: 22:38
 */
public interface ModulesViewListener {

    void onExpandButtonClick(MouseEvent mouseEvent, Object sender);

    void onCollapseButtonClick(MouseEvent mouseEvent, Object sender);

    void onTreeViewSelected(MouseEvent mouseEvent, Object sender);

    void onListViewSelected(MouseEvent mouseEvent, Object sender);
}
