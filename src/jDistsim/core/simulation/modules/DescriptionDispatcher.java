package jDistsim.core.simulation.modules;

import jDistsim.utils.resource.ModulesDescription;

/**
 * Author: Jirka Pénzeš
 * Date: 1.3.13
 * Time: 21:44
 */
public class DescriptionDispatcher implements IModuleDescription {

    private ModuleConfiguration configuration;
    private ModulesDescription description;

    public DescriptionDispatcher() {
        description = new ModulesDescription();
    }

    public void setConfiguration(ModuleConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public String getTitle() {
        return description.resolveTitle(configuration.getBaseIdentifier());
    }

    @Override
    public String getDescription() {
        return description.resolveText(configuration.getBaseIdentifier());
    }
}
