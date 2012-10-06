package jDistsim.designer.ui.control.event;

import jDistsim.core.simulation.event.ui.UIEventPreview;
import jDistsim.designer.ui.UIConfiguration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Author: Jirka Pénzeš
 * Date: 29.9.12
 * Time: 19:41
 */
public class ToolbarEventPreviewControl extends JComponent {

    private UIEventPreview eventPreview;
    private boolean active;

    public ToolbarEventPreviewControl(UIEventPreview eventPreview) {
        this.eventPreview = eventPreview;
        initializeComponents();
    }

    private void initializeComponents() {
        //eventControl.setSize(80, 60);
        //eventControl.setLocation(0, 0);
        //add(eventControl);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                setActive(true);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                setActive(false);
                repaint();
            }
        });
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(70, 80);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (isActive()) {
            graphics2D.setColor(new Color(230, 240, 251));
            graphics2D.fillRect(0, 0, getWidth(), getHeight());
            graphics2D.setColor(new Color(201, 224, 248));
            graphics2D.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }

        BufferedImage bufferedImage = new BufferedImage(70, 50, BufferedImage.TYPE_INT_ARGB);
        eventPreview.display(bufferedImage.createGraphics(), 70, 50);
        graphics.drawImage(bufferedImage, 0, 0, this);

        Font font = UIConfiguration.getInstance().getDefaultFont();
        String title = eventPreview.getEventDescription().getTitle();
        FontRenderContext context = graphics2D.getFontRenderContext();
        TextLayout txt = new TextLayout(title, font, context);
        Rectangle2D bounds = txt.getBounds();
        int xString = (int) ((getWidth() - bounds.getWidth()) / 2.0);

        graphics2D.setColor(Color.black);
        graphics2D.setFont(font);
        graphics2D.setStroke(UIConfiguration.getInstance().getEventControlBorderStroke());
        graphics2D.drawString(title, xString, getHeight() - 16);
    }
}
