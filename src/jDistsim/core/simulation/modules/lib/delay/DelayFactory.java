package jDistsim.core.simulation.modules.lib.delay;

import jDistsim.core.simulation.modules.IModuleFactory;
import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.ModuleConfiguration;
import jDistsim.core.simulation.modules.ModuleConnectedPoint;

/**
 * Author: Jirka Pénzeš
 * Date: 1.2.13
 * Time: 21:42
 */
public class DelayFactory implements IModuleFactory {

    private int currentNumber;
    private ModuleConfiguration moduleConfiguration;

    public DelayFactory() {
    }

    public String createIdentifier() {
        return "delay" + ++currentNumber;
    }

    @Override
    public void setModuleConfiguration(ModuleConfiguration moduleConfiguration) {
        this.moduleConfiguration = moduleConfiguration;
    }

    @Override
    public Module create() {
        Module module = new Delay(new DelayView(moduleConfiguration.getColorScheme()), moduleConfiguration);
        module.addOutputPoint(new ModuleConnectedPoint(1));
        module.addInputPoint(new ModuleConnectedPoint(Integer.MAX_VALUE));
        return module;
    }
}
