package jDistsim.ui.dialog;

import jDistsim.core.simulation.modules.lib.receiver.Receiver;
import jDistsim.ui.TypeInputValidator;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 8.3.13
 * Time: 0:15
 */
public class ReceiverModuleSettingsDialog extends BaseModuleSettingsDialog<Receiver> {

    private JTextField authorizedEntityTextField;

    public ReceiverModuleSettingsDialog(JFrame parent, Receiver module) {
        super(parent, module);
    }

    @Override
    protected void initializeUI() {
        authorizedEntityTextField.setText(module.getAuthorizedEntityName());
    }

    @Override
    protected void buildWindowBody() {
        setSize(200, 140);
        authorizedEntityTextField = getComponentFactory().makeTextField();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        build(getComponentFactory().makeLabel("Set authorized entity name"));
        constraints.insets = new Insets(0, 0, 8, 5);
        build(authorizedEntityTextField);
    }

    @Override
    protected boolean doLogic() {
        try {
            TypeInputValidator validator = new TypeInputValidator();
            String authorizedEntityName = validator.validateString(authorizedEntityTextField.getText(), "entity name");
            module.setAuthorizedEntityName(authorizedEntityName);
            module.notifyObservers();
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
