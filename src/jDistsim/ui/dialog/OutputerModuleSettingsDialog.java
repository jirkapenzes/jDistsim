package jDistsim.ui.dialog;

import jDistsim.ServiceLocator;
import jDistsim.application.designer.common.IDialogBuilder;
import jDistsim.core.simulation.modules.lib.outputer.Outputer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Author: Jirka Pénzeš
 * Date: 13.5.13
 * Time: 17:55
 */
public class OutputerModuleSettingsDialog extends BaseModuleSettingsDialog<Outputer> {

    private JLabel filePathLabel;

    public OutputerModuleSettingsDialog(JFrame parent, Outputer module) {
        super(parent, module);
    }

    @Override
    protected void initializeUI() {
        filePathLabel.setText(module.getSettings().getFilePath());
    }

    @Override
    protected boolean doLogic() {
        try {
            module.getSettings().setFilePath(filePathLabel.getText());
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    protected void buildWindowBody() {
        filePathLabel = getComponentFactory().makeLabel("");

        constraints.gridwidth = 1;
        build(getComponentFactory().makeLabel("File destination: "));
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        build(filePathLabel);
        constraints.gridwidth = 1;
        constraints.insets = new Insets(0, 0, 8, 5);

        JButton button = getComponentFactory().makeButton("Change");
        build(button);
        setSize(new Dimension(360, 140));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                IDialogBuilder dialogBuilder = ServiceLocator.getInstance().get(IDialogBuilder.class);
                JFileChooser fileChooser = dialogBuilder.buildFileChooser("Select file destination", "Make file", new FileNameExtensionFilter("Text files (*.txt)", "txt"));
                int returnVal = fileChooser.showSaveDialog(OutputerModuleSettingsDialog.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    String fullPath = fileChooser.getSelectedFile().getPath();
                    filePathLabel.setText(fullPath);
                }
            }
        });

    }
}
