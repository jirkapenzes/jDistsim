package jDistsim.core.simulation.modules;

/**
 * Author: Jirka Pénzeš
 * Date: 24.11.12
 * Time: 12:15
 */
public interface IModuleFactory {

    public Module create();

    public IModuleView createView();

    public String createIdentifier();

    void setModuleConfiguration(ModuleConfiguration moduleConfiguration);
}
