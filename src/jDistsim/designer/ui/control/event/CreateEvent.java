package jDistsim.designer.ui.control.event;

import jDistsim.core.simulation.event.description.IEventDescription;
import jDistsim.utils.math.PointUtilities;

import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 29.9.12
 * Time: 19:24
 */
public class CreateEvent extends EventControl {

    public CreateEvent(IEventDescription eventDescription) {
        super(eventDescription);
    }

    @Override
    public void draw(Graphics2D graphics2D, int width, int height) {
        Polygon polygon = new Polygon();
        polygon.addPoint(0, PointUtilities.byPercentageOnLine(10, height));
        polygon.addPoint(PointUtilities.byPercentageOnLine(70, width), PointUtilities.byPercentageOnLine(10, height));
        polygon.addPoint(width - 1, height / 2);
        polygon.addPoint(PointUtilities.byPercentageOnLine(70, width), PointUtilities.byPercentageOnLine(90, height));
        polygon.addPoint(0, PointUtilities.byPercentageOnLine(90, height));
        DecideEvent.drawEventControlPolygon(polygon, graphics2D);
    }
}
