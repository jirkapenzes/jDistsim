package jDistsim.core.simulation.modules.lib.create;

import jDistsim.core.simulation.exception.EntityNotCreatedException;
import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.RootModule;
import jDistsim.core.simulation.modules.RootSettings;
import jDistsim.core.simulation.modules.common.ModuleProperty;
import jDistsim.core.simulation.simulator.ISimulator;
import jDistsim.core.simulation.simulator.SimulatorOutput;
import jDistsim.core.simulation.simulator.entity.Attribute;
import jDistsim.core.simulation.simulator.entity.AttributeCollection;
import jDistsim.core.simulation.simulator.entity.Entity;
import jDistsim.utils.common.Counter;

import java.util.Random;

/**
 * Author: Jirka Pénzeš
 * Date: 21.2.13
 * Time: 22:36
 */
public class Create extends RootModule {

    private AttributeCollection initialEntityAttributes;
    private Counter entityCounter;
    private Random random;

    public Create(RootSettings rootSettings, boolean defaultInitialize) {
        super(rootSettings, defaultInitialize);
    }

    @Override
    protected void preInitialization() {
        entityCounter = new Counter();
        random = new Random(0);
        initialEntityAttributes = new AttributeCollection();
        initialEntityAttributes.put("creator", getLongIdentifier());
        initialEntityAttributes.put("iconName", settings.getIconName());
    }

    @Override
    protected void initializeDefaultValues() {
        settings.setBaseEntityName("entity_" + entityCounter.nextValue());
        settings.setArrivalsType(RootSettings.TimeBetweenArrivalsType.Constant);
        settings.setArrivalsTypeValue(1);
        settings.setMaxArrivals(Double.POSITIVE_INFINITY);
        settings.setEntityPerInterval(1);
        settings.setIconName("box");
        settings.setFirsCreation(0.0);
    }

    @Override
    protected void resetStates(ISimulator simulator) {
        entityCounter = new Counter();
    }

    @Override
    protected void setProperty() {
        if (settings.getArrivalsType() == null)
            settings.setArrivalsType(RootSettings.TimeBetweenArrivalsType.Constant);

        getProperties().set(new ModuleProperty("baseEntityName", settings.getBaseEntityName(), "entity name"));
        getProperties().set(new ModuleProperty("arrivalsType", settings.getArrivalsType() + "(" + settings.getArrivalsTypeValue() + ")", "arrivals type"));
        getProperties().set(new ModuleProperty("maxArrivals", settings.getMaxArrivals(), "max arrivals"));
        getProperties().set(new ModuleProperty("entityPerInterval", settings.getEntityPerInterval(), "per interval"));
        getProperties().set(new ModuleProperty("iconName", settings.getIconName(), "entity icon"));
        getProperties().set(new ModuleProperty("firstCreation", settings.getFirsCreation(), "first creation"));
    }

    private Entity makeEntity(ISimulator simulator) {
        String entityName = settings.getBaseEntityName() + entityCounter.nextValue();
        Entity entity = new Entity(entityName, settings.getIdentifier(), simulator.getEnvironment().getModelName());
        for (Attribute attribute : initialEntityAttributes) {
            entity.getAttributes().put(attribute);
        }
        entity.getAttributes().put("iconName", settings.getIconName());
        entity.getAttributes().put("creationTime", String.valueOf(simulator.getLocalTime()));
        entity.getAttributes().put("currentModule", settings.getIdentifier());
        return entity;
    }

    @Override
    public void logic(ISimulator simulator) {
        double currentTime = simulator.getLocalTime();

        for (Module module : getAllOutputDependencies()) {
            for (int index = 0; index < settings.getEntityPerInterval(); index++) {
                Entity entity = makeEntity(simulator);
                simulator.plan(currentTime, module, entity);
            }
        }

        try {
            if (entityCounter.getCurrentValue() >= settings.getMaxArrivals()) {
                simulator.getOutput().sendToOutput(SimulatorOutput.MessageType.Warning, "Entity not created -> max arrivals -> true");
                return;
            }
            double timeInterval = settings.getArrivalsTypeValue();
            if (settings.getArrivalsType() == RootSettings.TimeBetweenArrivalsType.Random_Expo)
                timeInterval = Math.round((random.nextDouble() * settings.getArrivalsTypeValue() + 1) * 100) / 100;

            simulator.plan(currentTime + timeInterval, (Module) clone(), null);
        } catch (CloneNotSupportedException exception) {
            throw new EntityNotCreatedException(settings.getIdentifier());
        }
    }
}
