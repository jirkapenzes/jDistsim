package jDistsim.core.simulation.modules.lib.outputer;

import jDistsim.core.simulation.modules.IModuleView;
import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.ModuleConnectedPoint;
import jDistsim.core.simulation.modules.lib.BaseModuleFactory;

/**
 * Author: Jirka Pénzeš
 * Date: 13.3.13
 * Time: 23:29
 */
public class OutputerFactory extends BaseModuleFactory {

    @Override
    public Module create() {
        Module module = new Outputer(new OutputerSettings(moduleConfiguration.getBaseIdentifier()));
        module.addOutputPoint(new ModuleConnectedPoint(1));
        module.addInputPoint(new ModuleConnectedPoint(Integer.MAX_VALUE));
        return module;
    }

    @Override
    public IModuleView createView() {
        return new OutputerView(moduleConfiguration.getColorScheme());
    }
}
