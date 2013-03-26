package jDistsim.core.simulation.modules.lib.condition;

import jDistsim.core.simulation.modules.IModuleView;
import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.ModuleConnectedPoint;
import jDistsim.core.simulation.modules.ModuleSettings;
import jDistsim.core.simulation.modules.lib.BaseModuleFactory;

/**
 * Author: Jirka Pénzeš
 * Date: 13.3.13
 * Time: 23:59
 */
public class ConditionFactory extends BaseModuleFactory {

    @Override
    public Module create() {
        return create(new ConditionSettings(moduleConfiguration.getBaseIdentifier()), true);
    }

    @Override
    public Module create(ModuleSettings settings) {
        return create(settings, false);
    }

    private Module create(ModuleSettings settings, boolean defaultInitialization) {
        Module module = new Condition((ConditionSettings) settings, defaultInitialization);
        module.addOutputPoint(new ModuleConnectedPoint(1));
        module.addOutputPoint(new ModuleConnectedPoint(1));
        module.addInputPoint(new ModuleConnectedPoint(Integer.MAX_VALUE));
        return module;
    }

    @Override
    public IModuleView createView() {
        return new ConditionView(moduleConfiguration.getColorScheme());
    }
}
