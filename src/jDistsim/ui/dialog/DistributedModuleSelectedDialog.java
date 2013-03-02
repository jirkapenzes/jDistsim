package jDistsim.ui.dialog;

import jDistsim.application.designer.common.Application;
import jDistsim.core.simulation.distributed.DistributedModelDefinition;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 2.3.13
 * Time: 21:13
 */
public class DistributedModuleSelectedDialog extends BaseDialog {

    private JComboBox comboBox;

    public DistributedModuleSelectedDialog(JFrame parent, String title) {
        super(parent, title);
    }

    @Override
    protected void initializeUI() {
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
        Iterable<String> models = Application.global().getDistributedModels().keys();
        for (String modelName : models) {
            comboBoxModel.addElement(modelName);
        }
        comboBox.setModel(comboBoxModel);
        comboBox.insertItemAt("Select model", 0);
        comboBox.setSelectedIndex(0);
    }

    @Override
    protected void buildWindowBody() {
        comboBox = getComponentFactory().makeComboBox();

        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        build(getComponentFactory().makeLabel("Select distributed model"));
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(0, 0, 8, 5);
        build(comboBox);
        setSize(getWidth(), 140);
    }

    @Override
    protected boolean okButtonLogic() {
        return true;
    }

    public DistributedModelDefinition getModelDefinition() {
        String modelName = comboBox.getSelectedItem().toString();
        if (Application.global().getDistributedModels().containsKey(modelName)) {
            return Application.global().getDistributedModels().get(modelName);
        }
        return null;
    }
}
