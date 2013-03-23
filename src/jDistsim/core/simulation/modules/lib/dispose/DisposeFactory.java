package jDistsim.core.simulation.modules.lib.dispose;

import jDistsim.core.simulation.modules.IModuleView;
import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.ModuleConnectedPoint;
import jDistsim.core.simulation.modules.lib.BaseModuleFactory;

/**
 * Author: Jirka Pénzeš
 * Date: 3.12.12
 * Time: 22:43
 */
public class DisposeFactory extends BaseModuleFactory {

    @Override
    public Module create() {
        Module module = new Dispose(moduleConfiguration);
        module.addInputPoint(new ModuleConnectedPoint(Integer.MAX_VALUE));
        return module;
    }

    @Override
    public IModuleView createView() {
        return new DisposeView(moduleConfiguration.getColorScheme());
    }
}
