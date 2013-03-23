package jDistsim.core.simulation.distributed;

import jDistsim.utils.pattern.observer.Observable;

/**
 * Author: Jirka Pénzeš
 * Date: 1.3.13
 * Time: 23:14
 */
public class DistributedModelDefinition extends Observable {

    private String modelName;
    private String rmiModelName;
    private String address;
    private int port;
    private boolean lookahead;
    private boolean receive;

    public DistributedModelDefinition() {
        DistributedModelDefinition defaultValues = createNull();
        this.modelName = defaultValues.modelName;
        this.rmiModelName = defaultValues.rmiModelName;
        this.address = defaultValues.address;
        this.port = defaultValues.port;
        this.lookahead = defaultValues.lookahead;
        this.receive = defaultValues.receive;
    }

    public DistributedModelDefinition(String rmiModelName, String address, int port) {
        this();
        this.rmiModelName = rmiModelName;
        this.address = address;
        this.port = port;
    }

    public DistributedModelDefinition(String modelName, String rmiModelName, String address, int port, boolean lookahead, boolean receive) {
        this.modelName = modelName;
        this.rmiModelName = rmiModelName;
        this.address = address;
        this.port = port;
        this.lookahead = lookahead;
        this.receive = receive;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
        setChanged();
    }

    public String getRmiModelName() {
        return rmiModelName;
    }

    public void setRmiModelName(String rmiModelName) {
        this.rmiModelName = rmiModelName;
        setChanged();
    }

    public boolean isLookahead() {
        return lookahead;
    }

    public void setLookahead(boolean lookahead) {
        this.lookahead = lookahead;
        setChanged();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
        setChanged();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        setChanged();
    }

    public boolean isReceive() {
        return receive;
    }

    public void setReceive(boolean receive) {
        this.receive = receive;
        setChanged();
    }

    public static DistributedModelDefinition createDefault() {
        return new DistributedModelDefinition("Remote model", "remote_model1", "localhost", 1089, false, false);
    }

    public static DistributedModelDefinition createNull() {
        return new DistributedModelDefinition("null", "null", "null", 0, false, false);
    }

    @Override
    public String toString() {
        return getAddress() + ":" + getPort() + "/" + getRmiModelName() + "." + isLookahead();
    }
}
