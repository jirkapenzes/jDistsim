package jDistsim.core.simulation.simulator;


import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.animation.ISimulationAnimator;
import jDistsim.core.simulation.simulator.entity.Entity;
import jDistsim.core.simulation.exception.SimulatorCoreException;
import jDistsim.utils.pattern.dispose.IDisposable;

import java.io.Serializable;

/**
 * Author: Jirka Pénzeš
 * Date: 18.2.13
 * Time: 10:39
 */
public interface ISimulator extends IDisposable, Serializable {

    double getLocalTime();

    void plan(double time, Module module, Entity entity);

    void simulate(ISimulationModel simulationModel) throws SimulatorCoreException;

    void stop();

    void setAnimator(ISimulationAnimator animator);

    SimulatorEnvironment getEnvironment();

    SimulatorOutput getOutput();
}
