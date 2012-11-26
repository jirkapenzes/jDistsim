package jDistsim.core.modules;

import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 24.11.12
 * Time: 21:05
 */
public class ModuleConfiguration {

    private String baseIdentifier;
    private Dimension moduleSize;

    public ModuleConfiguration(String baseIdentifier, Dimension moduleSize) {
        this.baseIdentifier = baseIdentifier;
        this.moduleSize = moduleSize;
    }

    public String getBaseIdentifier() {
        return baseIdentifier;
    }

    public Dimension getModuleSize() {
        return moduleSize;
    }
}
