package jDistsim.core.simulation.distributed;

import jDistsim.core.simulation.modules.ModuleConfiguration;
import jDistsim.ui.module.ModuleView;

/**
 * Author: Jirka Pénzeš
 * Date: 7.3.13
 * Time: 22:55
 */
public abstract class DistributedReceiveModule extends DistributedModule {

    protected String authorizedEntityName;

    public DistributedReceiveModule(ModuleView view, ModuleConfiguration moduleConfiguration) {
        super(view, moduleConfiguration);
    }

    public String getAuthorizedEntityName() {
        return authorizedEntityName;
    }

    public void setAuthorizedEntityName(String authorizedEntityName) {
        this.authorizedEntityName = authorizedEntityName;
        setChanged();
    }
}
