package jDistsim.application.designer.common;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

/**
 * Author: Jirka Pénzeš
 * Date: 22.2.13
 * Time: 0:45
 */
public interface IDialogBuilder {

    void buildErrorDialog(String message);

    int buildQuestionDialog(String message);

    JFileChooser buildFileChooser(String title, String approveButtonText);

    JFileChooser buildFileChooser(String title, String approveButtonText, FileFilter fileFilter);
}
