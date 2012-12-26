package jDistsim.demo;

import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 23.12.12
 * Time: 20:36
 */
public interface ISimulationModel {

    public List<SimulationModule> getStartedModules();

    public List<SimulationModule> getModules();

    public void addSimulationModule(SimulationModule simulationModule);

    public void removeSimulationModule(SimulationModule simulationModule);
}
