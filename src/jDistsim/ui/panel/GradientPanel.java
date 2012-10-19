package jDistsim.ui.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Author: Jirka Pénzeš
 * Date: 28.9.12
 * Time: 17:55
 */
public class GradientPanel extends JPanel {

    private Color color1;
    private Color color2;

    public GradientPanel() {
        this(new Color(236, 236, 236), new Color(228, 228, 228));
    }

    public GradientPanel(Color color1, Color color2) {
        this.color1 = color1;
        this.color2 = color2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradientPaint = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
        g2.setPaint(gradientPaint);
        g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
    }
}
