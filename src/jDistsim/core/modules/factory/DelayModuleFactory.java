package jDistsim.core.modules.factory;

import jDistsim.core.modules.IModuleFactory;
import jDistsim.core.modules.Module;
import jDistsim.core.modules.ModuleConfiguration;
import jDistsim.core.modules.ModuleConnectedPoint;
import jDistsim.core.modules.lib.DelayModule;
import jDistsim.ui.module.moduleView.DelayModuleView;

/**
 * Author: Jirka Pénzeš
 * Date: 1.2.13
 * Time: 21:42
 */
public class DelayModuleFactory implements IModuleFactory {

    private int currentNumber;
    private ModuleConfiguration moduleConfiguration;

    public DelayModuleFactory() {
    }

    public String createIdentifier() {
        return "delay" + ++currentNumber;
    }

    @Override
    public void setModuleConfiguration(ModuleConfiguration moduleConfiguration) {
        this.moduleConfiguration = moduleConfiguration;
    }

    @Override
    public Module create() {
        Module module = new DelayModule(new DelayModuleView(moduleConfiguration.getColorScheme()), moduleConfiguration);
        module.addOutputPoint(new ModuleConnectedPoint(1));
        module.addInputPoint(new ModuleConnectedPoint(Integer.MAX_VALUE));
        return module;
    }
}
