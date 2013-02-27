package jDistsim.core.modules.lib;

import jDistsim.core.modules.Module;
import jDistsim.core.modules.ModuleConfiguration;
import jDistsim.core.modules.RootModule;
import jDistsim.core.simulation.simulator.ISimulator;
import jDistsim.core.simulation.simulator.SimulatorOutput;
import jDistsim.core.simulation.simulator.entity.Attribute;
import jDistsim.core.simulation.simulator.entity.AttributeCollection;
import jDistsim.core.simulation.simulator.entity.Entity;
import jDistsim.core.simulation.simulator.exception.EntityNotCreatedException;
import jDistsim.ui.module.ModuleView;
import jDistsim.utils.common.Counter;

/**
 * Author: Jirka Pénzeš
 * Date: 21.2.13
 * Time: 22:36
 */
public class CreateModule extends RootModule {

    public enum TimeBetweenArrivalsType {
        Constant, Random_Expo
    }

    private AttributeCollection initialEntityAttributes;
    private Counter entityCounter;

    private String baseEntityName;
    private TimeBetweenArrivalsType arrivalsType;
    private double arrivalsTypeValue;
    private int entityPerInterval;
    private double maxArrivals;
    private String iconName;

    public CreateModule(ModuleView view, ModuleConfiguration moduleConfiguration) {
        super(view, moduleConfiguration);
    }

    @Override
    protected void initializeDefaultValues() {
        entityCounter = new Counter();
        baseEntityName = "entity_" + entityCounter.nextValue();
        arrivalsType = TimeBetweenArrivalsType.Constant;
        arrivalsTypeValue = 1;
        maxArrivals = Double.POSITIVE_INFINITY;
        entityPerInterval = 1;
        iconName = "box";
        setFirsCreation(0.0);

        initialEntityAttributes = new AttributeCollection();
        initialEntityAttributes.put("creator", getLongIdentifier());
        initialEntityAttributes.put("iconName", iconName);
    }

    @Override
    protected void resetStates(ISimulator simulator) {
        entityCounter = new Counter();
    }

    private Entity makeEntity(ISimulator simulator) {
        String entityName = baseEntityName + entityCounter.nextValue();
        Entity entity = new Entity(entityName, getIdentifier(), simulator.getEnvironment().getModelName());
        for (Attribute attribute : initialEntityAttributes) {
            entity.getAttributes().put(attribute);
        }
        entity.getAttributes().put("iconName", getIconName());
        entity.getAttributes().put("creationTime", String.valueOf(simulator.getLocalTime()));

        return entity;
    }

    @Override
    public void logic(ISimulator simulator) {
        double currentTime = simulator.getLocalTime();

        if (entityCounter.getCurrentValue() >= maxArrivals) {
            simulator.getOutput().sendToOutput(SimulatorOutput.MessageType.Warning, "Entity not created -> max arrivals -> true");
            return;
        }

        for (Module module : getAllOutputDependencies()) {
            for (int index = 0; index < entityPerInterval; index++) {
                Entity entity = makeEntity(simulator);
                entity.getAttributes().put("currentModule", getIdentifier());
                simulator.plan(currentTime, module, entity);
            }
        }

        try {
            simulator.plan(currentTime + arrivalsTypeValue, (Module) clone(), null);
        } catch (CloneNotSupportedException exception) {
            throw new EntityNotCreatedException(getIdentifier());
        }
    }

    public String getBaseEntityName() {
        return baseEntityName;
    }

    public void setBaseEntityName(String baseEntityName) {
        this.baseEntityName = baseEntityName;
    }

    public TimeBetweenArrivalsType getArrivalsType() {
        return arrivalsType;
    }

    public void setArrivalsType(TimeBetweenArrivalsType arrivalsType) {
        this.arrivalsType = arrivalsType;
    }

    public double getArrivalsTypeValue() {
        return arrivalsTypeValue;
    }

    public void setArrivalsTypeValue(double arrivalsTypeValue) {
        this.arrivalsTypeValue = arrivalsTypeValue;
    }

    public int getEntityPerInterval() {
        return entityPerInterval;
    }

    public void setEntityPerInterval(int entityPerInterval) {
        this.entityPerInterval = entityPerInterval;
    }

    public double getMaxArrivals() {
        return maxArrivals;
    }

    public void setMaxArrivals(double maxArrivals) {
        this.maxArrivals = maxArrivals;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }
}
