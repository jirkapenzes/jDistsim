package jDistsim.application.designer.controller.tabLogic;

import jDistsim.application.designer.controller.InformationController;
import jDistsim.application.designer.view.InformationView;
import jDistsim.ui.control.button.ImageButton;
import jDistsim.ui.panel.listener.LogTabListener;
import jDistsim.ui.panel.tab.LogTabPanel;
import jDistsim.utils.logging.Logger;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseEvent;

/**
 * Author: Jirka Pénzeš
 * Date: 2.3.13
 * Time: 14:34
 */
public class LogTabLogic implements LogTabListener {

    private LogTabPanel logTabPanel;
    private InformationController controller;

    public LogTabLogic(InformationController controller) {
        this.controller = controller;
        logTabPanel = controller.getMainFrame().getView(InformationView.class).getContentPane().getLogTabPanel();
        if (controller.getModel().isLogPanelScrollToEnd())
            logTabPanel.getScrollToEndButton().setActive(true);
    }


    @Override
    public void onTrashButtonClick(Object sender, MouseEvent mouseEvent) {
        logTabPanel.getLogTextArea().getTextArea().setText("");
        Logger.log("Clear application log output textarea");
    }

    @Override
    public void onWordWrapButtonClick(Object sender, MouseEvent mouseEvent) {
        ImageButton imageButton = logTabPanel.getWordWrapButton();
        imageButton.setActive(!imageButton.isActive());
        logTabPanel.getLogTextArea().setWordWrap(imageButton.isActive());
        controller.getModel().setLogPanelWordWrap(imageButton.isActive());
        Logger.log("Set word wrapping on application textarea: " + imageButton.isActive());
    }

    @Override
    public void onScrollToEndButtonClick(Object sender, MouseEvent mouseEvent) {
        ImageButton imageButton = logTabPanel.getScrollToEndButton();
        imageButton.setActive(!imageButton.isActive());
        logTabPanel.getLogTextArea().setAutoCaretPosition(imageButton.isActive());
        controller.getModel().setLogPanelScrollToEnd(imageButton.isActive());
        Logger.log("Set scroll to end on application textarea: " + imageButton.isActive());
    }

    @Override
    public void onCopyToClipboardButtonClick(Object sender, MouseEvent mouseEvent) {
        String logText = logTabPanel.getLogTextArea().getTextArea().getText();
        StringSelection selection = new StringSelection(logText);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);

        Logger.log("Log from textarea copy to clipboard");
    }
}