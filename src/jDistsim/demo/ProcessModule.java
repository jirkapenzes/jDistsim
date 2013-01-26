package jDistsim.demo;

/**
 * Author: Jirka Pénzeš
 * Date: 26.12.12
 * Time: 20:01
 */
public class ProcessModule extends Module {

    private double interval;

    public ProcessModule(String identifier, double interval) {
        super(identifier);
        this.interval = interval;
        createDependencyPoint();
    }

    @Override
    public void execute(Simulator simulator, Entity entity) {
        double currentTime = simulator.getLocalTime();
        for (Module module : getAllDependencies()) {
            simulator.plan(currentTime + interval, module, entity);
        }
    }
}
