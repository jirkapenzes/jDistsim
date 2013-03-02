package jDistsim.application.designer.controller.tabLogic;

import jDistsim.application.designer.controller.InformationController;
import jDistsim.core.simulation.simulator.Writer;
import jDistsim.ui.panel.listener.OutputTabListener;

import javax.swing.*;

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
        JTextArea textArea = controller.getModel().getOutputPanelTextArea();
        textArea.append(text + "\n");

        if (!text.contains("-----"))
            textArea.setCaretPosition(textArea.getDocument().getLength());

    }

    @Override
    public void clear() {
        controller.getModel().getOutputPanelTextArea().setText("");
    }
}

