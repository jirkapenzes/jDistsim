package jDistsim.application.designer.controller;

import jDistsim.application.designer.MainFrame;
import jDistsim.application.designer.model.InformationModel;
import jDistsim.application.designer.view.InformationView;
import jDistsim.core.simulation.simulator.Writer;
import jDistsim.ui.control.button.ImageButton;
import jDistsim.ui.panel.listener.LogTabListener;
import jDistsim.ui.panel.listener.OutputTabListener;
import jDistsim.ui.panel.tab.LogTabPanel;
import jDistsim.utils.logging.Logger;
import jDistsim.utils.pattern.mvc.AbstractController;

import javax.swing.*;
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
    private OutputTabLogic outputTabLogic;

    public InformationController(MainFrame mainFrame, InformationModel model) {
        super(mainFrame, model);
        view = getMainFrame().getView(InformationView.class);

        initialize();
    }

    private void initialize() {
        logTabLogic = new LogTabLogic();
        outputTabLogic = new OutputTabLogic();

        view.setLogTabListener(logTabLogic);
        view.setOutputListener(outputTabLogic);
    }

    public Writer makeSimulatorWriter() {
        return outputTabLogic;
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

    private class OutputTabLogic implements OutputTabListener, Writer {

        public OutputTabLogic() {

        }

        @Override
        public void write(String text) {
            JTextArea textArea = getModel().getOutputPanelTextArea();
            textArea.append(text + "\n");

            if (!text.contains("-----"))
                textArea.setCaretPosition(textArea.getDocument().getLength());

        }

        @Override
        public void clear() {
            getModel().getOutputPanelTextArea().setText("");
        }
    }
}
