package jDistsim.application.designer.model;

import jDistsim.core.simulation.modules.IModuleFactory;
import jDistsim.core.simulation.modules.IModuleDescription;
import jDistsim.core.simulation.modules.IModuleView;

/**
 * Author: Jirka Pénzeš
 * Date: 9.11.12
 * Time: 19:26
 */
public class ToolboxModelItem implements Comparable<ToolboxModelItem> {

    private int index;
    private IModuleView IModuleView;
    private IModuleDescription moduleDescription;
    private IModuleFactory moduleFactory;
    private String identifier;

    public ToolboxModelItem(int index, IModuleView IModuleView, IModuleDescription moduleDescription, IModuleFactory moduleFactory, String identifier) {
        this.index = index;
        this.IModuleView = IModuleView;
        this.moduleDescription = moduleDescription;
        this.moduleFactory = moduleFactory;
        this.identifier = identifier;
    }

    public int getIndex() {
        return index;
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

    @Override
    public int compareTo(ToolboxModelItem toolboxModelItem) {
        return new Integer(getIndex()).compareTo(toolboxModelItem.getIndex());
    }
}