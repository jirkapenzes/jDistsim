package jDistsim.core.simulation.distributed;

import jDistsim.core.simulation.exception.DistributedException;
import jDistsim.utils.logging.Logger;

/**
 * Author: Jirka Pénzeš
 * Date: 7.3.13
 * Time: 22:55
 */
public abstract class DistributedReceiveModule extends DistributedModule<ReceiveSettings> {

    public DistributedReceiveModule(ReceiveSettings receiveSettings, boolean defaultInitialize) {
        super(receiveSettings, defaultInitialize);
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
