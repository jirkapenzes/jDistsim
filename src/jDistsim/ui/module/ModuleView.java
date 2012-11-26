package jDistsim.ui.module;

import jDistsim.core.modules.IModuleView;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 2.11.12
 * Time: 12:41
 */
public abstract class ModuleView implements IModuleView {

    private JComponent view;

    protected ModuleView() {
        view = new ModuleViewComponent();
    }

    @Override
    public JComponent getContentPane() {
        return view;
    }

    @Override
    public void draw(Graphics2D graphics, int width, int height) {
        Graphics2D graphics2D = graphics;
        setDefaultRenderingMode(graphics2D);
        setDefaultBasicStroke(graphics2D);

        Polygon polygon = getBounds(width, height);
        graphics2D.setColor(getBackgroundColor());
        graphics2D.fillPolygon(polygon);
        graphics2D.setColor(getBorderColor());
        graphics2D.drawPolygon(polygon);
    }


    protected void setDefaultRenderingMode(Graphics2D graphics2D) {
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    protected void setDefaultBasicStroke(Graphics2D graphics2D) {
        graphics2D.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

    protected Color getBackgroundColor() {
        return new Color(67, 201, 224);
    }

    protected Color getBorderColor() {
        return new Color(70, 127, 137);
    }

    private class ModuleViewComponent extends JComponent {

        public ModuleViewComponent() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            draw((Graphics2D) graphics, getWidth(), getHeight());
        }
    }
}
