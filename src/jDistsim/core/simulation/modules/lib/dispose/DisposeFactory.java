package jDistsim.core.simulation.modules.lib.dispose;

import jDistsim.core.simulation.modules.IModuleFactory;
import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.ModuleConfiguration;
import jDistsim.core.simulation.modules.ModuleConnectedPoint;

/**
 * Author: Jirka Pénzeš
 * Date: 3.12.12
 * Time: 22:43
 */
public class DisposeFactory implements IModuleFactory {

    private int currentNumber;
    private ModuleConfiguration moduleConfiguration;

    public DisposeFactory() {
    }

    public String createIdentifier() {
        return moduleConfiguration.getBaseIdentifier() + "_" + ++currentNumber;
    }

    @Override
    public void setModuleConfiguration(ModuleConfiguration moduleConfiguration) {
        this.moduleConfiguration = moduleConfiguration;
    }

    @Override
    public Module create() {
        Module module = new Dispose(new DisposeView(moduleConfiguration.getColorScheme()), moduleConfiguration);
        module.addInputPoint(new ModuleConnectedPoint(Integer.MAX_VALUE));
        return module;
    }
}