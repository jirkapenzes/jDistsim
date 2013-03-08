package jDistsim.core.simulation.distributed;

import jDistsim.core.simulation.model.ISimulationModelValidator;
import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.simulator.BaseSimulator;
import jDistsim.core.simulation.simulator.ISimulatorEndCondition;
import jDistsim.utils.common.Counter;

import java.util.HashMap;

/**
 * Author: Jirka Pénzeš
 * Date: 19.2.13
 * Time: 0:26
 */
public class DistributedSimulator extends BaseSimulator {

    private HashMap<String, DistributedModelContainer> models;

    public DistributedSimulator(ISimulationModelValidator modelValidator) {
        super(modelValidator);
        models = new HashMap<>();
    }

    public DistributedSimulator(ISimulationModelValidator modelValidator, ISimulatorEndCondition endCondition) {
        super(modelValidator, endCondition);
    }

    @Override
    protected void classification(Module module) {
        if (module instanceof DistributedReceiveModule) {
            DistributedReceiveModule receiveModule = (DistributedReceiveModule) module;
            String modelName = receiveModule.getDistributedModelDefinition().getRmiModelName();


        }
    }

    @Override
    protected boolean canExecute() {
        return true;
    }

    private class DistributedModelContainer {
        private Counter counter;
    }
}
