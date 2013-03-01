package jDistsim.core.simulation.simulator;

import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.model.ISimulationModelValidator;

/**
 * Author: Jirka Pénzeš
 * Date: 19.2.13
 * Time: 0:26
 */
public class DistributedSimulator extends BaseSimulator {

    public DistributedSimulator(ISimulationModelValidator modelValidator) {
        super(modelValidator);
    }

    public DistributedSimulator(ISimulationModelValidator modelValidator, ISimulatorEndCondition endCondition) {
        super(modelValidator, endCondition);
    }

    @Override
    protected void classification(Module module) {

    }

    @Override
    protected boolean canExecute() {
        return true;
    }
}
