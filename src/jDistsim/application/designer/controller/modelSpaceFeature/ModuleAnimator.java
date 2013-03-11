package jDistsim.application.designer.controller.modelSpaceFeature;

import jDistsim.application.designer.common.UIConfiguration;
import jDistsim.application.designer.controller.ModuleConnector;
import jDistsim.application.designer.controller.modelSpaceFeature.util.ConnectorLine;
import jDistsim.core.simulation.modules.ui.ModuleConnectedPointUI;
import jDistsim.core.simulation.modules.ui.ModuleUI;
import jDistsim.core.simulation.animation.ISimulationAnimator;
import jDistsim.ui.control.EntityControl;
import jDistsim.ui.module.ColorScheme;
import jDistsim.utils.collection.observable.ObservableHashMap;
import jDistsim.utils.resource.Resources;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 22.2.13
 * Time: 23:15
 */
public class ModuleAnimator implements ISimulationAnimator {

    private ObservableHashMap<String, ModuleUI> modules;
    private JComponent contentPane;
    private ColorScheme colorScheme = UIConfiguration.getInstance().getColorSchemeForAnimateActionModule();

    public ModuleAnimator(ObservableHashMap<String, ModuleUI> modules, JComponent contentPane) {
        this.modules = modules;
        this.contentPane = contentPane;
    }

    @Override
    public void animate(String moduleFrom, String moduleTo, String iconName) {
        ModuleUI moduleUIFrom = modules.get(moduleFrom);
        ModuleUI moduleUITo = modules.get(moduleTo);

        if (moduleUIFrom == null && moduleUITo == null)
            return;

        if (moduleUIFrom == null) {
            moduleUITo.setColorScheme(colorScheme);
            waitTime(100);
            moduleUITo.setDefaultBackgroundColor();
            return;
        }

        ConnectorLine connectorLine = null;
        for (ModuleConnectedPointUI connectedPointUI : moduleUIFrom.getConnectedPoints()) {
            for (ModuleConnector moduleConnector : connectedPointUI.getDependencies().values()) {
                if (moduleConnector.getModuleB().getIdentifier().equals(moduleTo)) {
                    connectorLine = moduleConnector.getConnectorLine();
                    break;
                }
            }
        }

        if (connectorLine == null)
            return;

        moduleUIFrom.setColorScheme(colorScheme);
        waitTime(100);
        moduleUIFrom.setDefaultBackgroundColor();

        java.util.List<Point> points = connectorLine.getPoints();
        Dimension entityDimension = new Dimension(24, 24);
        EntityControl entityControl = new EntityControl(Resources.getImage("system/entity/" + iconName + ".png"), entityDimension);
        entityControl.setLocation(points.get(0).x + connectorLine.getLocation().x - (entityDimension.width / 2), points.get(0).y + connectorLine.getLocation().y - (entityDimension.height / 2));

        contentPane.add(entityControl, 0);
        contentPane.repaint();

        for (int i = 0; i < points.size() - 1; i++) {
            Point pointA = points.get(i);
            Point pointB = points.get(i + 1);

            if (pointA.y == pointB.y) {
                double dX = Math.abs(pointA.x - pointB.x);
                double k = dX / 2;
                double direction = pointA.x > pointB.x ? -1 : 1;
                int move = (int) (2 * direction);

                for (int j = 0; j < k; j++) {
                    Point currentLocation = entityControl.getLocation();
                    entityControl.setLocation(new Point(currentLocation.x + move, currentLocation.y));
                    waitTime(10);
                }
            } else {
                double dY = Math.abs(pointA.y - pointB.y);
                double k = dY / 2;
                double direction = pointA.y > pointB.y ? -1 : 1;
                int move = (int) (2 * direction);

                for (int j = 0; j < k; j++) {
                    Point currentLocation = entityControl.getLocation();
                    entityControl.setLocation(new Point(currentLocation.x, currentLocation.y + move));
                    waitTime(10);
                }
            }
        }
        contentPane.remove(entityControl);
        contentPane.repaint();

        moduleUITo.setColorScheme(colorScheme);
        waitTime(100);
        moduleUITo.setDefaultBackgroundColor();

    }

    private void waitTime(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
