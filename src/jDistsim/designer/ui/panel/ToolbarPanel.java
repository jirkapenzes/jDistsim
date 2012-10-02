package jDistsim.designer.ui.panel;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 3.10.12
 * Time: 0:24
 */
public class ToolbarPanel extends JPanel {

    public ToolbarPanel() {
        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 40);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(231, 231, 231));
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(new Color(220, 220, 220));
        g.fillRect(0, getHeight() - 15, getWidth(), getHeight());

        g.setColor((new Color(165, 165, 165)));
        g.drawLine(0, getHeight() - 15, getWidth(), getHeight() - 15);

        g.setColor((new Color(165, 165, 165)));
        g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
    }
}
