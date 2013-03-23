package jDistsim.core.simulation.modules.lib.create;

import jDistsim.core.simulation.modules.IModuleView;
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
        Module module = new Create(moduleConfiguration);
        module.addOutputPoint(new ModuleConnectedPoint(1));
        return module;
    }

    @Override
    public IModuleView createView() {
        return new CreateView(moduleConfiguration.getColorScheme());
    }
}
