package jDistsim.core.simulation.distributed;

import jDistsim.core.simulation.modules.ModuleConfiguration;

/**
 * Author: Jirka Pénzeš
 * Date: 7.3.13
 * Time: 21:18
 */
public abstract class DistributedSenderModule extends DistributedModule {

    protected DistributedModelDefinition distributedModelDefinition;
    protected String distributedEntityKeyName;

    public DistributedSenderModule(ModuleConfiguration moduleConfiguration) {
        super(moduleConfiguration);
    }

    public DistributedModelDefinition getDistributedModelDefinition() {
        return distributedModelDefinition;
    }

    public void setDistributedModelDefinition(DistributedModelDefinition distributedModelDefinition) {
        this.distributedModelDefinition = distributedModelDefinition;
        setChanged();
    }

    public String getDistributedEntityKeyName() {
        return distributedEntityKeyName;
    }

    public void setDistributedEntityKeyName(String distributedEntityKeyName) {
        this.distributedEntityKeyName = distributedEntityKeyName;
        setChanged();
    }
}
