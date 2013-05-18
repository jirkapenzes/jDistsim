package jDistsim.core.simulation.modules;

import jDistsim.application.designer.common.Application;
import jDistsim.core.simulation.modules.common.ModuleProperties;
import jDistsim.core.simulation.modules.common.ModuleProperty;
import jDistsim.core.simulation.simulator.ISimulator;
import jDistsim.core.simulation.simulator.entity.Attribute;
import jDistsim.core.simulation.simulator.entity.Entity;
import jDistsim.utils.collection.ReadOnlyList;
import jDistsim.utils.collection.observable.ObservableList;
import jDistsim.utils.logging.Logger;
import jDistsim.utils.pattern.observer.IObserver;
import jDistsim.utils.pattern.observer.Observable;

import java.util.ArrayList;
import java.util.List;


/**
 * Author: Jirka Pénzeš
 * Date: 24.11.12
 * Time: 12:16
 */
public abstract class Module<Settings extends ModuleSettings> extends Observable implements IObserver, Cloneable {

    protected boolean createdModule = false;
    protected Settings settings;
    private final ObservableList<ModuleConnectedPoint> inputConnectedPoints;
    private final ObservableList<ModuleConnectedPoint> outputConnectedPoints;
    private final ModuleProperties properties;
    private Module module;

    public Module(Settings moduleSettings, boolean defaultInitialize) {
        this.settings = moduleSettings;
        this.properties = new ModuleProperties();

        inputConnectedPoints = new ObservableList<>(this);
        outputConnectedPoints = new ObservableList<>(this);

        preInitialization();
        if (defaultInitialize) initializeDefaultValues();
        initialize();
    }

    protected abstract void preInitialization();

    protected abstract void initializeDefaultValues();

    private void initialize() {
        properties.set(new ModuleProperty("identifier", settings.getIdentifier(), "identifier"));
        properties.set(new ModuleProperty("correct", false, "correct"));

        setInputPointsProperties();
        setOutputPointsProperties();
        setProperty();
    }

    public void initializeForSimulation(ISimulator simulator) {
        resetBaseStates(simulator);
        resetStates(simulator);
    }

    protected abstract void resetStates(ISimulator simulator);

    private void resetBaseStates(ISimulator simulator) {
    }

    public void execute(ISimulator simulator, Entity entity) {
        preExecute(simulator, entity);
        logic(simulator, entity);
    }

    protected abstract void logic(ISimulator simulator, Entity entity);

    protected void preExecute(ISimulator simulator, Entity entity) {
        Attribute attribute = entity.getAttributes().get("modelLifeCycle");
        if (attribute == null) {
            entity.getAttributes().put("modelLifeCycle", settings.getIdentifier());
        } else {
            entity.getAttributes().put("modelLifeCycle", attribute.getValue() + "->" + settings.getIdentifier());
        }
        entity.getAttributes().put("distributedLifeCycle", "->" + getLongIdentifier());
    }

    public String getLongIdentifier() {
        return Application.global().getModelName() + "." + settings.getIdentifier();
    }

    public ModuleProperties getProperties() {
        return properties;
    }

    public Iterable<Module<Settings>> getAllOutputDependencies() {
        List<Module<Settings>> allDependencies = new ArrayList<>();
        for (ModuleConnectedPoint connectedPoint : getOutputConnectedPoints()) {
            for (Module module : connectedPoint.getDependencies()) {
                allDependencies.add(module);
            }
        }
        return allDependencies;
    }

    public Iterable<Module<Settings>> getAllInputDependencies() {
        List<Module<Settings>> allDependencies = new ArrayList<>();
        for (ModuleConnectedPoint connectedPoint : getInputConnectedPoints()) {
            for (Module module : connectedPoint.getDependencies()) {
                allDependencies.add(module);
            }
        }
        return allDependencies;
    }

    public void addInputPoint(ModuleConnectedPoint moduleConnectedPoint) {
        inputConnectedPoints.add(moduleConnectedPoint);
        inputConnectedPoints.notifyObservers();
    }

    public void addOutputPoint(ModuleConnectedPoint moduleConnectedPoint) {
        outputConnectedPoints.add(moduleConnectedPoint);
        outputConnectedPoints.notifyObservers();
    }

    public ReadOnlyList<ModuleConnectedPoint> getInputConnectedPoints() {
        return inputConnectedPoints;
    }

    public ReadOnlyList<ModuleConnectedPoint> getOutputConnectedPoints() {
        return outputConnectedPoints;
    }

    public boolean canInputConnected() {
        for (ModuleConnectedPoint moduleConnectedPoint : inputConnectedPoints) {
            if (moduleConnectedPoint.canBeConnected())
                return true;
        }
        return false;
    }

    public void addOutputDependency(Module module) {
        try {
            outputConnectedPoints.get(0).addDependency(module);
        } catch (Exception e) {
            Logger.log(e);
        }
    }

    public boolean canOutputConnected() {
        for (ModuleConnectedPoint moduleConnectedPoint : outputConnectedPoints) {
            if (moduleConnectedPoint.canBeConnected())
                return true;
        }
        return false;
    }

    public boolean isCreateModule() {
        return createdModule;
    }

    public Settings getSettings() {
        return settings;
    }

    @Override
    public void update(Observable observable, Object arguments) {
        Logger.log("module -> update");
        if (observable == inputConnectedPoints)
            setInputPointsProperties();

        if (observable == outputConnectedPoints)
            setOutputPointsProperties();

        rebuild();
    }

    public void rebuild() {
        properties.set(new ModuleProperty("correct", isValid(), "correct"));
        properties.get("identifier").setValue(settings.getIdentifier());
        setProperty();
        setChanged();
        notifyObservers();
    }

    private void setOutputPointsProperties() {
        Logger.log("set output points");
        properties.set(new ModuleProperty("outputPoints.size", outputConnectedPoints.size(), "count of output points"));
        for (int index = 0; index < outputConnectedPoints.size(); index++) {
            ModuleConnectedPoint connectedPoint = outputConnectedPoints.get(index);

            String description = "output point " + (index + 1);
            String pointName = "outputPoint_" + (index + 1);

            properties.set(new ModuleProperty(pointName + ".maxCapacity", connectedPoint.getCapacity(), description + " max capacity"));
            properties.set(new ModuleProperty(pointName + ".isFull", connectedPoint.isFull(), description + " is full"));
            properties.set(new ModuleProperty(pointName + ".occupied", connectedPoint.getDependencies().size(), description + " occupied"));
        }
        hasChanged();
    }

    private void setInputPointsProperties() {
        Logger.log("set input points");
        properties.set(new ModuleProperty("inputPoints.size", inputConnectedPoints.size(), "count of input points"));
        for (int index = 0; index < inputConnectedPoints.size(); index++) {
            ModuleConnectedPoint connectedPoint = inputConnectedPoints.get(index);

            String description = "input point " + (index + 1);
            String pointName = "inputPoint_" + (index + 1);

            properties.set(new ModuleProperty(pointName + ".maxCapacity", connectedPoint.getCapacity(), description + " max capacity"));
            properties.set(new ModuleProperty(pointName + ".isFull", connectedPoint.isFull(), description + " is full"));
            properties.set(new ModuleProperty(pointName + ".occupied", connectedPoint.getDependencies().size(), description + " occupied"));
        }
        hasChanged();
    }

    protected abstract void setProperty();

    public boolean isValid() {
        for (ModuleConnectedPoint connectedPoint : getInputConnectedPoints()) {
            if (connectedPoint.getDependencies().size() == 0)
                return false;
        }
        for (ModuleConnectedPoint connectedPoint : getOutputConnectedPoints()) {
            if (connectedPoint.getDependencies().size() == 0)
                return false;
        }
        return true;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            final Module result = (Module) super.clone();
            return result;
        } catch (final CloneNotSupportedException ex) {
            throw new AssertionError();
        }
    }

    public String getIdentifier() {
        return settings.getIdentifier();
    }
}
