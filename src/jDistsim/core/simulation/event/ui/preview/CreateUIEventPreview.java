package jDistsim.core.simulation.event.ui.preview;

import jDistsim.core.simulation.event.library.create.ICreateEventDescription;
import jDistsim.core.simulation.event.library.create.ICreateUIEventPreview;
import jDistsim.core.simulation.event.description.IEventDescription;
import jDistsim.core.simulation.event.ui.UIDrawingHelper;
import jDistsim.utils.math.PointUtilities;

import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 6.10.12
 * Time: 20:27
 */
public class CreateUIEventPreview implements ICreateUIEventPreview {

    private ICreateEventDescription createEventDescription;

    public CreateUIEventPreview(ICreateEventDescription createEventDescription) {
        this.createEventDescription = createEventDescription;
    }

    @Override
    public void display(Graphics2D graphics2D, int width, int height) {
        Polygon polygon = new Polygon();
        polygon.addPoint(0, PointUtilities.byPercentageOnLine(10, height));
        polygon.addPoint(PointUtilities.byPercentageOnLine(70, width), PointUtilities.byPercentageOnLine(10, height));
        polygon.addPoint(width - 1, height / 2);
        polygon.addPoint(PointUtilities.byPercentageOnLine(70, width), PointUtilities.byPercentageOnLine(90, height));
        polygon.addPoint(0, PointUtilities.byPercentageOnLine(90, height));

        new UIDrawingHelper().drawAsPolygon(graphics2D, polygon);
    }

    @Override
    public IEventDescription getEventDescription() {
        return createEventDescription;
    }
}
