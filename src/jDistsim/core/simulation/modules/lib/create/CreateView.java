package jDistsim.core.simulation.modules.lib.create;

import jDistsim.ui.module.ColorScheme;
import jDistsim.ui.module.ModuleView;
import jDistsim.utils.math.PointUtilities;

import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 2.11.12
 * Time: 12:52
 */
public class CreateView extends ModuleView {

    public CreateView() {
        super();
    }

    public CreateView(ColorScheme colorScheme) {
        super(colorScheme);
    }

    @Override
    protected void initializeConnectedPoints(int width, int height) {
        outputPoints.add(new Point(width - 1, height / 2));
    }

    @Override
    public Polygon getBounds(int width, int height) {
        Polygon polygon = new Polygon();
        polygon.addPoint(1, 1);
        polygon.addPoint(PointUtilities.byPercentageOnLine(60, width), 1);
        polygon.addPoint(width - 1, height / 2 - 1);
        polygon.addPoint(PointUtilities.byPercentageOnLine(60, width), height - 1);
        polygon.addPoint(1, height - 1);
        return polygon;
    }
}
