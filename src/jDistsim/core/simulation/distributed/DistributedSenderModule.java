package jDistsim.core.simulation.distributed;

/**
 * Author: Jirka Pénzeš
 * Date: 7.3.13
 * Time: 21:18
 */
public abstract class DistributedSenderModule extends DistributedModule<SenderSettings> {

    public DistributedSenderModule(SenderSettings senderSettings, boolean defaultInitialize) {
        super(senderSettings, defaultInitialize);
    }
}
