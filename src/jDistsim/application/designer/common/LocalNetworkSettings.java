package jDistsim.application.designer.common;

import jDistsim.utils.pattern.observer.Observable;

/**
 * Author: Jirka Pénzeš
 * Date: 8.3.13
 * Time: 17:43
 */
public class LocalNetworkSettings extends Observable {

    private String modelName;
    private int port;

    public LocalNetworkSettings() {
    }

    public LocalNetworkSettings(String modelName, int port) {
        this.modelName = modelName;
        this.port = port;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
        setChanged();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
        setChanged();
    }

    public static LocalNetworkSettings createNull() {
        return new LocalNetworkSettings("model1", 1099);
    }

    @Override
    public String toString() {
        return "localhost:" + port + "/" + modelName;
    }
}
