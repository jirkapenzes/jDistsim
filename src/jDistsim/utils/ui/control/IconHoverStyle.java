package jDistsim.utils.ui.control;

import jDistsim.designer.ui.control.ImageButton;

import javax.swing.*;

/**
 * Author: Jirka Pénzeš
 * Date: 3.10.12
 * Time: 0:12
 */
public class IconHoverStyle implements IIconButtonHoverStyle {

    private Icon icon;

    public IconHoverStyle(Icon icon) {
        this.icon = icon;
    }

    @Override
    public void applyHoverStyle(ImageButton imageButton) {
        if (icon != null)
            imageButton.setIcon(icon);
    }
}
