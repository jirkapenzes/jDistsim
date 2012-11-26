package jDistsim.core.modules.factory;

import jDistsim.core.modules.IModuleFactory;
import jDistsim.core.modules.Module;
import jDistsim.core.modules.ModuleConfiguration;
import jDistsim.core.modules.ModuleUI;
import jDistsim.ui.module.moduleView.CreateModuleView;

/**
 * Author: Jirka Pénzeš
 * Date: 24.11.12
 * Time: 13:00
 */
public class CreateModuleFactory implements IModuleFactory {

    private int currentNumber;
    private ModuleConfiguration moduleConfiguration;

    public CreateModuleFactory(ModuleConfiguration moduleConfiguration) {
        this.moduleConfiguration = moduleConfiguration;
    }

    public String createIdentifier() {
        return "create_" + ++currentNumber;
    }

    @Override
    public Module create() {
        return new Module(new ModuleUI(new CreateModuleView(), moduleConfiguration.getBaseIdentifier()), moduleConfiguration);
    }
}
