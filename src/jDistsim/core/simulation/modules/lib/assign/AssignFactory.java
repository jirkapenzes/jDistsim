package jDistsim.core.simulation.modules.lib.assign;

import jDistsim.core.simulation.modules.IModuleView;
import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.ModuleConnectedPoint;
import jDistsim.core.simulation.modules.ModuleSettings;
import jDistsim.core.simulation.modules.lib.BaseModuleFactory;

/**
 * Author: Jirka Pénzeš
 * Date: 3.3.13
 * Time: 13:39
 */
public class AssignFactory extends BaseModuleFactory {

    @Override
    public Module create() {
        return create(new AssignSettings(moduleConfiguration.getBaseIdentifier()), false);
    }

    @Override
    public Module create(ModuleSettings settings) {
        return create(settings, false);
    }

    private Module create(ModuleSettings settings, boolean defaultInitialization) {
        Module module = new Assign((AssignSettings) settings, defaultInitialization);
        module.addOutputPoint(new ModuleConnectedPoint(1));
        module.addInputPoint(new ModuleConnectedPoint(Integer.MAX_VALUE));
        return module;
    }

    @Override
    public IModuleView createView() {
        return new AssignView(moduleConfiguration.getColorScheme());
    }
}
