package jDistsim.core.modules.factory;

import jDistsim.core.modules.IModuleFactory;
import jDistsim.core.modules.Module;
import jDistsim.core.modules.ModuleConfiguration;
import jDistsim.core.modules.ModuleConnectedPoint;
import jDistsim.core.modules.lib.DisposeModule;
import jDistsim.ui.module.moduleView.DisposeModuleView;

/**
 * Author: Jirka Pénzeš
 * Date: 3.12.12
 * Time: 22:43
 */
public class DisposeModuleFactory implements IModuleFactory {

    private int currentNumber;
    private ModuleConfiguration moduleConfiguration;

    public DisposeModuleFactory() {
    }

    public String createIdentifier() {
        return moduleConfiguration.getBaseIdentifier() + "_" + ++currentNumber;
    }

    @Override
    public void setModuleConfiguration(ModuleConfiguration moduleConfiguration) {
        this.moduleConfiguration = moduleConfiguration;
    }

    @Override
    public Module create() {
        Module module = new DisposeModule(new DisposeModuleView(moduleConfiguration.getColorScheme()), moduleConfiguration);
        module.addInputPoint(new ModuleConnectedPoint(Integer.MAX_VALUE));
        return module;
    }
}
