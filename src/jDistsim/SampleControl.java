package jDistsim;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 11.11.12
 * Time: 14:49
 */
public class SampleControl extends JComponent {

    private Color color;

    public SampleControl(Color color) {
        this.color = color;
        setSize(50, 50);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50, 50);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.setColor(color);
        graphics.fillRect(0, 0, getWidth(), getHeight());
    }
}
