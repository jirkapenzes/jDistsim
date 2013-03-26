package jDistsim.core.simulation.modules.lib.receiver;

import jDistsim.core.simulation.distributed.DistributedReceiveModule;
import jDistsim.core.simulation.distributed.DistributedSimulator;
import jDistsim.core.simulation.distributed.ReceiveSettings;
import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.common.ModuleProperty;
import jDistsim.core.simulation.simulator.entity.Attribute;
import jDistsim.core.simulation.simulator.entity.Entity;

/**
 * Author: Jirka Pénzeš
 * Date: 5.3.13
 * Time: 17:11
 */
public class Receiver extends DistributedReceiveModule {

    public Receiver(ReceiveSettings receiveSettings, boolean defaultInitialize) {
        super(receiveSettings, defaultInitialize);
        createdModule = true;
    }

    @Override
    protected void preInitialization() {
    }

    @Override
    protected void initializeDefaultValues() {
        settings.setAuthorizedEntityName("entity");
    }

    @Override
    protected void resetStates(DistributedSimulator simulator) {
    }

    @Override
    protected void logic(DistributedSimulator simulator, Entity entity) {
        double localTime = simulator.getLocalTime();

        entity.getAttributes().set(new Attribute("distributed", "true"));
        for (Module module : getAllOutputDependencies()) {
            simulator.plan(localTime, module, entity);
        }
    }

    @Override
    protected void setChildProperty() {
        getProperties().set(new ModuleProperty("d.entity", settings.getAuthorizedEntityName(), "d.entity"));
    }
}
