package jDistsim.application.designer.common;

import javax.swing.*;

/**
 * Author: Jirka Pénzeš
 * Date: 22.2.13
 * Time: 0:42
 */
public class DialogBuilder implements IDialogBuilder {

    private JFrame frame;

    public DialogBuilder(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void buildErrorDialog(String message) {
        JOptionPane.showMessageDialog(frame, message, "jDistSim error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public int buildQuestionDialog(String message) {
        return JOptionPane.showConfirmDialog(frame, message, "jDistSim question", JOptionPane.YES_NO_OPTION);
    }
}
