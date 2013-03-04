package jDistsim.core.simulation.modules.lib.assign;

import jDistsim.ui.module.ColorScheme;
import jDistsim.ui.module.ModuleView;
import jDistsim.utils.math.PointUtilities;

import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 3.3.13
 * Time: 13:39
 */
public class AssignView extends ModuleView {

    public AssignView() {
        super();
    }

    public AssignView(ColorScheme colorScheme) {
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
        int d = 20;
        polygon.addPoint(PointUtilities.byPercentageOnLine(d, width), 1);
        polygon.addPoint(PointUtilities.byPercentageOnLine(100 - d, width), 1);
        polygon.addPoint(width - 1, PointUtilities.byPercentageOnLine(d, height));
        polygon.addPoint(width - 1, PointUtilities.byPercentageOnLine(100 - d, height));
        polygon.addPoint(PointUtilities.byPercentageOnLine(100 - d, width), height - 1);
        polygon.addPoint(PointUtilities.byPercentageOnLine(d, width), height - 1);
        polygon.addPoint(1, PointUtilities.byPercentageOnLine(100 - d, height));
        polygon.addPoint(1, PointUtilities.byPercentageOnLine(d, height));
        return polygon;
    }
}
