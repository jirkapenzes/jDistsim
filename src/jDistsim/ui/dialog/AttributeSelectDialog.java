package jDistsim.ui.dialog;

import jDistsim.core.simulation.simulator.entity.Attribute;
import jDistsim.core.simulation.simulator.entity.AttributeCollection;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 4.3.13
 * Time: 10:57
 */
public class AttributeSelectDialog extends BaseDialog {

    private JComboBox comboBox;
    private AttributeCollection attributes;

    public AttributeSelectDialog(JDialog parent, String title, AttributeCollection attributes) {
        super(parent, title);
        this.attributes = attributes;
    }

    public AttributeSelectDialog(JFrame parent, String title, AttributeCollection attributes) {
        super(parent, title);
        this.attributes = attributes;
    }

    @Override
    protected void initializeUI() {
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
        for (Attribute attribute : attributes) {
            comboBoxModel.addElement(attribute.getName());
        }
        comboBox.setModel(comboBoxModel);
        comboBox.insertItemAt("Select attribute", 0);
        comboBox.setSelectedIndex(0);
    }

    @Override
    protected void buildWindowBody() {
        comboBox = getComponentFactory().makeComboBox();

        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        build(getComponentFactory().makeLabel("Select attribute"));
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(0, 0, 8, 5);
        build(comboBox);
        setSize(getWidth(), 140);
    }

    @Override
    protected boolean okButtonLogic() {
        return true;
    }

    public Attribute getAttribute() {
        String attributeName = comboBox.getSelectedItem().toString();
        if (attributes.contains(new Attribute(attributeName))) {
            return attributes.get(attributeName);
        }
        return null;
    }
}
