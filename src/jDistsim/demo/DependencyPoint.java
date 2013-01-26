package jDistsim.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 27.12.12
 * Time: 14:51
 */
public class DependencyPoint {

    private List<Module> dependencies;

    public DependencyPoint() {
        this.dependencies = new ArrayList<>();
    }

    public void addDependency(Module module) {
        dependencies.add(module);
    }

    public Iterable<Module> getDependencies() {
        return dependencies;
    }

}
