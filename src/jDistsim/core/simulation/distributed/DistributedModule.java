package jDistsim.core.simulation.distributed;

import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.ModuleConfiguration;
import jDistsim.ui.module.ModuleView;

/**
 * Author: Jirka Pénzeš
 * Date: 1.3.13
 * Time: 20:43
 */
public abstract class DistributedModule extends Module {

    private DistributedModelDefinition distributedModelDefinition;

    public DistributedModule(ModuleView view, ModuleConfiguration moduleConfiguration) {
        super(view, moduleConfiguration);
    }

    public DistributedModelDefinition getDistributedModelDefinition() {
        return distributedModelDefinition;
    }
}
