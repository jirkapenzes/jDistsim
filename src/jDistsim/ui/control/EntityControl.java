package jDistsim.ui.control;

import jDistsim.utils.ui.SwingUtil;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 23.2.13
 * Time: 10:37
 */
public class EntityControl extends JComponent {

    private Image image;

    public EntityControl(Image image) {
        this(image, new Dimension(24, 24));
    }


    public EntityControl(Image image, Dimension dimension) {
        SwingUtil.setAbsoluteDimension(this, dimension);
        this.image = image;
    }

    public void setImage(Image image) {
        this.image = image;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (image != null)
            graphics.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}
