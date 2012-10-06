package jDistsim.designer.ui.control.event;

import jDistsim.core.simulation.event.description.IEventDescription;
import jDistsim.designer.ui.UIConfiguration;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 29.9.12
 * Time: 15:49
 */
public abstract class EventControl extends JComponent {

    private IEventDescription eventDescription;

    protected EventControl(IEventDescription eventDescription) {
        this.eventDescription = eventDescription;
    }

    public IEventDescription getEventDescription() {
        return eventDescription;
    }

    public abstract void draw(Graphics2D graphics2D, int width, int height);

    public static void drawEventControlPolygon(Polygon polygon, Graphics2D graphics2D) {
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setStroke(UIConfiguration.getInstance().getEventControlBorderStroke());
        graphics2D.setColor(UIConfiguration.getInstance().getEventControlFillColor());
        graphics2D.fillPolygon(polygon);
        graphics2D.setColor(UIConfiguration.getInstance().getEventControlBorderColor());
        graphics2D.drawPolygon(polygon);
    }
}
