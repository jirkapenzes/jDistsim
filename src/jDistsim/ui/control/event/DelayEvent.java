package jDistsim.ui.control.event;

import jDistsim.core.simulation.event.description.IEventDescription;
import jDistsim.utils.math.PointUtilities;

import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 29.9.12
 * Time: 23:21
 */
public class DelayEvent extends EventControl {

    public DelayEvent(IEventDescription eventDescription) {
        super(eventDescription);
    }

    @Override
    public void draw(Graphics2D graphics2D, int width, int height) {
        Polygon polygon = new Polygon();
        polygon.addPoint(0, PointUtilities.byPercentageOnLine(10, height));
        polygon.addPoint(width - 1, PointUtilities.byPercentageOnLine(10, height));
        polygon.addPoint(width - 1, PointUtilities.byPercentageOnLine(90, height));
        polygon.addPoint(0, PointUtilities.byPercentageOnLine(90, height));
        EventControl.drawEventControlPolygon(polygon, graphics2D);
    }
}
