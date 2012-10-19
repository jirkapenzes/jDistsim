package jDistsim.ui;

import jDistsim.utils.logging.Logger;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 24.9.12
 * Time: 22:58
 */
public class StatusBar extends JPanel {

    private JLabel statusBarLabelLeft;
    private JLabel statusBarLabelRight;

    public StatusBar() {
        initializeComponents();

        setLabelLeftText(Logger.getMessages().next().toString());
        setLabelRightText("Demo");
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(0, 10, 0, 10));

        statusBarLabelLeft = new JLabel();
        statusBarLabelRight = new JLabel();

        setBackground(new Color(240, 240, 240));

        add(statusBarLabelLeft, BorderLayout.WEST);
        add(statusBarLabelRight, BorderLayout.EAST);
    }

    public void setLabelLeftText(String text) {
        statusBarLabelLeft.setText(text);
    }

    public void setLabelRightText(String text) {
        statusBarLabelRight.setText(text);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 20);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(145, 145, 145));
        g2d.drawLine(0, 0, getWidth(), 0);
    }
}
