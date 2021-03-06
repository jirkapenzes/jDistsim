package jDistsim.core.simulation.modules;

import jDistsim.ui.module.ModuleView;
import jDistsim.utils.ioc.IObjectContainer;
import jDistsim.utils.ioc.ObjectContainer;
import jDistsim.utils.logging.Logger;

/**
 * Author: Jirka Pénzeš
 * Date: 10.11.12
 * Time: 17:48
 */
public class ModuleContainer {

    private IObjectContainer objectContainer;
    private ModuleConfiguration configuration;
    private int index;

    public ModuleContainer() {
        objectContainer = new ObjectContainer();
    }

    public String getBaseName() {
        return configuration.getBaseIdentifier();
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

    public ModuleContainer toUIFactory(IModuleUIFactory moduleUIFactory) {
        objectContainer.bind(IModuleUIFactory.class, moduleUIFactory);
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

    public IModuleUIFactory getUIFactory() {
        return (IModuleUIFactory) objectContainer.get(IModuleUIFactory.class);
    }

    public ModuleContainer withConfiguration(ModuleConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    public ModuleContainer toIndex(int index) {
        this.index = index;
        return this;
    }

    public int getIndex() {
        return index;
    }

    public void build() {
        if (getDescription() == null) {
            Logger.log("Module description not loaded -> load dispatcher");
            toDescription(new DescriptionDispatcher());
        }
        if (getView() == null)
            Logger.log("Module view not loaded");

        if (getFactory() == null)
            Logger.log("Module factory not loaded");

        if (getUIFactory() == null)
            Logger.log("Module UI factory not loaded");

        Logger.log("Module descriptor is valid");
        configure();
        Logger.log("Module configured");
    }

    private void configure() {
        getFactory().setModuleConfiguration(configuration);
        getView().setDefaultColorScheme(configuration.getColorScheme());

        if (getDescription() instanceof DescriptionDispatcher)
            ((DescriptionDispatcher) getDescription()).setConfiguration(configuration);
    }
}
