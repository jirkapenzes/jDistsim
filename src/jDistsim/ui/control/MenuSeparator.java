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
    private int height;

    public MenuSeparator() {
        this(new Color(183, 183, 183), 20);
    }

    public MenuSeparator(int height) {
        this(new Color(183, 183, 183), height);
    }

    public MenuSeparator(Color color) {
        this(color, 20);
    }

    public MenuSeparator(Color color, int height) {
        this.color = color;
        this.height = height;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(3, height);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.setColor(color);
        graphics.drawLine(1, 0, 1, getHeight());
    }
}

