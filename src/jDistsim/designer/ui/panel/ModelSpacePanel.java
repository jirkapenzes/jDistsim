package jDistsim.designer.ui.panel;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 30.9.12
 * Time: 18:15
 */
public class ModelSpacePanel extends JPanel {

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(2000, 2000);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(new Color(235, 235, 235));

        for (int i = 0; i < getWidth(); i = i + 20)
            g.drawLine(i, 0, i, getHeight());

        for (int i = 0; i < getHeight(); i = i + 20)
            g.drawLine(0, i, getWidth(), i);
    }
}
