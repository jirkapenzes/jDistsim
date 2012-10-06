package jDistsim.designer.ui.control.event;

import jDistsim.core.simulation.event.ui.IEventDescription;
import jDistsim.utils.math.PointUtilities;

import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 29.9.12
 * Time: 23:55
 */
public class RecordEvent extends EventControl {

    public RecordEvent(IEventDescription eventDescription) {
        super(eventDescription);
    }

    @Override
    public void draw(Graphics2D graphics2D, int width, int height) {
        Polygon polygon = new Polygon();

        int offset = PointUtilities.byPercentageOnLine(20, width);
        polygon.addPoint(0, PointUtilities.byPercentageOnLine(10, height) + offset);
        polygon.addPoint(offset, PointUtilities.byPercentageOnLine(10, height));
        polygon.addPoint(width - 1, PointUtilities.byPercentageOnLine(10, height));
        polygon.addPoint(width - 1, PointUtilities.byPercentageOnLine(90, height));
        polygon.addPoint(0, PointUtilities.byPercentageOnLine(90, height));

        EventControl.drawEventControlPolygon(polygon, graphics2D);
    }
}
