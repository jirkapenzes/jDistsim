package jDistsim.core.simulation.modules;

import jDistsim.core.simulation.distributed.DistributedModelDefinition;

/**
 * Author: Jirka Pénzeš
 * Date: 24.3.13
 * Time: 12:12
 */
public abstract class ModuleSettings {

    private String identifier;
    private String baseIdentifier;
    private DistributedModelDefinition distributedModelDefinition;

    public ModuleSettings(String baseIdentifier) {
        this.baseIdentifier = baseIdentifier;
        this.identifier = "";
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getBaseIdentifier() {
        return baseIdentifier;
    }

    public void setBaseIdentifier(String baseIdentifier) {
        this.baseIdentifier = baseIdentifier;
    }
}
