package jDistsim.application.designer.controller.tabLogic;

import jDistsim.application.designer.controller.InformationController;
import jDistsim.core.simulation.simulator.Writer;
import jDistsim.ui.control.LogTextArea;
import jDistsim.ui.control.button.ImageButton;
import jDistsim.ui.dialog.SimulatorOutputDialog;
import jDistsim.ui.panel.listener.OutputTabListener;
import jDistsim.utils.logging.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseEvent;

/**
 * Author: Jirka Pénzeš
 * Date: 2.3.13
 * Time: 14:39
 */
public class OutputTabLogic implements OutputTabListener, Writer {

    private final InformationController controller;

    public OutputTabLogic(InformationController controller) {
        this.controller = controller;
    }

    @Override
    public void write(String text) {
        writeToTextArea(controller.getModel().getOutputPanelTextArea(), text);
        writeToTextArea(controller.getModel().getOutputDialogTextArea(), text);
    }

    private void writeToTextArea(JTextArea textArea, String text) {
        textArea.append(text + "\n");

        if (!text.contains("-----"))
            textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    @Override
    public void clear() {
        controller.getModel().getOutputPanelTextArea().setText("");
        controller.getModel().getOutputDialogTextArea().setText("");
    }

    @Override
    public void onTrashButtonClick(Object sender, MouseEvent mouseEvent) {
        controller.getModel().getOutputDialogTextArea().setText(new String());
        controller.getModel().getOutputPanelTextArea().setText(new String());
        Logger.log("Clear simulator UI output");
    }

    @Override
    public void onScrollToEndButtonClick(Object sender, LogTextArea logTextArea, MouseEvent mouseEvent) {
        ImageButton imageButton = (ImageButton) sender;
        imageButton.setActive(!imageButton.isActive());
        logTextArea.setAutoCaretPosition(imageButton.isActive());
        controller.getModel().setLogPanelScrollToEnd(imageButton.isActive());
        Logger.log("Set scroll to end on simulator output textarea: " + imageButton.isActive());
    }

    @Override
    public void onCopyToClipboardButtonClick(Object sender, MouseEvent mouseEvent) {
        String logText = controller.getModel().getOutputPanelTextArea().getText();
        StringSelection selection = new StringSelection(logText);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
        Logger.log("Log from simulator output copy to clipboard");
    }

    @Override
    public void onSimulatorOutputDialogOpenButtonClick(Object sender, MouseEvent mouseEvent) {
        SimulatorOutputDialog dialog = new SimulatorOutputDialog(controller.getMainFrame().getFrame(), this, controller.getModel().getOutputDialogTextArea());
        dialog.showDialog(false);
    }
}

