package jDistsim.application.designer.controller;

import jDistsim.application.designer.controller.modelSpaceFeature.util.ConnectorLine;
import jDistsim.core.modules.ModuleConnectedPointUI;
import jDistsim.core.modules.ModuleUI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Author: Jirka Pénzeš
 * Date: 1.2.13
 * Time: 0:47
 */
public class ModuleConnector {

    private ModuleUI moduleA;
    private ModuleConnectedPointUI modulePointA;
    private ModuleUI moduleB;
    private ModuleConnectedPointUI modulePointB;
    private ConnectorLine connectorLine;

    public ModuleConnector(ModuleUI moduleA, ModuleConnectedPointUI modulePointA, ModuleUI moduleB, ModuleConnectedPointUI modulePointB) {
        this.moduleA = moduleA;
        this.modulePointA = modulePointA;
        this.moduleB = moduleB;
        this.modulePointB = modulePointB;

        initialize();
        recalculate();
    }

    private void initialize() {
        connectorLine = new ConnectorLine();
        connectorLine.setFocusable(true);
        connectorLine.setDrawingMode(false);

        moduleA.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                recalculate();
            }
        });
        moduleB.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                recalculate();
            }
        });
    }

    public void recalculate() {
        Point pointA = new Point(moduleA.getLocation().x + modulePointA.getLocation().x, moduleA.getLocation().y + modulePointA.getLocation().y);
        Point pointB = new Point(moduleB.getLocation().x + modulePointB.getLocation().x, moduleB.getLocation().y + modulePointB.getLocation().y);
        Dimension dimension = new Dimension(Math.abs(pointA.x - pointB.x), Math.abs(pointA.y - pointB.y));

        connectorLine.setSize(dimension);
        connectorLine.setLocation(Math.min(pointA.x, pointB.x), Math.min(pointA.y, pointB.y));
        connectorLine.setPoints(pointA, pointB);
    }

    public ConnectorLine getConnectorLine() {
        return connectorLine;
    }

    public ModuleUI getModuleA() {
        return moduleA;
    }

    public ModuleConnectedPointUI getModulePointA() {
        return modulePointA;
    }

    public ModuleUI getModuleB() {
        return moduleB;
    }

    public ModuleConnectedPointUI getModulePointB() {
        return modulePointB;
    }
}
