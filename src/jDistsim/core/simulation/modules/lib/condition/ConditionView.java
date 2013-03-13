package jDistsim.core.simulation.modules.lib.condition;

import jDistsim.ui.module.ColorScheme;
import jDistsim.ui.module.ModuleView;

import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 14.3.13
 * Time: 0:00
 */
public class ConditionView extends ModuleView {

    public ConditionView() {
        super();
    }

    public ConditionView(ColorScheme colorScheme) {
        super(colorScheme);
    }

    @Override
    protected void initializeConnectedPoints(int width, int height) {
        inputPoints.add(new Point(0, height / 2));
        outputPoints.add(new Point(width, height / 2));
        outputPoints.add(new Point(width / 2, height));
    }

    @Override
    public Polygon getBounds(int width, int height) {
        Polygon polygon = new Polygon();
        polygon.addPoint(width / 2, 1);
        polygon.addPoint(width - 1, height / 2);
        polygon.addPoint(width / 2, height - 1);
        polygon.addPoint(1, height / 2);
        return polygon;
    }
}
