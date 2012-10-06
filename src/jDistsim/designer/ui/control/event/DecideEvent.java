package jDistsim.designer.ui.control.event;

import jDistsim.core.simulation.event.ui.IEventDescription;

import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 29.9.12
 * Time: 19:29
 */
public class DecideEvent extends EventControl {

    public DecideEvent(IEventDescription eventDescription) {
        super(eventDescription);
    }

    @Override
    public void draw(Graphics2D graphics2D, int width, int height) {
        Polygon polygon = new Polygon();
        polygon.addPoint(width / 2, 0);
        polygon.addPoint(width - 1, height / 2);
        polygon.addPoint(width / 2, height - 1);
        polygon.addPoint(0, height / 2);
        DecideEvent.drawEventControlPolygon(polygon, graphics2D);
    }

}
