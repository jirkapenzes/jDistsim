package jDistsim.core.simulation.modules.ui;

import jDistsim.application.designer.controller.modelSpaceFeature.util.ConnectorLine;

/**
 * Author: Jirka Pénzeš
 * Date: 1.2.13
 * Time: 0:14
 */
public class ModuleDependencyUI {

    private ModuleUI moduleUI;
    private ModuleConnectedPointUI moduleConnectedPointUI;
    private ConnectorLine connectorLine;

    public ModuleDependencyUI(ModuleUI moduleUI, ModuleConnectedPointUI moduleConnectedPointUI, ConnectorLine connectorLine) {
        this.moduleUI = moduleUI;
        this.moduleConnectedPointUI = moduleConnectedPointUI;
        this.connectorLine = connectorLine;
    }

    public void invalide() {

    }

    public ModuleUI getModuleUI() {
        return moduleUI;
    }

    public ModuleConnectedPointUI getModuleConnectedPointUI() {
        return moduleConnectedPointUI;
    }

    public ConnectorLine getConnectorLine() {
        return connectorLine;
    }
}
