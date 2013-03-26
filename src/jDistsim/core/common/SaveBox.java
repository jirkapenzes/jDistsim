package jDistsim.core.common;

import jDistsim.application.designer.common.LocalNetworkSettings;
import jDistsim.core.simulation.distributed.DistributedModelDefinition;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 23.3.13
 * Time: 12:08
 */
public class SaveBox {

    private List<SaveContainer> containers;
    private List<DistributedModelDefinition> remotes;
    private LocalNetworkSettings networkSettings;

    public SaveBox() {
        containers = new ArrayList<>();
        remotes = new ArrayList<>();
    }

    public void store(SaveContainer saveContainer) {
        containers.add(saveContainer);
    }

    public void store(DistributedModelDefinition modelDefinition) {
        remotes.add(modelDefinition);
    }

    public List<SaveContainer> getModelData() {
        return containers;
    }

    public List<DistributedModelDefinition> getRemotes() {
        return remotes;
    }

    public LocalNetworkSettings getNetworkSettings() {
        return networkSettings;
    }

    public void setNetworkSettings(LocalNetworkSettings networkSettings) {
        this.networkSettings = networkSettings;
    }
}
