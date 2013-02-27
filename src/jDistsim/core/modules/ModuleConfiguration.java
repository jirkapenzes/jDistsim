package jDistsim.core.modules;

import jDistsim.ui.module.ColorScheme;

import java.awt.*;
import java.util.ArrayList;

/**
 * Author: Jirka Pénzeš
 * Date: 24.11.12
 * Time: 21:05
 */
public class ModuleConfiguration {

    private String baseIdentifier;
    private Dimension moduleSize;
    private ColorScheme colorScheme;
    private ArrayList<ModulePoint> connectingInputPoints;
    private ArrayList<ModulePoint> connectingOutputPoints;

    public ModuleConfiguration(String baseIdentifier, Dimension moduleSize, ColorScheme defaultColorScheme) {
        this.baseIdentifier = baseIdentifier;
        this.moduleSize = moduleSize;
        this.colorScheme = defaultColorScheme;
        connectingInputPoints = new ArrayList<>();
        connectingOutputPoints = new ArrayList<>();
    }

    public String getBaseIdentifier() {
        return baseIdentifier;
    }

    public Dimension getModuleSize() {
        return moduleSize;
    }

    public ArrayList<ModulePoint> getConnectingInputPoints() {
        return connectingInputPoints;
    }

    public ArrayList<ModulePoint> getConnectingOutputPoints() {
        return connectingOutputPoints;
    }

    public ColorScheme getColorScheme() {
        return colorScheme;
    }
}
