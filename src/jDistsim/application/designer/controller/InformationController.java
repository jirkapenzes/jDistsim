package jDistsim.application.designer.controller;

import jDistsim.application.designer.MainFrame;
import jDistsim.application.designer.model.InformationModel;
import jDistsim.application.designer.view.InformationView;
import jDistsim.ui.control.button.ImageButton;
import jDistsim.ui.panel.LogTabPanel;
import jDistsim.ui.panel.listener.LogTabListener;
import jDistsim.utils.logging.Logger;
import jDistsim.utils.pattern.mvc.AbstractController;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseEvent;

/**
 * Author: Jirka Pénzeš
 * Date: 15.2.13
 * Time: 17:58
 */
public class InformationController extends AbstractController<InformationModel> {

    private InformationView view;
    private LogTabListener logTabLogic;

    public InformationController(MainFrame mainFrame, InformationModel model) {
        super(mainFrame, model);
        view = getMainFrame().getView(InformationView.class);

        initialize();
    }

    private void initialize() {
        logTabLogic = new LogTabLogic();

        view.setLogTabListener(logTabLogic);
    }

    private class LogTabLogic implements LogTabListener {

        private LogTabPanel logTabPanel;

        public LogTabLogic() {
            logTabPanel = view.getContentPane().getLogTabPanel();

            if (getModel().isLogPanelScrollToEnd())
                logTabPanel.getControlPanel().getScrollToEndButton().setActive(true);
        }


        @Override
        public void onTrashButtonClick(Object sender, MouseEvent mouseEvent) {
            logTabPanel.getTextAreaPanel().getTextArea().setText("");
            Logger.log("Clear application log output textarea");
        }

        @Override
        public void onWordWrapButtonClick(Object sender, MouseEvent mouseEvent) {
            ImageButton imageButton = logTabPanel.getControlPanel().getWordWrapButton();
            imageButton.setActive(!imageButton.isActive());
            logTabPanel.getTextAreaPanel().setWordWrap(imageButton.isActive());
            getModel().setLogPanelWordWrap(imageButton.isActive());

            Logger.log("Set word wrapping on application textarea: " + imageButton.isActive());
        }

        @Override
        public void onScrollToEndButtonClick(Object sender, MouseEvent mouseEvent) {
            ImageButton imageButton = logTabPanel.getControlPanel().getScrollToEndButton();
            imageButton.setActive(!imageButton.isActive());
            logTabPanel.getTextAreaPanel().setAutoCaretPosition(imageButton.isActive());
            getModel().setLogPanelScrollToEnd(imageButton.isActive());

            Logger.log("Set scroll to end on application textarea: " + imageButton.isActive());
        }

        @Override
        public void onCopyToClipboardButtonClick(Object sender, MouseEvent mouseEvent) {
            String logText = logTabPanel.getTextAreaPanel().getTextArea().getText();
            StringSelection selection = new StringSelection(logText);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);

            Logger.log("Log from textarea copy to clipboard");
        }
    }
}
