package jDistsim.application.designer.common;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

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

    @Override
    public JFileChooser buildFileChooser(String title, String approveButtonText) {
        return buildFileChooser(title, approveButtonText, new FileNameExtensionFilter("jDistsim model (*.jdsim)", "jdsim"));
    }

    @Override
    public JFileChooser buildFileChooser(String title, String approveButtonText, FileFilter fileFilter) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setDialogTitle(title);
        fileChooser.setFileHidingEnabled(true);
        fileChooser.setApproveButtonText(approveButtonText);
        fileChooser.addChoosableFileFilter(fileFilter);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(fileFilter);
        return fileChooser;
    }
}
