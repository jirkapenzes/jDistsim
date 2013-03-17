package jDistsim.ui.panel.listener;

import jDistsim.ui.control.LogTextArea;
import jDistsim.utils.ui.IPanelListener;

import java.awt.event.MouseEvent;

/**
 * Author: Jirka Pénzeš
 * Date: 21.2.13
 * Time: 19:30
 */
public interface OutputTabListener extends IPanelListener {

    public void onTrashButtonClick(Object sender, MouseEvent mouseEvent);

    public void onScrollToEndButtonClick(Object sender, LogTextArea logTextArea, MouseEvent mouseEvent);

    public void onCopyToClipboardButtonClick(Object sender, MouseEvent mouseEvent);

    public void onSimulatorOutputDialogOpenButtonClick(Object sender, MouseEvent mouseEvent);

    public void onLocalEnvironmentButtonClick(Object sender, MouseEvent mouseEvent);

    public void onModulesEnvironmentButtonClick(Object sender, MouseEvent mouseEvent);

    public void onNetworkEnvironmentButtonClick(Object sender, MouseEvent mouseEvent);

    public void onMessagesEnvironmentButtonClick(Object sender, MouseEvent mouseEvent);
}
