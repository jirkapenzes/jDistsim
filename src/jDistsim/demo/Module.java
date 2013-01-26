package jDistsim.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 23.12.12
 * Time: 20:42
 */
public abstract class Module implements Cloneable {

    private String identifier;
    private List<DependencyPoint> dependencyPoints;

    public Module(String identifier) {
        this.identifier = identifier;
        dependencyPoints = new ArrayList<>();
    }

    public String getIdentifier() {
        return identifier;
    }

    // default index is zero
    public void connect(Module module) {
        connect(0, module);
    }

    public void connect(int indexPoint, Module module) {
        dependencyPoints.get(indexPoint).addDependency(module);
    }

    public List<DependencyPoint> getDependencyPoints() {
        return Collections.unmodifiableList(dependencyPoints);
    }

    public Iterable<Module> getAllDependencies() {
        List<Module> allDependencies = new ArrayList<>();
        for (DependencyPoint dependencyPoint : getDependencyPoints()) {
            for (Module module : dependencyPoint.getDependencies()) {
                allDependencies.add(module);
            }
        }
        return allDependencies;
    }

    public abstract void execute(Simulator simulator, Entity entity);

    protected void createDependencyPoint() {
        dependencyPoints.add(new DependencyPoint());
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            final Module result = (Module) super.clone();
            return result;
        } catch (final CloneNotSupportedException ex) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return getIdentifier();
    }
}
