package jDistsim.core.simulation.modules.lib.create;

import jDistsim.core.simulation.modules.IModuleFactory;
import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.ModuleConfiguration;
import jDistsim.core.simulation.modules.ModuleConnectedPoint;

/**
 * Author: Jirka Pénzeš
 * Date: 24.11.12
 * Time: 13:00
 */
public class CreateFactory implements IModuleFactory {

    private int currentNumber;
    private ModuleConfiguration moduleConfiguration;

    public CreateFactory() {
    }

    public String createIdentifier() {
        return "create_" + ++currentNumber;
    }

    public void setModuleConfiguration(ModuleConfiguration moduleConfiguration) {
        this.moduleConfiguration = moduleConfiguration;
    }

    @Override
    public Module create() {
        Module module = new Create(new CreateView(moduleConfiguration.getColorScheme()), moduleConfiguration);
        module.addOutputPoint(new ModuleConnectedPoint(1));
        return module;
    }
}
