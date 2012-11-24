package jDistsim.ui.module.moduleView;

import jDistsim.ui.module.ModuleView;
import jDistsim.utils.math.PointUtilities;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 10.11.12
 * Time: 0:12
 */
public class DisposeModuleView extends ModuleView {

    @Override
    protected JComponent makeView() {
        return new DisposeComponent();
    }

    private class DisposeComponent extends JComponent {

        public DisposeComponent() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            Graphics2D graphics2D = (Graphics2D) graphics;
            setDefaultRenderingMode(graphics2D);
            setDefaultBasicStroke(graphics2D);

            Polygon polygon = new Polygon();
            polygon.addPoint(0, getHeight() / 2 - 1);
            polygon.addPoint(PointUtilities.byPercentageOnLine(40, getWidth()), 1);
            polygon.addPoint(getWidth() - 1, 1);
            polygon.addPoint(getWidth() - 1, getHeight() - 1);
            polygon.addPoint(PointUtilities.byPercentageOnLine(40, getWidth()), getHeight() - 1);

            graphics2D.setColor(getBackgroundColor());
            graphics2D.fillPolygon(polygon);
            graphics2D.setColor(getBorderColor());
            graphics2D.drawPolygon(polygon);
        }
    }
}
