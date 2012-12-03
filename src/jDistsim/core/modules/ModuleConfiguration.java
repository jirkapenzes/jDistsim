package jDistsim.core.modules;

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
    private ArrayList<ModulePoint> connectingInputPoints;
    private ArrayList<ModulePoint> connectingOutputPoints;

    public ModuleConfiguration(String baseIdentifier, Dimension moduleSize) {
        this.baseIdentifier = baseIdentifier;
        this.moduleSize = moduleSize;
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
}
