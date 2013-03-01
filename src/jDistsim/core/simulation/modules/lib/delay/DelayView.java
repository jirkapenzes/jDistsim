package jDistsim.core.simulation.modules.lib.delay;

import jDistsim.ui.module.ColorScheme;
import jDistsim.ui.module.ModuleView;

import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 10.11.12
 * Time: 20:58
 */
public class DelayView extends ModuleView {

    public DelayView() {
        super();
    }

    public DelayView(ColorScheme colorScheme) {
        super(colorScheme);
    }

    @Override
    protected void initializeConnectedPoints(int width, int height) {
        inputPoints.add(new Point(0, height / 2));
        outputPoints.add(new Point(width, height / 2));
    }

    @Override
    public Polygon getBounds(int width, int height) {
        Polygon polygon = new Polygon();
        polygon.addPoint(1, 1);
        polygon.addPoint(width - 1, 1);
        polygon.addPoint(width - 1, height - 1);
        polygon.addPoint(1, height - 1);
        return polygon;
    }
}
