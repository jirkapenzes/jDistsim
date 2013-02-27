package jDistsim.core.modules;

import jDistsim.core.simulation.simulator.ISimulator;
import jDistsim.core.simulation.simulator.entity.Entity;
import jDistsim.ui.module.ModuleView;

/**
 * Author: Jirka Pénzeš
 * Date: 21.2.13
 * Time: 22:35
 */
public abstract class RootModule extends Module {

    private double firsCreation;

    public RootModule(ModuleView view, ModuleConfiguration moduleConfiguration) {
        super(view, moduleConfiguration);
    }

    public abstract void logic(ISimulator simulator);

    @Override
    public void logic(ISimulator simulator, Entity entity) {
        logic(simulator);
    }

    @Override
    protected void preExecute(ISimulator simulator, Entity entity) {
    }

    public double getFirsCreation() {
        return firsCreation;
    }

    public void setFirsCreation(double firsCreation) {
        this.firsCreation = firsCreation;
    }
}
