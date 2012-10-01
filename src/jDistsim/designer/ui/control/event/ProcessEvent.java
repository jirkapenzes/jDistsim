package jDistsim.designer.ui.control.event;

import jDistsim.core.simulation.event.description.IEventDescription;
import jDistsim.utils.math.PointUtilities;

import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 29.9.12
 * Time: 22:48
 */
public class ProcessEvent extends EventControl {

    public ProcessEvent(IEventDescription eventDescription) {
        super(eventDescription);
    }

    @Override
    public void draw(Graphics2D graphics2D, int width, int height) {
        int offset = PointUtilities.byPercentageOnLine(10, width);
        Polygon polygon = new Polygon();
        polygon.addPoint(0, offset + PointUtilities.byPercentageOnLine(10, height));
        polygon.addPoint(offset, PointUtilities.byPercentageOnLine(10, height));
        polygon.addPoint(width - offset, PointUtilities.byPercentageOnLine(10, height));
        polygon.addPoint(width - 1, offset + PointUtilities.byPercentageOnLine(10, height));
        polygon.addPoint(width - 1, PointUtilities.byPercentageOnLine(90, height) - offset);
        polygon.addPoint(width - offset, PointUtilities.byPercentageOnLine(90, height));
        polygon.addPoint(offset, PointUtilities.byPercentageOnLine(90, height));
        polygon.addPoint(0, PointUtilities.byPercentageOnLine(90, height) - offset);
        DecideEvent.drawEventControlPolygon(polygon, graphics2D);
    }
}
