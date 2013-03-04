package jDistsim.ui.dialog;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 4.3.13
 * Time: 0:49
 */
public class AttributeSettingsDialog extends BaseDialog {

    public AttributeSettingsDialog(JDialog parent, String title) {
        super(parent, title);
    }

    public AttributeSettingsDialog(JFrame parent, String title) {
        super(parent, title);
    }

    @Override
    protected void initializeUI() {

    }

    @Override
    protected void buildWindowBody() {
        setSize(200, 140);
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridwidth = 1;
        build(getComponentFactory().makeLabel("Attribute name"));
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        build(getComponentFactory().makeLabel("Attribute value"));
        constraints.insets = new Insets(0, 0, 8, 4);
        constraints.gridwidth = 1;
        build(getComponentFactory().makeTextField());
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        build(getComponentFactory().makeTextField());
    }

    @Override
    protected boolean okButtonLogic() {
        return true;
    }
}
