package jDistsim.core.simulation.model;

import jDistsim.application.designer.common.Application;
import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.ui.ModuleUI;
import jDistsim.core.simulation.simulator.ISimulationModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 21.2.13
 * Time: 22:15
 */
public final class SimulationModelBuilder {

    public static ISimulationModel buildSimulationModelFromUI(Collection<ModuleUI> moduleUIs) {
        List<Module> modules = new ArrayList<>();
        for (ModuleUI moduleUI : moduleUIs)
            modules.add(moduleUI.getModule());

        return new SimulationModel(modules, Application.global().getModelName());
    }
}
