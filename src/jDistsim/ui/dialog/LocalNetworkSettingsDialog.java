package jDistsim.ui.dialog;

import jDistsim.application.designer.common.Application;
import jDistsim.ui.TypeInputValidator;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 8.3.13
 * Time: 17:36
 */
public class LocalNetworkSettingsDialog extends BaseDialog {

    private JTextField modelNameTextField;
    private JTextField portTextField;

    public LocalNetworkSettingsDialog(JFrame parent, String title) {
        super(parent, title);
    }

    @Override
    protected void initializeUI() {
        modelNameTextField.setText(Application.global().getNetworkSettings().getModelName());
        portTextField.setText(String.valueOf(Application.global().getNetworkSettings().getPort()));
    }

    @Override
    protected void buildWindowBody() {
        setSize(190, 140);
        modelNameTextField = getComponentFactory().makeTextField();
        portTextField = getComponentFactory().makeTextField(5);

        constraints.gridwidth = 1;
        constraints.insets = new Insets(0, 0, 0, 5);
        build(getComponentFactory().makeLabel("Network model name"));
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        build(getComponentFactory().makeLabel("Port"));
        constraints.insets = new Insets(0, 0, 8, 5);
        constraints.gridwidth = 1;
        build(modelNameTextField);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        build(portTextField);
    }

    @Override
    protected boolean okButtonLogic() {
        try {
            TypeInputValidator validator = new TypeInputValidator();
            String modelName = validator.validateString(modelNameTextField.getText(), "model name");
            modelName = validator.validateSpecialCharacters(modelName, "model name");
            int port = validator.validateInteger(portTextField.getText(), "port");

            Application.global().getNetworkSettings().setModelName(modelName);
            Application.global().getNetworkSettings().setPort(port);
            Application.global().getNetworkSettings().notifyObservers();
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
