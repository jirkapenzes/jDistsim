package jDistsim.ui.component.toolboxView;

import jDistsim.ui.component.ComponentView;
import jDistsim.utils.math.PointUtilities;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 2.11.12
 * Time: 12:52
 */
public class CreateComponentView extends ComponentView {

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
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Polygon polygon = new Polygon();
            polygon.addPoint(1,1);
            polygon.addPoint(PointUtilities.byPercentageOnLine(60, getWidth()), 1);
            polygon.addPoint(getWidth() - 1, getHeight() / 2-1);
            polygon.addPoint(PointUtilities.byPercentageOnLine(60, getWidth()), getHeight()-1);
            polygon.addPoint(1, getHeight()-1);

            graphics2D.setColor(new Color(67,201,224));
            graphics2D.fillPolygon(polygon);

            BasicStroke lineStroke = new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            graphics2D.setColor(new Color(70,127,137));
            graphics2D.setStroke(lineStroke);
            graphics2D.drawPolygon(polygon);
        }
    }
}
