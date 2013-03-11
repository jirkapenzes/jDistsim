package jDistsim.utils.ui.control;

import jDistsim.ui.control.button.ImageButton;

import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 4.10.12
 * Time: 13:45
 */
public class IconBackgroundColorHoverStyle implements IIconButtonHoverStyle {

    private Color hoverBackgroundColor;
    private Color hoverBorderColor;
    private Color pressedBackgroundColor;
    private Color pressedBorderColor;

    public IconBackgroundColorHoverStyle() {
        this(new Color(209, 226, 242));
    }

    public IconBackgroundColorHoverStyle(Color hoverBackgroundColor) {
        this(hoverBackgroundColor, new Color(120, 174, 229), new Color(181, 190, 214),  new Color(8, 36, 107));
    }


    public IconBackgroundColorHoverStyle(Color hoverBackgroundColor, Color pressedBackgroundColor, Color pressedBorderColor) {
        this(hoverBackgroundColor, new Color(8, 36, 107), pressedBackgroundColor, pressedBorderColor);
    }

    public IconBackgroundColorHoverStyle(Color hoverBackgroundColor, Color hoverBorderColor, Color pressedBackgroundColor, Color pressedBorderColor) {
        this.hoverBackgroundColor = hoverBackgroundColor;
        this.hoverBorderColor = hoverBorderColor;
        this.pressedBackgroundColor = pressedBackgroundColor;
        this.pressedBorderColor = pressedBorderColor;
    }

    @Override
    public void applyHoverStyle(ImageButton imageButton) {
        applyBackgroundColor(imageButton, hoverBackgroundColor, hoverBorderColor);
    }

    @Override
    public void applyPressedStyle(ImageButton imageButton) {
        applyBackgroundColor(imageButton, pressedBackgroundColor, pressedBorderColor);
    }

    @Override
    public void applyReleaseStyle(ImageButton imageButton) {
        applyHoverStyle(imageButton);
    }

    private void applyBackgroundColor(ImageButton imageButton, Color backgroundColor, Color borderColor) {
        Graphics graphics = imageButton.getGraphics();
        graphics.setColor(backgroundColor);
        graphics.fillRect(0, 0, imageButton.getWidth(), imageButton.getHeight());
        graphics.setColor(borderColor);
        graphics.drawRect(0, 0, imageButton.getWidth() - 1, imageButton.getHeight() - 1);
        graphics.drawImage(imageButton.getImage(), imageButton.getPaddingSize(), imageButton.getPaddingSize(),
                imageButton.getWidth() - 2 * imageButton.getPaddingSize(), imageButton.getHeight() - 2 * imageButton.getPaddingSize(), imageButton);
    }
}
