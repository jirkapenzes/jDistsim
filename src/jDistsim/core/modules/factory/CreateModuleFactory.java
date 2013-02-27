package jDistsim.core.modules.factory;

import jDistsim.core.modules.IModuleFactory;
import jDistsim.core.modules.Module;
import jDistsim.core.modules.ModuleConfiguration;
import jDistsim.core.modules.ModuleConnectedPoint;
import jDistsim.core.modules.lib.CreateModule;
import jDistsim.ui.module.moduleView.CreateModuleView;

/**
 * Author: Jirka Pénzeš
 * Date: 24.11.12
 * Time: 13:00
 */
public class CreateModuleFactory implements IModuleFactory {

    private int currentNumber;
    private ModuleConfiguration moduleConfiguration;

    public CreateModuleFactory() {
    }

    public String createIdentifier() {
        return "create_" + ++currentNumber;
    }

    public void setModuleConfiguration(ModuleConfiguration moduleConfiguration) {
        this.moduleConfiguration = moduleConfiguration;
    }

    @Override
    public Module create() {
        Module module = new CreateModule(new CreateModuleView(moduleConfiguration.getColorScheme()), moduleConfiguration);
        module.addOutputPoint(new ModuleConnectedPoint(1));
        return module;
    }
}
