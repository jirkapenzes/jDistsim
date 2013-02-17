package jDistsim.core.modules;

import jDistsim.core.modules.common.ModuleProperties;
import jDistsim.core.modules.common.ModuleProperty;
import jDistsim.ui.module.ModuleView;
import jDistsim.utils.collection.ReadOnlyList;
import jDistsim.utils.collection.observable.ObservableList;
import jDistsim.utils.logging.Logger;
import jDistsim.utils.pattern.observer.IObserver;
import jDistsim.utils.pattern.observer.Observable;


/**
 * Author: Jirka Pénzeš
 * Date: 24.11.12
 * Time: 12:16
 */
public class Module extends Observable implements IObserver {

    private String identifier;
    private final ModuleView view;
    private final ObservableList<ModuleConnectedPoint> inputConnectedPoints;
    private final ObservableList<ModuleConnectedPoint> outputConnectedPoints;
    private final ModuleProperties properties;
    private boolean createModule;

    public Module(ModuleView view, ModuleConfiguration moduleConfiguration, boolean createModule) {
        this.identifier = moduleConfiguration.getBaseIdentifier();
        this.view = view;
        this.createModule = createModule;
        this.properties = new ModuleProperties(this);

        inputConnectedPoints = new ObservableList<>(this);
        outputConnectedPoints = new ObservableList<>(this);

        initialize();
    }

    private void initialize() {
        properties.set(new ModuleProperty("identifier", getIdentifier(), "identifier"));
        properties.set(new ModuleProperty("correct", false, "correct"));

        setInputPointsProperties();
        setOutputPointsProperties();
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
        properties.get("identifier").setValue(identifier);
        //notifyObservers("identifier");
    }

    public String getIdentifier() {
        return identifier;
    }

    public ModuleView getView() {
        return view;
    }

    public ModuleProperties getProperties() {
        return properties;
    }

    public void addInputPoint(ModuleConnectedPoint moduleConnectedPoint) {
        inputConnectedPoints.add(moduleConnectedPoint);
    }

    public void addOutputPoint(ModuleConnectedPoint moduleConnectedPoint) {
        outputConnectedPoints.add(moduleConnectedPoint);
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

    public boolean canOutputConnected() {
        for (ModuleConnectedPoint moduleConnectedPoint : outputConnectedPoints) {
            if (moduleConnectedPoint.canBeConnected())
                return true;
        }
        return false;
    }

    public boolean isCreateModule() {
        return createModule;
    }

    @Override
    public void update(Observable observable, Object arguments) {
        Logger.log("module -> update");
        if (observable == inputConnectedPoints)
            setInputPointsProperties();

        if (observable == outputConnectedPoints)
            setOutputPointsProperties();

        Logger.log("module property changed");
        notifyObservers("propertyChanged");
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
    }

    public void refreshProperties() {
        setInputPointsProperties();
        setOutputPointsProperties();
    }
}
