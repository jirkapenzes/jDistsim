package jDistsim.core.simulation.modules;

import jDistsim.core.simulation.simulator.ISimulator;
import jDistsim.core.simulation.simulator.entity.Entity;

/**
 * Author: Jirka Pénzeš
 * Date: 21.2.13
 * Time: 22:35
 */
public abstract class RootModule extends Module {

    public enum TimeBetweenArrivalsType {
        Constant, Random_Expo
    }

    protected double firsCreation;
    protected String baseEntityName;
    protected TimeBetweenArrivalsType arrivalsType;
    protected double arrivalsTypeValue;
    protected int entityPerInterval;
    protected double maxArrivals;
    protected String iconName;

    public RootModule(ModuleConfiguration moduleConfiguration) {
        super(moduleConfiguration);
        createdModule = true;
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
        setChanged();
    }

    public String getBaseEntityName() {
        return baseEntityName;
    }

    public void setBaseEntityName(String baseEntityName) {
        this.baseEntityName = baseEntityName;
        setChanged();
    }

    public TimeBetweenArrivalsType getArrivalsType() {
        return arrivalsType;
    }

    public void setArrivalsType(TimeBetweenArrivalsType arrivalsType) {
        this.arrivalsType = arrivalsType;
        setChanged();
    }

    public double getArrivalsTypeValue() {
        return arrivalsTypeValue;
    }

    public void setArrivalsTypeValue(double arrivalsTypeValue) {
        this.arrivalsTypeValue = arrivalsTypeValue;
        setChanged();
    }

    public int getEntityPerInterval() {
        return entityPerInterval;
    }

    public void setEntityPerInterval(int entityPerInterval) {
        this.entityPerInterval = entityPerInterval;
        setChanged();
    }

    public double getMaxArrivals() {
        return maxArrivals;
    }

    public void setMaxArrivals(double maxArrivals) {
        this.maxArrivals = maxArrivals;
        setChanged();
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
        setChanged();
    }

}
