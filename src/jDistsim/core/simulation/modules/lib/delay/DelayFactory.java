package jDistsim.core.simulation.modules.lib.delay;

import jDistsim.core.simulation.modules.IModuleView;
import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.ModuleConnectedPoint;
import jDistsim.core.simulation.modules.lib.BaseModuleFactory;

/**
 * Author: Jirka Pénzeš
 * Date: 1.2.13
 * Time: 21:42
 */
public class DelayFactory extends BaseModuleFactory {

    @Override
    public Module create() {
        Module module = new Delay(new DelaySettings(moduleConfiguration.getBaseIdentifier()));
        module.addOutputPoint(new ModuleConnectedPoint(1));
        module.addInputPoint(new ModuleConnectedPoint(Integer.MAX_VALUE));
        return module;
    }

    @Override
    public IModuleView createView() {
        return new DelayView(moduleConfiguration.getColorScheme());
    }
}
