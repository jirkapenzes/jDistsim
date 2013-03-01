package jDistsim.core.simulation.simulator;


import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.animation.ISimulationAnimator;
import jDistsim.core.simulation.simulator.entity.Entity;
import jDistsim.core.simulation.exception.SimulatorCoreException;

/**
 * Author: Jirka Pénzeš
 * Date: 18.2.13
 * Time: 10:39
 */
public interface ISimulator {

    double getLocalTime();

    void plan(double time, Module module, Entity entity);

    void simulate(ISimulationModel simulationModel) throws InterruptedException, SimulatorCoreException;

    void stop();

    void setAnimator(ISimulationAnimator animator);

    SimulatorEnvironment getEnvironment();

    SimulatorOutput getOutput();
}
