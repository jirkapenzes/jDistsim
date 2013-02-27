package jDistsim.ui.skins;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 20.2.13
 * Time: 23:57
 */
public class ScrollBarUI extends BasicScrollBarUI {

    private int scrollBarSize = 12;

    public boolean isVertical;

    public ScrollBarUI() {
        this(true);
    }

    public ScrollBarUI(boolean vertical) {
        this.isVertical = vertical;
    }

    private JButton emptyButton = new JButton() {
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(0, 0);
        }
    };

    @Override
    public Dimension getPreferredSize(JComponent c) {
        return isVertical ? new Dimension(scrollBarSize, c.getHeight()) : new Dimension(c.getWidth(), scrollBarSize);
    }

    @Override
    public Dimension getMaximumSize(JComponent c) {
        return isVertical ? new Dimension(scrollBarSize, c.getHeight()) : new Dimension(c.getWidth(), scrollBarSize);
    }

    @Override
    public Dimension getMinimumSize(JComponent c) {
        return isVertical ? new Dimension(scrollBarSize, c.getHeight()) : new Dimension(c.getWidth(), scrollBarSize);
    }

    @Override
    protected void paintThumb(Graphics graphics, JComponent c, Rectangle r) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        int margin = 2;

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradientPaint = new GradientPaint(0, 0, new Color(245, 245, 245), 12, 0, new Color(200, 200, 200));
        graphics2D.setPaint(gradientPaint);
        graphics2D.fillRoundRect(r.x + margin, r.y + margin, r.width - 2 * margin, r.height - 2 * margin, 2, 2);

        graphics2D.setColor(new Color(200, 200, 200));
        graphics2D.drawRoundRect(r.x + margin, r.y + margin, r.width - 2 * margin, r.height - 2 * margin, 2, 2);
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
        Graphics2D graphics2D = (Graphics2D) g;

        g.setColor(new Color(245, 245, 245));
        ((Graphics2D) g).fillRect(r.x, r.y, r.width, r.height);
        g.setColor(new Color(230, 230, 230));
        g.drawLine(r.x, r.y, r.x, r.height);
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return emptyButton;
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return emptyButton;
    }
}
