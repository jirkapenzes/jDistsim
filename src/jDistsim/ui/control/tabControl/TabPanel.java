package jDistsim.ui.control.tabControl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 27.9.12
 * Time: 8:43
 */
public class TabPanel extends JComponent {

    private TabTitleControl activeTabTitleControl;

    public TabPanel() {
        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(2, 2, 2, 2));
    }

    public void setActiveTabTitleControl(TabTitleControl activeTabTitleControl) {
        this.activeTabTitleControl = activeTabTitleControl;

        removeAll();
        JPanel panelContent = activeTabTitleControl.getPanel();
        panelContent.setSize(getWidth() - 2, getHeight() - 2);
        panelContent.setLocation(1, 1);
        add(panelContent, BorderLayout.CENTER);
        repaint();
        revalidate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(192, 192, 192));
        g.drawLine(0, 0, 0, getHeight());
        g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
        g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight());

        TabTitleControl tabTitleControl = activeTabTitleControl;
        if (activeTabTitleControl != null) {
            g.drawLine(0, 0, tabTitleControl.getX(), 0);
            g.drawLine(tabTitleControl.getX() + tabTitleControl.getWidth(), 0, getWidth() - 1, 0);

            g.setColor(new Color(231, 231, 231));
            g.drawLine(tabTitleControl.getX() + 1, 0, tabTitleControl.getX() + tabTitleControl.getWidth() - 1, 0);
            g.drawLine(tabTitleControl.getX() + 1, 1, tabTitleControl.getX() + tabTitleControl.getWidth() - 1, 1);

            // glass effect
            g.setColor(new Color(249, 249, 249));
            g.drawLine(1, 1, tabTitleControl.getX() + 1, 1);
            g.drawLine(tabTitleControl.getX() + tabTitleControl.getWidth() - 1, 1, getWidth() - 2, 1);
            g.drawLine(tabTitleControl.getX() + tabTitleControl.getWidth() - 1, 0, tabTitleControl.getX() + tabTitleControl.getWidth() - 1, 1);
            g.drawLine(tabTitleControl.getX() + 1, 0, tabTitleControl.getX() + 1, 1);
        }

        // glass effect
        g.setColor(new Color(249, 249, 249));
        g.drawLine(1, 1, 1, getHeight() - 2);
        g.drawLine(1, getHeight() - 2, getWidth() - 2, getHeight() - 2);
        g.drawLine(getWidth() - 2, 1, getWidth() - 2, getHeight() - 2);
    }
}
