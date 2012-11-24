package jDistsim.core.simulation.event.library;

import jDistsim.core.modules.IModuleFactory;
import jDistsim.core.simulation.event.description.IModuleDescription;
import jDistsim.core.modules.IModuleView;
import jDistsim.ui.module.ModuleView;
import jDistsim.utils.ioc.IObjectContainer;
import jDistsim.utils.ioc.ObjectContainer;

/**
 * Author: Jirka Pénzeš
 * Date: 10.11.12
 * Time: 17:48
 */
public class ModuleContainer {

    private IObjectContainer objectContainer;

    public ModuleContainer() {
        objectContainer = new ObjectContainer();
    }

    public ModuleContainer toView(ModuleView view) {
        objectContainer.bind(IModuleView.class, view);
        return this;
    }

    public ModuleContainer toDescription(IModuleDescription createDescription) {
        objectContainer.bind(IModuleDescription.class, createDescription);
        return this;
    }

    public ModuleContainer toFactory(IModuleFactory createDescription) {
        objectContainer.bind(IModuleFactory.class, createDescription);
        return this;
    }

    public IModuleView getView() {
        return (IModuleView) objectContainer.get(IModuleView.class);
    }

    public IModuleDescription getDescription() {
        return (IModuleDescription) objectContainer.get(IModuleDescription.class);
    }

    public IModuleFactory getFactory() {
        return (IModuleFactory) objectContainer.get(IModuleFactory.class);
    }
}
