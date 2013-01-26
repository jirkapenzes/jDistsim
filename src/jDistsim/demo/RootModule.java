package jDistsim.demo;

/**
 * Author: Jirka Pénzeš
 * Date: 26.12.12
 * Time: 16:27
 */
public abstract class RootModule extends Module {

    public RootModule(String identifier) {
        super(identifier);
    }

    @Override
    public void execute(Simulator simulator, Entity entity) {
        execute(simulator);
    }

    public abstract void execute(Simulator simulator);
}