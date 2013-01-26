package jDistsim.demo;

/**
 * Author: Jirka Pénzeš
 * Date: 26.12.12
 * Time: 20:03
 */
public class DisposeModule extends Module {
    public DisposeModule(String identifier) {
        super(identifier);
    }

    @Override
    public void execute(Simulator simulator, Entity entity) {
        //System.out.println("Dispose time: " + simulator.getLocalTime() + "-> entity: " + getEntity().getIdentifier());
    }
}
