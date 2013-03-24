package jDistsim.core.simulation.validator;

import jDistsim.core.simulation.distributed.DistributedReceiveModule;
import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.ModuleConnectedPoint;
import jDistsim.core.simulation.modules.RootModule;
import jDistsim.core.simulation.model.ISimulationModelValidator;
import jDistsim.core.simulation.simulator.ISimulationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 21.2.13
 * Time: 22:51
 */
public class SimulationModelValidator implements ISimulationModelValidator {

    @Override
    public ValidatorResult validateModel(ISimulationModel simulationModel) {
        List<ValidatorException> exceptions = new ArrayList<>();

        if (simulationModel == null) {
            exceptions.add(new ValidatorException("Model", "model not found (null pointer)"));
            return new ValidatorResult(exceptions);
        }

        validateNumberOfRootModules(simulationModel, exceptions);
        validateUndefinedModules(simulationModel, exceptions);
        validateModules(simulationModel, exceptions);
        validateEntitiesName(simulationModel, exceptions);

        return new ValidatorResult(exceptions);
    }

    private void validateModules(ISimulationModel simulationModel, List<ValidatorException> exceptions) {
        for (Module module : simulationModel.getModules()) {
            if (!module.isValid()) {
                exceptions.add(new ValidatorException(module.getIdentifier(), "module not valid"));
            }
        }
    }

    private void validateNumberOfRootModules(ISimulationModel simulationModel, List<ValidatorException> exceptions) {
        int numberOfRootModules = 0;
        for (RootModule ignored : simulationModel.getRootModules())
            numberOfRootModules++;

        for (Module module : simulationModel.getModules()) {
            if (module instanceof DistributedReceiveModule) {
                numberOfRootModules++;
            }
        }

        if (numberOfRootModules == 0)
            exceptions.add(new ValidatorException("Model", "it does not contain the root modules"));
    }

    private void validateUndefinedModules(ISimulationModel simulationModel, List<ValidatorException> exceptions) {
        for (RootModule rootModule : simulationModel.getRootModules()) {
            if (!simulationModel.containsModule(rootModule.getIdentifier()))
                exceptions.add(new ValidatorException(rootModule.getIdentifier(), "not defined in model"));

            for (ModuleConnectedPoint connectedPoint : rootModule.getOutputConnectedPoints()) {
                for (Module module : connectedPoint.getDependencies())
                    continueSearch(module, simulationModel, exceptions);
            }
        }
    }

    private void continueSearch(Module parentModule, ISimulationModel simulationModel, List<ValidatorException> exceptions) {
        if (!simulationModel.containsModule(parentModule.getIdentifier()))
            exceptions.add(new ValidatorException(parentModule.getIdentifier(), "not defined in model"));

        for (Object connectedPoint : parentModule.getOutputConnectedPoints()) {
            for (Module module : ((ModuleConnectedPoint) connectedPoint).getDependencies())
                continueSearch(module, simulationModel, exceptions);
        }
    }

    private void validateEntitiesName(ISimulationModel simulationModel, List<ValidatorException> exceptions) {
        for (RootModule rootModuleA : simulationModel.getRootModules()) {
            for (RootModule rootModuleB : simulationModel.getRootModules()) {
                if (rootModuleA.getIdentifier().equals(rootModuleB.getIdentifier()))
                    continue;

                if (rootModuleA.getSettings().getBaseEntityName().equals(rootModuleB.getSettings().getBaseEntityName()))
                    exceptions.add(new ValidatorException(rootModuleA.getIdentifier(), "duplicity entity name with " + rootModuleB.getIdentifier()));
            }
        }
    }
}
