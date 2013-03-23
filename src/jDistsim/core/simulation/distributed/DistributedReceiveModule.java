package jDistsim.core.simulation.distributed;

import jDistsim.core.simulation.exception.DistributedException;
import jDistsim.core.simulation.modules.ModuleConfiguration;
import jDistsim.utils.logging.Logger;

/**
 * Author: Jirka Pénzeš
 * Date: 7.3.13
 * Time: 22:55
 */
public abstract class DistributedReceiveModule extends DistributedModule {

    protected String authorizedEntityName;

    public DistributedReceiveModule(ModuleConfiguration moduleConfiguration) {
        super(moduleConfiguration);
    }

    public String getAuthorizedEntityName() {
        return authorizedEntityName;
    }

    public void setAuthorizedEntityName(String authorizedEntityName) {
        this.authorizedEntityName = authorizedEntityName;
        setChanged();
    }

    public DistributedReceiveModule clone() {
        try {
            return (DistributedReceiveModule) super.clone();
        } catch (CloneNotSupportedException e) {
            Logger.log(e);
            throw new DistributedException();
        }
    }
}
