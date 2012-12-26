package jDistsim.core.modules;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 4.12.12
 * Time: 21:48
 */
public class ModuleConnectedPoint {

    private int capacity;

    private List<Module> dependencies;

    public ModuleConnectedPoint(int capacity) {
        this.capacity = capacity;
        this.dependencies = new ArrayList<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public void addDependency(Module module) throws ModuleConnectedPointFullCapacityException {
        if (isFull()) throw new ModuleConnectedPointFullCapacityException("Capacity is full");
        dependencies.add(module);
    }

    public void removeDependency(Module module) {
        dependencies.remove(module);
    }

    public void removeDependency(String identifier) {
        for (Module module : dependencies) {
            if (module.equals(identifier)) {
                dependencies.remove(module);
                return;
            }
        }
    }

    public boolean isFull() {
        return dependencies.size() == capacity;
    }
}
