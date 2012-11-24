package jDistsim.ui.component.toolboxView;

import jDistsim.ui.component.ModuleView;
import jDistsim.utils.math.PointUtilities;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 2.11.12
 * Time: 12:52
 */
public class CreateModuleView extends ModuleView {

    @Override
    protected JComponent makeView() {
        return new CreateComponent();
    }

    private class CreateComponent extends JComponent {

        public CreateComponent() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            Graphics2D graphics2D = (Graphics2D) graphics;
            setDefaultRenderingMode(graphics2D);
            setDefaultBasicStroke(graphics2D);

            Polygon polygon = new Polygon();
            polygon.addPoint(1, 1);
            polygon.addPoint(PointUtilities.byPercentageOnLine(60, getWidth()), 1);
            polygon.addPoint(getWidth() - 1, getHeight() / 2 - 1);
            polygon.addPoint(PointUtilities.byPercentageOnLine(60, getWidth()), getHeight() - 1);
            polygon.addPoint(1, getHeight() - 1);

            graphics2D.setColor(getBackgroundColor());
            graphics2D.fillPolygon(polygon);
            graphics2D.setColor(getBorderColor());
            graphics2D.drawPolygon(polygon);
        }
    }
}
