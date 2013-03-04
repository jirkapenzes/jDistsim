package jDistsim.ui.dialog;

import jDistsim.core.simulation.simulator.entity.Attribute;
import jDistsim.ui.TypeInputValidator;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 4.3.13
 * Time: 0:49
 */
public class AttributeSettingsDialog extends BaseDialog {

    private Attribute attribute;
    private JTextField nameTextField;
    private JTextField valueTextField;

    public AttributeSettingsDialog(JDialog parent, String title) {
        super(parent, title);
        this.attribute = new Attribute(new String());
    }

    public AttributeSettingsDialog(JFrame parent, String title) {
        super(parent, title);
        this.attribute = new Attribute(new String());
    }

    public AttributeSettingsDialog(JDialog parent, String title, Attribute attribute) {
        super(parent, title);
        this.attribute = attribute;
    }

    public AttributeSettingsDialog(JFrame parent, String title, Attribute attribute) {
        super(parent, title);
        this.attribute = attribute;
    }

    @Override
    protected void initializeUI() {
        nameTextField.setText(attribute.getName());
        valueTextField.setText(attribute.getValue());
    }

    @Override
    protected void buildWindowBody() {
        setSize(200, 140);
        nameTextField = getComponentFactory().makeTextField();
        valueTextField = getComponentFactory().makeTextField();

        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridwidth = 1;
        build(getComponentFactory().makeLabel("Attribute name"));
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        build(getComponentFactory().makeLabel("Attribute value"));
        constraints.insets = new Insets(0, 0, 8, 4);
        constraints.gridwidth = 1;
        build(nameTextField);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        build(valueTextField);
    }

    @Override
    protected boolean okButtonLogic() {
        try {
            TypeInputValidator validator = new TypeInputValidator();
            String name = validator.validateString(nameTextField.getText(), "name");
            name = validator.validateSpecialCharacters(name, "name");
            String value = validator.validateString(valueTextField.getText(), "value");

            attribute.setName(name);
            attribute.setValue(value);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public Attribute getAttribute() {
        return attribute;
    }
}
