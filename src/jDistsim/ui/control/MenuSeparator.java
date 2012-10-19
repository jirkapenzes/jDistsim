package jDistsim.ui.control;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 6.10.12
 * Time: 18:10
 */
public class MenuSeparator extends JComponent {

    private Color color;

    public MenuSeparator() {
        this(new Color(183, 183, 183));
    }

    public MenuSeparator(Color color) {
        this.color = color;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(3, 20);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.setColor(color);
        graphics.drawLine(1, 0, 1, getHeight());
    }
}

