package jDistsim.ui.panel.listener;

import jDistsim.utils.ui.IPanelListener;

import java.awt.event.MouseEvent;

/**
 * Author: Jirka Pénzeš
 * Date: 21.2.13
 * Time: 20:26
 */
public interface ToolbarListener extends IPanelListener {

    void onSimulationStartButtonClick(MouseEvent mouseEvent, Object sender);

    void onSimulationStopButtonClick(MouseEvent mouseEvent, Object sender);

    void onModelSaveButtonClick(MouseEvent mouseEvent, Object sender);
}
