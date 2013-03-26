package jDistsim.core.simulation.modules.lib.create;

import jDistsim.core.simulation.modules.*;
import jDistsim.core.simulation.modules.lib.BaseModuleFactory;

/**
 * Author: Jirka Pénzeš
 * Date: 24.11.12
 * Time: 13:00
 */
public class CreateFactory extends BaseModuleFactory {

    @Override
    public Module create() {
        return create(new RootSettings(moduleConfiguration.getBaseIdentifier()), true);
    }

    @Override
    public Module create(ModuleSettings settings) {
        return create(settings, false);
    }

    private Module create(ModuleSettings settings, boolean defaultInitialization) {
        Module module = new Create((RootSettings) settings, defaultInitialization);
        module.addOutputPoint(new ModuleConnectedPoint(1));
        return module;
    }

    @Override
    public IModuleView createView() {
        return new CreateView(moduleConfiguration.getColorScheme());
    }
}
