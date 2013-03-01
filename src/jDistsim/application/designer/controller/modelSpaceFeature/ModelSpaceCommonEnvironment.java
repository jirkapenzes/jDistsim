package jDistsim.application.designer.controller.modelSpaceFeature;

import jDistsim.core.simulation.modules.ui.ModuleUI;

/**
 * Author: Jirka Pénzeš
 * Date: 27.12.12
 * Time: 23:51
 */
public class ModelSpaceCommonEnvironment {

    private ModuleUI currentActiveModule;

    public ModuleUI getCurrentActiveModule() {
        return currentActiveModule;
    }

    public void setCurrentActiveModule(ModuleUI currentActiveModule) {
        this.currentActiveModule = currentActiveModule;
    }
}
