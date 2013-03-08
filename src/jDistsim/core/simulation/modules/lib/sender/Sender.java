package jDistsim.core.simulation.modules.lib.sender;

import jDistsim.core.simulation.distributed.DistributedModelDefinition;
import jDistsim.core.simulation.distributed.DistributedSenderModule;
import jDistsim.core.simulation.modules.ModuleConfiguration;
import jDistsim.core.simulation.modules.common.ModuleProperty;
import jDistsim.core.simulation.simulator.ISimulator;
import jDistsim.core.simulation.simulator.entity.Entity;
import jDistsim.ui.module.ModuleView;

/**
 * Author: Jirka Pénzeš
 * Date: 1.3.13
 * Time: 20:27
 */
public class Sender extends DistributedSenderModule {


    public Sender(ModuleView view, ModuleConfiguration moduleConfiguration) {
        super(view, moduleConfiguration);
    }

    @Override
    protected void initializeDefaultValues() {
        distributedModelDefinition = DistributedModelDefinition.createNull();
        distributedEntityKeyName = "entity";
    }

    @Override
    protected void resetStates(ISimulator simulator) {
    }

    @Override
    protected void logic(ISimulator simulator, Entity entity) {
    }

    @Override
    protected void setChildProperty() {
        getProperties().set(new ModuleProperty("d.address", distributedModelDefinition.getAddress(), "d.address"));
        getProperties().set(new ModuleProperty("d.rmiModelName", distributedModelDefinition.getRmiModelName(), "d.rmi-name"));
        getProperties().set(new ModuleProperty("d.port", distributedModelDefinition.getPort(), "d.port"));
        getProperties().set(new ModuleProperty("d.modelName", distributedModelDefinition.getModelName(), "d.name"));
        getProperties().set(new ModuleProperty("d.lookahead", distributedModelDefinition.isLookahead(), "d.lookahead"));
        getProperties().set(new ModuleProperty("d.entity", distributedEntityKeyName, "d.lookahead"));
    }
}
