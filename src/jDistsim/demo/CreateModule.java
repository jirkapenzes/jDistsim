package jDistsim.demo;

/**
 * Author: Jirka Pénzeš
 * Date: 23.12.12
 * Time: 20:42
 */
public class CreateModule extends RootModule {

    private double interval;
    public static int counter = 0;

    public CreateModule(String identifier, double interval) {
        super(identifier);
        this.interval = interval;
        createDependencyPoint();
    }

    public double getInterval() {
        return interval;
    }

    public void setInterval(double interval) {
        this.interval = interval;
    }

    @Override
    public void execute(Simulator simulator) {
        double currentTime = simulator.getLocalTime();
        Entity entity = new Entity("entityId: " + counter);
        entity.addAttribute("order", counter);
        counter++;

        for (Module module : getAllDependencies()) {
            simulator.plan(currentTime, module, entity);
        }

        try {
            simulator.plan(currentTime + interval, (Module) clone(), null);
        } catch (CloneNotSupportedException e) {
        }
    }
}
