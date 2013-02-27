package jDistsim.core.simulation.model;

import jDistsim.core.simulation.simulator.ISimulationModel;
import jDistsim.core.simulation.validator.ValidatorResult;

/**
 * Author: Jirka Pénzeš
 * Date: 21.2.13
 * Time: 22:51
 */
public interface ISimulationModelValidator {

    ValidatorResult validateModel(ISimulationModel simulationModel);
}
