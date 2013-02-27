package jDistsim.core.simulation.simulator;

import jDistsim.core.modules.Module;
import jDistsim.core.modules.RootModule;

/**
 * Author: Jirka Pénzeš
 * Date: 18.2.13
 * Time: 22:10
 */
public interface ISimulationModel {

    String getModelName();

    Iterable<RootModule> getRootModules();

    Iterable<Module> getModules();

    boolean containsModule(String identifier);

}
