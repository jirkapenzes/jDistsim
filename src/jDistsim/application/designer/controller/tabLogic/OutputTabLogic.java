package jDistsim.application.designer.controller.tabLogic;

import jDistsim.application.designer.controller.InformationController;
import jDistsim.core.simulation.simulator.Writer;
import jDistsim.ui.dialog.SimulatorOutputDialog;
import jDistsim.ui.panel.listener.OutputTabListener;

import javax.swing.*;
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
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onWordWrapButtonClick(Object sender, MouseEvent mouseEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onScrollToEndButtonClick(Object sender, MouseEvent mouseEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onCopyToClipboardButtonClick(Object sender, MouseEvent mouseEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onSimulatorOutputDialogOpenButtonClick(Object sender, MouseEvent mouseEvent) {
        SimulatorOutputDialog dialog = new SimulatorOutputDialog(controller.getMainFrame().getFrame(), this, controller.getModel().getOutputDialogTextArea());
        dialog.showDialog(false);
    }
}

