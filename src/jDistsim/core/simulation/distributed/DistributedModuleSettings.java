package jDistsim.core.simulation.distributed;

import jDistsim.core.simulation.modules.ModuleSettings;

/**
 * Author: Jirka Pénzeš
 * Date: 24.3.13
 * Time: 13:07
 */
public class DistributedModuleSettings extends ModuleSettings {

    private DistributedModelDefinition distributedModelDefinition;

    public DistributedModuleSettings(String baseIdentifier) {
        super(baseIdentifier);
    }

    public DistributedModelDefinition getDistributedModelDefinition() {
        return distributedModelDefinition;
    }

    public void setDistributedModelDefinition(DistributedModelDefinition distributedModelDefinition) {
        this.distributedModelDefinition = distributedModelDefinition;
    }
}
