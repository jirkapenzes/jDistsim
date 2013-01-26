package jDistsim.demo;

import java.util.*;

/**
 * Author: Jirka Pénzeš
 * Date: 21.12.12
 * Time: 22:19
 */
public class SimulationModel implements ISimulationModel {

    private List<RootModule> rootModules;
    private HashMap<String, Module> modules;

    public SimulationModel() {
        rootModules = new ArrayList<>();
        modules = new HashMap<>();
    }

    @Override
    public void addRootModule(RootModule rootModule) {
        add(rootModule);
        rootModules.add(rootModule);
    }

    private void add(Module module) {
        for (Module dependencyModule : module.getAllDependencies()) {
            add(dependencyModule);
        }
        modules.put(module.getIdentifier(), module);
    }

    @Override
    public Iterable<RootModule> getRootModules() {
        return rootModules;
    }

    @Override
    public String toString() {
        return modules.toString();
    }
}
