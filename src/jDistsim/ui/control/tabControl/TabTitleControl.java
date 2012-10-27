package jDistsim.ui.control.tabControl;

import jDistsim.application.designer.common.UIConfiguration;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

/**
 * Author: Jirka Pénzeš
 * Date: 26.9.12
 * Time: 22:47
 */
public class TabTitleControl extends JComponent {

    private TabItem tabItem;
    private GradientPaint currentGradientPaint;
    private boolean active;

    public TabTitleControl(TabItem tabItem) {
        this(tabItem, false);
    }

    public TabTitleControl(TabItem tabItem, boolean active) {
        this.tabItem = tabItem;
        this.active = active;

        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel(tabItem.getName());
        label.setFont(UIConfiguration.getInstance().getDefaultFont());
        // label.setImage(new ImageIcon(Resources.getImage("system/project-toolbox-tab-log.png")));
        add(label, BorderLayout.CENTER);
        setBorder(new EmptyBorder(5, 10, 3, 10));

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if (isActive()) return;
                currentGradientPaint = new GradientPaint(0, 0, new Color(252, 253, 255), 0, getHeight(), new Color(235, 240, 251));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if (isActive()) return;
                setActive(false);
            }
        });
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        setCurrentGradientPaintByActiveState();
        repaint();
    }

    private void setCurrentGradientPaintByActiveState() {
        if (isActive()) {
            currentGradientPaint = new GradientPaint(0, 0, new Color(251, 251, 251), 0, getHeight(), new Color(231, 231, 231));
        } else {
            currentGradientPaint = new GradientPaint(0, 0, new Color(235, 235, 235), 0, getHeight(), new Color(224, 224, 224));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (currentGradientPaint == null)
            setCurrentGradientPaintByActiveState();

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(currentGradientPaint);
        g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        g.setColor(new Color(192, 192, 192));
        g2.drawLine(0, 0, 0, getHeight());
        g2.drawLine(0, 0, getWidth(), 0);

        // glass effect
        if (isActive()) {
            g.setColor(new Color(249, 249, 249));
            g2.drawLine(1, 1, getWidth(), 1);
            g2.drawLine(1, 1, 1, getHeight());
            g2.drawLine(getWidth() - 1, 1, getWidth() - 1, getHeight());
        } else {
            g2.setColor(new Color(238, 238, 238));
            g2.drawRect(1, 1, getWidth() - 2, getHeight() - 2);
        }
    }

    public JPanel getPanel() {
        return tabItem.getPanel();
    }
}
