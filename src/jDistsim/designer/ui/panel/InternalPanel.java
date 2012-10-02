package jDistsim.designer.ui.panel;

import jDistsim.designer.ui.control.GradientTitle;
import jDistsim.designer.ui.control.IconBar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 28.9.12
 * Time: 17:27
 */
public class InternalPanel extends JComponent {

    private int panelThickness = 3;
    private boolean visibleIconBar = false;

    public InternalPanel(String titleName) {
        initializeComponents(titleName);
    }

    private void initializeComponents(String titleName) {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(0, panelThickness, 0, panelThickness));
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        // titlePanel.add(new GradientTitle(this, titleName, new Color(236, 236, 236), new Color(228, 228, 228)));
        titlePanel.add(new GradientTitle(this, titleName, new Color(236, 236, 236), new Color(228, 228, 228)));

        if (visibleIconBar)
            titlePanel.add(new IconBar());

        add(titlePanel, BorderLayout.NORTH);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(192, 192, 192));
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.white);
        g.fillRect(panelThickness, 0, getWidth() - (2 * panelThickness), getHeight());

        // g.setColor(new Color(192, 192, 192));
        // g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight());
    }
}
