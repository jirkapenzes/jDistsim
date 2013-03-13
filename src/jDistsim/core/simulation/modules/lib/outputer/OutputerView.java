package jDistsim.core.simulation.modules.lib.outputer;

import jDistsim.ui.module.ColorScheme;
import jDistsim.ui.module.ModuleView;
import jDistsim.utils.math.PointUtilities;

import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 13.3.13
 * Time: 23:29
 */
public class OutputerView extends ModuleView {

    public OutputerView() {
        super();
    }

    public OutputerView(ColorScheme colorScheme) {
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
        int d = 35;

        polygon.addPoint(PointUtilities.byPercentageOnLine(d, width), 1);
        polygon.addPoint(width - 1, 1);
        polygon.addPoint(width - 1, height - 1);
        polygon.addPoint(1, height - 1);
        polygon.addPoint(1, PointUtilities.byPercentageOnLine(d, height));
        return polygon;
    }

    @Override
    protected void postDraw(Graphics2D graphics, int width, int height) {
        graphics.setColor(colorScheme.getBorderColor());
        int d = 35;
        int tmpX = PointUtilities.byPercentageOnLine(d, width);
        int tmpY = PointUtilities.byPercentageOnLine(d, height);
        graphics.drawLine(1, tmpY, tmpX, tmpY);
        graphics.drawLine(tmpX, tmpY, tmpX, 1);
    }
}
