package jDistsim.core.simulation.modules.lib.create;

import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.ModuleConnectedPoint;
import jDistsim.core.simulation.modules.lib.BaseModuleFactory;

/**
 * Author: Jirka Pénzeš
 * Date: 24.11.12
 * Time: 13:00
 */
public class CreateFactory extends BaseModuleFactory {

    @Override
    public Module create() {
        Module module = new Create(new CreateView(moduleConfiguration.getColorScheme()), moduleConfiguration);
        module.addOutputPoint(new ModuleConnectedPoint(1));
        return module;
    }
}
