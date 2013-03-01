package jDistsim.application.designer.model;

import jDistsim.core.simulation.modules.IModuleFactory;
import jDistsim.core.simulation.modules.IModuleDescription;
import jDistsim.core.simulation.modules.IModuleView;

/**
 * Author: Jirka Pénzeš
 * Date: 9.11.12
 * Time: 19:26
 */
public class ToolboxModelItem {

    private IModuleView IModuleView;
    private IModuleDescription moduleDescription;
    private IModuleFactory moduleFactory;
    private String identifier;

    public ToolboxModelItem(IModuleView IModuleView, IModuleDescription moduleDescription, IModuleFactory moduleFactory, String identifier) {
        this.IModuleView = IModuleView;
        this.moduleDescription = moduleDescription;
        this.moduleFactory = moduleFactory;
        this.identifier = identifier;
    }

    public IModuleView getComponentView() {
        return IModuleView;
    }

    public IModuleDescription getModuleDescription() {
        return moduleDescription;
    }

    public IModuleFactory getModuleFactory() {
        return moduleFactory;
    }

    public String getIdentifier() {
        return identifier;
    }
}