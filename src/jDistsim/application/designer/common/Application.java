package jDistsim.application.designer.common;

import jDistsim.core.simulation.distributed.DistributedModelDefinition;
import jDistsim.utils.collection.observable.ObservableHashMap;
import jDistsim.utils.pattern.observer.Observable;

/**
 * Author: Jirka Pénzeš
 * Date: 10.2.13
 * Time: 15:23
 */
public class Application extends Observable {

    private static Application instance;
    private static Object lock = new Object();

    private String modelName = "model1.jdsim";
    private String modelFullPath = "";
    private ObservableHashMap<String, DistributedModelDefinition> distributedModels;
    private LocalNetworkSettings networkSettings;

    private Application() {
        distributedModels = new ObservableHashMap<>();
        networkSettings = LocalNetworkSettings.createNull();
    }

    public static Application global() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new Application();
                }
            }
        }
        return instance;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
        setChanged();
    }

    public String getModelFullPath() {
        return modelFullPath;
    }

    public void setModelFullPath(String modelFullPath) {
        this.modelFullPath = modelFullPath;
        setChanged();
    }

    public ObservableHashMap<String, DistributedModelDefinition> getDistributedModels() {
        return distributedModels;
    }

    public LocalNetworkSettings getNetworkSettings() {
        return networkSettings;
    }
}
