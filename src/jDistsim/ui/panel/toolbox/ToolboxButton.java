package jDistsim.ui.panel.toolbox;

import jDistsim.ui.component.ComponentView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

/**
 * Author: Jirka Pénzeš
 * Date: 4.11.12
 * Time: 15:38
 */
public class ToolboxButton extends JComponent {

    private static final int DefaultSize = 50;
    private static final int DefaultPadding = 5;
    private static final int FontIndentation = 15;

    private ComponentView component;
    private boolean active = false;
    private boolean mouseEntered = false;
    private boolean mouseEnteredMode = true;
    private int padding;
    private String title;


    public ToolboxButton(ComponentView component, String title) {
        this(component, title, DefaultPadding, DefaultSize, DefaultSize);
    }

    public ToolboxButton(ComponentView component, String title, int padding, int width, int height) {
        this.component = component;
        this.title = title;
        this.padding = padding;
        setPreferredSize(new Dimension(width, height));
        initialize();
    }

    private void initialize() {
        resizeComponentView();
        add(component.getView());
        addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                resizeComponentView();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (mouseEnteredMode) {
                    mouseEntered = true;
                    repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (mouseEnteredMode) {
                    mouseEntered = false;
                    repaint();
                }
            }
        });
    }

    public void setMouseEnteredMode(boolean mouseEnteredMode) {
        this.mouseEnteredMode = mouseEnteredMode;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        repaint();
    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
        resizeComponentView();
    }

    private void resizeComponentView() {
        component.getView().setLocation(getPadding(), getPadding());
        component.getView().setSize(new Dimension(getWidth() - (getPadding() * 2), getHeight() - (getPadding() * 2) - FontIndentation));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (active || mouseEntered) {
            BasicStroke lineStroke = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, new float[]{5, 5}, 0);
            graphics2D.setStroke(lineStroke);

            if (active)
                graphics2D.setColor(new Color(217, 245, 249));
            else if (mouseEntered)
                graphics2D.setColor(new Color(242, 252, 253));

            graphics2D.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
            graphics2D.setColor(new Color(0, 187, 221));
            graphics2D.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }

        Font titleFont = new Font("Calibri", Font.PLAIN, 12);
        graphics2D.setFont(titleFont);

        graphics2D.setColor(new Color(58, 58, 58));
        FontRenderContext context = graphics2D.getFontRenderContext();
        Rectangle2D bounds = new TextLayout(title, titleFont, context).getBounds();
        graphics2D.drawString(title, (int) ((getWidth() - bounds.getWidth()) / 2.0), getHeight() - 6);
    }

}
