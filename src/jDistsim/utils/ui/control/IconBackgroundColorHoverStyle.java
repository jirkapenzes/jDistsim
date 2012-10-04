package jDistsim.utils.ui.control;

import jDistsim.designer.ui.control.IconButton;

import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 4.10.12
 * Time: 13:45
 */
public class IconBackgroundColorHoverStyle implements IIconButtonHoverStyle {

    private Color color;

    public IconBackgroundColorHoverStyle(Color color) {
        this.color = color;
    }

    @Override
    public void applyHoverStyle(IconButton iconButton) {
        iconButton.setBackground(color);
    }
}
