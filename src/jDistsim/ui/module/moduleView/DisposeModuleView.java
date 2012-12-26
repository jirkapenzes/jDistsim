package jDistsim.ui.module.moduleView;

import jDistsim.ui.module.ModuleView;
import jDistsim.utils.math.PointUtilities;

import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 10.11.12
 * Time: 0:12
 */
public class DisposeModuleView extends ModuleView {

    @Override
    public Polygon getBounds(int width, int height) {
        Polygon polygon = new Polygon();
        polygon.addPoint(0, height / 2 - 1);
        polygon.addPoint(PointUtilities.byPercentageOnLine(40, width), 1);
        polygon.addPoint(width - 1, 1);
        polygon.addPoint(width - 1, height - 1);
        polygon.addPoint(PointUtilities.byPercentageOnLine(40, width), height - 1);
        return polygon;
    }

    @Override
    protected void initializeConnectedPoints(int width, int height) {
      inputPoints.add(new Point(0, height / 2));
    }
}
