package jDistsim.core.simulation.simulator;

import jDistsim.core.simulation.simulator.entity.Attribute;
import jDistsim.core.simulation.simulator.entity.AttributeCollection;
import jDistsim.utils.pattern.observer.Observable;

/**
 * Author: Jirka Pénzeš
 * Date: 18.2.13
 * Time: 21:38
 */
public class SimulatorEnvironment extends Observable {

    private AttributeCollection simulatorAttributes;
    private AttributeCollection modulesAttributes;
    private AttributeCollection networkAttributes;
    private AttributeCollection distributedAttributes;

    private String modelName;
    private double localTime;

    public SimulatorEnvironment() {
        simulatorAttributes = new AttributeCollection();
        modulesAttributes = new AttributeCollection();
        networkAttributes = new AttributeCollection();
        distributedAttributes = new AttributeCollection();
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public double getLocalTime() {
        return localTime;
    }

    public void setLocalTime(double localTime) {
        this.localTime = localTime;
    }

    public void setSimulatorAtt(String name, Object value) {
        simulatorAttributes.set(new Attribute(name, value.toString()));
        setChanged();
    }

    public void setModulesAtt(String name, Object value) {
        modulesAttributes.set(new Attribute(name, value.toString()));
        setChanged();
    }


    public void setNetworkAtt(String name, Object value) {
        networkAttributes.set(new Attribute(name, value.toString()));
        setChanged();
    }


    public void setDistributedAtt(String name, Object value) {
        distributedAttributes.set(new Attribute(name, value.toString()));
        setChanged();
    }


    public Iterable<Attribute> getSimulatorAttributes() {
        return simulatorAttributes;
    }

    public Iterable<Attribute> getModulesAttributes() {
        return modulesAttributes;
    }

    public Iterable<Attribute> getNetworkAttributes() {
        return networkAttributes;
    }

    public Iterable<Attribute> getDistributedAttributes() {
        return distributedAttributes;
    }

    public void clear() {
        simulatorAttributes.clear();
        modulesAttributes.clear();
        networkAttributes.clear();
        distributedAttributes.clear();
        setChanged();
    }
}
