package jDistsim.core.simulation.model;

import jDistsim.core.simulation.distributed.DistributedReceiveModule;
import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.RootModule;
import jDistsim.core.simulation.simulator.ISimulationModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 21.2.13
 * Time: 22:14
 */
public class SimulationModel implements ISimulationModel {

    private List<RootModule> rootModules;
    private HashMap<String, DistributedReceiveModule> receiveModules;
    private HashMap<String, Module> modules;
    private String modelName;

    public SimulationModel(List<Module> modules, String modelName) {
        this.modules = new HashMap<>();
        this.receiveModules = new HashMap<>();
        this.rootModules = new ArrayList<>();
        this.modelName = modelName;

        initialize(modules);
    }

    private void initialize(List<Module> modules) {
        for (Module module : modules) {
            if (module instanceof RootModule) {
                rootModules.add((RootModule) module);
            }
            if (module instanceof DistributedReceiveModule) {
                DistributedReceiveModule distributedReceiveModule = (DistributedReceiveModule) module;
                receiveModules.put(distributedReceiveModule.getSettings().getAuthorizedEntityName(), distributedReceiveModule);
            }
            this.modules.put(module.getIdentifier(), module);
        }
    }

    @Override
    public String getModelName() {
        return modelName;
    }

    @Override
    public DistributedReceiveModule getReceiver(String entityName) {
        if (receiveModules.containsKey(entityName))
            return receiveModules.get(entityName);
        return null;
    }

    @Override
    public int getNumberOfRootModules() {
        return rootModules.size();
    }

    @Override
    public Iterable<RootModule> getRootModules() {
        return rootModules;
    }

    @Override
    public Iterable<Module> getModules() {
        return modules.values();
    }

    @Override
    public boolean containsModule(String identifier) {
        return modules.containsKey(identifier);
    }
}
