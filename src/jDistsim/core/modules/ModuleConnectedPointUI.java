package jDistsim.core.modules;

import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 31.12.12
 * Time: 0:23
 */
public class ModuleConnectedPointUI {

    public Type getType() {
        return type;
    }

    public enum Type {
        INPUT, OUTPUT
    }

    private Type type;
    private Point location;

    public ModuleConnectedPointUI(Type type, Point location) {
        this.type = type;
        this.location = location;
    }

    public Point getLocation() {
        return location;
    }
}
