package jDistsim.core.simulation.modules.lib;

import jDistsim.core.simulation.modules.IModuleFactory;
import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.ModuleConfiguration;
import jDistsim.utils.common.Counter;

/**
 * Author: Jirka Pénzeš
 * Date: 1.3.13
 * Time: 20:32
 */
public abstract class BaseModuleFactory implements IModuleFactory {

    private Counter counter;
    protected ModuleConfiguration moduleConfiguration;

    public BaseModuleFactory() {
        counter = new Counter();
    }

    @Override
    public abstract Module create();

    @Override
    public String createIdentifier() {
        return moduleConfiguration.getBaseIdentifier() + "_" + counter.nextValue();
    }

    @Override
    public void setModuleConfiguration(ModuleConfiguration moduleConfiguration) {
        this.moduleConfiguration = moduleConfiguration;
    }

    protected Counter getCounter() {
        return counter;
    }
}
