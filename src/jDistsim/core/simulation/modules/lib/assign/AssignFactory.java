package jDistsim.core.simulation.modules.lib.assign;

import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.ModuleConnectedPoint;
import jDistsim.core.simulation.modules.lib.BaseModuleFactory;

/**
 * Author: Jirka Pénzeš
 * Date: 3.3.13
 * Time: 13:39
 */
public class AssignFactory extends BaseModuleFactory {
    @Override
    public Module create() {
        Module module = new Assign(new AssignView(moduleConfiguration.getColorScheme()), moduleConfiguration);
        module.addOutputPoint(new ModuleConnectedPoint(1));
        module.addInputPoint(new ModuleConnectedPoint(Integer.MAX_VALUE));
        return module;
    }
}
