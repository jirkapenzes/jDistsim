package jDistsim.ui.dialog;

import jDistsim.core.simulation.modules.lib.delay.DelayModule;
import jDistsim.ui.TypeInputValidator;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 27.2.13
 * Time: 1:48
 */
public class DelayModuleSettingsDialog extends BaseModuleSettingsDialog<DelayModule> {

    private JTextField delayTimeTextField;

    public DelayModuleSettingsDialog(JFrame parent, DelayModule module) {
        super(parent, module);
        initializeUI();
    }

    protected void initializeUI() {
        delayTimeTextField.setText(String.valueOf(module.getDelayTime()));
    }

    @Override
    protected void buildWindowBody() {
        delayTimeTextField = getComponentFactory().makeTextField();

        constraints.gridwidth = GridBagConstraints.REMAINDER;
        build(getComponentFactory().makeLabel("Delay time"));
        constraints.insets = new Insets(0, 0, 8, 5);
        constraints.gridwidth = 1;
        build(delayTimeTextField);
        setSize(new Dimension(getWidth(), 140));
    }

    @Override
    protected void okButtonLogic() {
        try {
            TypeInputValidator validator = new TypeInputValidator();

            int delayTime = validator.validateInteger(delayTimeTextField.getText(), "Delay time");
            module.setDelayTime(delayTime);
        } catch (Exception exception) {

        }
    }
}
