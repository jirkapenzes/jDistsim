package jDistsim.ui.dialog;

import jDistsim.ui.control.button.ImageButton;

import javax.swing.*;

/**
 * Author: Jirka Pénzeš
 * Date: 24.2.13
 * Time: 16:49
 */
public interface IDialogComponentFactory {

    JLabel makeLabel(String text);

    JButton makeButton(String text);

    JTextField makeTextField();

    JTextField makeTextField(int size);

    JCheckBox makeCheckBox(String text);

    JComboBox makeComboBox();

    ImageButton makeImageButton(String imageUrl);
}
