package jDistsim.application.designer.controller.modelSpaceFeature;

import jDistsim.core.modules.ModuleConnectedPointUI;
import jDistsim.core.modules.ModuleUI;

import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 28.12.12
 * Time: 0:07
 */
public final class ModelSpaceHelper {

    public static Point calculatePointPosition(int size, ModuleConnectedPointUI connectedPointUI,  ModuleUI moduleUI) {
        int offset = size / 2;
        return new Point(
                (int) ((int) moduleUI.getLocation().getX() + connectedPointUI.getLocation().getX() - offset),
                (int) ((int) moduleUI.getLocation().getY() + connectedPointUI.getLocation().getY() - offset));
    }

    public static Point calculateDragLocation(Point location, Dimension dimension) {
        Point point = new Point();
        point.setLocation(location.getX() - (dimension.width / 2), location.getY() - (dimension.height / 2));
        return point;
    }

}
