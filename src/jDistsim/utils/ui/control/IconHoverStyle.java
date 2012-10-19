package jDistsim.utils.ui.control;

import jDistsim.ui.control.button.ImageButton;

import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 3.10.12
 * Time: 0:12
 */
public class IconHoverStyle implements IIconButtonHoverStyle {

    private Image image;

    public IconHoverStyle(Image image) {
        this.image = image;
    }

    @Override
    public void applyHoverStyle(ImageButton imageButton) {
        Graphics graphics = imageButton.getGraphics();
        graphics.clearRect(0, 0, imageButton.getWidth(), imageButton.getHeight());
        graphics.drawImage(image, 0, 0, imageButton.getWidth(), imageButton.getHeight(), imageButton);
    }
}
