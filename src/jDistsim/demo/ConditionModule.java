package jDistsim.demo;

/**
 * Author: Jirka Pénzeš
 * Date: 27.12.12
 * Time: 15:22
 */
public class ConditionModule extends Module {

    private ConditionPredicate conditionPredicate;

    public ConditionModule(String identifier, ConditionPredicate conditionPredicate) {
        super(identifier);
        this.conditionPredicate = conditionPredicate;
        createDependencyPoint();
        createDependencyPoint();
    }

    @Override
    public void execute(Simulator simulator, Entity entity) {
        if (conditionPredicate.match(entity)) {
            trueBranch(simulator, entity);
        } else {
            elseBranch(simulator, entity);
        }
    }

    private void trueBranch(Simulator simulator, Entity entity) {
        double currentTime = simulator.getLocalTime();
        for (Module module : getDependencyPoints().get(0).getDependencies()) {
            simulator.plan(currentTime, module, entity);
        }
    }

    private void elseBranch(Simulator simulator, Entity entity) {
        double currentTime = simulator.getLocalTime();
        for (Module module : getDependencyPoints().get(1).getDependencies()) {
            simulator.plan(currentTime, module, entity);
        }
    }
}

