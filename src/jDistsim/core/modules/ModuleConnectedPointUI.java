package jDistsim.core.modules;

import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 31.12.12
 * Time: 0:23
 */
public class ModuleConnectedPointUI {

    public enum Type {
        INPUT, OUTPUT
    }

    private Type type;
    private Point location;
    private ModuleUI owner;

    public ModuleConnectedPointUI(Type type, Point location, ModuleUI owner) {
        this.type = type;
        this.location = location;
        this.owner = owner;
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
}
