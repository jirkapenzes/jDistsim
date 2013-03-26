package jDistsim.core.common;

import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.ModuleSettings;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 24.3.13
 * Time: 2:06
 */
public class SaveContainer {

    private String module;
    private ModuleSettings settings;
    private Point location;
    private List<SaveConnect> in;
    private List<SaveConnect> out;

    public SaveContainer(Module module, Point location) {
        this.module = module.getClass().getName();
        this.settings = module.getSettings();
        this.location = location;
        this.in = new ArrayList<>();
        this.out = new ArrayList<>();
    }

    public String getModule() {
        return module;
    }

    public ModuleSettings getSettings() {
        return settings;
    }

    public Point getLocation() {
        return location;
    }

    public List<SaveConnect> getIn() {
        return in;
    }

    public List<SaveConnect> getOut() {
        return out;
    }
}
