package jDistsim.utils.ui.control;

import jDistsim.designer.ui.control.button.ImageButton;

import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 4.10.12
 * Time: 13:45
 */
public class IconBackgroundColorHoverStyle implements IIconButtonHoverStyle {

    private Color color;
    private Color borderColor;

    public IconBackgroundColorHoverStyle() {
        this(new Color(181, 190, 214));
    }

    public IconBackgroundColorHoverStyle(Color color) {
        this(color, new Color(8, 36, 107));
    }


    public IconBackgroundColorHoverStyle(Color color, Color borderColor) {
        this.color = color;
        this.borderColor = borderColor;
    }

    @Override
    public void applyHoverStyle(ImageButton imageButton) {
        Graphics graphics = imageButton.getGraphics();
        graphics.setColor(color);
        graphics.fillRect(0, 0, imageButton.getWidth(), imageButton.getHeight());
        graphics.setColor(borderColor);
        graphics.drawRect(0, 0, imageButton.getWidth() - 1, imageButton.getHeight() - 1);
        graphics.drawImage(imageButton.getImage(), imageButton.getPaddingSize(), imageButton.getPaddingSize(),
                imageButton.getWidth() - 2 * imageButton.getPaddingSize(), imageButton.getHeight() - 2 * imageButton.getPaddingSize(), imageButton);
    }
}
