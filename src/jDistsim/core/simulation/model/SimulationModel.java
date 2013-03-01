package jDistsim.core.simulation.model;

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
    private HashMap<String, Module> modules;
    private String modelName;

    public SimulationModel(List<Module> modules,String modelName) {
        this.modules = new HashMap<>();
        this.rootModules = new ArrayList<>();
        this.modelName = modelName;

        initialize(modules);
    }

    private void initialize(List<Module> modules) {
        for (Module module : modules) {
            if (module instanceof RootModule) {
                rootModules.add((RootModule) module);
            }
            this.modules.put(module.getIdentifier(), module);
        }
    }

    @Override
    public String getModelName() {
        return modelName;
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
