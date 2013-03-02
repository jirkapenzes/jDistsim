package jDistsim.core.simulation.distributed;

/**
 * Author: Jirka Pénzeš
 * Date: 1.3.13
 * Time: 23:14
 */
public class DistributedModelDefinition {

    private int port;
    private String modelName;
    private String rmiModelName;
    private boolean lookahead;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getRmiModelName() {
        return rmiModelName;
    }

    public void setRmiModelName(String rmiModelName) {
        this.rmiModelName = rmiModelName;
    }

    public boolean isLookahead() {
        return lookahead;
    }

    public void setLookahead(boolean lookahead) {
        this.lookahead = lookahead;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
