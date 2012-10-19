package jDistsim.core.simulation.event.ui;

import jDistsim.designer.UIConfiguration;

import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 6.10.12
 * Time: 20:45
 */
public class UIDrawingHelper {

    public void drawAsPolygon(Graphics2D graphics2D, Polygon polygon) {
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setStroke(UIConfiguration.getInstance().getEventControlBorderStroke());
        graphics2D.setColor(UIConfiguration.getInstance().getEventControlFillColor());
        graphics2D.fillPolygon(polygon);
        graphics2D.setColor(UIConfiguration.getInstance().getEventControlBorderColor());
        graphics2D.drawPolygon(polygon);
    }
}
