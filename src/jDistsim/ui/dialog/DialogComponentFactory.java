package jDistsim.ui.dialog;

import jDistsim.application.designer.common.UIConfiguration;
import jDistsim.ui.control.button.ImageButton;
import jDistsim.utils.resource.Resources;
import jDistsim.utils.ui.control.IconBackgroundColorHoverStyle;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 24.2.13
 * Time: 16:55
 */
public class DialogComponentFactory implements IDialogComponentFactory {

    @Override
    public JLabel makeLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(UIConfiguration.getInstance().getDefaultFont(true, 12));
        return label;
    }

    @Override
    public JButton makeButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(70, 24));
        return button;
    }

    @Override
    public JTextField makeTextField() {
        return new JTextField(10);
    }

    @Override
    public JTextField makeTextField(int size) {
        return new JTextField(size);
    }


    @Override
    public JCheckBox makeCheckBox(String text) {
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setBackground(Color.white);
        return checkBox;
    }

    @Override
    public JComboBox makeComboBox() {
        return new JComboBox();
    }

    @Override
    public ImageButton makeImageButton(String imageUrl) {
        return new ImageButton(Resources.getImage(imageUrl), new IconBackgroundColorHoverStyle(), new Dimension(16, 16), 3);
    }
}
