package jDistsim.designer.ui.control;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 29.9.12
 * Time: 15:40
 */
public class IconBar extends JComponent {

    private Color backgroundColor;

    public IconBar() {
        this(new Color(240, 240, 240));
    }

    public IconBar(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 30);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
