package jDistsim.core.simulation.modules.lib.receiver;

import jDistsim.core.simulation.distributed.ReceiveSettings;
import jDistsim.core.simulation.modules.IModuleView;
import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.ModuleConnectedPoint;
import jDistsim.core.simulation.modules.ModuleSettings;
import jDistsim.core.simulation.modules.lib.BaseModuleFactory;

/**
 * Author: Jirka Pénzeš
 * Date: 5.3.13
 * Time: 17:11
 */
public class ReceiverFactory extends BaseModuleFactory {

    @Override
    public Module create() {
        return create(new ReceiveSettings(moduleConfiguration.getBaseIdentifier()), true);
    }

    @Override
    public Module create(ModuleSettings settings) {
        return create(settings, false);
    }

    private Module create(ModuleSettings settings, boolean defaultInitialization) {
        Module module = new Receiver((ReceiveSettings) settings, defaultInitialization);
        module.addOutputPoint(new ModuleConnectedPoint(1));
        return module;
    }

    @Override
    public IModuleView createView() {
        return new ReceiverView(moduleConfiguration.getColorScheme());
    }
}

