package jDistsim.core.simulation.modules.ui;

import jDistsim.application.designer.controller.ModuleConnector;
import jDistsim.core.simulation.modules.ModuleConnectedPoint;

import java.awt.*;
import java.util.HashMap;

/**
 * Author: Jirka Pénzeš
 * Date: 31.12.12
 * Time: 0:23
 */
public class ModuleConnectedPointUI {

    public enum Type {
        INPUT, OUTPUT
    }

    private ModuleConnectedPoint connectedPoint;
    private Type type;
    private Point location;
    private ModuleUI owner;
    private HashMap<ModuleConnectedPointUI, ModuleConnector> dependencies;

    public ModuleConnectedPointUI(ModuleConnectedPoint parentConnectedPoint, Type type, Point location, ModuleUI owner) {
        this.connectedPoint = parentConnectedPoint;
        this.type = type;
        this.location = location;
        this.owner = owner;
        dependencies = new HashMap<>();
    }

    public ModuleConnectedPoint getParent() {
        return connectedPoint;
    }

    public Type getType() {
        return type;
    }

    public Point getLocation() {
        return location;
    }

    public ModuleUI getOwner() {
        return owner;
    }

    public ModuleConnector connect(ModuleConnectedPointUI connectedPointB) throws Exception {
        connectedPoint.addDependency(connectedPointB.getOwner().getModule());
        connectedPointB.getParent().addDependency(owner.getModule());
        ModuleConnector moduleConnector = new ModuleConnector(owner, this, connectedPointB.getOwner(), connectedPointB);
        dependencies.put(connectedPointB, moduleConnector);
        connectedPointB.dependencies.put(this, moduleConnector);
        return moduleConnector;
    }

    public void disconnect(ModuleConnectedPointUI modulePointB) {
        connectedPoint.removeDependency(modulePointB.getOwner().getModule());
        modulePointB.connectedPoint.removeDependency(this.getOwner().getModule());

        dependencies.remove(modulePointB);
        modulePointB.dependencies.remove(this);
    }

    public HashMap<ModuleConnectedPointUI, ModuleConnector> getDependencies() {
        return dependencies;
    }
}
