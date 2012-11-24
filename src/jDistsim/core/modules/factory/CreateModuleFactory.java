package jDistsim.core.modules.factory;

import jDistsim.core.modules.IModuleFactory;
import jDistsim.core.modules.Module;
import jDistsim.core.modules.ModuleUI;
import jDistsim.core.modules.module.CreateModule;
import jDistsim.ui.module.moduleView.CreateModuleView;

/**
 * Author: Jirka Pénzeš
 * Date: 24.11.12
 * Time: 13:00
 */
public class CreateModuleFactory implements IModuleFactory {

    @Override
    public Module create() {
        return new CreateModule(new ModuleUI(new CreateModuleView()));
    }
}
