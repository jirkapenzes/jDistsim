package jDistsim.core.simulation.modules.lib.sender;

import jDistsim.core.simulation.distributed.SenderSettings;
import jDistsim.core.simulation.modules.IModuleView;
import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.ModuleConnectedPoint;
import jDistsim.core.simulation.modules.ModuleSettings;
import jDistsim.core.simulation.modules.lib.BaseModuleFactory;

/**
 * Author: Jirka Pénzeš
 * Date: 1.3.13
 * Time: 20:27
 */
public class SenderFactory extends BaseModuleFactory {

    @Override
    public Module create() {
        return create(new SenderSettings(moduleConfiguration.getBaseIdentifier()), true);
    }

    @Override
    public Module create(ModuleSettings settings) {
        return create(settings, false);
    }

    private Module create(ModuleSettings settings, boolean defaultInitialization) {
        Module module = new Sender((SenderSettings) settings, defaultInitialization);
        module.addInputPoint(new ModuleConnectedPoint(Integer.MAX_VALUE));
        return module;
    }

    @Override
    public IModuleView createView() {
        return new SenderView(moduleConfiguration.getColorScheme());
    }
}
