package jDistsim.ui.module.moduleView;

import jDistsim.ui.module.ModuleView;
import jDistsim.utils.math.PointUtilities;

import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 2.11.12
 * Time: 12:52
 */
public class CreateModuleView extends ModuleView {

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
