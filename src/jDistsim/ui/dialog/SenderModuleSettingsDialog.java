package jDistsim.ui.dialog;

import jDistsim.application.designer.common.Application;
import jDistsim.core.simulation.distributed.DistributedModelDefinition;
import jDistsim.core.simulation.modules.lib.sender.Sender;
import jDistsim.ui.TypeInputValidator;
import jDistsim.ui.control.button.ImageButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;

/**
 * Author: Jirka Pénzeš
 * Date: 7.3.13
 * Time: 12:09
 */
public class SenderModuleSettingsDialog extends BaseModuleSettingsDialog<Sender> {

    private JComboBox modelsComboBox;
    private JTextField entityNameTextField;
    private JLabel remoteAddressInfoLabel;

    public SenderModuleSettingsDialog(JFrame parent, Sender module) {
        super(parent, module);
    }

    @Override
    protected void initializeUI() {
        rebuildComboBox(module.getSettings().getDistributedModelDefinition().getRmiModelName());
        rebuildRemoteAddressLabel(module.getSettings().getDistributedModelDefinition());
        entityNameTextField.setText(module.getSettings().getDistributedEntityKeyName());
    }

    private void rebuildRemoteAddressLabel(DistributedModelDefinition modelDefinition) {
        remoteAddressInfoLabel.setText(modelDefinition.toString());
    }

    private void rebuildComboBox(String selectedModel) {
        modelsComboBox.removeAllItems();
        int selectedIndex = 0;
        Collection<DistributedModelDefinition> distributedModels = Application.global().getDistributedModels().values();
        int index = 0;
        for (DistributedModelDefinition modelDefinition : distributedModels) {
            modelsComboBox.addItem(modelDefinition.getRmiModelName());
            if (selectedModel.equals(modelDefinition.getRmiModelName()))
                selectedIndex = index + 1;
            index++;
        }
        modelsComboBox.insertItemAt("Select model", 0);
        modelsComboBox.setSelectedIndex(selectedIndex);

    }

    @Override
    protected void buildWindowBody() {
        setSize(210, 225);
        entityNameTextField = getComponentFactory().makeTextField(10);
        remoteAddressInfoLabel = getComponentFactory().makeLabel("");
        modelsComboBox = getComponentFactory().makeComboBox();

        constraints.gridwidth = 2;
        build(getComponentFactory().makeLabel("Select target distributed model      "));
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        build(getComponentFactory().makeLabel(""));

        constraints.insets = new Insets(0, 0, 8, 5);
        constraints.gridwidth = 2;
        build(modelsComboBox);
        constraints.gridwidth = 1;
        ImageButton imageButton = getComponentFactory().makeImageButton("system/panels/mt_add.png");
        imageButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                onAddDistributedModelMouseClick();
            }
        });
        build(imageButton);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        build(getComponentFactory().makeLabel(""));

        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        build(getComponentFactory().makeLabel("Set entity distributed name"));

        constraints.insets = new Insets(0, 0, 8, 5);
        constraints.gridwidth = 1;
        build(entityNameTextField);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        build(getComponentFactory().makeLabel(""));

        constraints.insets = new Insets(0, 0, 0, 0);
        JLabel label = getComponentFactory().makeLabel("Current selected configuration:");
        label.setForeground(new Color(120, 120, 120));
        remoteAddressInfoLabel.setForeground(new Color(120, 120, 120));

        build(label);
        build(remoteAddressInfoLabel);
    }

    private void onAddDistributedModelMouseClick() {
        DistributedModelDialog dialog = new DistributedModelDialog((JFrame) getParent());
        dialog.showDialog();
        if (dialog.getDialogResult() == BaseDialog.Result.OK) {
            DistributedModelDefinition modelDefinition = dialog.getDistributedModelDefinition();
            Application.global().getDistributedModels().put(modelDefinition.getRmiModelName(), modelDefinition);
            Application.global().getDistributedModels().notifyObservers();
            rebuildComboBox(modelDefinition.getRmiModelName());
            rebuildRemoteAddressLabel(modelDefinition);
        }
    }

    @Override
    protected boolean doLogic() {
        try {
            TypeInputValidator validator = new TypeInputValidator();
            String selectedModel = validator.validateComboBoxValue(modelsComboBox.getSelectedIndex(), (String) modelsComboBox.getSelectedItem(), "models");
            Collection<DistributedModelDefinition> distributedModels = Application.global().getDistributedModels().values();
            DistributedModelDefinition distributedModelDefinition = null;
            for (DistributedModelDefinition modelDefinition : distributedModels) {
                if (modelDefinition.getRmiModelName().equals(selectedModel)) {
                    distributedModelDefinition = modelDefinition;
                    break;
                }
            }
            validator.validateNull(distributedModelDefinition, "models");

            String entityName = validator.validateString(entityNameTextField.getText(), "entity name");
            entityName = validator.validateSpecialCharacters(entityName, "entity name");

            module.getSettings().setDistributedModelDefinition(distributedModelDefinition);
            module.getSettings().setDistributedEntityKeyName(entityName);
            module.notifyObservers();

            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
