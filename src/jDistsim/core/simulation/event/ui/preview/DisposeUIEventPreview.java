package jDistsim.core.simulation.event.ui.preview;

import jDistsim.core.simulation.event.description.IEventDescription;
import jDistsim.core.simulation.event.library.dispose.IDisposeDescription;
import jDistsim.core.simulation.event.library.dispose.IDisposeUIEventPreview;
import jDistsim.core.simulation.event.ui.UIDrawingHelper;
import jDistsim.utils.math.PointUtilities;

import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 6.10.12
 * Time: 22:16
 */
public class DisposeUIEventPreview implements IDisposeUIEventPreview {

    private IDisposeDescription disposeDescription;

    public DisposeUIEventPreview(IDisposeDescription disposeDescription) {
        this.disposeDescription = disposeDescription;
    }

    @Override
    public void display(Graphics2D graphics2D, int width, int height) {
        Polygon polygon = new Polygon();
        polygon.addPoint(0, height / 2);
        polygon.addPoint(PointUtilities.byPercentageOnLine(30, width), PointUtilities.byPercentageOnLine(10, height));
        polygon.addPoint(width - 1, PointUtilities.byPercentageOnLine(10, height));
        polygon.addPoint(width - 1, PointUtilities.byPercentageOnLine(90, height));
        polygon.addPoint(PointUtilities.byPercentageOnLine(30, width), PointUtilities.byPercentageOnLine(90, height));

        new UIDrawingHelper().drawAsPolygon(graphics2D, polygon);
    }

    @Override
    public IEventDescription getEventDescription() {
        return disposeDescription;
    }
}
