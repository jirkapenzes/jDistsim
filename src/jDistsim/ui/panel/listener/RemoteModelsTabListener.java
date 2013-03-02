package jDistsim.ui.panel.listener;

import jDistsim.utils.ui.IPanelListener;

import java.awt.event.MouseEvent;

/**
 * Author: Jirka Pénzeš
 * Date: 2.3.13
 * Time: 14:43
 */
public interface RemoteModelsTabListener extends IPanelListener {

    void onOpenAddDialogButtonClick(MouseEvent mouseEvent, Object sender);

    void onOpenEditDialogButtonClick(MouseEvent mouseEvent, Object sender);

    void onOpenRemoveDialogButtonClick(MouseEvent mouseEvent, Object sender);
}
