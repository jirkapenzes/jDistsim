package jDistsim.utils.ui.control;

import jDistsim.designer.ui.control.IconButton;

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
    public void applyHoverStyle(IconButton iconButton) {
        if (icon != null)
            iconButton.getIconLabel().setIcon(icon);
    }
}
